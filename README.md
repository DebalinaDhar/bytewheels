# bytewheels
ByteWheels is a car rental web application. The Repository contains REST API services and resources, to perform the following actions:

1> User can fetch all the cars owned by Bytewheel [ @GET : http://localhost:1234/bytewheels/rentalservice/] <br /> 
2> User can fetch all cars by category  [@GET : http://localhost:1234/bytewheels/rentalservice/full ]<br /> 
3> User can fetch available cars by dates [@GET: http://localhost:1234/bytewheels/rentalservice/{startdate}/{enddate}] [yyyy-MM-dd]
<br /> 
4> User can fetch available cars by dates and categpry [http://localhost:1234/bytewheels/rentalservice/compact/{startdate}/{enddate}] <br /> 
5> User can confirm the booking an available car  <br /> 
  
  @POST 
  Request:
  {
        "model_name": "Chevrolet Malibu",<br /> 
        "from_date": "2018-09-01", <br /> 
        "to_date": "2018-09-12",<br /> 
        "email": "jennifer.h"<br /> 
  }
  
  Response :
  {
    "booking_id": 3,<br /> 
    "email": "jennife.h@gmail.com",<br /> 
    "from_date": "2018-09-02",<br /> 
    "model_name": "Chevrolet Malibu", <br /> 
    "no_of_days": 19,<br /> 
    "serial_number": "CHEVROLET-MALIBU-FULL-2",<br /> 
    "status": "CONFIRMED",<br /> 
    "to_date": "2018-09-21",<br /> 
    "total_cost": 570<br /> 
}
6> User can get the details of booking through booking id 
[ @GET:http://localhost:1234/bytewheels/rentalservice/showBookingDetails/{bookingid}]

  RESPONSE :
  
  {
    "booking_id": 2,<br /> 
    "email": "jennifer.h4@gmail.com",<br /> 
    "from_date": "2018-09-01",<br /> 
    "model_name": "Chevrolet Malibu", <br /> 
    "no_of_days": 11,<br /> 
    "serial_number": "CHEVROLET-MALIBU-FULL-1",<br /> 
    "status": "CONFIRMED", <br /> 
    "to_date": "2018-09-12", <br /> 
    "total_cost": 330 <br /> 
}

** A Car is available if booking table does not contain a CONFIRMED entry of the Car's Model Number in the given range of dates
Below are the Table details used for this API:

TABLE 1 : 
desc car
--------
id	int(11)	NO	PRI		<br /> 
model_name	varchar(45)	NO	<br /> 		
category	varchar(45)	NO		<br /> 	
cost_per_day	int(11)	NO			<br /> 
serial_number	varchar(45)	NO		<br /> 	

** The serial number in the car table represents a unique vehicle here.
So for 2 Ford Fiesta Cars there will have serial numbers FORD-FIESTA-COMPACT-1, FORD-FIESTA-COMPACT-2

 SAMPLE DATA FOR CARS TABLE 
 --------------------------
id	model_name	category	cost_per_day	serial_number<br /> 
1	Ford Fiesta	Compact	20	FORD-FIESTA-COMPACT-1 <br /> 
2	Ford Fiesta	Compact	20	FORD-FIESTA-COMPACT-2 <br /> 
3	Ford Focus	Compact	20	FORD-FOCUS-COMPACT-1 <br /> 
4	Ford Focus	Compact	20	FORD-FOCUS-COMPACT-2 <br /> 
5	Chevrolet Malibu	Full	30	CHEVROLET-MALIBU-FULL-1 <br /> 
6	Chevrolet Malibu	Full	30	CHEVROLET-MALIBU-FULL-2 <br /> 
7	Volkswagen Jetta	Full	30	VOLKSWAGEN-JETTA-FULL-1 <br /> 
8	Volkswagen Jetta	Full	30	VOLKSWAGEN-JETTA-FULL-2 <br /> 
9	Ford Egde	Large	40	FORD-EDGE-LARGE-1 <br /> 
10	Ford Egde	Large	40	FORD-EDGE-LARGE-2 <br /> 
11	Ford Escape	Large	40	FORD-ESCAPE-LARGE-1 <br /> 
12	Ford Escape	Large	40	FORD-ESCAPE-LARGE-2 <br /> 
13	BMW 328i	Luxury	50	BMW-328i-LUXURY-1 <br /> 
14	BMW 328i	Luxury	50	BMW-328i-LUXURY-2 <br /> 
15	BMW X5	Luxury	50	BMW-X5-LUXURY-1 <br /> 
16	BMW X5	Luxury	50	BMW-X5-LUXURY-2 <br /> 


** Bookings Table contain the below mentioned columns. We Can start with some empty booking table and populate it through API and test

desc bookings
--------------------
field type null key <br /> 
booking_id	int(11)	NO	PRI	<br /> 
email	varchar(45)	NO			<br /> 
from_date	date	NO			<br /> 
model_name	varchar(45)	NO	<br /> 		
no_of_days	int(11)	NO			<br /> 
serial_number	varchar(45)	NO		<br /> 	
status	varchar(45)	NO			<br /> 
to_date	date	NO			<br /> 
total_cost	int(11)	YES			<br /> 

INSTRUCTIONS FOR USE:
------------------------
* THE SQL FILES IN THE REPOSITORY WILL CONTAIN COMMANDS FOR CREATING BOTH TABLES AND POPULATING DATA IN THE SAME.
* THE DATABASE CONNECTIONS ARE HANDLED IN DBConnections.java. KINDLY UPDATE THE URL, USERNAME AND PASSWORD, AS IT CONTAINS REFERENCE TO LOCALHOST
* THE PROJECT IS TESTED VIA POSTMAN. 
* THE ERROR CASES ARE HANDLED. INPUT DATA DATE RANGE IS VALIDATED AND VERIFIED , AND EXCEPTION MAPPER IS USED TO SEND PROPER MESSAGE TO CLIENT.

Example: 
    If a POST request for booking a vehicle is sent for a date range where it is not available. The following json object is sent to client
    
    {
    "errorCode": 400, <br /> 
    "errorMessage": "No Car under the requested Model Name is available for booking between the date range"<br /> 
    }
    
 VARIOUS INPUT VALIDATIONS THAT ARE HANDLED ARE :
 ---------------------------------------------------
 >> Date format should match 'yyyy-MM-dd' <br /> 
 >> End date should not be before start date <br /> 
 >> for booking confirmation model name, from date , to date and emailID are mandatory <br /> 
 >> email shoud be in proper format.<br /> 

* PROJECT WAS PACKAGED BY LAYER. BELOW IS A PROJECT STRUCTURE.
>project name --> bytewheels
> packages :<br /> 
>> <b> org.bytemark.bytewheels.dao <b> <br /> 
  >>BookingDAO.java <br /> 
  >>BookingDAOImpl.java<br /> 
  >>CarDAO.java<br /> 
  >>CarDAOImpl.java<br /> 
  >>DBConnection.java <br /> 
>> <b> org.bytemark.bytewheels.exceptions <b> <br /> 
  >>CarNotAvailableExection.java <br /> 
  >>GenericExceptionMapper.java <br /> 
  >>InvalidRequestException <br /> 
>> <b> org.bytemark.bytewheels.models <b> <br /> 
  >>Booking.java <br /> 
  >>Car.java <br /> 
  >>ErrorMessage.java <br /> 
  >>StringConstants.java <br /> 
>> <b> org.bytemark.bytewheels.resources <b> <br />  
  >>CarRentalSystemResource <br /> 
>> <b> org.bytemark.bytewheels.services <b> <br /> 
  >>CarRentalSystemService <br /> 
  >>InputDataValidation <br /> 
  
FILES IN THE REPOSITORY:
------------------------
1> Java Files For API <br /> 
2> pom.xml for maven dependency <br /> 
3> web.xml for deployment <br /> 
4> SQL for tables <br /> 

**The project name is bytewheels, database schema name used is bytewheels.
