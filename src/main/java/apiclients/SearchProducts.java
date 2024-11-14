package apiclients;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class SearchProducts extends BaseApiClient {

    public SearchProducts(APIRequestContext apiRequestContext) {
        super(apiRequestContext);
    }

    @Override
    public String relativeUrlPart() {
        return "/api/searchProduct";
    }

    public dtos.response.searchproduct.SearchProducts post(String searchTerm) {
        dtos.response.searchproduct.SearchProducts searchProducts;

        //TODO: refactor to new (generic) classes
        var requestOptions = RequestOptions.create();
        var formData = FormData.create();
        formData.set("search_product", searchTerm);

        requestOptions.setHeader("content-type", "application/x-www-form-urlencoded");
        requestOptions.setForm(formData);

        try {
            var response = apiRequestContext.post(relativeUrlPart(), requestOptions);
            var mapper = new ObjectMapper();
            searchProducts = mapper.readValue(response.text(), dtos.response.searchproduct.SearchProducts.class);
        } catch (JsonProcessingException e) {
            //TODO: log
            throw new RuntimeException(e);
        }

        return searchProducts;
    }
}
