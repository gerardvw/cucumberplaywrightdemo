package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.ScenarioManager;

public class HomePage {

    private WebDriver webdriver;
    private WebDriverWait webDriverWait;

    @FindBy(css = "#search_query_top")
    private WebElement searchQuery;

    @FindBy(css = "#searchbox > button")
    private WebElement searchButton;

    @FindBy(css = "#list > a")
    private WebElement resultsInAList;

    @FindBy(css = "#center_column > ul > li > div > div > div.center-block.col-xs-4.col-xs-7.col-md-4 > h5 > a")
    private WebElement description;

    @FindBy(css = "#center_column > ul > li > div > div > div.center-block.col-xs-4.col-xs-7.col-md-4 > span > span")
    private WebElement availability;

    @FindBy(css = "a.button.ajax_add_to_cart_button.btn.btn-default")
    private WebElement addToCart;

    private By searchQueryLocator = By.cssSelector("#search_query_top");
    private By searchResultLocator = By.cssSelector("#center_column > h1 > span.heading-counter");
    private By descriptionLocator = By.cssSelector("#center_column > ul > li > div > div > div.center-block.col-xs-4.col-xs-7.col-md-4 > h5 > a");

    public HomePage() {
        this.webdriver = ScenarioManager.getWebdriver();
        this.webDriverWait = ScenarioManager.getWebDriverWait();
        PageFactory.initElements(this.webdriver, this);
    }

    public void navigate() {
        webdriver.navigate().to("http://automationpractice.com/index.php");
        waitUntilPageIsLoaded();
    }

    public void searchFor(String searchTerm) {
        searchQuery.sendKeys(searchTerm);
        searchButton.click();
        waitUntilPageIsLoaded();
        waitUntilSearchResultAvailable();
    }

    public void selectResultsInAList() {
        resultsInAList.click();
        waitUntilResultsInAList();
    }

    public String getDescriptionFromList() {
        return description.getText();
    }

    public String getAvailabilityFromList() {
        return availability.getText();
    }

    public boolean addToCartIsAvailable() {
        return addToCart.isEnabled();
    }

    private void waitUntilResultsInAList() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(descriptionLocator));
    }

    private void waitUntilSearchResultAvailable() {
        webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(searchResultLocator, "been found."));
    }

    private void waitUntilPageIsLoaded() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(searchQueryLocator));
    }
}
