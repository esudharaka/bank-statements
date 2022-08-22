FROM openjdk:17-jdk-slim-buster
WORKDIR /app

#COPY app/build/lib/* build/lib/

COPY target/nagarro-test-1.0.jar build/
COPY accountsdb.accdb build/

WORKDIR /app/build
ENTRYPOINT java -jar nagarro-test-1.0.jar