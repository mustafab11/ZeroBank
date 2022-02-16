package com.zerobank.stepdefnitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import java.util.List;

public class AccountSummaryStepDefs {

    LoginPage loginPage = new LoginPage();
    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();

    @Given("the user is logged in and sees Account Summary Page")
    public void the_user_is_logged_in_and_sees_account_summary_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.userLogin.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.userPassword.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.sign_Login_Bttn.click();


        String expectedTitle = "Zero - Account Summary";
        BrowserUtils.titleVerification(expectedTitle);
    }


    @Then("the Account Summary Page should have the following account types")
    public void the_account_summary_page_should_have_the_following(List<String> expectedAccountTypes ) {

        System.out.println("expectedAccountTypes = " + expectedAccountTypes);

        for (String eachAccount:expectedAccountTypes ) {
            String actualAccountType = accountSummaryPage.findAccountTypes(eachAccount).getText();
            System.out.println("actualAccountType = " + actualAccountType);
            assertEquals(eachAccount+" not found", eachAccount, actualAccountType);
        }

    }


    @Then("{string} type should have the following columns")
    public void type_should_have_the_following_types(String expectedAcountType, List<String> expectedColumnOfAccountType) {

        System.out.println("expectedAcountType = " + expectedAcountType);
        System.out.println("expectedColumnOfAccountType = " + expectedColumnOfAccountType);

        for (String eachColumn:expectedColumnOfAccountType) {
            String actualColumnOfAccountType = accountSummaryPage.findColumnsOfType(expectedAcountType, eachColumn).getText();
            System.out.println("actualColumnOfAccountType = " + actualColumnOfAccountType);

            assertEquals(eachColumn+" not found", eachColumn, actualColumnOfAccountType);
        }
    }


    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_account_summary_page(String expectedLink) {

        accountSummaryPage.navigateFromLinkOfSummaryToActivity(expectedLink).click();

    }



}
