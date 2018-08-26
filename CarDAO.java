package org.bytemark.bytewheels.dao;

import java.util.List;

import org.bytemark.bytewheels.models.Car;

public interface CarDAO {
	
	List<Car> getAllCars(DBConnection con)throws Exception;
	List<Car> getCarByCategory(String category,DBConnection con) throws Exception ;
}
