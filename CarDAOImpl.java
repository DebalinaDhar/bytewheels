package org.bytemark.bytewheels.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bytemark.bytewheels.exceptions.CarNotAvailableExection;
import org.bytemark.bytewheels.models.Car;
import org.bytemark.bytewheels.models.StringConstants;

/**
 * 
 * @author ddhar
 * 
 * DAO class for performing CRUD operations on the 'car' table
 *
 */

public class CarDAOImpl implements CarDAO{

	@Override
	public List<Car> getAllCars(DBConnection con) throws Exception {
		// TODO Auto-generated method stub
		List<Car> allCars=new ArrayList<Car>();
		Connection connection=con.getConnection();
		PreparedStatement stmt = con.getConnection().prepareStatement(StringConstants.GET_ALL_CARS);
        ResultSet rs = stmt.executeQuery();
        while(rs.next())
        {
            Car car=new Car();
            car.setId(rs.getInt("id"));
            car.setSerial_number(rs.getString("serial_number"));
            car.setModel_name(rs.getString("model_name"));
            car.setCategory(rs.getString("category"));
            car.setCost_per_day(rs.getInt("cost_per_day"));
            allCars.add(car);
        }
        connection.close();
		return allCars;
	}

	@Override
	public List<Car> getCarByCategory(String category, DBConnection con) throws Exception {
		// TODO Auto-generated method stub
		List<Car> allCars=new ArrayList<Car>();
		Connection connection=con.getConnection();
		PreparedStatement stmt = connection.prepareStatement(StringConstants.GET_CARS_BY_CATEGORY);
		stmt.setString(1, category);
        ResultSet rs = stmt.executeQuery();
        if(!rs.next())
        	throw new CarNotAvailableExection("The Only Supported Categories are :: Compact, Full, Large, Luxury");
        while(rs.next())
        {
            Car car=new Car();
            car.setId(rs.getInt("id"));
            car.setSerial_number(rs.getString("serial_number"));
            car.setModel_name(rs.getString("model_name"));
            car.setCategory(rs.getString("category"));
            car.setCost_per_day(rs.getInt("cost_per_day"));
            allCars.add(car);
        }
        connection.close();
		return allCars;
	}


}
