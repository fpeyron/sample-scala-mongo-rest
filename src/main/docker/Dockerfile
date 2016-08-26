FROM java:8
ENV PROFILES dev
VOLUME /tmp
ADD sample-scala-mongo-rest.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILES]","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8080