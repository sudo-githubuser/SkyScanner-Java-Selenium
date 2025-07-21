package pageFactory;

import objectRepository.HomePageFlightsObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Utils;

import java.time.Duration;

public class HomePageFlights {

    WebDriver driver;
    WebDriverWait wait;
    HomePageFlightsObjects homePageFlightsObjects;

    public HomePageFlights(WebDriver driver){
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.homePageFlightsObjects = new HomePageFlightsObjects(driver);
    }

    public void selectFlight(){
        if(!homePageFlightsObjects.getFlightButton().isSelected()){
            try{
                homePageFlightsObjects.getFlightButton().click();
                System.out.println("Flight button was not selected, so it was clicked and is now selected.");
            } catch (Exception e) {
                throw new RuntimeException("Failed to click and select the flight button.", e);
            }
        } else {System.out.println("Flight button was already selected. No action taken.");}
    }

    public void tripType(){
        homePageFlightsObjects.getSelectTripType().click();
        if(!homePageFlightsObjects.selectJourneyType("RETURN").isSelected()){
            try{
                homePageFlightsObjects.selectJourneyType("RETURN").click();
                System.out.println("Journey type selected as 'Return Journey'");
            } catch (Exception e){
                throw new RuntimeException("Failed to select the journey type", e);
            }
        } else {System.out.println("Journey type is already selected as 'Return Journey'");}
    }

    public void fromCity(){
        homePageFlightsObjects.getOriginCity().sendKeys("Bengaluru");
        homePageFlightsObjects.getFirstCityFromOriginCityDropdown().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void toCity(){
        homePageFlightsObjects.getDestinationCity().sendKeys("Bangkok");
        homePageFlightsObjects.getFirstCityFromDestinationCityDropdown().sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void openDatePicker(){
        homePageFlightsObjects.getDepartDateField().click();
    }

    public void departDate(){
        String departMonth = homePageFlightsObjects.getDepartDate().getText();
        System.out.printf("Departed month is '%s'", departMonth); //Assert it with the departure date providing through excel
        homePageFlightsObjects.selectDepartDate("26 July 2025");
    }

    public void returnDate(){
        String returnMonth = homePageFlightsObjects.getReturnDate().getText();
        System.out.printf("Return month is '%s'", returnMonth); //Assert it with the return date providing through excel
        homePageFlightsObjects.selectReturnDate("04 August 2025");
    }

    public void applyDates(){
        Utils.scrollToElement(driver, homePageFlightsObjects.getDatePickerApply());
        homePageFlightsObjects.getDatePickerApply().click();
    }

    public void travelCabinClass(){
        homePageFlightsObjects.getTravellerCabinClass().click();
    }

    public void cabinClassSelection(){
        Utils.selectByVisibleText(homePageFlightsObjects.getCabinClassDropdown(), "Premium Economy");
    }

    public void passengerCount(){
        homePageFlightsObjects.adjustPassengerCount(driver, "Adult", 4);
    }

    public void applyTravelCabinClass(){
        Utils.scrollToElement(driver, homePageFlightsObjects.getTravellerAndCabinClassApply());
        homePageFlightsObjects.getTravellerAndCabinClassApply().click();
    }

    public void searchFlight(){
        homePageFlightsObjects.getSearchFlights().click();
    }
}
