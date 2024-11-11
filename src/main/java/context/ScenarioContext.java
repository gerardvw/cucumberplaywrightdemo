package context;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;

public class ScenarioContext {

    public Page page;
    public APIRequestContext requestContext;

    public ScenarioContext() {}
}
