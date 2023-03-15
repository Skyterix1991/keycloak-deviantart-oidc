FROM quay.io/keycloak/keycloak:21.0.1

EXPOSE 8080

COPY target/keycloak-deviantart-provider-0.0.1-SNAPSHOT.jar /opt/keycloak/providers
