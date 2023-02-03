package org.keycloak.social.deviantart;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

public class DeviantArtIdentityProviderFactory extends AbstractIdentityProviderFactory<DeviantArtIdentityProvider>
        implements SocialIdentityProviderFactory<DeviantArtIdentityProvider> {

    public static final String PROVIDER_ID = "deviantart";

    @Override
    public String getName() {
        return "DeviantArt";
    }

    @Override
    public DeviantArtIdentityProvider create(KeycloakSession session, IdentityProviderModel model) {
        return new DeviantArtIdentityProvider(session, new DeviantArtIdentityProviderConfig(model));
    }

    @Override
    public DeviantArtIdentityProviderConfig createConfig() {
        return new DeviantArtIdentityProviderConfig();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}