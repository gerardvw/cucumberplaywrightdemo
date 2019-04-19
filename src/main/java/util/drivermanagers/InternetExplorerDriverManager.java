package util.drivermanagers;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.File;

public class InternetExplorerDriverManager extends DriverManager {

    private InternetExplorerDriverService driverService;

    @Override
    public void startService() {
        if (null == driverService) {
            try {
                driverService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(new File(".\\src\\main\\resources\\drivers\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe"))
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
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.introduceFlakinessByIgnoringSecurityDomains();
        options.destructivelyEnsureCleanSession();
        options.requireWindowFocus();
        options.ignoreZoomSettings();

        driver = new InternetExplorerDriver(driverService, options);
    }

}
