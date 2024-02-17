FROM quay.io/wildfly/wildfly:27.0.0.Final-jdk11
ADD build/libs/itmo_web_3.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]