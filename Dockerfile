#FROM maven:3.8.4-openjdk-11 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#COPY src ./src/main/resources/json/*
#RUN mvn package

#FROM openjdk:11-jre-slim
#WORKDIR /app
#COPY --from=build /app/target/meteo-0.0.1-SNAPSHOT.jar app.jar
#CMD ["java", "-jar", "app.jar"]
# Etapa de construcción
FROM maven:3.8.4-openjdk-11-slim AS builder
WORKDIR /app
COPY ./ /app/
RUN mvn clean package

# Etapa de ejecución
FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=builder /app/target/meteo-0.0.1-SNAPSHOT.jar /app/
#para definir el argumento del puedo 
#ARG PORT=8080
ENV PORT 9092
EXPOSE 9092
#ENV SERVE_PORT $PORT
#EXPOSE $SERVE_PORT
CMD ["sh", "-c", "java -Dserver.port=${PORT} -jar /app/meteo-0.0.2.jar"]

#kubectl apply -f deployment.yaml
#kubectl apply -f service.yaml
#kubectl delete pod meteojson-56465d54d7-8ggwt
#kubectl delete pod meteojson-d66c9d6d8-82rmb
#kubectl delete pod meteojson-d66c9d6d8-9drd2
#kubectl delete pod meteojson-d66c9d6d8-llpzk
#docker build --build-arg PORT=9090 -t meteoconjson1 .
#docker build -t tu-repositorio/proyecto_principal:latest .
#docker run -p 8080:8080 meteoconjson
