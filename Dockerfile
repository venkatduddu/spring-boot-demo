FROM amazoncorretto:11 as buildImage

ARG APP_NAME=spring-boot-demo

RUN if ! [ -x "$(command -v unzip)" ]; then yum install -y unzip; fi

ADD gradle /appcode/$APP_NAME/gradle
ADD gradlew* /appcode/$APP_NAME/
RUN cd /appcode/$APP_NAME && ./gradlew --no-daemon -version && cd -

ADD *.gradle /appcode/$APP_NAME/

ADD . /appcode/$APP_NAME
WORKDIR /appcode/$APP_NAME

RUN ./gradlew --no-daemon clean bootDistZip && cd build/distributions && /usr/bin/unzip -o $APP_NAME-boot.zip

FROM amazoncorretto:11 as runImage

RUN yum install -y wget

ENV APP_NAME=spring-boot-demo

ENV SERVER_SERVLET_CONTEXT_PATH=/$APP_NAME

EXPOSE 8080

HEALTHCHECK CMD curl --fail http://localhost:8080$SERVER_SERVLET_CONTEXT_PATH/actuator/health || exit 1

COPY --from=buildImage /appcode/$APP_NAME/build/distributions/$APP_NAME-boot  /apps/$APP_NAME
COPY --from=buildImage /appcode/$APP_NAME/src/main/scripts  /apps/$APP_NAME/bin


ENTRYPOINT ["/apps/spring-boot-demo/bin/docker-entrypoint.sh"]