package util.drivermanagers;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    protected WebDriver driver;
    protected abstract void startService();
    protected abstract void stopService();
    protected abstract void createDriver();

    public WebDriver startDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (null != driver) {
            driver.close();
            driver.quit();
            driver = null;

            stopService();
        }
    }
}
