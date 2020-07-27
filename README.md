# MAN Truck Monitor
Truck Monitor is a solution to allow the Fleet Manager receives a call from the truck driver, then give the answers the truck driver needs.

### How to run it?
The truck-monitor application can be ran with a single command in a terminal:
```
mvnw spring-boot:run
```

#### truck-monior (REST API)
This is a Spring Boot (Java) based application that connects with a
database that and expose the REST endpoints that can be consumed by
frontend. It supports multiple HTTP REST methods like GET, POST, PUT and
DELETE for one resource - Truck

Full list of available REST endpoints could be found in Swagger UI,
which could be called using link: **http://localhost:8080/api/swagger-ui.html**

#### truck-monitor-ui (Frontend)
The project was generated using the **npx** packager runner tool, but wasn't developed.


