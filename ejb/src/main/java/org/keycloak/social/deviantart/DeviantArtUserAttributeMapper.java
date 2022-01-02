package org.keycloak.social.deviantart;

import org.keycloak.broker.oidc.mappers.AbstractJsonUserAttributeMapper;

public class DeviantArtUserAttributeMapper extends AbstractJsonUserAttributeMapper {

	private static final String[] COMPATIBLE_PROVIDERS = new String[] { DeviantArtIdentityProviderFactory.PROVIDER_ID };

	@Override
	public String[] getCompatibleProviders() {
		return COMPATIBLE_PROVIDERS;
	}

	@Override
	public String getId() {
		return "deviantart-user-attribute-mapper";
	}

}
