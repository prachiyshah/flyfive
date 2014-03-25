package edu.sjsu.fly5.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.sjsu.fly5.dao.CrewDao;
import edu.sjsu.fly5.pojos.Crew;
import edu.sjsu.fly5.util.Fly5Exception;

public class CrewService 
{

	private CrewDao crewDao;
	
	public CrewService()
	{
		this.crewDao = new CrewDao();
	}
	
	
	public boolean addCrew(String crewName) throws Fly5Exception
	{
		boolean flag=false;
		flag=crewDao.addCrew(crewName);
		return flag;
	}
	
	public Crew[] listcrews() throws Fly5Exception, ParseException
	{
		List<Crew> listOfCrews=new ArrayList<Crew>();
		listOfCrews=crewDao.listOfCrew();
		return listOfCrews.toArray(new Crew[listOfCrews.size()]);
	}
		
	public Crew viewCrewDetails(long crewID) throws Fly5Exception
	{
		Crew crew=null;
		crew=crewDao.viewCrewDetails(crewID);
		return crew;
	}
	
	public boolean updateCrew(Crew crew) throws Fly5Exception, ParseException
	{
		boolean flag=false;
		flag=crewDao.updateCrew(crew);
		return flag;
	}
	
	public boolean removeCrew(long crewID) throws Fly5Exception
	{
		boolean flag=false;
		flag=crewDao.removeCrew(crewID);
		return flag;
	}
	
	
	
	
	
	
	
}
