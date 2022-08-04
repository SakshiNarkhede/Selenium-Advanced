package lambdatest;

//import java.lang.reflect.Method;
//import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

import org.openqa.selenium.*;

public class LambdaTest {

 
    public RemoteWebDriver driver = null;
@Test
public void testScenarioOne() throws Exception {
		
	            WebDriverWait wait = new WebDriverWait(driver, 20);
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']")));
		        String parent = driver.getWindowHandle();
		         
		        
		         WebElement element = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[7]/div/div/div/div/a"));
		         JavascriptExecutor js = (JavascriptExecutor) driver; 
		         js.executeScript("arguments[0].scrollIntoView();" , element);
		         
		         
		         String click = Keys.chord(Keys.CONTROL, Keys.ENTER);
		         driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[7]/div/div/div/div/a")).sendKeys(click);
		         
		         
		        Set<String>s=driver.getWindowHandles();
		         Iterator<String> I1= s.iterator();

		         while(I1.hasNext())
		         {

		         String child_window=I1.next();


		         if(!parent.equals(child_window))
		         {
		        	 
		         driver.switchTo().window(child_window);
		         driver.manage().window().maximize();
		         Thread.sleep(3000);
		         

		   
		         
		         SoftAssert sa = new SoftAssert();
		         String url1 = driver.getCurrentUrl();
		         if(!url1.equals("https://www.lambdatest.com/integrations")) 
		         {
		        	 sa.equals("https://www.lambdatest.com/integrations"==url1);
		        	 System.out.print("Url 1 is not verified");
		         }
		         else {
		        	 System.out.println("Url 1 is  verified successfully");
		         }
		         System.out.println(s);
		         
		         
		         WebElement element2 = driver.findElement(By.linkText("Codeless Automation"));
		         JavascriptExecutor jse = (JavascriptExecutor) driver; 
		         jse.executeScript("arguments[0].scrollIntoView();" , element2);
			      
			      
			      driver.findElement(By.xpath("//a[@href='https://www.lambdatest.com/support/docs/testingwhiz-integration/']")).click();
			      
			      
			      String title2 = driver.getTitle();
			      System.out.println(title2);
			      if(title2 != "TestingWhiz Integration | LambdaTest") {
			    	  System.out.println("Title verificatioon status:"+sa.equals( "TestingWhiz Integration | LambdaTest" ==title2));
			      }
			      else {
			    	  System.out.println("Title verified successfully");
			      }
			      
			      
			     driver.close();
			     s.remove(child_window);
		         }
		         }
		         //switch to the parent window
		         driver.switchTo().window(parent);
		         
		         
		         System.out.println("Number of windows is: "+s.size());
		     
		         
		         driver.get("https://www.lambdatest.com/blog");
		         
		         
		         driver.findElement(By.xpath("//*[@id=\"menu-item-10121\"]/a")).click();
		         String url2 = driver.getCurrentUrl();
		         if(url2.equals("https://community.lambdatest.com/")){
		        	 System.out.println("Url 2 is verified");
		         }
		         else {
		        	 System.out.println("Url 2 is not verified");
		         }
		     
		         
		         driver.close();
  }
 @BeforeMethod
 @Parameters({ "browser", "version", "platform" })
  public void setUpClass(String browser, String version, String platform) throws Exception {

  	    String username = "sakshinarkhede9235";
		String accesskey = "Btd44rb6gzUJADc4HK5HbnkND54UNThkte6CZWkB4v7zpxv4BK";

  		DesiredCapabilities capability = new DesiredCapabilities();    	
        
  		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
  		capability.setCapability(CapabilityType.VERSION,version);
  		capability.setCapability(CapabilityType.PLATFORM, platform);		
  		
          capability.setCapability("build", "New TestNG");
  		capability.setCapability("network", true);
  		capability.setCapability("video", true);
  		capability.setCapability("console", true);
  		capability.setCapability("visual", true);

  		String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
  		System.out.println(gridURL);
  		driver = new RemoteWebDriver(new URL(gridURL), capability);
  		System.out.println(capability);
  		System.out.println(driver.getSessionId());
        driver.get("https://www.lambdatest.com/");
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); // Waiting time to page the load completely
  		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
	 
  }
  @AfterMethod
  public void close()
  {
	  driver.quit();
  }

}