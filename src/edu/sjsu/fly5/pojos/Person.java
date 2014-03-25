package edu.sjsu.fly5.pojos;

import java.util.Date;

public class Person 
{
	private long personID;   
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private long zipcode;
	private Date dateOfBirth;
	private String emailAddress;
	private String password;
	
	
	public Person(long personID, String firstName, String lastName,
			String address, String city, String state, long zipcode,
			Date dateOfBirth, String emailAddress, String password) {
		super();
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.dateOfBirth = dateOfBirth;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public Person() {
		// TODO Auto-generated constructor stub
		super();
	}

	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
