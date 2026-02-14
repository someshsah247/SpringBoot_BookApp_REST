FROM eclipse-temurin:8-jdk
LABEL authors="someshsah"
WORKDIR /app
COPY target/bookapp.jar /app
EXPOSE 8080
CMD ["java", "-jar", "bookapp.jar"]