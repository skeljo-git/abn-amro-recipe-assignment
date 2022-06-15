# ABN AMRO recipes assignment application

## Build & run
* You can run the application from your favorite IDE
* Run it using Maven
* Build executable .jar with Maven and run it

After app is started it's available on http://localhost:8080/api/recipes. In root directory under **postman** folder you can find Postman collection with example API requests.

Swagger-ui is accessible on http://localhost:8080/swagger-ui.html

### Running it using Maven
To run the application using Maven from the command line, position yourself in the root of the project and run command
```
mvn spring-boot:start
```
To stop the application run command
```
mvn spring-boot:stop
```

### Build .jar and run it
**Not recommended to run locally**

To build .jar with Maven from the command line, position yourself in the root of the project and run command
```
mvn clean package
```
Then run command
```
java -jar interview-project-0.0.1-SNAPSHOT.jar
```
To stop the app you will need to kill the process that was created from the **java -jar** command.

## Dependencies
- Java: 17
- Spring Boot Data JPA: 2.7.0 (version set by starter v2.7.0)
- Spring Security: 5.7.1 (version set by starter v2.7.0)
- Spring Web: 5.3.20 (version set by starter v2.7.0)
- Spring Validation: : 2.7.0 (version set by starter v2.7.0)
- Flyway: 8.5.11 (version set by starter v2.7.0)
- H2: 2.1.212 (version set by starter v2.7.0)

