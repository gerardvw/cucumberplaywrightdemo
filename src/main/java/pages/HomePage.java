package pages;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    public HomePage(String baseUrl, Page page) {
        super(baseUrl, page);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }
}
