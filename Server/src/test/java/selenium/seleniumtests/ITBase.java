package selenium.seleniumtests;

import selenium.SeleniumBase;

/**
 * Created by sharonk on 6/20/2016
 */
public class ITBase extends SeleniumBase {
    @Override
    public void buildup() {
        super.buildup();
        waitFor(TIME_TO_WAIT);
        performFacebookLogin();
    }
}
