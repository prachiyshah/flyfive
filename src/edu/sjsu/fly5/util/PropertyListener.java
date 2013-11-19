package edu.sjsu.fly5.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class PropertyListener implements ServletContextListener {

	private String appProp = "app.properties";
	
	public void contextDestroyed(ServletContextEvent contextEvent) {
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		ServletContext context = contextEvent.getServletContext();
		InputStream is = context.getResourceAsStream(appProp);
	    ApplicationProperties.load(is);
	    try {
			is.close();
		} catch (IOException e) {
			System.err.println("Listener initialization failed. Cannot publish webservice.");
		}
	}

}
