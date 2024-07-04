	Innovation Test Application

	Android/IOS



	If the test is for Android, do it with Java and if the test is for iOS, do it with SwiftUI



	A company requires a system to have basic information about their employees at any time. As part of the Innovation Department you were selected to develop this project. The design department has already sent you the screens you need to implement.





	Splash screen

	This should be a regular splash screen that longs 3 seconds, the image shown can be replace for other one if you like to.

	Login screen

	This activity will validate that the User ID entered is a valid one. Save the response you get from the service, you’ll use it later. This screen must be shown only once unless the user logs out.

	Employee list

	You must show all the records from the service in the way you think is the best one. A navigation drawer must be implemented containing a Settings option. With this, the user must be able to change the basic picture to the one he wants to show (library or camera). If an employee is selected, you must be able to access to another activity showing individual information.

	Other specifications:

	 	Age must be green if the user is more than 25 but less than 35

	Age is red in any other case Employee details



	Display the information from the user selected on the Employee list activity

	Specifications:

	 	Salary must be green if the user is more than 1000

	Salary is red in any other case





	You are free to add any other functionality you think is a great contribution. You can use any library you need, just specify for what is used on the Gradle file.





	Important points to be considered:



	 	Good practices are used to build the app





	 	Add some unit test





	 	Open interface to make it to the developer's imagination and improve from their perspective





	 	Quality in the app





Screens design




Splash screen



Employee list

	Login





Employee details

	Navigation drawer





REST Services


	Get all employee data

	/employee http://dummy.restapiexample.com/api/v1/employees Values: ID, Name, Salary, Age

	Get a single employee data

	/employee/{id} http://dummy.restapiexample.com/api/v1/employee/1





