# Estágio de compilação
FROM maven:3.9.6-eclipse-temurin-17-focal as BUILD
COPY src /usr/src/myapp/src
COPY pom.xml /usr/src/myapp
RUN mvn -f /usr/src/myapp/pom.xml clean package

# Estágio de implantação
FROM tomcat:7.0
COPY --from=BUILD /usr/src/myapp/target/vcRiquinho-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/vcRiquinho.war
