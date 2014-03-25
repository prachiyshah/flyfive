package edu.sjsu.fly5.pojos;

public class FlightSearchAttributes {
	
	private String source;
	private String destination;
	private String departureDate;
	private String travelClass;
	private int adults;
	private int children;
	private int infants;
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
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getTravelClass() {
		return travelClass;
	}
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public int getInfants() {
		return infants;
	}
	public void setInfants(int infants) {
		this.infants = infants;
	}
}
