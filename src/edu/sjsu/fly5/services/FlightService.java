package edu.sjsu.fly5.services;

import javax.jws.WebService;

import edu.sjsu.fly5.pojos.Attribute;
import edu.sjsu.fly5.pojos.Flight;

@WebService
public class FlightService 
{

	public boolean addFlightDetails(Flight flight)
	{
		boolean flag=false;

		return flag;	

	}
	public boolean viewFlightDetails(String flightId)
	{
		boolean flag=false;

		return flag;
	}
	public boolean updateFlightDetails(Flight flight)
	{
		boolean flag=false;

		return flag;
	}
	public boolean deleteFlightDetails(String flightId)
	{
		boolean flag=false;

		return flag;	
	}
	public Flight[] listFlights()
	{
		Flight[] listOfFlights=null;

		return listOfFlights;
	}
	public Flight searchFlight(Attribute[] searchAttributes)
	{
		Flight flight=null;


		return flight;

	}



}
