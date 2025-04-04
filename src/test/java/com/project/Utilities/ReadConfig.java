package com.project.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties prop;

	public ReadConfig() {
		File src = new File("./Configuration_Files/config.properties");
		//Import the data
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
		
	}
	public String getUserName() {
		String uname = prop.getProperty("userName");
		return uname;
		
	}
	public String getPasswordL() {
		String pswd = prop.getProperty("password");
		return pswd;
		
	}
	public String getChromePath() {
		String path = prop.getProperty("chromePath");
		return path;
		
	}
	

}
