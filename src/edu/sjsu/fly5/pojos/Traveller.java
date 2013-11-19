package edu.sjsu.fly5.pojos;

public class Traveller 
{

	private String travellerID;
	private String passportNumber;
	private String nationality;
	private String personID;
	
	
	
	
	
	
	public Traveller() {
		super();
	}
	public Traveller(String travellerID, String passportNumber,String nationality, String personID) 
	{
	
		this.travellerID = travellerID;
		this.passportNumber = passportNumber;
		this.nationality = nationality;
		this.personID = personID;
	}
	public String getTravellerID() {
		return travellerID;
	}
	public void setTravellerID(String travellerID) {
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
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	
	
	
	
	
}
