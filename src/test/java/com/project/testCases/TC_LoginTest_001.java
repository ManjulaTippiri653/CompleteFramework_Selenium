package com.project.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.project.pageObjects.LoginPage;

import junit.framework.Assert;

public class TC_LoginTest_001 extends BaseClass{
	
	@Test
	public void Login() throws IOException {
		driver.get(baseURL);
		logger.info("URL is Open");
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName); // takes input from ReadConfig File
		lp.setPassword(password);
		lp.submit();
		
		if(driver.getTitle().equals(driver.getTitle())) {
			Assert.assertTrue(true);
			logger.info("Test Case Passed");
			
		}
		else {
			captureScreen(driver,"login test");
			Assert.assertTrue(false);
			logger.info("Test Case Failed");
		}
		logger.info("Login test passed");
		System.out.println(driver.getTitle());
		
	}

}
