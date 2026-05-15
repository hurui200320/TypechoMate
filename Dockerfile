FROM gradle:9.5.0-jdk25 AS build
WORKDIR /home/gradle/project
COPY ./ /home/gradle/project/
RUN gradle installDist --no-daemon



FROM azul/zulu-openjdk:25-latest
RUN mkdir /opt/app
RUN mkdir /data
WORKDIR /opt/app
COPY --from=build /home/gradle/project/build/install/TypechoMate/ /opt/app/
EXPOSE 8080/tcp
CMD ["./bin/TypechoMate", "/data/config.properties"]
