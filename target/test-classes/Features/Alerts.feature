Feature: Alert Dashboard and Video Analysis Verification
  This feature validates the Alert Dashboard and Video Analysis functionality,
  ensuring alerts, playback, and system responses work as expected.

  Background:
  Given user is on login page
  And entered "Akash_QA1" and "root@1234"
  Then clicked signin button
  And clicked All Alerts tabs

@TC_01
 Scenario Outline: Verify alert dashboard page
 When user logged in it should display Alert Monitoring Dashboard title
 And All Alerts tabs should display Alert history

@TC_02
Scenario Outline: Verify the search functionality in the alert dashboard
When user enters a valid "<Alert ID>" in the search bar
Then the system should display the corresponding alert details "<Alert ID>"

Examples:
|Alert ID|
|657a79b9-11d9-491b-9f2b-636581b7e717|

@TC_03
Scenario Outline: Verify the search functionality in the alert dashboard
When user enters a Invalid "<Alert ID>" in the search bar
Then the system should display message No Alerts Found for provided "<Alert ID>"

Examples:
|Alert ID|
|657a79b9-11d9-491b-9f2b-636581b7e717|

@TC_04
Scenario Outline: Verify the alert list by selecting valid date range
When user selects a valid date range from "<FromDate>" to "<ToDate>"
Then the system should display the alerts within the selected date range

Examples:
|FromDate|ToDate|
|03-12-2025|03-12-2025|

@TC_05  
Scenario Outline: Verify the alert list by selecting invalid date range
When user selects an invalid date range from "<FromDate>" to "<ToDate>"
Then the system should display no alerts for the selected date range

Examples:
|FromDate|ToDate|
|18-11-2025|19-11-2025|

@TC_06  
Scenario Outline: Verify the alert list by selecting severity
When user selects a severity of alert "<severitylevel>"
Then the system should display the alerts for the selected severity level "<severitylevel>"

Examples:
|severitylevel|
|Medium|

@TC_07
Scenario Outline: Devices that capture video reference for raised alerts
When user enters "<alertid>" and clicked View Devices option
Then the system should display the devices that are invovled in raised alert

Examples:
|alertid|
|657a79b9-11d9-491b-9f2b-636581b7e717|

@TC_08
Scenario Outline: Verify the specific alert details
When user clicked specific "<alertid>"
Then user navigate to the specific alert "<alertid>" details page
And Details section should display the following information AlertID, Severity, Alertmessage, Status, Timestamp
Then View Details & Actions button should be clickable and it should display Device Name, Inference Analysis and Status

Examples:
|alertid|
|657a79b9-11d9-491b-9f2b-636581b7e717|