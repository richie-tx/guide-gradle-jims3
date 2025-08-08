package mojo.messaging.administrationevents;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command RemoveLDAPObjectCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {F1FAE91E-F17E-4564-9606-C9B49A492CFC}
 */
public class RemoveLDAPObjectEvent extends RequestEvent {
	/** @modelguid {C8D38D9C-E707-4AA6-B2BA-574BEE98D252} */
    private String ldapServer;
	/** @modelguid {E3C49E3E-110B-495F-944C-42B831FB1BEE} */
    private String path;

	/** @modelguid {24A239FD-7785-4660-9887-D46C0FB136C4} */
    public RemoveLDAPObjectEvent() { }

	/** @modelguid {6573AEF8-8BB6-4BFB-A471-A84E7FE37554} */
    public String getLdapServer() {
        return ldapServer;
    }

	/** @modelguid {5FDC704D-96BA-4122-9217-C6003CBC7AAD} */
    public void setLdapServer(String ldapServer) {
        this.ldapServer = ldapServer;
    }

	/** @modelguid {E6A5F921-E5A0-48F6-9D2A-D53D8F7DB77A} */
    public String getPath() {
        return path;
    }

	/** @modelguid {81B7738A-44E0-419D-AE99-072B01F997CF} */
    public void setPath(String path) {
        this.path = path;
    }
}
