/*
 * Created on Feb 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider.reply;

import mojo.km.messaging.ResponseEvent;


/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DuplicateServiceProviderProgramResponseEvent extends ResponseEvent
{
    private String serviceProviderId;
    private String programIdentifier;
    private String programName;
    private String message;
    
    
    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return Returns the programIdentifier.
     */
    public String getProgramIdentifier() {
        return programIdentifier;
    }
    /**
     * @param programIdentifier The programIdentifier to set.
     */
    public void setProgramIdentifier(String programIdentifier) {
        this.programIdentifier = programIdentifier;
    }
    /**
     * @return Returns the programName.
     */
    public String getProgramName() {
        return programName;
    }
    /**
     * @param programName The programName to set.
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    /**
     * @return Returns the serviceProviderId.
     */
    public String getServiceProviderId() {
        return serviceProviderId;
    }
    /**
     * @param serviceProviderId The serviceProviderId to set.
     */
    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }
}
