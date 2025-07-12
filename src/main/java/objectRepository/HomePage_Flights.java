package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Utils;

public class HomePage_Flights {
    private final WebDriver driver;

    public HomePage_Flights(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[title='Flights']")
    private WebElement flightButton; //Verify isSelected, if not then select

    @FindBy(css = "button[title='Select trip type']")
    private WebElement selectTripType; //click, dropdown will be displayed

    /*@FindBy(xpath = ".//section[@id='popover-TripTypeTopLevel']/div/ul")
     * displayed dropdown xpath, can be used for assertion/verify the values to display to the user to select one */
    public static By selectJourneyType(String testIdValue) {
        return By.xpath(String.format(".//span[@data-testid='%s']", testIdValue)); //pass value and perform click, values will be ONE_WAY, RETURN and MULTI_CITY
    }

    @FindBy(css = "#originInput-input")
    private WebElement originCity; //click and send values

    //xpath = "//ul[@class='BpkList_bpk-list__YWExM ChipDropdown_list__ZGRjM']/li[1]"
    @FindBy(css = "li[id^='originInput-item']") //put ExplicitWait/fluent wait and use
    private WebElement firstCityFromOriginCityDropdown; //perform click or use .sendKeys(Keys.ARROW_DOWN + Keys.ENTER) as an alternative
    /*
    can be improved further based on sent values in origin city.
    After selecting city from dropdown, perform getText and display the same.
     */

    @FindBy(css = "#destinationInput-input")
    private WebElement destinationCity; //click and send values

    @FindBy(css = "li[id^='destinationInput-item']") //put ExplicitWait/fluent wait and use
    private WebElement firstCityFromDestinationCityDropdown; //perform click or use .sendKeys(Keys.ARROW_DOWN + Keys.ENTER) as an alternative
    /*
     can be improved further based on sent values in destination city.
     After selecting city from dropdown, perform getText and display the same.
     */

    @FindBy(css = "button[aria-label='Swap origin and destination'] span")
    private WebElement swapOriginDestination; //It's a button, click

    @FindBy(css = "button[data-testid='depart-btn']")
    private WebElement departDateField; //click, date picker will be displayed

    //@FindBy(xpath = ".//div[@data-testid='calendar']") -- use for assertion. To assert, date picker is displayed
    @FindBy(xpath = ".//div[@class='CustomCalendar_CalendarsContainer__ZGIwN']/div[1]")
    private WebElement departDate;

    public static By selectDepartDate(String departureDate){
        return By.xpath(String.format("//button[contains(@aria-label, '%s') and contains(@aria-label, 'Select as departure date')]", departureDate));
        //give departureDate = 16 July 2025
        /* ("//button[@aria-label='%s. Select as departure date']", departureDate)
          give departureDate = Wednesday, 16 July 2025 */
    }

    @FindBy(xpath = ".//div[@class='CustomCalendar_CalendarsContainer__ZGIwN']/div[2]")
    private WebElement returnDate;

    public static By selectReturnDate(String returnDate){
        return By.xpath(String.format("//button[contains(@aria-label, '%s') and contains(@aria-label, 'Select as return date')]", returnDate));
    }

    /*
     Before selecting the date, when date picker is displayed, from departure date we need to match that the month
     is displayed in the date picker window correctly and then select the date
    */
    /* The Departure date should be greater than or equal to the return date */

    public static By verfiyMonth(String month){
        return By.xpath(String.format("//h2[normalize-space()='%s']", month));
        //get text from the xpath and match with the month given in selectDepartDate
        //same for return date
    }

    @FindBy(xpath = "//button[contains(@aria-label, 'Next month')]")
    private WebElement goToNextMonth; // click operation, after clicking, assert the month you are in

    @FindBy(xpath = "//button[contains(@aria-label, 'Previous month')]")
    private WebElement goToPreviousMonth; // click operation, after clicking, assert the month you are in

    @FindBy(css = "button[id='1']")
    private WebElement flexibleDates;

    @FindBy(css = "button[id='0']")
    private WebElement specificDates; //by default specific date is selected

    @FindBy(xpath = "//button[normalize-space()='Apply']")
    private WebElement datePickerApply; // click operation, after selecting dates.Scroll to the element and click like below
//    public void scrollAndClickApplyButton() {
//        Utils.scrollToElement(driver, datePickerApply);
//        datePickerApply.click();
//    }
    @FindBy(xpath = "//button[@data-testid='traveller-button']")
    private WebElement travellerCabinClass; // onClick, the selection window will be displayed



}
