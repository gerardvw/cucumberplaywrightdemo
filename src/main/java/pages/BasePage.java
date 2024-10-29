package pages;

import com.microsoft.playwright.*;
import org.apache.commons.lang3.StringUtils;

public abstract class BasePage {

    protected String baseUrl;
    protected Page page;

    protected abstract String getRelativeUrl();

    public BasePage(String baseUrl, Page page)
    {
        this.baseUrl = baseUrl;
        this.page = page;
    }

    public void navigate() {
        page.navigate(String.format(StringUtils.stripEnd(baseUrl, "/"),getRelativeUrl()));
    }
}
