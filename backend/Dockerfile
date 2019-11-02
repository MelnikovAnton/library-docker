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
ENV MONGO=mongodb://library-mongo
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY --from=0 $PROJECT_DIR/target/library.jar $PROJECT_DIR/library.jar
ADD ./wrapper.sh $PROJECT_DIR

EXPOSE 8080

CMD ["./wrapper.sh" , "http://config-server:5000/actuator","java","-jar" ,"library.jar"]