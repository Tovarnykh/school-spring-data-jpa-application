# Java CRUD Console application "School"
This is a training project of the mentoring program of the FoxmindEd training center.

According to the program, the developing of this application is consisted of several milestones. At each stage, a new application layer or functionality with required library for implementation it is added. In the final, the application is converted to Web application and some libraries is replaced.

## Proposal

"School" application manages the database of students, courses, groups and implements a crud functionality via comand line interface.

## Application design

### Entities

* Student - the person who is an attendee of lectures
* Course - certain academic subject
* Group - the group of Students with is assigned to the Courses

### How To Run

0. Stop existing pgsql process in services;
1. Run command *docker compose up -d*;
2. Recieve a control to system input to docker *docker attach school-app*
3. Enjoy

## Application components

The application includes the following components:

* Build tool (**Maven**)
* ORM (**Hibernate**)
* IoC (**Spring, SpringBoot**)
* Unit tests (**JUnit5**)
* Database versioning (**Flyway**)
* Way of interacting with DB (**JDBC API**)
* DB Tests (**Testcontainers**)
* Logging (**Log4j2**)
* AOP

Database management system used for data storage: **Postgres** in runtime and use **Testcontainer** during tests.