// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package mojo.mfa.authentication;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

	public static final String MSAL_WEB_APP_STATE_COOKIE = "msal_web_app_auth_state";
	public static final String MSAL_WEB_APP_NONCE_COOKIE = "msal_web_app_auth_nonce";
	public static final String AUTH_SUCCESS = "authsuccess";

    public static void setStateNonceCookies
            (HttpServletRequest httpRequest, HttpServletResponse httpResponse, String state, String nonce){

        boolean userAgentSameSiteNoneAware =
                CookieHelper.isUserAgentAwareOfSameSiteNone(httpRequest.getHeader("User-Agent"));

        String sameSiteCookieAttribute = userAgentSameSiteNoneAware ? "; SameSite=none" : "";

        httpResponse.addHeader("Set-Cookie",
                MSAL_WEB_APP_STATE_COOKIE + "=" + state + "; Path=/; secure; HttpOnly" + sameSiteCookieAttribute);

        httpResponse.addHeader("Set-Cookie",
                MSAL_WEB_APP_NONCE_COOKIE + "=" + nonce + ";Path=/; secure; HttpOnly" + sameSiteCookieAttribute);
        

	}

	public static void setAuthSuccessCookie(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
	{		
		  boolean userAgentSameSiteNoneAware =
		  CookieHelper.isUserAgentAwareOfSameSiteNone(httpRequest.getHeader(
		  "User-Agent"));
		  
		  String sameSiteCookieAttribute = userAgentSameSiteNoneAware ?
		  "; SameSite=none" : "";
		  
		  httpResponse.addHeader("Set-Cookie", AUTH_SUCCESS + "=Success ; Path=/;secure; HttpOnly" + sameSiteCookieAttribute);
		 
		/*Cookie successCookie = new Cookie(AUTH_SUCCESS, "Success");
		httpResponse.addCookie(successCookie);*/
		
	}
	public static void removeAuthSuccessCookie(HttpServletRequest httpRequest,HttpServletResponse httpResponse)
	{
	/*	Cookie authCookie = new Cookie(AUTH_SUCCESS, "");
		authCookie.setMaxAge(0);
        httpResponse.addCookie(authCookie);*/
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) { // Yes, this can return null! The for loop would otherwise throw NPE.
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authsuccess")) {
                	cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    httpResponse.addCookie(cookie);
                    break;
                }
            }
        }
	}
	public static void setExpiryCookie(HttpServletRequest httpRequest, HttpServletResponse httpResponse,Date expiry)
	{		
		  boolean userAgentSameSiteNoneAware =
		  CookieHelper.isUserAgentAwareOfSameSiteNone(httpRequest.getHeader(
		  "User-Agent"));
		  
		  String sameSiteCookieAttribute = userAgentSameSiteNoneAware ?
		  "; SameSite=none" : "";
		  
		  httpResponse.addHeader("Set-Cookie","tokenexpiry="+expiry.getTime()+" ; Path=/;secure; HttpOnly" + sameSiteCookieAttribute);
		 
		/*Cookie successCookie = new Cookie(AUTH_SUCCESS, "Success");
		httpResponse.addCookie(successCookie);*/
		
	}
	public static void removeExpiryCookie(HttpServletRequest httpRequest,HttpServletResponse httpResponse)
	{
	/*	Cookie authCookie = new Cookie(AUTH_SUCCESS, "");
		authCookie.setMaxAge(0);
        httpResponse.addCookie(authCookie);*/
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) { // Yes, this can return null! The for loop would otherwise throw NPE.
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("tokenexpiry")) {
                	cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    httpResponse.addCookie(cookie);
                    break;
                }
            }
        }
	}

    public static void removeStateNonceCookies(HttpServletRequest httpRequest,HttpServletResponse httpResponse){

        /*Cookie stateCookie = new Cookie(MSAL_WEB_APP_STATE_COOKIE, "");
        stateCookie.setMaxAge(0);

        httpResponse.addCookie(stateCookie);

        Cookie nonceCookie = new Cookie(MSAL_WEB_APP_NONCE_COOKIE, "");
        nonceCookie.setMaxAge(0);

        httpResponse.addCookie(nonceCookie);*/
    	Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) { // Yes, this can return null! The for loop would otherwise throw NPE.
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("msal_web_app_auth_state")) {
                	cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    httpResponse.addCookie(cookie);
                    break;
                }                
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("msal_web_app_auth_nonce")) {
                	cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    httpResponse.addCookie(cookie);
                    break;
                }                
            }
        }
    }

    public static String getCookie(HttpServletRequest httpRequest, String cookieName){
    	if(httpRequest.getCookies()!=null)
    	{
	        for(Cookie cookie : httpRequest.getCookies()){
	            if(cookie.getName().equals(cookieName)){
	                return cookie.getValue();
	            }
	        }
    	}
        return null;
    }

    /**
     * Check whether user agent support "None" value of "SameSite" attribute of cookies
     *
     * The following code is for demonstration only: It should not be considered complete.
     * It is not maintained or supported.
     *
     * @param userAgent
     * @return true if user agent supports "None" value of "SameSite" attribute of cookies,
     * false otherwise
     */
    public static boolean isUserAgentAwareOfSameSiteNone(String userAgent){

        // Cover all iOS based browsers here. This includes:
        // - Safari on iOS 12 for iPhone, iPod Touch, iPad
        // - WkWebview on iOS 12 for iPhone, iPod Touch, iPad
        // - Chrome on iOS 12 for iPhone, iPod Touch, iPad
        // All of which are broken by SameSite=None, because they use the iOS networking
        // stack.
        if(userAgent.contains("CPU iPhone OS 12") || userAgent.contains("iPad; CPU OS 12")){
            return false;
        }

        // Cover Mac OS X based browsers that use the Mac OS networking stack.
        // This includes:
        // - Safari on Mac OS X.
        // This does not include:
        // - Chrome on Mac OS X
        // Because they do not use the Mac OS networking stack.
        if (userAgent.contains("Macintosh; Intel Mac OS X 10_14") &&
                userAgent.contains("Version/") && userAgent.contains("Safari")) {
            return false;
        }

        // Cover Chrome 50-69, because some versions are broken by SameSite=None,
        // and none in this range require it.
        // Note: this covers some pre-Chromium Edge versions,
        // but pre-Chromium Edge does not require SameSite=None.
        if(userAgent.contains("Chrome/5") || userAgent.contains("Chrome/6")){
            return false;
        }

        return true;
    }
}
