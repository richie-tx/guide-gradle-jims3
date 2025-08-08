package mojo.messaging.securityadministrationinterfaceevents;

import mojo.km.messaging.ResponseEvent;

/**
 * Responsible for housing data that will be sent to control command AddPermissionCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {7609D953-F535-4666-ADC3-F45769AE3B91}
 */
public class DisplayPermissionsCollectionEvent extends ResponseEvent {
	/** @modelguid {E36C9E72-3498-4590-A665-0537845D132E} */
    private Object OID;
	/** @modelguid {9751C80E-A3A0-4BC3-A5CE-4BBFE7CBD7C6} */
    private String permission;
	/** @modelguid {F57EFA34-4B2D-489A-8952-3326D3FF30B9} */
    private String role;
	/** @modelguid {437F2D5B-AC42-48CA-99B5-2A10EF14085A} */
    private int accessLevel;

	/** @modelguid {ECCB43E0-8D5A-4BC8-B083-F6D1CD0266D8} */
    public DisplayPermissionsCollectionEvent() { }

	/** @modelguid {783D5F98-93F4-477C-A3D3-90F71BFD0199} */
    public Object getOID() {
        return OID;
    }

	/** @modelguid {422764A2-1694-4540-BBA2-EA5DA58BEDD2} */
    public void setOID(Object OID) {
        this.OID = OID;
    }

	/** @modelguid {26A8A2F8-2055-4A1F-93BC-93734873CFA9} */
    public String getPermission() {
        return permission;
    }

	/** @modelguid {76829FE8-5A12-439E-971D-29BFE9FD08EE} */
    public void setPermission(String permission) {
        this.permission = permission;
    }

	/** @modelguid {AC9BA9FF-34A4-4B20-A62C-E8DD0F83CB88} */
    public String getRole() {
        return role;
    }

	/** @modelguid {F2A3E710-EE29-4F4F-8E5C-50EB9B929375} */
    public void setRole(String role) {
        this.role = role;
    }

	/** @modelguid {80E5ED59-E767-4B70-AA4A-9BC94F65CBE5} */
    public int getAccessLevel() {
        return accessLevel;
    }

	/** @modelguid {D8926259-FB00-46AF-9DA3-4C15ABE2BA6E} */
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
