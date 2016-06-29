package selenium.seleniumtests.e2e;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import selenium.pageObjects.ContinuePageObject;
import selenium.pageObjects.CreatePageObject;
import selenium.pageObjects.MainPageObject;
import selenium.pageObjects.ViewStoryObject;
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
        createPageObject.chooseCategotyByIndex(2);
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

        log("Verify Story Title text");
        ViewStoryObject viewStoryObject = facade.viewStoryObject();
        Assert.assertEquals("Title is not as expected ", viewStoryObject.storyTitle().getText(), STORY_TITLE);

        log("Verify Paragraph Title text");
        Assert.assertEquals("Paragraph Title is not as expected ", viewStoryObject.paragraphTitle().getText(),
                            PARAGRAPH_TITLE);

        log("Verify Body text");
        Assert.assertTrue("Body is not as expected ", BODY_TEXT.contains(viewStoryObject.body().getText().substring
                (0, 60)));


    }

    @Test
    public void continueStoryAndVerifyOnStoryPage() {
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

        log("Verify Story Title text");
        ViewStoryObject viewStoryObject = facade.viewStoryObject();
        Assert.assertEquals("Title is not as expected ", viewStoryObject.storyTitle().getText(), STORY_TITLE);

        log("Verify Paragraph Title text");
        Assert.assertEquals("Paragraph Title is not as expected ", viewStoryObject.paragraphTitle().getText(),
                            PARAGRAPH_TITLE);

        log("Verify Body text");
        Assert.assertTrue("Body is not as expected ", BODY_TEXT.contains(viewStoryObject.body().getText().substring
                (0, 60)));

        log("Click on Continue story");
        viewStoryObject.createButton().click();
        waitFor(TIME_TO_WAIT);
        ContinuePageObject continuePageObject = facade.continuePageObject();

        log("Fill Paragraph Title");
        continuePageObject.paragraphTitle().sendKeys(CONTINUE_PARAGRAPH_TITLE);
        waitFor(TIME_TO_WAIT);

        log("Fill Body text");
        continuePageObject.body().sendKeys(BODY_TEXT);
        waitFor(TIME_TO_WAIT);

        log("Click on 'Set It Free'");
        continuePageObject.setItFree().click();

        log("Verify Story Title text");
        viewStoryObject = facade.viewStoryObject();
        Assert.assertEquals("Title is not as expected ", viewStoryObject.storyTitle().getText(), STORY_TITLE);

        log("Verify Paragraph Title text");
        Assert.assertEquals("Paragraph Title is not as expected ", viewStoryObject.paragraphTitle().getText(),
                            CONTINUE_PARAGRAPH_TITLE);

        log("Verify Body text");
        Assert.assertTrue("Body is not as expected ", BODY_TEXT.contains(viewStoryObject.body().getText().substring
                (0, 60)));

    }

}
