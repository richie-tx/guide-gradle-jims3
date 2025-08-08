package mojo.messaging.administrationevents;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command ModifyPermissionCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {DACB011E-E9FE-4CC8-B0F1-BE4876D55F23}
 */
public class ModifyPermissionEvent extends RequestEvent {
	/** @modelguid {49870CA8-F943-4C15-93AB-6A113D01AAD3} */
    private Object OID;
	/** @modelguid {45036E0E-6F1B-47F9-8077-02A418CB5B8D} */
    private int accessLevel;

	/** @modelguid {129909DB-98C1-45FD-A98D-68CA962BE206} */
    public ModifyPermissionEvent() { }

	/** @modelguid {EADDD536-3538-4B5D-B7E1-3FDAABF27DF0} */
    public Object getOID() {
        return OID;
    }

	/** @modelguid {99F65BEC-0FFB-4C18-AF3C-453CF50DBBC1} */
    public void setOID(Object OID) {
        this.OID = OID;
    }

	/** @modelguid {1D861BE3-F028-4706-A000-F0D24EB87AE7} */
    public int getAccessLevel() {
        return accessLevel;
    }

	/** @modelguid {E426C1A3-CE4E-47AD-8190-4F9808241BB8} */
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
