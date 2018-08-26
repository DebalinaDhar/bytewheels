package org.bytemark.bytewheels.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bytemark.bytewheels.models.Booking;
import org.bytemark.bytewheels.models.Car;
import org.bytemark.bytewheels.services.CarRentalSystemService;

/**
 * This the resource class that maps GET and POST requests 
 * to the respective service methods
 */

@Path("/rentalservice")
public class CarRentalSystemResource {

	CarRentalSystemService cs=new CarRentalSystemService();

	/*
	 * Resource to fetch all the car models owned by the company
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> getAllCars() throws Exception{
		System.out.println("calling resource getAllCars()");
		List<Car> cars=null;
		cars=cs.getAllCars();
		System.out.println("existing resource getAllCars()");
		return cars;
	}

	/*
	 * Resource to fetch all the car models owned by the company under a given category
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}")
	public List<Car> getAllCarsByCategory(@PathParam("category") String category) throws Exception{
		System.out.println("calling resource getAllCarsByCategory()");
		List<Car> cars=null;
		cars=cs.getAllCarsByCategory(category);
		System.out.println("exiting resource getAllCarsByCategory()");
		return cars;
	}

	/*
	 * Resource to fetch all the car models available under a specified category between the requested dates
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{category}/{fromdate}/{todate}")
	public List<Car> getAllCarsByCategoryAndAvailableDate(@PathParam("category") String category,
			@PathParam("fromdate") String fromdate,
			@PathParam("todate") String todate) throws Exception{
		System.out.println("calling resource getAllCarsByCategory()");
		List<Car> cars=null;
		cars=cs.getAllCarsByCategoryAndAvailableDate(category,fromdate,todate);
		System.out.println("exiting resource getAllCarsByCategory()");
		return cars;
	}

	/*
	 * Resource to fetch all the car models available under all category between the requested dates
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{fromdate}/{todate}")
	public List<Car> getAllCarsByCategoryAndAvailableDate(@PathParam("fromdate") String fromdate,
			@PathParam("todate") String todate) throws Exception{
		System.out.println("calling resource getAllCarsByCategory()");
		List<Car> cars=null;
		cars=cs.getAllCarsByAvailableDate(fromdate,todate);
		System.out.println("exiting resource getAllCarsByCategory()");
		return cars;
	}

	/*
	 * Resource to book a car with the based on the vehicle name and, email id and date range
	 * If the no item of the requested model type is available it shows user an error message confirming 
	 * the same.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Booking bookCarByDateRange(Booking booking) throws Exception{
		System.out.println("calling resource bookCarByDateRange()");
		booking=cs.bookCarByDateRange(booking);
		System.out.println("exiting resource bookCarByDateRange()");
		return booking;
	}
	
	/*
	 * Resource to get the booking details by a particular booking id
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/showBookingDetails/{bookingId}")
	public Booking getBookingDetails(@PathParam("bookingId") int bookingID) throws Exception{
		return cs.getBookingDetails(bookingID);
	}
}
