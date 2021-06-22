# Employee

REST Service

## data.sql
This file initializa data into database

Console H2
http://host:port/h2-console

## Swagger Test URL

The Services can be tested by next URL:


```bash
http://host:port/swagger-ui.html
```

## Usage

Base EndPoint:

http://host:port/api/employee


Services 
```
[GET] /all  => return all employees, ordered by name
```
```
[GET] /all/{filterBy}/{value} => return filtered employees
    * filterBy : Could be 'position' or 'name'
    * value : The value to filter (case sensitive)
```
```
[GET] /positions  => return all position ordered by position.name and employees.salary
```
```
[POST] /  => new Employee object as Body (see in Swagger the model)
```

```
[PUT] /{id}  => Update Employee object
```

```
[DELETE] /{id}  => Delete Employee object
```
## author
Cesar Diaz.
