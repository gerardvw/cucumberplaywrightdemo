package util;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager = null;

        switch (type) {
            case chrome:
                driverManager = new ChromeDriverManager();
                break;
            case ie:
                driverManager = new InternetExplorerDriverManager();
                break;
            case phantomjs:
                driverManager = new PhantomJsDriverManager();
                break;
            default:
                throw new IllegalArgumentException("DriverType not supported (yet)");
        }
        return driverManager;

    }
}
