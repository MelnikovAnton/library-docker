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

COPY --from=0 $PROJECT_DIR/target/config-server.jar $PROJECT_DIR/config-server.jar
ADD ./wrapper.sh $PROJECT_DIR

EXPOSE 5000

CMD ["./wrapper.sh" , "http://eureka-server:8001" ,"java","-jar" ,"config-server.jar"]