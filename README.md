# Spring Boot Test Container PostgreSQL

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-test-container-postgresql/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-test-container-postgresql/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![Testcontainers](https://img.shields.io/badge/Testcontainers-2.x-2496ED?logo=docker)

A demonstration project showcasing the integration of Spring Boot with Testcontainers for PostgreSQL database testing.

## Overview

This project is a RESTful API for managing blog posts, built with Spring Boot and PostgreSQL. It demonstrates how to use
Testcontainers for integration testing with a real PostgreSQL database.

## Technologies Used

- Java 25 (LTS, no preview features required)
- Spring Boot 4.1.x
- Spring Data JDBC
- Spring WebFlux (WebClient)
- Jackson 3 (`tools.jackson`)
- PostgreSQL 17.5 (Alpine)
- Testcontainers 2.x
- Docker & Docker Compose
- Maven (Maven Wrapper included)
- Springdoc OpenAPI 3.x
- Lombok

## Prerequisites

- JDK 25 or higher (e.g. [Eclipse Temurin](https://adoptium.net/))
- Docker and Docker Compose (required by Testcontainers and `compose.yaml`)
- Maven 3.9+ (or use the bundled `./mvnw`)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/hendisantika/spring-boot-test-container-postgresql.git
cd spring-boot-test-container-postgresql
```

### Build and Run

You can run the application using Maven:

```bash
./mvnw spring-boot:run
```

Or build and run the JAR file:

```bash
./mvnw clean package
java -jar target/test-container-postgresql-0.0.1-SNAPSHOT.jar
```

### Using Docker Compose

The project includes a `compose.yaml` file for running the PostgreSQL database:

```bash
docker compose up -d
```

> Spring Boot's `spring-boot-docker-compose` support also starts this service automatically when you run the app
> with `./mvnw spring-boot:run`.

## API Endpoints

The application provides the following RESTful API endpoints:

- `GET /api/posts` - Get all posts
- `GET /api/posts/{id}` - Get a post by ID
- `POST /api/posts` - Create a new post
- `PUT /api/posts/{id}` - Update an existing post
- `DELETE /api/posts/{id}` - Delete a post

### API Documentation

The API documentation is available via Springdoc OpenAPI at:

```
http://localhost:8080/swagger-ui.html
```

### Using cURL

You can interact with the API using cURL commands:

#### Get All Posts

```bash
curl -X GET http://localhost:8080/api/posts
```

#### Get Post by ID

```bash
curl -X GET http://localhost:8080/api/posts/1
```

#### Create a New Post

```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "title": "New Post", "body": "This is a new post created with cURL"}'
```

#### Update a Post

```bash
curl -X PUT http://localhost:8080/api/posts/1 \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "title": "Updated Post", "body": "This post was updated with cURL"}'
```

#### Delete a Post

```bash
curl -X DELETE http://localhost:8080/api/posts/1
```

### HTTP Client

The project includes a WebClient-based HTTP client for programmatic API access. The client is implemented in the
`PostClient` class.

#### Using the HTTP Client

To use the HTTP client in your code:

```java

@Autowired
private PostClient postClient;

// Get all posts
Flux<Post> posts = postClient.getAllPosts();

// Get post by ID
Mono<Post> post = postClient.getPostById(1);

// Create a new post
Post newPost = new Post(null, 1, "New Post", "This is a new post", null);
Mono<Post> createdPost = postClient.createPost(newPost);

// Update a post
Post updatedPost = new Post(1, 1, "Updated Post", "This post was updated", null);
Mono<Post> result = postClient.updatePost(1, updatedPost);

// Delete a post
Mono<Void> deleteResult = postClient.deletePost(1);
```

#### Running the Example Client

The project includes an example client that demonstrates how to use the HTTP client. To run it:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=client-example
```

## Data Model

The application uses a simple `Post` model with the following fields:

- `id` (Integer) - Primary key
- `userId` (Integer) - User ID associated with the post
- `title` (String) - Post title (required)
- `body` (String) - Post content (required)
- `version` (Integer) - Version for optimistic locking

## Testing

The project demonstrates several testing approaches using Testcontainers:

### Repository Tests

Tests the repository layer with a real PostgreSQL database using Testcontainers.

```bash
./mvnw test -Dtest=PostRepositoryTest
```

### Controller Tests

Tests the controller layer with a real PostgreSQL database using Testcontainers.

```bash
./mvnw test -Dtest=PostControllerTest
```

### Integration Tests

Tests the entire application with a real PostgreSQL database using Testcontainers.

```bash
./mvnw test -Dtest=SpringBootTestContainerPostgresqlApplicationTests
```

### JSON Tests

Tests JSON (de)serialization of the `Post` record with `@JsonTest`.

```bash
./mvnw test -Dtest=PostJsonTest
```

### Run All Tests

```bash
./mvnw clean verify
```

### Notes on Spring Boot 4 / Testcontainers 2

- Testcontainers 2 uses the `testcontainers-` artifact prefix (`testcontainers-postgresql`,
  `testcontainers-junit-jupiter`) and the `org.testcontainers.postgresql.PostgreSQLContainer` class.
- Spring Boot 4 ships test slices in dedicated modules: `spring-boot-starter-data-jdbc-test` (`@DataJdbcTest`),
  `spring-boot-starter-webmvc-test` (`@WebMvcTest`) and `spring-boot-resttestclient`
  (`TestRestTemplate` + `@AutoConfigureTestRestTemplate`).
- `@MockBean` has been replaced with `@MockitoBean`.
- String templates (`STR."..."`) were removed from the JDK, so tests use text blocks with `.formatted(...)`.
- Since JDK 23 annotation processing is no longer implicit, so Lombok and the configuration processor are declared
  in `maven-compiler-plugin` `annotationProcessorPaths`.

## Continuous Integration

GitHub Actions ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) builds and tests the project on every
push and pull request to `main` using **JDK 25 (Temurin)** with Maven dependency caching. On pushes to `main` it also
submits the Maven dependency graph to GitHub to improve Dependabot alerts. The workflow can be triggered manually
via `workflow_dispatch`.

## Dependency Updates

[Dependabot](.github/dependabot.yml) keeps dependencies up to date:

| Ecosystem      | Schedule (Asia/Jakarta) | Grouping                                                                |
|----------------|-------------------------|-------------------------------------------------------------------------|
| Maven          | Daily, 05:00            | `spring-boot`, `testcontainers`, `springdoc`, `maven-plugins`, minor/patch |
| GitHub Actions | Weekly (Mon), 05:00     | All actions in one PR                                                   |
| Docker Compose | Weekly (Mon), 05:00     | PostgreSQL image in `compose.yaml`                                      |

PRs are labelled (`dependencies`, `java`, `github_actions`, `docker`) and use conventional commit prefixes
(`build(deps)`, `ci(deps)`, `build(docker)`).

## Project Structure

- `src/main/java` - Java source code
  - `controller` - REST controllers
  - `model` - Data models
  - `repository` - Data access layer
  - `exception` - Custom exceptions
  - `client` - WebClient-based HTTP client
  - `config` - Application configuration (WebClient)
- `src/main/resources` - Application resources
  - `data/posts.json` - Initial data for the application
  - `schema.sql` - Database schema definition
- `src/test/java` - Test source code
- `compose.yaml` - PostgreSQL service for local development
- `.github/` - GitHub Actions workflow and Dependabot configuration

## License

This project is open source and available under the [MIT License](LICENSE).

## Author

Hendi Santika

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34
