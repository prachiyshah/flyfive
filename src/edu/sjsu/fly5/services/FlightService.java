package edu.sjsu.fly5.services;

import javax.jws.WebService;

import edu.sjsu.fly5.pojos.Attribute;
import edu.sjsu.fly5.pojos.Flight;

/*Exceptions/Failure Modes
Your project MUST properly handle the following failure conditions
- Creating Duplicate Flights/Journey/Travelers
- Addresses (for Person) that are not formed properly (see below) */
/*Further, for all operations defined above, 
 * you need to ensure that if a particular operation fails 
 * (for example, a client requests that a duplicate reservation be created), 
 * your system is left in a consistent state.
 */

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
		//Display information about a flight (attributes plus list of customers on board)
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
