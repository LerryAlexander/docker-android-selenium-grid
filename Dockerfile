FROM maven:3.8-jdk-8 AS build
MAINTAINER Lerry laortizs@falabella.cl
WORKDIR /docker_selenium_grid/
COPY . /docker_selenium_grid
VOLUME /var/run/docker.sock:/var/run/docker.sock
USER root
RUN apt-get update && \
    apt-get -y install sudo && \
    apt-get update -y && \
    apt-get install -y jq && \
    curl -sSL https://get.docker.com/ | sh
ENTRYPOINT ["tail", "-f", "/dev/null"]