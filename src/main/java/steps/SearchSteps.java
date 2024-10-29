package steps;

import context.SearchContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.ProductsPage;
import context.ScenarioContext;
import context.TestContext;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchSteps {

    private final ProductsPage productsPage;
    private final SearchContext searchContext;

    public SearchSteps(TestContext testContext, ScenarioContext scenarioContext, SearchContext searchContext) {
        this.searchContext = searchContext;
        productsPage = new ProductsPage(testContext.baseUrl, scenarioContext.page);
    }

    @Given("^products page is opened$")
    public void productsPageIsOpened() {
        productsPage.navigate();
    }

    @When("^I search for (.*)$")
    public void iSearchFor(String searchTerm) {
        productsPage.searchFor().fill(searchTerm);
        productsPage.submit().click();
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
