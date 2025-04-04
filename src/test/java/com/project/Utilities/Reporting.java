package com.project.Utilities;
//Listener Class used to generate Extent Reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentSparkReporter sparkReporter; //UI of the report
	public ExtentReports report; //Populate common info on the report
	public ExtentTest test;  //creating test case entries in the report and update status of the test methods
	
	//Executes before start of the execution
	public void onStart(ITestContext testContext) {
		
		//UI configuration of the report
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time Stamp
		String reportName = "Test-Report-" + timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/report/"+reportName);
		
		sparkReporter.config().setReportName("Automation");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("E Banking");
		
		//Info on the report
		report = new ExtentReports();
		report.attachReporter(sparkReporter); // attaching UI to the info
		
		report.setSystemInfo("Computer Name","local host");
		report.setSystemInfo("Environment","QA");
		report.setSystemInfo("Tester Name","ShinChan");
		report.setSystemInfo("Browser Name","Chrome");
		
		
	  }
	
	//This method will trigger whenever as TC is passed
	public void onTestSuccess(ITestResult tr) {
	   //tr (test result captures info of the result
		test = report.createTest(tr.getName()); // create new entry in the report
		test.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Update the status of the Tc - Passed/failed/skipped
		
	  }
	
	public void onTestFailure(ITestResult tr) {
		   //tr (test result captures info of the result
			test = report.createTest(tr.getName()); // create new entry in the report
			test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // Update the status of the Tc - Passed/failed/skipped
			//test.log(Status.FAIL,"TC failed due to"+tr.getThrowable());
			String screenshotPath = System.getProperty("user.dir")+"//Screenshots//"+tr.getName()+".png";
			File f = new File(screenshotPath);
			if(f.exists()) {
				try {
					test.fail("Screenshot below:" + test.addScreenCaptureFromPath(screenshotPath));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		  }
	
	public void onTestSkipped(ITestResult tr) {
		   //tr (test result captures info of the result
			test = report.createTest(tr.getName()); // create new entry in the report
			test.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE)); // Update the status of the Tc - Passed/failed/skipped
			
		  }

	public void onFinish(ITestContext testcontext) {
		report.flush();
	}
	

}
