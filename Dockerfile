FROM gradle:jdk9 AS build
MAINTAINER Lerry laortizs@falabella.cl
COPY --chown=gradle:gradle . /docker_selenium_grid/
WORKDIR /docker_selenium_grid/
RUN gradle build -x test
EXPOSE 8080
ENTRYPOINT ["/bin/bash"]