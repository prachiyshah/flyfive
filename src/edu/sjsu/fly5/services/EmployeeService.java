package edu.sjsu.fly5.services;
import javax.jws.WebService;

import edu.sjsu.fly5.pojos.Attribute;
import edu.sjsu.fly5.pojos.Employee;


@WebService
public class EmployeeService 
{

	public boolean addEmployee(Employee employee)
	{
		boolean flag=false;



		return flag;

	}
	public boolean updateEmployee(Employee employee)
	{
		boolean flag=false;



		return flag;
	}
	public boolean removeEmployee(long employeeID)
	{
		boolean flag=false;



		return flag;
	}
	public Employee[] listEmployees()
	{
		Employee[] listOfEmployees=null;


		return listOfEmployees;

	}
	public Employee searchEmployeesBasedOnAttributes(Attribute[] attributes)
	{
		Employee employee=null;


		return employee;
	}
	public Employee viewEmployeeInfo(long employeeID)
	{
		Employee employee=null;

		return employee;

	}

}
