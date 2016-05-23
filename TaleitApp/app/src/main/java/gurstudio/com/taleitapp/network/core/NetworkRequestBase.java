package gurstudio.com.taleitapp.network.core;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public abstract class NetworkRequestBase extends JsonObjectRequest {
    public NetworkRequestBase(BaseUri baseUri, int method, String route, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, baseUri.toString() + route, jsonRequest, listener, errorListener);
    }

    public void sendAsync(NetworkManager manager){
        manager.sendAsync(this);
    }
}

