package org.bytemark.bytewheels.models;

/*
 * POJO Class Representing the 
 * car Table in Database
 */

public class Car {
	
	private int id;
	private String model_name;
	private String category;
	private int cost_per_day;
	private String serial_number;
	
	public Car(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCost_per_day() {
		return cost_per_day;
	}

	public void setCost_per_day(int cost_per_day) {
		this.cost_per_day = cost_per_day;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public Car(int id, String model_name, String category, int cost_per_day,
			String serial_number) {
		this.id = id;
		this.model_name = model_name;
		this.category = category;
		this.cost_per_day = cost_per_day;
		this.serial_number = serial_number;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model_name=" + model_name + ", category="
				+ category + ", cost_per_day=" + cost_per_day
				+ ", serial_number=" + serial_number + "]";
	}
	
	
	
}
