package gurstudio.com.taleitapp.network;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;

import gurstudio.com.taleitapp.networkhandlers.BaseNetworkResponseHandler;

public class GetStoriesRequest extends BaseTaleItRequest {
    final static String ROUTE = "/rest/stories/browse";

    private GetStoriesRequest(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(Request.Method.GET, ROUTE, null, listener, errorListener);
    }

    public static GetStoriesRequest create(BaseNetworkResponseHandler handler){
        return new GetStoriesRequest(handler, handler);
    }
}