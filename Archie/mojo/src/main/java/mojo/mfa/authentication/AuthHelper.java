// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package mojo.mfa.authentication;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.microsoft.aad.msal4j.AuthorizationCodeParameters;
import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
//import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;


public class AuthHelper {

    static final String PRINCIPAL_SESSION_NAME = "principal";
    static final String TOKEN_CACHE_SESSION_ATTRIBUTE = "token_cache";

   
    BasicConfiguration configuration;

    private static ConfidentialClientApplication createClientApplication(BasicConfiguration config) throws MalformedURLException {
        return ConfidentialClientApplication.builder(config.clientId,
                ClientCredentialFactory.createFromSecret(config.secret))
                .b2cAuthority(config.signUpSignInAuthority)
                .build();
    }
    public IAuthenticationResult getAuthResultByAuthCode(
            HttpServletRequest httpServletRequest,
            AuthorizationCode authorizationCode,
            String currentUri,BasicConfiguration config, Set<String> scopes) throws Throwable {

        IAuthenticationResult result;
        ConfidentialClientApplication app;
        try {
            app = createClientApplication( config);

            String authCode = authorizationCode.getValue();
            AuthorizationCodeParameters parameters = AuthorizationCodeParameters.builder(
                    authCode,
                    new URI(currentUri))
                    .scopes(scopes)
                    .build();

            CompletableFuture<IAuthenticationResult> future = app.acquireToken(parameters);

            result = future.get();
        } catch (ExecutionException e) {
            throw e.getCause();
        }

        if (result == null) {
            throw new ServiceUnavailableException("authentication result was null");
        }

        storeTokenCacheInSession(httpServletRequest, app.tokenCache().serialize());

        return result;
    }

	
    private static void storeTokenCacheInSession(HttpServletRequest httpServletRequest, String tokenCache){
        httpServletRequest.getSession().setAttribute(AuthHelper.TOKEN_CACHE_SESSION_ATTRIBUTE, tokenCache);
    }

    public static void setSessionPrincipalString(HttpServletRequest httpRequest, String result) {
        httpRequest.getSession().setAttribute(AuthHelper.PRINCIPAL_SESSION_NAME, result);        
    }
    /*static void setSessionPrincipal(HttpServletRequest httpRequest, IAuthenticationResult result) {
        httpRequest.getSession().setAttribute(AuthHelper.PRINCIPAL_SESSION_NAME, result);
    }*/
    public void removePrincipalFromSession(HttpServletRequest httpRequest) {
        httpRequest.getSession().removeAttribute(AuthHelper.PRINCIPAL_SESSION_NAME);
    }


    public static boolean isAuthenticationSuccessful(AuthenticationResponse authResponse) {
        return authResponse instanceof AuthenticationSuccessResponse;
    }

    public boolean isAuthenticated(HttpSession session) {
        return session.getAttribute(PRINCIPAL_SESSION_NAME) != null;
    }
    
    public static IAuthenticationResult getAuthSessionObject(HttpServletRequest request) {
        Object principalSession = request.getSession().getAttribute(PRINCIPAL_SESSION_NAME);
        if(principalSession instanceof IAuthenticationResult){
            return (IAuthenticationResult) principalSession;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean containsAuthenticationCode(HttpServletRequest httpRequest) {
        Map<String, String[]> httpParameters = httpRequest.getParameterMap();

        boolean isPostRequest = httpRequest.getMethod().equalsIgnoreCase("POST");
        boolean containsErrorData = httpParameters.containsKey("error");
        boolean containIdToken = httpParameters.containsKey("id_token");
        boolean containsCode = httpParameters.containsKey("code");

        return isPostRequest && containsErrorData || containsCode || containIdToken;
    }
}
