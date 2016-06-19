package gurstudio.com.taleitapp.network.taleit;

import com.android.volley.Response;
import com.facebook.AccessToken;

import org.json.JSONException;
import org.json.JSONObject;

import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;

public class FacebookLoginRequest extends TaleItNetworkRequestBase {
    final static String ROUTE = "/rest/accounts/fblogin";

    private FacebookLoginRequest(JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, ROUTE, jsonRequest, listener, errorListener);
    }

    public static FacebookLoginRequest create(AccessToken accessToken, NetworkResponseHandlerBase errorHandler){
        JSONObject content = new JSONObject();

        try {
            content.put("facebookAccessToken", accessToken.getToken());
            content.put("facebookId", accessToken.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new FacebookLoginRequest(content, errorHandler, errorHandler);
    }
}
