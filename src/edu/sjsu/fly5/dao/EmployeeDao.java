package edu.sjsu.fly5.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import edu.sjsu.fly5.pojos.Employee;
import edu.sjsu.fly5.util.ConnectionFactory;
import edu.sjsu.fly5.util.FaultBean;
import edu.sjsu.fly5.util.Fly5Exception;
import edu.sjsu.fly5.util.Messages;


public class EmployeeDao implements Messages 
{
	public boolean addEmployee(Employee employee) throws Fly5Exception
	{
		boolean flag=false;
		String query="insert into Employee(work_description,position,hire_date,crew_id) values(?,?,?,?)";
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			java.util.Date myDate = new Date();
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());


			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,employee.getWorkDescription());
			ps.setString(2,employee.getDesignation());
			ps.setDate(3,sqlDate);
			ps.setLong(4, employee.getCrewID());
			
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
	
	public List<Employee> listEmployees() throws Fly5Exception, ParseException
	{
		List<Employee> listOfEmployees=new ArrayList<Employee>();
		String query="select * from Employee";
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			Employee employee=new Employee();
			employee.setEmployeeID(rs.getLong(1));
			employee.setWorkDescription(rs.getString(2));
			employee.setDesignation(rs.getString(3));
			employee.setHireDate(rs.getDate(4).toString());
			employee.setCrewID(rs.getLong(5));
			listOfEmployees.add(employee);
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
		return listOfEmployees;
	}
	
	public Employee viewEmployeeInfo(long employeeID) throws Fly5Exception
	{
		Employee employee=null;
		String query="select * from Employee where employee_id=?";
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(query);
			ps.setLong(1, employeeID);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				employee=new Employee();
				employee.setEmployeeID(rs.getLong(1));
				employee.setWorkDescription(rs.getString(2));
				employee.setDesignation(rs.getString(3));
				employee.setHireDate(rs.getDate(4).toString());
				employee.setCrewID(rs.getLong(5));
				
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
	
	public boolean updateEmployee(Employee employee) throws Fly5Exception, ParseException
	{
		boolean flag=false;
		String query="update Employee set work_description = ?, position =? ,hire_date = ?, crew_id = ? where employee_id = ?";
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date parsed = format.parse(employee.getHireDate());
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());

			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,employee.getWorkDescription());
			ps.setString(2,employee.getDesignation());
			ps.setDate(3,sql);
			ps.setLong(4, employee.getCrewID());
			ps.setLong(5, employee.getEmployeeID());
			
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
	
	public boolean removeEmployee(long employeeID) throws Fly5Exception
	{
		boolean flag=false;
		String query="delete from Employee where employee_id = ?";
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			con=ConnectionFactory.getInstance().getConnection();
			ps=con.prepareStatement(query);
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
	
	
	
}
