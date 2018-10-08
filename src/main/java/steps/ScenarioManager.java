package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Config;

public class ScenarioManager {

    private static WebDriver webdriver;

    @Before("@ie")
    public void beforeScenario() {
        System.setProperty("webdriver.ie.driver",".\\src\\main\\resources\\drivers\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");

        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

        webdriver = new InternetExplorerDriver(capabilities);
        webdriver.manage().window().maximize();
    }

    @Before("@chrome")
    public void beforeScenarioChrome() {
        System.setProperty("webdriver.chrome.driver",".\\src\\main\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

        webdriver = new ChromeDriver(capabilities);
        webdriver.manage().window().maximize();
    }

    @Before("@phantomjs")
    public void beforeScenarioPhantomJS() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", false);
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                ".\\src\\main\\resources\\drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
        );
        webdriver = new PhantomJSDriver(caps);
        webdriver.manage().window().maximize();
    }

    @After("@ie, @chrome, @phantomjs")
    public void afterScenario() {
        webdriver.close();
        webdriver.quit();
    }

    public static WebDriver getWebdriver() {
        return webdriver;
    }

    public static WebDriverWait getWebDriverWait() {
        WebDriverWait webDriverWait = new WebDriverWait(webdriver, Config.getTimeoutInSeconds(), Config.getSleepInMillis());
        webDriverWait.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return webDriverWait;
    }
}
