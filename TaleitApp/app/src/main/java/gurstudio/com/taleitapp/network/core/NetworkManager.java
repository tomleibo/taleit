package gurstudio.com.taleitapp.network.core;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by gur on 5/17/2016.
 */
public class NetworkManager {
    private RequestQueue queue;

    public NetworkManager(Application context){
        queue = Volley.newRequestQueue(context);
    }

    public void sendAsync(Request request){
        queue.add(request);
    }
}
