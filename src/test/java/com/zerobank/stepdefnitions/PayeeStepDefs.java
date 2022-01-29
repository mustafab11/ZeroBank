package com.zerobank.stepdefnitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.PayeePage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.Map;

public class PayeeStepDefs {

    LoginPage loginPage = new LoginPage();
    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
    PayeePage payeePage = new PayeePage();

    @Given("Add New Payee tab")
    public void add_new_payee_tab() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.userLogin.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.userPassword.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.sign_Login_Bttn.click();

        String expectedTitle = "Zero - Account Summary";
        BrowserUtils.titleVerification(expectedTitle);
        accountSummaryPage.navigateToTabOfPage("Pay Bills", "Add New Payee");
        expectedTitle = "Zero - Pay Bills";
        BrowserUtils.titleVerification(expectedTitle);
    }



    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> payeeInfo) {
        payeePage.payeeName.sendKeys(payeeInfo.get("Payee Name"));
        payeePage.payeeAddress.sendKeys(payeeInfo.get("Payee Address"));
        payeePage.payeeAccount.sendKeys(payeeInfo.get("Account"));
        payeePage.payeeDetails.sendKeys(payeeInfo.get("Payee details"));
        payeePage.payeeAddBttn.click();
    }

    @Then("message {} should be displayed")
    public void message_the_new_payee_the_law_offices_of_hyde_price_scharks_was_successfully_created_should_be_displayed(String expectedSuccessMessage) {
        String actualAlertMessage = payeePage.successAlert.getText();
        BrowserUtils.wait(5);
        assertEquals("Payee could not be added successfully", expectedSuccessMessage, actualAlertMessage);
    }
}
