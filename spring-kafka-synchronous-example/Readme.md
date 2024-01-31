reference: https://github.com/asbnotebook/spring-boot/tree/master/spring-kafka-synchronous-example

https://asbnotebook.com/synchronous-request-reply-using-apache-kafka-spring-boot/

postman
```
curl --location 'localhost:8080/get-result' \
--header 'Content-Type: application/json' \
--data '{
"name": "leenawat",
"registrationNumber": "1234",
"grade": "A"
}'
```

add hosts /etc/hosts
```
192.168.1.144 kafka
```


