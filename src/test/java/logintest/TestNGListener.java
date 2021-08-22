package logintest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener{	
	 // When Test case get failed, this method is called.
    public void onTestFailure(ITestResult Result) 					
    {
    System.out.println("Failed Testcase Name :"+Result.getName());
   // System.out.println(Loginfeature.driver);
    try {
		Loginfeature.takeSnapShot(Loginfeature.driver,System.getProperty("user.dir")+"//screenshot//failure_"+dateTime()+".png");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    public static String dateTime() {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
    		LocalDateTime now = LocalDateTime.now(); 
    		String  dateTime= dtf.format(now);
    	   //System.out.println(dateTime);
		return dateTime; 
    	   
    }
}