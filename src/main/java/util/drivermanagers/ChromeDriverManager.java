package util.drivermanagers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

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
        driver = new ChromeDriver(driverService);
    }

}
