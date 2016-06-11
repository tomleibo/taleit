package selenium;

import org.openqa.selenium.WebDriver;
import selenium.pageObjects.MainPageObjectTZIPI;

/**
 * Created by Shai on 07/06/2016.
 */
public class ServicesSeleniumFacade {
    WebDriver webDriver;

    public ServicesSeleniumFacade(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public MainPageObjectTZIPI mainPageObject() {
       return new MainPageObjectTZIPI(webDriver);
    }


}
