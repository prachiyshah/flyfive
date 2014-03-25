package edu.sjsu.fly5.pojos;

public class Traveller extends Person
{

	private long travellerID;     //Bhavi
	private String passportNumber;
	private String nationality;
	private String travellerClass;
	private String travellerType;
	
	public Traveller() {
		super();
	}
	public Traveller(long travellerID, String passportNumber,String nationality) 
	{
	
		this.travellerID = travellerID;
		this.passportNumber = passportNumber;
		this.nationality = nationality;
	}
	public long getTravellerID() {
		return travellerID;
	}
	public void setTravellerID(long travellerID) {
		this.travellerID = travellerID;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getTravellerClass() {
		return travellerClass;
	}
	public void setTravellerClass(String travellerClass) {
		this.travellerClass = travellerClass;
	}
	public String getTravellerType() {
		return travellerType;
	}
	public void setTravellerType(String travellerType) {
		this.travellerType = travellerType;
	}
	
}
