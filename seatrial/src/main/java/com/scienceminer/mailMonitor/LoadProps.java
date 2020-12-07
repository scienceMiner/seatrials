package com.scienceminer.mailMonitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProps {
	
	private Properties configProp = new Properties();
	private String propFileName = "config.properties";
	
	public static void main(String[] args) {
		
		LoadProps sample = new LoadProps();
		try {
			sample.loadProps1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sample.getProperty("gmail.password");
		
	}

	public void loadProps1() throws IOException{
		
		// why getContextClassloader ??
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();		
		InputStream in = classloader.getResourceAsStream(propFileName);
			
		if (in != null) {
			configProp.load(in);		
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		
	}
	
	public String getProperty(String property) {
		return configProp.getProperty(property);
	}
	
	
}