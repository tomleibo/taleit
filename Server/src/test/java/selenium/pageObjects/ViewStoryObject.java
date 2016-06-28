package selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by sharonk on 6/11/2016
 */
public class ViewStoryObject {
    WebDriver webDriver;


    public ViewStoryObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement storyTitle(){
        return webDriver.findElement(By.id("storyTitle"));
    }


    public WebElement paragraphTitle(){
        return webDriver.findElement(By.id("paragraphTitle"));
    }


    public WebElement body(){
        return webDriver.findElement(By.id("bodyText"));
    }

    public WebElement author(){
        return webDriver.findElement(By.id("Author"));
    }

    public WebElement createButton(){
        return webDriver.findElement(By.className("createButton"));
    }



}

