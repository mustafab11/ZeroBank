@regression
Feature: Pay Bills Page

  Background: Verify that Pay Bills Page seen and the functions are working
    Given the user is logged in and sees Account Summary Page
    When the user navigates to "Pay Bills" page
    And the user should see the "Zero - Pay Bills" title


  Scenario Outline: Positive Scenario
    When the user completes the payment operation as required: "<Payee>" "<Account>" "<Amount>" "<Date>" "<Description>"
    Then the user should see "The payment was successfully submitted." message
    Examples:
      | Payee           | Account | Amount | Date       | Description |
      | Bank of America | Savings | 345    | 2022-01-19 | Bilal       |

#
#
#
#    Create 2-3 different branches for differrent user stories simultaneously and practice
#
#

  Scenario: Negative Scenarios --without Amount
    When the user DOES NOT complete the payment operation as required: Without "amount"
    Then the user sees "Please fill out this field." message


  Scenario: Negative Scenarios --without Date
    When the user DOES NOT complete the payment operation as required: Without "date"
    Then the user sees "Please fill out this field." message

   @bug
  Scenario: Negative Scenarios --alphabetical Amount
    When the user DOES NOT complete the payment operation as required: Amount field with alphabetical "asdfdsa"
    Then the user should NOT see "The payment was successfully submitted." message

   @bug
  Scenario: Negative Scenarios --special character Amount
    When the user DOES NOT complete the payment operation as required: Amount field with special characters "^%$$#"
    Then the user should NOT see "The payment was successfully submitted." message


  Scenario: Negative Scenarios --alphabetical Date
    When the user DOES NOT complete the payment operation as required: Date field with alphabetical characters "asdfasd"
    Then the user should NOT see "The payment was successfully submitted." message
