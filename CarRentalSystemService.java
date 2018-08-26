package org.bytemark.bytewheels.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bytemark.bytewheels.dao.BookingDAOImpl;
import org.bytemark.bytewheels.dao.CarDAOImpl;
import org.bytemark.bytewheels.dao.DBConnection;
import org.bytemark.bytewheels.exceptions.CarNotAvailableExection;
import org.bytemark.bytewheels.models.Booking;
import org.bytemark.bytewheels.models.Car;
import org.bytemark.bytewheels.models.StringConstants;

/**
 * This is the Service cars containing the business logic to 
 * perform the requested operations of fetching and booking 
 * cars.
 */

public class CarRentalSystemService {

	DBConnection con=DBConnection.getInstance();
	CarDAOImpl cardao=new CarDAOImpl();
	BookingDAOImpl bookingdao=new BookingDAOImpl();
	static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

	/*
	 * Fetch All the cars hosted by bytewheels
	 */
	public List<Car> getAllCars() throws Exception{
		List<Car> cars=null;
		cars=cardao.getAllCars(con);	
		return cars;
	}

	/*
	 * Fetch All the cars hosted by bytewheels , category wise
	 */
	public List<Car> getAllCarsByCategory(String category) throws Exception{
		List<Car> cars=null;
		cars=cardao.getCarByCategory(category, con);	
		return cars;
	}

	/*
	 * Get All the cars hosted by bytewheels between a date range, category wise
	 */
	public List<Car> getAllCarsByCategoryAndAvailableDate(String category,String fromdate,String todate) throws Exception{
		
		InputDataValidation.validateInputDates(fromdate, todate);
		//check all booking table entries for requested date, return the cars not booked in the available range and mentioned category
		List<Car> cars=new ArrayList<Car>();
		Connection connection=con.getConnection();
		PreparedStatement stmt = connection.prepareStatement(StringConstants.GET_AVAILABLE_CARS_IN_DATE_RANGE);
		stmt.setString(1, fromdate);
		stmt.setString(2, todate);
		stmt.setString(3, fromdate);
		stmt.setString(4, todate);
		ResultSet rs = stmt.executeQuery();

		while(rs.next())
		{
			if(rs.getString("category").toLowerCase().equals(category.toLowerCase())){
				Car car=new Car();
				car.setId(rs.getInt("id"));
				car.setSerial_number(rs.getString("serial_number"));
				car.setModel_name(rs.getString("model_name"));
				car.setCategory(rs.getString("category"));
				car.setCost_per_day(rs.getInt("cost_per_day"));
				cars.add(car);
			}
		}

		if(cars.size()==0){
			throw new CarNotAvailableExection("No Cars in the requested category is available between the given dates");
		}
		connection.close();
		return cars;
	}
	
	
	/*
	 * Get All the cars hosted by bytewheels between a date range
	 */
	public List<Car> getAllCarsByAvailableDate(String fromdate,String todate) throws Exception{
		
		InputDataValidation.validateInputDates(fromdate, todate);
		Connection connection=con.getConnection();
		
		//check all booking table entries for requested date, return the cars not booked in the available range
		List<Car> cars=new ArrayList<Car>();
		PreparedStatement stmt = connection.prepareStatement(StringConstants.GET_AVAILABLE_CARS_IN_DATE_RANGE);
		stmt.setString(1, fromdate);
		stmt.setString(2, todate);
		stmt.setString(3, fromdate);
		stmt.setString(4, todate);
		ResultSet rs = stmt.executeQuery();

		while(rs.next())
		{
				Car car=new Car();
				car.setId(rs.getInt("id"));
				car.setSerial_number(rs.getString("serial_number"));
				car.setModel_name(rs.getString("model_name"));
				car.setCategory(rs.getString("category"));
				car.setCost_per_day(rs.getInt("cost_per_day"));
				cars.add(car);
		}


		if(cars.size()==0){
			connection.close();
			throw new CarNotAvailableExection("No Cars in the requested category is available between the given dates");
		}
		connection.close();
		return cars;
	}

	
	/*
	 * Book a car by providing emai, date and model name and get the details of your bookings
	 */
	public Booking bookCarByDateRange(Booking booking) throws Exception{
		System.out.println("calling service bookCarByDateRange()");
		
		String fromdate=booking.getFrom_date();
		String todate=booking.getTo_date();
		String modelName=booking.getModel_name();
		String email=booking.getEmail();
		
		//*****Validate input data*****
		InputDataValidation.validateBookingRequest(fromdate, todate, modelName, email);
				
		List<Car> cars=new CarRentalSystemService().getAllCarsByAvailableDate(fromdate, todate);
		Car carModelForBooking=null;
		for(Car car:cars){
			if(car.getModel_name().toLowerCase().contains(modelName.toLowerCase())){
				carModelForBooking=car;
				break;
			}
		}
		if(carModelForBooking==null){
			throw new CarNotAvailableExection("No Car under the requested Model Name is available for booking between the date range");
		}else{
			int costPerDay=carModelForBooking.getCost_per_day();
			
			Date startDate=sdf.parse(fromdate);
			Date endDate=sdf.parse(todate);
			
			long difference = endDate.getTime() - startDate.getTime();
		    float daysBetween = (difference / (1000*60*60*24));
		    
		    System.out.println("+++ NUMBER OF DAYS ARE : "+ daysBetween);
		    
		    int totalCost=(int)daysBetween*costPerDay;
			booking=bookingdao.addBooking(con, carModelForBooking.getSerial_number(), carModelForBooking.getModel_name(), email, fromdate, todate, totalCost,(int)daysBetween);
		}
		return booking;
	}
	
	/*
	 * Service to get booking details of a particular booking ID
	 */
	
	public Booking getBookingDetails(int bookingId) throws Exception{
		Booking booking=bookingdao.getBooking(con, bookingId);
		return booking; 
	}
	
}
