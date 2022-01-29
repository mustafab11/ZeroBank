package com.zerobank.stepdefnitions;

import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.Select;

public class PayBillsStepDefinition {

    PayBillsPage payBillsPage = new PayBillsPage();
    Select select;
    String actual = "";

    @When("the user completes the payment operation as required: {string} {string} {string} {string} {string}")
    public void the_user_completes_the_payment_operation_as_required(String expPayee, String expAccount, String expAmount,
                                                                     String expDate, String expDescription) {

        select = new Select(payBillsPage.payeeField);
        select.selectByVisibleText(expPayee);

        BrowserUtils.wait(2);
        select = new Select(payBillsPage.acountField);
        select.selectByVisibleText(expAccount);

        BrowserUtils.wait(2);
        payBillsPage.amountField.sendKeys(expAmount);

        BrowserUtils.wait(1);
        payBillsPage.dateField.sendKeys(expDate);

        BrowserUtils.wait(1);
        payBillsPage.descriptionField.sendKeys(expDescription);

        BrowserUtils.wait(1);
        payBillsPage.payBttn.click();

    }

    @Then("the user should see {string} message")
    public void the_user_should_see_message(String expSuccessMessage) {
        assertEquals("Success message is not seen", expSuccessMessage, payBillsPage.successMessage.getText());
    }


    @When("the user DOES NOT complete the payment operation as required: Without {string}")
    public void the_user_does_not_complete_the_payment_operation_as_required_without_amount(String emptyField) {

        actual = emptyField;

        select = new Select(payBillsPage.payeeField);
        select.selectByVisibleText("Apple");

        BrowserUtils.wait(2);
        select = new Select(payBillsPage.acountField);
        select.selectByVisibleText("Loan");

        if(emptyField.equals("amount")) {
            BrowserUtils.wait(1);
            payBillsPage.amountField.sendKeys("");

            BrowserUtils.wait(2);
            payBillsPage.dateField.sendKeys("2022-01-19");

        }else if(emptyField.equals("date")) {
            BrowserUtils.wait(1);
            payBillsPage.amountField.sendKeys("999");

            BrowserUtils.wait(2);
            payBillsPage.dateField.sendKeys("");
        }

        BrowserUtils.wait(2);
        payBillsPage.payBttn.click();

    }


    @Then("the user sees {string} message")
    public void the_user_sees_message(String expectedFillOut) {

        System.out.println("actual = emptyField = " + actual);
        String actualFillOutMessage = "";
        if(actual.equals("date")){
            actualFillOutMessage = payBillsPage.dateField.getAttribute("validationMessage");
        }else if(actual.equals("amount")) {
            actualFillOutMessage = payBillsPage.amountField.getAttribute("validationMessage");
        }


//        String actualMessageOut = payBillsPage.amountField.getAttribute("outerHTML");
//        String actualMessageIn = payBillsPage.amountField.getAttribute("innerHTML");
//        String actualMessageVal = payBillsPage.amountField.getAttribute("value");
//
//        String actualMessageOuter = Driver.getDriver().switchTo().activeElement().getAttribute("outerHTML");
//        String actualMessageInnerr = Driver.getDriver().switchTo().activeElement().getAttribute("innerHTML");
//        String actualMessageValue = Driver.getDriver().switchTo().activeElement().getAttribute("value");
////        System.out.println("Driver.getDriver().getPageSource() = " + Driver.getDriver().getPageSource());
//        System.out.println("actualMessage = " + actualMessage);
//        System.out.println("actualMessageOut = " + actualMessageOut);
//        System.out.println("actualMessageIn = " + actualMessageIn);
//        System.out.println("actualMessageVal = " + actualMessageVal);
//
//        System.out.println("outer = " + actualMessageOuter);
//        System.out.println("inner = " + actualMessageInnerr);
//        System.out.println("value = " + actualMessageValue);

        assertEquals(expectedFillOut + " message is not seen ", expectedFillOut, actualFillOutMessage);

    }


    @When("the user DOES NOT complete the payment operation as required: Amount field with alphabetical {string}")
    public void the_user_does_not_complete_the_payment_operation_as_required_amount_field_with_alphabetical_characters(String expAlphabetical) {
        select = new Select(payBillsPage.payeeField);
        select.selectByVisibleText("Bank of America");

        BrowserUtils.wait(1);
        select = new Select(payBillsPage.acountField);
        select.selectByVisibleText("Credit Card");

        BrowserUtils.wait(1);
        payBillsPage.dateField.sendKeys("2022-01-19");

        BrowserUtils.wait(1);
        payBillsPage.amountField.sendKeys(expAlphabetical);



        BrowserUtils.wait(1);
        payBillsPage.payBttn.click();

    }


    @When("the user DOES NOT complete the payment operation as required: Amount field with special characters {string}")
    public void the_user_does_not_complete_the_payment_operation_as_required_amount_field_with_special_characters(String expSpecial) {
        select = new Select(payBillsPage.payeeField);
        select.selectByVisibleText("Bank of America");

        BrowserUtils.wait(1);
        select = new Select(payBillsPage.acountField);
        select.selectByVisibleText("Credit Card");

        BrowserUtils.wait(1);
        payBillsPage.dateField.sendKeys("2022-01-19");

        BrowserUtils.wait(1);
        payBillsPage.amountField.sendKeys(expSpecial);



        BrowserUtils.wait(1);
        payBillsPage.payBttn.click();

    }

    @Then("the user should NOT see {string} message")
    public void the_user_should_not_see_message(String expSuccessMessage) {

        System.out.println("payBillsPage.successMessage.getText() = " + payBillsPage.successMessage.getText());
        System.out.println("payBillsPage.successMessage.isDisplayed() = " + payBillsPage.successMessage.isDisplayed());
        assertFalse(payBillsPage.successMessage.isDisplayed());
    }

    @When("the user DOES NOT complete the payment operation as required: Date field with alphabetical characters {string}")
    public void the_user_does_not_complete_the_payment_operation_as_required_date_field_with_alphabetical_characters(String expAlphabeticalDate) {

        select = new Select(payBillsPage.payeeField);
        select.selectByVisibleText("Bank of America");

        BrowserUtils.wait(1);
        select = new Select(payBillsPage.acountField);
        select.selectByVisibleText("Credit Card");

        BrowserUtils.wait(1);
        payBillsPage.amountField.sendKeys("701");

        BrowserUtils.wait(2);
        payBillsPage.dateField.sendKeys(expAlphabeticalDate);

        BrowserUtils.wait(1);
        payBillsPage.payBttn.click();
    }







}
