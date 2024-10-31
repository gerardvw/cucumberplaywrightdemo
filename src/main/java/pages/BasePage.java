package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
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

    public Response navigate() {
        return page.navigate(StringUtils.stripEnd(baseUrl, "/") + getRelativeUrl());
    }

    public void acceptConsentIfVisible(){
        var consentButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Consent"));
        if (consentButton.isVisible()) {
            consentButton.click();
        }
    }
}
