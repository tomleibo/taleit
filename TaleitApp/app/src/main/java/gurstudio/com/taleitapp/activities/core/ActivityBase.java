package gurstudio.com.taleitapp.activities.core;

import android.app.Activity;
import android.content.Intent;
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

        initBeforeLayoutInflation();

        setContentView(getContentViewLayoutResourceId());
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

        getBaseApplication().setCurrentActivity(null);
    }

    @Override
    public void onResume(){
        super.onResume();

        getBaseApplication().setCurrentActivity(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        createBinder.clear();
    }

    public <T extends Activity> void  startActivity(Class<T> activity){
        startActivity(new Intent(this, activity));
    }

    protected DataBinder getResumeBinder(){ return resumeBinder; }
    protected DataBinder getCreateBinder(){ return createBinder; }

    protected T getBaseApplication() {
        return (T) getApplicationContext();
    }

    protected abstract int getContentViewLayoutResourceId();

    protected abstract void findViews();
    protected abstract void initViews();

    protected void initBeforeLayoutInflation() {}
}