package org.bytemark.bytewheels.services;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bytemark.bytewheels.exceptions.InvalidRequestException;

/**
 * 
 * @author ddhar
 * 
 * Class to perform basic input data validations
 * before proceeding to the service calls
 *
 */

public class InputDataValidation {

	static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	/*
	 * Check if the any of the mandatory input field is null or empty
	 */
	
	public static void validateBookingRequest(String fromdate,String todate,String modelName,String email) throws Exception{
		
		System.out.println("Validating Booking Request");
		
		if(fromdate==null||fromdate.isEmpty()){
			throw new InvalidRequestException("From date is mandatory, please provide a from date");
		}else if(todate==null||todate.isEmpty()){
			throw new InvalidRequestException("To date is mandatory, please provide a To date");
		}else if(modelName==null||modelName.isEmpty()){
			throw new InvalidRequestException("modelName is mandatory, please provide the Model Name For booking");
		}else if(email==null||email.isEmpty()){
			throw new InvalidRequestException("Please provide emailId");
		}
		
		if(!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"))
			throw new InvalidRequestException("Email Pattern Not Valid");
		validateInputDates(fromdate,todate);
	}
	
	/*
	 *  Checks if the date range provided is valid
	 */
	public static void validateInputDates(String fromdate,String todate) throws InvalidRequestException{
		System.out.println("Validating dates provided");
		
		Date startDate=null;
		Date endDate=null;
		try{
			startDate=sdf.parse(fromdate);
			endDate=sdf.parse(todate);
		}catch(ParseException e){
			throw new InvalidRequestException("PlEASE PROVIDE DATE IN FORMAT : 'YYYY-MM-DD'");
		}
		if(startDate.after(endDate)){
			throw new InvalidRequestException("To date cannot be before start date");
		}

	}

}
