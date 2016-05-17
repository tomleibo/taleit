package gurstudio.com.taleitapp.network;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import gurstudio.com.taleitapp.BuildConfig;

/**
 * Created by gur on 5/17/2016.
 */
public abstract class BaseTaleItRequest extends JsonObjectRequest {
    final static String BASE_URI =
            BuildConfig.SERVER_PROTOCOL + "://" +
            BuildConfig.SERVER_ADDRESS + ":" +
            BuildConfig.SERVER_PORT +
            BuildConfig.SERVER_APP_ROUTE;

    public BaseTaleItRequest(int method, String route, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, BASE_URI + route, jsonRequest, listener, errorListener);
    }

    public void sendAsync(NetworkManager manager){
        manager.sendAsync(this);
    }
}