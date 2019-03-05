package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;

public class LoginPageAppTest
{
    private WebDriver wd = null;
    private DesiredCapabilities cap = null;
    private ChromeOptions options = null;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "E:\\drivers\\selenium\\chromedriver.exe");
    System.out.println("+++ Class: " + this);

    // trace perfomance
    // http://chromedriver.chromium.org/logging/performance-log

        // DesiredCapabilities cap = DesiredCapabilities.chrome();  // ver.1.
        cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        options = new ChromeOptions();
        options.merge(cap);


        options.setPageLoadStrategy(PageLoadStrategy.NONE);

    }

    @Test
    public void loginTest()
    {
        wd = new ChromeDriver(options);


    }


    @Test
    public void testCaseChrome01()
    {

        wd = new ChromeDriver(options);
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        Long t1 = System.currentTimeMillis();
       // wd.get("http://google.com");
        wd.navigate().to("http://google.com");

        System.out.println( "dT=" + (System.currentTimeMillis() - t1) );

        wd.findElement(By.name("q")).sendKeys("Jupiter");



        assertTrue( true );
    }


    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
