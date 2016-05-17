package gurstudio.com.taleitapp.networkhandlers;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNetworkResponseHandler implements Response.Listener<JSONObject>, Response.ErrorListener {
    protected final Context context;

    protected BaseNetworkResponseHandler(Context context){
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG);
    }

    protected List<JSONObject> toList(JSONArray array) throws JSONException {
        List<JSONObject> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++){
            list.add((JSONObject) array.get(i));
        }

        return list;
    }
}
