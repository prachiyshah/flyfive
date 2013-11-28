package edu.sjsu.fly5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import edu.sjsu.fly5.util.ConnectionFactory;


import edu.sjsu.fly5.util.FaultBean;
import edu.sjsu.fly5.util.Fly5Constants;
import edu.sjsu.fly5.util.Fly5Exception;
import edu.sjsu.fly5.pojos.Flight;

public class FlightDao {

	private static final int FLIGHT_ID_INDEX = 1;
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

	public void createFlight(Flight flight) throws Fly5Exception
	{
		java.sql.Connection connection = null;
		connection = ConnectionFactory.getInstance().getConnection();
		String query = "insert into flight(no_of_seats,source,destination,"
				+ "departure_time,arrival_time,status,base_fare,distance,frequency,crew,airline) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, flight.getNoOfSeats());
			preparedStatement.setString(2, flight.getSource());
			preparedStatement.setString(3, flight.getDestination());
			Time departureTime = new Time(Long.parseLong(flight.getDepartureTime()));
			preparedStatement.setTime(4, departureTime);
			Time arrivalTime = new Time(Long.parseLong(flight.getArrivalTime()));
			preparedStatement.setTime(5, arrivalTime);
			preparedStatement.setString(6, flight.getFlightStatus());
			preparedStatement.setDouble(7, flight.getBaseFare());
			preparedStatement.setLong(8, flight.getDistance());
			preparedStatement.setString(9, flight.getFrequency());
			preparedStatement.setLong(10, flight.getCrewID());
			preparedStatement.setString(11,flight.getAirline());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean("EXC108"), e);

		}finally{
			ConnectionFactory.getInstance().closeConnection(connection);
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
				return flight;
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean("EXC108"), e);
		}		
		return null;	
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
				flightList.add(flight);
			}
		} catch (SQLException e) {
			throw new Fly5Exception(new FaultBean("EXC108"), e);
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
	public boolean isDuplicateFlight(Flight newFlight) throws Fly5Exception
	{
		Connection con = null;
		String query = "select * from flight where source=? and destination=? and departure_time=? and arrival_time=?";
		//PreparedStatement preparedStatement
		
		return false;

	}

	public void updateFlight(Flight flight)
	{
		
	}
}
