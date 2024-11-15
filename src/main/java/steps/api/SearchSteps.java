package steps.api;

import apiclients.SearchProducts;
import context.ScenarioContext;
import context.SearchContext;
import context.TestContext;
import dtos.response.searchproduct.Product;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

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
        assertThat(response).isNotNull();
        assertThat(response.responseCode()).isBetween(200, 299);

        searchContext.searchProducts = response;
    }

    @Then("^I should get an item with description (.*) and a price of (.*)$")
    public void iShouldGetAnItemWithDescriptionDescriptionAndAPriceOfPrice(String expectedDescription, String expectedPrice) {
        var searchProducts = searchContext.searchProducts;

        assertThat(searchProducts.products())
                .extracting(Product::name, Product::price)
                .contains(
                        tuple(expectedDescription, expectedPrice)
                );
    }
}
