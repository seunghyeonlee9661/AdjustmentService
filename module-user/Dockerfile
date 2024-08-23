# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle files from the root context to the service context and verify each step
COPY gradlew /app/
RUN echo "Contents of /app after copying gradlew:" && ls -l /app/ && cat /app/gradlew

COPY gradle /app/gradle/
RUN echo "Contents of /app after copying gradle directory:" && ls -l /app/ && ls -l /app/gradle/

COPY settings.gradle /app/
RUN echo "Contents of /app after copying settings.gradle:" && ls -l /app/ && cat /app/settings.gradle

COPY build.gradle /app/
RUN echo "Contents of /app after copying build.gradle:" && ls -l /app/ && cat /app/build.gradle

# 공통 모듈의 파일
COPY module-common/build.gradle /app/module-common/
RUN echo "Contents of /app/module-common after copying build.gradle:" && ls -l /app/module-common/ && cat /app/module-common/build.gradle

COPY module-common/src /app/module-common/src
RUN echo "Contents of /app/module-common after copying src directory:" && ls -l /app/module-common/

# 모듈 내부 파일
COPY module-user/build.gradle /app/module-user/
RUN echo "Contents of /app/module-user after copying build.gradle:" && ls -l /app/module-user/ && cat /app/module-user/build.gradle

COPY module-user/src /app/module-user/src
RUN echo "Contents of /app/module-user after copying src directory:" && ls -l /app/module-user/

# Ensure gradlew is executable
RUN chmod +x gradlew

# Gradle wrapper 설정
RUN ./gradlew wrapper --gradle-version 8.8

# Build 디렉토리 초기화
RUN ./gradlew clean
RUN rm -rf build/

# 해당 모듈에 의존성을 가진 정보만 빌드하도록 설정
RUN ./gradlew :module-user:clean :module-user:build -x test --info

# Copy the built JAR file using the exact name
COPY module-user/build/libs/adjustment.module-user.jar app.jar
RUN echo "Contents of /app after copying the built JAR:" && ls -l /app/

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
