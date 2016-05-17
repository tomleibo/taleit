package gurstudio.com.taleitapp;

import android.app.Application;

import gurstudio.com.taleitapp.model.DataBinder;

/**
 * Created by gur on 5/17/2016.
 */
public class BaseApplication extends Application{
    private static BaseApplication application;
    private DataBinder binder;

    public static BaseApplication getApplication(){
        return application;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        application = this;

        binder = new DataBinder();
    }

    public DataBinder getDataBinder(){ return binder; }
}
