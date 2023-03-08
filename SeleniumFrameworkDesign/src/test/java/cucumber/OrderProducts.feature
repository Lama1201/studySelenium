@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of Order products
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | name            | password   | productName     |
      | faker@email.com | Password1@ | adidas original |
      
