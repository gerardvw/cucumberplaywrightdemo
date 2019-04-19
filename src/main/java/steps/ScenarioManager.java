package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.*;
import util.drivermanagers.DriverManager;
import util.drivermanagers.DriverManagerFactory;
import util.properties.EnvironmentProperties;
import util.properties.SystemProperties;

public class ScenarioManager {

    private static WebDriver webdriver;
    private DriverManager driverManager = null;
    private DriverType driverType = null;

    @Before
    public void beforeScenario() {
        driverType = SystemProperties.getDriverType();
        driverManager = DriverManagerFactory.getManager(driverType);
        startDriver();
        maximizeWindow();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
        }
        driverManager.quitDriver();
    }

    public static WebDriver getWebdriver() {
        return webdriver;
    }

    public static WebDriverWait getWebDriverWait() {
        WebDriverWait webDriverWait = new WebDriverWait(webdriver, EnvironmentProperties.getTimeoutInSeconds(), EnvironmentProperties.getSleepInMillis());
        webDriverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return webDriverWait;
    }

    private void maximizeWindow() {
        webdriver.manage().window().maximize();
    }

    private void startDriver() {
        webdriver = driverManager.startDriver();
    }
}
