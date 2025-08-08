/*
 * Created on Nov 13, 2006
 *
 */
package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;
import mojo.km.messaging.RequestEvent;


/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetByIdsEvent extends RequestEvent {

	public Collection oids = new ArrayList(); 
    
    /**
     * @return Returns the oids.
     */
    public Collection getOids() {
        return oids;
    }
    /**
     * @param oids The oids to set.
     */
    public void setOids(Collection oids) {
        this.oids = oids;
    }

    /**
     * @param oids The oids to set.
     */
    public void insertOid(Collection oids) {
        this.oids.add(oids);
    }
}
