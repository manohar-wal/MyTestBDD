package pageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewBooking {
	WebDriver driver;

	public ReviewBooking(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	By travel_insuranceNo=By.xpath("//*[@id='insurance-section']/div/div[3]/label[2]/input");
    By continueButton=By.xpath("//*[@id='review-continue']");
	
	
    public void continueBooking()
    {
    	
    	Set<String> windowHandles=driver.getWindowHandles();
  Iterator<String> l1=windowHandles.iterator();
  
    	//driver.switchTo().window(l1.next());
    	//driver.switchTo().window(l1.next());
    	
    	
    	System.out.println("Current page Url" + driver.getCurrentUrl());
    	 ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,10000)");
     	// ((JavascriptExecutor)driver).executeScript("document.getElementById('review-continue').focus();");
    	// ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",driver.findElement(continueButton));
    	 
   	  System.out.println("Scrolling");
    	
   	  
		WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class);
		//wait.until (wd->((JavascriptExecutor)driver).executeScript("return document.readyState").toString()=="complete");
		
		wait.until(ExpectedConditions.elementToBeClickable(travel_insuranceNo));
		wait.until(ExpectedConditions.elementToBeClickable(continueButton));
         
	       driver.findElement(travel_insuranceNo).click();
	   	  driver.findElement(continueButton).click();
	   	  
	   		
		

   	  
   	  
   	  
    }
	

}
