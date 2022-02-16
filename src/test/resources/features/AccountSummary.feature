@regression
Feature: Account Summary page

  Scenario: Verify that Account Summary Page is seen
    Given the user is logged in and sees Account Summary Page
    Then the Account Summary Page should have the following account types
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |
    And "Credit Accounts" type should have the following columns
      | Account     |
      | Credit Card |
      | Balance     |
    And "Investment Accounts" type should have the following columns
      | Account     |
      | Balance     |