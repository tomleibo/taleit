package gurstudio.com.taleitapp.application.taleit;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import gurstudio.com.taleitapp.activities.taleit.TaleItActivity;
import gurstudio.com.taleitapp.application.core.ApplicationBase;
import gurstudio.com.taleitapp.model.taleit.TaleItModel;
import gurstudio.com.taleitapp.network.taleit.FacebookLoginRequest;
import gurstudio.com.taleitapp.network.taleit.GetCategoriesRequest;
import gurstudio.com.taleitapp.network.taleit.GetStoriesRequest;
import gurstudio.com.taleitapp.network.taleit.TaleItNetworkManager;
import gurstudio.com.taleitapp.network.taleit.TaleItNetworkRequestBase;
import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;
import gurstudio.com.taleitapp.networkhandlers.taleit.GetCategoriesResponseHandler;
import gurstudio.com.taleitapp.networkhandlers.taleit.GetStoriesResponseHandler;

/**
 * Created by gur on 5/17/2016.
 */
public class TaleItApplication extends ApplicationBase<TaleItModel, TaleItNetworkManager, TaleItActivity> {
    @Override
    public void onCreate(){
        super.onCreate();

        buildApplicationModel();

        FacebookSdk.sdkInitialize(getApplicationContext());

        initProfile();
    }

    private void initProfile() {
        final AccessToken token = AccessToken.getCurrentAccessToken();
        if (token == null){
            return;
        }

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // this is where you should have the profile

                        getNetworkManager().sendAsync(FacebookLoginRequest.create(token, new NetworkResponseHandlerBase(TaleItApplication.this) {
                            @Override
                            public void onResponse(Object response) {
                                try {
                                    JSONObject jsonResponse = (JSONObject) response;
                                    String cookie = jsonResponse.getJSONObject("data").getString("cookie");
                                    getApplicationModel().setUserCookie(cookie);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }));
                        getApplicationModel().setFacebookProfile(Profile.getCurrentProfile());
                        Log.v("fetched info", object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link"); //write the fields you need
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected TaleItModel createApplicationModel() {
        return new TaleItModel(this);
    }

    @Override
    protected TaleItNetworkManager createNetworkManager() {
        return new TaleItNetworkManager(this);
    }

    public void buildApplicationModel(){
        GetCategoriesResponseHandler categoriesResponseHandler = new GetCategoriesResponseHandler(this);
        TaleItNetworkRequestBase categoriesRequest = GetCategoriesRequest.create(categoriesResponseHandler);
        categoriesRequest.sendAsync(getNetworkManager());

        GetStoriesResponseHandler storiesResponseHandler = new GetStoriesResponseHandler(this);
        TaleItNetworkRequestBase storiesRequest = GetStoriesRequest.create(storiesResponseHandler);
        storiesRequest.sendAsync(getNetworkManager());
    }
}