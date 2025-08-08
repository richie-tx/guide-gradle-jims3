/*
 * Created on Dec 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

import mojo.km.messaging.Composite.CompositeRequest;
import messaging.contact.domintf.IAddress;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveServiceProviderEvent extends CompositeRequest 
{

    /************ SaveServiceProviderRequestEvent Member Variables ***********************/
    private String serviceProviderId;
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
    private IAddress shippingAddress;
    private boolean isFaithBased;

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
    
	public boolean isFaithBased() {
		return isFaithBased;
	}
	public void setFaithBased(boolean isFaithBased) {
		this.isFaithBased = isFaithBased;
	}
}//end of CSSaveServiceProviderRequestEvent class
