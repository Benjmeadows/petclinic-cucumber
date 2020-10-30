#Author:Ben.Meadows@Microsoft.com

@WebTestScenarios @TestID:109
Feature: Test adding a new owner and a new pet for that owner
  This feature will use features in the petclinic web app to test a workflow that adds an owner and a pet to
  the clinic.

# Remember: Keep it simple. Focus on the workflow. 'Look and Feel' should be delegated to manual inspection
  @RegressionWebTestScenario
  Scenario Outline: Add a new owner
# Home Page Check
    Given I am on the home page of the petclinic web app
    And I can see the welcome message on the home screen
    When I click the Find Owners tab
    Then I should see the Last Name field
    And I should see the Find Owner button
# Begin Owner Page Check
	  When I click the Add Owner button
		Then I should see the First Name field
		And I should see the Last Name field
		And I should see the Address field
    And I should see the City field	
    And I should see the Telephone field
    And I should see the Add Owner button
		When I fill out the First Name field with "<FirstName>"
		And I fill out the Last Name field with "<LastName>"
    And I fill out the Address field with "<Address>"
    And I fill out the City field with "<City>"
    And I fill out the Telephone field with "<Telephone>"
    And I click the Add Owner button on the New Owner page
# Successful Owner Information screen navigation & input check
		Then I should be presented with the Owner Information screen
# Successful Add New Pet screen navigation and input check
    When I click the Add New Pet button
    Then I should be presented with the New Pet screen
    When I fill out the "<PetName>"
    And I fill out the "<PetBirthDay>" in the calendar
		And I select the Pet type "<Type>" from the drop down
		And I click the Add Pet button
# Successful Owner Information with newly added Pet navigation check
		Then I should be presented with the Owner Information screen
# Error Tab Check
		When I click the Error Tab
		Then I should be on the Error Page
		
		Examples:
		|FirstName|LastName|Address|City|Telephone|PetName|PetBirthDay|Type|
		|Ben|Meadows|1234 Test Street|TestCity|9199999999|Patrick|2008-10-01|dog|
		#|Janice|Choi|4567 Cool Street|WestCity|2139999999|Polly|2009-11-02|bird|
		#|Dennis|Bass|8910 Coast Street|CoastCity|7049999999|Sam|2010-10-07|cat|