package edu.sjsu.fly5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		driver ="com.mysql.jdbc.Driver";
		connectionUrl = "jdbc:mysql://localhost:3306/fly5";
		user = "root";
		password = "admin";
	}

	public Connection getConnection() throws Fly5Exception{
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fly5","root","admin");
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

