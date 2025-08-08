package mojo.messaging.administrationevents;

import mojo.km.messaging.RequestEvent;
import javax.naming.directory.Attributes;

/**
 * Responsible for housing data that will be sent to control command AddLDAPObjectCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {72C0D5AD-8CA2-46CC-922C-5EAAC26224BE}
 */
public class AddLDAPObjectEvent extends RequestEvent {
	/** @modelguid {EC944640-1B99-4E88-9D08-FB9EF5BFEA33} */
    private String ldapServer;
	/** @modelguid {F98BCA89-F7F2-4307-8C6D-402591E73047} */
    private String path;
	/** @modelguid {798FE957-4ED7-4951-A185-C103BD65EBE6} */
    private Attributes attributes;

	/** @modelguid {3DEA596E-0ED4-4376-B404-F9D20FB5824F} */
    public AddLDAPObjectEvent() { }

	/** @modelguid {A394F72E-73B5-48AC-B273-84DDAF02032D} */
    public String getLdapServer() {
        return ldapServer;
    }

	/** @modelguid {4658B228-8C8D-45A0-AE6F-E52D0F865775} */
    public void setLdapServer(String ldapServer) {
        this.ldapServer = ldapServer;
    }

	/** @modelguid {2AEE1513-1FBE-44CC-AAF2-71698852DC8B} */
    public String getPath() {
        return path;
    }

	/** @modelguid {03792BA5-6AA9-4054-A6F8-32AD29883CE8} */
    public void setPath(String path) {
        this.path = path;
    }

	/** @modelguid {08EADC21-0477-4B4C-A710-EF46BDAB5743} */
    public Attributes getAttributes() {
        return attributes;
    }

	/** @modelguid {65554A23-B4BE-45FC-843C-6002A0A65259} */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
