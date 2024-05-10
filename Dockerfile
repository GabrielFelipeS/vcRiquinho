# Apenas estágio de implantação
FROM tomcat:7.0
COPY ./target/vcRiquinho-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/vcRiquinho.war