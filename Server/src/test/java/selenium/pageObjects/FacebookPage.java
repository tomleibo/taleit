package selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by sharonk on 6/20/2016
 */
public class FacebookPage {

    WebDriver webDriver;

    public FacebookPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement email() {
        return webDriver.findElement(By.id("email"));
    }

    public WebElement password() {
        return webDriver.findElement(By.id("pass"));
    }

    public WebElement login() {
        return webDriver.findElement(By.id("loginbutton"));
    }
}
