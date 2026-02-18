FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/Grupp_5_Projektarbete-1.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]