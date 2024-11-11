package util;

import com.microsoft.playwright.*;
import org.apache.commons.lang3.StringUtils;

public class ApiInstance
{
    public APIRequestContext ApiRequestContext;

    public void setup(String baseUrl)
    {
        var playwright = Playwright.create();

        ApiRequestContext = playwright
                .request()
                .newContext(new APIRequest.NewContextOptions()
                    .setBaseURL(StringUtils.stripEnd(baseUrl, "/"))
                );
    }

    public void teardown()
    {
        if (ApiRequestContext != null) {
            ApiRequestContext.dispose();
            ApiRequestContext = null;
        }
    }
}
