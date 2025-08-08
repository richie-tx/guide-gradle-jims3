/*
 * Created on Apr 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.security;

import java.io.IOException;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import mojo.km.config.*;
import mojo.km.context.ContextManager;
import mojo.km.naming.ServerNames;

/**
 * @author fsjodin
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
public class SecurityFilter implements Filter
{

    private FilterConfig filterConfig = null;

    private String info;

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
        this.info = filterConfig.getInitParameter("info");
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        MojoProperties.getInstance().setActiveServerName(ServerNames.GUI);

        HttpSession session = req.getSession();

        //added to clear old session details
        getNewSession(req, session);

        ContextManager contextManager = ContextManager.currentContext();
        contextManager.setCurrentSessionID(session.getId());

        /*
         * Verify if security is being enforced and if so, whether the requested resource is protected. If it is protected we
         * require the user to be authenticated. If they are not we redirect to the login page. If they are authenticated we check
         * if any particular features are required to access the resource, or if the user needs to be of a certain type. If that
         * is the case and the user does not have the appropriate grants we redirect to an access denied page.
         */
        SecurityProperties secProperties = SecurityProperties.getInstance();
        if (secProperties.getProperty("EnforceSecurity") == null
                || !secProperties.getProperty("EnforceSecurity").trim().equals("false"))
        {

            String requestURI = req.getRequestURI();

            if (secProperties.isUnprotected(requestURI) == false)
            {

                if (secProperties == null)
                {
                    System.out.println("SEC Properties = null");
                }

                // Make sure user is authenticated. If not, send to login page
                ISecurityManager sm = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
                if (sm == null)
                {
                    /*
                     * Redirect to login page
                     */
                    System.out.println("SecurityManager not found - redirecting to login page url");
                    res.sendRedirect(secProperties.getProperty("LoginPageURL"));
                    return;
                }
                /*
                 * Check if requested page requires special access rights
                 */

                IUserInfo user = sm.getIUserInfo();

                Map queryParams = req.getParameterMap();
                String[] requiredFeatures = secProperties.getRequiredFeatures(requestURI, queryParams);
                String[] requiredUserTypes = secProperties.getRequiredUserTypes(requestURI, queryParams);
                if ((requiredFeatures != null && requiredFeatures.length > 0)
                        || (requiredUserTypes != null && requiredUserTypes.length > 0))
                {
                    if (requiredFeatures != null && requiredFeatures.length > 0)
                    {
                        if (sm.isAllowed(requiredFeatures) == false)
                        {
                            System.out.println("User does not have required feature");

                            // Redirect to access denied page
                            res.sendRedirect(secProperties.getProperty("AccessDeniedPageURL"));
                            return;
                        }
                    }
                    if (requiredUserTypes != null && requiredUserTypes.length > 0)
                    {
                        boolean userIsOfRequiredType = false;
                        for (int i = 0; i < requiredUserTypes.length; i++)
                        {
                            if (sm.isUserOfType(requiredUserTypes[i]))
                            {
                                userIsOfRequiredType = true;
                                System.out.println("User is of right type");
                                break;
                            }
                        }
                        if (!userIsOfRequiredType)
                        {
                            System.out.println("User is not of required type");
                            /*
                             * Redirect to access denied page
                             */
                            res.sendRedirect(secProperties.getProperty("AccessDeniedPageURL"));
                            return;
                        }
                    }
                }
                else
                {
                    /*
                     * No special features or user type needed. User is authenticated which is sufficient
                     */
                }
            }
        }
        chain.doFilter(req, res);

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy()
    {
    }

    /*
     * checks for the url for login.jsp and clears the old session details
     */
    private void getNewSession(HttpServletRequest req, HttpSession session)
    {
        String requestURITest = req.getRequestURI();
        if (requestURITest != null && requestURITest.endsWith("/login.jsp"))
        {
            session.invalidate();
            session = req.getSession(true);
        }

    }
}
