FROM java:openjdk-8-jre-alpine
MAINTAINER lwf
COPY target/springboot-demo-0.0.1-SNAPSHOT.jar /home/app.jar

WORKDIR /home
RUN chmod +x app.jar
#RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENV ARGS=""

EXPOSE 8080
ENTRYPOINT ["sh","-c","java -jar /home/app.jar $ARGS"]

