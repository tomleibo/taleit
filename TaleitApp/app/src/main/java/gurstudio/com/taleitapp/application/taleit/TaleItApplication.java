package gurstudio.com.taleitapp.application.taleit;

import gurstudio.com.taleitapp.application.core.ApplicationBase;
import gurstudio.com.taleitapp.model.taleit.TaleItModel;
import gurstudio.com.taleitapp.network.taleit.TaleItNetworkRequestBase;
import gurstudio.com.taleitapp.network.taleit.GetCategoriesRequest;
import gurstudio.com.taleitapp.network.taleit.GetStoriesRequest;
import gurstudio.com.taleitapp.network.core.NetworkManager;
import gurstudio.com.taleitapp.networkhandlers.taleit.GetCategoriesResponseHandler;
import gurstudio.com.taleitapp.networkhandlers.taleit.GetStoriesResponseHandler;

/**
 * Created by gur on 5/17/2016.
 */
public class TaleItApplication extends ApplicationBase<TaleItModel, NetworkManager> {
    @Override
    public void onCreate(){
        super.onCreate();

        buildApplicationModel();
    }

    @Override
    protected TaleItModel createApplicationModel() {
        return new TaleItModel();
    }

    @Override
    protected NetworkManager createNetworkManager() {
        return new NetworkManager(this);
    }

    private void buildApplicationModel(){
        GetCategoriesResponseHandler categoriesResponseHandler = new GetCategoriesResponseHandler(this);
        TaleItNetworkRequestBase categoriesRequest = GetCategoriesRequest.create(categoriesResponseHandler);
        categoriesRequest.sendAsync(getNetworkManager());

        GetStoriesResponseHandler storiesResponseHandler = new GetStoriesResponseHandler(this);
        TaleItNetworkRequestBase storiesRequest = GetStoriesRequest.create(storiesResponseHandler);
        storiesRequest.sendAsync(getNetworkManager());
    }
}