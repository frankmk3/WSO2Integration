FROM openjdk:8-jdk

#VOLUME /tmp
ADD  app.jar  /home/app.jar
ADD  wso2ei.pem  /tmp/cert/wso2ei.pem 

RUN keytool -import -noprompt -alias wso2ei -storepass changeit  -keystore  /usr/local/openjdk-8/jre/lib/security/cacerts -file /tmp/cert/wso2ei.pem 

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /home/app.jar $APP_OPTS