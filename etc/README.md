# 카프카 설치하기 - Window

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

