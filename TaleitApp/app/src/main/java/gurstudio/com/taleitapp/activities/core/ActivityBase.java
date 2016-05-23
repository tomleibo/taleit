package gurstudio.com.taleitapp.activities.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gurstudio.com.taleitapp.application.core.ApplicationBase;
import gurstudio.com.taleitapp.model.core.DataBinder;

/**
 * Created by gur on 4/23/2016.
 */
public abstract class ActivityBase<T extends ApplicationBase> extends AppCompatActivity {
    private final DataBinder createBinder = new DataBinder();
    private final DataBinder resumeBinder = new DataBinder();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        findViews();
        initViews();
    }

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

    protected DataBinder getResumeBinder(){ return resumeBinder; }
    protected DataBinder getCreateBinder(){ return createBinder; }

    protected T getBaseApplication() {
        return (T) getApplicationContext();
    }

    protected abstract int getContentViewId();

    protected abstract void findViews();
    protected abstract void initViews();
}