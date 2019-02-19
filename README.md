# Spring Boot with Angular Front End

## Abstract

This example shows how to use Spring Boot, Spring Data 
and JPA back-end, with Angular 7 front-end.

## Artifacts

* **customer-management** the parent POM
* **customer-management-api** the back-end.
* **customer-management-ui** the front-end.

## Build

The build uses Apache Maven. Simply use:

```
mvn clean install
```
This will run some integrationtests. Then, in order to run the Spring Boot microservice:

```
cd ./customer-management-api
mvn spring-boot:run
```
And to run the UI:
```
cd ../customer-management-ui
ng serve
```
Now, go to http://localhost4200 and have fun.

