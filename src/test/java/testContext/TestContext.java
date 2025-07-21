package testContext;

import lombok.Getter;
import managers.PageObjectManager;
import managers.WebDriverManager;

@Getter
public class TestContext {
    private final WebDriverManager webDriverManager;
    private final PageObjectManager pageObjectManager;

    public TestContext() {
        this.webDriverManager = new WebDriverManager();
        this.pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
    }

    public TestContext(WebDriverManager webDriverManager, PageObjectManager pageObjectManager) {
        this.webDriverManager = webDriverManager;
        this.pageObjectManager = pageObjectManager;
    }

}

