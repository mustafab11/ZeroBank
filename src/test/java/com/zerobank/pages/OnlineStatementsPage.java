package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OnlineStatementsPage extends BasePage {

    public WebElement yearElement(Integer year){
        String yearLocator = "//a[.='"+ year +"']";
        return Driver.getDriver().findElement(By.xpath(yearLocator));
    }

    public List<WebElement> countOfYear(Integer year){
        String countLocator = "//div[@id='os_"+ year +"']//a[contains(text(),'Statement')]";
        return Driver.getDriver().findElements(By.xpath(countLocator));
    }

    public WebElement clickOnStatementElement(String year, String statementName){
        String statementLocator = "//div[@id='os_"+ year +"']//a[contains(text(),'"+statementName+"')]";
        return Driver.getDriver().findElement(By.xpath(statementLocator));
    }
}
