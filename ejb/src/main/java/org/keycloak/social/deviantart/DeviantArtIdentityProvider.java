package org.keycloak.social.deviantart;

import com.fasterxml.jackson.databind.JsonNode;
import org.jboss.logging.Logger;
import org.keycloak.broker.oidc.AbstractOAuth2IdentityProvider;
import org.keycloak.broker.oidc.mappers.AbstractJsonUserAttributeMapper;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.broker.provider.IdentityBrokerException;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.broker.social.SocialIdentityProvider;
import org.keycloak.events.EventBuilder;
import org.keycloak.models.KeycloakSession;

public class DeviantArtIdentityProvider extends AbstractOAuth2IdentityProvider<DeviantArtIdentityProviderConfig>
        implements SocialIdentityProvider<DeviantArtIdentityProviderConfig> {

    private static final Logger log = Logger.getLogger(DeviantArtIdentityProvider.class);

    public static final String AUTH_URL = "https://www.deviantart.com/oauth2/authorize";
    public static final String TOKEN_URL = "https://www.deviantart.com/oauth2/token";
    public static final String PROFILE_URL = "https://www.deviantart.com/api/v1/oauth2/user/whoami";
    public static final String DEFAULT_SCOPE = "basic";

    public DeviantArtIdentityProvider(KeycloakSession session, DeviantArtIdentityProviderConfig config) {
        super(session, config);
        config.setAuthorizationUrl(AUTH_URL);
        config.setTokenUrl(TOKEN_URL);
        config.setUserInfoUrl(PROFILE_URL);
    }

    @Override
    protected boolean supportsExternalExchange() {
        return true;
    }

    @Override
    protected String getProfileEndpointForValidation(EventBuilder event) {
        return PROFILE_URL;
    }

    @Override
    protected BrokeredIdentityContext extractIdentityFromProfile(EventBuilder event, JsonNode profile) {
        BrokeredIdentityContext user = new BrokeredIdentityContext(getJsonProperty(profile, "userid"));

        user.setUsername(getJsonProperty(profile, "username"));
        user.setIdpConfig(getConfig());
        user.setIdp(this);

        AbstractJsonUserAttributeMapper.storeUserProfileForMapper(user, profile, getConfig().getAlias());

        return user;
    }

    @Override
    protected BrokeredIdentityContext doGetFederatedIdentity(String accessToken) {
        log.debug("doGetFederatedIdentity()");

        JsonNode profile;

        try {
            profile = SimpleHttp.doGet(PROFILE_URL, session).header("Authorization", "Bearer " + accessToken).asJson();
        } catch (Exception e) {
            throw new IdentityBrokerException("Could not obtain user profile from deviantart.", e);
        }

        return extractIdentityFromProfile(null, profile);
    }

    @Override
    protected String getDefaultScopes() {
        return DEFAULT_SCOPE;
    }
}
