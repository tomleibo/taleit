package selenium.seleniumtests.it;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import selenium.pageObjects.ContinuePageObject;
import selenium.pageObjects.MainPageObject;
import selenium.pageObjects.ViewStoryObject;
import selenium.seleniumtests.ITBase;

/**
 * Created by sharonk on 6/19/2016
 */
public class ContinueStoryIT extends ITBase {

    private MainPageObject mainPageObject;
    private ViewStoryObject viewStoryObject;
    private ContinuePageObject continuePageObject;



    @Before
    @Override
    public void buildup() {
        super.buildup();
        mainPageObject = facade.mainPageObject();
        waitFor(TIME_TO_WAIT);
        createNewStory();
        viewStoryObject = facade.viewStoryObject();

        log("Click On Create Story");
        viewStoryObject.createButton().click();
        waitFor(TIME_TO_WAIT);

        continuePageObject = facade.continuePageObject();

    }

    @Test
    public void noParagraphTitle() {
        log("Click on 'Set It Free' button");
        continuePageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

        log("Verify paragraph title is required");
        Assert.assertTrue(continuePageObject.paragraphTitle().getAttribute("class").contains("ng-invalid-required"));

    }

    @Test
    public void noBodyText() {
        log("Fill Paragraph Title");
        continuePageObject.paragraphTitle().sendKeys(CONTINUE_PARAGRAPH_TITLE);
        waitFor(TIME_TO_WAIT);


        log("Click on 'Set It Free' button");
        continuePageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

        log("Verify story title is required");
        Assert.assertTrue(continuePageObject.body().getAttribute("class").contains("ng-invalid-required"));

    }

}
