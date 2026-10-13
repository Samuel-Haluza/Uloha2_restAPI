# Employee Directory REST API

Spring Boot REST API for reading employees from the `employee_directory` MySQL database
using a three-layer architecture:

- **DAO** - `EmployeeDAO` and `EmployeeDAOJpalmpl` load entities with Hibernate/JPA.
- **Service** - `EmployeeService` contains the application logic.
- **Controller** - `EmployeeRestController` exposes the REST endpoint.

## Running the application

1. Create the `employee_directory` database and import the supplied SQL script.
2. Check the MySQL username and password in
   `src/main/resources/application.properties`.
3. Start the application:

   ```text
   .\mvnw.cmd spring-boot:run
   ```

4. In Postman send a `GET` request to
   `http://localhost:8080/api/employees`.

The response is a JSON array containing all employees.

## Postman response

After running the request in Postman, save the screenshot as
`docs/postman-employees.png` and it will be displayed here:

![Successful GET /api/employees response](docs/postman-employees.png)
