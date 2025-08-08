/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.manageassociate;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_rsojitrawala
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class IsAssociateReferencedEvent extends RequestEvent 
{

    private String associateId;

    /**
     * @return Returns the associateId.
     */
    public String getAssociateId()
    {
        return associateId;
    }

    /**
     * @param associateId
     *            The associateId to set.
     */
    public void setAssociateId(String associateId)
    {
        this.associateId = associateId;
    }

}
