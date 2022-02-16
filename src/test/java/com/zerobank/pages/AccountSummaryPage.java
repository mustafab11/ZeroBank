package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSummaryPage extends BasePage {


    public WebElement findAccountTypes(String acountType) {
        String xPath = "//*[.='" + acountType + "']";
        return Driver.getDriver().findElement(By.xpath(xPath));
    }

    public WebElement findColumnsOfType(String acountType, String column) {
        //       //*[.='Credit Accounts']/following-sibling::*[1]//tr/th[.='Credit Card']

        String xPath = "//*[.='" + acountType + "']/following-sibling::*[1]//tr/th[.='" + column + "']";
        return Driver.getDriver().findElement(By.xpath(xPath));
    }

    public WebElement navigateFromLinkOfSummaryToActivity(String expectedLinkOnSummary){
        String expectedElementLocator = "//a[.='"+expectedLinkOnSummary+"']";
        return Driver.getDriver().findElement(By.xpath(expectedElementLocator));
    }


}
