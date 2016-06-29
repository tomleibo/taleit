package gurstudio.com.taleitapp.activities.taleit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import gurstudio.com.taleitapp.R;
import gurstudio.com.taleitapp.network.taleit.FacebookLoginRequest;
import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;

/**
 * Created by gur on 6/18/2016.
 */
public class FacebookLoginActivity extends TaleItActivity implements FacebookCallback<LoginResult>{
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle state){
        super.onCreate(state);
    }

    @Override
    protected int getContentViewLayoutResourceId() { return R.layout.activity_facebook_login; }

    @Override
    protected void findViews() {
        loginButton = (LoginButton)findViewById(R.id.login_button);
    }

    @Override
    protected void initViews() {
        loginButton.registerCallback(callbackManager, this);
    }

    @Override
    protected void initBeforeLayoutInflation() {
        callbackManager = CallbackManager.Factory.create();
    }

    private void fetchProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // this is where you should have the profile

                        updateApplicationUser(AccessToken.getCurrentAccessToken());
                        getBaseApplication().getApplicationModel().setFacebookProfile(Profile.getCurrentProfile());
                        Log.v("fetched info", object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link"); //write the fields you need
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        final AccessToken token = loginResult.getAccessToken();
        getBaseApplication().getApplicationModel().setAccessToken(token);

        fetchProfile();


        ProfileTracker profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                this.stopTracking();
                Profile.setCurrentProfile(currentProfile);
                getBaseApplication().getApplicationModel().setFacebookProfile(currentProfile);
                updateApplicationUser(token);
            }
        };
        profileTracker.startTracking();
    }

    private void updateApplicationUser(AccessToken token) {
        getBaseApplication().getNetworkManager().sendAsync(FacebookLoginRequest.create(token, new NetworkResponseHandlerBase(getBaseApplication()) {
            @Override
            public void onResponse(Object response) {
                try {
                    JSONObject jsonResponse = (JSONObject) response;
                    String cookie = jsonResponse.getJSONObject("data").getString("cookie");
                    getBaseApplication().getApplicationModel().setUserCookie(cookie);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    @Override
    public void onCancel() {
        Log.i(getClass().getSimpleName(), "login cancelled");
    }

    @Override
    public void onError(FacebookException error) {
        Log.e(getClass().getSimpleName(), error.getMessage());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}