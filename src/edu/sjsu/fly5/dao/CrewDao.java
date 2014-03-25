package edu.sjsu.fly5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import edu.sjsu.fly5.pojos.Crew;
import edu.sjsu.fly5.util.ConnectionFactory;
import edu.sjsu.fly5.util.Fly5Constants;
import edu.sjsu.fly5.util.Fly5Exception;

public class CrewDao
{

public boolean addCrew(String crewName) throws Fly5Exception
{
	PreparedStatement ps=null;
	Connection con=null;
	boolean flag=false;
	
	
	try
	{
		con=ConnectionFactory.getInstance().getConnection();
		ps=con.prepareStatement(Fly5Constants.ADD_CREW_DETAILS_QUERY);
		ps.setString(1, crewName);
		int i=ps.executeUpdate();
		if(i>0)
		{
			flag=true;
		}
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();	
	}
	finally
	{
		ConnectionFactory.getInstance().closeConnection(con);
	}
	
	return flag;
	
}
	
public ArrayList<Crew> listOfCrew() throws Fly5Exception
{
	ArrayList<Crew> listOfCrew=new ArrayList<Crew>();
	Crew crew=null;
	PreparedStatement ps=null;
	Connection con=null;
	
	try
	{
		con=ConnectionFactory.getInstance().getConnection();
		ps=con.prepareStatement(Fly5Constants.VIEW_ALL_CREW_DETAILS_QUERY);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next())
		{
			crew = new Crew();
			crew.setCrewID(rs.getLong(1));
			crew.setCrewName(rs.getString(2));
			listOfCrew.add(crew);
		}
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();	
	}
	finally
	{
		ConnectionFactory.getInstance().closeConnection(con);
	}
	
	return listOfCrew;
	
}

	public Crew viewCrewDetails(long crewID) throws Fly5Exception 
	{
		PreparedStatement ps=null;
		Connection con=null;
		Crew crew=new Crew();
		
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(Fly5Constants.VIEW_CREW_DETAILS_BY_ID_QUERY);
			ps.setLong(1, crewID);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				crew.setCrewID(rs.getLong(1));
				crew.setCrewName(rs.getString(2));
			}
			
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();

		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		
		return	crew;
	}
	
 public boolean updateCrew(Crew crew) throws Fly5Exception
 {
	 boolean flag=false;
	 PreparedStatement ps=null;
	 Connection con=null;
	 
	 try
	 {
		 con=ConnectionFactory.getInstance().getConnection();
		 ps=con.prepareStatement(Fly5Constants.UPDATE_CREW_DETAILS_QUERY);
		 ps.setString(1, crew.getCrewName());
		 ps.setLong(2, crew.getCrewID());
		 
		 int i=ps.executeUpdate();
		 if(i>0)
		 {
			 flag=true;
			 
		 }
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	 finally
	 {
		 ConnectionFactory.getInstance().closeConnection(con);
	 }
	 return flag;
	 
 }
	
 
 
 public boolean removeCrew(long crewID) throws Fly5Exception
 {
	 PreparedStatement ps=null;
	 Connection con=null;
	 boolean flag=false;
	 
	 try
	 {
		 con=ConnectionFactory.getInstance().getConnection();
		 ps=con.prepareStatement(Fly5Constants.DELETE_CREW_BY_ID_QUERY);
		 ps.setLong(1, crewID);
		 
		 int i=ps.executeUpdate();
		 if(i>0)
		 {
			 flag=true;
		 }
		 
	 }
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	 finally
	 {
		 ConnectionFactory.getInstance().closeConnection(con);
	 }
	 return flag;
	 
 }
	
	
}
