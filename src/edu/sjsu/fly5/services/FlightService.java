package edu.sjsu.fly5.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.sjsu.fly5.dao.FlightDao;
import edu.sjsu.fly5.pojos.Flight;
import edu.sjsu.fly5.pojos.FlightInstance;
import edu.sjsu.fly5.pojos.FlightSearchAttributes;
import edu.sjsu.fly5.pojos.TravelerInfo;
import edu.sjsu.fly5.util.Fly5Exception;

@WebService
public class FlightService 
{
	FlightDao flightDao = new FlightDao();
	public FlightService()
	{
		
	}
	
	@WebMethod
	public void addFlightDetails(Flight flight) throws Fly5Exception
	{
		flightDao.createFlight(flight);
	}
	
	@WebMethod
	public Flight viewFlightDetails(String flightId) throws Fly5Exception
	{
		return flightDao.getFlight(flightId);	
	}
	
	@WebMethod
	public void updateFlightDetails(Flight flight) throws Fly5Exception
	{
		flightDao.updateFlight(flight);
	}
	
	@WebMethod
	public void deleteFlightDetails(String flightId) throws Fly5Exception
	{
		flightDao.deleteFlight(flightId);	
	}
	
	@WebMethod
	public Flight[] listFlights() throws Fly5Exception
	{
		Flight[] listOfFlights= flightDao.getAllFlights();
		return listOfFlights;
	}
	
	@WebMethod
	public FlightInstance[] searchFlight(FlightSearchAttributes searchAttributes) throws Fly5Exception
	{
		return flightDao.searchFlight(searchAttributes);
	}
	
	@WebMethod
	public TravelerInfo[] getTravellersOnBoard(String flightID,String departureDate) throws Fly5Exception
	{
		return flightDao.getListOfCustomersOnBoard(flightID, departureDate);
	}
}
