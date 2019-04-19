package pages;

import util.drivermanagers.DriverManager;
import util.properties.EnvironmentProperties;

public abstract class BasePage {

    public BasePage() {
    }

    public void navigate() {
        DriverManager.getWebdriver().navigate().to(EnvironmentProperties.getBaseUrl() + getRelativeUrl());
        waitUntilPageIsLoaded();
    }

    protected abstract String getRelativeUrl();

    protected void waitUntilPageIsLoaded() {
        //TODO: generic wait for all pages, for example wait until javascript, jquery, etc. is ready when used
    }
}
