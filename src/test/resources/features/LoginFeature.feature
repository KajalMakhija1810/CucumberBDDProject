Feature: Login

  @Sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given User Launchs  browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page Title should be "nopCommerce demo store. Login"
    And close browser

    @regression
    Scenario Outline: Successful Login Using DDT
      Given User Launchs  browser
      When User opens URL "http://admin-demo.nopcommerce.com/login"
      And User enters Email as "<email>" and Password as "<password>"
      And Click on Login
      Then Page Title should be "Dashboard / nopCommerce administration"
      When User click on Log out link
      Then Page Title should be "nopCommerce demo store. Login"
      And close browser

      Examples:
      |email|password|
      |admin@yourstore.com|admin|


