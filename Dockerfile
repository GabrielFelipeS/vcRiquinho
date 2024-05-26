# Apenas estágio de implantação
FROM tomcat:9.0
COPY ./target/vcRiquinho-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/vcRiquinho.war