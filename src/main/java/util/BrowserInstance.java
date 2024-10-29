package util;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenSize;
import com.microsoft.playwright.options.ViewportSize;

import java.nio.file.Paths;
import java.util.ArrayList;

public class BrowserInstance
{
    private Browser _browser;
    private BrowserContext _context;

    public Page page;

    public void setup(String browserName, Boolean headless)
    {
        var playwright = Playwright.create();

        var launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setChannel(browserName);
        launchOptions.setHeadless(headless);

        var args = new ArrayList<String>();
        args.add("--auth-server-allowlist=\"_\"");
        args.add("--start-maximized");
        launchOptions.setArgs(args);

        _browser = playwright.chromium().launch(launchOptions);

        var newContextOptions = new Browser.NewContextOptions();
        newContextOptions.setIgnoreHTTPSErrors(true);

        var viewPortSize = new ViewportSize(1920, 1080);
        newContextOptions.setViewportSize(viewPortSize);

        var screenSize = new ScreenSize(1920, 1080);
        newContextOptions.setScreenSize(screenSize);

        newContextOptions.setHttpCredentials("TODOusername", "TODOpassword");

        _context = _browser.newContext(newContextOptions);
        _context.setDefaultTimeout(10000);
        _context.clearCookies();

        page = _context.newPage();
    }

    public void teardown()
    {
        page.close();
        _context.close();
        _browser.close();
    }

    public byte[] CreateScreenShot(String step)
    {
        return page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(step,"screenshot.png")).setFullPage(true));
    }
}
