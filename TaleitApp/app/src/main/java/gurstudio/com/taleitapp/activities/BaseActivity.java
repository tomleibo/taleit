package gurstudio.com.taleitapp.activities;

import android.support.v7.app.AppCompatActivity;

import gurstudio.com.taleitapp.model.DataBinder;

/**
 * Created by gur on 4/23/2016.
 */
public class BaseActivity extends AppCompatActivity {
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
}