Feature: Add items to Vodafone eShop cart

  Scenario: Add 2 products from homepage and 1 from search
    Given User logged to the website
    When user selects 2 products from home page
    And user adds 1 product from search
    Then user opens the cart to check order
