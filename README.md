# keycloak-deviant art

Keycloak DeviantArt Identity Provider (oidc) plugin.

## Install

Download `keycloak-deviantart-ear-<version>.ear` from [Releases page](https://github.com/Skyterix1991/keycloak-deviantart-oidc/releases).
Then deploy it into `$KEYCLOAK_HOME/standalone/deployments/` directory.

Or use provided Dockerfile.

### DeviantArt

Register your application [DeviantArt Developer Portal](https://www.deviantart.com/developers/).
You will get Client ID and Client Secret from the created application.

## Source Build

Clone this repository and run `mvn package`.
You can see `keycloak-deviantart-ear-<version>.ear` under `ear/target` directory.

## Licence

[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)