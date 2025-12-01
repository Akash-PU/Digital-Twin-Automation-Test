Feature: Testing Device list page

Background:
Given user is on login page
And provided "Akash_QA1" and "root@1234"
Then clicked signin button
And select menu option
Then user select Device list option
And redirected to Device List page

@validscenario
Scenario Outline: Testing list of Devices

And user able to see list of Devices
Then user able to see Active Devices

@Configurationoption
Scenario Outline: Testing Device configuration option

And user able to see configuration option only for Active Devices
Then clicked configuration option
And user redirected to Device configuration page

@Deviceconfiguration
Scenario Outline: Testing Device configuration for object Detection

And user click settings option for Active Device
Then user click Add option
And user entered "<starttime>" and "<endtime>"
Then select configuration status Enable
And user clicked Add option
Then verify configured time saved in Device Configuration
And user navigated to main dashboard

Examples:
|starttime|endtime|
|20:15:10|20:16:10|

@Invalidtimeconfiguration
Scenario Outline: Testing invalid time Device configuration

And user click settings option of Active Device
Then click Add option
And entered "<starttime>" more then "<endtime>"
Then selected Enable status of configuration
And clicked Add option
Then It should throw exception Start time cannot be more than End time

Examples:
|starttime|endtime|
|20:17:10|20:16:10|

@Invalidtimeformat
Scenario Outline: Testing invalid time Device configuration

And user selected configuration option
Then user select Add option
And user entered alphabet at "<starttime>" and "<endtime>"
Then selected Enable status at configuration
And user selected Add option
Then It should throw exception End Time cannot be earlier than Start Time

Examples:
|starttime|endtime|
|starttime|endtime|

@Mandatorydeviceconfiguration
Scenario Outline: Testing mandatory status Device configuration

And selected configuration option
Then select Add option
And entered "<starttime>" and "<endtime>"
Then selected Add option
And It should throw exception Status is mandatory

Examples:
|starttime|endtime|
|20:15:10|20:16:10|

@Blanktimeconfiguration
Scenario Outline: Testing mandatoy time configuration

And user clicked configuration setting option
Then user clicked Add button
And user not entered "<starttime>" and "<endtime>"
And select Device status Enable
Then user click Save button
And It should throw mandatory exception start time and end time are required

Examples:
|starttime|endtime|
|||

@Disableconfiguration
Scenario Outline: Testing Device configuration for object Detection

And Configuration settings button clicked
Then user click Add button
And user provide "<starttime>" and "<endtime>"
Then select configuration status Disable
And user clicked Save button
Then configured time saved in Device Configuration page
And user redirected back to main dashboard

Examples:
|starttime|endtime|
|21:10:10|21:11:10|
