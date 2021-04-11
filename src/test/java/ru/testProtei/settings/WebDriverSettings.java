package ru.testProtei.settings;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverSettings {

    public ChromeDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver(89.0.4389.23).exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

//    @After
//    public void close() {
//        driver.quit();
//    }
}
