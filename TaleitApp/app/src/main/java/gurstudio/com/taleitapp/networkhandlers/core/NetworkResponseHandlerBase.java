package gurstudio.com.taleitapp.networkhandlers.core;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import gurstudio.com.taleitapp.application.core.ApplicationBase;

public abstract class NetworkResponseHandlerBase<A extends ApplicationBase> implements Response.Listener<JSONObject>, Response.ErrorListener {
    protected final A application;

    protected NetworkResponseHandlerBase(A application){
        this.application = application;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(application, error.toString(), Toast.LENGTH_LONG);
    }

    protected List<JSONObject> toList(JSONArray array) throws JSONException {
        List<JSONObject> list = new ArrayList<>();

        for (int i = 0; i < array.length(); i++){
            list.add((JSONObject) array.get(i));
        }

        return list;
    }
}
