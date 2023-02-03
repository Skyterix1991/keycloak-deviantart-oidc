package org.keycloak.social.deviantart;

import org.keycloak.broker.oidc.OAuth2IdentityProviderConfig;
import org.keycloak.models.IdentityProviderModel;

public class DeviantArtIdentityProviderConfig extends OAuth2IdentityProviderConfig {

    public DeviantArtIdentityProviderConfig(IdentityProviderModel model) {
        super(model);
    }

    public DeviantArtIdentityProviderConfig() {
    }
}
