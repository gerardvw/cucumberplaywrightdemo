package apiclients;

import com.microsoft.playwright.APIRequestContext;

public abstract class BaseApiClient {

    protected final APIRequestContext apiRequestContext;

    public BaseApiClient(APIRequestContext apiRequestContext){
        this.apiRequestContext = apiRequestContext;
    }

    public abstract String relativeUrlPart();
}
