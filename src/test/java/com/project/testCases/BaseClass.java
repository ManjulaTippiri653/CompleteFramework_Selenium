package com.project.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.project.Utilities.ReadConfig;



public class BaseClass {
	ReadConfig rc = new ReadConfig();
	public String baseURL = rc.getApplicationURL();
	public String userName = rc.getUserName();
	public String password = rc.getPasswordL();
	public static WebDriver driver;
	public static Logger logger = LogManager.getLogger(BaseClass.class);
	
	

	@BeforeClass
	public void setUp() {
		System.setProperty("WebDriver.chrome.driver", System.getProperty("user.dir")+rc.getChromePath());
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	
	}
	

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	//take screenshot of the failure TC
	public void captureScreen(WebDriver driver, String tname) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) driver ;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(System.getProperty("user.dir")+"//Screenshots//"+tname+".png");
			FileUtils.copyFile(source,target);
			System.out.println("Screenshot Taken");
			
		}
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
	}
		
}
