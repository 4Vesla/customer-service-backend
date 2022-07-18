# Customer-service-backend REST API. 
## IT-Revolution team 4vesla.
## Endpoints:
* POST /login HTTP/1.1 - jwt authorization
* POST /registration HTTP/1.1 - customer registration
* GET /activate/email/{id}/{activationCode} HTTP/1.1 - activates customer account
* GET /customers HTTP/1.1 - retrieves list of customers
* GET /customers/{id} HTTP/1.1 - retrieves customer with id
* PUT /customers/{id} HTTP/1.1 - updates data about customer with id
* DELETE /customers/{id} HTTP/1.1 - removes customer's account

## Technologies:
* Java 11
* Maven
* Spring Boot, Spring Data, Spring Security
* PostgreSQL
* FlyWay
* AWS S3

## Hosted
The REST API is hosted on roman-ko.net domain.