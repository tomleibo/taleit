package selenium;

import org.openqa.selenium.WebDriver;
import selenium.pageObjects.CreatePageObject;
import selenium.pageObjects.MainPageObject;

/**
 * Created by Shai on 07/06/2016.
 */
public class ServicesSeleniumFacade {
    WebDriver webDriver;

    public ServicesSeleniumFacade(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public MainPageObject mainPageObject() {
       return new MainPageObject(webDriver);
    }

    public CreatePageObject createPageObject() {
        return new CreatePageObject(webDriver);
    }


}
