package com.fb.vuebasicproxy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
class OAuthClient {

	public OAuthClient() {

	}

	public String getToken(String username, String password) {
		try {
			OAuth2AccessToken token = obtainToken(username, password);
			return token.toString();
		} catch (Exception e) {
			return "ERROR";
		}

	}

	private OAuth2AccessToken obtainToken(String username, String password) {

		ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();

		List<String> scopes = new ArrayList<String>(2);
		scopes.add("write");
		scopes.add("read");

		ResourceOwnerPasswordResourceDetails passwordResourceDetails = new ResourceOwnerPasswordResourceDetails();
		passwordResourceDetails.setUsername(username);
		passwordResourceDetails.setPassword(password);
		passwordResourceDetails.setClientId("vueproxy");
		passwordResourceDetails.setClientSecret("SECRET_KEY");
		passwordResourceDetails.setScope(scopes);
		passwordResourceDetails.setAccessTokenUri("http://localhost:1010/api/oauth/token");
		DefaultAccessTokenRequest defaultAccessTokenRequest = new DefaultAccessTokenRequest();
		OAuth2AccessToken token;
		try {
			token = provider.obtainAccessToken(passwordResourceDetails, defaultAccessTokenRequest);
			return token;
		} catch (OAuth2AccessDeniedException accessDeniedException) {
			// throw new BadCredentialsException("Invalid credentials",
			// accessDeniedException);
			return null;
		}
	}

}