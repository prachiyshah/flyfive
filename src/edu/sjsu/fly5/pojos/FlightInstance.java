package edu.sjsu.fly5.pojos;

public class FlightInstance {
	
	String flight_no;
	String travelClass;
	float adultFare;
	String source;
	String destination;
	int availableTickets;
	String departuteTime;
	String arrivalTime;
	String departureDate;
	String arrivalDate;
	String journeyTime;
	float distance;
	int totalSeats;
	
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public String getFlight_no() {
		return flight_no;
	}
	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
	}
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public float getAdultFare() {
		return adultFare;
	}
	public void setAdultFare(float adultFare) {
		this.adultFare = adultFare;
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
	public int getAvailableTickets() {
		return availableTickets;
	}
	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}
	public String getDepartuteTime() {
		return departuteTime;
	}
	public void setDepartuteTime(String departuteTime) {
		this.departuteTime = departuteTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
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
	public String getJourneyTime() {
		return journeyTime;
	}
	public void setJourneyTime(String journeyTime) {
		this.journeyTime = journeyTime;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
			
}
