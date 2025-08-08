/*
 * Created on Jan 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateCSServiceProviderEvent extends RequestEvent 
{
    private String serviceProviderName;
    
    /**
     * @return Returns the serviceProviderName.
     */
    public String getServiceProviderName() {
        return serviceProviderName;
    }
    /**
     * @param serviceProviderName The serviceProviderName to set.
     */
    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }
    
}//end of ValidateServiceProvider class
