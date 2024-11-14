package steps.api;

import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import context.ScenarioContext;
import context.SearchContext;
import context.TestContext;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchSteps {
    private final ScenarioContext scenarioContext;
    private final SearchContext searchContext;

    public SearchSteps(TestContext testContext, ScenarioContext scenarioContext, SearchContext searchContext) {
        this.scenarioContext = scenarioContext;
        this.searchContext = searchContext;
    }

    @When("^I search by api for (.*)$")
    public void iSearchForByApi(String searchTerm) {
        var requestOptions = RequestOptions.create();
        var formData = FormData.create();
        formData.set("search_product", searchTerm);

        requestOptions.setHeader("content-type", "application/x-www-form-urlencoded");
        requestOptions.setForm(formData);
        //TODO: move to separate api client
        var response = scenarioContext.requestContext.post("/api/searchProduct", requestOptions);
        assertThat(response).isOK();

        searchContext.apiResponse = response;
    }

    @Then("^I should get an item with description (.*) and a price of (.*)$")
    public void iShouldGetAnItemWithDescriptionDescriptionAndAPriceOfPrice(String expectedDescription, String expectedPrice) throws JsonProcessingException {
        var response = searchContext.apiResponse;
        var mapper = new ObjectMapper();;
        var searchProducts = mapper.readValue(response.text(), SearchProducts.class);

        Assert.assertTrue("expectedDescription:" + expectedDescription + ";actualDescription:" + response.text(),
                searchProducts.products.stream().anyMatch(
                        product -> product.name.equals(expectedDescription) && product.price.equals(expectedPrice)));
    }

    //TODO: move to dto package
    public record SearchProducts(int responseCode, List<Product> products){}

    public record Product(int id, String name, String price, String brand, Category category){}

    public record Category(UserType usertype, String category){}

    public record UserType(String usertype){}
}

