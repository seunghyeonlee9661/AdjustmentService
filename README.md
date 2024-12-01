# 📊 정산 시스템

## 📄 프로젝트 소개
**AdjustmentService API**는 영상, 광고 업로드 및 조회수 기반 수익 정산 통계를 제공하는 서비스의 API입니다. 해당 프로젝트는 다음과 같은 목표를 달성하고자 합니다
- **수익 정산 통계 제공**: 영상 및 광고 조회수를 기반으로 수익을 자동 계산하여 통계 데이터를 제공합니다.
- **마이크로 서비스 구현**: `Spring Boot`, `Docker Compose`, `Eureka`, `Gateway`를 활용해 효율적인 마이크로 서비스 아키텍처를 구축합니다.
- **대용량 트래픽 처리 및 성능 측정**: `Spring Batch`와 `JMeter`를 활용하여 대용량 트래픽을 처리하고 성능을 측정합니다.
- **CI/CD 파이프라인 구축**: `Github Actions`를 사용하여 자동화된 빌드 및 배포 프로세스를 구현합니다.
- **API 자동 문서화**: `Swagger`를 활용하여 API 명세서를 자동으로 생성하고 팀 간 협업을 지원합니다.

## 🛠️ 기술 의사 결정
![image](https://github.com/user-attachments/assets/1a3288eb-76ff-4bc2-8683-abeaeaad964f)
### 주요 기술 스택
| **기술**                          | **설명**                                                                                                                                           |
|----------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| **Spring Boot**                  | 빠른 개발과 간단한 설정, 다양한 연계 기술 활용 가능                                                                                                          |
| **Spring Eureka**                | 분산 환경에서 서비스 간의 **자동 등록**과 **탐색**을 제공                                                                                                  |
| **Spring Gateway**               | 서비스 간 **라우팅, 로드 밸런싱** 등을 처리하는 **API Gateway**로 활용                                                                                           |
| **Spring Batch**                 | 복잡한 연산을 미리 처리하여 **대용량 트래픽에 대비**                                                                                                          |
| **Docker Compose**               | 분산된 여러 서비스를 **컨테이너로 관리**하고 **CI/CD와 쉽게 통합**                                                                                             |
| **Swagger**                      | **API 명세**를 자동으로 생성하고 관리                                                                                                                   |
| **Github Actions**               | CI/CD를 통한 **빌드와 배포의 자동화**                                                                                                                   |
| **Nginx**                        | 프로젝트들을 구분하여 관리하는 **리버스 프록시 서버**로 사용                                                                                                  |
#### 1. **Spring Eureka + Spring Gateway**
- **Eureka**는 서비스의 **자동 탐색과 등록**을, **Gateway**는 외부 요청에 대해 **라우팅**을 관리
- **Eureka 서버**의 문제가 발생하면 **전체 서비스로 전파** 가능

#### 2. **Spring Batch > Apache Camel**
- **Spring Batch**는 **대규모 배치 처리**와 대량 데이터의 **일괄 처리 작업**에서 뛰어남
- **Apache Camel**은 실시간 **메시징과 라우팅**에 강점을 가짐
- **트랜잭션 관리와 대량 배치 처리**를 위해 **Spring Batch** 선택

#### 3. **Docker Compose > Kubernetes**
- 작은 규모의 프로젝트에서 **Docker Compose**를 통해 빠르고 간편하게 개발 가능
- **Kubernetes**는 **고가용성과 대규모 배포**에 유용하지만 **설정이 복잡하고 프로젝트 규모에 부적합**
- **Docker Compose**는 리소스 소모가 적어 **배포에 유리**, **컨테이너 수에 따라 성능 저하 가능**

## 🌟 **주요 기능**
<aside>
  
- **🎯 회원 서비스** : 회원가입, 로그인, 로그아웃 기능
- 📽️ **스트리밍 서비스** : 동영상 재생, 조회수 증가, 재생 시간 및 종료 시점 기록
- ⏯️ **광고 재생** : 동영상에 등록된 광고 재생, 광고 조회수 증가
- 📊 **통계 및 정산** : 동영상과 광고 조회수를 기반으로 **일간, 주간, 월간** 정산 금액 산정

</aside>

## 🗂️ 프로젝트 구조
<details>
<summary>File Structure</summary>
<pre>
📦adjustment
 ┣ 📂.github
 ┃ ┗ 📂workflows
 ┃ ┃ ┗ 😺deploy.yml
 ┣ 📂module-adjust
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.controller
 ┃ ┃ ┃ ┣ 📂java.com.sparta.dto
 ┃ ┃ ┃ ┣ 📂java.com.sparta.entity.base
 ┃ ┃ ┃ ┣ 📂java.com.sparta.entity
 ┃ ┃ ┃ ┣ 📂java.com.sparta.repository
 ┃ ┃ ┃ ┣ 📂java.com.sparta.service
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ ⚙️application-profile.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 🐘build.gradle
 ┃ ┗ 🐋Dockerfile
 ┃ ┗ 🐘settings.gradle
 ┣ 📂module-batch
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.config
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ ⚙️application-profile.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 🐘build.gradle
 ┃ ┗ 🐋Dockerfile
 ┃ ┗ 🐘settings.gradle
 ┣ 📂module-common
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.config
 ┃ ┃ ┃ ┣ 📂java.com.sparta.dto
 ┃ ┃ ┃ ┣ 📂java.com.sparta.entity
 ┃ ┃ ┃ ┣ 📂java.com.sparta.filter
 ┃ ┃ ┃ ┣ 📂java.com.sparta.handler
 ┃ ┃ ┃ ┣ 📂java.com.sparta.repository
 ┃ ┃ ┃ ┣ 📂java.com.sparta.security
 ┃ ┃ ┃ ┣ 📂java.com.sparta.service
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ ⚙️application.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 🐘build.gradle
 ┣ 📂module-eureka
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┗ 📂java.com.sparta
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ ⚙️application.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 🐘build.gradle
 ┃ ┗ 🐋Dockerfile
 ┃ ┗ 🐘settings.gradle
 ┃ 📂module-gateway
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┃ ┗ ⚙️application.properties
 ┃ ┣ 🐘build.gradle
 ┃ ┣ 🐋Dockerfile
 ┃ ┗ ⚙️settings.gradle
 ┣ 📂module-user
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.controller
 ┃ ┃ ┃ ┣ 📂java.com.sparta.service
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ ⚙️application-profile.properties
 ┃ ┗ 🐘build.gradle
 ┃ ┗ 🐋Dockerfile
 ┃ ┗ 🐘settings.gradle
 ┣ 📂module-video
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.controller
 ┃ ┃ ┃ ┣ 📂java.com.sparta.service
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ ⚙️application-profile.properties
 ┃ ┗ 🐘build.gradle
 ┃ ┗ 🐋Dockerfile
 ┃ ┗ 🐘settings.gradle
 ┣ 🐘build.gradle
 ┣ 🐋docker-compose.yml
 ┗ 🐘settings.gradle 
</pre>
</details>

### 모듈별 설명
| 모듈 이름        | 설명                                                            |
|------------------|-----------------------------------------------------------------|
| **adjust** | 정산 과정을 이행하고 이에 대해 결과를 데이터베이스에 저장하고 불러올 수 있는 기능의 모듈. |
| **batch**  | 각 정산 과정을 특정한 시간에 이행할 수 있도록 구현한 batch 모듈. |
| **common** | 모든 모듈에 공통적으로 적용되는 기능(인증, 인가, 레디스, 핸들러)을 관리하는 모듈. |
| **eureka** | 각 모듈에 대해 유레카 클라우드의 서버 역할을 수행하는 모듈. |
| **gateway**| 모든 모듈 간의 요청을 라우팅하고 API Gateway 기능을 제공하여 클라이언트와 서비스 간의 통신을 효율적으로 관리하는 모듈. |
| **user**   | 사용자 로그인 및 개인 정보를 확인하는 기능 모듈.              |
| **video**  | 영상 및 광고를 업로드하고 이를 재생 및 영상 시청 시점을 기록하는 기능 모듈. |

## 🤔 Troubleshooting
<details>
<summary><strong>Docker Container 영상 업로드 기능 구현에 따른 문제 발생</strong></summary>
   
   💡 **문제** : Docker Container에서 영상 업로드 기능 구현을 했지만 **업로드된 파일을 찾을 수 없는 문제 발생**<br>
   ❌ **원인** : **Docker Container 내부에 영상 파일이 저장되어 서버 경로에서 해당 파일을 찾지 못함**<br>
   ✔️ **해결** : Docker 빌드시 파일의 저장 장소를 도커가 있는 서버에 연결! → (`volumes`)를 Docker Compose 파일에 추가
   <pre>
     video-service:
       build:
         context: ./  # Root context for the build
         dockerfile: module-video/Dockerfile
       container_name: video_service
       ports:
         - "8083:8080"
       env_file:
         - .env
       depends_on:
         - mysql
         - redis
       volumes:
         - /var/www/uploads/adjustment:/var/www/uploads/adjustment   
   </pre>
   Docker에서 파일을 저장하는 해당 경로는 Docker 외부의 **서버의 경로와 연결**되어 **파일이 원하는 장소에 저장**되었으며 필요한 파일을 찾을 수 있게 됨   
</details>

<details>
<summary>Multi Module 빌드시 <strong>Main class name has not been configured and it could not be resolved from classpath</strong> 오류 </summary>
   
   💡 **문제** : 스프링 Multi Module를 빌드 할 경우 나타나는 Main Class를 찾지 못하는 문제가 발생함 [Stackoverflow](https://stackoverflow.com/questions/78903577/main-class-name-has-not-been-configured-and-it-could-not-be-resolved-from-classp)<br>
   ❌ **원인** : Docker 빌드 과정에서 root 경로에서 필요한 파일을 가져오는 과정에서 **settings.gradle**을 참조하는데 모든 모듈을 include 하도록 작성되어 있기 때문에 특정 모듈에 필요하지 않은 모듈도 빌드를 시도하면서 나타나는 문제<br>
   ```
   //settings.gradle
   rootProject.name = 'adjustment'
   include 'module-user'
   include 'module-video'
   include 'module-common'
   include 'module-adjust'
   ```

   ✔️ **해결** : 빌드 과정에 필요한 모듈의 이름만 포함한 **settings.gradle**을 각 모듈에 배치하여 필요한 파일만 빌드할 수 있도록 구성함 <br>
   ```
   //module-user/settings.gradle
   rootProject.name = 'adjustment'
   include 'module-user'
   include 'module-common'
   ```
   또한 해당 업로드 과정을 각 모듈의 DockerFile에 적용함<br>
   ```
   # Base image
   FROM openjdk:17-jdk-slim
   
   # Set working directory
   WORKDIR /app
   
   # Copy Gradle files from the root context to the service context
   COPY gradlew /app/
   COPY gradle /app/gradle/
   COPY build.gradle /app/
   
   COPY module-common/build.gradle /app/module-common/
   COPY module-common/src /app/module-common/src
   
   COPY module-user/build.gradle /app/module-user/
   COPY module-user/src /app/module-user/src
   
   #Copy settings.gradle for module
   COPY module-user/settings.gradle /app/ 
   
   # Ensure gradlew is executable
   RUN chmod +x gradlew
   
   # Build the application
   RUN ./gradlew build -x test --stacktrace
   
   # Check the build output directory
   RUN ls -l module-user/build/libs/
   
   # Copy the built JAR file to /app.jar
   RUN cp module-user/build/libs/module-user-0.0.1-SNAPSHOT.jar /app.jar
   
   # Expose port
   EXPOSE 8080
   
   # Run the application
   CMD ["java", "-jar", "/app.jar"]
   ```
   💾 **Multi Module 구성 과정을 상세하게 설명하고 바로 적용할 수 있도록 별도의 Git Repository를 작성함** : [muti-module](https://github.com/seunghyeonlee9661/muti-module)
</details>

<details>
<summary><strong>Eureka Server - Spring Security 인가 요청</strong></summary>
   
   💡 **문제** : Eureka Server 페이지 접근 시 접근이 불가능하며 로그인을 요청함<br>
   ❌ **원인** :
   1. Spring Security가 root 경로의 의존성에 존재해 **모든 모듈이 Spring Security의 인가를 처리**하도록 되어있어 Login 페이지를 호출<br>
   2. Eureka Server에 연결된 client앱이 오류를 발생시킴<br>
   ✔️ **해결** :
   1. ~~Eureka Server에 기반 모듈을 연결하고 **Security Config에 대해 접근을 허용함**~~ -> 불필요한 의존성이 생기며 빌드 시간이 오래 걸림<br>
   2. Eureka Module의 **application.properties에 Security 보안 설정을 무시**하도록 설정
   ```
   # Spring Security Exception
   eureka.security.enable-self-preservation=false
   management.endpoints.web.exposure.include=health,info
   ```   
</details>

<details>
<summary><strong>ConflictingBeanDefinitionException</strong> 오류 발생</summary>
   
   💡 **문제** : 프로젝트 빌드 시 **ConflictingBeanDefinitionException** 오류가 나타나며 빌드가 실패함<br>
   ❌ **원인** : Spring Project에서 파일을 삭제해도 Git Actions에서 기존 파일을 삭제하지 않기 때문에 이전 파일이 남아 오류를 발생 시킴<br>
   ✔️ **해결** :
   1. ~~deploy.yml에서 모든 파일을 삭제하고 다시 다운로드 하도록 설정~~ -> 프로젝트 규모, 배포 횟수에 따라 전송 시간이 늘어날 수 있고 필요한 파일을 삭제할 가능성이 있음
   2. `rsync --delete` 옵션을 deploy.yml에 추가해 제거된 파일도 함께 동기화 할 수 있도록 수정함
   3. 또한 Docker Composer 빌드 시 **변경 감지를 통해 수정 사항만 새로 빌드할 수 있도록 기능을 추가**함
   ```
   # deploy.yml
   name: Deploy to Ubuntu Server
   
   on:
     push:
       branches:
         - main
         - develop
   
   jobs:
     deploy:
       runs-on: ubuntu-latest
   
       steps:
         - name: Checkout code
           uses: actions/checkout@v3
           with:
             fetch-depth: 2  # 변경 감지를 위한 이전 파일 확인
   
         - name: Set up SSH
           uses: webfactory/ssh-agent@v0.7.0
           with:
             ssh-private-key: ${{ secrets.SSH_PRIVATE_KEY }}
   
         - name: Copy files via SSH with file deletion # 필요한 파일을 Git Repository에서 받아오되 삭제된 파일도 적용할 수 있도록 수정함
           run: |
             rsync -avz --delete -e "ssh -o StrictHostKeyChecking=no" ./ ${{ secrets.USER }}@${{ secrets.HOST }}:/home/leesh/Sparta/AdjustmentService
   
         - name: Install Docker Compose
           run: |
             ssh -o StrictHostKeyChecking=no ${{ secrets.USER }}@${{ secrets.HOST }} 'bash -s' << 'EOF'
             curl -L "https://github.com/docker/compose/releases/download/v2.6.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
             chmod +x /usr/local/bin/docker-compose
             EOF
   
         - name: Deploy with Docker Compose
           run: |
             ssh -o StrictHostKeyChecking=no ${{ secrets.USER }}@${{ secrets.HOST }} << 'EOF'
             cd /home/leesh/Sparta/AdjustmentService
             
             # 환경 변수 저장
             echo "MYSQL_ROOT_PASSWORD=${{ secrets.MYSQL_ROOT_PASSWORD }}" > .env
             echo "MYSQL_DATABASE=${{ secrets.MYSQL_DATABASE }}" >> .env
             echo "MYSQL_USER=${{ secrets.MYSQL_USER }}" >> .env
             echo "MYSQL_PASSWORD=${{ secrets.MYSQL_PASSWORD }}" >> .env
             echo "REDIS_HOST=${{ secrets.REDIS_HOST }}" >> .env
             echo "REDIS_PORT=${{ secrets.REDIS_PORT }}" >> .env
             echo "JWT_SECRET_KEY=${{ secrets.JWT_SECRET_KEY }}" >> .env
             echo "KAKAO_CLIENT_ID=${{ secrets.KAKAO_CLIENT_ID }}" >> .env
             echo "KAKAO_REDIRECT_URI=${{ secrets.KAKAO_REDIRECT_URI }}" >> .env
             
             # Detect which modules changed
             changed_modules=$(git diff --name-only HEAD^ HEAD)
   
             # Build all services if module-common changed
             if echo "$changed_modules" | grep -q '^module-common/'; then
               echo "Changes detected in common module"
               docker-compose build user-service
               docker-compose build video-service
               docker-compose build adjust-service
               docker-compose build batch-service
               docker-compose build eureka-server
               docker-compose build common
             else
               # Build only if specific directories have changed
               if echo "$changed_modules" | grep -q '^module-user/'; then
                 echo "Changes detected in user-service"
                 docker-compose build user-service
               fi
   
               if echo "$changed_modules" | grep -q '^module-video/'; then
                 echo "Changes detected in video-service"
                 docker-compose build video-service
               fi
   
               if echo "$changed_modules" | grep -q '^module-adjust/'; then
                 echo "Changes detected in adjust-service"
                 docker-compose build adjust-service
               fi
             
               if echo "$changed_modules" | grep -q '^module-batch/'; then
                 echo "Changes detected in batch-service"
                 docker-compose build batch-service
               fi
             
               if echo "$changed_modules" | grep -q '^module-eureka/'; then
                 echo "Changes detected in eureka-service"
                 docker-compose build eureka-server
               fi
             fi
             
             docker-compose up -d
             EOF
   ```
</details>

<details>
<summary><strong>Eureka static resource - 404 Error</strong></summary>
   
   💡 **문제** : Eureka 페이지에서 js와 css와 같은 Static Resource를 찾지 못하는 문제<br>
   ❌ **원인** : Nginx가 Eureka 서버에 프록시를 적용하는데 **Static Resource가 다른 경로로 연결**되어 있어 정상적으로 페이지가 나타나지 않음<br>
   ✔️ **해결** : Nginx에 **Static Resource에 대한 추가적인 프록시 설정**을 넣음

   ```
    location /adjustment/eureka/ {
        proxy_pass http://localhost:8761/;  # Eureka 프록시
     }

    location /eureka/ { 
        proxy_pass http://localhost:8761/eureka/; # # Eureka Resource 프록시
    }
   ```
</details>

<details>
<summary><strong>배치 작업 최적화</strong></summary>
   
   💡 **문제** : 기존 정산 처리 작업에서 비동기 처리 방식으로 진행했으나, 소요 시간이 약 58분으로 오래 걸림<br>
   ❌ **원인** : 작업 자체의 복잡함으로 데이터 수가 증가하면서 Batch 작업의 소요시간이 급증함<br>
   ✔️ **해결** : **멀티 스레드를 적용**해 10개 스레드가 동시 처리하도록 조정 (소요시간 28분)  
   
   ```java
   import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Transactional
public void setDailySummaryWithThreads() {
    logger.info("멀티스레드 작업 시작");

    LocalDateTime todayStart = LocalDate.now().atStartOfDay();  // 오늘 0시 0분 0초
    LocalDateTime yesterdayStart = todayStart.minusDays(1);  // 어제 0시 0분 0초
    LocalDateTime weekStart = todayStart.minusWeeks(1);  // 1주일 전 (저번주 시작)
    LocalDateTime monthStart = todayStart.minusMonths(1);  // 한 달 전 (지난달 시작)

    logger.info("오늘 시작 시간: {}", todayStart);
    logger.info("어제 시작 시간: {}", yesterdayStart);
    logger.info("1주일 전 시작 시간: {}", weekStart);
    logger.info("1달 전 시작 시간: {}", monthStart);

    List<Video> videos = videoRepository.findAll();
    logger.info("총 {} 개의 비디오에 대해 정산을 진행합니다.", videos.size());

    ExecutorService executorService = Executors.newFixedThreadPool(10);  // 스레드 풀 크기 설정 (여기서는 10개)

    for (Video video : videos) {
        executorService.submit(() -> {
            logger.info("비디오 ID: {} 처리 시작 (스레드 ID: {})", video.getId(), Thread.currentThread().getId());

            long videoDailyViewCount = 0L;
            long videoWeeklyViewCount = 0L;
            long videoMonthlyViewCount = 0L;
            long videoDailyProfit = 0L;
            long videoWeeklyProfit = 0L;
            long videoMonthlyProfit = 0L;
            long adDailyViewCount = 0L;
            long adWeeklyViewCount = 0L;
            long adMonthlyViewCount = 0L;
            long adDailyProfit = 0L;
            long adWeeklyProfit = 0L;
            long adMonthlyProfit = 0L;

            Optional<DailyRecord> todayRecordOptional = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(todayStart), video);
            Optional<DailyRecord> yesterdayRecordOptional = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(yesterdayStart), video);

            if (todayRecordOptional.isPresent()) {
                DailyRecord todayRecord = todayRecordOptional.get();
                if (yesterdayRecordOptional.isPresent()) {
                    DailyRecord yesterdayRecord = yesterdayRecordOptional.get();
                    videoDailyViewCount = todayRecord.getTotalVideoViews() - yesterdayRecord.getTotalVideoViews();
                    adDailyViewCount = todayRecord.getTotalAdViews() - yesterdayRecord.getTotalAdViews();
                    logger.info("비디오 {}의 일간 조회수 차이: {}", video.getId(), videoDailyViewCount);
                    logger.info("광고 {}의 일간 조회수 차이: {}", video.getId(), adDailyViewCount);
                }

                Optional<DailyRecord> prevWeekRecord = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(weekStart), video);
                if (prevWeekRecord.isPresent()) {
                    DailyRecord prevRecord = prevWeekRecord.get();
                    videoWeeklyViewCount = todayRecord.getTotalVideoViews() - prevRecord.getTotalVideoViews();
                    adWeeklyViewCount = todayRecord.getTotalAdViews() - prevRecord.getTotalAdViews();
                    logger.info("비디오 {}의 주간 조회수 차이: {}", video.getId(), videoWeeklyViewCount);
                    logger.info("광고 {}의 주간 조회수 차이: {}", video.getId(), adWeeklyViewCount);
                }

                Optional<DailyRecord> prevMonthRecord = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(monthStart), video);
                if (prevMonthRecord.isPresent()) {
                    DailyRecord prevMonthRecordValue = prevMonthRecord.get();
                    videoMonthlyViewCount = todayRecord.getTotalVideoViews() - prevMonthRecordValue.getTotalVideoViews();
                    adMonthlyViewCount = todayRecord.getTotalAdViews() - prevMonthRecordValue.getTotalAdViews();
                    logger.info("비디오 {}의 월간 조회수 차이: {}", video.getId(), videoMonthlyViewCount);
                    logger.info("광고 {}의 월간 조회수 차이: {}", video.getId(), adMonthlyViewCount);
                }
            }

            // 이미 존재하는 정산이면 업데이트
            Optional<DailySummary> existingSummary = dailySummaryRepository.findByDateAndVideo(Timestamp.valueOf(todayStart), video);
            if (existingSummary.isPresent()) {
                DailySummary summary = existingSummary.get();
                summary.setVideoDailyViewCount(videoDailyViewCount);
                summary.setVideoWeeklyViewCount(videoWeeklyViewCount);
                summary.setVideoMonthlyViewCount(videoMonthlyViewCount);
                summary.setVideoDailyProfit(videoDailyProfit);
                summary.setVideoWeeklyProfit(videoWeeklyProfit);
                summary.setVideoMonthlyProfit(videoMonthlyProfit);
                summary.setAdDailyViewCount(adDailyViewCount);
                summary.setAdWeeklyViewCount(adWeeklyViewCount);
                summary.setAdMonthlyViewCount(adMonthlyViewCount);
                summary.setAdDailyProfit(adDailyProfit);
                summary.setAdWeeklyProfit(adWeeklyProfit);
                summary.setAdMonthlyProfit(adMonthlyProfit);
                logger.info("비디오 {}의 기존 정산 업데이트", video.getId());
            } else {
                // 새로운 정산 기록을 추가
                DailySummary newSummary = new DailySummary(Timestamp.valueOf(todayStart), video,
                        videoDailyViewCount, videoWeeklyViewCount, videoMonthlyViewCount,
                        adDailyViewCount, adWeeklyViewCount, adMonthlyViewCount);
                dailySummaryRepository.save(newSummary);
                logger.info("비디오 {}의 새로운 정산 기록 추가", video.getId());
            }

            logger.info("비디오 ID: {} 처리 완료 (스레드 ID: {})", video.getId(), Thread.currentThread().getId());
        });
    }

    executorService.shutdown();  // 모든 작업이 끝난 후 ExecutorService 종료
    logger.info("멀티스레드 작업 종료");
}

   ```
</details>


## 🔗 **성능 측정**

### 개요

- **측정 목표** : `Batch Process`를 통해 미리 생성된 정산의 **응답 속도 효율 실험**
- **측정 대상** : 사용자의 영상과 광고에 대한 **조회수와 정산 금액 호출** 기능
- **측정 도구** : `JMeter`

### 측정 과정

1. **`Python`기반의 테스트 데이터 생성**
    <details>
    <summary>Python 코드</summary>
    
    ```python
    import pymysql
    import random
    import faker
    from datetime import datetime, timedelta
    import pytz  # 타임존 관리 라이브러리
    
    # MySQL 연결
    connection = pymysql.connect(
        host='**',  # MySQL 컨테이너의 IP 주소
        user='**',        # MySQL 사용자
        password='**',    # MySQL 비밀번호
        database='**'  # 사용하려는 데이터베이스 이름
    )
    
    # Faker 라이브러리로 랜덤 데이터 생성
    fake = faker.Faker()
    
    # KST (Asia/Seoul) 타임존 설정
    kst = pytz.timezone('Asia/Seoul')
    
    def generate_random_video_sql(user_id):
        # 랜덤 제목, URL, 썸네일 생성
        title = fake.sentence(nb_words=5)  # 5개의 단어로 이루어진 랜덤 문장
        url = fake.url()
        thumbnail = fake.image_url()
        duration = random.randint(10, 200)  # 10초에서 200초 사이의 랜덤 영상 길이
    
        # 랜덤으로 1년 이내의 날짜 생성
        today = datetime.today()
        random_days = random.randint(0, 365)  # 0일부터 365일까지 랜덤
        random_date = today - timedelta(days=random_days)
    
        # 전날 15시로 시간 설정
        random_date = random_date.replace(hour=15, minute=0, second=0, microsecond=0)
    
        # KST 타임존에 맞게 로컬라이즈
        random_date_kst = kst.localize(random_date)
        registration_date = random_date_kst.strftime('%Y-%m-%d %H:%M:%S')
    
        # SQL 생성
        sql = f"""
            INSERT INTO video (url, thumbnail, user_id, title, view_count, registration_date, duration)
            VALUES ('{url}', '{thumbnail}', {user_id}, '{title}', 0, '{registration_date}', {duration});
        """
        return sql
    
    def generate_random_adlist_sql(video_id):
        # 광고 ID는 2008부터 2108까지 범위에서 랜덤으로 선택
        ad_ids = random.sample(range(2008, 2107), random.randint(1, 5))  # 1개에서 5개 사이의 랜덤 광고 선택
    
        # AdList SQL 생성
        adlist_sqls = []
        for ad_id in ad_ids:
            adlist_sql = f"""
                INSERT INTO ad_list (video_id, ad_id, view_count)
                VALUES ({video_id}, {ad_id}, 0);
            """
            adlist_sqls.append(adlist_sql)
    
        return adlist_sqls
    
    def generate_daily_record_sql(video_id, start_date, today):
        # start_date부터 오늘까지 하루씩 증가하는 데이터를 생성
        daily_records = []
    
        total_video_views = 0
        total_ad_views = 0
    
        current_date = start_date
        while current_date <= today:
            # 전날 15시로 설정
            current_date = current_date.replace(hour=15, minute=0, second=0, microsecond=0)
    
            # 영상 조회수와 광고 조회수 랜덤 증가
            daily_video_views = random.randint(100, 1000)  # 100 ~ 1000 사이의 랜덤 증가
            daily_ad_views = random.randint(10, 100)  # 10 ~ 100 사이의 랜덤 증가
    
            # 총합 업데이트
            total_video_views += daily_video_views
            total_ad_views += daily_ad_views
    
            # DailyRecord SQL 생성
            daily_record_sql = f"""
                INSERT INTO daily_record (date, video_id, total_video_views, total_ad_views)
                VALUES ('{current_date.strftime('%Y-%m-%d %H:%M:%S')}', {video_id}, {total_video_views}, {total_ad_views});
            """
            daily_records.append(daily_record_sql)
    
            # 하루씩 증가
            current_date += timedelta(days=1)
    
        return daily_records
    
    try:
        with connection.cursor() as cursor:
            # 사용자 ID는 1
            user_id = 1
    
            # 50개의 영상 데이터를 생성하고 관계 테이블에 추가
            for _ in range(47):
                # 영상 데이터 생성 및 삽입
                video_sql = generate_random_video_sql(user_id)
                cursor.execute(video_sql)
                # 생성된 영상의 ID 가져오기
                video_id = cursor.lastrowid
    
                # 생성된 영상에 대해 일자별 DailyRecord 데이터 삽입
                start_date = datetime.strptime("2023-11-01", "%Y-%m-%d")  # 예시로 11월 1일 시작
                today = datetime.today()  # 오늘 날짜
                daily_record_sqls = generate_daily_record_sql(video_id, start_date, today)
                for daily_record_sql in daily_record_sqls:
                    cursor.execute(daily_record_sql)
    
                # 광고-영상 관계 삽입
                adlist_sqls = generate_random_adlist_sql(video_id)
                for adlist_sql in adlist_sqls:
                    cursor.execute(adlist_sql)
    
                print(f"Video {video_id} added successfully!")
    
            connection.commit()  # 커밋하여 변경 사항 반영
    finally:
        connection.close()
    ```
      </details>

   - **영상 데이터 생성**
     - 영상의 제목, URL, 썸네일을 랜덤으로 생성
     - 영상 길이는 10초에서 200초 사이의 랜덤 값으로 설정
     - 등록일은 오늘 날짜를 기준으로 0일부터 365일 사이의 랜덤 날짜로 설정
   - **광고-영상 관계 데이터 생성**
     - 광고 ID를 100개의 실제 광고 데이터 중 1개에서 5개 사이의 랜덤 값으로 설정
   - **일별 기록 데이터 생성**
     - 영상 생성일부터 오늘까지 하루씩 증가하는 데이터를 생성
     - 영상 조회수는 각 날짜마다 100에서 1000 사이의 랜덤값으로 증가
     - 광고 조회수는 각 날짜마다 10에서 100 사이의 랜덤값으로 증가

3. **배치 작업 수행**
   - 오늘 날짜로 배치 작업을 실행하여, 데이터의 배치 결과를 처리
   - 실제 작업은 Batch 스케줄러로 매일 진행됨

4. **실험 환경 설정**
   - JMeter에서 연결 테스트 및 반환 결과 검증

5. **실험 진행**
   - **단일 사용자 실험** (1명이 100번 반복)
     - **즉시 로딩**을 통해 요청을 진행하고 측정
     - **배치된 결과 로딩**을 통해 요청을 진행하고 측정
   - **다중 사용자 실험** (100명이 1번씩 반복)
     - **즉시 로딩**을 통해 요청을 진행하고 측정
     - **배치된 결과 로딩**을 통해 요청을 진행하고 측정
   - **데이터 수 확장**
     - **50개** 데이터에서 시작하여, **100개**, **1000개**, **10000개**로 데이터 수를 증가

6. **결과 분석**
   - 단일 사용자 및 다중 사용자 각각의 **응답 시간**을 비교하여 배치 처리 방식이 성능에 미치는 영향을 분석
   - 각 데이터 수에 따른 **응답 시간**을 분석하여, 배치 처리의 효과를 시각적으로 확인

### 측정 결과

- **데이터 수 50개**
  - **단일 사용자**
    - **즉시**: 평균 응답 시간 56ms, 최소 48ms, 최대 104ms
    - **배치**: 평균 응답 시간 33ms, 최소 23ms, 최대 110ms
  - **다중 사용자**
    - **즉시**: 평균 응답 시간 796ms, 최소 139ms, 최대 2255ms
    - **배치**: 평균 응답 시간 568ms, 최소 32ms, 최대 1603ms

- **데이터 수 100개**
  - **단일 사용자**
    - **즉시**: 평균 응답 시간 97ms, 최소 87ms, 최대 150ms
    - **배치**: 평균 응답 시간 46ms, 최소 36ms, 최대 166ms
  - **다중 사용자**
    - **즉시**: 평균 응답 시간 1622ms, 최소 286ms, 최대 4131ms
    - **배치**: 평균 응답 시간 1321ms, 최소 108ms, 최대 4113ms

- **데이터 수 1000개**
  - **단일 사용자**
    - **즉시**: 평균 응답 시간 201ms, 최소 173ms, 최대 318ms
    - **배치**: 평균 응답 시간 29ms, 최소 17ms, 최대 120ms
  - **다중 사용자**
    - **즉시**: 평균 응답 시간 2735ms, 최소 642ms, 최대 5903ms
    - **배치**: 평균 응답 시간 2067ms, 최소 144ms, 최대 5965ms

- **데이터 수 10000개**
  - **단일 사용자**
    - **즉시**: 평균 응답 시간 781ms, 최소 656ms, 최대 1459ms
    - **배치**: 평균 응답 시간 78ms, 최소 64ms, 최대 208ms
  - **다중 사용자**
    - **즉시**: 평균 응답 시간 13472ms, 최소 2087ms, 최대 11962ms
    - **배치**: 평균 응답 시간 6541ms, 최소 546ms, 최대 11612ms

### 성능 향상 분석

- **단일 사용자 실험**
  - 데이터 수가 적을수록 **즉시 처리**와 **배치 처리**의 차이가 크지 않지만, 데이터 수가 많아질수록 **배치 처리**의 성능 향상 효과가 두드러짐.
  - **1000개 이상**의 데이터에서 배치 처리 방식이 **응답 시간**을 크게 단축시킴.
  - **10000개 데이터**에서는 배치 처리 방식이 **응답 시간**을 **85% 이상** 향상시킴.

- **다중 사용자 실험**
  - 다중 사용자 환경에서는 배치 처리의 효과가 더 크게 나타나며, 특히 **응답 시간**에서 **30% 이상** 향상이 발생.
  - 배치 처리 방식은 대량 데이터 요청 시 성능을 크게 향상시키고, **서버 자원**을 효율적으로 활용할 수 있게 도와줌.

### 결론

배치 처리 방식은 특히 데이터 양이 많을 경우 성능을 크게 향상시킬 수 있는 방법입니다. 실험 결과, **즉시 로딩**보다 **배치된 결과 로딩**이 **응답 속도** 면에서 유리하며, 데이터 수가 많아질수록 그 차이는 더욱 두드러집니다. 다중 사용자 환경에서도 배치 처리의 이점이 확연히 나타나며, 서버 성능과 사용자 경험을 최적화하는 데 중요한 역할을 합니다.
