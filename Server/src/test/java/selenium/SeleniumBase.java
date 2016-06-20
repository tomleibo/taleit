package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by sharonk on 6/11/2016
 */
public class SeleniumBase {
    protected static final int TIME_TO_WAIT = 2000;
    protected WebDriver webDriver;
    protected static final String URL_BASE = "http://localhost:8080";
    protected ServicesSeleniumFacade facade;

    @Before
    public void buildup(){
        System.setProperty("webdriver.chrome.driver", "utils\\chromedriver.exe");
        log("Open new Chrome Driver");
        webDriver = new ChromeDriver();
        log("Maximize window");
        webDriver.manage().window().maximize();
        log("Navigating to Tale it");
        webDriver.get(URL_BASE);
        facade = new ServicesSeleniumFacade(webDriver);
       // (new DummyDB()).eraseInjectDummyDB();

    }

    @After
    public void tearDown(){
        webDriver.close();
    }

    protected void waitFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void log(String str){
       System.out.print(str);
    }
}
