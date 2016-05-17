package gurstudio.com.taleitapp;

import gurstudio.com.taleitapp.network.NetworkManager;

/**
 * Created by gur on 5/17/2016.
 */
public class TaleItApplication extends BaseApplication {
    private NetworkManager networkManager;

    public void onCreate(){
        super.onCreate();

        initNetworkLayer();
    }

    private void initNetworkLayer() {
        networkManager = new NetworkManager(this);
    }

    public static NetworkManager getNetworkManager(){
        return ((TaleItApplication)getApplication()).networkManager;
    }
}