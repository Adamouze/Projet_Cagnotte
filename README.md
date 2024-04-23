# Projet Cagnotte

This project is a simple banking application that allows clients to create accounts and make transactions. The project is built using Java, SQL, Spring Boot, and Maven. It uses a MariaDB database to store data and Adminer for database management. The database is managed automatically using Spring Data JPA.

## Architecture

The project follows a typical Spring Boot architecture with Controllers, Services, Repositories, and Entities.

- **Controllers**: Handle incoming HTTP requests and return responses. They use services to perform operations.
- **Services**: Contain the business logic of the application. They use repositories to interact with the database.
- **Repositories**: Interface with the database. They extend Spring Data JPA's `JpaRepository` to provide methods for CRUD operations.
- **Entities**: Represent the tables in the database. They are simple Java classes annotated with `@Entity`.

The project uses Spring Boot's automatic configuration to set up most of the application. The `application.properties` file contains configuration properties for the application, such as the database URL and credentials.

## Dependencies

The project uses several dependencies:

- **Spring Boot Starter Data JPA**: Provides Spring Data JPA and Hibernate to handle database operations.
- **Spring Boot Starter Web**: Provides Spring MVC and Tomcat to handle HTTP requests and responses.
- **MariaDB Java Client**: A JDBC driver for MariaDB.

## Installation

1. Clone the repository to your local machine.
2. Navigate to the project `resources` directory (`cd src/main/resources`)
3. Run `docker-compose up` to start the MariaDB database and Adminer using Docker. The database will be available on port 3307 and Adminer on port 8081.
4. Run the Spring Boot application, you can use your preferred IDE or execute the following command at the root of the project : `mvn clean install`. It will start on port 8080.

## Adminer

To connect to Adminer:

- System: MySQL
- Server: resources-db-1
- Username: root
- Password: root

## API Specifications

The project provides several APIs for managing clients and transactions. Here are the main ones:

- `POST /client/createClient`: Creates a new client. Takes a `name` and `cagnotte` as parameters.
- `GET /client/getClient`: Retrieves a client by `id` or `name`.
- `POST /transaction/makeTransaction`: Makes a transaction for a client. Takes a `clientId` and `amount` as parameters.
- `GET /transaction/getTransactions`: Retrieves all transactions for a client. Takes a `clientId` as a parameter.
- `GET /transaction/isCagnotteAvailable`: Checks if a client's cagnotte is available. Takes a `clientId` as a parameter.

For more details, please refer to the controller classes in the `bforbank.cagnotte.controller` package.

## Testing

This project includes a suite of unit tests located in the `src/test` directory. To run these tests, you can use your preferred IDE or execute the following command at the root of the project :  `mvn test`

Moreover, There is a Postman collection for testing the APIs. You can import this collection into Postman and run the requests to test the APIs (**run these in order**).