# Java CRUD Console application "School"
This is a training project that consist of many technologies such as: spring, flyway, docker, junit, mockito and etc..

According to the program, the developing of this application is consisted of several milestones. At each stage, a new application layer or functionality with required library for implementation it is added. In the final, the application is converted to Web application and some libraries will be replaced.

## Proposal

"School" application manages the database of students, courses, groups and implements a crud functionality via command line interface.

## Application design

### Entities

* Student - the person who is an attendee of lectures
* Course - certain academic subject
* Group - the group of Students with is assigned to the Courses

### How To Run

0. Stop existing pgsql process in services;
1. Go to the project root directory
2. Run command *./mvnw clean package*
3. Then run command *docker compose up -d*;
4. Also we need to write *docker attach school_app* to interact with application by Command Line
5. Enjoy

## Application components

The application includes the following components:

* Build tool (**Maven**)
* ORM (**Hibernate**)
* IoC (**Spring, SpringBoot**)
* Unit tests (**JUnit5, Mockito**)
* Database versioning (**Flyway**)
* Way of interacting with DB (**JDBC API**)
* DB Tests (**Testcontainers**)
* Logging (**Log4j2**)
* AOP
* Deployment Service (**Docker**)

Database management system used for data storage: **Postgres** in runtime and use **Testcontainer** during tests.

Made by: **Viktor Tovarnykh**, under professional supervision of **Roman Yushin**