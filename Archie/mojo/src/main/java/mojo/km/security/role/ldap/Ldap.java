/*
 * Class Ldap.java
 * Created on May 28, 2002, 8:06:19 AM
 */

package mojo.km.security.role.ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.BasicAttribute;

import mojo.km.config.AppProperties;
import mojo.km.security.role.IRoles;
import mojo.km.security.role.Roles;

/** @modelguid {DF31C922-F8CD-43F0-A62B-4A71DBBACB99} */
public class Ldap {
	/** @modelguid {DF04D20D-9869-4585-A795-1354ECB96FC8} */
    private static Hashtable defaultProperties = null;

    // Common JNDI settings which can be reused by all users trying to be
    // authenticated.
    static {
        defaultProperties = new Hashtable();
        defaultProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        defaultProperties.put(Context.PROVIDER_URL, AppProperties.getInstance().getProperty("SecurityURL"));
        defaultProperties.put(Context.SECURITY_AUTHENTICATION, "simple");
    }

	/** @modelguid {47D97560-E7DF-4464-B8F7-1FDB135D0F60} */
    public static DirContext createContext(LdapUser ldapUser, boolean old) throws mojo.km.security.NamingException
    {
        DirContext userContext = null;

        if (ldapUser == null) {
            throw new mojo.km.security.NamingException("Username was not provided");
        }

        try {
            if (old == true)
            {
            	userContext = Ldap.authenticateUser(ldapUser.getUsername(), ldapUser.getPassword());
			}
            else
            {
                userContext = Ldap.authenticateUserNew(ldapUser.getUsername(), ldapUser.getPassword());
            }

            return userContext;
        }
	    catch(javax.naming.NameNotFoundException nnfe)
		{
			nnfe.printStackTrace();
			throw new mojo.km.security.NamingException("Unable to communicate with the directory service.");
		}
		catch(javax.naming.OperationNotSupportedException onse)
		{
			onse.printStackTrace();
			
			if (onse.getMessage().indexOf("53") > 0)
			{
				throw new mojo.km.security.NamingException("The user account is not allowed access during this time of day.");
			}
			else
			{
				throw new mojo.km.security.NamingException("The user account has expired.");
			}
	    }
		catch(javax.naming.AuthenticationException ae)
		{
			ae.printStackTrace();
			
			if (ae.getMessage().indexOf("49") > 0)
			{
				throw new mojo.km.security.NamingException("The password supplied was incorrect.");
			}
			else
			{
				throw new mojo.km.security.NamingException("The user name supplied was not found in the directory.");
			}
		}
        catch (Exception e) {
		    e.printStackTrace();
            throw new mojo.km.security.NamingException("General directory service error.");
        }
    }

    /**
     * After setting both the username and password fields.  This method is
     * called for authentication.
     * @exception NamingException
     * @exception AuthenticationException
     * @modelguid {DC14CB1F-3C05-4399-9DBD-A25BD2BD7CD0}
     */
    public static IRoles login(LdapUser ldapUser) throws mojo.km.security.NamingException {
        DirContext userContext = null;
        IRoles iRoles = null;

        try
        {
			userContext = Ldap.createContext(ldapUser,true);
		    iRoles = Ldap.lookupGroupMembership(userContext, ldapUser.getUsername());
        }
        finally {
            // close connection with LDAP Server
            try {
                if (userContext != null)
                {
                    userContext.close();
                    userContext = null;
                }
            }
            catch (Exception e) { }
        }

        return iRoles;
    }

    /**
     * If authentication succeeds then a reference to the given user is returned.
     * @param userName - the username for the given user
     * @param password - the password for the given user.
     * @return DirContext - the directory service interface, containing methods for examining and updating attributes
     * associated with objects, and for searching the directory.
     * @exception Exception - if a naming exception is encountered
     * @modelguid {01231CE5-FBD8-426A-94AC-C69D2FC42C1D}
     */
    public static DirContext authenticateUser(IUserContext userName, String password) throws Exception {
        Hashtable props = (Hashtable)defaultProperties.clone();

        props.put(Context.SECURITY_PRINCIPAL, userName.getDn());
        props.put(Context.SECURITY_CREDENTIALS, password);

        return new InitialDirContext(props);
    }

    /**
     * If authentication succeeds then a reference to the given user is returned.
     * @param userName - the username for the given user
     * @param password - the password for the given user.
     * @return DirContext - the directory service interface, containing methods for examining and updating attributes
     * associated with objects, and for searching the directory.
     * @exception Exception - if a naming exception is encountered
     * @modelguid {245B7F0C-A7A3-4AF6-90CD-1A5D40C6E448}
     */
    public static DirContext authenticateUserNew(IUserContext userName, String password) throws Exception {
		Hashtable env = new Hashtable(5);

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        String url = AppProperties.getInstance().getProperty("SecurityURL");
        if (url != null) {
        	url = url.substring(0, url.indexOf("/o="));
        }
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.SECURITY_PRINCIPAL, userName.getDn());
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        return new InitialDirContext(env);
    }

    /**
     * Retrieves all of the groups the given user has membership with.
     * @param userContext - the directory service interface, containing methods for examining and updating attributes
     * associated with objects, and for searching the directory.
     * @param userName - the username for the given user
     * @return GroupMembership - collection of groups user has membership with
     * @exception javax.naming.NamingException - if a naming exception is encountered
     * @modelguid {57A69163-F6FC-416D-88DB-8B33E4C02211}
     */
    public static Roles lookupGroupMembership(DirContext userContext, IUserContext userName) throws mojo.km.security.NamingException {
        try
        {
			String applicationName = null;
			Roles role = new Roles();
			// Retrieves selected attributes associated with named user.
			String[] strAttrID = {"groupMembership"};
			Attributes attrs = userContext.getAttributes("cn=" + userName.getCn(), strAttrID);
			
			NamingEnumeration ae = attrs.getAll();
			Attribute attr = null;
			NamingEnumeration groups = null;
			String groupName = null;
			
			while (ae.hasMore()) {
				attr = (Attribute)ae.next();
			
				groups = attr.getAll();
			
				// store all groups the user has membership with.
				while (groups.hasMore()) {
					try {
						groupName = (String)groups.next();
						applicationName = ApplicationName.getApplicationName(groupName);
			
						role.add(applicationName);
					}
					catch (Exception unlikely) {
						// Should never occur. Can only occur if
						// groupMembership is malformed.
					}
				}
			}
			
			role.setReadOnly();
			
			return role;
	 	}
        catch(Exception e)
        {
             throw new mojo.km.security.NamingException(e.getMessage());
        }
    }

	/**
     * Change password for a user.
     * @param userName - the username for the given user
     * @param password - the password for the given user.
     * @param newPassword - the new password for the given user.
     * @exception mojo.km.security.NamingException - if a naming exception is encountered
     * @modelguid {B6568E80-7142-4205-8BE5-A0919CA7150C}
     */
    public static void changePassword(LdapUser ldapUser, String newPassword) throws  mojo.km.security.NamingException
    {
        DirContext userContext = null;
        try
        {
			userContext = Ldap.createContext(ldapUser, false);

    	    ModificationItem[] mods = new ModificationItem[2];

			mods[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
					new BasicAttribute("userPassword", ldapUser.getPassword()));

   			mods[1] = new ModificationItem(DirContext.ADD_ATTRIBUTE,
 					new BasicAttribute("userPassword", newPassword));

	        userContext.modifyAttributes(ldapUser.getUsername().getDn(), mods);
        }
        catch(Exception e)
        {
             e.printStackTrace();
             System.out.println(e.getMessage());
			 throw new mojo.km.security.NamingException(e.getMessage());
        }
        finally
        {
            // close connection with LDAP Server
            try {
                if (userContext != null)
                {
                    userContext.close();
                    userContext = null;
                }
            }
            catch (Exception e) { }
        }
	}
}
