from: https://www.youtube.com/watch?v=ekhU2TrY0Ak

[HINDI] Spring Boot Exception Handling & Validation REST API + Postman + PostgreSQL | 2024 |

@Valid
all annotation
```
curl --location 'localhost:8080/api/v1/employees' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"devdev",
    "email":"dev@gmail.com"
}'
```

@Validated
only matched group
```
curl --location --request PUT 'localhost:8080/api/v1/employees' \
--header 'Content-Type: application/json' \
--data '{
    "job":"dev"
}'
```