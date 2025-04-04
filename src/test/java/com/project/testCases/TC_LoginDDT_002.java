package com.project.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.Utilities.XLUtils;
import com.project.pageObjects.LoginPage;

import junit.framework.Assert;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test (dataProvider="LoginData")
	public void loginDDT(String username,String psswd) throws InterruptedException{
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username); // takes input from excel sheet
		logger.info("Entered Username");
		lp.setPassword(psswd);
		logger.info("Entered password");
		lp.submit();
		Thread.sleep(5000);
		logger.info("Login successful");
		
		if(isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Invalid Credentials");
		}
		else {
			Assert.assertTrue(true);
			logger.info("login Successful");
			scrollDown();
			lp.logout();
		}
			
	}
	
	public boolean isAlertPresent() { //User defined method 
		
		try {
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e) {
			return false;
		}
	}
	@DataProvider (name = "LoginData")
	String [][] getData() throws IOException{
	String path = System.getProperty("user.dir")+"/src/test/java/com/project/TestData/LoginCredentials.xlsx";
	int noOfRows = XLUtils.getRowCount(path,"Sheet1");
	int noOfCol = XLUtils.getCellCount(path,"Sheet1", 1);
	String logindata[][] = new String[noOfRows][noOfCol];
	
	for (int i= 1; i<= noOfRows; i++) {
		
		for(int j= 0; j< noOfCol; j++) {
			
			logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			
		}
	}
	
	return logindata;
	}
}
