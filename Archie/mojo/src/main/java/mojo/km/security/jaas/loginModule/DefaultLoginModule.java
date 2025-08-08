package mojo.km.security.jaas.loginModule;// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*
 * Class DefaultLoginModule.java
 * Created on May 9, 2002, 2:36:44 PM
 

package mojo.km.security.jaas.loginModule;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import mojo.km.security.NamingException;
import mojo.km.security.helper.IPermissions;
import mojo.km.security.role.IUser;
import mojo.km.security.role.UserFactory;

*//**
 * The LoginContext is responsible for reading the Configuration and instantiating
 * the appropriate LoginModules. Each LoginModule is initialized with a Subject,
 * a CallbackHandler, shared LoginModule state, and LoginModule-specific options.
 * The Subject represents the Subject currently being authenticated and is
 * updated with relevant Credentials if authentication succeeds. LoginModules
 * use the CallbackHandler to communicate with users. The CallbackHandler may
 * be used to prompt for usernames and passwords, for example.
 * Note that the CallbackHandler may be null. LoginModules which absolutely
 * require a CallbackHandler to authenticate the Subject may throw a
 * LoginException. LoginModules optionally use the shared state to share
 * information or data among themselves.
 * <p>
 * The LoginModule-specific options represent the options configured for this
 * LoginModule by an administrator or user in the login Configuration. The
 * options are defined by the LoginModule itself and control the behavior
 * within it. For example, a LoginModule may define options to support
 * debugging/testing capabilities. Options are defined using a key-value syntax,
 * such as debug=true. The LoginModule stores the options as a Map so that the
 * values may be retrieved using the key. Note that there is no limit to the
 * number of options a LoginModule chooses to define.
 * <p>
 * The calling application sees the authentication process as a single
 * operation. However, the authentication process within the LoginModule proceeds in two
 * distinct phases. In the first phase, the LoginModule's login method gets
 * invoked by the LoginContext's login method. The login method for the
 * LoginModule then performs the actual authentication (prompt for and verify a
 * password for example) and saves its authentication status as private state
 * information. Once finished, the LoginModule's login method either returns
 * true (if it succeeded) or false (if it should be ignored), or throws a
 * LoginException to specify a failure. In the failure case, the LoginModule
 * must not retry the authentication or introduce delays. The responsibility of
 * such tasks belongs to the application. If the application attempts to retry
 * the authentication, the LoginModule's login method will be called again. <p>
 * In the second phase, if the LoginContext's overall authentication succeeded
 * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
 * succeeded), then the commit method for the LoginModule gets invoked. The
 * commit method for a LoginModule checks its privately saved state to see if
 * its own authentication succeeded. If the overall LoginContext authentication
 * succeeded and the LoginModule's own authentication succeeded, then the commit
 * method associates the relevant Principals (authenticated identities) and
 * Credentials (authentication data such as cryptographic keys) with the Subject located within the LoginModule.
 * @author Chad Oliver
 * @modelguid {6747AF66-701F-4DC1-9CFC-7979B6F77313}
 *//*
public class DefaultLoginModule implements LoginModule {
    // initial state
	*//** @modelguid {E02445AB-D689-4B25-90EE-9556F687C5FF} *//*
    private Subject subject;
	*//** @modelguid {B744A338-1406-48B8-971D-67A7269800F8} *//*
    private CallbackHandler callbackHandler;
	*//** @modelguid {17C9C5FF-EF78-4FAE-B8D1-44297758E637} *//*
    private Map sharedState;
	*//** @modelguid {22743B75-F481-4516-997A-0706B2A2F8B0} *//*
    private Map options;

    // User object with groupmemberships
	*//** @modelguid {FC9708AB-2F9C-4155-A2BF-9E77B4204F55} *//*
    private IUser user = null;

    // the authentication status
	*//** @modelguid {A3EBA95B-BD5F-440C-AB4F-BAA8320E42C9} *//*
    private boolean succeeded = false;
	*//** @modelguid {D4EF6758-DA8D-4A94-AFA3-54D284684E2B} *//*
    private boolean commitSucceeded = false;

    *//**
     * Initialize this <code>DefaultLoginModule</code>. <p>
     * @param subject - the <code>Subject</code> to be authenticated. <p>
     * @param callbackHandler - a <code>CallbackHandler</code> for communicating with the end user (prompting for usernames and
     * passwords, for example). <p>
     * @param sharedState - shared <code>DefaultLoginModule</code> state. <p>
     * @param options - options specified in the login <code>Configuration</code> for this particular
     * <code>LdapLoginModule</code>.
     * @modelguid {A7485CF0-77BB-4144-BBF5-A3572FF734B6}
     *//*
    public void initialize(Subject subject, CallbackHandler handler, Map sharedState, Map options) {
        this.subject = subject;
        this.callbackHandler = handler;
        this.sharedState = sharedState;
        this.options = options;
    }

    *//**
     * Method to authenticate a Subject (phase 1).
     * <p>
     * The implementation of this method authenticates a Subject.
     * For example, it may prompt for Subject information such as a username
     * and password and then attempt to verify the password. This method saves
     * the result of the authentication attempt as private state within the LoginModule.
     * @return true if the authentication succeeded, or false if this LoginModule should be ignored.
     * @modelguid {51EA1DDE-F02F-4973-9DBE-A6E5D0B743C0}
     *//*
    public boolean login() throws LoginException {
        // prompt for a username and password
        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available " +
                "to garner authentication information from the user");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Username: ");
        callbacks[1] = new PasswordCallback("Password: ", false);

        try {
            // invoke the callback handler
            callbackHandler.handle(callbacks);

            // retreive the username and password. where the user information
            // is retreived from (LDAP, Database, File) is determined by the
            // user object.
            user = new UserFactory().getUser();
            user.setUserName(((NameCallback)callbacks[0]).getName());
            user.setPassword(((PasswordCallback)callbacks[1]).getPassword());

            ((PasswordCallback)callbacks[1]).clearPassword();
        } catch (java.io.IOException ioe) {
            throw new LoginException(ioe.toString());
        } catch (NamingException ne) {
            throw new LoginException("Error: " + ne.toString() + ". Invalid username supplied");
        } catch (UnsupportedCallbackException uce) {
            throw new LoginException("Error: " + uce.getCallback().toString() +
                " not available to garner authentication information " + "from the user");
        } catch (Exception e) {
            throw new LoginException();
        }

        try {
            user.login();

            // authentication succeeded!!!
            succeeded = true;

            return true;
        }
		catch (NamingException ne) {
		    succeeded = false;
            this.user.clear();
				
            throw new LoginException(ne.getMessage());
		}		  
        catch (Exception e) {
            succeeded = false;
            this.user.clear();

            throw new FailedLoginException("Unable to reach authentication server");
        }
    }

    *//**
     * Method to commit the authentication process (phase 2).
     * <P>
     * This method is called if the LoginContext's overall authentication
     * succeeded (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules succeeded).
     * <p>
     * If this LoginModule's own authentication attempt succeeded
     * (checked by retrieving the private state saved by the login method),
     * then this method associates relevant Principals and Credentials with
     * the Subject located in the LoginModule. If this LoginModule's own
     * authentication attempted failed, then this method removes/destroys any state that was originally saved.
     * @return true if this method succeeded, or false if this LoginModule should be ignored.
     * @modelguid {E0241C7D-7B4D-437D-AB95-5ACC868F7F92}
     *//*
    public boolean commit() {
        if (!succeeded) {
            return false;
        }

		try 
		{
			IPermissions permissions = null;
			
			permissions = user.getPermissions();  //FeatureListMapping.findPermissions(user.getRole());
			
	        // save the application Permissions
            subject.getPublicCredentials().add(permissions);
			// clear the user credentails information
        	this.user.clear();				
			commitSucceeded = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}

        return commitSucceeded;
    }

    *//**
     * Method to abort the authentication process (phase 2).
     * <p>
     * This method is called if the LoginContext's overall authentication
     * failed. (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules did not succeed).
     * <p>
     * If this LoginModule's own authentication attempt succeeded
     * (checked by retrieving the private state saved by the login method),
     * then this method cleans up any state that was originally saved.
     * @return true if this method succeeded, or false if this LoginModule should be ignored.
     * @modelguid {40E31D00-A1DC-4FEF-B05A-7B2E75B0BFC1}
     *//*
    public boolean abort() {
        if (succeeded && !commitSucceeded) {
            // login succeeded but overall authentication failed
            succeeded = false;
            this.user.clear();
        }
        else if (succeeded) {
            // overall authentication succeeded and commit succeeded,
            // but someone else's commit failed
            logout();
        } else {
            return false;
        }

        return true;
    }

    *//**
     * Method which logs out a Subject.
     * <p>
     * Removes a Subject's Principals and Credentials.
     * @return true if this method succeeded, or false if this LoginModule should be ignored.
     * @modelguid {FCF585DE-34A9-4A05-B5C9-1EDC42830B8C}
     *//*
    public boolean logout() {
        succeeded = false;
        succeeded = commitSucceeded;
        this.user.clear();

        return true;
    }
    
    /// hack for the short-term
    public String getUserFullname() {
    	try {
	    	if (user != null) {
	    		return ((mojo.km.security.role.file.FileUser)user).getFullName();
	    	}
		} catch (ClassCastException e) {}
    	return null;
    }
    
}
*/