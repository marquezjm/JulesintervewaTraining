# Stage 1: Build the application using a Gradle-enabled image
# This stage has the full JDK and Gradle to compile our code and build the JAR.
FROM gradle:8.8.0-jdk17-jammy AS build

# Set the working directory inside the container
WORKDIR /home/gradle/src

# Copy the project files into the container
# --chown=gradle:gradle ensures the files are owned by the 'gradle' user, which is good practice.
COPY --chown=gradle:gradle . .

# Run the gradle build command to compile the code and create the executable JAR.
# We use 'build' which runs tests, and '--no-daemon' is recommended for CI/CD environments.
# The JAR will be located in build/libs/
RUN gradle build --no-daemon

# Stage 2: Create the final, smaller image for running the application
# We use a JRE (Java Runtime Environment) image which is smaller than a full JDK image.
FROM eclipse-temurin:17-jre-jammy

# Set the working directory for the application
WORKDIR /app

# Copy the executable JAR from the 'build' stage into the final image.
# Renaming it to app.jar makes the ENTRYPOINT command simpler.
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

# Expose port 8080, which is the default port for Spring Boot applications.
EXPOSE 8080

# The command to run when the container starts.
# This executes the Spring Boot application.
ENTRYPOINT ["java", "-jar", "app.jar"]
