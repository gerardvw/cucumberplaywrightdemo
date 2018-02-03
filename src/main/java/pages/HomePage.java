package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver webdriver;

//    @FindBy(id = "#search_query_top")
//    private WebElement searchQuery;

    private By searchQueryLocator = By.cssSelector("#search_query_top");

    public HomePage(WebDriver webdriver) {
        this.webdriver = webdriver;
//        PageFactory.initElements(this.webdriver, this);
    }

    public void navigate() {
        webdriver.navigate().to("http://automationpractice.com/index.php");
    }

    private void waitUntilPageLoaded() {
        WebDriverWait webDriverWait = new WebDriverWait(webdriver, 10, 500);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(searchQueryLocator));
    }

}
