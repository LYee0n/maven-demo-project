


https://docs.spring.io/spring-boot/docs/1.5.6.RELEASE/reference/html/boot-features-external-config.html

```sh
mvn spring-boot:run

export SERVER_PORT=8765

java -jar target/demo-0.0.1-SNAPSHOT.jar 

unset SERVER_PORT

java -jar target/demo-0.0.1-SNAPSHOT.jar  --spring.config.location=file:./application.properties

java -jar target/demo-0.0.1-SNAPSHOT.jar  --spring.config.location=file:/workspaces/maven-demo-project/demo/application.properties

```