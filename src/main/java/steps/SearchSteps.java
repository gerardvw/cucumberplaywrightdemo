package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.HomePage;

public class SearchSteps {

    private HomePage homePage = new HomePage();

    @Given("^homepage is opened$")
    public void homepageIsOpened() {
        homePage.navigate();
    }

    @When("^I search for (.*)$")
    public void iSearchFor(String searchTerm) {
        homePage.searchFor(searchTerm);
    }

    @And("^I choose to view the search results for being viewed in a list$")
    public void iChooseToViewTheSearchResultsForBeingViewedInAList() {
        homePage.selectResultsInAList();
    }

    @Then("^I should see an item with description (.*) and a price of (.*)$")
    public void iShouldSeeAnItemWithDescriptionDescriptionAndAPriceOfPrice(String expectedDescription, String expectedPrice) {
        Assert.assertEquals("Description not found.", expectedDescription, homePage.getDescriptionFromList());
    }

    @And("^this item should be (.*)$")
    public void thisItemShouldBeInStock(String expectedAvailability) {
        Assert.assertEquals("Availability not as expected.", expectedAvailability, homePage.getAvailabilityFromList());
    }

    @And("^it should be possible to add this item to my cart$")
    public void itShouldBePossibleToAddThisItemToMyCart() {
        Assert.assertTrue("AddToCart not available.", homePage.addToCartIsAvailable());
    }
}
