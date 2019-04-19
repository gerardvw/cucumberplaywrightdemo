package util.drivermanagers;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class PhantomJsDriverManager extends DriverManager {

    private PhantomJSDriverService driverService;

    @Override
    public void startService() {
        if (null == driverService) {
            try {
                driverService = new PhantomJSDriverService.Builder()
                        .usingPhantomJSExecutable(new File(".\\src\\main\\resources\\drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"))
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
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", false);
//        caps.setCapability(
//                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                ".\\src\\main\\resources\\drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
//        );
        driver = new PhantomJSDriver(driverService, caps);
    }

}
