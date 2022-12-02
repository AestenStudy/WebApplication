FROM openjdk:17-jdk-slim
EXPOSE 8080
RUN addgroup spring && useradd -g spring spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]