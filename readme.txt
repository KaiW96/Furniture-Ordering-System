readme.txt

included:
Source files (.java)
Order.java
Calculations.java
Storage.java
OfficeFurniture.java
Chair.java
Desk.java
Lamp.java
Filing.java


# Furniture Ordering System

Order is a program which produces an order for a furniture based on the current inventory of furniture parts

Updated: April 2021

Introduction
------------

Order is a program which incorporates an inventory of used furniture parts. When a client requests a furniture, the program searches through
the inventory to see if there are any combinations of furniture parts that can be assembled to make a complete, sell-able furniture. If multiple
combinations are possible to meet client requirements, the program will suggest the lowest costing combination.

If no possible combinations are found, the program will suggest the client to reach out to manufacturers.


Installation
-------------------

The Order program can be run in any directory which has a Java machine installed.

If you are working in the src directory, use the following command below to run the program:
```
javac -cp .;mysql-connector-java-8.0.23.jar;. edu/ucalgary/ensf409/Order.java
```
followed by :
				java -cp .;mysql-connector-java-8.0.23.jar;. edu/ucalgary/ensf409/Order 
				
The code will now ask the user for username and password. Enter the credentials on your computer and the program will ask for a furniture request.
After a successful order is created, an output text file (orderform.txt) will be created in the same directory as src. The user can update 
the MySQL database at anytime to get the most updated inventory.

The program user should follow the following conventions:

When the program asks the user for the category and type input, keep the first letter in upper case and the following letters in lowercase.

For example: Chair, Mesh, Desk, 

When the program asks for items requested, enter a real number such as 1,3 and so on.
After the successful order is created, the user can decide to make another order or exit.

				
Unit Testing
-------------------
We recommend you running the unit tests in an IDE, where you can choose which tests to run individually. To set up the unit test environment
we recommend creating a new project folder and copy paste the .java files in the new folder.

After each test, the user must update the database in MySQL for example by re-uploading the source using "source  C:\Users\-path-to-\inventory.sql".
Every single test performed assumes that the original database is present, therefore if multiple tests are run consecutively, 
it is not a good representation of the performance of the unit tests.

Assuming you are still working in the src directory, to check if the program works in the terminal, use the following commands: 

				javac edu/ucalgary/ensf409/Order.java  
followed by: 
				javac -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar;system-rules-1.19.0.jar edu/ucalgary/ensf409/OrderTest.java
followed by:   
				java -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar;system-rules-1.19.0.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.OrderTest
							

							