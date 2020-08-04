# MAN Truck Monitor
Truck Monitor is a solution to allow the Fleet Manager receives a call from the truck driver, then give the answers the truck driver needs.

### Prerequisites

In order to run this application you need to install two tools: **Docker** & **Docker Compose**.

Instructions how to install **Docker** on [Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/), [Windows](https://docs.docker.com/docker-for-windows/install/) , [Mac](https://docs.docker.com/docker-for-mac/install/) .

**Docker Compose** is already included in installation packs for *Windows* and *Mac*, so only Ubuntu users needs to follow [this instructions](https://docs.docker.com/compose/install/) .


### How to run it?

An entire application can be ran with a single command in a terminal:

```
$ docker-compose up -d
```

If you want to stop it use following command:

```
$ docker-compose down
```

#### truck-monitor-postgres (Database)

PostgreSQL database contains only single schema with two tables - trucks and trucks_location table.

After running the app it can be accessible using this connectors:

- Host: *localhost*
- Database: *truck*
- User: *postgres*
- Password: *password*


Like other parts of application Postgres database is containerized and the definition of its Docker container can be found in *docker-compose.yml* file.

```yml
truck-monitor-postgres:
image: postgres
container_name: truck-monitor-postgres
ports:
    - 5432:5432
environment:
    - POSTGRES_DB=truck
    - POSTGRES_USER=postgres
    - POSTGRES_PASSWORD=password
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


