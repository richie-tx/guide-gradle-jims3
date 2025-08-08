/*
 * Created on Feb 4, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSPContactEvent extends CompositeRequest 
{
    private String contactId;
    

    /**
     * @return Returns the contactId.
     */
    public String getContactId() {
        return contactId;
    }
    /**
     * @param contactId The contactId to set.
     */
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
}//end of GetSPContactEvent
