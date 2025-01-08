## Run docker compose
```shell
docker compose up -d
```

## คัดลอกไฟล์ไปยัง container
```shell
# คัดลอกไฟล์ไปยัง container
docker cp users.ldif openldap:/tmp/

# เข้าไปใน container
docker exec -it openldap bash

# import LDIF file
ldapadd -x -D "cn=admin,dc=mycompany,dc=com" -w admin -f /tmp/users.ldif
```

## Run Project
```shell
mvn spring-boot:run
```

## john login
```shell
curl -X POST 'http://localhost:8090/api/auth/login' -H 'Content-Type: application/json;charset=UTF-8' -d '{
  "username": "john",
  "password": "password123"
}' 
```

## jane login
```shell
curl -X POST 'http://localhost:8090/api/auth/login' -H 'Content-Type: application/json;charset=UTF-8' -d '{
  "username": "jane",
  "password": "password456"
}' 
```

## jwt payload
```json
{
  "sub": "jane",
  "roles": [
    "developers"
  ],
  "iat": 1736344257,
  "exp": 1736430657
}
```