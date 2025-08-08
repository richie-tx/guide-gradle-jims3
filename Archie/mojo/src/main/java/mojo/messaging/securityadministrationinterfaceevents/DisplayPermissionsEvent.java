package mojo.messaging.securityadministrationinterfaceevents;

import mojo.km.messaging.ResponseEvent;
import java.util.Vector;

/**
 * Responsible for housing data that will be returned to boundry command DisplayPermissionsCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {2C81B5EA-08D0-4CB7-8A68-CEF5500BDF3B}
 */
public class DisplayPermissionsEvent extends ResponseEvent {
	/** @modelguid {8B453A15-B063-4B1D-99D1-7098956B17FA} */
    private Vector permissions;

	/** @modelguid {182FE934-604A-4B64-8C40-5C72377F2BF2} */
    public DisplayPermissionsEvent() { }

	/** @modelguid {B2F9EABD-E717-4C2D-8962-843341204AF8} */
    public Vector getPermissions() {
        return permissions;
    }

	/** @modelguid {DFB67028-1960-4B7A-97A1-02867D609397} */
    public void setPermissions(Vector permissions) {
        this.permissions = permissions;
    }
}
