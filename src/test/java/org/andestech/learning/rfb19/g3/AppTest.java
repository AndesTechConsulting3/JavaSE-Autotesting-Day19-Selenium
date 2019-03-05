package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Level;

import static org.testng.Assert.assertTrue;

public class AppTest
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

    }

    @Test
    public void testCaseChrome01()
    {

      //  wd = new ChromeDriver(cap);
        wd = new ChromeDriver(options);
        wd.get("http://google.com");

        wd.manage().logs().getAvailableLogTypes().forEach(t -> System.out.println(t));

     //   wd.manage().logs().get("browser").getAll().
     //           forEach( (LogEntry logEntry) ->

           wd.manage().logs().get("performance").getAll().
                   forEach( (LogEntry logEntry) ->

        System.out.printf("%s, level:%s, mess:%s \n",
                new Date(logEntry.getTimestamp()),
                logEntry.getLevel(), logEntry.getMessage())                   );

      //  Files.write(P)


        assertTrue( true );
    }


    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}