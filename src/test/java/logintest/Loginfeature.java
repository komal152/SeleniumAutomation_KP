package logintest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(logintest.TestNGListener.class)

public class Loginfeature {
	
	//Variable Declaration 
	static WebDriver driver;
    String Actual,Expected = "";
    long start, finish, timeDiff = 0;
    		
    @BeforeMethod
    @Parameters("browser")
		
		public void setup(String browser) throws Exception{
		
		if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else {
			throw new Exception("Incorrect Browser");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
		
    //Login Test - User Logged in Successfully
	@Test (priority = 0)
	public void LoginSuccessStdUser()throws Exception{
		driver.get("https://www.saucedemo.com");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
		Loginfeature.takeSnapShot(driver,System.getProperty("user.dir")+"//screenshot//LoginSuccess_"+TestNGListener.dateTime()+".png") ;	
		Actual = driver.findElement(By.xpath("//span[@class='title']")).getText();
		Expected = "PRODUCTS";
		Assert.assertEquals(Expected, Actual);
		System.out.println(Actual + ":- Logged in Successfully");
	}
	
	//Login Test - Locked out user verification - error message verification
	@Test (priority = 1)
	public void loginLockedOutUser()throws Exception{
		driver.get("https://www.saucedemo.com");
		driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		//Verify the following Error message as user has been locked out
		//Sorry, this user has been locked out.
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Sorry, this user has been locked out.')]")));
		Loginfeature.takeSnapShot(driver,System.getProperty("user.dir")+"//screenshot//loginLockedOutUser_"+TestNGListener.dateTime()+".png") ;	
		Actual = driver.findElement(By.xpath("//*[contains(text(),'Sorry, this user has been locked out.')]")).getText();
	    Expected = "Epic sadface: Sorry, this user has been locked out.";
		Assert.assertEquals(Expected, Actual);
		System.out.println(Actual + ":- Getting an error message of user has been locked out");	
	}
	
	//Login Test - PageLoad Performance Verification - Test/Assertion will fail and throw an exception
	@Test (priority = 2)
	public void loginPerformanceGlitch()throws Exception{
		//start = System.currentTimeMillis();
		driver.get("https://www.saucedemo.com");
		//finish = System.currentTimeMillis();
		//timeDiff = finish - start;
		//System.out.println("Time diff finish - start "+timeDiff);
		driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		System.out.println("Verifying the performance of Login feature with user name: performance_glitch_user - Performance Threshold is 50 milliseconds");
		System.out.println("Assertion condition will fail and throw an exception or error with informational message");
		//Verify the performance issue in user login - Performance threshold is 50 milliseconds.
		start = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(driver,2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
		finish = System.currentTimeMillis();
		timeDiff = finish - start;
		System.out.println("Page Loading Time Difference:- "+timeDiff);
		Assert.assertTrue(timeDiff<50,"Performance Issue - Page Load Time should be less than 50 milliseconds");
		Loginfeature.takeSnapShot(driver,System.getProperty("user.dir")+"//screenshot//LoginSuccess_"+TestNGListener.dateTime()+".png") ;	
		Actual = driver.findElement(By.xpath("//span[@class='title']")).getText();
		Expected = "PRODUCTS";
		Assert.assertEquals(Expected, Actual);
		System.out.println(Actual + ":- Logged in Successfully");
	}
	
	//Login Test - Incorrect Password - Error Message verification
	@Test (priority = 3)
	public void LoginIncorrectPassword()throws Exception{
		driver.get("https://www.saucedemo.com");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("TestAbc");
		driver.findElement(By.id("login-button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Username and password do not match any user in this service')]")));
		Loginfeature.takeSnapShot(driver,System.getProperty("user.dir")+"//screenshot//LoginIncorrectPassword_"+TestNGListener.dateTime()+".png") ;	
		Actual = driver.findElement(By.xpath("//*[contains(text(),'Username and password do not match any user in this service')]")).getText();
		Expected = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(Expected, Actual);
		System.out.println(Actual + ":- Getting an error message for incorrect password");
	}
	
	//Login Test - Mandatory field verification
	@Test (priority = 4)
	public void LoginMandatoryField()throws Exception{
		driver.get("https://www.saucedemo.com");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		//Missing Password
		//driver.findElement(By.id("password")).sendKeys("TestAbc");
		driver.findElement(By.id("login-button")).click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'is required')]")));
		Loginfeature.takeSnapShot(driver,System.getProperty("user.dir")+"//screenshot//LoginMandatoryField_"+TestNGListener.dateTime()+".png") ;	
		Actual = driver.findElement(By.xpath("//*[contains(text(),'is required')]")).getText();
		Expected = "Epic sadface: Password is required";
		Assert.assertEquals(Expected, Actual);
		System.out.println(Actual + ":- Getting an error message for Mandatory Field - for example Password is required");
	}
	
	// Forcefully failed this test as to verify listener.		
	@Test (priority = 11)	
	public void TestToFail()				
	{		
	    System.out.println("This method to test fail");
	    Assert.assertTrue(false);			
	}
	
	@AfterMethod
	public void closeBrowser(){
		driver.close();
	}
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		Reporter.log("<a href='"+ DestFile.getAbsolutePath() + "'> <img src='"+ DestFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		}
}


