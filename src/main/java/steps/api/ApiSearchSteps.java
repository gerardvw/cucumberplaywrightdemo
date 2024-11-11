package steps.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.options.RequestOptions;
import context.ScenarioContext;
import context.SearchContext;
import context.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ApiSearchSteps {
    private final ScenarioContext scenarioContext;
    private final SearchContext searchContext;

    public ApiSearchSteps(TestContext testContext, ScenarioContext scenarioContext, SearchContext searchContext) {
        this.scenarioContext = scenarioContext;
        this.searchContext = searchContext;
    }

    @When("^I search by api for (.*)$")
    public void iSearchForByApi(String searchTerm) {
        var requestBody = new HashMap<>();
        requestBody.put("search_product", searchTerm);

        var requestOptions = RequestOptions.create();
        requestOptions.setHeader("content-type", "application/json");
        //requestOptions.setQueryParam("search_product", searchTerm);
        requestOptions.setData(requestBody);
        //TODO: move to separate api client
        var response = scenarioContext.requestContext.post("/api/searchProduct", requestOptions);
        assertThat(response).isOK();

        searchContext.apiResponse = response;
    }

    @Then("^I should get an item with description (.*) and a price of (.*)$")
    public void iShouldGetAnItemWithDescriptionDescriptionAndAPriceOfPrice(String expectedDescription, String expectedPrice) {
        var response = searchContext.apiResponse;
        JsonObject json = new Gson().fromJson(response.text(), JsonObject.class);
        Assert.assertTrue("expectedDescription:" + expectedDescription + ";actualDescription:" + response.text(), response.text().contains(expectedDescription));
    }
}
