# bytewheels
ByteWheels is a car rental web application. The Repository contains REST API services and resources, to perform the following actions:

1> User can fetch all the cars owned by Bytewheel [ @GET : http://localhost:1234/bytewheels/rentalservice/]
2> User can fetch all cars by category  [@GET : http://localhost:1234/bytewheels/rentalservice/full ]
3> User can fetch available cars by dates [@GET: http://localhost:1234/bytewheels/rentalservice/{startdate}/{enddate}] [yyyy-MM-dd]
4> User can fetch available cars by dates and categpry [http://localhost:1234/bytewheels/rentalservice/compact/{startdate}/{enddate}]
5> User can confirm the booking an available car 
  
  @POST 
  Request:
  {
        "model_name": "Chevrolet Malibu",
        "from_date": "2018-09-01",
        "to_date": "2018-09-12",
        "email": "jennifer.h"
  }
  
  Response :
  {
    "booking_id": 3,
    "email": "jennife.h@gmail.com",
    "from_date": "2018-09-02",
    "model_name": "Chevrolet Malibu",
    "no_of_days": 19,
    "serial_number": "CHEVROLET-MALIBU-FULL-2",
    "status": "CONFIRMED",
    "to_date": "2018-09-21",
    "total_cost": 570
}
6> User can get the details of booking through booking id 
[ @GET:http://localhost:1234/bytewheels/rentalservice/showBookingDetails/{bookingid}]

  RESPONSE :
  
  {
    "booking_id": 2,
    "email": "jennifer.h4@gmail.com",
    "from_date": "2018-09-01",
    "model_name": "Chevrolet Malibu",
    "no_of_days": 11,
    "serial_number": "CHEVROLET-MALIBU-FULL-1",
    "status": "CONFIRMED",
    "to_date": "2018-09-12",
    "total_cost": 330
}

** A Car is available if booking table does not contain a CONFIRMED entry of the Car's Model Number in the given range of dates
Below are the Table details used for this API:

TABLE 1 : 
desc car
--------
id	int(11)	NO	PRI		
model_name	varchar(45)	NO			
category	varchar(45)	NO			
cost_per_day	int(11)	NO			
serial_number	varchar(45)	NO			

** The serial number in the car table represents a unique vehicle here.
So for 2 Ford Fiesta Cars there will have serial numbers FORD-FIESTA-COMPACT-1, FORD-FIESTA-COMPACT-2

 SAMPLE DATA FOR CARS TABLE 
 --------------------------
id	model_name	category	cost_per_day	serial_number
1	Ford Fiesta	Compact	20	FORD-FIESTA-COMPACT-1
2	Ford Fiesta	Compact	20	FORD-FIESTA-COMPACT-2
3	Ford Focus	Compact	20	FORD-FOCUS-COMPACT-1
4	Ford Focus	Compact	20	FORD-FOCUS-COMPACT-2
5	Chevrolet Malibu	Full	30	CHEVROLET-MALIBU-FULL-1
6	Chevrolet Malibu	Full	30	CHEVROLET-MALIBU-FULL-2
7	Volkswagen Jetta	Full	30	VOLKSWAGEN-JETTA-FULL-1
8	Volkswagen Jetta	Full	30	VOLKSWAGEN-JETTA-FULL-2
9	Ford Egde	Large	40	FORD-EDGE-LARGE-1
10	Ford Egde	Large	40	FORD-EDGE-LARGE-2
11	Ford Escape	Large	40	FORD-ESCAPE-LARGE-1
12	Ford Escape	Large	40	FORD-ESCAPE-LARGE-2
13	BMW 328i	Luxury	50	BMW-328i-LUXURY-1
14	BMW 328i	Luxury	50	BMW-328i-LUXURY-2
15	BMW X5	Luxury	50	BMW-X5-LUXURY-1
16	BMW X5	Luxury	50	BMW-X5-LUXURY-2


** Bookings Table contain the below mentioned columns. We Can start with some empty booking table and populate it through API and test

desc bookings
--------------------
field type null key
booking_id	int(11)	NO	PRI		
email	varchar(45)	NO			
from_date	date	NO			
model_name	varchar(45)	NO			
no_of_days	int(11)	NO			
serial_number	varchar(45)	NO			
status	varchar(45)	NO			
to_date	date	NO			
total_cost	int(11)	YES			

INSTRUCTIONS FOR USE:
------------------------
* THE SQL FILES IN THE REPOSITORY WILL CONTAIN COMMANDS FOR CREATING BOTH TABLES AND POPULATING DATA IN THE SAME.
* THE DATABASE CONNECTIONS ARE HANDLED IN DBConnections.java. KINDLY UPDATE THE URL, USERNAME AND PASSWORD, AS IT CONTAINS REFERENCE TO LOCALHOST
* THE PROJECT IS TESTED VIA POSTMAN. 
* THE ERROR CASES ARE HANDLED. INPUT DATA DATE RANGE IS VALIDATED AND VERIFIED , AND EXCEPTION MAPPER IS USED TO SEND PROPER MESSAGE TO CLIENT.

Example: 
    If a POST request for booking a vehicle is sent for a date range where it is not available. The following json object is sent to client
    
    {
    "errorCode": 400,
    "errorMessage": "No Car under the requested Model Name is available for booking between the date range"
    }
 VARIOUS INPUT VALIDATIONS THAT ARE HANDLED ARE :
 ---------------------------------------------------
 >> Date format should match 'yyyy-MM-dd'
 >> End date should not be before start date
 >> for booking confirmation model name, from date , to date and emailID are mandatory
 >> email shoud be in proper format.

* PROJECT WAS PACKAGED BY LAYER. BELOW IS A PROJECT STRUCTURE.
> package :
>> org.bytemark.bytewheels.dao
  >>BookingDAO.java
  >>BookingDAOImpl.java
  >>CarDAO.java
  >>CarDAOImpl.java
  >>DBConnection.java
>>org.bytemark.bytewheels.exceptions
  >>CarNotAvailableExection.java
  >>GenericExceptionMapper.java
  >>InvalidRequestException
>>org.bytemark.bytewheels.models
  >>Booking.java
  >>Car.java
  >>ErrorMessage.java
  >>StringConstants.java
>>org.bytemark.bytewheels.resources
  >>CarRentalSystemResource
>>org.bytemark.bytewheels.services
  >>CarRentalSystemService
  >>InputDataValidation
  
FILES IN THE REPOSITORY:
------------------------
1> Java Files For API
2> pom.xml for maven dependency
3> web.xml for deployment
4> SQL for tables
