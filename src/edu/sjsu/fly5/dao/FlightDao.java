package edu.sjsu.fly5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import edu.sjsu.fly5.pojos.Flight;
import edu.sjsu.fly5.pojos.FlightInstance;
import edu.sjsu.fly5.pojos.FlightSearchAttributes;
import edu.sjsu.fly5.pojos.TravelerInfo;
import edu.sjsu.fly5.util.ConnectionFactory;
import edu.sjsu.fly5.util.FaultBean;
import edu.sjsu.fly5.util.Fly5Constants;
import edu.sjsu.fly5.util.Fly5Exception;
import edu.sjsu.fly5.util.Messages;

public class FlightDao implements Messages{

	private static final String YYYY_MM_DD = "yyyy-MM-dd";
	private static final String TIME_SEPARATOR = ":";
	private static final int SEAT_RATIO_INDEX = 3;
	private static final int TRAVEL_CLASS_NAME_INDEX = 1;
	private static final int FLIGHT_ID_INDEX = 1;
	private static final int JOURNEY_TIME_INDEX = 13;
	private static final int AIRLINE_INDEX = 12;
	private static final int CREW_ID_INDEX = 11;
	private static final int FREQUENCY_INDEX = 10;
	private static final int DISTANCE_INDEX = 9;
	private static final int BASE_FAIR_INDEX = 8;
	private static final int FLIGHT_STATUS_INDEX = 7;
	private static final int ARRIVAL_TIME = 6;
	private static final int DEPARTURE_TIME_INDEX = 5;
	private static final int DESTINATION_INDEX = 4;
	private static final int SOURCE_INDEX = 3;
	private static final int NO_OF_SEATS_INDEX = 2;
	
	
	public FlightInstance[] searchFlight(FlightSearchAttributes attributes) throws Fly5Exception
	{
		// Calculate frequency 
		String frequency = calculateFrequency(attributes.getDepartureDate());
		// Get non-stop flights 
		List<FlightInstance> directFlights = getDirectFlights(attributes,frequency);
		
		return directFlights.toArray(new FlightInstance[0]);
	}

	@SuppressWarnings("deprecation")
	private String calculateFrequency(String date) throws Fly5Exception {
		String[] dateArray = date.split("-"); 
		Date departureDate = new Date(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]));
		int day = departureDate.getDay();
		switch (day)
		{
		case 0: return Fly5Constants.SUNDAY;
		case 1: return Fly5Constants.MONDAY;
		case 2: return Fly5Constants.TUESDAY;
		case 3: return Fly5Constants.WEDNESDAY;
		case 4: return Fly5Constants.THIRSDAY;
		case 5: return Fly5Constants.FRIDAY;
		case 6: return Fly5Constants.SUNDAY;
				
		}
		throw new Fly5Exception(new FaultBean(EXC111));
	}
	
	/*private List<FlightSearchResult> getConnectingFlights(FlightSearchAttributes attributes) {
		
		return null;
	}*/

	private List<FlightInstance> getDirectFlights(FlightSearchAttributes attributes, String frequency) throws Fly5Exception {
		List<FlightInstance> flightList = new ArrayList<FlightInstance>();
		Connection con = null;
		con = ConnectionFactory.getInstance().getConnection();
		String query = "select fno,fare,source,destination,no_of_seats,available_seats,departure_time,arrival_time,"+
					   "departure_date, journey_time,distance,arrival_date,name from (select f.flight_no as fno,f.source,f.destination,f.frequency,"+
						"f.departure_time,f.arrival_time,"+
						"f.distance,(f.base_fare * tc1.fare_factor) as fare,f.no_of_seats,f.journey_time,tc1.name "+
						"from fly5.flight f,fly5.travel_class tc1 where tc1.name=?) as F left join fly5.flight_instance fi "+
						"on fi.flight_no=F.fno and departure_date=? and class=? "+
						"where F.source=? and F.destination=? and F.frequency LIKE '%" + frequency + "%';";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, attributes.getTravelClass());
			preparedStatement.setString(2, attributes.getDepartureDate());
			preparedStatement.setString(3, attributes.getTravelClass());
			preparedStatement.setString(4, attributes.getSource());
			preparedStatement.setString(5, attributes.getDestination());
			//preparedStatement.setString(6, frequency);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				FlightInstance directFlightInstance = new FlightInstance();
				directFlightInstance.setFlight_no(Fly5Constants.FLIGHT_NUMBER_PREFIX + resultSet.getInt(1));
				directFlightInstance.setAdultFare(resultSet.getFloat(2));
				directFlightInstance.setSource(resultSet.getString(3));
				directFlightInstance.setDestination(resultSet.getString(4));
				directFlightInstance.setTotalSeats(resultSet.getInt(5));
				directFlightInstance.setAvailableTickets(resultSet.getInt(6));
				directFlightInstance.setDepartuteTime(resultSet.getString(7));
				directFlightInstance.setArrivalTime(resultSet.getString(8));
				directFlightInstance.setDepartureDate(resultSet.getString(9));
				directFlightInstance.setJourneyTime(resultSet.getString(10));
				directFlightInstance.setDistance(resultSet.getFloat(11));
				directFlightInstance.setArrivalDate(resultSet.getString(12));
				directFlightInstance.setTravelClass(resultSet.getString(13));
							
				flightList.add(directFlightInstance);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Fly5Exception(new FaultBean(EXC108));
		}catch (Exception e){
			e.printStackTrace();	
		}
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return flightList;
	}

	public void createFlight(Flight flight) throws Fly5Exception
	{
		int flightId = isDuplicateFlight(flight);
		// flight entry is already present in the database
		if(flightId!=0)
		{
			throw new Fly5Exception(new FaultBean(EXC109, flightId));
		}
		
		String arrivalTime = getArrivalTime(flight.getDepartureTime(),flight.getJourneyTime());
		java.sql.Connection connection = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String query = "insert into flight(no_of_seats,source,destination,"
				+ "departure_time,arrival_time,base_fare,distance,frequency,crew,airline,journey_time) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, flight.getNoOfSeats());
			preparedStatement.setString(2, flight.getSource());
			preparedStatement.setString(3, flight.getDestination());
			//Time departureTime = new Time(Long.parseLong(flight.getDepartureTime()));
			preparedStatement.setString(4, flight.getDepartureTime());
			//Time arrivalTime = new Time(Long.parseLong(flight.getArrivalTime()));
			preparedStatement.setString(5, arrivalTime);
			preparedStatement.setDouble(6, flight.getBaseFare());
			preparedStatement.setLong(7, flight.getDistance());
			preparedStatement.setString(8, flight.getFrequency());
			preparedStatement.setLong(9, flight.getCrewID());
			preparedStatement.setString(10,flight.getAirline());
			preparedStatement.setString(11, flight.getJourneyTime());
			preparedStatement.executeUpdate();
			
			// Get flight number of recently created flight
			String query1 = "select max(flight_no) from flight";
			PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
			ResultSet rs = preparedStatement1.executeQuery();
			rs.next();
			int flight_no = rs.getInt(1);
			// Create flight seats for recently added flight
			createFlightSeats(flight_no,flight.getNoOfSeats());
			// Create flight instances for the recently created flight
			createFlightInstances(flight,flight_no);
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108), e);

		}finally{
			ConnectionFactory.getInstance().closeConnection(connection);
		}

	}

	private String getArrivalTime(String departureTime, String journeyTime) {
		int HOUR = 3600 * 1000;
		int MIN = 60 * 1000;
		String[] departureTimeComponents = departureTime.split(TIME_SEPARATOR);
		int departureHours = Integer.parseInt(departureTimeComponents[0]);
		int departureMins = Integer.parseInt(departureTimeComponents[1]);

		String[] journeyTimeComponents = journeyTime.split(TIME_SEPARATOR);
		int journeyHours = Integer.parseInt(journeyTimeComponents[0]);
		int journeyMins = Integer.parseInt(journeyTimeComponents[1]);
		
		Date deptDate = new Date();
		deptDate.setHours(departureHours);
		deptDate.setMinutes(departureMins);
		deptDate.setTime(deptDate.getTime()+ (journeyHours * HOUR + journeyMins * MIN));
		return deptDate.getHours() + TIME_SEPARATOR + deptDate.getMinutes();
	}

	private String getArrivalTime(String departureTime, float journeyTime) {
		// TODO Auto-generated method stub
		return null;
	}

	private void createFlightSeats(int flight_no, int noOfSeats) throws Fly5Exception {
		
		Connection con = null;
		con = ConnectionFactory.getInstance().getConnection();
		String query = "select * from travel_class";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					String travelClass = rs.getString(TRAVEL_CLASS_NAME_INDEX);
					float seatRatio = rs.getFloat(SEAT_RATIO_INDEX);
					int seats = Math.round(noOfSeats * seatRatio); 
					insertIntoFlightSeats(flight_no,travelClass,seats);
				}
			}
			else
			{
				throw new Fly5Exception(new FaultBean(EXC113));
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108));
		}
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
	}

	private void insertIntoFlightSeats(int flight_no, String travelClass,
			int seats) throws Fly5Exception {
		Connection con = ConnectionFactory.getInstance().getConnection();
		String query = "insert into flight_seats(flight_no,class,total_seats) values(?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, flight_no);
			preparedStatement.setString(2, travelClass);
			preparedStatement.setInt(3, seats);
			preparedStatement.executeUpdate();
		}catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("Seats already exist for flightNo="+flight_no+" and travelClass="+travelClass);
		} 
		catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC112, Fly5Constants.FLIGHT_NUMBER_PREFIX+flight_no,travelClass));
		}finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		
	}

	private void createFlightInstances(Flight flight, int flight_no) throws Fly5Exception {
		long HOUR = 3600*1000;
		long MIN = 60 * 1000;
		long SEC = 60;
		long DAY = 24 * HOUR;
		
		Connection con = ConnectionFactory.getInstance().getConnection();
		String query = "insert into fly5.flight_instance(flight_no,departure_date,arrival_date,available_seats,class)"+
				"values(?, ?, ?, (select total_seats from fly5.flight_seats where flight_no = ? and class=?),?),"+
				"(?, ?, ?, (select total_seats from fly5.flight_seats where flight_no = ? and class=?),?),"
				+ "(?, ?, ?, (select total_seats from fly5.flight_seats where flight_no = ? and class=?),?);";
		String departureTime = flight.getDepartureTime();
		String[] departureTimeComponents = departureTime.split(TIME_SEPARATOR);
		int departureHours = Integer.parseInt(departureTimeComponents[0]);
		int departureMins = Integer.parseInt(departureTimeComponents[1]);
		int departureSecs = 0;
		
		// Departure Start date would be the next day
		Date departureStartDate = new Date();
		departureStartDate.setTime(departureStartDate.getTime()+ DAY + (departureHours*HOUR)
				+ (departureMins*MIN) + (departureSecs * SEC) );
		
		// Departure end date would be a date after 6 months
		Date departureEndDate = new Date();
		departureEndDate.setTime(departureEndDate.getTime()+ (180 * DAY) + (departureHours*HOUR) 
				+ (departureMins*MIN) + (departureSecs * SEC));


		String jounrneyTime = flight.getJourneyTime();
		String array[] = jounrneyTime.split(TIME_SEPARATOR);
		int journeyHours = Integer.parseInt(array[0]);
		int journeyMinutes = Integer.parseInt(array[1]);
		
		try {
			while(!departureStartDate.equals(departureEndDate))
			{
				Date arrivalDate = new Date(departureStartDate.getTime()+ (journeyHours * HOUR)+ (journeyMinutes * MIN));
				SimpleDateFormat dateFormatter = new SimpleDateFormat(YYYY_MM_DD);
				
				// Insert this instance of departure date and arrival date into flight_instance table
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, flight_no);
				preparedStatement.setString(2, dateFormatter.format(departureStartDate));
				preparedStatement.setString(3, dateFormatter.format(arrivalDate));
				preparedStatement.setInt(4, flight_no);
				preparedStatement.setString(5, Fly5Constants.FIRST);
				preparedStatement.setString(6, Fly5Constants.FIRST);

				preparedStatement.setInt(7, flight_no);
				preparedStatement.setString(8, dateFormatter.format(departureStartDate));
				preparedStatement.setString(9, dateFormatter.format(arrivalDate));
				preparedStatement.setInt(10, flight_no);
				preparedStatement.setString(11, Fly5Constants.BUSINESS);
				preparedStatement.setString(12, Fly5Constants.BUSINESS);

				preparedStatement.setInt(13, flight_no);
				preparedStatement.setString(14, dateFormatter.format(departureStartDate));
				preparedStatement.setString(15, dateFormatter.format(arrivalDate));
				preparedStatement.setInt(16, flight_no);
				preparedStatement.setString(17, Fly5Constants.ECONOMY);
				preparedStatement.setString(18, Fly5Constants.ECONOMY);

				preparedStatement.executeUpdate();
				
				// Increase departure start day by 1
				departureStartDate.setTime(departureStartDate.getTime() + DAY);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Fly5Exception(new FaultBean(EXC108));
		}		
	}

	public Flight getFlight(String flightID) throws Fly5Exception
	{
		Connection con = null;
		con = ConnectionFactory.getInstance().getConnection();
		String query = "select * from flight where flight_no=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			int flightNumber = Integer.parseInt(flightID.substring(Fly5Constants.FLIGHT_NUMBER_PREFIX.length()));
			preparedStatement.setInt(1, flightNumber);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.isBeforeFirst())
			{
				rs.next();
				Flight flight = new Flight();
				flight.setFlightID(flightID);
				flight.setNoOfSeats(rs.getInt(NO_OF_SEATS_INDEX));
				flight.setSource(rs.getString(SOURCE_INDEX));
				flight.setDestination(rs.getString(DESTINATION_INDEX));
				flight.setDepartureTime(rs.getString(DEPARTURE_TIME_INDEX));
				flight.setArrivalTime(rs.getString(ARRIVAL_TIME).toString());
				flight.setFlightStatus(rs.getString(FLIGHT_STATUS_INDEX));
				flight.setBaseFare(rs.getFloat(BASE_FAIR_INDEX));
				flight.setDistance(rs.getInt(DISTANCE_INDEX));
				flight.setFrequency(rs.getString(FREQUENCY_INDEX));
				flight.setCrewID(rs.getInt(CREW_ID_INDEX));
				flight.setAirline(rs.getString(AIRLINE_INDEX));
				flight.setJourneyTime(rs.getString(JOURNEY_TIME_INDEX));
				
				return flight;
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108), e);
		}	
		finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		throw new Fly5Exception(new FaultBean(EXC110));	
	}	

	public TravelerInfo[] getListOfCustomersOnBoard(String flight_no,String departureDate) throws Fly5Exception
	{
		List<TravelerInfo> listOfTravellers = new ArrayList<TravelerInfo>();
		Connection con = ConnectionFactory.getInstance().getConnection();
		String query = "select jt.ticket_no,p.first_name,p.last_name from journey_travellers jt join person p"
				+"on jt.traveller_id = p.id where jt.booking_reference in (select jf.booking_reference"
				+"from journey_flights jf,journey j where jf.flight_no=? and j.departure_date=?);";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(flight_no.substring(Fly5Constants.FLIGHT_NUMBER_PREFIX.length())));
			preparedStatement.setString(2, departureDate);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.isBeforeFirst())
			{
				TravelerInfo travelerInfo = new TravelerInfo();
				travelerInfo.setTicket_no(rs.getInt(1));
				travelerInfo.setFirstName(rs.getString(2));
				travelerInfo.setLastName(rs.getString(3));
				listOfTravellers.add(travelerInfo);
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108));
		}
		return listOfTravellers.toArray(new TravelerInfo[0]);
	}
	
	public Flight[] getAllFlights() throws Fly5Exception
	{
		List<Flight> flightList = new ArrayList<Flight>();
		Connection con = null;
		con = ConnectionFactory.getInstance().getConnection();
		String query = "select * from flight;";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Flight flight = new Flight();
				flight.setFlightID(Fly5Constants.FLIGHT_NUMBER_PREFIX+rs.getInt(FLIGHT_ID_INDEX));
				flight.setNoOfSeats(rs.getInt(NO_OF_SEATS_INDEX));
				flight.setSource(rs.getString(SOURCE_INDEX));
				flight.setDestination(rs.getString(DESTINATION_INDEX));
				flight.setDepartureTime(rs.getString(DEPARTURE_TIME_INDEX));
				flight.setArrivalTime(rs.getString(ARRIVAL_TIME).toString());
				flight.setFlightStatus(rs.getString(FLIGHT_STATUS_INDEX));
				flight.setBaseFare(rs.getFloat(BASE_FAIR_INDEX));
				flight.setDistance(rs.getInt(DISTANCE_INDEX));
				flight.setFrequency(rs.getString(FREQUENCY_INDEX));
				flight.setCrewID(rs.getInt(CREW_ID_INDEX));
				flight.setAirline(rs.getString(AIRLINE_INDEX));
				flight.setJourneyTime(rs.getString(JOURNEY_TIME_INDEX));
				flightList.add(flight);
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108), e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return flightList.toArray(new Flight[0]);		
	}

	/**
	 * This function checks whether a flight is already present in the database
	 * in order to avoid duplicate entries
	 * @param newFlight
	 * @return
	 * @throws Fly5Exception 
	 */
	public int isDuplicateFlight(Flight newFlight) throws Fly5Exception
	{
		Connection con = null;
		con = ConnectionFactory.getInstance().getConnection();
		String query = "select * from flight where source=? and destinatio"
				+ "n=? and departure_time=? and journey_time=? and frequency=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, newFlight.getSource());
			preparedStatement.setString(2, newFlight.getDestination());
			preparedStatement.setString(3, newFlight.getDepartureTime());
			preparedStatement.setString(4,newFlight.getJourneyTime());
			preparedStatement.setString(5, newFlight.getFrequency());

			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				resultSet.next();
				return resultSet.getInt(FLIGHT_ID_INDEX);
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108), e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}

		return 0;
	}

	public void deleteFlight(String flightId) throws Fly5Exception
	{
		Connection con = null;
		con = ConnectionFactory.getInstance().getConnection();
		String query = "delete from flight where flight_no=?";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(flightId.substring(Fly5Constants.FLIGHT_NUMBER_PREFIX.length())));
			int response = preparedStatement.executeUpdate();
			if(response == 0)
			{
				throw new Fly5Exception(new FaultBean(EXC110, flightId));
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108), e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		
	}
	
	public void updateFlight(Flight flight) throws Fly5Exception
	{
		Connection con = null;
		con = ConnectionFactory.getInstance().getConnection();
		String query = "update flight set no_of_seats=?,source=?,destination=?,departure_time=?,arrival_time=?,status=?,base_fare=?,"
				+ "distance=?,frequency=?,crew=?,airline=?,journey_time=? where flight_no=?";
		
		try {
			Flight[]  array = new Flight[1];
			array[0] = flight;
			printFlights(array);
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, flight.getNoOfSeats());
			preparedStatement.setString(2, flight.getSource());
			preparedStatement.setString(3, flight.getDestination());
			preparedStatement.setString(4, flight.getDepartureTime());
			String arrivalTime = getArrivalTime(flight.getDepartureTime(), flight.getJourneyTime());
			preparedStatement.setString(5, arrivalTime);
			preparedStatement.setString(6, flight.getFlightStatus());
			preparedStatement.setFloat(7, (float) flight.getBaseFare());
			preparedStatement.setFloat(8, flight.getDistance());
			preparedStatement.setString(9, flight.getFrequency());
			preparedStatement.setLong(10, flight.getCrewID());
			preparedStatement.setString(11,flight.getAirline());
			preparedStatement.setString(12,flight.getJourneyTime());
			preparedStatement.setInt(13, Integer.parseInt(flight.getFlightID().substring(Fly5Constants.FLIGHT_NUMBER_PREFIX.length())));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean(EXC108), e);
		}finally{
			ConnectionFactory.getInstance().closeConnection(con);
		}
	}
	
private static void printFlights(Flight[] flightArray) {
		
		for(int i=0; i<flightArray.length; i++)
		{
			System.out.println("Airline:"+flightArray[i].getAirline());
			System.out.println("ArrivalTime:"+flightArray[i].getArrivalTime());
			System.out.println("BaseFare"+flightArray[i].getBaseFare());
			System.out.println("Crew:"+flightArray[i].getCrewID());
			System.out.println("Departure time:"+flightArray[i].getDepartureTime());
			System.out.println("Destination:"+flightArray[i].getDestination());
			System.out.println("Distance:"+flightArray[i].getDistance());
			System.out.println("ID:"+flightArray[i].getFlightID());
			System.out.println("Status"+flightArray[i].getFlightStatus());
			System.out.println("Frequency:"+flightArray[i].getFrequency());
			System.out.println("Journey time"+flightArray[i].getJourneyTime());
			System.out.println("No of seats"+flightArray[i].getNoOfSeats());
			System.out.println("Source:"+flightArray[i].getSource());
			System.out.println("\n");
		}
	}
}
