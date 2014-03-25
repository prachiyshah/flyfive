package edu.sjsu.fly5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import edu.sjsu.fly5.pojos.Attribute;
import edu.sjsu.fly5.pojos.Employee;
import edu.sjsu.fly5.util.ConnectionFactory;
import edu.sjsu.fly5.util.FaultBean;
import edu.sjsu.fly5.util.Fly5Constants;
import edu.sjsu.fly5.util.Fly5Exception;
import edu.sjsu.fly5.util.Messages;


public class EmployeeDao implements Messages 
{
	    //////*****************************This Method Adds the Employee Details. ***********************///////////////////////
	public boolean addEmployee(Employee employee) throws Fly5Exception
	{
		boolean personFlag=false;
		boolean employeeFlag=false;
		boolean flag=false;
		
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		long personID=0L;
		
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			
		
			con.setAutoCommit(false);
			
			ps=con.prepareStatement(Fly5Constants.ADD_PERSON_QUERY);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3,employee.getAddress());
			ps.setString(4, employee.getCity());
			ps.setString(5, employee.getState());
			ps.setLong(6, employee.getZipcode());
			
			
			java.util.Date utilDateOfBirth=employee.getDateOfBirth();
			java.sql.Date sqlDateOfBirth=new java.sql.Date(utilDateOfBirth.getTime());
			
			ps.setDate(7, sqlDateOfBirth);
			ps.setString(8, employee.getEmailAddress());
			int personCount=ps.executeUpdate();
			
			if(personCount>0)
			{
				personFlag=true;
			}
			
			ps1=con.prepareStatement(Fly5Constants.MAX_ID_FROM_PERSON_QUERY);
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				personID=rs.getLong(Fly5Constants.PERSON_ID_INDEX);
			}
			
			ps2=con.prepareStatement(Fly5Constants.ADD_EMPLOYEE_QUERY);
			ps2.setLong(1, personID);
			ps2.setString(2,employee.getWorkDescription());
			ps2.setString(3,employee.getDesignation());
			
			java.util.Date utilHireDate =employee.getHireDate();
			java.sql.Date sqlHireDate = new java.sql.Date(utilHireDate.getTime());
			
			ps2.setDate(4,sqlHireDate);
			ps2.setLong(5,employee.getCrewID());
			
			int employeeCount=ps2.executeUpdate();
			
			if(employeeCount>0)
			{
				employeeFlag=true;
			}
			
			if(personFlag==true && employeeFlag==true)
			{
				flag=true;
			}
			con.commit();
		

		
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
			
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return flag;
		
	}
	
	
		//////*****************************ADD EMPLOYEE METHOD ENDS ***********************///////////////////////
	
	   //////*****************************This Method Returns List OF EMPLOYEES. ***********************///////////////////////
	
	public List<Employee> listOfEmployees() throws Fly5Exception
	{
		List<Employee> listOfEmployees=new ArrayList<Employee>();
		Connection con=null;
		PreparedStatement ps=null;
		Employee employee=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(Fly5Constants.SELECT_ALL_EMPLOYEE_QUERY);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				employee = new Employee();
				employee=getEmployeePersonalDetails(rs.getLong(1));
				employee.setEmployeeID(rs.getInt(Fly5Constants.EMPLOYEE_ID_INDEX));
				employee.setWorkDescription(rs.getString(Fly5Constants.WORK_DESCRIPTION_INDEX));
				employee.setDesignation(rs.getString(Fly5Constants.POSITION_INDEX));
				employee.setHireDate(rs.getDate(Fly5Constants.HIRE_DATE_INDEX));
				employee.setCrewID(rs.getLong(Fly5Constants.CREW_ID_INDEX));
				listOfEmployees.add(employee);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
			
		}
		return listOfEmployees;
	}
	///////////////////////**************** LIST OF EMPLOYEES METHOD ENDS *************************////////////////////////
	
	//////////////////////***************** This Method Returns Extra Information About Employee From Person Table.************///////////////
	public Employee getEmployeePersonalDetails(long id) throws Fly5Exception
	{
		Employee employee=null;
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(Fly5Constants.SELECT_PERSON_BY_ID_QUERY);
			ps.setLong(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				employee=new Employee();
				employee.setFirstName(rs.getString(Fly5Constants.PERSON_FIRST_NAME_INDEX));
				employee.setLastName(rs.getString(Fly5Constants.PERSON_LAST_NAME_INDEX));
				employee.setAddress(rs.getString(Fly5Constants.PERSON_STREET_INDEX));
				employee.setCity(rs.getString(Fly5Constants.PERSON_CITY_INDEX));
				employee.setState(rs.getString(Fly5Constants.PERSON_STATE_INDEX));
				employee.setZipcode(rs.getLong(Fly5Constants.PERSON_ZIPCODE_INDEX));
				employee.setDateOfBirth(rs.getDate(Fly5Constants.PERSON_DATE_OF_BIRTH_INDEX));
				employee.setEmailAddress(rs.getString(Fly5Constants.PERSON_EMAIL_ADDRESS_INDEX));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return employee;
	}
	
	/////////////////////////**************** EMPLOYEE OTHER INFORMATION FROM PERSON TABLE ENDS. ***************/////////////////////////
	
	//////////////////////// **************** This Method Returns Particular Information About an EMPLOYEE.******************///////////////////// 
	public Employee viewEmployeeInfo(long employeeID) throws Fly5Exception
	{
		Employee employee=null;
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(Fly5Constants.SELECT_EMPLOYEE_BY_ID_QUERY);
			ps.setLong(1, employeeID);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				employee=new Employee();
				employee=getEmployeePersonalDetails(employeeID);
				employee.setEmployeeID(rs.getLong(Fly5Constants.EMPLOYEE_ID_INDEX));
				employee.setWorkDescription(rs.getString(Fly5Constants.WORK_DESCRIPTION_INDEX));
				employee.setDesignation(rs.getString(Fly5Constants.POSITION_INDEX));
				employee.setHireDate(rs.getDate(Fly5Constants.HIRE_DATE_INDEX));
				employee.setCrewID(rs.getLong(Fly5Constants.CREW_ID_INDEX));
				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
			
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return employee;
	}
	///////////////////////////************* View Employee Information By ID Ends. *********************////////////////////////////////
	///////////////////////////*************** This Method Updates the Employee Information ****************////////////////////////////
	public boolean updateEmployee(Employee employee) throws Fly5Exception, ParseException
	{
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		try
		{
			
			Date employeeHireDate= employee.getHireDate();
			java.sql.Date hireDate = new java.sql.Date(employeeHireDate.getTime());
			con=ConnectionFactory.getInstance().getConnection();
			
			ps=con.prepareStatement(Fly5Constants.UPDATE_EMPLOYEE_BY_ID_QUERY);
			ps.setString(1,employee.getWorkDescription());
			ps.setString(2,employee.getDesignation());
			ps.setDate(3,hireDate);
			ps.setLong(4, employee.getCrewID());
			ps.setLong(5, employee.getEmployeeID());
			
			int i=ps.executeUpdate();
			System.out.println("value of first query"+i);
			
			
			ps1=con.prepareStatement(Fly5Constants.UPDATE_PERSON_BY_ID_QUERY);
			ps1.setString(1, employee.getFirstName());
			ps1.setString(2, employee.getLastName());
			ps1.setString(3, employee.getEmailAddress());
			ps1.setString(4, employee.getAddress());
			ps1.setString(5, employee.getCity());
			ps1.setString(6, employee.getState());
			ps1.setLong(7, employee.getZipcode());
			ps1.setLong(8, employee.getEmployeeID());
			
			int j=ps1.executeUpdate();
			
			System.out.println("Value of second query"+j);
			
			if(i>0 && j>0)
			{
				flag=true;
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
			
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return flag;
	}
	///////////////////////////////***************  UPDATE EMPLOYEE INFORMATION METHOD ENDS. *****************////////////////////////////////
	
	//////////////////////////////**************** This Method Deletes an Employee Information *****************//////////////////////////////
	
	public boolean removeEmployee(long employeeID) throws Fly5Exception
	{
		boolean flag=false;
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(Fly5Constants.DELETE_EMPLOYEE_BY_ID_QUERY);
			ps.setLong(1, employeeID);
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				flag=true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new Fly5Exception(new FaultBean(e.getMessage()));
			
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		return flag;
	}
	
	//////////////////////////////////////**************** DELETE EMPLOYEE METHOD ENDS. *********************//////////////////////////////////
	
	
	////////////////////////////////////*************** THIS METHOD SEARCH AN EMPLOYEE INFORMATION BASED ON SEARCH FIELDS *****************/////////////////////
	public List<Employee> searchEmployeesBasedOnAttributes(Attribute[] attributes) throws Fly5Exception
	{
		Employee employee=null;
		String query="";
		StringBuilder sb=new StringBuilder("select * from Employee where ");
		List<Employee> listOfEmployees=new ArrayList<Employee>();
		for(int i=0;i<attributes.length;i++)
		{
			
			sb.append(attributes[i].getAttributeName());
			sb.append("=");
			sb.append("?");
			
			if(i<attributes.length-1)
			{
				sb.append(" and ");
			}
			
		}
		sb.append(";");
		
		query = sb.toString();
		System.out.println(query);
		Connection con=null;
		PreparedStatement ps=null;
		
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(query);
			
			
			
			for(int i = 0; i<attributes.length;i++)
			{
				if(attributes[i].getAttributeName().equals("employee_id"))
				{
					ps.setInt(i+1, new Integer(attributes[i].getAttributeValue()).intValue());
					System.out.println("The employee id is"+attributes[i].getAttributeValue());
					
					
				}
				 if(attributes[i].getAttributeName().equals("position"))
				{
					ps.setString(i+1, attributes[i].getAttributeValue());
					System.out.println("The position is"+attributes[i].getAttributeValue());
				}
				 if(attributes[i].getAttributeName().equals("hire_date"))
				{
					ps.setString(i+1, attributes[i].getAttributeValue());
					System.out.println("The Hire Date is"+attributes[i].getAttributeValue());
				}
				 if(attributes[i].getAttributeName().equals("crew_id"))
				{
					ps.setInt(i+1, new Integer(attributes[i].getAttributeValue()).intValue());
					System.out.println("The crew id is"+attributes[i].getAttributeValue());
				}
				
				
			}
			
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				employee=new Employee();
				employee=getEmployeePersonalDetails(rs.getLong(1));
				employee.setEmployeeID(rs.getLong(Fly5Constants.EMPLOYEE_ID_INDEX));
				employee.setWorkDescription(rs.getString(Fly5Constants.WORK_DESCRIPTION_INDEX));
				employee.setDesignation(rs.getString(Fly5Constants.POSITION_INDEX));
				employee.setHireDate(rs.getDate(Fly5Constants.HIRE_DATE_INDEX));
				employee.setCrewID(rs.getLong(Fly5Constants.CREW_ID_INDEX));
				
				listOfEmployees.add(employee);
			}
			
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ConnectionFactory.getInstance().closeConnection(con);
		}
		
		
		return listOfEmployees;
	}
	
	/////////////////////////////////////////***************** EMPLOYEE INFORMATION BASED ON SEARCH PARAMETER ENDS. ************************/////////////////////////////
	
}
