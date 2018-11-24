package util.drivermanagers;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import util.drivermanagers.DriverManager;

import java.io.File;

public class InternetExplorerDriverManager extends DriverManager {

    private InternetExplorerDriverService driverService;

    @Override
    public void startService() {
        if (null == driverService) {
            try {
                driverService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(new File(".\\src\\main\\resources\\drivers\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe"))
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
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        //requireWindowFocus is a workaround for slow typing in textfields with 64 bit driver server
        //When this is not working anymore switch to 32 bit driver server
        capabilities.setCapability("requireWindowFocus", true);

        driver = new InternetExplorerDriver(driverService, capabilities);
    }

}
