@facebook
Feature: facebook login

  Scenario Outline: log in facebbok
    Given open the facebbok url
    When navigated to home page
    Then enter user name "<username>" and Passcode "<password>"
    Then it should show the error message

    Examples: 
      | username             | password |
      | anurag kumar cgshhsg | pass1234 |