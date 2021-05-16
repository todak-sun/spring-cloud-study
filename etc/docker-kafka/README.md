

```shell
# jdbc 커넥터 설치
confluent-hub install confluentinc/kafka-connect-jdbc:latest

# 도커에 maria db 넣기
docker cp /c/Users/tjsdy/.m2/repository/org/mariadb/jdbc/mariadb-java-client/2.7.2/mariadb-java-client-2.7.2.jar connect:/usr/share/java/kafka/
```

