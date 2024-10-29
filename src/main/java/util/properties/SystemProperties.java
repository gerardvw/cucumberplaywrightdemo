package util.properties;

import util.BrowserName;
import util.Environment;

public class SystemProperties {

    private SystemProperties() {
    }

    public static Environment getEnvironment() {
        String environmentSystemProperty = System.getProperty("env");
        return (environmentSystemProperty == null) ? Environment.dev : Environment.valueOf(environmentSystemProperty.toLowerCase());
    }

    public static BrowserName getBrowserName() {
        String browserSystemProperty = System.getProperty("browser");
        return (browserSystemProperty == null) ? BrowserName.chrome : BrowserName.valueOf(browserSystemProperty.toLowerCase());
    }

    public static boolean getHeadless() {
        String headlessSystemProperty = System.getProperty("headless");
        return headlessSystemProperty == null || Boolean.parseBoolean(headlessSystemProperty);
    }
}