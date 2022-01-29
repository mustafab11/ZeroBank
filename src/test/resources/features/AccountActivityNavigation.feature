@regression
Feature: Navigating to specific accounts in Accounts Activity

  Background:
    Given the user is logged in and sees Account Summary Page

  Scenario: Savings account redirect
    When the user clicks on "Savings" link on the Account Summary page
    Then the user should see the "Zero - Account Activity" title
    And Account dropdown default option is "Savings"


  Scenario: Brokerage account redirect
    When the user clicks on "Brokerage" link on the Account Summary page
    Then the user should see the "Zero - Account Activity" title
    And Account dropdown default option is "Brokerage"


  Scenario: Checking account redirect
    When the user clicks on "Checking" link on the Account Summary page
    Then the user should see the "Zero - Account Activity" title
    And Account dropdown default option is "Checking"


  Scenario: Credit Card account redirect
    When the user clicks on "Credit Card" link on the Account Summary page
    Then the user should see the "Zero - Account Activity" title
    And Account dropdown default option is "Credit Card"


  Scenario: Loan account redirect
    When the user clicks on "Loan" link on the Account Summary page
    Then the user should see the "Zero - Account Activity" title
    And Account dropdown default option is "Loan"

#    And Account drop down should have Loan selected