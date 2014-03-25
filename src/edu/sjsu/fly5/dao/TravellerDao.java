package edu.sjsu.fly5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.sjsu.fly5.pojos.Person;
import edu.sjsu.fly5.pojos.Traveller;
import edu.sjsu.fly5.util.ConnectionFactory;
import edu.sjsu.fly5.util.FaultBean;
import edu.sjsu.fly5.util.Fly5Exception;
import edu.sjsu.fly5.util.Messages;

public class TravellerDao implements Messages {
	

	public boolean addTraveller (Person person, Traveller traveller) throws Fly5Exception{
		System.out.println("in add traveller");
		boolean flag=false;
		boolean checkAvailability = false;
		String query= "insert into person(first_name,last_name,street,city,state,zip,date_of_birth,emailAddress,password) values (?,?,?,?,?,?,?,?,?) ";
		String queryCheck = "Select * from person";
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3 = null;
		
		int check=0;
		int check1=0;
		int count = 0;
		try{
			
			con=ConnectionFactory.getInstance().getConnection();
			ps3=con.prepareStatement(queryCheck);
			ResultSet rs1 = ps3.executeQuery();
		
		while(rs1.next()){
			String emailAddress = person.getEmailAddress();
			if(emailAddress.equals(rs1.getString(9)))
			{flag = false;
			return flag;
			}
			
		}
			
System.out.println("Same id"+checkAvailability);


	con.setAutoCommit(false);
	ps=con.prepareStatement(query);

		ps.setString(1, person.getFirstName());
		ps.setString(2, person.getLastName());
		ps.setString(3, person.getAddress());
		ps.setString(4, person.getCity());
		ps.setString(5, person.getState());
		ps.setLong(6,person.getZipcode());
		
		java.util.Date utilDate=person.getDateOfBirth();
		java.sql.Date sqlDate= new java.sql.Date(utilDate.getTime());
		ps.setDate(7,sqlDate);
		ps.setString(8, person.getEmailAddress());
		ps.setString(9,person.getPassword());
		
		check=ps.executeUpdate();
		
		if(check>0){
			flag = true;
			System.out.println("updated person");
		}
		
		
		ps1 = con.prepareStatement("select max(id) from person");
		
		ResultSet rs = ps1.executeQuery();
		
		if(rs.next()){
			
			count=rs.getInt(1);
			System.out.println("id from the person"+ count);
			
		}
		
		String query1= "insert into traveller(traveller_id,passport_number,nationality)values(?,?,?)";
		ps2=con.prepareStatement(query1);
		ps2.setInt(1,count);
		ps2.setString(2,traveller.getPassportNumber());
		ps2.setString(3,traveller.getNationality());
		
		check1=ps2.executeUpdate();
		
		if(check1>0){
			flag = true;
			System.out.println("updated traveller");
		}
		
		con.commit();
		
		
	}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
			
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		
	return flag;
	
	}

	


	public int authenticateTraveller(String email,String password) throws Fly5Exception{
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		int check=0;
		int id = 0;
		
		try{
			System.out.println("in authenticate dao");
			
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			ps=con.prepareStatement("Select * from person where emailAddress = ? && password = ?");
			ps.setString(1, email);
			ps.setString(2,password);
			
			System.out.println(email);
			System.out.println(password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				id = rs.getInt(1);
			}
			System.out.println("id"+id);
				if(id > 0)
				{
					flag=true;
				}
			
		
			con.commit();
		}
		
			
		catch(SQLException e){
			e.printStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
			
		}
		finally{
			
			ConnectionFactory.getInstance().closeConnection(con);
		}
		
		
		return id;
	}
	
	public boolean updateTraveller (Person person, Traveller traveller) throws Fly5Exception{
		boolean flag=false;
		Connection con = null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		
		int check=0; 
		int check1=0;
		
		
		try{
			System.out.println("in update dao");
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			long travellerId = 0; 
			
			ps1=con.prepareStatement("select * from person where emailAddress = ?  ");
			ps1.setString(1, person.getEmailAddress());
			System.out.println("executing query");
			ResultSet rs=ps1.executeQuery();
			while(rs.next()){
				
					travellerId=rs.getLong(1);
					}
			System.out.println("travellerId"+travellerId);
			System.out.println("dob : "+person.getDateOfBirth());
			ps=con.prepareStatement("update person set first_name = ?,last_name = ?,street = ?,city = ?,state = ?,zip = ?,date_of_birth = ?, password = ? where emailAddress = ?");
			ps.setString(1,	person.getFirstName());
			
			java.util.Date utilDate=person.getDateOfBirth();
			java.sql.Date sqlDate= new java.sql.Date(utilDate.getTime());
			
			ps.setString(2,person.getLastName());
			ps.setString(3, person.getAddress());
			ps.setString(4, person.getCity());
			ps.setString(5, person.getState());
			ps.setLong(6,person.getZipcode());
			ps.setDate(7, sqlDate);
			ps.setString(8, person.getPassword());
			ps.setString(9, person.getEmailAddress());
			
			check = ps.executeUpdate();
			System.out.println("check value:"+check);
			if(check > 0){
				flag = true;
			}
			
			ps2=con.prepareStatement("update traveller set passport_number = ?, nationality = ? where traveller_id = ?");
			ps2.setString(1,traveller.getPassportNumber());
			ps2.setString(2, traveller.getNationality());
			ps2.setLong(3, travellerId);
			
			check1= ps2.executeUpdate();
			System.out.println("executing traveller query");
			if(check1 > 0){
				flag = true;
			}
			
			con.commit();
			
			
		}
		catch(SQLException e){
			e.getStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
			
		}
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return flag;
	}
	
	public Traveller viewTravellerInfo(long travellerId) throws Fly5Exception{
		
		Traveller traveller = new Traveller();
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try{
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("select * from person where id = ?");
			
			ps.setLong(1, travellerId);
			ResultSet rs1 = ps.executeQuery();
			
			
			while(rs1.next()){
				traveller.setFirstName(rs1.getString(2));
				traveller.setLastName(rs1.getString(3));
				traveller.setAddress(rs1.getString(4));
				traveller.setCity(rs1.getString(5));
				traveller.setState(rs1.getString(6));
				traveller.setZipcode(rs1.getLong(7));
				traveller.setDateOfBirth(rs1.getDate(8));
				traveller.setEmailAddress(rs1.getString(9));
				traveller.setPassword(rs1.getString(10));
				
		
				
			}
			ps1 = con.prepareStatement("select * from traveller where traveller_id = ?");
			ps1.setLong(1, travellerId);
			ResultSet rs2 = ps1.executeQuery();
			while(rs2.next()){
				
				traveller.setPassportNumber(rs2.getString(2));
				traveller.setNationality(rs2.getString(3));
			}
			con.commit();
			
		}
		catch (SQLException e){
			e.getStackTrace();
			throw new Fly5Exception (new FaultBean(e.getMessage()));
			
		}
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return traveller;
		
	}
	public Traveller[] listTraveller() throws Fly5Exception, ParseException{
		List<Traveller> listOfTraveller = new ArrayList<Traveller>();
		
		Traveller traveller = null;
		Connection con = null;
		PreparedStatement ps = null;
		Traveller[] travellerList;
		
		try{
			con = ConnectionFactory.getInstance().getConnection();
			
			ps = con.prepareStatement("select * from traveller");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				traveller=new Traveller();
				traveller=getOtherInfo(rs.getLong(1));
				traveller.setTravellerID(rs.getLong(1));
				traveller.setPassportNumber(rs.getString(2));
				traveller.setNationality(rs.getString(3));
				 
				listOfTraveller.add(traveller);
			}
			travellerList = listOfTraveller.toArray(new Traveller[listOfTraveller.size()] );
		
		
		}
		catch(SQLException e)
		{
			e.getStackTrace();
			throw new Fly5Exception (new FaultBean(e.getMessage()));
		}
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return travellerList;
	}
	public Traveller getOtherInfo(long travellerId)throws Fly5Exception{
		
		Traveller traveller = new Traveller();
		Connection con = null;
		PreparedStatement ps = null;
		
		try{
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("select * from person where id = ?");
			ps.setLong(1, travellerId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				traveller.setFirstName(rs.getString(2));
				traveller.setLastName(rs.getString(3));
				traveller.setAddress(rs.getString(4));
				traveller.setCity(rs.getString(5));
				traveller.setState(rs.getString(6));
				traveller.setZipcode(rs.getLong(7));
				traveller.setDateOfBirth(rs.getDate(8));
				traveller.setEmailAddress(rs.getString(9));
				traveller.setPassword(rs.getString(10));
				
			}
			con.commit();
			
		}
		catch (SQLException e){
			e.getStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
			
		}
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return traveller;
	}
	
	public boolean removeTraveller(long travellerId ) throws Fly5Exception{
		
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int check = 0;
		int check1 = 0;
		
		try{
				con = ConnectionFactory.getInstance().getConnection();
				con.setAutoCommit(false);
				ps = con.prepareStatement("delete from person where id = ?");
				ps.setLong(1,travellerId);
				
				check = ps.executeUpdate();
				if(check > 0){
					flag = true;
				}
				
				ps1 = con.prepareStatement("delete from traveller where traveller_id = ?"); 
				ps.setLong(1, travellerId);
				
				check1 = ps.executeUpdate();
				if(check1 > 0){
					flag = true;
				}
			con.commit();
			
		}
		catch (SQLException e){
			e.getStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
		}
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return flag;
	}
}
