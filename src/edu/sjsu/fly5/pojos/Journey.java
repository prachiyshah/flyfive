package edu.sjsu.fly5.pojos;


import java.sql.Time;

import java.util.Date;

public class Journey 
{
private String bookingReferenceNo;
private String source;
private String destination;
private double totalPrice;
private String typeOfClass;
private String typeOfTrip;
private Flight[] listOfFlights;
private Traveller[] listOfTraveller;
private Date departureDate;
private Date arrivalDate;
private Time arrivalTime;
private Time departureTime;






public Journey() {
	super();
}



public Journey(String bookingReferenceNo, String source, String destination,
		double totalPrice, String typeOfClass, String typeOfTrip,
		Flight[] listOfFlights, Traveller[] listOfTraveller,
		Date departureDate, Date arrivalDate, Time arrivalTime,
		Time departureTime) {

	this.bookingReferenceNo = bookingReferenceNo;
	this.source = source;
	this.destination = destination;
	this.totalPrice = totalPrice;
	this.typeOfClass = typeOfClass;
	this.typeOfTrip = typeOfTrip;
	this.listOfFlights = listOfFlights;
	this.listOfTraveller = listOfTraveller;
	this.departureDate = departureDate;
	this.arrivalDate = arrivalDate;
	this.arrivalTime = arrivalTime;
	this.departureTime = departureTime;
}



public String getBookingReferenceNo() {
	return bookingReferenceNo;
}



public void setBookingReferenceNo(String bookingReferenceNo) {
	this.bookingReferenceNo = bookingReferenceNo;
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



public double getTotalPrice() {
	return totalPrice;
}



public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}



public String getTypeOfClass() {
	return typeOfClass;
}



public void setTypeOfClass(String typeOfClass) {
	this.typeOfClass = typeOfClass;
}



public String getTypeOfTrip() {
	return typeOfTrip;
}



public void setTypeOfTrip(String typeOfTrip) {
	this.typeOfTrip = typeOfTrip;
}



public Flight[] getListOfFlights() {
	return listOfFlights;
}



public void setListOfFlights(Flight[] listOfFlights) {
	this.listOfFlights = listOfFlights;
}



public Traveller[] getListOfTraveller() {
	return listOfTraveller;
}



public void setListOfTraveller(Traveller[] listOfTraveller) {
	this.listOfTraveller = listOfTraveller;
}



public Date getDepartureDate() {
	return departureDate;
}



public void setDepartureDate(Date departureDate) {
	this.departureDate = departureDate;
}



public Date getArrivalDate() {
	return arrivalDate;
}



public void setArrivalDate(Date arrivalDate) {
	this.arrivalDate = arrivalDate;
}



public Time getArrivalTime() {
	return arrivalTime;
}



public void setArrivalTime(Time arrivalTime) {
	this.arrivalTime = arrivalTime;
}



public Time getDepartureTime() {
	return departureTime;
}



public void setDepartureTime(Time departureTime) {
	this.departureTime = departureTime;
}








}
