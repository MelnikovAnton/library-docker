FROM maven:3.6.2-jdk-12

ENV PROJECT_DIR=/app

RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD ./pom.xml $PROJECT_DIR
RUN mvn dependency:resolve

ADD ./src $PROJECT_DIR/src
RUN mvn install

FROM openjdk:12-jdk

ENV PROJECT_DIR=/app
ENV MONGO=mongodb://library-mongo
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY --from=0 $PROJECT_DIR/target/config-server.jar $PROJECT_DIR/config-server.jar

EXPOSE 8080

CMD ["java","-jar" ,"config-server.jar"]