FROM openjdk:11 as build

WORKDIR /workspace

# Copy pom.xml and source code
COPY . .

ARG S3_USER_ACCESS_KEY
ARG S3_USER_SECRET_KEY
ARG SPRING_MAIL_PASSWORD

# Build jar file
RUN ./mvnw clean package -Denv.S3_USER_ACCESS_KEY=${S3_USER_ACCESS_KEY} -Denv.S3_USER_SECRET_KEY=${S3_USER_SECRET_KEY} -Denv.SPRING_MAIL_PASSWORD=${SPRING_MAIL_PASSWORD}

###########################################################
# Running stage
###########################################################
FROM openjdk:11

WORKDIR /app

COPY --from=build /workspace/target/customer-service-*.jar application.jar

EXPOSE 8080

CMD ["java", \
    "-Xms1G", \
    "-Xmx1G", \
    "-XX:+UseG1GC", \
    "-XX:OnOutOfMemoryError=\"kill -9 %p\"", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-XX:HeapDumpPath=\"./config/memory-dump.hprof\"", \
    "-Dsys.prop.log.dir=logs", \
    "-Dsys.prop.log.file.async.level=INFO", \
    "-Dsys.prop.log.console.level=INFO", \
    "-jar", "application.jar"]

###########################################################
#
# Building locally:
#
# If you already have S3_USER_ACCESS_KEY, S3_USER_SECRET_KEY and SPRING_MAIL_PASSWORD environment variable:
# docker build --build-arg S3_USER_ACCESS_KEY S3_USER_SECRET_KEY SPRING_MAIL_PASSWORD  -t customer-service .
#
# If you don't have S3_USER_ACCESS_KEY S3_USER_SECRET_KEY SPRING_MAIL_PASSWORD environment variable:
# docker build --build-arg S3_USER_ACCESS_KEY=value1 S3_USER_SECRET_KEY=value2 SPRING_MAIL_PASSWORD=value3 -t customer-service .
#
#
# Running locally:
# docker run -it --rm --publish 8080:8081 customer-service
#
