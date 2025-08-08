/*
 * Created on Dec 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider.reply;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IAddress;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSServiceProviderResponseEvent extends ResponseEvent 
{
    /************ CSServiceProviderResponseEvent Member Variables ***********************/
    private int serviceProviderId;
    private String serviceProviderName;
    private Date startDate;
    private boolean inHouse;
    private String ifasNumber;
    private String phoneNumber;
    private String extension;
    private String faxNumber;
    private String website;
    private String emailAddress;
    private String ftpAddress;
    private String comments;
    private IAddress billingAddress;
    private String billingAddressId;
    private IAddress shippingAddress;
    private String shippingAddressId;
    private List programs;
    private List contacts;
    private boolean hasContractProgram;
    private boolean operationSuccessful = false;
    private String serviceProviderStatus;
    private Date statusChangeDate;
    private boolean isFaithBased; 

    
	/**** Attributes of view that shows a service provider's programs *****/
    private String programIdentifier;
    private String programName;
    private String programGroupCode;
    private String programGroupDesc;
    private String programTypeCode;
    private String programTypeDesc;
    private boolean isContractProgram;
    private String programStatusCode;
    private String programStatusDesc;
    
	public static Comparator serviceProviderComparator = new Comparator( )
	{
		public int compare( Object service_provider, Object other_service_provider )
		{
			String sp_name = ((CSServiceProviderResponseEvent)service_provider).getServiceProviderName();
			String other_sp_name = ((CSServiceProviderResponseEvent)other_service_provider).getServiceProviderName();
			return sp_name.compareTo( other_sp_name ) ;
		}
	}; 	
 
    /**
     * @return Returns the operationSuccessful.
     */
    public boolean isOperationSuccessful() {
        return operationSuccessful;
    }
    /**
     * @param operationSuccessful The operationSuccessful to set.
     */
    public void setOperationSuccessful(boolean operationSuccessful) 
    {
        this.operationSuccessful = operationSuccessful;
    }
    /************ Member Variable Getters & Setters ***********************/

    /**
     * @return Returns the billingAddress.
     */
    public IAddress getBillingAddress() {
        return billingAddress;
    }
    /**
     * @param billingAddress The billingAddress to set.
     */
    public void setBillingAddress(IAddress billingAddress) {
        this.billingAddress = billingAddress;
    }
    /**
     * @return Returns the billingAddressId.
     */
    public String getBillingAddressId() {
        return billingAddressId;
    }
    /**
     * @param billingAddressId The billingAddressId to set.
     */
    public void setBillingAddressId(String billingAddressId) {
        this.billingAddressId = billingAddressId;
    }
    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * @return Returns the contacts.
     */
    public List getContacts() {
        return contacts;
    }
    /**
     * @param contacts The contacts to set.
     */
    public void setContacts(List contacts) {
        this.contacts = contacts;
    }
    /**
     * @return Returns the emailAddress.
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * @param emailAddress The emailAddress to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * @return Returns the extension.
     */
    public String getExtension() {
        return extension;
    }
    /**
     * @param extension The extension to set.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
    /**
     * @return Returns the faxNumber.
     */
    public String getFaxNumber() {
        return faxNumber;
    }
    /**
     * @param faxNumber The faxNumber to set.
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    /**
     * @return Returns the ftpAddress.
     */
    public String getFtpAddress() {
        return ftpAddress;
    }
    /**
     * @param ftpAddress The ftpAddress to set.
     */
    public void setFtpAddress(String ftpAddress) {
        this.ftpAddress = ftpAddress;
    }
    /**
     * @return Returns the ifasNumber.
     */
    public String getIfasNumber() {
        return ifasNumber;
    }
    /**
     * @param ifasNumber The ifasNumber to set.
     */
    public void setIfasNumber(String ifasNumber) {
        this.ifasNumber = ifasNumber;
    }
    /**
     * @return Returns the isInHouse.
     */
    public boolean isInHouse() {
        return inHouse;
    }
    /**
     * @param isInHouse The isInHouse to set.
     */
    public void setInHouse(boolean inHouse) {
        this.inHouse = inHouse;
    }
        
    public boolean isFaithBased() {
		return isFaithBased;
	}
    
	public void setFaithBased(boolean isFaithBased) {
		this.isFaithBased = isFaithBased;
	}
	
    /**
     * @return Returns the phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * @param phoneNumber The phoneNumber to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * @return Returns the programs.
     */
    public List getPrograms() {
        return programs;
    }
    /**
     * @param programs The programs to set.
     */
    public void setPrograms(List programs) {
        this.programs = programs;
    }
    /**
     * @return Returns the serviceProviderId.
     */
    public int getServiceProviderId() {
        return serviceProviderId;
    }
    /**
     * @param serviceProviderId The serviceProviderId to set.
     */
    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
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
     * @return Returns the shippingAddress.
     */
    public IAddress getShippingAddress() {
        return shippingAddress;
    }
    /**
     * @param shippingAddress The shippingAddress to set.
     */
    public void setShippingAddress(IAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    /**
     * @return Returns the shippingAddressId.
     */
    public String getShippingAddressId() {
        return shippingAddressId;
    }
    /**
     * @param shippingAddressId The shippingAddressId to set.
     */
    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
    /**
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return Returns the website.
     */
    public String getWebsite() {
        return website;
    }
    /**
     * @param website The website to set.
     */
    public void setWebsite(String website) {
        this.website = website;
    }
    /**
     * @return Returns the hasContractProgram.
     */
    public boolean getHasContractProgram() {
        return hasContractProgram;
    }
    /**
     * @param hasContractProgram The hasContractProgram to set.
     */
    public void setHasContractProgram(boolean hasContractProgram) {
        this.hasContractProgram = hasContractProgram;
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
     * @return Returns the statusChangeDate.
     */
    public Date getStatusChangeDate() {
        return statusChangeDate;
    }
    /**
     * @param statusChangeDate The statusChangeDate to set.
     */
    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }
    /**
     * @return Returns the isContractProgram.
     */
    public boolean getIsContractProgram() {
        return isContractProgram;
    }
    /**
     * @param isContractProgram The isContractProgram to set.
     */
    public void setIsContractProgram(boolean isContractProgram) {
        this.isContractProgram = isContractProgram;
    }
    /**
     * @return Returns the programGroupCode.
     */
    public String getProgramGroupCode() {
        return programGroupCode;
    }
    /**
     * @param programGroupCode The programGroupCode to set.
     */
    public void setProgramGroupCode(String programGroupCode) {
        this.programGroupCode = programGroupCode;
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
     * @return Returns the programStatusCode.
     */
    public String getProgramStatusCode() {
        return programStatusCode;
    }
    /**
     * @param programStatusCode The programStatusCode to set.
     */
    public void setProgramStatusCode(String programStatusCode) {
        this.programStatusCode = programStatusCode;
    }
    /**
     * @return Returns the programTypeCode.
     */
    public String getProgramTypeCode() {
        return programTypeCode;
    }
    /**
     * @param programTypeCode The programTypeCode to set.
     */
    public void setProgramTypeCode(String programTypeCode) {
        this.programTypeCode = programTypeCode;
    }
	public String getProgramGroupDesc() {
		return programGroupDesc;
	}
	public void setProgramGroupDesc(String programGroupDesc) {
		this.programGroupDesc = programGroupDesc;
	}
	public String getProgramTypeDesc() {
		return programTypeDesc;
	}
	public void setProgramTypeDesc(String programTypeDesc) {
		this.programTypeDesc = programTypeDesc;
	}
	public String getProgramStatusDesc() {
		return programStatusDesc;
	}
	public void setProgramStatusDesc(String programStatusDesc) {
		this.programStatusDesc = programStatusDesc;
	}
}//end of CSServiceProviderResponseEvent class 
