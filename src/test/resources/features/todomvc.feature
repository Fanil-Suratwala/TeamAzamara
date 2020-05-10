Feature: TODO MVC Functional Test cases
      
     # Our Scenarios will begin from here. 
		  @test1
      Scenario: Verify Toggle All Functionality
   		Given I launch "http://www.todomvc.com/examples/backbone/" page
			When I enter "10" entries
			Then I should see the count "10"
			When I click Toggle All CTA
			Then I should see all entries Striked out
			And I should see the count "0"
			Then I should not see entries
			And I clear completed
			
			@test1
		  Scenario: Verify Toggle All Functionality2
		  Given I launch "http://www.todomvc.com/examples/backbone/" page
   		When I enter "10" entries
   		And I navigate to Active tab
   		Then I should see all entries
   		And I should see the count "10"
   		When I navigate to Completed tab
   		Then I should see the count "10"
   		And I should not see entries
   		
   		@test1
   		Scenario: Verify Completed Filter tab
		  Given I launch "http://www.todomvc.com/examples/backbone/" page
   		When I enter "10" entries
   		And I navigate to Completed tab
   		Then I should not see entries
   		And I should see the count "10"
   		When I navigate to Active tab
   		Then I should see the count "10"
   		And I should see all entries
   		
   		@test1
   		Scenario: Verify Completed Filter tab
		  Given I launch "http://www.todomvc.com/examples/backbone/" page
   		When I enter "50" entries
   		Then I verify every prime element is striked out of "50" entries
   			
   		@test1
   		Scenario: Verify editable entry
		  Given I launch "http://www.todomvc.com/examples/backbone/" page
   		When I enter "1" entries
   		Then I verify entry is editable
   		
			@test1
   		Scenario: Verify Destroy CTA
		  Given I launch "http://www.todomvc.com/examples/backbone/" page
   		When I enter "2" entries
   		Then I verify destroy CTA
   		
   		@test1
      Scenario: Verify we can add 100 entries
   		Given I launch "http://www.todomvc.com/examples/backbone/" page
			When I enter "50" entries
			Then I should see the count "50"

			@test1
      Scenario: Verify spaces in text
   		Given I launch "http://www.todomvc.com/examples/backbone/" page
			When I enter "test             2345" text
			Then I should see "test 2345" text
			
			@test1
			Scenario: Verify length of todo text
			Given I launch "http://www.todomvc.com/examples/backbone/" page
			When I enter text for "50" seconds and verify
			
		  @test1
      Scenario: Verify Links should open on same page
   		Given I launch "http://www.todomvc.com/examples/backbone/" page
			When I click the link "TodoMVC"
			Then I should see page "TodoMVC" on same tab
			When I navigate to Back
			And  I click the link "let us know"
			Then I should see page "Issues" on same tab
			When I navigate to Back
			And  I click the link "Addy Osmani" on new tab
			Then I should see page "addyosmani" on new tab
			When I click the link "Backbone.js" for different window
			Then I should see page "Backbone.js" on different window
			And I quit Browser
			