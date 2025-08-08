/*
 * Class User.java
 * Created on June 21, 2002, 9:19:50 AM
 */

package mojo.km.security.role;

import mojo.km.security.NamingException;
import javax.security.auth.login.AccountExpiredException;

/**
 * Authenticates a user and retrieves all of the groups they have membership
 * with. The user account and groupmembership information exists in a LDAP Server.
 * @author Chad Oliver
 * @modelguid {C505D414-C744-421D-8DAA-385B99EF9F6F}
 */
public abstract class User implements IUser {

    /** @link aggregation 
     * @modelguid {F24AACB7-7695-4F76-B6BA-07CC7E0485FB}
     */
    protected IRoles lnkRole = null;
	/** @modelguid {7ACF185B-CD36-40DC-941A-97C1ADC8BDDF} */
    protected String password = null;

    /**
     * After setting both the username and password fields.  This method is called for authentication.
     * @exception <{NamingException}>
     * @exception <{AuthenticationException}>
     * @modelguid {E3EA9BD0-E745-41FD-BAA4-FCF95B4396BF}
     */
    public abstract void login() throws NamingException;

    /**
     * Set the username for a given user
     * @param userName - username for a given user
     * @modelguid {C6473CD7-92A2-40A0-B54B-1866D57B59EE}
     */
    public abstract void setUserName(String userName) throws NamingException;

    /**
     * Set the users password. Treat a NULL password as an empty password
     * @param password - a given users password
     * @modelguid {29B2ED9D-F099-49D7-B5FA-7A7BC708B44B}
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Set the users password. Treat a NULL password as an empty password
     * @param password - a given users password
     * @modelguid {58CA6E02-89E5-4756-B9F5-2E2DBF19786B}
     */
    public void setPassword(char[] password) {
        StringBuffer newPassword = new StringBuffer();

        if (password != null) {
            newPassword = new StringBuffer();

            for (int i = 0; i < password.length; i++) {
                newPassword.append(password[i]);
            }

            this.password = newPassword.toString();
        }

        this.password = newPassword.toString();
    }

	/** @modelguid {0FBBE654-588D-4D73-9D6D-6485EAE1726E} */
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves a collection of Roles the given user has membership with. Returns null when no Roles are found
     * @return <{IRoles}>
     * @modelguid {F6F599E3-E62F-4E19-BA9E-02A19AACC108}
     */
    public IRoles getRole() {
        return this.lnkRole;
    }
}

