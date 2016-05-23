package gurstudio.com.taleitapp.activities.core;

import android.support.v7.app.AppCompatActivity;

import gurstudio.com.taleitapp.application.core.ApplicationBase;
import gurstudio.com.taleitapp.model.core.DataBinder;

/**
 * Created by gur on 4/23/2016.
 */
public abstract class ActivityBase<T extends ApplicationBase> extends AppCompatActivity {
    protected final DataBinder createBinder = new DataBinder();
    protected final DataBinder resumeBinder = new DataBinder();

    @Override
    public void onPause(){
        super.onPause();

        resumeBinder.clear();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        createBinder.clear();
    }

    public T getBaseApplication() {
        return (T) getApplicationContext();
    }
}