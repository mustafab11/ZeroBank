package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "signin_button")
    public WebElement signIn;

    @FindBy(id = "user_login")
    public WebElement userLogin;

    @FindBy(id = "user_password")
    public WebElement userPassword;

    @FindBy(xpath = "//*[.='Keep me signed in']")
    public WebElement keepMeSignedIn;

    @FindBy(name = "submit")
    public WebElement sign_Login_Bttn;

    @FindBy(xpath = "//*[contains(text(),'Login and/or password are wrong.')]")
    public WebElement loginErrorMessage;

    public void loginGeneral(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        userLogin.sendKeys(ConfigurationReader.getProperty("username"));
        userPassword.sendKeys(ConfigurationReader.getProperty("password"));
        sign_Login_Bttn.click();
    }




}
