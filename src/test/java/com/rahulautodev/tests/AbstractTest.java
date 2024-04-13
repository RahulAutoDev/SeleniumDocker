package com.rahulautodev.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {
    protected WebDriver driver;

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        if(Boolean.getBoolean("selenium.grid.enabled"))
        {
            this.driver = getRemoteDriver();
        }
        else{
            this.driver = getLocalDriver();
        }
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        ChromeOptions option = null;
        if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
            option = new ChromeOptions();
            option.addArguments("--headless");
            option.addArguments("--disable-gpu");
            option.addArguments("--window-size=1920,1200");
            option.addArguments("--ignore-certificate-errors");
            option.addArguments("--silent");
            option.addArguments("--remote-allow-origins=*");
            option.addArguments("--no-sandbox");
            option.addArguments("--disable-dev-shm-usage");
            option.merge(cap);
        } else {
            FirefoxOptions options = new FirefoxOptions();
        }
        return new RemoteWebDriver(new URL("http://143.110.244.31:4444/grid/console"), option);
    }

    private WebDriver getLocalDriver()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--headless");
        option.addArguments("--disable-gpu");
        option.addArguments("--window-size=1920,1200");
        option.addArguments("--ignore-certificate-errors");
        option.addArguments("--silent");
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("--no-sandbox");
        option.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(option);
    }


    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
