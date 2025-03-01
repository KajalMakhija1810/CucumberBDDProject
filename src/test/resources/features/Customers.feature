Feature: Customer
  Background: Steps common for all scenarios
    Given User Launchs  browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And Click on Login
    Then User can view Dashboard

    @Sanity
    Scenario: Adding New Customer
      When User click on customer Menu
      And click on customer Menu Item
      And click on Add new button
      Then user can view Add customer page
      When User enters customer info
      And click on Save button
      Then User can view confirmation message "The new customer has been added successfully."
      And close browser

      @regression
      Scenario: Search Customer by Email
        When User click on customer Menu
        And click on customer Menu Item
        And Enter customer Email
        When Click on search button
        Then User should find Email in the Search table
        And close browser

        @Sanity
        Scenario: Search Customer by Name
          When User click on customer Menu
          And click on customer Menu Item
          And Enter customer FirstName
          And Enter customer LastName
          When Click on search button
          Then User should find Name in the Search table
          And close browser

