package org.bytemark.bytewheels.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bytemark.bytewheels.exceptions.InvalidRequestException;
import org.bytemark.bytewheels.models.Booking;
import org.bytemark.bytewheels.models.StringConstants;

/**
 * 
 * @author ddhar
 *
 * DAO class for performing CRUD operations on the 'bookings' table
 */

public class BookingDAOImpl implements BookingDAO{
	

	@Override
	public List<Booking> getAllBookings(DBConnection con) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("calling getAllBookings()......");
		List<Booking> allBooking=new ArrayList<Booking>();
		Connection connection=con.getConnection();
		PreparedStatement stmt = connection.prepareStatement(StringConstants.GET_ALL_BOOKING);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			Booking booking=new Booking();
			booking.setBooking_id(rs.getInt("booking_id"));
			booking.setEmail(rs.getString("email"));
			booking.setModel_name(rs.getString("model_name"));
			booking.setSerial_number(rs.getString("serial_number"));
			booking.setStatus(rs.getString("status"));
			booking.setFrom_date(rs.getString("fromdate"));
			booking.setTo_date(rs.getString("todate"));
			booking.setTotal_cost(rs.getInt("total_cost"));
			booking.setNo_of_days(rs.getInt("no_of_days"));
			allBooking.add(booking);
		}
		connection.close();
		return allBooking;
	}

	@Override
	public Booking addBooking(DBConnection con,String model_number,String model_name,String email,String fromdate,String todate,int total, int no_of_days) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("calling addBooking()......");
		int bookingId=0;
		Connection connection=con.getConnection();
		PreparedStatement stmt = connection.prepareStatement("select max(booking_id) as booking_id from bookings");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			bookingId=rs.getInt("booking_id")+1;
		}

		Booking booking=new Booking();
		stmt = con.getConnection().prepareStatement(StringConstants.INSERT_NEW_BOOKING);
		stmt.setInt(1, bookingId);
		stmt.setString(2, model_number);
		stmt.setString(3, model_name);
		stmt.setString(4, fromdate);
		stmt.setString(5, todate);
		stmt.setString(6, StringConstants.STATUS_CONFIRMED);
		stmt.setString(7, email);
		stmt.setInt(8, total);
		stmt.setInt(9, no_of_days);
		int updated = stmt.executeUpdate();

		if(updated==1){
			System.out.println("Booking CONFIRMED");
			booking=new BookingDAOImpl().getBooking(con, bookingId);
		}
		connection.close();
		return booking;
	}

	@Override
	public Booking getBooking(DBConnection con, int bookingId)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("calling getBooking().........");
		Booking booking=new Booking();
		Connection connection=con.getConnection();
		PreparedStatement stmt = connection.prepareStatement(StringConstants.GET_BOOKING_BY_ID);
		stmt.setInt(1, bookingId);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		{
			booking.setBooking_id(rs.getInt("booking_id"));
			booking.setEmail(rs.getString("email"));
			booking.setModel_name(rs.getString("model_name"));
			booking.setSerial_number(rs.getString("serial_number"));
			booking.setStatus(rs.getString("status"));
			booking.setFrom_date(rs.getString("from_date"));
			booking.setTo_date(rs.getString("to_date"));
			booking.setTotal_cost(rs.getInt("total_cost"));
			booking.setNo_of_days(rs.getInt("no_of_days"));
		}else{
			throw new InvalidRequestException("The Requested Booking ID does not exist");
		}
		
		connection.close();
		return booking;
	}

	@Override
	public Booking updateBookingStatus(DBConnection con, String status,int bookingId)
			throws Exception {
		// TODO Auto-generated method stub
		Booking booking=new Booking();
		Connection connection=con.getConnection();
		PreparedStatement stmt = con.getConnection().prepareStatement(StringConstants.UPDATE_STATUS);
		stmt.setString(1, status);
		stmt.setInt(2, bookingId);
		int updated = stmt.executeUpdate();
		if(updated==1){
			System.out.println("Booking UPDATED");
			booking=new BookingDAOImpl().getBooking(con, bookingId);
		}
		connection.close();
		return booking;
	}

}
