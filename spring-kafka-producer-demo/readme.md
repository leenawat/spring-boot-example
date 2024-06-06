ref: https://www.stackchief.com/blog/Spring%20Boot%20Kafka%20Consumer

```
docker-compose exec kafka kafka-console-producer.sh --topic test --broker-list kafka:9092
```
or
```
./kafka-console-producer --bootstrap-server=localhost:9092 --topic test
```