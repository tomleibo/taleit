package gurstudio.com.taleitapp.network.taleit;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import gurstudio.com.taleitapp.networkhandlers.core.NetworkResponseHandlerBase;

public class CreateStoryRequest extends TaleItNetworkRequestBase {
    final static String ROUTE = "/rest/stories/create";

    private CreateStoryRequest(JSONObject content, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(Method.POST, ROUTE, content, listener, errorListener);
    }

    public static CreateStoryRequest create(String storyName, String category, String paragraphTitle, String paragraphContent, String userId, NetworkResponseHandlerBase handler){
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject rootParagraph = new JSONObject();
            rootParagraph.put("title", paragraphTitle);
            rootParagraph.put("text", paragraphContent);
            jsonObject.put("title", storyName);
            jsonObject.put("category", category);
            jsonObject.put("rootParagraph", rootParagraph);
            jsonObject.put("cookie", userId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new CreateStoryRequest(jsonObject, handler, handler);
    }
}
