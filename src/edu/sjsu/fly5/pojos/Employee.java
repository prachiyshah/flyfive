package edu.sjsu.fly5.pojos;

import java.util.Date;

public class Employee 
{

	private String employeeID;
	private String workDescription;
	private String designation;
	private Date hireDate;
	private long crewID;
	private String personID;
	
	
	
	
	public Employee() {
		super();
	}



	public Employee(String employeeID, String workDescription,String designation, Date hireDate, long crewID, String personID) 
	{
		super();
		this.employeeID = employeeID;
		this.workDescription = workDescription;
		this.designation = designation;
		this.hireDate = hireDate;
		this.crewID = crewID;
		this.personID = personID;
	}



	public String getEmployeeID() {
		return employeeID;
	}


	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}


	public String getWorkDescription() {
		return workDescription;
	}


	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public Date getHireDate() {
		return hireDate;
	}


	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}


	public long getCrewID() {
		return crewID;
	}


	public void setCrewID(long crewID) {
		this.crewID = crewID;
	}



	public String getPersonID() {
		return personID;
	}



	public void setPersonID(String personID) {
		this.personID = personID;
	}
	
	
	
	
	
	
}
