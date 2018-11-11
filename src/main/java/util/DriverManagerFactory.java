package util;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager = null;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case IE:
                driverManager = new InternetExplorerDriverManager();
                break;
            case PHANTOMJS:
                driverManager = new PhantomJsDriverManager();
                break;
            default:
                throw new IllegalArgumentException("DriverType not supported (yet)");
        }
        return driverManager;

    }
}
