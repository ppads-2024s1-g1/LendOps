# Step 1: Use an official Maven image to build the application
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Step 4: Package the application
RUN mvn clean package -DskipTests

# Step 5: Use a lightweight OpenJDK image to run the application
FROM openjdk:17-jdk-alpine

# Step 6: Set the working directory in the container
WORKDIR /app

# Step 7: Copy the packaged JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Step 8: Make port 8080 available to the world outside this container
EXPOSE 8080

# Step 9: Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
