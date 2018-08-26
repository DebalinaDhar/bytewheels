package org.bytemark.bytewheels.models;

/*
 * POJO Class Representing the 
 * bookings Table in Database
 */

public class Booking {
	
	private int booking_id;
	private String model_name;
	private String category;
	private int total_cost;
	private String serial_number;
	private String from_date;
	private String to_date;
	private String status;
	private String email;
	private int no_of_days;
	
	public Booking(){
		
	}

	public Booking(int booking_id, String model_name, String category,
			int total_cost, String serial_number, String from_date,
			String to_date, String status, String email, int no_of_days) {
		super();
		this.booking_id = booking_id;
		this.model_name = model_name;
		this.category = category;
		this.total_cost = total_cost;
		this.serial_number = serial_number;
		this.from_date = from_date;
		this.to_date = to_date;
		this.status = status;
		this.email = email;
		this.no_of_days = no_of_days;
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(int total_cost) {
		this.total_cost = total_cost;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}

	@Override
	public String toString() {
		return "Booking [booking_id=" + booking_id + ", model_name="
				+ model_name + ", category=" + category + ", total_cost="
				+ total_cost + ", serial_number=" + serial_number
				+ ", from_date=" + from_date + ", to_date=" + to_date
				+ ", status=" + status + ", email=" + email + ", no_of_days="
				+ no_of_days + "]";
	}

}
