package edu.sjsu.fly5.pojos;

import java.sql.Date;

public class Employee 
{

	private long employeeID;
	private String workDescription;
	private String designation;
	private String hireDate;
	private long crewID;
	private String personID;
	
	
	
	
	public Employee() {
		super();
	}



	
	
	
	

	public Employee(long employeeID, String workDescription,
			String designation, String hireDate, long crewID, String personID) {
		super();
		this.employeeID = employeeID;
		this.workDescription = workDescription;
		this.designation = designation;
		this.hireDate = hireDate;
		this.crewID = crewID;
		this.personID = personID;
	}








	public long getEmployeeID() {
		return employeeID;
	}






	public void setEmployeeID(long employeeID) {
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


	public String getHireDate() {
		return hireDate;
	}








	public void setHireDate(String hireDate) {
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
