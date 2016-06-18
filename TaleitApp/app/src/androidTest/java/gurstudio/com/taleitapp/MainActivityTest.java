package gurstudio.com.taleitapp;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import gurstudio.com.taleitapp.activities.taleit.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {


    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void test_viewDrama(){ }
}
