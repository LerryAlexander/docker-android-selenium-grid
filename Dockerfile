#FROM gradle:jdk9 AS build
FROM gradle:5.2.1-jdk8 AS build
MAINTAINER Lerry laortizs@falabella.cl
COPY --chown=gradle:gradle . /docker_selenium_grid/
WORKDIR /docker_selenium_grid/
RUN ./gradlew build -x test
#EXPOSE 8080
ENTRYPOINT ["/bin/bash"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]