1 on property
```
app.name=app1
app.id=1
```

2.1 intellij -> environment variable
```app.name=app2;app.id=2```

2.2 eclipse -> vm argument
```-Dapp.name=app2 -Dapp.id=2```

3 command line
```./mvnw spring-boot:run -Dspring-boot.run.arguments="--app.name=app3 --app.id=1"```

4 on jar run
```
java -jar target/spring-app-property-demo-0.0.1-SNAPSHOT.jar --app.id=4 --app.name=app4
```