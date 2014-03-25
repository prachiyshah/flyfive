package edu.sjsu.fly5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.sjsu.fly5.util.ApplicationProperties;

public class ConnectionFactory {

	private String connectionUrl; 
	private String user; 
	private String password;
	private String driver; 

	private static ConnectionFactory factory = null;

	public static ConnectionFactory getInstance() {
		if (factory == null)
			factory = new ConnectionFactory();
		return factory;
	}

	private ConnectionFactory(){
		init();
		try{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e){
			e.printStackTrace(System.out);
		}
	}

	private void init(){
		driver = ApplicationProperties.getProperty("driver");
		connectionUrl = ApplicationProperties.getProperty("url");
		user = ApplicationProperties.getProperty("user");
		password = ApplicationProperties.getProperty("password");
	}
	
	private void init1(){
		driver = "com.mysql.jdbc.Driver";
		connectionUrl = "jdbc:mysql://localhost:3306/fly5";
		user = "root";
		password = "root";
	}

	public Connection getConnection() throws Fly5Exception{
		Connection con = null;
		try {
			con = DriverManager.getConnection(connectionUrl,user,password);
		} catch (SQLException e) {
			throw new Fly5Exception(null, e);
		}
		return con;
	}

	public void closeConnection(Connection con) throws Fly5Exception{
		if (con == null)
			return;
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new Fly5Exception(null, e);
		}
	}
}

