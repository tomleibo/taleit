package gurstudio.com.taleitapp.activities.taleit;

import android.os.Bundle;
import android.util.Log;

import gurstudio.com.taleitapp.activities.core.ActivityBase;
import gurstudio.com.taleitapp.application.taleit.TaleItApplication;

public abstract class TaleItActivity extends ActivityBase<TaleItApplication> {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        TaleItApplication application = getBaseApplication();

        Log.d(getClass().getSimpleName(), "Created");
    }
}
