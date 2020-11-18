FROM docker.io/tomcat:latest
MAINTAINER lwf
COPY target/tomcat-demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/app.war
EXPOSE 8880
ENTRYPOINT ["/usr/local/tomcat/bin/catalina.sh","run"]