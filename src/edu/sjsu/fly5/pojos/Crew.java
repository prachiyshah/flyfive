package edu.sjsu.fly5.pojos;

public class Crew 
{
private long crewID;
private String crewName;



public Crew() {
	super();
}


public Crew(long crewID, String crewName) {

	this.crewID = crewID;
	this.crewName = crewName;
}


public long getCrewID() {
	return crewID;
}


public void setCrewID(long crewID) {
	this.crewID = crewID;
}


public String getCrewName() {
	return crewName;
}


public void setCrewName(String crewName) {
	this.crewName = crewName;
}




}
