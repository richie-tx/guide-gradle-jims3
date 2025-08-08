/*
 * Created on Jan 2, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchByServiceProviderEvent extends SearchServiceProviderEvent 
{
		//attributes for service provider name search
    private String serviceProviderName;
    private String serviceProviderStatus;
    private String isInHouse;
    private String hasContractProgram;

    /**
     * @return Returns the isInHouse.
     */
    public String getIsInHouse() {
        return isInHouse;
    }
    /**
     * @param isInHouse The isInHouse to set.
     */
    public void setIsInHouse(String isInHouse) {
        this.isInHouse = isInHouse;
    }
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
    /**
     * @return Returns the serviceProviderStatus.
     */
    public String getServiceProviderStatus() {
        return serviceProviderStatus;
    }
    /**
     * @param serviceProviderStatus The serviceProviderStatus to set.
     */
    public void setServiceProviderStatus(String serviceProviderStatus) {
        this.serviceProviderStatus = serviceProviderStatus;
    }
    /**
     * @return Returns the hasContractProgram.
     */
    public String getHasContractProgram() {
        return hasContractProgram;
    }
    /**
     * @param hasContractProgram The hasContractProgram to set.
     */
    public void setHasContractProgram(String hasContractProgram) {
        this.hasContractProgram = hasContractProgram;
    }
}//end of CSGetServiceProviderByNameEvent class
