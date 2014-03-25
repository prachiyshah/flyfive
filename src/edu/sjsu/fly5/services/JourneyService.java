package edu.sjsu.fly5.services;

import java.util.ArrayList;

import javax.jws.WebService;

import edu.sjsu.fly5.dao.JourneyDao;
import edu.sjsu.fly5.pojos.Flight;
import edu.sjsu.fly5.pojos.FlightInstance;
import edu.sjsu.fly5.pojos.Journey;
import edu.sjsu.fly5.pojos.PaymentDetails;
import edu.sjsu.fly5.pojos.Person;
import edu.sjsu.fly5.pojos.Traveller;
import edu.sjsu.fly5.util.Fly5Exception;

@WebService
public class JourneyService 
{
	JourneyDao journeyDao = new JourneyDao();

	public boolean bookJourney(Traveller[] traveller,FlightInstance[] flightInstance,PaymentDetails paymentDetails,Person[] person)
	{
		boolean flag=false;
		flag = journeyDao.bookJourney(traveller, flightInstance, paymentDetails, person);
		return flag;

	}
	public boolean cancelJourney(int bookingId)
	{	
		boolean flag=false;
		flag = journeyDao.cancelJourney(bookingId);
		return flag;

	}
	public boolean rescheduleJourney(String bookingId,Journey journey)
	{
		boolean flag=false;
		flag = journeyDao.rescheduleJourney(bookingId, journey);
		return flag;
	}
	public Journey[] listAllJourneys()
	{
		ArrayList<Journey> listAllJourney = new ArrayList<>();
		listAllJourney = (ArrayList<Journey>) journeyDao.listAllJourneys();
		return listAllJourney.toArray(new Journey[listAllJourney.size()]);
	}
	/*public Journey[] listAllJourney(int bookingReferenceNo)
	{
		ArrayList<Journey> listAllJourneybyId= new ArrayList<>();
		listAllJourneybyId = journeyDao.listAllJourney(bookingReferenceNo);
		return listAllJourneybyId.toArray(new Journey[listAllJourneybyId.size()]);
	}*/
	
	public Journey[] listAllJourney(String userName)
	{
		ArrayList<Journey> listAllJourneybyId= new ArrayList<>();
		try 
		{
			listAllJourneybyId = journeyDao.listAllJourney(userName);
		}
		catch (Fly5Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listAllJourneybyId.toArray(new Journey[listAllJourneybyId.size()]);
	}
	
	
	
	public Journey generateItinerary(long bookingReferenceNo,String lastName)
	{
		Journey journey=null;

		return journey;
	}

}
