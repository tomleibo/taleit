package selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by sharonk on 6/11/2016
 */
public class ContinuePageObject {

    WebDriver webDriver;

    public ContinuePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement paragraphTitle() {
        return webDriver.findElement(By.className("titleInput"));
    }

    public WebElement body() {
        return webDriver.findElement(By.tagName("textarea"));
    }

    public WebElement setItFree() {
        return webDriver.findElement(By.id("createBtn"));
    }

    public WebElement cancel() {
        return webDriver.findElement(By.id("cancelBtn"));
    }
}

