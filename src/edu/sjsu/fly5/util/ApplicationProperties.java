package edu.sjsu.fly5.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

	private static Properties properties;
	
	public static void load(InputStream input){
		properties = new Properties();
		try {
			properties.load(input);
		} catch (IOException e) {
			/*change exception type*/e.printStackTrace(System.out);
		}
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
}
