package com.zerobank.stepdefnitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.OnlineStatementsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.FileUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class OnlineStatementsStepDefs {

    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
    OnlineStatementsPage onlineStatementsPage = new OnlineStatementsPage();
    private File mostRecentFile;
    boolean fileFound;

    @Given("I am on the {} page")
    public void i_am_on_the_online_statements_page(String page) {
        accountSummaryPage.navigateToPage(page).click();
    }


    @Given("the user selects the Recent Statements Year {int}")
    public void the_user_selects_the_recent_statements_year(Integer year) {
        onlineStatementsPage.yearElement(year).click();
    }

    @Then("{int} statements should be displayed for that {int}")
    public void statements_should_be_displayed_for_that(Integer expectedCount, Integer year) {
        List<String> actualCountsText = BrowserUtils.getElementsText(onlineStatementsPage.countOfYear(year));
        System.out.println("countsText = " + actualCountsText);
        Assert.assertEquals(expectedCount, (Integer) actualCountsText.size());

    }

    @When("the user clicks on statement {string}")
    public void the_user_clicks_on_statement(String statement) {
        String year = 20 + statement.substring(16, 18);
        onlineStatementsPage.clickOnStatementElement(year, statement).click();
        BrowserUtils.wait(5);
    }


    @Then("the downloaded file name should contain {string}")
    public void the_downloaded_file_name_should_contain(String expectedName) {

        BrowserUtils.wait(1);
        String downloadsFolder = System.getProperty("user.home") + "/Downloads/";
        System.out.println("downloadsFolder = " + downloadsFolder);
        mostRecentFile = FileUtility.getMostRecentFile(downloadsFolder);
        String actualName = mostRecentFile.getName();
        System.out.println("actualName = " + actualName);
        System.out.println("expectedName = " + expectedName);
        assertTrue(actualName.contains(expectedName));
        fileFound = true;


    }


    @Then("the file type should be pdf")
    public void the_file_type_should_be_pdf() {
        assertTrue(mostRecentFile.getName().endsWith(".pdf"));
        if (fileFound) {
            mostRecentFile.delete();
        }

    }


}
