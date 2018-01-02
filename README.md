# SearchEngineService
part of SearchEngine;

[Read](https://github.com/MaximDzhezhelo/SearchEnginePortal/blob/master/GENERAL.md) more information about SearchEngine;

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Prerequisites

What things you need to install the software and how to install them
* Java 8
* Maven

## Installing
    mvn clean install
    
## Running the tests
    mvn clean test
    
## Run application
    cd ~~/path/to/~~search-engine-service-war
    mvn clean spring-boot:run
    
## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## General info
Subsystem **SearchEngineService** created for search token in Document;
- **search-engine-service** - module for store data into DB.
- **search-engine-service-war** - api module for work with data;

## Generall settings:
    1. urn  - '/search-engine';
    2. port - 8082;

