# Building docker
FROM gradle:8.0.2-jdk17
WORKDIR /home/gradle/project
COPY ./ /home/gradle/project/
RUN gradle installDist
# Runtime docker
FROM ibm-semeru-runtimes:open-17-jre
RUN mkdir /opt/app
RUN mkdir /data
WORKDIR /opt/app
COPY --from=0 /home/gradle/project/build/install/TypechoMate/ /opt/app/
EXPOSE 8080/tcp
CMD ["./bin/TypechoMate", "/data/config.properties"]
