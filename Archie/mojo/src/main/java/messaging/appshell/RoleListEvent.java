package messaging.appshell;

import java.util.Collection;
import java.util.Iterator;
import mojo.km.messaging.RequestEvent;

/**
 * A list of RoleEvents.
 *
 * @author James McNabb
 * @modelguid {56C03B96-93EB-4C0D-8E00-6A47869272A1}
 */
public class RoleListEvent extends RequestEvent {

    /**
     * @associates <{RoleEvent}>
     * @supplierCardinality 0..*
     * @clientCardinality 1
     * @label Roles
     * @modelguid {6F883A0C-F10A-45AA-8B8C-1EA72BE9486A}
     */
    private Collection Roles = new java.util.Vector();

	/** @modelguid {E294A219-9AFF-4CB2-9787-2EB278E002BF} */
     public RoleListEvent() { setServer(mojo.naming.ServerNames.LOGINCONTROLLER); }

	/** @modelguid {B6B67E2F-5234-47C3-8DBD-79F161524E24} */
    public Iterator getRoles() {
        return Roles.iterator();
    }

	/** @modelguid {1CF02F6E-6F64-4A16-8847-0F592115645A} */
    public void insertRole(RoleEvent aRole) {
        Roles.add(aRole);
    }
}
