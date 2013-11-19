package edu.sjsu.fly5.pojos;

public class PaymentDetails 
{
	private String travellerID;
	private long creditCardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvvNumber;
	private String paymentType;

	
	
	
	public PaymentDetails() {
		super();
	}


	public PaymentDetails(String travellerID, long creditCardNumber,int expiryMonth, int expiryYear, int cvvNumber, String paymentType) 
	{
		
		this.travellerID = travellerID;
		this.creditCardNumber = creditCardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvvNumber = cvvNumber;
		this.paymentType = paymentType;
	}


	public String getTravellerID() {
		return travellerID;
	}


	public void setTravellerID(String travellerID) {
		this.travellerID = travellerID;
	}


	public long getCreditCardNumber() {
		return creditCardNumber;
	}


	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


	public int getExpiryMonth() {
		return expiryMonth;
	}


	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}


	public int getExpiryYear() {
		return expiryYear;
	}


	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}


	public int getCvvNumber() {
		return cvvNumber;
	}


	public void setCvvNumber(int cvvNumber) {
		this.cvvNumber = cvvNumber;
	}


	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	
	
	
}
