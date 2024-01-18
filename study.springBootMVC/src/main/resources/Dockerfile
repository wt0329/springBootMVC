FROM openjdk:8-jdk-alpine
ARG WAR_FILE=study.springBootMVC-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} study.springBootMVC-0.0.1-SNAPSHOT.war
RUN apk add --no-cache coreutils && \
	apk add --no-cache fontconfig ttf-dejavu && \
	apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime && \
    echo "Asia/Seoul" > /etc/timezone
ENTRYPOINT ["java", "-Dspring.config.location=./config/application.properties", "-Dspring.profiles.active=dev", "-jar","/study.springBootMVC-0.0.1-SNAPSHOT.war"]