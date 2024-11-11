package steps;

import context.SearchContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import pages.ProductsPage;
import context.ScenarioContext;
import context.TestContext;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchSteps {

    private final ProductsPage productsPage;
    private final SearchContext searchContext;
    private final ScenarioContext scenarioContext;

    public SearchSteps(TestContext testContext, ScenarioContext scenarioContext, SearchContext searchContext) {
        this.searchContext = searchContext;
        this.scenarioContext = scenarioContext;
        productsPage = new ProductsPage(testContext.baseUrl,this.scenarioContext.page);
    }

    @Given("^products page is opened$")
    public void productsPageIsOpened() {
        var response= productsPage.navigate();
        Assert.assertTrue("Response status code should be Ok", response.ok());

        productsPage.acceptConsentIfVisible();
    }

    @When("^I search for (.*)$")
    public void iSearchFor(String searchTerm) {
        productsPage.searchForItem(searchTerm);
    }

    @Then("^I should see an item with description (.*) and a price of (.*)$")
    public void iShouldSeeAnItemWithDescriptionAndAPriceOf(String expectedDescription, String expectedPrice) {
        searchContext.productInfo = productsPage.productInfo(expectedDescription, expectedPrice);

        assertThat(searchContext.productInfo.self).isVisible();
    }

    @And("^it should be possible to add this item to my cart$")
    public void itShouldBePossibleToAddThisItemToMyCart() {
        assertThat(searchContext.productInfo.addToCart()).isEnabled();
    }
}
