// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\manageassociate\\DeleteAssociateEvent.java

package messaging.manageassociate;

import mojo.km.messaging.RequestEvent;

public class DeleteAssociateEvent extends RequestEvent
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
