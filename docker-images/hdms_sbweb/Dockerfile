FROM hdms
LABEL maintainer "Srinivasa Gulla"

EXPOSE 8010

USER hdms

CMD ["java", "-DSERVER_PORT=8010", "-DHDMS_REST_HOSTNAME=hdmslb", "-DHDMS_REST_PORT=80", "-jar", "/app/HelloDevopsMicroserviceSpringBootWeb/target/hwms-sbweb-0.0.1-SNAPSHOT.jar"]
