package context;

import com.microsoft.playwright.APIResponse;
import pages.ProductsPage;

public class SearchContext {

    public ProductsPage.ProductInfo productInfo;
    public APIResponse apiResponse;

    public SearchContext() {}
}
