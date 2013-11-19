package edu.sjsu.fly5.pojos;

import java.util.Date;

public class Person 
{
	private String personID;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private long zipcode;
	private Date dateOfBirth;
	
	
	
	
	
	public Person(String personID, String firstName, String lastName,String address, String city, String state, long zipcode,Date dateOfBirth)
	{
	
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.dateOfBirth = dateOfBirth;
	}





	public String getPersonID() {
		return personID;
	}





	public void setPersonID(String personID) {
		this.personID = personID;
	}





	public String getFirstName() {
		return firstName;
	}





	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}





	public String getLastName() {
		return lastName;
	}





	public void setLastName(String lastName) {
		this.lastName = lastName;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getCity() {
		return city;
	}





	public void setCity(String city) {
		this.city = city;
	}





	public String getState() {
		return state;
	}





	public void setState(String state) {
		this.state = state;
	}





	public long getZipcode() {
		return zipcode;
	}





	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}





	public Date getDateOfBirth() {
		return dateOfBirth;
	}





	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	
	
	
	

}
