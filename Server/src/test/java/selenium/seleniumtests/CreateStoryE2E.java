package selenium.seleniumtests;

import org.junit.Before;
import org.junit.Test;
import selenium.SeleniumBase;
import selenium.pageObjects.CreatePageObject;
import selenium.pageObjects.FaceboogPage;
import selenium.pageObjects.MainPageObject;

/**
 * Created by sharonk on 6/19/2016
 */
public class CreateStoryE2E extends SeleniumBase {

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
        createPageObject.storyTitle().sendKeys("The True King Of The North");
        waitFor(TIME_TO_WAIT);

        log("Fill Paragraph Title");
        createPageObject.paragraphTitle().sendKeys("Dark Beginning");
        waitFor(TIME_TO_WAIT);

        log("Fill Body text");
        createPageObject.body().sendKeys("Eddard Stark was just a fool, \n " +
                                                 "but...");
        waitFor(TIME_TO_WAIT);

        log("Choose Category");
        createPageObject.chooseCategotyByIndex(3);
        waitFor(TIME_TO_WAIT);

        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

        log("Accept Alert");
        webDriver.switchTo().alert().accept();

        log("Perform Facebook login");

        String parentHandle = webDriver.getWindowHandle();
        log("Click on 'Facebook Login' button");
        mainPageObject.facebookButton().click();
        for (String winHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        waitFor(TIME_TO_WAIT);
        FaceboogPage faceboogPage = facade.faceboogPage();

        log("Insert Email");
        faceboogPage.email().sendKeys("taleit42@gmail.com");
        waitFor(TIME_TO_WAIT);

        log("Insert Password");
        faceboogPage.password().sendKeys("BritneySpears42");
        waitFor(TIME_TO_WAIT);

        log("Click login");
        faceboogPage.login().click();
        webDriver.switchTo().window(parentHandle);
        waitFor(TIME_TO_WAIT);

        log("Click on 'Set It Free' button");
        createPageObject.setItFree().click();
        waitFor(TIME_TO_WAIT);

    }

}
