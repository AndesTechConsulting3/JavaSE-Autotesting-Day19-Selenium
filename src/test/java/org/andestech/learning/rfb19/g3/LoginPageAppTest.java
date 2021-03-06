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
import org.testng.Assert;
import org.testng.annotations.*;

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

   //@BeforeGroups(groups = "loginTests")
   @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "E:\\drivers\\selenium\\chromedriver.exe");
    System.out.println("+++ Class: " + this);

        options = new ChromeOptions();
        options.merge(cap);
        options.setBinary("E:\\progs\\chrome-win\\chrome.exe");
        options.addArguments("--user-data-dir="+"C:\\Users\\and\\AppData\\Local\\Chromium\\User Data");
      //  options.setPageLoadStrategy(PageLoadStrategy.NONE);
        wd = new ChromeDriver(options);
   }

    @Test(groups = "loginTests", priority = 1)
    public void positiveLoginTest()
    {
        // wd = new ChromeDriver(options);

        LoginData loginData = new LoginData("ppetrov2", "Ppetrov1!");
        LoginPage page = new LoginPage(wd, loginData);

        Assert.assertTrue(page.tryLogin(),"Неверный логин или пароль!!");

        Assert.assertTrue(page.tryLogin(),"Неверный логин или пароль!!");

    }

    @Test(groups = "loginTests")
    public void negativeLoginTest()
    {
       // wd = new ChromeDriver(options);

        LoginData loginData = new LoginData("ppetrov2", "Ppetr22!");
        LoginPage page = new LoginPage(wd, loginData);

        Assert.assertFalse(page.tryLogin(),"Верный логин и пароль!!");
    }


    @Test
    @Ignore
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


    //  @AfterGroups
      @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
