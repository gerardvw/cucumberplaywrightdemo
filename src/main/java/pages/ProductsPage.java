package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Page;

public class ProductsPage extends BasePage {

    public ProductsPage(String baseUrl, Page page) {
        super(baseUrl, page);
    }

    @Override
    protected String getRelativeUrl() {
        return "/products";
    }

    private Locator searchFor(){
        return page.locator("#search_product");
    }

    private Locator submit() {
        return page.locator("#submit_search");
    }

    public void searchForItem(String searchTerm) {
        searchFor().fill(searchTerm);
        submit().click();
    }

    public ProductInfo productInfo(String expectedDescription, String expectedPrice) {
        return new ProductInfo(
                page.locator(".productinfo")
                        .filter(new Locator.FilterOptions().setHasText(expectedDescription))
                        .filter(new Locator.FilterOptions().setHasText(expectedPrice))
        );
    }

    public static class ProductInfo {

        public Locator self;

        public ProductInfo(Locator parent){
            self = parent;
        }

        public Locator addToCart(){
            return self.locator(".add-to-cart");
        }
    }
}
