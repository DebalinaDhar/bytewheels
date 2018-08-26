package org.bytemark.bytewheels.models;

public class StringConstants {
	
	public static final String GET_ALL_CARS="select * from car";
	public static final String GET_CARS_BY_CATEGORY="select * from car where category =?";
	public static final String GET_ALL_BOOKING="select * from car";
	public static final String GET_BOOKING_BY_ID="select * from bookings where booking_id=?";
	public static final String INSERT_NEW_BOOKING="insert into bookings values(?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_STATUS="update bookings set status=? where booking_id=?";
	public static final String GET_AVAILABLE_CARS_IN_DATE_RANGE="select * from car as c where c.serial_number not in (select b.serial_number from bookings as b where (b.from_date between ? and ? or   b.to_date between ? and ?) AND STATUS IN ('CONFIRMED'));";
	
	public static final String STATUS_CONFIRMED="CONFIRMED";
	public static final String STATUS_CANCELLED="CANCELLED";

}
