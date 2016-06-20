package selenium.seleniumtests.e2e;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import selenium.pageObjects.CreatePageObject;
import selenium.pageObjects.MainPageObject;
import selenium.seleniumtests.E2EBase;

/**
 * Created by sharonk on 6/19/2016
 */
public class CreateStoryE2E extends E2EBase {

    private MainPageObject mainPageObject;


    @Before
    @Override
    public void buildup() {
        super.buildup();
        mainPageObject = facade.mainPageObject();
        waitFor(TIME_TO_WAIT);
    }

    @Test
    public void createStoryAndVerifyOnStoryPage() {
        log("Click on 'Create' button");
        mainPageObject.createButton().click();
        waitFor(TIME_TO_WAIT);

        log("Fill Story Title");
        CreatePageObject createPageObject = facade.createPageObject();
        createPageObject.storyTitle().sendKeys(STORY_TITLE);
        waitFor(TIME_TO_WAIT);

        log("Fill Paragraph Title");
        createPageObject.paragraphTitle().sendKeys(PARAGRAPH_TITLE);
        waitFor(TIME_TO_WAIT);

        log("Fill Body text");
        createPageObject.body().sendKeys(BODY_TEXT);
        waitFor(TIME_TO_WAIT);

        log("Choose Category");
        createPageObject.chooseCategotyByIndex(3);
        waitFor(TIME_TO_WAIT);

        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

        log("Verify Alert text");
        Assert.assertTrue("Alert Text Message is incorrect", webDriver.switchTo().alert().getText().contains
                ("Facebook"));

        log("Accept Alert");
        webDriver.switchTo().alert().accept();

        log("Perform Facebook login");
        performFacebookLogin();

        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

    }

}
