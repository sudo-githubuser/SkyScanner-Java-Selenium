package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectRepository.HomePageFlightsObjects;
import pageFactory.HomePageFlights;
import testContext.TestContext;

public class SearchFlight_ReturnJourney {

    TestContext testContext;
    HomePageFlights homePageFlights;
    HomePageFlightsObjects homePageFlightsObjects;

    public SearchFlight_ReturnJourney(TestContext context){
        testContext = context;
        homePageFlights = testContext.getPageObjectManager().getHomePageFlights();
        homePageFlightsObjects = testContext.getPageObjectManager().getHomePageFlightsObjects();
    }

    @Given("User visits skyscanner website")
    public void userVisitsSkyscannerWebsite() {
        homePageFlights.launchUrl();
    }

    @And("Selects Flight")
    public void selectsFlight() {
    }

    @When("User enters {string} city")
    public void userEntersCity(String arg0) {
    }

    @When("Enters {string} city")
    public void entersCity(String arg0) {
    }

    @When("Enters {string} date")
    public void entersDate(String arg0) {
    }

    @When("Selects {string}")
    public void selects(String arg0) {
    }

    @When("Search flights")
    public void searchFlights() {
    }

    @Then("Flight results are displayed")
    public void flightResultsAreDisplayed() {
    }
}
