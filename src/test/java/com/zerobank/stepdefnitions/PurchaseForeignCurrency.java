package com.zerobank.stepdefnitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.BasePage;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Locale;

public class PurchaseForeignCurrency {

    LoginPage loginPage = new LoginPage();
    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
    PayBillsPage payBillsPage = new PayBillsPage();


    @Given("the user accesses the Purchase foreign currency cash tab")
    public void the_user_accesses_the_purchase_foreign_currency_cash_tab() {
        loginPage.loginGeneral();
        accountSummaryPage.navigateToTabOfPage("Pay Bills", "Purchase Foreign Currency");
    }


    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> expectedCurrencyList) {

        Select select = new Select(payBillsPage.currencySelect);
//        List<WebElement> options = select.getOptions();
        List<String> actualElementsText = BrowserUtils.getElementsText(select.getOptions());
        System.out.println("actualElementsText = " + actualElementsText);
        System.out.println("expectedCurrencyList = " + expectedCurrencyList);
        for (String each : expectedCurrencyList) {
            Assert.assertTrue(each + " is not available", actualElementsText.contains(each));
        }

        Assert.assertTrue("All are NOT available", actualElementsText.containsAll(expectedCurrencyList));

    }


    @When("user tries to calculate cost without {}")
    public void user_tries_to_calculate_cost_without_selecting_a_currency(String message) {
        if (message.toLowerCase().contains("currency")){
            BrowserUtils.wait(2);
            payBillsPage.amountFieldInPurchaseTab.sendKeys("200");
            BrowserUtils.wait(2);
            payBillsPage.calculateCostsBttn.click();
            BrowserUtils.wait(2);
        }else if (message.toLowerCase().contains("value")){
            BrowserUtils.wait(2);
            Select select = new Select(payBillsPage.currencySelect);
            select.selectByIndex(5);
            BrowserUtils.wait(2);
            payBillsPage.calculateCostsBttn.click();
            BrowserUtils.wait(1);
        }

    }


    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        Alert alert = Driver.getDriver().switchTo().alert();
        String expectedMessage = "Please, ensure that you have filled all the required fields with valid values.";
        System.out.println("alert.getText().isEmpty() = " + alert.getText().isEmpty());
        Assert.assertFalse(alert.getText().isEmpty());
        Assert.assertEquals(expectedMessage, alert.getText());
        alert.accept();

    }









}
