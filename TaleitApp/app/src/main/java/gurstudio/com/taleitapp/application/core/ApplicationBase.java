package gurstudio.com.taleitapp.application.core;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import gurstudio.com.taleitapp.activities.core.ActivityBase;
import gurstudio.com.taleitapp.model.core.ApplicationModel;
import gurstudio.com.taleitapp.model.core.DataBinder;
import gurstudio.com.taleitapp.network.core.NetworkManager;

/**
 * Created by gur on 5/17/2016.
 */
public abstract class ApplicationBase<M extends ApplicationModel, N extends NetworkManager, A extends ActivityBase> extends Application{
    private DataBinder binder;
    private M applicationModel;
    private N networkManager;
    private A currentActivity;

    @Override
    public void onCreate(){
        super.onCreate();

        binder = new DataBinder();
        applicationModel = createApplicationModel();
        networkManager = createNetworkManager();
    }

    protected abstract M createApplicationModel();
    protected abstract N createNetworkManager();

    public DataBinder getDataBinder(){ return binder; }

    public M getApplicationModel() { return applicationModel; }

    public N getNetworkManager() { return networkManager; }

    public <T extends Activity> void  startActivity(Class<T> activity){
        startActivity(new Intent(this, activity));
    }

    public void setCurrentActivity(A activity){
        this.currentActivity = activity;
    }

    public A getCurrentActivity(){
        return currentActivity;
    }
}
