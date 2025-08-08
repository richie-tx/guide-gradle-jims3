/*
 * Created on Sep 27, 2007
 *
 */
package mojo.km.security.helper;

import mojo.km.context.ContextManager;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.messaging.securitytransactionsevents.reply.UserInfoResponseEvent;

/**
 * @author Jim Fisher
 *  
 */
public class SecurityUtil
{
    private static final String DEFAULT_JIMS_LOGON_ID = "JIRCL1";

    private static final String DEFAULT_JIMS2_LOGON_ID = "TESTING@JIMS.NET";
    
    public static IUserInfo getCurrentUser()
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get(
                "ISecurityManager");

        IUserInfo user = null;

        if (manager != null)
        {
            user = manager.getIUserInfo();
        }

        if (user == null)
        {
            user = new UserInfoResponseEvent();
         user.setJIMSLogonId(DEFAULT_JIMS_LOGON_ID);
           user.setJIMS2LogonId(DEFAULT_JIMS2_LOGON_ID);
        }

        return user;
    }
    
    public static IUserInfo getCurrentUser(String defaultLogonId, String defaultJIMS2LogonId)
    {
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get(
                "ISecurityManager");

        IUserInfo user = null;

        if (manager != null)
        {
            user = manager.getIUserInfo();
        }

        if (user == null)
        {
            user = new UserInfoResponseEvent();
            user.setJIMSLogonId(defaultLogonId);
            user.setJIMS2LogonId(defaultJIMS2LogonId);
        }

        return user;
    }
}
