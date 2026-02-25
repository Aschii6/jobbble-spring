# Jobbble React
> Java, Spring Boot, Spring JPA & Hibernate with Postgres, Spring Security, JWT, Spring Validation, MapStruct Entities, DTOs, Repositories, Services, Controllers, Custom Exceptions and Global Controller Exception Handling

Requests that want to access protected routes need to include a JWT token in the Authorization header. The username is extracted from the token, Spring Security checks that the user exists in the DB and that the token is not expired and sets the context of the authentication assigning user roles and other custom data.

Users can only access and alter data that they created.
