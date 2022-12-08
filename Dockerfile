FROM adoptopenjdk/maven-openjdk11 AS BUILD_IMAGE
ENV APP_HOME=/root/login-system
RUN mkdir -p $APP_HOME/
WORKDIR $APP_HOME
COPY . .
RUN mvn clean install

FROM openjdk:11-jre
WORKDIR /root/
COPY --from=BUILD_IMAGE /root/login-system/target/registration-login*.jar ./registration-login.jar
EXPOSE 8086
CMD ["java","-jar","registration-login.jar"]