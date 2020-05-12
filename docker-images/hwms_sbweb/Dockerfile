FROM hwms
LABEL maintainer "Srinivasa Gulla"

EXPOSE 8030

USER hwms

CMD ["java", "-DSERVER_PORT=8030", "-DHWMS_REST_HOSTNAME=hwmslb", "-DHWMS_REST_PORT=80", "-jar", "/app/HelloWorldMicroserviceSpringBootWeb/target/hwms-sbweb-0.0.1-SNAPSHOT.jar"]
