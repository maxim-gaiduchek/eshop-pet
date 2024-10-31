# E-Shop Website

My E-Shop API and website pet project

General project description:

- Easy way for customers to search and buy staff
- Functional marketplace for multiple sellers and companies
- Backend: Java, Spring, PostgreSQL
- Frontend: React

## Table of Contents

1. [Conceptual scheme](#conceptual-scheme)
2. [Project Goals](#project-goals)
4. [Architecture Description](#architecture-description)
    - [Client Side](#client-side)
    - [Backend Services](#backend-services)
        - [Root Service](#root-service)
        - [Security Service](#security-service)
        - [PostgreSQL Database](#postgresql-database)

## Conceptual scheme

Approximate conceptual scheme of the model:

<img alt="conceptual_scheme" width="100%" src="conceptual_scheme.png">

Actual entities scheme (to be updated):

<img alt="entities_scheme" width="100%" src="entities_scheme.png">

## Project Goals

The E-Shop project aims to show developer's skills in Java and Spring. 

The main goals are:

1. **Products creation and searching** - implement product creation by seller and easy search by customer.
2. **Products categorizing and filtering** - implement products filtering by given parameters.
3. **Customer and seller user interface** - create the UI for a customer and a seller that provides easy way to search and create products using React.

## Architecture Description

### Client Side

The client application is developed using React.js. It provides a user-friendly interface for interacting with the system. Customers can easily search and view products. Sellers can create companies and create products in these companies.

### Backend Services

#### Root Service

REST API service that provides full E-Shop functionality. The API documentation can be found on [Swagger-generated page](http://localhost:8080/api/swagger-ui/index.html).

#### Security Service

REST API service that provides user login and authentication storage. Generates JWT token for each login. The API documentation can be found on [Swagger-generated page](http://localhost:8090/security/swagger-ui/index.html).

#### PostgreSQL Database

Data storage with all E-Shop information using PostgreSQL.
