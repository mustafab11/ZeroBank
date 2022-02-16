package com.zerobank.stepdefnitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class LoginStepDefinition {

    LoginPage loginPage = new LoginPage();

    @Given("the user is on login page")
    public void the_user_is_on_login_page() {

        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

    }


    @When("the user enters the valid credentials")
    public void the_user_enters_the_valid_credentials (){

        //loginPage.signIn.click();
        loginPage.userLogin.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.userPassword.sendKeys(ConfigurationReader.getProperty("password"));
        //loginPage.keepMeSignedIn.click();


    }

    @When("the user enters the INVALID {string} and {string}")
    public void the_user_enters_the_INVALID_and(String username, String password) {

        //loginPage.signIn.click();
        loginPage.userLogin.sendKeys(username);
        loginPage.userPassword.sendKeys(password);
        //loginPage.keepMeSignedIn.click();


    }

    @When("the user clicks the signIn button")
    public void the_user_clicks_the_sign_in_button() {
        loginPage.sign_Login_Bttn.click();
    }

    @Then("the user should see the {string} title")
    public void the_user_should_see_the_title(String expectedTitle) {
        assertEquals("Title is not correct", expectedTitle, Driver.getDriver().getTitle());

    }

    @Then("the user should see the {string} message")
    public void the_user_should_see_the_message(String expectedFailureMessage) {

        assertEquals("failure message", expectedFailureMessage, loginPage.loginErrorMessage.getText());

    }


}
