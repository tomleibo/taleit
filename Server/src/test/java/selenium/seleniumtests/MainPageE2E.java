package selenium.seleniumtests;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import selenium.SeleniumBaseTZIPI;
import selenium.pageObjects.MainPageObjectTZIPI;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Shai on 07/06/2016.
 */
public class MainPageE2E extends SeleniumBaseTZIPI {

    @Test
    public void numberOfCategories(){
        MainPageObjectTZIPI mainPageObject = facade.mainPageObject();

        assertEquals(mainPageObject.categories().size(), 9);
    }

    @Test
    public void imgChanges(){
        MainPageObjectTZIPI mainPageObject = facade.mainPageObject();

        List<WebElement> elems = mainPageObject.categoryImages();
        String width = elems.get(0).getCssValue("width");
        assertTrue(width.contains("160"));

        Actions action = mainPageObject.getAction();
        action.moveToElement(elems.get(0));
        action.perform();
        
        mainPageObject.wait2secs();

        elems = mainPageObject.categoryImages();
        width = elems.get(0).getCssValue("width");
        assertTrue(width.contains("220"));


    }
}
