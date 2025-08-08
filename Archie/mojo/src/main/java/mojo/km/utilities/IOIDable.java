package mojo.km.utilities;

import java.io.Serializable;

/**
 * Interface used for objects that have OIDs.  This interface is used by the
 * abstract base classes for commands. <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 *  Egan Royal          08/27/2001  Created.
 * </pre>
 * @author Egan Royal
 * @modelguid {7B2573F5-A654-4ABA-9588-B29283B7D97D}
 */
public interface IOIDable {

    /**
    * sets the OID
    * @param anOID An OID.
    * @modelguid {0331A732-3D2C-476A-BBC7-34E66444B51F}
    */
    public void setOID(String anOID);

    /**
    * returns the OID.
    * @return The OID.
    * @modelguid {DAA97277-32B3-488A-9C30-C91B29EB2C5B}
    */
    public String getOID();
}
