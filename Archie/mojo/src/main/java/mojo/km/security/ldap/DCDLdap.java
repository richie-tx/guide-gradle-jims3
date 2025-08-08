package mojo.km.security.ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import mojo.km.config.AppProperties;

/** @modelguid {100EE430-30E4-4D19-BFA2-D98C1665BB20} */
public class DCDLdap
{
	/** @modelguid {334C0815-114B-4CD5-AEDC-5F4847D529F9} */
    private Hashtable env = null;
	/** @modelguid {C253A155-B918-4F91-BACF-2FDC811C70BF} */
    private DirContext dirContext = null;

	/** @modelguid {C721F751-095E-4D4C-B613-BB292ADB7770} */
    public DCDLdap()
    {
        // Set up environment for creating initial context
        env = new Hashtable(11);

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.PROVIDER_URL, AppProperties.getInstance().getProperty("SecurityURL"));
    }

	/** @modelguid {F891309C-1FB5-4EF8-9C30-00FA30316DEC} */
    public DCDLdap(String userName, String password)
    {
        this();
        setUserName(userName);
        setPassword(password);
    }

	/** @modelguid {921CE70A-5710-454E-94BE-CDCBB0BFD81D} */
    public void setUserName(String userName)
    {
        env.put(Context.SECURITY_PRINCIPAL, userName);
    }

	/** @modelguid {41C158AC-8413-482A-B8FD-E5501F6F6978} */
    public void setPassword(String password)
    {
        env.put(Context.SECURITY_CREDENTIALS, password);
    }

	/** @modelguid {5A1BC148-3D4D-4535-BB0F-7B5869803BA0} */
    public void login() throws mojo.km.security.NamingException
    {
	 	try 
		{
        	dirContext = new InitialDirContext(env);
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

	/** @modelguid {F4AC67F4-B212-467B-9BFA-AD21A467804A} */
    public void login(String userName, String password) throws mojo.km.security.NamingException
    {
        setUserName(userName);
        setPassword(password);

        login();
    }

	/** @modelguid {37E2CFDF-D004-4CF9-84A6-BC75266CF7D7} */
    public void logout()
    {
        try
        {
            // Close the context when we're done
            dirContext.close();
        }
        catch (Exception e) { }
    }

	/** @modelguid {34C83BA2-EB34-49F7-B518-6BC545829324} */
    public DCDSearchResults searchUser(String searchUserID, String firstName, String lastName,
        String pathShortDescription) throws javax.naming.NamingException
        {
            DCDFilter filter = new DCDFilter(searchUserID, firstName, lastName);
            DCDPath dcdPath = DCDPath.getDCDPath(pathShortDescription);
            DCDAttributeCollection dCDAttributes = new DCDAttributeCollection();

            return searchUser(dcdPath, dCDAttributes, filter);
    }

	/** @modelguid {C8827936-54A1-4180-802A-A905D7D07784} */
    public DCDSearchResults searchUser(DCDPath path, DCDAttributeCollection dCDAttributes,
        DCDFilter filter) throws javax.naming.NamingException
        {
            SearchControls ctls = new SearchControls();
            ctls.setReturningAttributes(dCDAttributes.toArray());
            ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

            // Search for objects using filter
            NamingEnumeration answer = this.dirContext.search(path.getPath(), filter.getFilter(), ctls);

            // Enumerate the class definitions
            DCDSearchResults dCDSearchResults = new DCDSearchResults();
            DCDSearchResult dCDSearchResult = null;
            SearchResult searchResult = null;
            Attributes attributes = null;

            while (answer.hasMore())
            {
                searchResult = (SearchResult)answer.next();
                attributes = searchResult.getAttributes();
                dCDSearchResult = new DCDSearchResult(attributes);
                dCDSearchResults.add(dCDSearchResult);
            }

            return dCDSearchResults;
    }

	/** @modelguid {7B28B61D-4A8A-4C2C-8F42-CF9D7323416F} */
    public void dcdirectAccess(String searchUserID, String pathShortDescription, boolean access) throws javax.naming.NamingException
    {
        DCDPath dcdPath = DCDPath.getDCDPath(pathShortDescription);
        DCDAttributeCollection dCDAttributes = new DCDAttributeCollection();

        dcdirectAccess(searchUserID, dcdPath, access);
    }

	/** @modelguid {3E95FE9E-06A9-4691-B4A5-116495D092D4} */
    public void dcdirectAccess(String searchUserId, DCDPath path, boolean access) throws javax.naming.NamingException
    {
        String value = "false";

        if (access)
        {
            value = "true";
        }

        // Specify the changes to make
        ModificationItem[] mods = new ModificationItem[1];

        // Replace the "DCdirect" attribute with a new value
        mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
            new BasicAttribute(DCDAttribute.DCDIRECT.getName(), value));

        // Perform the requested modifications on the named object
        StringBuffer userPath = new StringBuffer();
        userPath.append("uid=");
        userPath.append(searchUserId);
        userPath.append(",");
        userPath.append(path.getPath());

        this.dirContext.modifyAttributes(userPath.toString(), mods);
    }
}
