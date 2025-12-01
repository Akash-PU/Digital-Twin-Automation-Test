Feature: feature to test login funtionality

Background: Login Page
Given user in on login page

@Validcredentials
Scenario Outline: Check login is successful with valid credentials

When user enters valid credentials <username> and <password>
And clicks on sign in button
Then user is navigated to main Dashboard
And user click open menu option
When user click logout option
Then user navigated back to Login page

Examples:
|username|password|
|Akash_QA1|root@1234|

@InvalidUsername
Scenario Outline: Check login is unsuccessful with Invalid username

When user enters Invalid <username> and <password>
And clicks on sign in button
Then Throwing exception Invalid username

Examples:
|username|password|
|Akash|root@1234|

@InvalidPassword
Scenario Outline: Check login is unsuccessful with Invalid password

When user enters <username> and Invalid <password>
And clicks on sign in button
Then Throwing exception Invalid password

Examples:
|username|password|
|Akash_QA1|root|

@EmptyCredentials
Scenario Outline: Check login is unsuccessful with empty credentials

When user enters empty <username> and empty <password>
And clicks on sign in button
Then Throwing exception Username is required and Password is required

Examples:
|username|password|
|	|	|

@UsernamelessRange
Scenario Outline: Check range of the username

When user enter less than 3 characters <username> and valid <password>
And clicks on sign in button
Then Throwing exception Username must be at least 3 characters

Examples:
|username|password|
|ab|root@1234|

@PasswordlessRange
Scenario Outline: Check range of the password

When user enter valid <username> and less than 6 characters in <password>
And clicks on sign in button
Then Throw exception Password must be at least 6 characters

Examples:
|username|password|
|Akash_QA1|root|

@UsernamemoreRange
Scenario Outline: Check range of the username

When user enter more than 20 characters in <username> and valid <password>
And clicks on sign in button
Then Throw exception Username must be no more than 20 characters

Examples:
|username|password|
|abcdefghijklmnopqrstuv|root@1234|

@PasswordmoreRange
Scenario Outline: Check range of the password

When user enter valid <username> and more than 24 characters <password> 
And clicks on sign in button
Then Throw password must be more than 24 characters exception

Examples:
|username|password|
|Akash_QA1|abcdefghijklmnopqrstuvwxyzabcdefgh |

@Casesensitivitycheck
Scenario Outline: Check the case senstivity of username

When user enters not casesensitive <username> and valid <password>
And clicks on sign in button
Then Throwing exception Invalid casesensitive username

Examples:
|username|password|
|akash_qa1|root@1234|