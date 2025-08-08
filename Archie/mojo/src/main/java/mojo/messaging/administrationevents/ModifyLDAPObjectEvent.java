package mojo.messaging.administrationevents;

import mojo.km.messaging.RequestEvent;
import javax.naming.directory.Attributes;

/**
 * Responsible for housing data that will be sent to control command ModifyLDAPObjectCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {8236940F-3D91-4732-9F19-0966B15AA74B}
 */
public class ModifyLDAPObjectEvent extends RequestEvent {
	/** @modelguid {87C045CD-979B-4365-99E1-C8B8E682E578} */
    private String ldapServer;
	/** @modelguid {E15073C9-22F4-4016-B7E4-C3F1AB629D27} */
    private String oldPath;
	/** @modelguid {24C672FD-8351-487F-B0A1-05E239C448CD} */
    private String newPath;
	/** @modelguid {B6FC5AC9-84E4-494D-87F6-F8C6D5748245} */
    private Attributes attributes;

	/** @modelguid {0A96C9BA-33BE-4C5E-9E6D-2AB5150FB5D1} */
    public ModifyLDAPObjectEvent() { }

	/** @modelguid {A337F979-1B13-461B-B98B-CD392EADE037} */
    public String getLdapServer() {
        return ldapServer;
    }

	/** @modelguid {7E3CEF9C-5303-4554-9539-8F86369CDDDC} */
    public void setLdapServer(String ldapServer) {
        this.ldapServer = ldapServer;
    }

	/** @modelguid {5DE39EBA-4E62-4206-8FB9-D8DDFB9FFBE6} */
    public String getOldPath() {
        return oldPath;
    }

	/** @modelguid {F2E41FD9-C881-4763-B08B-FD6503E3AE76} */
    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

	/** @modelguid {F3D996F4-2F31-4069-A4FF-74D7A38B0972} */
    public String getNewPath() {
        return newPath;
    }

	/** @modelguid {53FA91D9-61AA-4BF8-8A1B-C0510CD647A7} */
    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

	/** @modelguid {95A7BAA3-7559-425F-AED2-30F5C2971AD5} */
    public Attributes getAttributes() {
        return attributes;
    }

	/** @modelguid {EA2323F2-0696-4BBA-B61A-A831060587E7} */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
