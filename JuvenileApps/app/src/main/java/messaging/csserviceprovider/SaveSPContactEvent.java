/*
 * Created on Feb 4, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

import java.util.Date;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveSPContactEvent extends CompositeRequest 
{
    	//Member Variables
    private String serviceProviderContactId;
    private String serviceProviderId;
    private String lastName;
    private String firstName;
    private String middleName;
    private boolean isAdminContact;
    private String officeNumber;
    private String extension;
    private String faxNumber;
    private String cellNumber;
    private String pagerNumber;
    private String emailAddress;
    private String notes;
    private String statusCode;
    private Date statusChangeDate;
    private String statusChangeComments;
    private String jobTitle;

    
    /**
     * @return Returns the cellNumber.
     */
    public String getCellNumber() {
        return cellNumber;
    }
    /**
     * @param cellNumber The cellNumber to set.
     */
    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
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
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return Returns the isAdminContact.
     */
    public boolean getIsAdminContact() {
        return isAdminContact;
    }
    /**
     * @param isAdminContact The isAdminContact to set.
     */
    public void setIsAdminContact(boolean isAdminContact) {
        this.isAdminContact = isAdminContact;
    }
    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return Returns the middleName.
     */
    public String getMiddleName() {
        return middleName;
    }
    /**
     * @param middleName The middleName to set.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    /**
     * @return Returns the notes.
     */
    public String getNotes() {
        return notes;
    }
    /**
     * @param notes The notes to set.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**
     * @return Returns the officeNumber.
     */
    public String getOfficeNumber() {
        return officeNumber;
    }
    /**
     * @param officeNumber The officeNumber to set.
     */
    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
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
     * @param isAdminContact The isAdminContact to set.
     */
    public void setAdminContact(boolean isAdminContact) {
        this.isAdminContact = isAdminContact;
    }
    /**
     * @return Returns the pagerNumber.
     */
    public String getPagerNumber() {
        return pagerNumber;
    }
    /**
     * @param pagerNumber The pagerNumber to set.
     */
    public void setPagerNumber(String pagerNumber) {
        this.pagerNumber = pagerNumber;
    }
        /**
         * @return Returns the serviceProviderContactId.
         */
        public String getServiceProviderContactId() {
            return serviceProviderContactId;
        }
        /**
         * @param serviceProviderContactId The serviceProviderContactId to set.
         */
        public void setServiceProviderContactId(String serviceProviderContactId) {
            this.serviceProviderContactId = serviceProviderContactId;
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
     * @return Returns the statusChangeComments.
     */
    public String getStatusChangeComments() {
        return statusChangeComments;
    }
    /**
     * @param statusChangeComments The statusChangeComments to set.
     */
    public void setStatusChangeComments(String statusChangeComments) {
        this.statusChangeComments = statusChangeComments;
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
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
}
