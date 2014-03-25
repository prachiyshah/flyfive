package edu.sjsu.fly5.services;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import edu.sjsu.fly5.dao.EmployeeDao;
import edu.sjsu.fly5.pojos.Attribute;
import edu.sjsu.fly5.pojos.Employee;
import edu.sjsu.fly5.util.Fly5Exception;
import edu.sjsu.fly5.util.Messages;


@WebService
public class EmployeeService implements Messages
{
	private EmployeeDao employeeDao;
	
	public EmployeeService()
	{
		this.employeeDao=new EmployeeDao();
	}
	
	public boolean addEmployee(Employee employee) throws Fly5Exception
	{
		boolean flag=false;
		flag=employeeDao.addEmployee(employee);
		return flag;

	}
	public boolean updateEmployee(Employee employee) throws Fly5Exception, ParseException
	{
		boolean flag=false;
		flag=employeeDao.updateEmployee(employee);
		return flag;
	}
	public boolean removeEmployee(long employeeID) throws Fly5Exception
	{
		boolean flag=false;
		flag=employeeDao.removeEmployee(employeeID);
		return flag;
	}
	public Employee[] listEmployees() throws Fly5Exception, ParseException
	{
		List<Employee> listOfEmployees=new ArrayList<Employee>();
		
		listOfEmployees=employeeDao.listOfEmployees();
		
		return listOfEmployees.toArray(new Employee[listOfEmployees.size()]);

	}
		
	
	public Employee[] searchEmployeesBasedOnAttributes(Attribute[] attributes) throws Fly5Exception
	{
		List<Employee> listOfEmployees=new ArrayList<Employee>();
		
		listOfEmployees=employeeDao.searchEmployeesBasedOnAttributes(attributes);
		
		return listOfEmployees.toArray(new Employee[listOfEmployees.size()]);
	}
	public Employee viewEmployeeInfo(long employeeID) throws Fly5Exception
	{
		Employee employee=null;
		employee=employeeDao.viewEmployeeInfo(employeeID);
		return employee;

	}

}
