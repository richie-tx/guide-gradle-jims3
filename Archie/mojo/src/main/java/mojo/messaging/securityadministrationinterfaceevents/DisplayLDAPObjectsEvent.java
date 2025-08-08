package mojo.messaging.securityadministrationinterfaceevents;

import mojo.km.messaging.ResponseEvent;
import java.util.Vector;

/**
 * Responsible for housing data that will be returned to boundry command DisplayLDAPObjectsCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {E5CD7295-D204-4078-8E08-5E7DD51278B3}
 */
public class DisplayLDAPObjectsEvent extends ResponseEvent {
	/** @modelguid {9B7870E4-C912-4F2C-A42F-8A0095E2BD85} */
    private Vector nodes;

	/** @modelguid {3B3E1685-29DD-49AC-BF6D-05B0B93D80FB} */
    public DisplayLDAPObjectsEvent() { }

	/** @modelguid {59C6537D-3FAD-420B-9151-C57B3B5839FC} */
    public Vector getNodes() {
        return nodes;
    }

	/** @modelguid {EB41204D-7B84-4FDA-9196-56EA427B73DF} */
    public void setNodes(Vector nodes) {
        this.nodes = nodes;
    }
}
