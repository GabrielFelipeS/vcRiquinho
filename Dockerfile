# Caso preferir pode descomentar os comandos abaixo para compilar e implementar, lembrando de comentar os comandos do outro bloco

# Estágio de compilação e implantação
# FROM maven as BUILD
# COPY src /usr/src/myapp/src
# COPY pom.xml /usr/src/myapp
# RUN mvn -f /usr/src/myapp/pom.xml clean package
# FROM tomcat:7.0
# COPY --from=BUILD /usr/src/myapp/target/vcRiquinho-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/vcRiquinho.war


# Apenas estágio de implantação
FROM tomcat:7.0
COPY ./target/vcRiquinho-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/vcRiquinho.war