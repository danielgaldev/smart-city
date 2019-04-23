FROM openjdk:8-jdk-alpine

# Ant
WORKDIR /home
RUN wget https://www-us.apache.org/dist//ant/binaries/apache-ant-1.10.5-bin.tar.gz && \
    tar -xzvf apache-ant-1.10.5-bin.tar.gz

# Jason
RUN mkdir /home/jason
WORKDIR /home/jason
RUN wget https://kent.dl.sourceforge.net/project/jason/jason/version%202.3/jason-2.3.zip && \
    unzip jason-2.3.zip

ENV ANT_HOME="/home/apache-ant-1.10.5/"
ENV PATH="${ANT_HOME}/bin:/home/jason/scripts:${PATH}"

RUN mkdir /code && \
    apk add bash
WORKDIR /code

ENTRYPOINT [ "./docker-entrypoint.sh" ]
