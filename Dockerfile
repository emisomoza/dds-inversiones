FROM tomcat:8.0.20-jre8
ARG WAR_FILE
ADD $WAR_FILE /usr/local/tomcat/webapps/ddsinversiones.war