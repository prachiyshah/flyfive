package edu.sjsu.fly5.pojos;

import java.sql.Time;

public class Flight 
{
private String flightID;
private String source;
private String destination;
private long noOfSeats;
private String flightStatus;
private Time departureTime;
private Time arrivalTime;
private double baseFare;
private long crewID;
private long distance;
private String availibilityDays;




public Flight() {
	super();
}

public Flight(String flightID, String source, String destination,long noOfSeats, String flightStatus, Time departureTime,Time arrivalTime, double baseFare, long crewID, long distance,String availibilityDays) {
	
	this.flightID = flightID;
	this.source = source;
	this.destination = destination;
	this.noOfSeats = noOfSeats;
	this.flightStatus = flightStatus;
	this.departureTime = departureTime;
	this.arrivalTime = arrivalTime;
	this.baseFare = baseFare;
	this.crewID = crewID;
	this.distance = distance;
	this.availibilityDays = availibilityDays;
}

public String getFlightID() {
	return flightID;
}

public void setFlightID(String flightID) {
	this.flightID = flightID;
}

public String getSource() {
	return source;
}

public void setSource(String source) {
	this.source = source;
}

public String getDestination() {
	return destination;
}

public void setDestination(String destination) {
	this.destination = destination;
}

public long getNoOfSeats() {
	return noOfSeats;
}

public void setNoOfSeats(long noOfSeats) {
	this.noOfSeats = noOfSeats;
}

public String getFlightStatus() {
	return flightStatus;
}

public void setFlightStatus(String flightStatus) {
	this.flightStatus = flightStatus;
}

public Time getDepartureTime() {
	return departureTime;
}

public void setDepartureTime(Time departureTime) {
	this.departureTime = departureTime;
}

public Time getArrivalTime() {
	return arrivalTime;
}

public void setArrivalTime(Time arrivalTime) {
	this.arrivalTime = arrivalTime;
}

public double getBaseFare() {
	return baseFare;
}

public void setBaseFare(double baseFare) {
	this.baseFare = baseFare;
}

public long getCrewID() {
	return crewID;
}

public void setCrewID(long crewID) {
	this.crewID = crewID;
}

public long getDistance() {
	return distance;
}

public void setDistance(long distance) {
	this.distance = distance;
}

public String getAvailibilityDays() {
	return availibilityDays;
}

public void setAvailibilityDays(String availibilityDays) {
	this.availibilityDays = availibilityDays;
}






}
