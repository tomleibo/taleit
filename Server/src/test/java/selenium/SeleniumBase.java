package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by sharonk on 6/11/2016
 */
public class SeleniumBase {
    protected WebDriver webDriver;
    protected static final String URL_BASE = "http://localhost:8080";
    protected ServicesSeleniumFacade facade;

    @Before
    public void buildup(){
        System.setProperty("webdriver.chrome.driver", "utils\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(URL_BASE);
        facade = new ServicesSeleniumFacade(webDriver);
       // (new DummyDB()).eraseInjectDummyDB();

    }

    @After
    public void tearDown(){
        webDriver.close();
    }
}
