Feature: Book a Flight
Scenario: Search flights on MakeMyTrip

Given User is on MakeMyTrip page.
When User navigate to Flights page.
And User selects source "From Source" and destination "To Destination" for the flight with date of journey.
Then Book flight available on the journey date.	