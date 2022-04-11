package org.Test.com;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Demo.DemoLog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Reports {
	
	public static ExtentHtmlReporter obj1=new ExtentHtmlReporter("./Reports/extentreport1.html");
	public static ExtentReports obj2=new ExtentReports();
	public static WebDriver driver;
	
	@BeforeClass
	public static void setDriver()
	{
		DemoLog.log(2);
		obj2.attachReporter(obj1);
		ExtentTest obj3=obj2.createTest("Opening the Test");
		obj3.log(Status.INFO,"Opening the browser");
		try
		{
			DemoLog.log(1);
		WebDriverManager.chromedriver().setup();
	      driver = new ChromeDriver();
	      obj3.log(Status.PASS,"The browser open");
		}
		catch(Exception e)
		{
			DemoLog.log(3);
			System.out.println("Before Report block");
			obj3.log(Status.FAIL,"The browser does not opened");
		}
		obj2.flush();
	}
	@Test 
	public static void search()
	{
		DemoLog.log(2);
		obj2.attachReporter(obj1);
		ExtentTest obj4=obj2.createTest("Opening the Application and Searching data");
		obj4.log(Status.INFO,"Opening the Google site");
		try
		{
			DemoLog.log(1);
		 driver.get("https://www.google.com/");
		 obj4.log(Status.PASS,"The application opened in the browser");
		 WebElement search=driver.findElement(By.name("q"));
		 search.sendKeys("java");
		 search.sendKeys(Keys.ENTER);
		 try {
			 Assert.assertEquals(driver.getTitle(), "java - Google Search");
		 }
		 catch(AssertionError e) {
			 DemoLog.log(4);
		 }
		 Screenshots.screenShot(1);
		 Thread.sleep(3000);
		 obj4.log(Status.PASS,"The application navigated to the next page");
		 String filepath=System.getProperty("user.dir")+"//ScreenShots//Screenshot1.png";
		 obj4.addScreenCaptureFromPath(filepath);
		}
		catch(Exception e)
		{
			DemoLog.log(5);
			System.out.println("Reports");
			obj4.log(Status.FAIL,"The applcation failed");
		}
		obj2.flush();
		 
	}
	@AfterClass
	public static void closeWindow()
	{
		DemoLog.log(1);
		obj2.attachReporter(obj1);
		ExtentTest obj5=obj2.createTest("Closing the browser");
		obj5.log(Status.INFO,"Closing the Application");
		try
		{
			DemoLog.log(1);
		driver.close();
		obj5.log(Status.PASS,"The application closed");
		}
		catch(Exception e)
		{
			DemoLog.log(3);
			System.out.println("After Reports block");
			obj5.log(Status.FAIL,"The applcation doesn't closed ");
		}
		obj2.flush();
	}
}
