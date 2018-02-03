package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

public class SearchSteps {

    private WebDriver webdriver = ScenarioManager.getWebdriver();
    HomePage homepage = new HomePage(webdriver);

    @Given("^homepage is opened$")
    public void homepageIsOpened() {
        homepage.navigate();
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
