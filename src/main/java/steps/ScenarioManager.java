package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.*;

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

    @After("@ie, @chrome, @phantomjs")
    public void afterScenario() {
        driverManager.quitDriver();
    }

    protected void maximizeWindow() {
        webdriver.manage().window().maximize();
    }

    protected void startDriver() {
        webdriver = driverManager.startDriver();
    }

    public static WebDriver getWebdriver() {
        return webdriver;
    }

    public static WebDriverWait getWebDriverWait() {
        WebDriverWait webDriverWait = new WebDriverWait(webdriver, EnvironmentProperties.getTimeoutInSeconds(), EnvironmentProperties.getSleepInMillis());
        webDriverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return webDriverWait;
    }
}
