# 카프카 - Window

## 카프카 다운로드
https://kafka.apache.org/downloads

```powershell
# 다운로드
curl https://mirror.navercorp.com/apache/kafka/2.8.0/kafka_2.13-2.8.0.tgz > kafka_2.13-2.8.0.tgz

# 압축해제
tar xvf kafka_2.13-2.8.0.tgz

# 주키퍼 서버 실행
 .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# 카프카 서버 실행
.\bin\windows\kafka-server-start.bat .\config\server.properties

# 토픽 목록 확인
.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list

# 토픽 생성
.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --create --topic quickstart-events --partitions 1

# 토픽 정보 상세확인
.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --describe quickstart-events

# 프로듀서 실행
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic quickstart-events

# 컨슈머 실행
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic quickstart-events --from-beginning
```

## JDBC connector
- 다운로드 : https://www.confluent.io/hub/confluentinc/kafka-connect-jdbc
- confluentinc-kafka-connect-jdbc-10.1.1.zip 압축해제
- 다음과 같이 confluent-X.X.X\etc\connect-distributed.properties 수정

```properties
plugin.path=\D:\\workspace\\kafka\\confluentinc-kafka-connect-jdbc-10.1.1\\lib
```


### 클래스 패스 설정
- .\bin\windows\kafka-run-class.bat
- rem Classpath addition for core 상단에 아래의 코드 추가

```batch
...
rem Classpath addition for LSB style path
if exist %BASE_DIR%\share\java\kafka\* (
	call :concat %BASE_DIR%\share\java\kafka\*
)
...
```

### Maria DB - JDBC 옮기기
- confluent-X.X.X\share\java\kafak\mariadb-java-client-X.X.X.jar


```powershell 
# connect 실행
.\bin\windows\connect-distributed.bat .\etc\kafka\connect-distributed.properties

# 토픽 확인
.\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --list
# connect-configs
# connect-offsets
# connect-status
```

### Kafka Connect

Source System -> Kafka Connect Source -> Kafka Cluster -> Kafka Connect Sink

### Kafka Source Connect
`POST http://127.0.0.1:8083/connectors`
```json
{
    "name": "my-source-connect",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
        "connection.url": "jdbc:mariadb://localhost:3306/mydb",
        "connection.user": "root",
        "connection.password": "todak@1234",
        "mode": "incrementing",
        "incrementing.column.name": "id",
        "table.whitelist": "users",
        "topic.prefix": "my_topic_",
        "tasks.max": "1"
    }
}
```

### 커넥터 등록 후 Data insert
```sql
INSERT INTO users(EMAIL, NAME, UUID, PASSWORD) VALUES('tjsdydwn@gmail.com', 'yongjoo', 'uuid', 'password');

INSERT INTO users(EMAIL, NAME, UUID, PASSWORD) VALUES('tjsdydwn2@naver.com', 'yjsun', 'uuid2', 'password2');
```

### 확인
```powershell
# 토픽에 쌓인 메시지 확인
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my_topic_users --from-beginning
```
```json
{"schema":{"type":"struct","fields":[{"type":"int64","optional":false,"field":"id"},{"type":"string","optional":false,"field":"email"},{"type":"string","optional":false,"field":"name"},{"type":"string","optional":false,"field":"uuid"},{"type":"string","optional":false,"field":"password"}],"optional":false,"name":"users"},"payload":{"id":1,"email":"tjsdydwn@gmail.com","name":"yongjoo","uuid":"uuid","password":"password"}}
{"schema":{"type":"struct","fields":[{"type":"int64","optional":false,"field":"id"},{"type":"string","optional":false,"field":"email"},{"type":"string","optional":false,"field":"name"},{"type":"string","optional":false,"field":"uuid"},{"type":"string","optional":false,"field":"password"}],"optional":false,"name":"users"},"payload":{"id":2,"email":"tjsdydwn2@naver.com","name":"yjsun","uuid":"uuid2","password":"password2"}}
```

## Kafka Sink Connect

```json
{
    "name": "my-sink-connect",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "connection.url": "jdbc:mariadb://localhost:3306/mydb",
        "connection.user": "root",
        "connection.password": "todak@1234",
        "auto.create": "true",
        "auto.evolve": "true",
        "delete.enabled": "false",
        "tasks.max": "1",
        "topics": "my_topic_users"
    }
}
```