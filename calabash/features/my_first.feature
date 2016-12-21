Feature: Sign-in

  Scenario: As a valid user I can sign in my Yandex mail
    Given I see "Email account"
    Then I enter text "calabash.cat@yandex.com" into field with id "account_email"
    Given I see "NEXT"
    Then I press view with id "next"
    Then I press view with id "NoResourceEntry-2"
    Then I wait
    Given I see "Sign in"
    Then I enter text "calabash" into field with id "regular_password"
    Then I press view with id "next"
    Given I see "Incoming server settings"
    Then I scroll up
    Then I scroll down
    Then I select spinner by id "account_security_type" and touch "SSL/TLS (Accept all certificates"
    Then I clear input field with id "account_server"
    Then I enter text "imap.yandex.com.tr" into field with id "account_server"
    Then I hide keyboard
    Then I clear input field with id "account_port"
    Then I enter text "993" into field with id "account_port"
    Then I hide keyboard
    Then I press view with id "next"
    Given I see "CANCEL"
    Then I wait to see "Outgoing server settings"
    Then I clear input field with id "account_server"
    Then I enter text "smtp.yandex.com.tr" into field with id "account_server"
    Then I hide keyboard
    Then I clear input field with id "account_port"
    Then I enter text "465" into field with id "account_port"
    Then I hide keyboard 
    Then I select spinner by id "account_security_type" and touch "SSL/TLS (Accept all certificates)"
    Then I press view with id "next"
    Then I wait to see "Account options"
    Then I press view with id "next"
    Then I wait to see "Your account is set up and email is on its way"
    Then I press view with id "next"
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait
    Then I wait

