package selenium.seleniumtests;

import selenium.SeleniumBase;
import selenium.pageObjects.MainPageObject;

/**
 * Created by sharonk on 6/20/2016
 */
public class ITBase extends SeleniumBase {
    @Override
    public void buildup() {
        super.buildup();
        MainPageObject mainPageObject = facade.mainPageObject();
        waitFor(TIME_TO_WAIT);
        performFacebookLogin();
    }
}
