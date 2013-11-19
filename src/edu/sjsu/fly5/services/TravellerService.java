package edu.sjsu.fly5.services;

import javax.jws.WebService;

import edu.sjsu.fly5.pojos.Traveller;



@WebService
public class TravellerService 
{

	public Traveller[] listTravellers()
	{
		Traveller[] travellerDetails=null;

		return travellerDetails;
	}
	public Traveller viewTravellerInfo(long travellerId)
	{
		Traveller traveller=null;
		return traveller;
	}
	public boolean 	addTraveller(Traveller traveller)
	{
		boolean flag=false;
		return flag;
	}
	public boolean updateTraveller(Traveller traveller)
	{
		boolean flag=false;
		return flag;	
	}
	public boolean removeTraveller(long travellerId)
	{
		boolean flag=false;
		return flag;
	}

	public boolean authenticateTraveller(String email,String password)
	{
		boolean flag=false;
		return flag;	
	}

}
