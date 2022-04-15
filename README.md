# Spring-Framework-Pet-project
Pet project on Java Spring Framework is an coursework project for my university graduation.
Stack of technologies used in the project: Spring Security, Spring Boot, Thymeleaf, Spring Data, Json Web Token, SQLite database, Maven.

The application is a client-server interaction. The main task is to automate the work of the accounting department of the company.

The application database schema is shown below:

![image](https://user-images.githubusercontent.com/38047651/163593951-5fe7d10c-706d-4015-9818-7fc2400fe36a.png)

The main task is to realize Spring web-application with intercation with SQLite database and protect it by Spring Security.

The application uses a standard MVC scheme using repositories for interacting with the database, 
services for communicating controllers with repositories and controllers for working with http requests.

Server side of application interacts with client side by HTTP requests from client. 

Client side of application has HTML pages and forms to allow user to interact with application by GUI.

Thymeleaf engine is used to make HTML pages more flexible for interaction and adaptable to the amount of data received from the server.

The Json Web Token is used for authorized access to the functions of adding and editing information in the database.
AFter registration user becomes allowed to sign in into his account and Client side gets a user token from server.
Token has information about user roles, such as USER or ADMIN, time of issue and time of expiration of token. 
After token expiration user has to sign in into the system again to get new token and allow access.

Spring Security works with tokens and adjust access to Server-side controllers by its configuration.
