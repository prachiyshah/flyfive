package edu.sjsu.fly5.util;

import javax.xml.ws.WebFault;

@WebFault(name="Fly5Fault")
public class Fly5Exception extends Exception {
	
	private static final long serialVersionUID = -4596248053832564642L;
	
	private FaultBean faultBean;
	
	public Fly5Exception(FaultBean faultBean, Throwable e){
		super(e);
		this.faultBean = faultBean;
	}
	
	public Fly5Exception(FaultBean faultBean){
		this.faultBean = faultBean;
	}

	public FaultBean getFaultBean() {
		return faultBean;
	}
	
}
