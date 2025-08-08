// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package mojo.mfa.authentication;


public class BasicConfiguration {
	
	String clientId;
    String secret;
    String redirectUri;
    String redirectUriLogout;

    
	String api;
    String apiScope;

    String signUpSignInAuthority;
	    
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getApiScope() {
		return apiScope;
	}
	public void setApiScope(String apiScope) {
		this.apiScope = apiScope;
	}
	public String getSignUpSignInAuthority() {
		return signUpSignInAuthority;
	}
	public void setSignUpSignInAuthority(String signUpSignInAuthority) {
		this.signUpSignInAuthority = signUpSignInAuthority;
	}
	public String getRedirectUriLogout() {
		return redirectUriLogout;
	}
	public void setRedirectUriLogout(String redirectUriLogout) {
		this.redirectUriLogout = redirectUriLogout;
	}
}
