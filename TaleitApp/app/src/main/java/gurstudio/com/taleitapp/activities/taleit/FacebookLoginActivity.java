package gurstudio.com.taleitapp.activities.taleit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import gurstudio.com.taleitapp.R;

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
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        getBaseApplication().getApplicationModel().setLoggedInUser(loginResult.getAccessToken());
        finish();
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