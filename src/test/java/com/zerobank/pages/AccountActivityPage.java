package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountActivityPage extends BasePage{

    public AccountActivityPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "aa_accountId")
    public WebElement accountDropdownSelect;

//    @FindBy(xpath = "(//*[@id='all_transactions_for_account']//tr)[1]")
//    public List<WebElement> transactionColumns;

    @FindBy(css = "table.table-condensed.table-hover>thead>tr>th")
    public List<WebElement> transactionColumns;

    //     //li[.='Find Transactions']
    @FindBy(xpath = "//li[starts-with(@class,'ui-state-default')][.='Find Transactions']")
    public WebElement findTransactionTab;

    @FindBy (xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[1]")
    public List<WebElement> dateColumnAll;

    @FindBy (xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[2]")
    public List<WebElement> descriptionColumnAll;

    @FindBy (xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[3]")
    public List<WebElement> depositColumnAll;

    @FindBy (xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[4]")
    public List<WebElement> withdrawalColumnAll;

    public List<WebElement> returnDateColumnAll(){
        BrowserUtils.waitForPageToLoad(2);
        List<WebElement> dates;
        dates = Driver.getDriver().findElements(By.xpath("(//*[@id='filtered_transactions_for_account']//tr)//td[1]"));
        return dates;
    }

    @FindBy(id = "aa_description")
    public WebElement description;

    @FindBy(id = "aa_fromDate")
    public WebElement fromDate;

    @FindBy(id = "aa_toDate")
    public WebElement toDate;

    @FindBy(id = "aa_fromAmount")
    public WebElement fromAmount;

    @FindBy(id = "aa_toAmount")
    public WebElement toAmount;

    @FindBy(id = "aa_type")
    public WebElement typeSelect;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement findBttn;









}
