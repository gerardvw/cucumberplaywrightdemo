package util.drivermanagers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService driverService;

    @Override
    public void startService() {
        if (null == driverService) {
            try {
                driverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(".\\src\\main\\resources\\drivers\\chromedriver_win32\\chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                driverService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != driverService)
            driverService.stop();
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(driverService, capabilities);
    }

}
