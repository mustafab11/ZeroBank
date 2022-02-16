@regression
Feature: Account Activity Page

@bug
  Scenario: Verify that Account Activity Page is seen
    Given the user is logged in and sees Account Summary Page
    When the user navigates to "Account Activity" page
    Then the user should see the "Zero - Account Activity" title
    And Account dropdown default option is "Savings"
    And Account dropdown has the following options
      | Savings     |
      | Checking    |
      | Loan        |
      | Credit Card |
      | Brokerage   |
    And Transactions Table has the following columns
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
