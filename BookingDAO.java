package org.bytemark.bytewheels.dao;

import java.util.List;

import org.bytemark.bytewheels.models.Booking;

public interface BookingDAO {
	List<Booking> getAllBookings(DBConnection con)throws Exception;
	Booking addBooking(DBConnection con,String model_number,String model_name,String email,String fromdate,String todate,int total,int no_of_days)throws Exception;
	Booking getBooking(DBConnection con,int bookingId)throws Exception;
	Booking updateBookingStatus(DBConnection con,String status,int bookingId)throws Exception;
}
