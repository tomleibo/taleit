package selenium.seleniumtests;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import selenium.pageObjects.MainPageObject;
import selenium.SeleniumBase;

/**
 * Created by Shai on 07/06/2016.
 */
public class MainPageE2E extends SeleniumBase {

    @Test
    public void numberOfCategories(){
        MainPageObject MainPageObject = facade.mainPageObject();

        assertEquals(MainPageObject.categories().size(), 9);
    }
}
