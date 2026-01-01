Feature: Login

  @Login
  Scenario Outline: Login User with correct email and password
    Given user launches the application
    When user navigates to Signup Login page
    And User login with correct email "<Email>" and correct password "<Password>"
    Then user "<User>" is logged in

  Examples:
    | User  | Email                   | Password   |
    | Anto  | antomanjooran@gmail.com | 1234       |

  @InvalidLogin
  Scenario Outline: Login User with correct email and password
    Given user launches the application
    When user navigates to Signup Login page
    And User login with correct email "<Email>" and incorrect password "<Password>"
    Then verify error message "Your email or password is incorrect!"

    Examples:
      | Email                   | Password   |
      | antomanjooran@gmail.com | 12345      |