package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchSteps {
    private WebDriver webdriver;

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

    @Given("^homepage is opened$")
    public void homepageIsOpened() {
        webdriver.navigate().to("http://automationpractice.com/index.php");

        // example of waiting until element is present on page after navigating
        WebDriverWait webDriverWait = new WebDriverWait(webdriver, 10, 500);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search_query_top")));
    }

    @When("^I search for (.*)$")
    public void iSearchFor(String searchTerm) {
        webdriver.findElement(By.cssSelector("#search_query_top")).sendKeys(searchTerm);
        webdriver.findElement((By.cssSelector("#searchbox > button"))).click();

        WebDriverWait webDriverWait = new WebDriverWait(webdriver, 10, 500);
        webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#center_column > h1 > span.heading-counter"), "1 result has been found."));
    }

    @And("^I choose to view the search results for being viewed in a list$")
    public void iChooseToViewTheSearchResultsForBeingViewedInAList() {
        webdriver.findElement(By.cssSelector("#list > a")).click();
    }

    @Then("^I should see an item with description (.*) and a price of (.*)$")
    public void iShouldSeeAnItemWithDescriptionDescriptionAndAPriceOfPrice(String expectedDescription, String expectedPrice) {
        By expectedDescriptionLocator = By.cssSelector("#center_column > ul > li > div > div > div.center-block.col-xs-4.col-xs-7.col-md-4 > h5 > a");

        // example of waiting until element is present
        WebDriverWait webDriverWait = new WebDriverWait(webdriver, 15, 500);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(expectedDescriptionLocator));

        // and after this checking for expected result
        String actualDescription = webdriver.findElement(expectedDescriptionLocator).getText();
        Assert.assertTrue("Description not found. Expected:" + expectedDescription + ". Actual:" + actualDescription, expectedDescription.equals(actualDescription));
    }

    @And("^this item should be (.*)$")
    public void thisItemShouldBeInStock(String expectedAvailability) {
        String actualAvailability = webdriver.findElement(By.cssSelector("#center_column > ul > li > div > div > div.center-block.col-xs-4.col-xs-7.col-md-4 > span > span")).getText();
        Assert.assertTrue("Availability. Expected:" + expectedAvailability + ". Actual:" + actualAvailability, expectedAvailability.equals(actualAvailability));
    }

    @And("^it should be possible to add this item to my cart$")
    public void itShouldBePossibleToAddThisItemToMyCart() {
        boolean availabilityAddToCart = webdriver.findElement(By.cssSelector("a.button.ajax_add_to_cart_button.btn.btn-default")).isEnabled();
        Assert.assertTrue("AddToCart is not available.", availabilityAddToCart);
    }

}
