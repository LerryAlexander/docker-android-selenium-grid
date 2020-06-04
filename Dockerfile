FROM gradle:5.2.1-jdk8 AS build
MAINTAINER Lerry laortizs@falabella.cl
COPY --chown=gradle:gradle . /docker_selenium_grid/
WORKDIR /docker_selenium_grid/
USER root
RUN adduser gradle sudo && \
    apt-get update && \
    apt-get -y install sudo && \
    ./gradlew build -x test && \
    sudo apt-get update -y && \
    sudo apt-get install -y jq
ENTRYPOINT ["/bin/sh"]
