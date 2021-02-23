package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import helperUtilities.GetdatafromExcel;
import helperUtilities.Setup;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.FlightsPage;
import pageObjects.MakeMyTripHomePage;
import pageObjects.ReviewBooking;


public class BookMyflight {
	
  WebDriver driver;
  Scenario scenario;
  
  @Before
  public void before(Scenario scenario) {
      this.scenario = scenario;
  }


  
	@Given("User is on MakeMyTrip page.")
	public void user_is_on_MakeMyTrip_page() {
//	    throw new io.cucumber.java.PendingException();
		driver=  new Setup().driver_start();
		
		
	}

	@When("User navigate to Flights page.")
	public void user_navigate_to_Flights_page() {
	    //throw new io.cucumber.java.PendingException();
		MakeMyTripHomePage mmp=new MakeMyTripHomePage(driver);
		mmp.click_flights();
		
	}

	@And("User selects source {string} and destination {string} for the flight with date of journey.")
	public void user_selects_source_and_destination_for_the_flight_with_date_of_journey(String source,String destination) {
	    
		FlightsPage fp=new FlightsPage(driver);
		
		try {
			fp.selectSource(GetdatafromExcel.get(scenario.getName(), source));
			fp.selectDestination(GetdatafromExcel.get(scenario.getName(), destination));
			//System.out.println(scenario.getName());
			//System.out.println(GetdatafromExcel.get(scenario.getName(), source));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fp.selectTravelDate();

		
		
		
	}

	@Then("Book flight available on the journey date.")
	public void book_flight_available_on_the_journey_date() {
		FlightsPage fp=new FlightsPage(driver);
		fp.bookAflight();
		
		ReviewBooking rp=new ReviewBooking(driver);
		rp.continueBooking();
		
	}

	

}
