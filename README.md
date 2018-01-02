# SearchEngineService
part of SearchEngine;

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them
* Java 8
* Maven

### Installing
    mvn clean install
    
## Running the tests
    mvn clean test
    
## Run application
    from **search-engine-service-war** folder
    mvn clean spring-boot:run
    
## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## General info
Subsystem **SearchEngineService** created for search token in Document;
- **search-engine-service** - module for handle info in DB. 
- **search-engine-service-war** - module for handle user requests;

## Generall settings:
    a) urn  - '/search-engine';
    b) port - 8082;

