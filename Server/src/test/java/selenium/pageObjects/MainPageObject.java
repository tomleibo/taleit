package selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created by sharonk on 6/11/2016
 */
public class MainPageObject {
    WebDriver webDriver;


    public MainPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> categories(){
        return webDriver.findElements(By.className("categoryClass"));
    }

    public List<WebElement> categoryImages(){
        return webDriver.findElements(By.className("categoryImg"));
    }

    public WebElement createButton(){
            return webDriver.findElement(By.id("create"));
    }

    public WebElement facebookButton(){
        return webDriver.findElement(By.tagName("fb:login-button"));
    }

    public Actions getAction(){
        Actions action = new Actions(webDriver);
        return action;
    }
}

