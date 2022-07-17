FROM openjdk:17-jdk-alpine as build

WORKDIR /workspace

# Copy pom.xml and source code
COPY . .

ARG ENV_NAME

# Build jar file
RUN ./mvnw -s settings.xml clean package -Denv.ENV_NAME=${ENV_NAME}

###########################################################
# Running stage
###########################################################
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /workspace/target/customer-service-*.jar application.jar

EXPOSE 8081

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
# docker run -it --rm --publish 8091:8091 customer-service
#
