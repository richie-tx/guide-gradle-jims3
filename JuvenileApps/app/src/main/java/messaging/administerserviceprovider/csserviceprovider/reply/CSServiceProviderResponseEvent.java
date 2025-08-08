/*
 * Created on Dec 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerserviceprovider.csserviceprovider.reply;

import java.util.List;
import messaging.contact.domintf.IAddress;
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
    private java.util.Date startDate;
    private boolean isInHouse;
    private String statusCode;
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
    private String validLocStatus;
    private List programs;
    private List contacts;
    
 
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
        return isInHouse;
    }
    /**
     * @param isInHouse The isInHouse to set.
     */
    public void setInHouse(boolean isInHouse) {
        this.isInHouse = isInHouse;
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
    public java.util.Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return Returns the statusCode.
     */
    public String getStatusCode() {
        return statusCode;
    }
    /**
     * @param statusCode The statusCode to set.
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    /**
	 * @return the validLocStatus
	 */
	public String getValidLocStatus() {
		return validLocStatus;
	}
	/**
	 * @param validLocStatus the validLocStatus to set
	 */
	public void setValidLocStatus(String validLocStatus) {
		this.validLocStatus = validLocStatus;
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
}//end of CSServiceProviderResponseEvent class 
