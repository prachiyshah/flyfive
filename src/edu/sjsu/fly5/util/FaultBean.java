package edu.sjsu.fly5.util;

public class FaultBean {

	private String faultMessage;

	public String getFaultMessage() {
		return faultMessage;
	}

	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}
	
	public FaultBean(){
	}
	
	public FaultBean(String message) {
        faultMessage = message;
	}
	
	public FaultBean(String message, Object... args){
        if (args != null)
		  this.faultMessage = String.format(message, args);
	}
}
