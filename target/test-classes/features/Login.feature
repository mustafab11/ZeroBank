@regression
Feature: Only authorized users should be able to login to the application

  Background:
    Given the user is on login page

  Scenario Outline: Login with INVALID credentials
    When the user enters the INVALID "<username>" and "<password>"
    And the user clicks the signIn button
    Then the user should see the "Login and/or password are wrong." message
    Examples:
      | username      | password      |
      | username      | wrongPassword |
      | wrongUsername | Password      |
      | wrongUsername | wrongPassword |
      |               | password      |
      | username      |               |

  @login
  Scenario: Login with valid credentials
    When the user enters the valid credentials
    And the user clicks the signIn button
    Then the user should see the "Zero - Account Summary" title