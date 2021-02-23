package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightsPage 
{
	WebDriver driver;

	public FlightsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
By fromCity=By.xpath("//input[@id='fromCity']");
By toCity=By.xpath("//div[@class='fsw widgetOpen']//input[@id='toCity']");
By departure_date=By.xpath("//div[@class='fsw_inner ']//label[@for='departure']");
By search_Flight=By.xpath("//*[@id='root']/div/div[2]/div/div[1]/div[2]/p/a");
//By available_flight=By.xpath("//div[@id='premEcon']//div[@class='fli-list-body-section']//button[text()='Book Now']");
	
	public void selectSource(String source)
	{
		WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class);
		//driver.findElement(fromCity).clear();
		driver.findElement(fromCity).sendKeys(source);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='fromCity']/parent::label/parent::div//div[@role='combobox']//li")));
		List<WebElement> drop_down=driver.findElements(By.xpath("//input[@id='fromCity']/parent::label/parent::div//div[@role='combobox']//li"));
	
		for(WebElement e: drop_down)
		{
			if(e.getAttribute("innerText").contains(source))
			{
				e.click();
				break;
			}
		}
		
	}

 public 	void selectDestination(String destination)
	{
 //driver.findElement(toCity).click();
	 
		WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class);
	//  wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(toCity))));
	  //  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
		driver.findElement(toCity).sendKeys(destination);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@id='toCity']/parent::label/parent::div//div[@role='combobox']//li")));
		List<WebElement> drop=driver.findElements(By.xpath("//input[@id='toCity']/parent::label/parent::div//div[@role='combobox']//li"));
	
		for(WebElement e1: drop)
		{
			if(e1.getAttribute("innerText").contains("destination"))
			{
				e1.click();
				break;
			}
		}
		
	
		
	}
 
  public void selectTravelDate()
  {
		WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class);

	  wait.until(ExpectedConditions.elementToBeClickable(departure_date)).click();;

//	  driver.findElement(departure_date).click();
	  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='fsw_inner ']//div[@class='datePickerContainer']//div[@class='DayPicker-Months']/div[1]/div[@class='DayPicker-Body']//div[@role='gridcell'] [not(contains(@class,'disabled'))]")));
	  ArrayList<WebElement> date_picker=(ArrayList<WebElement>) driver.findElements(By.xpath("//div[@class='fsw_inner ']//div[@class='datePickerContainer']//div[@class='DayPicker-Months']/div[1]/div[@class='DayPicker-Body']//div[@role='gridcell'] [not(contains(@class,'disabled'))]"));
	  
	  
	  
	    date_picker.get(5).click();
	    
	    driver.findElement(search_Flight).click();
	  
  }
  
  
  public void bookAflight()
  {
	  
		WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class);

		 
		  	   	
		
	  //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();");
	  

	 /* for(int i=0;i<10;i++)
	  {
		  
		  
	 
			  ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,10000)");
	  System.out.println("Scrolling");
	 // ArrayList<WebElement> flight_list=(ArrayList<WebElement>) driver.findElements(available_flight);
	  if(flight_list.size()>=1)
	  {
		  flight_list.get(0).click();
		  break;
	  }
	  
	  }
	*/  
	//  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(available_flight));

	  
	  
  }
  
  
  
  
  
  
  
  
 
}




