# Adjustment Service

## 📜 프로젝트 개요: **정산 시스템**
>
>이 프로젝트는 **2024 스파르타 코딩클럽 동북 이노베이션 캠프**에서 진행된 개인 프로젝트입니다.
>고난이도의 목표를 선정하고 문제 해결 과정을 기록하며 도전적인 과제를 수행했습니다.
>
자원이 한정된 환경에서 엔지니어는 효율적으로 시스템을 관리해야 합니다. 이 프로젝트의 핵심은 많은 사용자들을 처리하면서도 **복잡한 로직**을 매일 우선적으로 처리하는 방식으로 시스템을 최적화하는 방법을 연구하는 것입니다. **유튜브**를 모티브로 한 **스트리밍 정산 시스템**을 개발하였으며, 다음과 같은 주요 기능들을 포함합니다:

### 주요 기능
1. **회원 서비스** 
   - 회원가입 및 계정 구분
   - 로그인 및 로그아웃 기능

2. **스트리밍 서비스** 
   - 특정 동영상 재생 및 조회수 증가
   - 동영상 재생 시간 및 재생 시점 기록

3. **광고 재생** 
   - 동영상에 등록된 광고를 재생하고 광고 조회수 증가

4. **통계 및 정산** 
   - 1일, 1주일, 1달 단위로 조회수와 재생 시간이 높은 Top 5 영상 기록
   - 동영상과 광고 조회수를 기반으로 **정산 금액 산정**
  
### Link


## 🗓️ 개발 기간과 환경

- **개발 기간**: 2024년 8월 19일부터 2024년 9월 13일 (4주간)

- **개발 환경**:
  - **운영 체제**: Windows, Ubuntu
  - **프로그래밍 언어**: Java
  - **프레임워크**: Spring Boot 3.1.5
  - **데이터베이스**: MySQL
  - **배포 환경**: Docker

## 🛠️ 사용 기술과 선정 이유

| 기술                                                                                                  | 설명                                                                 | 선정 이유                                                                                              |
|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)    | Java 기반의 애플리케이션을 빠르고 쉽게 개발할 수 있는 프레임워크       | 복잡한 웹 애플리케이션을 간단하게 구축하고, 빠르게 배포할 수 있는 기능을 제공하기 때문에 선택했습니다.    |
| ![Spring Eureka](https://img.shields.io/badge/Spring%20Eureka-6DB33F?style=flat&logo=spring&logoColor=white)     | 마이크로서비스 아키텍처에서 서비스 디스커버리를 위한 솔루션           | 마이크로서비스 환경에서 각 서비스가 서로를 자동으로 찾을 수 있도록 하기 위해 Eureka를 도입했습니다.      |
| ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat&logo=spring-security&logoColor=white) | Spring 기반 애플리케이션에 인증과 권한 관리를 제공하는 보안 프레임워크  | 회원 서비스와 스트리밍 서비스에 안전한 인증 및 권한 관리를 구현하기 위해 선택했습니다.                    |
| ![Spring Batch](https://img.shields.io/badge/Spring%20Batch-6DB33F?style=flat&logo=spring&logoColor=white)    | 대량의 데이터를 효율적으로 처리하기 위한 배치 프로세스 프레임워크         | 정산 과정을 특정한 시간에 자동으로 수행하여 자원을 효율적으로 관리하고, 업무의 자동화를 지원하기 위해 선택했습니다. |
| ![Docker Compose](https://img.shields.io/badge/Docker%20Compose-2496ED?style=flat&logo=docker&logoColor=white)  | 여러 개의 컨테이너를 한 번에 관리할 수 있는 Docker 도구               | 다양한 서비스 (DB, Redis 등) 환경을 손쉽게 구성하고, 배포 프로세스를 자동화하기 위해 Docker Compose를 사용했습니다.|
| ![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat&logo=redis&logoColor=white)              | 메모리 기반의 고속 데이터 저장소                                      | 세션 관리 및 캐싱 기능을 빠르게 처리하기 위해 사용했습니다. 특히 회원 인증 및 스트리밍 기록 관리에 적합합니다.|
| ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)              | 관계형 데이터베이스 관리 시스템                                        | 사용자, 동영상, 광고 등의 데이터를 안정적으로 저장하고 관리하기 위해 선택한 데이터베이스입니다.           |
| ![JMeter](https://img.shields.io/badge/JMeter-0A7E32?style=flat&logo=apache&logoColor=white)           | 성능 테스트 및 부하 테스트를 위한 도구                                 | 대량 데이터 주입 및 사용자 테스트를 통해 애플리케이션의 성능과 안정성을 평가하기 위해 JMeter를 사용했습니다.       |
| ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white)           | 버전 관리 시스템으로 코드와 협업 도구 제공                            | 코드 버전 관리를 위해 사용했으며 Readme를 통해 프로젝트의 과정과 결과에 대해 설명합니다.          |
| ![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat&logo=github-actions&logoColor=white) | GitHub에서 제공하는 CI/CD 자동화 도구                                | 애플리케이션을 자동으로 빌드하고 배포하는 CI/CD 파이프라인을 구축하기 위해 사용했습니다.                  |

## 🗂️ 프로젝트 구조
<details>
<summary>File Structure</summary>
<pre>
📦adjustment
 ┣ 📂.github
 ┃ ┗ 📂workflows
 ┃ ┃ ┗ 📜deploy.yml
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
 ┃ ┃ ┃   ┗ 📜application-profile.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 📜build.gradle
 ┃ ┗ 📜Dockerfile
 ┃ ┗ 📜settings.gradle
 ┣ 📂module-batch
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.config
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ 📜application-profile.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 📜build.gradle
 ┃ ┗ 📜Dockerfile
 ┃ ┗ 📜settings.gradle
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
 ┃ ┃ ┃   ┗ 📜application.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 📜build.gradle
 ┣ 📂module-eureka
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┗ 📂java.com.sparta
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ 📜application.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 📜build.gradle
 ┃ ┗ 📜Dockerfile
 ┃ ┗ 📜settings.gradle
 ┣ 📂module-user
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.controller
 ┃ ┃ ┃ ┣ 📂java.com.sparta.service
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ 📜application-profile.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 📜build.gradle
 ┃ ┗ 📜Dockerfile
 ┃ ┗ 📜settings.gradle
 ┣ 📂module-video
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java.com.sparta.controller
 ┃ ┃ ┃ ┣ 📂java.com.sparta.service
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃   ┗ 📜application-profile.properties
 ┃ ┃ ┗ 📂test
 ┃ ┗ 📜build.gradle
 ┃ ┗ 📜Dockerfile
 ┃ ┗ 📜settings.gradle
 ┣ 📜.gitignore
 ┣ 📜build.gradle
 ┣ 📜docker-compose.yml
 ┣ 📜gradlew
 ┣ 📜gradlew.bat
 ┣ 📜HELP.md
 ┗ 📜settings.gradle 
</pre>
</details>

### 모듈별 설명
| 모듈 이름        | 설명                                                            |
|------------------|-----------------------------------------------------------------|
| **module-adjust** | 정산 과정을 이행하고 이에 대해 결과를 데이터베이스에 저장하고 불러올 수 있는 기능의 모듈. |
| **module-batch**  | 각 정산 과정을 특정한 시간에 이행할 수 있도록 구현한 batch 모듈. |
| **module-common** | 모든 모듈에 공통적으로 적용되는 기능(인증, 인가, 레디스, 핸들러)을 관리하는 모듈. |
| **module-eureka** | 각 모듈에 대해 유레카 클라우드의 서버 역할을 수행하는 모듈. |
| **module-user**   | 사용자 로그인 및 개인 정보를 확인하는 기능 모듈.              |
| **module-video**  | 영상 및 광고를 업로드하고 이를 재생 및 영상 시청 시점을 기록하는 기능 모듈. |

## 🤔 트러블 슈팅
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

## 🎓 질문과 답변

<details>
<summary><strong>MSA에 대한 이해</strong></summary>
</details>
