Feature: Hotel Management
  As an Administrator
  I want to manage hotels in the platform

  Scenario: Create a new hotel
    Given I am logged in as an administrator
    When I create a hotel named "Uevora Palace" in "Evora"
    Then the hotel "Uevora Palace" should be available in the system