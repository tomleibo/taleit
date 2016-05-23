package gurstudio.com.taleitapp.network.taleit;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONObject;

import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;

public class GetStoriesRequest extends TaleItNetworkRequestBase {
    final static String ROUTE = "/rest/stories/browse";

    private GetStoriesRequest(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(Request.Method.GET, ROUTE, null, listener, errorListener);
    }

    public static GetStoriesRequest create(NetworkResponseHandlerBase handler){
        return new GetStoriesRequest(handler, handler);
    }
}

