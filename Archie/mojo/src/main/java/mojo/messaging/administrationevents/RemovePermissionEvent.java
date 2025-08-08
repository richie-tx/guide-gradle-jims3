package mojo.messaging.administrationevents;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command RemovePermissionCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {0FC4F5F1-4716-4F66-BF1B-26825C01B683}
 */
public class RemovePermissionEvent extends RequestEvent {
	/** @modelguid {A74C00E4-A4CE-40F1-ADCE-9396DC7B690C} */
    private Object OID;

	/** @modelguid {DBFD1FB5-C2AE-40C2-9EAF-A96174FDF110} */
    public RemovePermissionEvent() { }

	/** @modelguid {FC8AC426-1B2D-4F7B-8C93-6839AF88EC03} */
    public Object getOID() {
        return OID;
    }

	/** @modelguid {7BFFECC1-A3C9-4FF6-A97E-A08D9579FF82} */
    public void setOID(Object OID) {
        this.OID = OID;
    }
}
