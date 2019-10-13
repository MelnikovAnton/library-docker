FROM melnikoffaa/library:v3

ENV PROJECT_DIR=/app

RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

ADD ./pom.xml $PROJECT_DIR

ADD ./src $PROJECT_DIR/src
RUN mvn package

FROM openjdk:12-jdk

RUN yum install -y curl

ENV PROJECT_DIR=/app
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY --from=0 $PROJECT_DIR/target/zuul-service-gateway.jar $PROJECT_DIR/zuul-service-gateway.jar

EXPOSE 8765

CMD ["java","-jar" ,"zuul-service-gateway.jar"]