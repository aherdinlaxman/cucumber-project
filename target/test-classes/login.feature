Feature: Login feature


  Scenario Outline: User should be able to login with valid credentials
  Given User is on login page
    When User enters valid "<username>" and "<password>"
    And User clicks on login button
    Then User should be able to login successfully

    Examples:
      |  username             |password    |
      |standard_user          |secret_sauce|
      |problem_user           |secret_sauce|
      |performance_glitch_user|secret_sauce|

  Scenario Outline: User should not be able to login with Invalid credentials
    Given User is on login page
    When User enters valid "<username>" and "<password>"
    And User clicks on login button
    Then User should get error message of "Epic sadface: Username and password do not match any user in this service"

    Examples:
      |  username             |password    |
      |steve Austin           |secret_sauce|
      |Invalid user           |InvalidPassword|


  Scenario: User should not be able to login with Locked user credentials credentials
    Given User is on login page
    When User enters valid "locked_out_user" and "secret_sauce"
    And User clicks on login button
    Then User should get Locked error message of "Epic sadface: Sorry, this user has been locked out."

