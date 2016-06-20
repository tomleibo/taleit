package selenium;

import org.openqa.selenium.WebDriver;
import selenium.pageObjects.CreatePageObject;
import selenium.pageObjects.FacebookPage;
import selenium.pageObjects.MainPageObject;
import selenium.pageObjects.ViewStoryObject;

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

    public FacebookPage facebookPage() {
        return new FacebookPage(webDriver);
    }

    public ViewStoryObject viewStoryObject() {
        return new ViewStoryObject(webDriver);
    }



}
