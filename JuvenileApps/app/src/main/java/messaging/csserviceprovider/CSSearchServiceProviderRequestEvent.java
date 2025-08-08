/*
 * Created on Dec 31, 2007
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
public class CSSearchServiceProviderRequestEvent extends RequestEvent 
{
    	//specifies whether search is by service provider or by program
    private String searchType;
    	
    	//attributes for service provider name search
    private String serviceProviderName;
    private String serviceProviderStatus;
    private String isInHouse;
    
    	//attributes for service provider program search
    private String programName;
    private String programGroup;
    private String programType;
    private String isContractProgram;
    private String programStatus;
    
    /**
     * @return Returns the isContractProgram.
     */
    public String getIsContractProgram() {
        return isContractProgram;
    }
    /**
     * @param isContractProgram The isContractProgram to set.
     */
    public void setIsContractProgram(String isContractProgram) {
        this.isContractProgram = isContractProgram;
    }
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
     * @return Returns the programGroup.
     */
    public String getProgramGroup() {
        return programGroup;
    }
    /**
     * @param programGroup The programGroup to set.
     */
    public void setProgramGroup(String programGroup) {
        this.programGroup = programGroup;
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
     * @return Returns the programStatus.
     */
    public String getProgramStatus() {
        return programStatus;
    }
    /**
     * @param programStatus The programStatus to set.
     */
    public void setProgramStatus(String programStatus) {
        this.programStatus = programStatus;
    }
    /**
     * @return Returns the programType.
     */
    public String getProgramType() {
        return programType;
    }
    /**
     * @param programType The programType to set.
     */
    public void setProgramType(String programType) {
        this.programType = programType;
    }
        /**
         * @return Returns the searchType.
         */
        public String getSearchType() {
            return searchType;
        }
        /**
         * @param searchType The searchType to set.
         */
        public void setSearchType(String searchType) {
            this.searchType = searchType;
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
}//end of CSSearchServiceProviderRequestEvent class
