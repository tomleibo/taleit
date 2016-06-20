package selenium.seleniumtests;

import org.junit.Before;
import org.junit.Test;
import selenium.SeleniumBase;
import selenium.pageObjects.CreatePageObject;
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
        mainPageObject.wait2secs();
    }

    @Test
    public void createStoryAndVerifyOnStoryPage() {
        mainPageObject.createButton().click();
        mainPageObject.wait2secs();
        CreatePageObject createPageObject = facade.createPageObject();
        createPageObject.storyTitle().sendKeys("The True King Of The North");
        createPageObject.paragraphTitle().sendKeys("Dark Beginning");
        createPageObject.body().sendKeys("Eddard Stark was just a fool, \n " +
                                                 "but...");
        createPageObject.chooseCategotyByIndex(3);

        createPageObject.setItFree().click();
        webDriver.switchTo().alert().accept();

        webDriver.navigate().to(URL_BASE);

    }

}
