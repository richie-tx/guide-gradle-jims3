/*
 * Created on Jan 29, 2008
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
public class GetSPProgramEvent extends CompositeRequest 
{
    private String programId;
    /**
     * @return Returns the programId.
     */
    public String getProgramId() {
        return programId;
    }
    /**
     * @param programId The programId to set.
     */
    public void setProgramId(String programId) {
        this.programId = programId;
    }
}//end of GetSPProgramEvent()
