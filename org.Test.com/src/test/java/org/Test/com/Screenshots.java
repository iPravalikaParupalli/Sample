package org.Test.com;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshots extends Reports{

	public static void screenShot(int i) throws InterruptedException {
		try {
			//Convert webdriver object to take Screenshot
			TakesScreenshot scrShot=((TakesScreenshot)driver);
			
			//Calling thegetScreenshotAs() method to create an image file
			File Src=scrShot.getScreenshotAs(OutputType.FILE);//function to capture particular file
			//Giving the location
			String filePath=System.getProperty("user.dir")+"//ScreenShots//Screenshot"+i+".png";
			//Moving image fle to new Destination
			File Dest=new File(filePath);
			
			//Copying the file destination
			FileUtils.copyFile(Src, Dest);
		}
		catch(Exception e)
		{
			System.out.println("Screen Capture");
		}
			

}

}
