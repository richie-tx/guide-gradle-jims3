/*
 * Created on Sep 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.manageassociate.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteAssociateErrorEvent extends ResponseEvent
{
    private String errorMsg;

    /**
     * @return Returns the errorMsg.
     */
    public String getErrorMsg()
    {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            The errorMsg to set.
     */
    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }
}
