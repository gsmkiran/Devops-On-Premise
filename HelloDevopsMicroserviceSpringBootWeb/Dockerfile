FROM isuper/java-oracle:jdk_latest
LABEL maintainer "Srinivasa Gulla"

# Install wget (for Maven install)
RUN apt-get update && apt-get install -y wget

RUN groupadd -r hdms && useradd -r -m -g hdms hdms

WORKDIR /app

# Copy over project source code
COPY pom.xml /app/pom.xml
COPY HelloDevopsMicroserviceSpringBootWeb/pom.xml /app/HelloDevopsMicroserviceSpringBootWeb/pom.xml
COPY HelloDevopsMicroserviceSpringBootWeb/src /app/HelloDevopsMicroserviceSpringBootWeb/src

RUN chown -R hwms. /app

USER hwms

ENV MAVEN_VERSION=3.5.0

# Install maven
RUN wget --quiet http://mirror.vorboss.net/apache/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz && tar zxf apache-maven-${MAVEN_VERSION}-bin.tar.gz

ENV MAVEN_HOME=/app/apache-maven-${MAVEN_VERSION}

# Compile and package all projects
RUN ${MAVEN_HOME}/bin/mvn clean compile package install -DskipTests

# Compile and package the Spring Boot Web project
WORKDIR /app/HelloWorldMicroserviceSpringBootWeb
RUN ${MAVEN_HOME}/bin/mvn clean compile package install -DskipTests
