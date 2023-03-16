FROM postgres:15.2-alpine
MAINTAINER ALEX

#ADD /product-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD  /ddl/*.sql /docker-entrypoint-initdb.d/
ADD  /dml/*.sql /docker-entrypoint-initdb.d/