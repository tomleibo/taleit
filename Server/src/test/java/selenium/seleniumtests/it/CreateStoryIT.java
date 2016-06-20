package selenium.seleniumtests.it;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import selenium.pageObjects.CreatePageObject;
import selenium.pageObjects.MainPageObject;
import selenium.seleniumtests.ITBase;

/**
 * Created by sharonk on 6/19/2016
 */
public class CreateStoryIT extends ITBase {

    private MainPageObject mainPageObject;


    @Before
    @Override
    public void buildup() {
        super.buildup();
        mainPageObject = facade.mainPageObject();
        waitFor(TIME_TO_WAIT);
    }

    @Test
    public void noStoryTitle() {
        log("Click on 'Create' button");
        mainPageObject.createButton().click();
        waitFor(TIME_TO_WAIT);

        CreatePageObject createPageObject = facade.createPageObject();

        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

        log("Verify story title is required");
        Assert.assertTrue(createPageObject.storyTitle().getAttribute("class").contains("ng-invalid-required"));

    }

    @Test
    public void noParagraphTitle() {
        log("Click on 'Create' button");
        mainPageObject.createButton().click();
        waitFor(TIME_TO_WAIT);

        CreatePageObject createPageObject = facade.createPageObject();

        log("Fill Story Title");
        createPageObject.storyTitle().sendKeys(STORY_TITLE);
        waitFor(TIME_TO_WAIT);

        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

        log("Verify story title is required");
        Assert.assertTrue(createPageObject.paragraphTitle().getAttribute("class").contains("ng-invalid-required"));

    }

    @Test
    public void noBodyText() {
        log("Click on 'Create' button");
        mainPageObject.createButton().click();
        waitFor(TIME_TO_WAIT);

        CreatePageObject createPageObject = facade.createPageObject();

        log("Fill Story Title");
        createPageObject.storyTitle().sendKeys(STORY_TITLE);
        waitFor(TIME_TO_WAIT);

        log("Fill Paragraph Title");
        createPageObject.paragraphTitle().sendKeys(PARAGRAPH_TITLE);
        waitFor(TIME_TO_WAIT);


        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

        log("Verify story title is required");
        Assert.assertTrue(createPageObject.body().getAttribute("class").contains("ng-invalid-required"));

    }

}
