package edu.sjsu.fly5.services;

import java.text.ParseException;

import javax.jws.WebService;

import edu.sjsu.fly5.dao.TravellerDao;
import edu.sjsu.fly5.pojos.Person;
import edu.sjsu.fly5.pojos.Traveller;
import edu.sjsu.fly5.util.Fly5Exception;



@WebService
public class TravellerService 
{
	private TravellerDao travellerDao;
	
	public TravellerService(){
		
		travellerDao = new TravellerDao();
	}
	public Traveller[] listTravellers() throws Fly5Exception, ParseException
	{
		Traveller[] travellerDetails=null;
		travellerDetails = travellerDao.listTraveller();
		return travellerDetails;
	}
	public Traveller viewTravellerInfo(long travellerId) throws Fly5Exception
	{
		Traveller traveller = new Traveller();
		traveller = travellerDao.viewTravellerInfo(travellerId);
		return traveller;
	}
	public boolean 	addTraveller(Person person, Traveller traveller) throws Fly5Exception
	{
		boolean flag=false;
		flag = travellerDao.addTraveller(person, traveller);
		return flag;
	}
	public boolean updateTraveller(Person person , Traveller traveller) throws Fly5Exception
	{
		boolean flag=false;
		flag = travellerDao.updateTraveller(person, traveller);
		return flag;	
	}
	public boolean removeTraveller(long travellerId) throws Fly5Exception
	{
		boolean flag=false;
		flag = travellerDao.removeTraveller(travellerId);
		return flag;
	}

	public int authenticateTraveller(String email,String password) throws Fly5Exception
	{
		int id = 0;
		
		id = travellerDao.authenticateTraveller(email, password);
		return id;	
	}

}
