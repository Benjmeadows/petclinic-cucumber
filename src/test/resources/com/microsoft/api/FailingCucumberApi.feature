@Toast
Feature: Test API Feature
  Author: Ben.Meadows@Microsoft.com
  I want to use this template for my feature file

  Scenario: Test the response from bitbucket.org
    Given I intend to test an API endpoint
    When I set the uri to be 'https://api.bitbucket.org/2.0/users/benjmeadows'
    Then The response code should be 'HTTP/1.1 200 OK'
    And The page output will include 
    	"""
    	"display_name": "Toast"
    	"""