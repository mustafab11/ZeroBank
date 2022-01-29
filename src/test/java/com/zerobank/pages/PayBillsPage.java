package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PayBillsPage extends BasePage {


    @FindBy(id = "sp_payee")
    public WebElement payeeField;

    @FindBy(id = "sp_account")
    public WebElement acountField;

    @FindBy(id = "sp_amount")
    public WebElement amountField;

    @FindBy(id = "sp_date")
    public WebElement dateField;

    @FindBy(id = "sp_description")
    public WebElement descriptionField;

    @FindBy(id = "pay_saved_payees")
    public WebElement payBttn;

    @FindBy(xpath = "//*[@id='alert_content']")
    public WebElement successMessage;

    //Purchase foreign currency tab
    @FindBy(id = "pc_currency")
    public WebElement currencySelect;

    @FindBy(id = "pc_amount")
    public WebElement amountFieldInPurchaseTab;

    @FindBy(id = "pc_calculate_costs")
    public WebElement calculateCostsBttn;




}
