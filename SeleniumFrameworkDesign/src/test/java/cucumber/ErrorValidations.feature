
@tag
Feature: Error Validations
  I want to use this template for my feature file
  

 @ErrorValidation
  Scenario Outline: Login Error
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name            | password   |
      | faker@email.com | Password1@1 |
