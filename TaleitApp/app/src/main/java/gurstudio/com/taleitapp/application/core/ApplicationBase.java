package gurstudio.com.taleitapp.application.core;

import android.app.Application;

import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.core.DataBinder;
import gurstudio.com.taleitapp.network.core.NetworkManager;

/**
 * Created by gur on 5/17/2016.
 */
public abstract class ApplicationBase<A extends ApplicationModel, N extends NetworkManager> extends Application{
    private DataBinder binder;
    private A applicationModel;
    private N networkManager;

    @Override
    public void onCreate(){
        super.onCreate();

        binder = new DataBinder();
        applicationModel = createApplicationModel();
        networkManager = createNetworkManager();
    }

    protected abstract A createApplicationModel();
    protected abstract N createNetworkManager();

    public DataBinder getDataBinder(){ return binder; }

    public A getApplicationModel() { return applicationModel; }

    public N getNetworkManager() { return networkManager; }
}
