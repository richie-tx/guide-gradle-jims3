package mojo.messaging.administrationevents;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command RenameLDAPObjectCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {DF6A57E8-F7D1-425F-BE5D-623A84C99CFA}
 */
public class RenameLDAPObjectEvent extends RequestEvent {
	/** @modelguid {52927F83-A09F-4C66-AB7A-85F832B44064} */
    private String ldapServer;
	/** @modelguid {36CF6EAD-ABA3-4B04-9E46-C99BAC17D2A4} */
    private String oldPath;
	/** @modelguid {5A85265E-FCCB-4EB2-93BE-131B8DAFE211} */
    private String newPath;

	/** @modelguid {30595E47-96E1-46E5-B8DD-66B1EEA74527} */
    public RenameLDAPObjectEvent() { }

	/** @modelguid {BF06F6EC-EA22-45AD-A4B4-0CE129C40011} */
    public String getLdapServer() {
        return ldapServer;
    }

	/** @modelguid {10FC98AC-43D8-43F2-807A-C6335697F2C4} */
    public void setLdapServer(String ldapServer) {
        this.ldapServer = ldapServer;
    }

	/** @modelguid {422E46C9-5D12-4CCD-A1C2-4EDF196DBFB3} */
    public String getOldPath() {
        return oldPath;
    }

	/** @modelguid {A859B51C-E4B0-4334-9498-5C889F7F86C6} */
    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

	/** @modelguid {29764773-421E-4B52-8AF4-D2D641501F77} */
    public String getNewPath() {
        return newPath;
    }

	/** @modelguid {7C844218-85C2-421C-B616-137DC0194E7C} */
    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }
}
