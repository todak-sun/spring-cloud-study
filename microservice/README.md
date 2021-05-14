
### 비공개키 생성
```shell
 keytool -genkeypair\
  -alias apiEncryptionKey\
  -keyalg RSA\
   -dname "CN=Todak Sun, OU=API Development, O=todak.sun, L=Seoul, C=KR"\
   -keypass "test1234"\
    -keystore apiEncryptionKey.jks\
     -storepass "test1234"
```

### 생성한 키 확인
```shell
keytool -list -keystore apiEncryptionKey.jks -v
```

### 비공개키를 통해 공개키 생성
```shell
keytool -export -alias apiEncryptionKey -keystore apiEncryptionKey.jks -rfc -file trustServer.cer
```

### 생성한 공개키를 다시 jks로 변환
```shell
keytool -import -alias trustServer -file trustServer.cer -keystore publicKey.jks
```