package edu.sjsu.fly5.util;

/**
 * This class defines constants used in Fly5
 * @author Vijaya
 *
 */
public class Fly5Constants {
	
	public static final String FLIGHT_NUMBER_PREFIX = "FF";
	public static final String AIRLINE = "Fly5";
	public static final String FLIGHT_NUMBER = "";
	
	/************ Flight Search attributes ***********************/
	public static final String SOURCE = "source";
	public static final String DESTINATION = "destination";
	public static final String DEPARTURE_DATE = "departureDate";
	public static final String TRAVEL_CLASS = "class";
	public static final String ADULTS = "adults";
	public static final String CHILDREN = "children";
	public static final String INFANTS = "infants";
	/***************************************************************/
	public static final String DEPARTURE_TIME = "";
	public static final String ARRIVAL_TIME = "";
	public static final String FLIGHT_STATUS = "";
	public static final String BASE_FARE = "";
	
	/****************Frequency ************************************/
	public static final String SUNDAY = "S";
	public static final String MONDAY = "M";
	public static final String TUESDAY = "Tu";
	public static final String WEDNESDAY = "W";
	public static final String THIRSDAY = "Th";
	public static final String FRIDAY = "F";
	public static final String SATURDAY = "Sa";
	
	/*************** Travel classes*******************************/
	public static final String BUSINESS = "business";
	public static final String ECONOMY = "economy";
	public static final String FIRST = "first";
	
/////////////////CREW DAO QUERIES STARTS ////////////////////////////////
	
public static final String ADD_CREW_DETAILS_QUERY="insert into CREW(crew_name) values(?)";
public static final String VIEW_ALL_CREW_DETAILS_QUERY="select * from CREW";
public static final String VIEW_CREW_DETAILS_BY_ID_QUERY="select * from CREW where crew_id=?";
public static final String UPDATE_CREW_DETAILS_QUERY="update CREW set crew_name=? where crewID=?";
public static final String DELETE_CREW_BY_ID_QUERY="delete from CREW where crew_id=?";


//////////////////CREW DAO QUERIES ENDS//////////////////////////////////////////
	
//////////////  EMPLOYEE DAO QUERIES START /////////////////////////////////

public static final int EMPLOYEE_ID_INDEX = 1;
 
public static final int WORK_DESCRIPTION_INDEX = 2;

public static final int POSITION_INDEX = 3;

public static final int HIRE_DATE_INDEX = 4;

public static final int CREW_ID_INDEX = 5;

public static final int PERSON_ID_INDEX=1;

public static final int PERSON_FIRST_NAME_INDEX=2;

public static final int PERSON_LAST_NAME_INDEX=3;

public static final int PERSON_STREET_INDEX=4;

public static final int PERSON_CITY_INDEX=5;

public static final int PERSON_STATE_INDEX=6;

public static final int PERSON_ZIPCODE_INDEX=7;

public static final int PERSON_DATE_OF_BIRTH_INDEX=8;

public static final int PERSON_EMAIL_ADDRESS_INDEX=9;





public static final String ADD_PERSON_QUERY="insert into Person(first_name,last_name,street,city,state,zip,date_of_birth,emailAddress) values(?,?,?,?,?,?,?,?)";
public static final String ADD_EMPLOYEE_QUERY="insert into Employee values(?,?,?,?,?)";
public static final String MAX_ID_FROM_PERSON_QUERY="select max(id) from Person";
public static final String SELECT_ALL_EMPLOYEE_QUERY="select * from Employee";
public static final String SELECT_EMPLOYEE_BY_ID_QUERY="select * from Employee where employee_id=?";
public static final String UPDATE_EMPLOYEE_BY_ID_QUERY="update Employee set work_description = ?, position =? ,hire_date = ?, crew_id = ? where employee_id = ?";
public static final String UPDATE_PERSON_BY_ID_QUERY="update Person set first_name=?, last_name=? ,emailAddress=?,street=?,city=?,state=?,zip=? where id=?";
public static final String DELETE_EMPLOYEE_BY_ID_QUERY="delete from Employee where employee_id = ?";
public static final String SELECT_PERSON_BY_ID_QUERY="select * from Person where id=?";
		


//////////////EMPLOYEE DAO QUERIES ENDS /////////////////////////////////



//////////////////CREW DAO QUERIES ENDS//////////////////////////////////////////	
}
