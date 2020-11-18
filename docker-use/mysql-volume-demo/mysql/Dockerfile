FROM bitnami/mysql:5.7
MAINTAINER lwf
COPY init.sql /docker-entrypoint-initdb.d

USER root

RUN chmod a+x /docker-entrypoint-initdb.d/init.sql

EXPOSE 3360


