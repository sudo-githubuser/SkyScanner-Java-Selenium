Feature: Search Flight - Return Journey
  Description: Search flight with return journey and specific dates


Scenario: Search Flight - Return Journey
  Given User visits skyscanner website
  And Selects Flight
  When User enters "From" city
  * Enters "To" city
  * Enters "Departure" date
  * Enters "Return" date
  * Selects "Cabin class"
  * Selects "No. of Passengers"
  * Search flights
  Then Flight results are displayed