package edu.sjsu.fly5.services;

import javax.jws.WebService;

import edu.sjsu.fly5.pojos.Flight;
import edu.sjsu.fly5.pojos.Journey;
import edu.sjsu.fly5.pojos.PaymentDetails;
import edu.sjsu.fly5.pojos.Traveller;

@WebService
public class JourneyService 
{

	public boolean bookJourney(Traveller traveller,Flight flight,PaymentDetails paymentDetails)
	{
		boolean flag=false;

		return flag;

	}
	public boolean cancelJourney(String bookingId)
	{	
		boolean flag=false;

		return flag;

	}
	public boolean rescheduleJourney(String bookingId)
	{
		boolean flag=false;

		return flag;
	}
	public Journey[] listAllJourneys()
	{
		Journey[] listOfJourneys=null;
		return listOfJourneys;
	}
	public Journey generateItinerary(long bookingReferenceNo,String lastName)
	{
		Journey journey=null;

		return journey;
	}

}
