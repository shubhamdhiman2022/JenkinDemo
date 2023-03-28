
Feature: Google naviagte

 @fast
  Scenario: Verify user logged in successfully
   Given naviagte to dummy site
   When User enter username and password and submit
   Then Verify user logged in successfully
   
   
 @fast
  Scenario: Failed Scenario
  Then verify failure 