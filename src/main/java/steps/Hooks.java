package steps;

import context.ScenarioContext;
import context.TestContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import util.*;
import util.properties.EnvironmentProperties;
import util.properties.SystemProperties;

public class Hooks {

    private final ScenarioContext scenarioContext;
    private final TestContext testContext;

    private final BrowserInstance BrowserInstance = new BrowserInstance();

    public Hooks(TestContext testContext, ScenarioContext scenarioContext) {
        this.testContext = testContext;
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void beforeScenario(){
        try {
            this.testContext.baseUrl = EnvironmentProperties.getBaseUrl();
            this.testContext.browserName = SystemProperties.getBrowserName().toString();
            this.testContext.headless = SystemProperties.getHeadless();

            BrowserInstance.setup(this.testContext.browserName, this.testContext.headless);
            this.scenarioContext.page = BrowserInstance.page;
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
            throw exception;
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                var testName = scenario.getName();
                var screenshot = BrowserInstance.CreateScreenShot(testName);

                scenario.attach(screenshot, "image/png", testName);
            }
        }
        catch (Exception exception) {
            System.err.println(exception.getMessage());
            throw exception;
        }
        finally {
            BrowserInstance.teardown();
        }
    }
}
