package selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by sharonk on 6/11/2016
 */
public class CreatePageObject {
    WebDriver webDriver;


    public CreatePageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement storyTitle(){
        return webDriver.findElement(By.id("sInput"));
    }


    public WebElement categoryDropDown(){
        return webDriver.findElement(By.tagName("select"));
    }


    public WebElement paragraphTitle(){
        return webDriver.findElement(By.id("pInput"));
    }


    public WebElement body(){
        return webDriver.findElement(By.tagName("textarea"));
    }

    public WebElement setItFree(){
        return webDriver.findElement(By.id("createBtn"));
    }

    public WebElement cancel() {
        return webDriver.findElement(By.id("cancelBtn"));
    }

    public void chooseCategotyByIndex(int index){
        categoryDropDown().click();
        webDriver.findElements(By.tagName("option")).get(index).click();
         body().click();
    }


}

