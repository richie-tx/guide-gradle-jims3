/*
 * Class Mediator.java
 * Created on May 24, 2002, 12:05:48 PM
 */

package mojo.km.security.role.ldap;

import javax.naming.NamingException;

/** @author Chad Oliver 
 * @modelguid {7256A20B-D4C0-467B-ABF4-EEE78C11D2AE}
 */
class Mediator {
    /** @link dependency */

    /*# Dn lnkDn; */

    /** @link dependency */

    /*# Cn lnkCn; */

    /** @link dependency */

    /*# Context lnkContext; */

    /** @link dependency */

    /*# ApplicationName lnkApplicationName; 
     * @modelguid {FEF35A10-7C80-49A9-8509-D0D379D3EE5C}
     */

    public String getDistinguishedName(Object distinguishedName) throws NamingException {
        return Dn.getDistinguishedName(distinguishedName);
    }

	/** @modelguid {D4303399-57FA-4C3D-8E6C-01D4D84AFA95} */
    public String getApplicationName(String distinguishedName) {
        return ApplicationName.getApplicationName(distinguishedName);
    }

	/** @modelguid {440069D3-2184-4597-986D-ED3A69A7B85D} */
    public String getCn(String distinguishedName) {
        return Cn.getCn(distinguishedName);
    }

	/** @modelguid {B43FD789-201E-4CF4-A35F-DD68AC7A4309} */
    public String getContext(String distinguishedName) {
        return Context.getContext(distinguishedName);
    }
}
