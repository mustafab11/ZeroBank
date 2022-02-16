package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "searchTerm")
    public WebElement searchBox;


    public WebElement navigateToPage(String page){
        String xPath = "//li/a[.='"+page+"']";
        return Driver.getDriver().findElement(By.xpath(xPath));
    }

    public void navigateToTabOfPage( String page, String tab){
        String pageLocator = "//li/a[.='"+page+"']";
        String tabLocator = "//li[starts-with(@class,'ui-state-default')][.='" + tab + "']";
        BrowserUtils.waitForPageToLoad(2);
        Driver.getDriver().findElement(By.xpath(pageLocator)).click();
        BrowserUtils.waitForPageToLoad(1);
        Driver.getDriver().findElement(By.xpath(tabLocator)).click();
    }


    @FindBy(xpath = "//a[.='Logout']")
    public WebElement logoutBttn;




}
