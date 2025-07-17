package objectRepository;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFlightsObjects {
    private final WebDriver driver;

    public HomePageFlightsObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(css = "button[title='Flights']")
    private WebElement flightButton; //Verify isSelected, if not then select

    @Getter
    @FindBy(css = "button[title='Select trip type']")
    private WebElement selectTripType; //click, dropdown will be displayed

    /*@FindBy(xpath = ".//section[@id='popover-TripTypeTopLevel']/div/ul")
     * displayed dropdown xpath, can be used for assertion/verify the values to display to the user to select one */
    public WebElement selectJourneyType(String testIdValue) {
        var locator = By.xpath(String.format(".//span[@data-testid='%s']", testIdValue));
        return driver.findElement(locator); //pass value and perform click, values will be ONE_WAY, RETURN and MULTI_CITY
    }

    @Getter
    @FindBy(css = "#originInput-input")
    private WebElement originCity; //click and send values

    @Getter
    //xpath = "//ul[@class='BpkList_bpk-list__YWExM ChipDropdown_list__ZGRjM']/li[1]"
    @FindBy(css = "li[id^='originInput-item']") //put ExplicitWait/fluent wait and use
    private WebElement firstCityFromOriginCityDropdown; //perform click or use .sendKeys(Keys.ARROW_DOWN + Keys.ENTER) as an alternative
    /*
    can be improved further based on sent values in origin city.
    After selecting city from dropdown, perform getText and display the same.
     */

    @Getter
    @FindBy(css = "#destinationInput-input")
    private WebElement destinationCity; //click and send values

    @Getter
    @FindBy(css = "li[id^='destinationInput-item']") //put ExplicitWait/fluent wait and use
    private WebElement firstCityFromDestinationCityDropdown; //perform click or use .sendKeys(Keys.ARROW_DOWN + Keys.ENTER) as an alternative
    /*
     can be improved further based on sent values in destination city.
     After selecting city from dropdown, perform getText and display the same.
     */

    @Getter
    @FindBy(css = "button[aria-label='Swap origin and destination'] span")
    private WebElement swapOriginDestination; //It's a button, click

    @Getter
    @FindBy(css = "button[data-testid='depart-btn']")
    private WebElement departDateField; //click, date picker will be displayed

    @Getter
    //@FindBy(xpath = ".//div[@data-testid='calendar']") -- use for assertion. To assert, date picker is displayed
    @FindBy(xpath = ".//div[@class='CustomCalendar_CalendarsContainer__ZGIwN']/div[1]/h2")
    private WebElement departDate;

    public void selectDepartDate(String departureDate){
        var dDate =  By.xpath(String.format("//button[contains(@aria-label, '%s') and contains(@aria-label, 'Select as departure date')]", departureDate));
        driver.findElement(dDate);
        //give departureDate = 16 July 2025
        /* ("//button[@aria-label='%s. Select as departure date']", departureDate)
          give departureDate = Wednesday, 16 July 2025 */
    }

    @Getter
    @FindBy(xpath = ".//div[@class='CustomCalendar_CalendarsContainer__ZGIwN']/div[2]/h2")
    private WebElement returnDate;

    public void selectReturnDate(String returnDate){
        var rDate = By.xpath(String.format("//button[contains(@aria-label, '%s') and contains(@aria-label, 'Select as return date')]", returnDate));
        driver.findElement(rDate);
    }

    /*
     Before selecting the date, when date picker is displayed, from departure date we need to match that the month
     is displayed in the date picker window correctly and then select the date
    */
    /* The Departure date should be greater than or equal to the return date */

    public static By verifyMonth(String month){
        return By.xpath(String.format("//h2[normalize-space()='%s']", month));
        //get text from the xpath and match with the month given in selectDepartDate
        //same for return date
    }

    @Getter
    @FindBy(xpath = "//button[contains(@aria-label, 'Next month')]")
    private WebElement goToNextMonth; // click operation, after clicking, assert the month you are in

    @Getter
    @FindBy(xpath = "//button[contains(@aria-label, 'Previous month')]")
    private WebElement goToPreviousMonth; // click operation, after clicking, assert the month you are in

    @Getter
    @FindBy(css = "button[id='1']")
    private WebElement flexibleDates;

    @Getter
    @FindBy(css = "button[id='0']")
    private WebElement specificDates; //by default specific date is selected

    @Getter
    @FindBy(xpath = "//button[normalize-space()='Apply']")
    private WebElement datePickerApply; // click operation, after selecting dates.Scroll to the element and click like below
    /*
    public void scrollAndClickApplyButton() {
        Utils.scrollToElement(driver, datePickerApply);
        datePickerApply.click();
    }
    */

    @Getter
    @FindBy(xpath = "//button[@data-testid='traveller-button']")
    private WebElement travellerCabinClass; // onClick,
    // a window will be displayed
    // for selecting cabin class and number of travelers

    @Getter
    @FindBy(css = "#search-controls-cabin-class-dropdown")
    private WebElement cabinClassDropdown; // select class, pass this WebElement to the utility class select method

    /**
     * Adjusts passenger count for "Adults", "Children", etc., based on given value.
     *
     * @param driver        WebDriver instance
     * @param passengerType "Adults" or "Children"
     * @param expectedCount Desired number of passengers
     */
    public void adjustPassengerCount(WebDriver driver, String passengerType, int expectedCount) {
        var moreButtonLocator = By.xpath(String.format("//button[@title='More %s']", passengerType));
        var fewerButtonLocator = By.xpath(String.format("//button[@title='Fewer %s']", passengerType));
        var countDisplayLocator = By.cssSelector("#" + passengerType.toLowerCase() + "-nudger");

        var addButton = driver.findElement(moreButtonLocator);
        var removeButton = driver.findElement(fewerButtonLocator);
        var countDisplay = driver.findElement(countDisplayLocator);

        var currentCount = Integer.parseInt(countDisplay.getText().trim());
        //verify the selected number of adult passengers against the user provided data
        // and can be used for assertion also

        while (currentCount < expectedCount) {
            addButton.click();
            currentCount++;
        }

        while (currentCount > expectedCount) {
            removeButton.click();
            currentCount--;
        }

        System.out.printf("Passenger count for %s set to: %d%n", passengerType, currentCount);
    }
    // usage adjustPassengerCount(driver, "Adults", 3);
    // add retryClicks for flaky tests
    /*
    var addButton = wait.until(ExpectedConditions.elementToBeClickable(moreButtonLocator));
    private static void retryClick(WebElement element) {
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                element.click();
                return;
            } catch (ElementClickInterceptedException | StaleElementReferenceException | TimeoutException e) {
                logger.warn("Click attempt {} failed: {}", attempt, e.getClass().getSimpleName());
                Thread.sleep(500);
            }
        }
        throw new RuntimeException("Failed to click element after " + MAX_RETRIES + " attempts.");
    }
     */

    @Getter
    @FindBy(xpath = "//button[contains(text(), 'Apply')]")
    private WebElement travellerAndCabinClassApply; // scroll to view and click like below
    /*
    public void scrollAndClickApplyButton() {
        Utils.scrollToElement(driver, travellerAndCabinClassApply);
        travellerAndCabinClassApply.click();
    }
     */

    @Getter
    @FindBy(xpath = "//button[contains(text(), 'Search')]")
    private WebElement searchFlights; // click operation

}
