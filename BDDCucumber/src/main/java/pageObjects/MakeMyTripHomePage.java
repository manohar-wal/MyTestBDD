package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MakeMyTripHomePage {
	WebDriver driver;
	By nav_flight=By.xpath("//*[@id='SW']/div[1]/div[2]/div/div/nav/ul/li[@class='menu_Flights']");
	
	public MakeMyTripHomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	
	public void click_flights()
	{
		
		
			try {
		if(driver.findElement(By.xpath("//*[@id='SW']/div[1]/div[1]/ul/li[5]/div[3]")).isDisplayed())
		{
			//driver.findElement(By.xpath("//*[@id='SW']/div[1]/div[1]/a/img")).click();
			//driver.navigate().to("https://www.makemytrip.com/flights");;
			//driver.navigate().refresh();
			System.out.println("Pop-up is found");
             Actions a=new Actions(driver);
             a.moveToElement(driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/span[2]"))).click().perform();
             
			//driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div[2]/span[2]")).click();	
		
		}}
			catch(Exception e)
			{

				
			}
			
		
		//driver.findElement(By.xpath("//*[@id='SW']/div[1]")).click();
			WebDriverWait wait=(WebDriverWait) new WebDriverWait(driver, 20).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(nav_flight)).click();
	//driver.findElement(nav_flight).click();
		Assert.assertEquals(driver.getCurrentUrl().contains("flights"),true);
	}
	
	
	
	

}
