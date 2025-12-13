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

And user able to see configuration option only for Active "<DeviceName>"
Then clicked configuration option
And user redirected to Device configuration page

Examples:
|DeviceName|
|ASNI DEMO CAM 03|

@Deviceconfiguration
Scenario Outline: Testing Device configuration for object Detection

And user able to see configuration option only for Active "<DeviceName>"
Then clicked configuration option
And user redirected to Device configuration page
Then user click Add option
And user entered "<starttime>" and "<endtime>"
Then select configuration "<Status>"
And user clicked Add option
Then verify configured time saved in Device Configuration
And user navigated to main dashboard

Examples:
|DeviceName|starttime|endtime|Status|
|ASNI DEMO CAM 03|20:15:10|20:16:10|Enabled|

@Invalidtimeconfiguration
Scenario Outline: Testing invalid time Device configuration

And user able to see configuration option only for Active "<DeviceName>"
Then clicked configuration option
And user redirected to Device configuration page
Then user click Add option
And entered "<starttime>" more then "<endtime>"
Then select configuration "<Status>"
And user clicked Add option
Then It should throw exception Start time cannot be more than End time

Examples:
|DeviceName|starttime|endtime|Status|
|ASNI DEMO CAM 03|20:15:10|20:14:10|Enabled|

@Invalidtimeformat
Scenario Outline: Testing invalid time Device configuration

And user able to see configuration option only for Active "<DeviceName>"
Then clicked configuration option
And user redirected to Device configuration page
Then user click Add option
And user entered alphabet at "<starttime>" and "<endtime>"
Then select configuration "<Status>"
And user clicked Add option
Then It should throw exception Enter proper time format

Examples:
|DeviceName|starttime|endtime|Status|
|ASNI DEMO CAM 03|starttime|endtime|Enabled|

@Mandatorydeviceconfiguration
Scenario Outline: Testing mandatory status Device configuration

And user able to see configuration option only for Active "<DeviceName>"
Then clicked configuration option
And user redirected to Device configuration page
Then user click Add option
And entered "<starttime>" and "<endtime>"
Then selected Add option
And It should throw exception Status is mandatory

Examples:
|DeviceName|starttime|endtime|
|ASNI DEMO CAM 03|20:15:10|20:16:10|

@Blanktimeconfiguration
Scenario Outline: Testing mandatoy time configuration

And user able to see configuration option only for Active "<DeviceName>"
Then clicked configuration option
And user redirected to Device configuration page
Then user click Add option
And user not entered "<starttime>" and "<endtime>"
Then select configuration "<Status>"
And user clicked Add option
And It should throw mandatory exception start time and end time are required

Examples:
|DeviceName|starttime|endtime|Status|
|ASNI DEMO CAM 03|||Enabled|

@Disableconfiguration
Scenario Outline: Testing Device configuration for object Detection

And user able to see configuration option only for Active "<DeviceName>"
Then clicked configuration option
And user redirected to Device configuration page
Then user click Add option
And user not entered "<starttime>" and "<endtime>"
Then select configuration "<Status>"
And user clicked Add option
Then configured time saved in Device Configuration page
And user navigated to main dashboard

Examples:
|DeviceName|starttime|endtime|Status|
|ASNI DEMO CAM 03|20:15:10|20:16:10|Disabled|
