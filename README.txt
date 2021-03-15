The program provides tests for GET, POST and DELETE operations in a previosuly provided REST Api


Before trying* to run any tests both test files (UsersTesting.java, UserDomainTesting.java) need the 3 class variables: url, auth and apikey need to be assigned proper values.
To run the tests import the project along with all the dependencies in pom.xml file,
click on the project title to have it all highlighted and press RUN >> Run As >> JUnit Test.
All the tests should run with one failing for the user with no domain creation

Potential improvements:
- Test case order, which is implement in JUnit 5.
- JSON decoder for the messages especially to imprve the domain testcases.
- TestSuite class fix with regards to some library issues. 


*Disclaimer that just importing the project might not work from my setup as not all tools and libraries are installed from dependencies.
The setup used for the project was maven version 3.6.3, JUnit 4, REST Assured, Eclipse version  4.18, Java JDK 15.0.2