Feature: Testing Device Order Functionality

Background: Login Page
Given user in on login page
And entered "Akash_QA1" and "root@1234"
Then clicks on sign in button
And User click menu option
Then select Device order menu
And user redirected to Device order tab


@ValidcaseDeviceOrder
Scenario Outline: Provide new device details and order a device

Then user enter "Camera 06"
And click Device Type drop down and select the device type
Then click Submit button

@SameDeviceName
Scenario Outline: Testing negative case by providing same device name

And user enter the exsisting "Camera 04"
Then user selected Device Type
And user clicked submit button
Then Device already exsist exception should be displayed

@BlankDeviceName
Scenario Outline: Testing blank device name will be allowed or not in Device Order

And user click Device Type Drop down and select Device type
Then Submit button opacity should be "0.6"

@DeselectingDeviceType
Scenario Outline: Testing Device order without selecting Device Type

And user enter "CameraTesting16"
Then User not selected Device Type and Submit button opacity should be "0.6"
