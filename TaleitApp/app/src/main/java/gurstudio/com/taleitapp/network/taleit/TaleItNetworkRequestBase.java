package gurstudio.com.taleitapp.network.taleit;

import com.android.volley.Response;

import org.json.JSONObject;

import gurstudio.com.taleitapp.BuildConfig;
import gurstudio.com.taleitapp.network.core.BaseUri;
import gurstudio.com.taleitapp.network.core.NetworkRequestBase;

/**
 * Created by gur on 5/17/2016.
 */
public abstract class TaleItNetworkRequestBase extends NetworkRequestBase {
    private final static BaseUri BASE_URI = new BaseUri(BuildConfig.SERVER_PROTOCOL, BuildConfig.SERVER_ADDRESS, BuildConfig.SERVER_PORT, BuildConfig.SERVER_APP_ROUTE);

    public TaleItNetworkRequestBase(int method, String route, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(BASE_URI, method, route, jsonRequest, listener, errorListener);
    }
}

