package edu.sjsu.fly5.pojos;


import java.sql.Time;

import java.util.Date;

public class Journey 
{
private int bookingReferenceNo; //
private String source;//
private String destination;//
private double totalPrice;//
private String typeOfClass;//
private String typeOfTrip;
private Flight[] listOfFlights;
private Traveller[] listOfTraveller;
private int noOfTraveller;
private String departureDate;//
private String arrivalDate;//
private String arrivalTime;
private String departureTime;






public Journey() {
	super();
}



public Journey(int bookingReferenceNo, String source, String destination,
		double totalPrice, String typeOfClass, String typeOfTrip,
		Flight[] listOfFlights, Traveller[] listOfTraveller,
		String departureDate, String arrivalDate, String arrivalTime,
		String departureTime) {

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



public int getBookingReferenceNo() {
	return bookingReferenceNo;
}



public void setBookingReferenceNo(int bookingReferenceNo) {
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



public int getNoOfTraveller() {
	return noOfTraveller;
}



public void setNoOfTraveller(int noOfTraveller) {
	this.noOfTraveller = noOfTraveller;
}



public void setListOfTraveller(Traveller[] listOfTraveller) {
	this.listOfTraveller = listOfTraveller;
}



public String getDepartureDate() {
	return departureDate;
}



public void setDepartureDate(String departureDate) {
	this.departureDate = departureDate;
}



public String getArrivalDate() {
	return arrivalDate;
}



public void setArrivalDate(String arrivalDate) {
	this.arrivalDate = arrivalDate;
}



public String getArrivalTime() {
	return arrivalTime;
}



public void setArrivalTime(String arrivalTime) {
	this.arrivalTime = arrivalTime;
}



public String getDepartureTime() {
	return departureTime;
}



public void setDepartureTime(String departureTime) {
	this.departureTime = departureTime;
}








}
