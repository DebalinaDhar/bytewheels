package org.bytemark.bytewheels.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * 
 * @author ddhar
 * 
 */

public class DBConnection {


	public static final String URL = "jdbc:mysql://localhost:3306/bytewheels";
	public static final String USER = "root";
	public static final String PASS = "admin";
	private static DBConnection instance=null;
	
	private DBConnection(){}
	
	/**
	 * Get a connection to database
	 * @return Connection object
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public  Connection getConnection() throws ClassNotFoundException, SQLException
	{
		System.out.println("connecting to db........");
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(URL,USER,PASS);
		System.out.println("connected successfully........");
		return con;
	}
	

	public static DBConnection getInstance() {
		if(instance==null){
			instance=new DBConnection();
			System.out.println("Creating new DB connection instance");
		}else{
			System.out.println("Instance already exist....");
		}
		return instance;
	}

}
