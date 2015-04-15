package edu.sjsu.fly5.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import edu.sjsu.fly5.pojos.Flight;
import edu.sjsu.fly5.pojos.FlightInstance;
import edu.sjsu.fly5.pojos.Journey;
import edu.sjsu.fly5.pojos.PaymentDetails;
import edu.sjsu.fly5.pojos.Person;
import edu.sjsu.fly5.pojos.TravelClass;
import edu.sjsu.fly5.pojos.Traveller;
import edu.sjsu.fly5.util.ConnectionFactory;
import edu.sjsu.fly5.util.Fly5Constants;
import edu.sjsu.fly5.util.Fly5Exception;

public class JourneyDao {
	
	//public boolean bookJourney(Traveller[] traveller,Flight[] flight,PaymentDetails paymentDetails,Person[] person)
	public boolean bookJourney(Traveller[] traveller,FlightInstance[] flightInstance,PaymentDetails paymentDetails,Person[] person)
	{
		boolean flag=false;
		Connection con = null;
		PreparedStatement insetIntoPerson2 = null;
		PreparedStatement insetIntoPerson1 =null;
		PreparedStatement insetIntoPerson3 = null;
		PreparedStatement insertIntoJourney1 = null;
		PreparedStatement insertIntoJourney2 = null;
		PreparedStatement insetJourNeyTraveller = null;
		PreparedStatement insertJourneyFlight = null;
		PreparedStatement insertInFlightSeat1 = null;
		PreparedStatement insertInFlightSeat2 = null;
		int count= 0;
		float finalfare = 0;
		float individualFare = 0;
		int bookingRefId = 0;
		int[] travellerId = new int[traveller.length];
		int check=0;
		int check1=0;
		int check3=0;
		int check4=0;
		int availableSeats = 0;
		boolean isDuplicate = false;
		try 
		{
		
		isDuplicate = isDuplicateJourney(person, flightInstance);
		if(isDuplicate == false)
		{
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			//Insert Query for flight_instances : Start
			for(int i = 0;i<flightInstance.length;i++)
			{
				
					insertInFlightSeat1 = con.prepareStatement("select available_seats from flight_instance where flight_no =? && departure_date = ?");
					System.out.println("flight_no"+flightInstance[i].getFlight_no());
					int flightNo = Integer.parseInt(flightInstance[i].getFlight_no().substring(Fly5Constants.FLIGHT_NUMBER_PREFIX.length()));
					insertInFlightSeat1.setInt(1, flightNo);
					insertInFlightSeat1.setString(2, flightInstance[i].getDepartureDate());
					
					ResultSet set1 = insertInFlightSeat1.executeQuery();
					
					if(set1.next())
					{
						availableSeats = set1.getInt(1);
					}
					
					availableSeats = availableSeats - traveller.length;
					
					insertInFlightSeat2 = con.prepareStatement("update flight_instance set available_seats = ? where flight_no =? && departure_date = ?");
					insertInFlightSeat2.setInt(1, availableSeats);
					insertInFlightSeat2.setInt(2, flightNo);
					insertInFlightSeat2.setString(3, flightInstance[i].getDepartureDate());
					
					insertInFlightSeat2.executeUpdate();
			}
			
			// Insert Query for flight_instances : End
			
			// 1) Book Journey: First Step: Enter the traveller details in Person Table and Traveller Table : Start
			
			
			for(int i=0;i<person.length;i++)
			{
				if(!person[i].getFirstName().equalsIgnoreCase("FromSession"))
				{
					insetIntoPerson1 = con.prepareStatement("insert into person(first_name,last_name,street,city,state,zip,date_of_birth) values (?,?,?,?,?,?,?)");
					insetIntoPerson1.setString(1, person[i].getFirstName());
					insetIntoPerson1.setString(2, person[i].getLastName());
					insetIntoPerson1.setString(3, person[i].getAddress());
					insetIntoPerson1.setString(4, person[i].getCity());
					insetIntoPerson1.setString(5, person[i].getState());
					insetIntoPerson1.setLong(6, person[i].getZipcode());
 					java.util.Date utilDateOfBirth = person[i].getDateOfBirth();
 					java.sql.Date sqlDateOfBirth = new java.sql.Date(utilDateOfBirth.getTime());
					
					
					insetIntoPerson1.setDate(7, sqlDateOfBirth);
					
					check = insetIntoPerson1.executeUpdate();
					
					insetIntoPerson2 = con.prepareStatement("select max(id) from person");
					
					ResultSet resultSet = insetIntoPerson2.executeQuery();
					
					if(resultSet.next())
					{
						//count = Integer.parseInt(resultSet.toString());
						count=resultSet.getInt(1);
					}
					
						travellerId[i] = count; // To store traveller's id in array to later use it for table entry in journey_traveller
				}
				
				else
				{
					insetIntoPerson1 = con.prepareStatement("select * from person where emailAddress = ?");
					insetIntoPerson1.setString(1, person[i].getEmailAddress());
					
					ResultSet resultSet = insetIntoPerson1.executeQuery();
					
					if(resultSet.next())
					{
						count = resultSet.getInt("id");
					}
					travellerId[i] = count;// To store traveller's id in array to later use it for table entry in journey_traveller
				}
					
				System.out.println("Traveller id"+i+":"+travellerId[i]);
				
				insetIntoPerson3 = con.prepareStatement("insert into traveller values(?,?,?,?)");
				insetIntoPerson3.setInt(1, count);
				insetIntoPerson3.setString(2, traveller[i].getPassportNumber());
				insetIntoPerson3.setString(3, traveller[i].getNationality());
				insetIntoPerson3.setString(4, traveller[i].getTravellerClass());
					
					
					check1 = insetIntoPerson3.executeUpdate();
				// 1) Book Journey: First Step: Enter the traveller details in Person Table and Traveller Table : End
			
					// Fare details for each traveller:Start
					if(traveller[i].getTravellerType().equalsIgnoreCase("adult"))
					{
						individualFare = flightInstance[0].getAdultFare();
					}
					else if(traveller[i].getTravellerType().equalsIgnoreCase("child"))
					{
						individualFare = (float) (flightInstance[0].getAdultFare() * 0.5);
					}
					else if(traveller[i].getTravellerType().equalsIgnoreCase("infant"))
					{
						individualFare = 200;
					}
					// Fare details for each traveller: end
				 
					// Total Amount of Fare details
					finalfare += individualFare;
			 }
			
			
			
			// 2) INSERT INTO JOURNEY:::: START:::::::::::::::::::::::::
			insertIntoJourney1 = con.prepareStatement("insert into journey(source,destination,total_price,no_of_travellers,class,departure_date,arrival_date) values(?,?,?,?,?,?,?)");
			insertIntoJourney1.setString(1, flightInstance[0].getSource());
			insertIntoJourney1.setString(2, flightInstance[0].getDestination());
			insertIntoJourney1.setFloat(3,finalfare);
			insertIntoJourney1.setInt(4, traveller.length);
			insertIntoJourney1.setString(5, traveller[0].getTravellerClass());
			insertIntoJourney1.setString(6,flightInstance[0].getDepartureDate());
			insertIntoJourney1.setString(7, flightInstance[0].getArrivalDate());
			
			int check2 = insertIntoJourney1.executeUpdate();
			// 2) INSERT INTO JOURNEY:::: END:::::::::::::::::::::::::
			
			//  3) INSERT INTO JOURNEY_TRAVELERS::::::::START:::::::::::
			
			// Select Booking reference id::::::Start
			insertIntoJourney2 = con.prepareStatement("select max(booking_reference) from journey");
			ResultSet set1 = insertIntoJourney2.executeQuery();
			while(set1.next())
			{
				//bookingRefId = Integer.parseInt(set1.toString());
				bookingRefId = set1.getInt(1);
			}
			// Select Booking reference id::::::End
			
			for(int k = 0; k< travellerId.length;k++)
			{
				
				/*for(int l=0;l<person.length;l++)
				{*/
					if(traveller[k].getTravellerType().equalsIgnoreCase("adult"))
					{
						individualFare = flightInstance[0].getAdultFare();
					}
					else if(traveller[k].getTravellerType().equalsIgnoreCase("child"))
					{
						individualFare = (float) (flightInstance[0].getAdultFare() * 0.5);
					}
					else if(traveller[k].getTravellerType().equalsIgnoreCase("infant"))
					{
						individualFare = 200;
					}
					insetJourNeyTraveller = con.prepareStatement("insert into journey_travellers(traveller_id,booking_reference,price) values(?,?,?)");
					insetJourNeyTraveller.setInt(1, travellerId[k]);
					insetJourNeyTraveller.setInt(2, bookingRefId);
					insetJourNeyTraveller.setFloat(3, individualFare);
				
					check3 = insetJourNeyTraveller.executeUpdate();
					
				//}
			}
			//  3) INSERT INTO JOURNEY_TRAVELERS::::::::END::::::::::::
			
			// 4) INSERT INTO JOURNEY_FLIGHTS :::::::::::START:::::::::
			
			for(int l=0;l<flightInstance.length;l++)
			{
				int flightNo = Integer.parseInt(flightInstance[l].getFlight_no().substring(Fly5Constants.FLIGHT_NUMBER_PREFIX.length()));
				insertJourneyFlight = con.prepareStatement("insert into journey_flights values(?,?)");
				insertJourneyFlight.setInt(1, flightNo);
				insertJourneyFlight.setInt(2, bookingRefId);
				
				check4 = insertJourneyFlight.executeUpdate();
			}
			// 4) INSERT INTO JOURNEY_FLIGHTS :::::::::::END:::::::::
			if(check>0 && check1 > 0 && check2>0 && check3>0 && check4>0)
			{
				flag = true;
			}
			con.commit();
		}
		else
		{
			flag = false;
		}
		}
		catch (SQLException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		catch (Fly5Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				ConnectionFactory.getInstance().closeConnection(con);
			}
			catch (Fly5Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean isDuplicateJourney(Person[] person,FlightInstance[] fliInstances)
	{
		boolean isDuplicate = false;
		Connection con = null;
		PreparedStatement selectPrsnId = null;
		PreparedStatement selectBookingRefId = null;
		PreparedStatement selectJourney = null;
		PreparedStatement selectFlightNo = null;
		String tempSource = null;
		String tempDestination = null;
		String tempDepartureDate = null;
		int check;
		int travellerid = 0;
		int bookingReference = 0;
		String tempFlightNo = null;
		try 
		{
			con = ConnectionFactory.getInstance().getConnection();
			for(int i=0;i<person.length;i++)
			{
				
					selectPrsnId = con.prepareStatement("select * from person where emailAddress = ?");
					selectPrsnId.setString(1, person[i].getEmailAddress());
					
					ResultSet set = selectPrsnId.executeQuery();
					
					if(set.next())
					{
						travellerid = set.getInt("id");
					}
					
					selectBookingRefId = con.prepareStatement("select * from journey_travellers where traveller_id=? ");
					selectBookingRefId.setInt(1, travellerid);
					
					ResultSet set1 = selectBookingRefId.executeQuery();
					
					if(set1.next())
					{
						bookingReference = set1.getInt("booking_refernece");
					}
					
					selectJourney = con.prepareStatement("select * from journey where booking_reference = ?");
					selectJourney.setInt(1, bookingReference);
					
					ResultSet set2 = selectJourney.executeQuery();
					
					if(set2.next())
					{
						 tempSource = (set2.getString("source"));
						tempDestination = (set2.getString("destination"));
						tempDepartureDate = (set2.getDate("departure_date").toString());
					}
					
					selectFlightNo = con.prepareStatement("select * from journey_flights where booking_reference =?");
					selectFlightNo.setInt(1, bookingReference);
					
					ResultSet set3 = selectFlightNo.executeQuery();
					
					if(set3.next())
					{
						tempFlightNo = set3.getString("flight_no");
					}
					
					if(fliInstances[0].getSource().equalsIgnoreCase(tempSource) && fliInstances[0].getDestination().equalsIgnoreCase(tempDestination) && fliInstances[0].getDepartureDate().equalsIgnoreCase(tempDepartureDate) && (fliInstances[0].getFlight_no().equalsIgnoreCase(tempFlightNo)))
							{
									isDuplicate = true;
							}
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Fly5Exception e1)
		{
			e1.printStackTrace();
		}
		
		return isDuplicate;
	}

	public boolean cancelJourney(int bookingId)
	{	
		boolean flag=false;
		Connection con = null;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		
		try 
		{
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			statement = con.prepareStatement("delete from journey_travellers where booking_reference =?");
			statement.setInt(1, bookingId);
			
			int check = statement.executeUpdate();
			
			statement1 = con.prepareStatement("delete from journey_flights where booking_reference = ?");
			statement1.setInt(1, bookingId);
			
			int check1 = statement1.executeUpdate();
			
			statement2 = con.prepareStatement("delete from journey where booking_reference = ?");
			statement2.setInt(1, bookingId);
			int check2 = statement2.executeUpdate();
			
			if(check>0 && check1>0 && check2>0)
			{
				flag = true;
			}
			con.commit();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Fly5Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				ConnectionFactory.getInstance().closeConnection(con);
			}
			catch (Fly5Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean rescheduleJourney(String bookingId,Journey journey)
	{
		boolean flag=false;
		Connection con = null;
		PreparedStatement statement = null;
		
		try 
		{
			con =ConnectionFactory.getInstance().getConnection();
			statement = con.prepareStatement("update journey set departure_date =?,arrival_date=? where booking_reference =?");
			statement.setString(1, journey.getDepartureDate());
			statement.setString(2, journey.getArrivalDate());
			statement.setInt(3, journey.getBookingReferenceNo());
			
			int check = statement.executeUpdate();
			
			if(check>0)
			{
				flag = true;
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Fly5Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<Journey> listAllJourneys()
	{
		List<Journey> listOfJourney=new ArrayList<Journey>();
		Journey journey=null;
		Connection con = null;
		PreparedStatement statement = null;
		ArrayList<Flight> listFlights = new ArrayList<Flight>();
		ArrayList<Traveller> listTravellers = new ArrayList<Traveller>();
		
		try 
		{
			con = ConnectionFactory.getInstance().getConnection();
			statement = con.prepareStatement("select * from journey");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				journey = new Journey();
				journey.setBookingReferenceNo(resultSet.getInt("booking_reference"));
				journey.setSource(resultSet.getString("source"));
				journey.setDestination(resultSet.getString("destination"));
				journey.setTypeOfClass(resultSet.getString("class"));
				journey.setArrivalDate(resultSet.getString("arrival_date"));
				journey.setDepartureDate(resultSet.getString("departure_date"));
				journey.setTotalPrice(resultSet.getInt("total_price"));
				journey.setNoOfTraveller(resultSet.getInt(5));
				listFlights = getFlightDetails(journey.getBookingReferenceNo());
				Flight[] listOfAllFlightsFinal=listFlights.toArray(new Flight[listFlights.size()]);
				journey.setListOfFlights(listOfAllFlightsFinal);
				
				listTravellers = getPersonDetails(journey.getBookingReferenceNo());
				Traveller[] traveller = listTravellers.toArray(new Traveller[listTravellers.size()]);
				journey.setListOfTraveller(traveller);
				
				listOfJourney.add(journey);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Fly5Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfJourney;
	}
	
	public ArrayList<Traveller> getPersonDetails(int bookingReferenceNo)
	{
		ArrayList<Traveller> listofPersonDetails = new ArrayList<Traveller>();
		Connection con = null;
		PreparedStatement statement = null;
		PreparedStatement ps = null;
		PreparedStatement statement2 = null;
		Traveller traveller = null;
		int count = 0;
		
		try
		{
			con = ConnectionFactory.getInstance().getConnection();
			ps = con.prepareStatement("select count(*) from journey_travellers where booking_reference = ?");
			ps.setInt(1, bookingReferenceNo);
			
			ResultSet set = ps.executeQuery();
			
			if(set.next())
			{
				count = set.getInt(1);
			}
		
			/*for(int i=0;i<count;i++)
			{*/
				statement = con.prepareStatement("select * from person where id in (select t.traveller_id from journey_travellers t,journey j where(t.booking_reference = ?))");
				statement.setInt(1, bookingReferenceNo);
				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next())
				{
					traveller = new Traveller();
					
					traveller = getTravellerOtherInfo(bookingReferenceNo);
					traveller.setFirstName(rs.getString("first_name"));
					traveller.setLastName(rs.getString("last_name"));
					traveller.setAddress(rs.getString("street"));
					traveller.setCity(rs.getString("city"));
					traveller.setDateOfBirth(rs.getDate("date_of_birth"));
					traveller.setEmailAddress(rs.getString("emailAddress"));
					traveller.setPassword(rs.getString("password"));
					traveller.setState(rs.getString("state"));
					traveller.setZipcode(rs.getInt("zip"));
					
					listofPersonDetails.add(traveller);
				}
			//}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Fly5Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				ConnectionFactory.getInstance().closeConnection(con);
			}
			catch (Fly5Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listofPersonDetails;
	}
	
	public Traveller getTravellerOtherInfo(int bookingRefNo) throws Fly5Exception
	{
		PreparedStatement ps=null;
		Connection con=null;
		Traveller traveller=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement("select * from traveller where traveller_id in (select t.traveller_id from journey_travellers t,journey j where(t.booking_reference = ?))");
			ps.setInt(1, bookingRefNo);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				traveller = new Traveller();
				traveller.setPassportNumber(rs.getString(2));
				traveller.setNationality(rs.getString(3));
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
		
		return traveller;
		
		
		
	}
	
	public ArrayList<Flight> getFlightDetails(int bookingReferenceNo)
	{
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement statement1=null;
		ArrayList<Flight> listOfFlight = new ArrayList<>();
		Flight flight;
		int count=0;
		
		try 
		{
			con = ConnectionFactory.getInstance().getConnection();
			ps = con.prepareStatement("select count(*) from journey_flights where booking_reference = ?");
			ps.setInt(1, bookingReferenceNo);
			flight = new Flight();
			ResultSet set = ps.executeQuery();
			
			if(set.next())
			{
				count=set.getInt(1);
			}
			
			for(int i=0;i<count;i++)
			{
				statement1 = con.prepareStatement("select * from flight where flight_no in (select f.flight_no from journey_flights f,journey j where f.booking_reference = ?)");
				statement1.setInt(1, bookingReferenceNo);
				
				ResultSet rs = statement1.executeQuery();
				
				while(rs.next())
				{
					flight.setFlightID(rs.getString("flight_no"));
					flight.setArrivalTime(rs.getString("arrival_time"));
					flight.setDepartureTime(rs.getString("departure_time"));
					listOfFlight.add(flight);
				}
				
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Fly5Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				ConnectionFactory.getInstance().closeConnection(con);
			}
			catch (Fly5Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfFlight;
	}
	
	public ArrayList<Journey> listAllJourney(String userName) throws Fly5Exception
	{
		ArrayList<Journey> listJourneysById = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		int bookingRefNo = 0;
		con = ConnectionFactory.getInstance().getConnection();
		try
		{
			statement = con.prepareStatement("select * from journey_travellers where traveller_id in (select id from person where emailAddress = ?)");
			statement.setString(1, userName);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				bookingRefNo = rs.getInt(3);
				listJourneysById = listAllJourney(bookingRefNo);
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listJourneysById;
	}
	
	
	public ArrayList<Journey> listAllJourney(int bookingReferenceNo)
	{
		ArrayList<Journey> listJourneysById = new ArrayList<>();
		Connection con = null;
		PreparedStatement statement = null;
		Journey journey=null;
		ArrayList<Flight> listFlights = new ArrayList<>();
		ArrayList<Traveller> listTravellers = new ArrayList<Traveller>();
		
		try 
		{
			con = ConnectionFactory.getInstance().getConnection();
			statement = con.prepareStatement("select * from journey where booking_reference = ?");
			statement.setInt(1, bookingReferenceNo);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				journey = new Journey();
				journey.setBookingReferenceNo(resultSet.getInt("booking_reference"));
				journey.setSource(resultSet.getString("source"));
				journey.setDestination(resultSet.getString("destination"));
				journey.setTypeOfClass(resultSet.getString("class"));
				journey.setArrivalDate(resultSet.getString("arrival_date"));
				journey.setDepartureDate(resultSet.getString("departure_date"));
				journey.setTotalPrice(resultSet.getInt("total_price"));
				journey.setNoOfTraveller(resultSet.getInt(5));
				
				listFlights = getFlightDetails(journey.getBookingReferenceNo());
				Flight[] listOfAllFlightsFinal=listFlights.toArray(new Flight[listFlights.size()]);
				journey.setListOfFlights(listOfAllFlightsFinal);
				
				
				listTravellers = getPersonDetails(journey.getBookingReferenceNo());
				Traveller[] traveller = listTravellers.toArray(new Traveller[listTravellers.size()]);
				journey.setListOfTraveller(traveller);
				
				listJourneysById.add(journey);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Fly5Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listJourneysById;
	}
}
