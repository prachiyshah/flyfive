package edu.sjsu.fly5.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.sjsu.fly5.pojos.Flight;
import edu.sjsu.fly5.util.Fly5Constants;
import edu.sjsu.fly5.util.Fly5Exception;

public class TestFlightDao {
	
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
	FlightDao flightDao = new FlightDao();
	
	public void testCreateFlight(Flight flight) throws Fly5Exception
	{
		flightDao.createFlight(flight);
	}
	
	public Flight[] testGetAllFlights() throws Fly5Exception
	{
		return flightDao.getAllFlights();
	}
	
	public Flight testGetFlight(String flightID) throws Fly5Exception
	{
		return flightDao.getFlight(flightID);
	}
	
	public void testDeleteFlight(String flightID) throws Fly5Exception
	{
		flightDao.deleteFlight(flightID);
	}
	
	public void testInsertFlightSeats() throws Fly5Exception
	{
		//flightDao.createFlightSeats(2, 15);
	}
	
	public static void main1(String[] args) throws Fly5Exception {
		Flight flight = new Flight();
		flight.setAirline(Fly5Constants.AIRLINE);
		flight.setArrivalTime("3:00:00");
		flight.setCrewID(1);
		flight.setBaseFare(100);
		flight.setDepartureTime("2:00:00");
		flight.setDestination("NYC");
		flight.setDistance(100);
		flight.setFrequency("MTu");
		flight.setJourneyTime("1.0");
		flight.setNoOfSeats(200);
		flight.setSource("SFO");
		TestFlightDao testFlightDao = new TestFlightDao();
		//testFlightDao.testCreateFlight(flight);
		
		//Flight[] flightArray = testFlightDao.testGetAllFlights();
		Flight[] flightArray = new Flight[1];
		//flightArray[0] = testFlightDao.testGetFlight("FF7");
		//printFlights(flightArray);
		
		//testFlightDao.testDeleteFlight("FF1");
		testFlightDao.testInsertFlightSeats();
	}

	public static void main2(String[] args) {
		/*Date departureDate = new Date("12/03/2013");
		departureDate.setHours(28);
		System.out.println(Math.round(2.3));
		
		System.out.println(departureDate.toString());
		// Current date
				Date date = new Date();
				// Departure Start date would be the next date
				Date departureStartDate = new Date();
				//departureStartDate.setDate(date.getDate()+1);
				System.out.println(departureStartDate.toString());
				// Departure end date would be a date after 6 months
				Date departureEndDate = new Date();
				//departureEndDate.setMonth(date.getMonth()+6);
				System.out.println(departureEndDate.toString());
				
				//departureEndDate.setMinutes(20);
				System.out.println(departureEndDate.toString());
				System.out.println(departureStartDate.equals(departureEndDate));*/
		Date oldDate = new Date();	
		oldDate.setHours(3);
		oldDate.setMinutes(30);
		System.out.println(oldDate);
		long HOUR = 3600*1000;
		long MIN = 60*1000;
		Date newDate = new Date(oldDate.getTime() + (12 * HOUR + 2* MIN));
		System.out.println(newDate.toString());
		SimpleDateFormat dateFormater = new SimpleDateFormat(YYYY_MM_DD);
		String date1 = dateFormater.format(newDate);
		System.out.println(date1);
	}
	
	public static void main3(String[] args) {
		Date currentDate = new Date();
		long HOUR = 3600*1000;
		long MIN = 60*1000;
		
		// Departure Start date would be the next day
		System.out.println("currentDate:"+currentDate);
		Date departureStartDate = new Date();
		departureStartDate.setTime(departureStartDate.getTime()+24 * HOUR + 3*HOUR + 4*MIN);
		System.out.println("dstartdate:"+departureStartDate);
		
		// Departure end date would be a date after 6 months
		Date departureEndDate = new Date();
		
		departureEndDate.setTime(departureEndDate.getTime()+3*24 * HOUR + 3*HOUR + 4*MIN);
		System.out.println("dstartEnddate:"+departureEndDate);
		
		while(!departureStartDate.equals(departureEndDate))
		{
			departureStartDate.setTime(departureStartDate.getTime()+ (24*HOUR));
			System.out.println(departureStartDate.toString());
		}
		System.out.println("done");
	}
	
	public static void main(String[] args) {
		 String TIME_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		Pattern pattern = Pattern.compile(TIME_PATTERN);
		Matcher matcher = pattern.matcher("23:23");
		System.out.println(matcher.find());
		
		String departureTime = "23:00";
		String journeyTime = "10:50";
		
		int HOUR = 3600 * 1000;
		int MIN = 60 * 1000;
		int DAY = 24 * HOUR;
		String[] departureTimeComponents = departureTime.split(":");
		int departureHours = Integer.parseInt(departureTimeComponents[0]);
		int departureMins = Integer.parseInt(departureTimeComponents[1]);

		/*Calendar deptDate = Calendar.getInstance();
		deptDate.set(Calendar.HOUR, departureHours);
		deptDate.set(Calendar.MINUTE, departureMins);
		deptDate.set(Calendar.SECOND, 0);
		*/
		String[] journeyTimeComponents = journeyTime.split(":");
		int journeyHours = Integer.parseInt(journeyTimeComponents[0]);
		int journeyMins = Integer.parseInt(journeyTimeComponents[1]);
		
		//System.out.println(deptDate.getTime());
		
		/*Date arrivalDate = deptDate.getTime();
		arrivalDate.setTime(arrivalDate.getTime() + (journeyHours * HOUR + journeyMins * MIN));
		*/
		//System.out.println(arrivalDate);
		
		Date deptDate = new Date();
		//deptDate.setTime(deptDate.getTime()+(departureHours * HOUR + departureMins * MIN));
		deptDate.setHours(departureHours);
		deptDate.setMinutes(departureMins);
		System.out.println(deptDate.toString());
		deptDate.setTime(deptDate.getTime()+ (journeyHours * HOUR + journeyMins * MIN));
		System.out.println(deptDate.toString());
		System.out.println(deptDate.getHours());
		System.out.println(deptDate.getMinutes());
		//long totalArrivalInMilliSeconds = journeyMins * MIN * 1000 + journeyHours * HOUR + deptDate.getTimeInMillis();
		
		/*Calendar arrivalDate = Calendar.getInstance();
		arrivalDate.setTimeInMillis(totalArrivalInMilliSeconds);
		System.out.println(arrivalDate);*/
	}
	
	private static void printFlights(Flight[] flightArray) {
		
		for(int i=0; i<flightArray.length; i++)
		{
			System.out.println("Airline:"+flightArray[i].getAirline());
			System.out.println("ArrivalTime:"+flightArray[i].getArrivalTime());
			System.out.println("BaseFare"+flightArray[i].getBaseFare());
			System.out.println("Crew:"+flightArray[i].getCrewID());
			System.out.println("Departure time:"+flightArray[i].getDepartureTime());
			System.out.println("Destination:"+flightArray[i].getDestination());
			System.out.println("Distance:"+flightArray[i].getDistance());
			System.out.println("ID:"+flightArray[i].getFlightID());
			System.out.println("Status"+flightArray[i].getFlightStatus());
			System.out.println("Frequency:"+flightArray[i].getFrequency());
			System.out.println("Journey time"+flightArray[i].getJourneyTime());
			System.out.println("No of seats"+flightArray[i].getNoOfSeats());
			System.out.println("Source:"+flightArray[i].getSource());
			System.out.println("\n");
		}
	}

}
