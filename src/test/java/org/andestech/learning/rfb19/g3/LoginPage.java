package org.andestech.learning.rfb19.g3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private static final String BASE_URL = "http://andestech.org/learning/rfb18";
    private static final String LOGIN_URL = BASE_URL + "/login.html";

    private WebDriver wd;

    public LoginPage(WebDriver wd, LoginData loginData) {
        this.wd = wd;
        this.loginData = loginData;
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.get(LOGIN_URL);

        PageFactory.initElements(wd, this);


    }

    @FindBy(id = "login")
    private WebElement loginTxt;

    @FindBy(id="pass")
    private WebElement passwordTxt;

    @FindBy(name="reset")
    private WebElement resetBtn;

    @FindBy(id="lgn")
    private WebElement loginBtn;

    @FindBy(tagName = "header")
    private WebElement header;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(linkText = "Login")
    private WebElement loginLink;


    private boolean isLoggedIn(){
        if(header != null)
        {
            if(header.getAttribute("innerText").
                    contains(loginData.getLogin())) return true;
        }
        return false;
    }

    private void logout(){
          logoutLink.click();
          wd.switchTo().alert().accept();
    }

    public boolean tryLogin(){

        if(isLoggedIn()) logout();

        loginLink.click();

        resetBtn.click();
        loginTxt.sendKeys(loginData.getLogin());
        passwordTxt.sendKeys(loginData.getPassword());
        loginBtn.click();

        if(header != null)
        {
            if(header.getAttribute("innerText").
                    contains(loginData.getLogin())) return true;
        }

        String errorText = "";
        errorText = wd.switchTo().alert().getText();
        if(errorText.contains("Неверный")) return false;
        else return true;




    }



    private LoginData loginData;






}
