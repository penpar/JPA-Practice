## NOTE
### Commands
- ./gradlew dependencies : 의존 라이브러리 확인
- ./gradlew clean build : build clean
- ./gradlew build : jar 생성, 명령어 입력 경로에서 build 폴더가 생성되며 build/libs 경로에 jar가 생성된다.
- java -jar [filename].jar : jar 실행

### JUNIT4와 JUNIT5 차이
- @ExtendWith(SpringExtension.class) 는 @RunWith(SpringRunner.class)로 대체 됨

https://stackoverflow.com/questions/42550874/intellij-junit-runwith-not-resolved

### dependencies
 org.springframework.boot:spring-boot-starter-data-jpa
- com.zaxxer:HikariCP:3.2.0 : DB connection pool 관리





