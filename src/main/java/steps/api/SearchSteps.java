package steps.api;

import apiclients.SearchProducts;
import context.ScenarioContext;
import context.SearchContext;
import context.TestContext;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SearchSteps {
    private final ScenarioContext scenarioContext;
    private final SearchContext searchContext;

    public SearchSteps(TestContext testContext, ScenarioContext scenarioContext, SearchContext searchContext) {
        this.scenarioContext = scenarioContext;
        this.searchContext = searchContext;
    }

    @When("^I search by api for (.*)$")
    public void iSearchForByApi(String searchTerm) {
        var searchProducts = new SearchProducts(scenarioContext.requestContext);
        var response = searchProducts.post(searchTerm);
        //TODO: refactor to separate method somewhere
        Assert.assertTrue(response != null && (response.responseCode() >= 200 && response.responseCode() <= 299));

        searchContext.searchProducts = response;
    }

    @Then("^I should get an item with description (.*) and a price of (.*)$")
    public void iShouldGetAnItemWithDescriptionDescriptionAndAPriceOfPrice(String expectedDescription, String expectedPrice) throws JsonProcessingException {
        var searchProducts = searchContext.searchProducts;

        //TODO: create expected object here based on parameters and compare to actual object?
        Assert.assertTrue("expectedDescription:" + expectedDescription + ";actualResponse:" + searchProducts.toString(),
                searchProducts.products().stream().anyMatch(
                        product -> product.name().equals(expectedDescription) && product.price().equals(expectedPrice)));
    }
}
