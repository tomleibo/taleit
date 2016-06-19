package gurstudio.com.taleitapp.network.taleit;

import com.android.volley.Response;

import org.json.JSONObject;

import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;

public class GetCategoriesRequest extends TaleItNetworkRequestBase {
    final static String ROUTE = "/rest/stories/categories";

    private GetCategoriesRequest(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(Method.GET, ROUTE, null, listener, errorListener);
    }

    public static GetCategoriesRequest create(NetworkResponseHandlerBase handler){
        return new GetCategoriesRequest(handler, handler);
    }
}

