Feature: User Management
  The system allows the management of platform users (Administrators and Guests).

  Scenario: Create a new Guest user
    Given the user management system is ready
    When I create a guest with username "yassine2026" and email "yassine@example.com"
    Then the guest "yassine2026" should be successfully recorded