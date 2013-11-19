package edu.sjsu.fly5.pojos;

public class TravelClass 
{
private String className;
private double fareFactor;
private String classType;
private double seatRatio;




public TravelClass() {
	super();
}

public TravelClass(String className, double fareFactor, String classType,double seatRatio) 
{

	this.className = className;
	this.fareFactor = fareFactor;
	this.classType = classType;
	this.seatRatio = seatRatio;
}

public String getClassName() {
	return className;
}

public void setClassName(String className) {
	this.className = className;
}

public double getFareFactor() {
	return fareFactor;
}

public void setFareFactor(double fareFactor) {
	this.fareFactor = fareFactor;
}

public String getClassType() {
	return classType;
}

public void setClassType(String classType) {
	this.classType = classType;
}

public double getSeatRatio() {
	return seatRatio;
}

public void setSeatRatio(double seatRatio) {
	this.seatRatio = seatRatio;
}





}
