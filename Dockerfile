
## Stage 1 Maven Build
FROM maven as build
ARG mvnGoals="clean package"
ARG mvnPrepareGoals="release:prepare -DskipTests=true"
WORKDIR /app
COPY . .
RUN echo "Beginning to build product ..... Goals: ${mvnGoals}"
RUN mvn -B -e -f /app/pom.xml ${mvnGoals}
RUN echo "Preparing for release .... Goals: ${mvnPrepareGoals}"
RUN mvn -B -e -f /app/pom.xml ${mvnPrepareGoals}


# Stage 3 Final Stage
FROM openjdk:8-jdk-alpine
ARG artifactPath=target
VOLUME /tmp

COPY --from=build /app/${artifactPath}/*.jar /opt/app/app.jar
EXPOSE 8080

# Non Root User Configuration
RUN addgroup -S -g 10001 appGrp \
    && adduser -S -D -u 10000 -s /sbin/nologin -h /opt/app/ -G appGrp app \
    && chown -R 10000:10001 /opt/app

USER 10000

ENTRYPOINT exec java $JAVA_OPTS \
-XX:+UseContainerSupport \
-jar /opt/app/app.jar
