package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PayeePage extends BasePage {

    @FindBy(id = "np_new_payee_name")
    public WebElement payeeName;

    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddress;

    @FindBy(id = "np_new_payee_account")
    public WebElement payeeAccount;

    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetails;

    @FindBy(id = "add_new_payee")
    public WebElement payeeAddBttn;

    @FindBy(id = "alert_content")
    public WebElement successAlert;


}
