package mojo.messaging.administrationevents;

import mojo.km.messaging.RequestEvent;

/**
 * Responsible for housing data that will be sent to control command AddPermissionCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {905A0F00-9FA7-4637-B131-B82C4BB90167}
 */
public class AddPermissionEvent extends RequestEvent {
	/** @modelguid {E831AEAD-6A09-47A3-8A6D-65810C7F17B0} */
    private String permission;
	/** @modelguid {F2A27B00-BA60-43F2-B9EE-18E49E5CBC7D} */
    private String role;
	/** @modelguid {FF31B5DB-EEB8-4062-BDE6-4BCC87F90919} */
    private int accessLevel;

	/** @modelguid {141208DC-6716-42A8-B466-DA21958A1A55} */
    public AddPermissionEvent() { }

	/** @modelguid {4C4C658A-80CA-4C5A-A2DA-1C499E36CF71} */
    public String getPermission() {
        return permission;
    }

	/** @modelguid {0ECC012F-48F4-489A-B16E-EDAA7567C7DD} */
    public void setPermission(String permission) {
        this.permission = permission;
    }

	/** @modelguid {18FBC14A-E97D-411D-BC20-CE6C5843A2A7} */
    public String getRole() {
        return role;
    }

	/** @modelguid {C89041EB-1A56-446F-939D-91A414B3A649} */
    public void setRole(String role) {
        this.role = role;
    }

	/** @modelguid {2ABD5B22-FF1B-483B-8DCA-0440D1586626} */
    public int getAccessLevel() {
        return accessLevel;
    }

	/** @modelguid {BB0BE19C-E017-4B95-AC69-B4F779C91B85} */
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
