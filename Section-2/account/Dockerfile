# Start with a base image containing java runtime
FROM openjdk:17-jdk-slim

# Information around who maintains the image
MAINTAINER krishtech.com

# Add the applications jar to the image
COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "account-0.0.1-SNAPSHOT.jar"]
