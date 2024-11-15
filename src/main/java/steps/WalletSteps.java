package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class WalletSteps {
    private int totalDollars;

    @Given("^I have (\\d+) dollar in my wallet$")
    public void I_have_dollar_in_my_wallet(int dollars) {
        totalDollars = dollars;
    }

    @When("^I take out (\\d+) dollar$")
    public void iTakeOut(int dollarsOut) {
        totalDollars = totalDollars - dollarsOut;
    }

    @Then("^my wallet should have (\\d+) dollar$")
    public void myWalletShouldHaveDollar(int dollarsExpected) {
        assertThat(totalDollars).isEqualTo(dollarsExpected);
    }
}
