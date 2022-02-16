package com.zerobank.stepdefnitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AccountActivityStepDefs {

    LoginPage loginPage = new LoginPage();
    AccountActivityPage accountActivityPage = new AccountActivityPage();

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String page) {
        accountActivityPage.navigateToPage(page).click();

    }


    @Then("Account dropdown default option is {string}")
    public void account_dropdown_default_option_is(String expectedFirstOption) {
        Select select = new Select(accountActivityPage.accountDropdownSelect);
        String actualFirstOption = select.getFirstSelectedOption().getText();
        assertEquals("first selected option is not " + expectedFirstOption, expectedFirstOption, actualFirstOption);

    }


    @Then("Account dropdown has the following options")
    public void account_dropdown_has_the_following_options(List<String> expectedDropdownOptions) {
        Select select = new Select(accountActivityPage.accountDropdownSelect);
        List<WebElement> actualDropdownElements = select.getOptions();
        List<String> actualDropdownStrings = new ArrayList<>();

        for (WebElement eachElement : actualDropdownElements) {
            actualDropdownStrings.add(eachElement.getText());
        }
        System.out.println("expectedDropdownOptions = " + expectedDropdownOptions);
        System.out.println("actualDropdownStrings = " + actualDropdownStrings);


        assertEquals("Options do not match", expectedDropdownOptions, actualDropdownStrings);

    }

    @Then("Transactions Table has the following columns")
    public void transactions_table_has_the_following_columns(List<String> expectedTransactionColumns) {

        List<WebElement> actualTransactionColumnElements = accountActivityPage.transactionColumns;

        List<String> actualTransactionClmnStrings = new ArrayList<>();

        for (WebElement eacElement : actualTransactionColumnElements) {
            actualTransactionClmnStrings.add(eacElement.getText());
        }

        System.out.println("expectedTransactionColumns = " + expectedTransactionColumns);
        System.out.println("actualTransactionClmnStrings = " + actualTransactionClmnStrings);
        System.out.println("actualTransactionClmnStrings.size() = " + actualTransactionClmnStrings.size());
        assertEquals(expectedTransactionColumns, actualTransactionClmnStrings);

    }


    //--------find transactions

    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_find_transactions_tab() {

        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.userLogin.sendKeys(ConfigurationReader.getProperty("username"));
        loginPage.userPassword.sendKeys(ConfigurationReader.getProperty("password"));

        loginPage.sign_Login_Bttn.click();

        accountActivityPage.navigateToPage("Account Activity").click();

        accountActivityPage.findTransactionTab.click();
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {
        System.out.println("the user enters date range from  " + fromDate + " - " + toDate);
        BrowserUtils.wait(1);
        accountActivityPage.fromDate.clear();
        accountActivityPage.fromDate.sendKeys(fromDate);
        BrowserUtils.wait(1);
        accountActivityPage.toDate.clear();
        accountActivityPage.toDate.sendKeys(toDate);
        BrowserUtils.wait(1);
    }

    @When("clicks search")
    public void clicks_search() {
        System.out.println("click search");
        BrowserUtils.waitForClickability(accountActivityPage.findBttn, 2);
        accountActivityPage.findBttn.click();
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String fromDate, String toDate) {
        System.out.println("results table should only show transactions dates between " + fromDate + " - " + toDate);
        BrowserUtils.waitForPageToLoad(3);
        BrowserUtils.wait(1);
        List<String> dateStr = BrowserUtils.getElementsText(accountActivityPage.dateColumnAll);
        System.out.println("BETWEEN ...... dateStr.size() = " + dateStr.size());


        Integer fromDateInt = Integer.parseInt(fromDate.replaceAll("-", ""));
        Integer toDateInt = Integer.parseInt(toDate.replaceAll("-", ""));

        List<Integer> dateInteger = new ArrayList<>();

        for (int i = 0; i < dateStr.size(); i++) {
            String dateDummy = dateStr.get(i).replaceAll("-", "");
            dateInteger.add(Integer.parseInt(dateDummy));
        }

        System.out.println("...............dateInteger = " + dateInteger);

        boolean check = true;

        for (int i = 0; i < dateInteger.size(); i++) {
            //        01                     06 05 01            06
            if (!(fromDateInt <= dateInteger.get(i) && dateInteger.get(i) <= toDateInt)) {
                check = false;
                System.out.println("dateInteger.get(i) = " + dateInteger.get(i));
                break;
            }
        }

        assertTrue(check);
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        System.out.println("........the results should be sorted by most recent date");

        List<Integer> dateInteger = new ArrayList<>();

        List<String> dateString = BrowserUtils.getElementsText(accountActivityPage.dateColumnAll);
        System.out.println("IN SORT..... dateString.size() = " + dateString.size());
        for (int i = 0; i < dateString.size(); i++) {
            String dateDummy = dateString.get(i).replaceAll("-", "");
            dateInteger.add(i, Integer.parseInt(dateDummy));
        }

        List<Integer> dateListExpected = new ArrayList<>();
        dateListExpected.addAll(dateInteger);
//        System.out.println("dateExpected = " + dateListExpected);
//        System.out.println("dateInteger = " + dateInteger);
        Collections.sort(dateListExpected);
        Collections.reverse(dateListExpected);
        System.out.println("dateExpected = " + dateListExpected);

        assertEquals(dateListExpected, dateInteger);
//        BrowserUtils.wait(1);
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String dateExpected) {
        System.out.println("the results table should only not contain transactions dated " + dateExpected);

        List<Integer> dateInteger = new ArrayList<>();
        List<String> dateString = BrowserUtils.getElementsText(accountActivityPage.dateColumnAll);
        System.out.println("LAST STEP......... dateString.size() = " + dateString.size());
        for (int i = 0; i < dateString.size(); i++) {
            String dateDummy = dateString.get(i).replaceAll("-", "");
            dateInteger.add(i, Integer.parseInt(dateDummy));
        }

        System.out.println("dateInteger = " + dateInteger);
        assertFalse(dateInteger.contains(dateExpected));
    }

    //-------------------------------------------
    @When("the user enters description {string}")
    public void the_user_enters_description(String searchItem) {
        accountActivityPage.description.clear();
        accountActivityPage.description.sendKeys(searchItem);
        BrowserUtils.waitForPageToLoad(2);

    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String expectedSearchItem) {

        BrowserUtils.waitForPageToLoad(2);
        BrowserUtils.wait(2);
        List<String> actualSearchItems = BrowserUtils.getElementsText(accountActivityPage.descriptionColumnAll);
        List<WebElement> descriptionColumnAll = accountActivityPage.descriptionColumnAll;
        System.out.println("descriptionColumnAll.size() = " + descriptionColumnAll.size());

        System.out.println("actualSearchItems.size() ..containing = " + actualSearchItems.size());
        System.out.println("actualSearchItems = " + actualSearchItems);
        if (actualSearchItems.size() > 0) {
            for (String description : actualSearchItems) {
                System.out.println("description = " + description);
                assertTrue(description.contains(expectedSearchItem));
            }
        }else {
            assertTrue("Search w/ lowercase do not work....", actualSearchItems.size() > 0);
        }
    }


    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String expectedSearchItem) {
        BrowserUtils.waitForPageToLoad(3);
        List<String> actualSearchItems = BrowserUtils.getElementsText(accountActivityPage.descriptionColumnAll);
        System.out.println("actualSearchItems.size() NOT CONTAINING = " + actualSearchItems.size());

        for (String description : actualSearchItems) {
            assertFalse(description.contains(expectedSearchItem));

        }
    }

//---------------------------------------------------------------------------------

    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_deposit() {
        System.out.println("results table should show at least one result under Deposit");
        BrowserUtils.waitForPageToLoad(3);
        List<String> elementsTextD = BrowserUtils.getElementsText(accountActivityPage.depositColumnAll);
        int count = 0;
        for (String eachElement : elementsTextD) {
            System.out.println("eachElementD = " + eachElement);
            if (!eachElement.isEmpty()) {
                count++;
            }
        }
        System.out.println("count D = " + count);
        assertTrue("All items are empty", count > 0);
    }

    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_withdrawal() {
        System.out.println("results table should show at least one result under Withdrawal");
        BrowserUtils.waitForPageToLoad(3);
        List<String> elementsTextW = BrowserUtils.getElementsText(accountActivityPage.withdrawalColumnAll);

        int count = 0;
        for (String eachElement : elementsTextW) {
            System.out.println("eachElementW = " + eachElement);
            if (!eachElement.isEmpty()) {
                count++;
            }
        }
        System.out.println("count W = " + count);
        assertTrue("All items are empty", count > 0);

//        System.out.println(elementsTextW.size() + "   elementsTextW = " + elementsTextW);
//        assertTrue("There should be at least 1 result in Withdrawal column",elementsTextW.size()>=1);
    }

    @When("user selects type {string}")
    public void user_selects_type(String type) {
        System.out.println("user selects type " + type);
        Select selectType = new Select(accountActivityPage.typeSelect);
        selectType.selectByVisibleText(type);
        accountActivityPage.findBttn.click();
        BrowserUtils.wait(1);
    }

    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_withdrawal() {
        System.out.println("results table should show no result under Withdrawal");
        BrowserUtils.waitForPageToLoad(3);
        List<String> elementsTextW = BrowserUtils.getElementsText(accountActivityPage.withdrawalColumnAll);
        System.out.println(elementsTextW.size() + " .... NoelementsTextW = " + elementsTextW);
        for (String eachElement : elementsTextW) {
            System.out.println("NO element under W --- eachElementW = " + eachElement);
            assertTrue("is not empty", eachElement.isEmpty());
        }
        //assertTrue("There should be at least 1 result in Withdrawal column",elementsTextW.size()>=1);
    }

    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_deposit() {
        System.out.println("results table should show no result under Deposit");
        BrowserUtils.waitForPageToLoad(3);
        List<String> elementsTextD = BrowserUtils.getElementsText(accountActivityPage.depositColumnAll);
        System.out.println(elementsTextD.size() + "    elementsTextD = " + elementsTextD);

        for (String eachElement : elementsTextD) {
            System.out.println("NO element under D --- eachElementD = " + eachElement);
            assertTrue("is not empty", eachElement.isEmpty());
        }

        //assertTrue("There should be at least 1 result in Deposit column",elementsTextD.size()>=1);
    }


}
