package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import util.drivermanagers.DriverManager;

public class HomePage extends BasePage {

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
        super();
        PageFactory.initElements(DriverManager.getWebdriver(), this);
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
        DriverManager.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(descriptionLocator));
    }

    private void waitUntilSearchResultAvailable() {
        DriverManager.getWebDriverWait().until(ExpectedConditions.textToBePresentInElementLocated(searchResultLocator, "been found."));
    }

    @Override
    protected void waitUntilPageIsLoaded() {
        super.waitUntilPageIsLoaded();
        DriverManager.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(searchQueryLocator));
    }

    @Override
    protected String getRelativeUrl() {
        return "/index.php";
    }
}
