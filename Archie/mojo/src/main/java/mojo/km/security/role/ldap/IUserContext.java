/*
 * Class IUserContext.java
 * Created on May 24, 2002, 12:53:13 PM
 */

package mojo.km.security.role.ldap;

/** @author Chad Oliver 
 * @modelguid {49A02314-14C9-44ED-B8C6-F86F728376C0}
 */
public interface IUserContext extends java.io.Serializable {
	/** @modelguid {60A6A04E-DA4F-45C4-829F-8B2DB7D55C47} */
    public String getDn();

	/** @modelguid {D8677C8E-9A6D-491F-A845-9AE02B14D46D} */
    public String getApplicationName();

	/** @modelguid {0954E68C-35C4-44D9-8350-30D0CDE512E9} */
    public String getContext();

	/** @modelguid {85A481BC-75E3-4F46-A5AD-8434B82DEA0B} */
    public String getCn();
}
