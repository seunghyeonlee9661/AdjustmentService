## What is this?
이 프로젝트는 **2024 스파르타 코딩클럽 동북 이노베이션 캠프**에서 진행된 개인 프로젝트입니다. 고난이도의 목표를 선정하고 문제 해결 과정을 기록하며 도전적인 과제를 수행했습니다.

## 프로젝트 개요: **정산 시스템**
자원이 한정된 환경에서 엔지니어는 효율적으로 시스템을 관리해야 합니다. 이 프로젝트의 핵심은 많은 사용자들을 처리하면서도 **복잡한 로직**을 매일 우선적으로 처리하는 방식으로 시스템을 최적화하는 방법을 연구하는 것입니다.
**유튜브**를 모티브로 한 **스트리밍 정산 시스템**을 개발하였으며, 다음과 같은 주요 기능들을 포함합니다:

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
  
## 개발 기간과 환경

- **개발 기간**: 2024년 8월 19일부터 2024년 9월 13일 (4주간)

- **개발 환경**:
  - **운영 체제**: Windows, Ubuntu
  - **프로그래밍 언어**: Java
  - **프레임워크**: Spring Boot 3.1.5
  - **데이터베이스**: MySQL
  - **배포 환경**: Docker

## 사용 기술과 선정 이유

| 기술                                                                                                  | 설명                                                                 | 선정 이유                                                                                              |
|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)    | Java 기반의 애플리케이션을 빠르고 쉽게 개발할 수 있는 프레임워크       | 복잡한 웹 애플리케이션을 간단하게 구축하고, 빠르게 배포할 수 있는 기능을 제공하기 때문에 선택했습니다.    |
| ![Spring Eureka](https://img.shields.io/badge/Spring%20Eureka-6DB33F?style=flat&logo=spring&logoColor=white)     | 마이크로서비스 아키텍처에서 서비스 디스커버리를 위한 솔루션           | 마이크로서비스 환경에서 각 서비스가 서로를 자동으로 찾을 수 있도록 하기 위해 Eureka를 도입했습니다.      |
| ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat&logo=spring-security&logoColor=white) | Spring 기반 애플리케이션에 인증과 권한 관리를 제공하는 보안 프레임워크  | 회원 서비스와 스트리밍 서비스에 안전한 인증 및 권한 관리를 구현하기 위해 선택했습니다.                    |
| ![Spring Batch](https://img.shields.io/badge/Spring%20Batch-6DB33F?style=flat&logo=spring&logoColor=white)    | 대량의 데이터를 효율적으로 처리하기 위한 배치 프로세스 프레임워크         | 정산 과정을 특정한 시간에 자동으로 수행하여 자원을 효율적으로 관리하고, 업무의 자동화를 지원하기 위해 선택했습니다. |
| ![Docker Compose](https://img.shields.io/badge/Docker%20Compose-2496ED?style=flat&logo=docker&logoColor=white)  | 여러 개의 컨테이너를 한 번에 관리할 수 있는 Docker 도구               | 다양한 서비스 (DB, Redis 등) 환경을 손쉽게 구성하고, 배포 프로세스를 자동화하기 위해 Docker Compose를 사용했습니다.|
| ![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat&logo=redis&logoColor=white)              | 메모리 기반의 고속 데이터 저장소                                      | 세션 관리 및 캐싱 기능을 빠르게 처리하기 위해 사용했습니다. 특히 회원 인증 및 스트리밍 기록 관리에 적합합니다.|
| ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)              | 관계형 데이터베이스 관리 시스템                                        | 사용자, 동영상, 광고 등의 데이터를 안정적으로 저장하고 관리하기 위해 선택한 데이터베이스입니다.           |
| ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white)           | 버전 관리 시스템으로 코드와 협업 도구 제공                            | 코드 버전 관리를 위해 사용했으며, 여러 가지 협업 도구를 통해 팀 간의 효율적인 협업을 지원합니다.          |
| ![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat&logo=github-actions&logoColor=white) | GitHub에서 제공하는 CI/CD 자동화 도구                                | 애플리케이션을 자동으로 빌드하고 배포하는 CI/CD 파이프라인을 구축하기 위해 사용했습니다.                  |

## 프로젝트 구조
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