package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.properties.EnvironmentProperties;

public abstract class BasePage {

    private WebDriver webdriver;
    private WebDriverWait webDriverWait;

    public BasePage(WebDriver webdriver, WebDriverWait webdriverWait) {
        this.webdriver = webdriver;
        this.webDriverWait = webdriverWait;
    }

    public void navigate() {
        webdriver.navigate().to(EnvironmentProperties.getBaseUrl() + getRelativeUrl());
        waitUntilPageIsLoaded();
    }

    protected abstract String getRelativeUrl();

    protected WebDriver getWebdriver() {
        return webdriver;
    }

    protected WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    protected void waitUntilPageIsLoaded() {
        //TODO: generic wait for all pages, for example wait until javascript, jquery, etc. is ready when used
    }
}
