FROM maven:3.9 AS build
WORKDIR /app
COPY src/backend/pom.xml .
RUN mvn dependency:go-offline -B
COPY src/backend/src ./src
RUN mvn package -DskipTests

FROM gcr.io/distroless/java17
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
CMD ["app.jar"]