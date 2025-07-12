package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

    /**
     * Scrolls the page to bring the given WebElement into view.
     *
     * @param driver  the active WebDriver instance
     * @param element the WebElement to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element
        );
    }

}
