/*
 * Created on Jan 3, 2024
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileMasterStatusResponseEvent extends ResponseEvent
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String statusId;
    private String juvenileId;
    
    
    
    
    public String getStatusId()
    {
        return statusId;
    }
    public void setStatusId(String statusId)
    {
        this.statusId = statusId;
    }
    public String getJuvenileId()
    {
        return juvenileId;
    }
    public void setJuvenileId(String juvenileId)
    {
        this.juvenileId = juvenileId;
    }	
	
	
}
