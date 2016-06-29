package selenium.seleniumtests;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.SeleniumBase;
import selenium.pageObjects.MainPageObject;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Shai on 07/06/2016.
 */
public class MainPageE2E extends SeleniumBase {
    @Ignore
    @Test
    public void numberOfCategories(){
        MainPageObject mainPageObject = facade.mainPageObject();

        assertEquals(mainPageObject.categories().size(), 9);
    }
    @Ignore
    @Test
    public void imgChanges(){
        MainPageObject mainPageObject = facade.mainPageObject();

        List<WebElement> elems = mainPageObject.categoryImages();
        String width = elems.get(0).getCssValue("width");
        assertTrue(width.contains("160"));

        Actions action = mainPageObject.getAction();
        action.moveToElement(elems.get(0));
        action.perform();

        waitFor(TIME_TO_WAIT);

        elems = mainPageObject.categoryImages();
        width = elems.get(0).getCssValue("width");
        assertTrue(width.contains("220"));

    }
}
