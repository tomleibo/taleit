package gurstudio.com.taleitapp.network.taleit;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;

public class ContinueStoryRequest extends TaleItNetworkRequestBase {
    final static String ROUTE = "/rest/stories/continue";

    private ContinueStoryRequest(JSONObject content, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(Method.POST, ROUTE, content, listener, errorListener);
    }

    public static ContinueStoryRequest create(String storyId, String paragraphId, String title, String content, String userId, NetworkResponseHandlerBase handler){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("text", content);
            jsonObject.put("storyId", storyId);
            jsonObject.put("paragraphId", paragraphId);
            jsonObject.put("cookie", userId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ContinueStoryRequest(jsonObject, handler, handler);
    }
}