Feature: Sample Login Journey Test

    Background: User should be able to access the site
        Given I navigate to "login" page
        Then I should see the following on the login page
            | text                                                                                                                                                                                  |
            | Login Page                                                                                                                                                                            |
            | Username                                                                                                                                                                              |
            | Password                                                                                                                                                                              |
            | Login                                                                                                                                                                                 |
            | This is where you can log into the secure area. Enter tomsmith for the username and SuperSecretPassword! for the password. If the information is wrong you should see error messages. |

    @positive
    Scenario: A user with valid credentials can login successfully
        When I enter username as "tomsmith"
        And I enter password as "SuperSecretPassword!"
        And I click on "Login" button
        Then I should see the  "You logged into a secure area!" message

    @negative
    Scenario Outline: A user with invalid username or password should get the appropriate error message
        When I enter username as "<username>"
        And I enter password as "<password>"
        And I click on "Login" button
        Then I should see the  "<message>" message

        Examples:
            | username    | password             | message                   |
            | tomsmithery | SuperSecretPassword! | Your username is invalid! |
            | tomsmith    | SuperSecret          | Your password is invalid! |

    @positive
    Scenario: A user should be able to logout successfully
        When I enter username as "tomsmith"
        And I enter password as "SuperSecretPassword!"
        And I click on "Login" button
        And I click on "Logout" link
        Then I should see the  "You logged out of the secure area!" message

    @negative
    Scenario: A user should not be able to access the inbox without logging in
        When I navigate to "secure" page
        Then I should see the  "You must login to view the secure area!" message

