/*
 * Created on Mar 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.manageassociate.reply;

import java.util.Date;

import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_rsojitrawala
 *
 */

public class AssociateResponseEvent extends ResponseEvent implements Comparable{

	private String associateId;
//	private Name assocName;
	private String assocFirstName;
	private String assocMiddleName;
	private String assocLastName;
	private String assocFormattedName;
	private String relationship;
	private String relationshipTypeId;
	private String displayLabel;
	private boolean status;

	private IPhoneNumber homePhone;
	private IPhoneNumber cellPhone;
	private IPhoneNumber workPhone;

	private String spn;
	
	private String comments;
	private IPhoneNumber pager;
	private String email;
	
	private String primaryResidenceAddressId;
//	private AssociateAddress primaryResidenceAddress = null;
	
//	private AssociateAddress otherAddress = null;
	private String otherAddressId;
	
	private AssociateAddressResponseEvent primaryAddress;
	private AssociateAddressResponseEvent otherAddress;

	private Date   updateDate;
	
	/**
	 * 
	 */
	public AssociateResponseEvent() {
		super();
		homePhone = new PhoneNumberBean("");
		cellPhone = new PhoneNumberBean("");
		workPhone = new PhoneNumberBean("");
		pager = new PhoneNumberBean("");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		AssociateResponseEvent evt = (AssociateResponseEvent) obj;
		
		String str1 = displayLabel.trim();
		String str2 = evt.getDisplayLabel().trim();		
		return str1.compareToIgnoreCase(str2);
	}
	
	/**
	 * @return String formatted Name
	 */
	public String getFormattedName()
	{
		String name = null;
		StringBuffer full = new StringBuffer();
		if(isLastAvailable()){
			full.append(" ");
			full.append(assocLastName);
		}
		if (this.isFirstAvailable())
		{
			//if (isLastAvailable())
			full.append(", ");
			
			full.append(assocFirstName);
			if (this.isMiddleAvailable())
			{
				full.append(" " + assocMiddleName);
			}
		}
		name = full.toString();
		return name;
	}

	
	/**
	 * @return boolean
	 */
	private boolean isLastAvailable()
	{
		boolean available = true;
		if (assocLastName == null || assocLastName.equals(""))
		{
			available = false;
		}
		return available;
	}

	/**
	 * @return biikeab
	 */
	private boolean isFirstAvailable()
	{
		boolean available = true;
		if (assocFirstName == null || assocFirstName.equals(""))
		{
			available = false;
		}
		return available;
	}
	/**
	 * @return boolean 
	 */
	private boolean isMiddleAvailable()
	{
		boolean available = true;
		if (assocMiddleName == null || assocMiddleName.equals(""))
		{
			available = false;
		}
		return available;
	}
	
	/**
	 * @return Returns the displayLabel.
	 */
	public String getDisplayLabel() {
		return displayLabel;
	}
	/**
	 * @param displayLabel The displayLabel to set.
	 */
	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}
	/**
	 * @return Returns the relationship.
	 */
	public String getRelationship() {
		return relationship;
	}
	/**
	 * @param relationship The relationship to set.
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	/**
	 * @return Returns the associateId.
	 */
	public String getAssociateId() {
		return associateId;
	}
	/**
	 * @param associateId The associateId to set.
	 */
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	/**
	 * @return Returns the status.
	 */
	public boolean getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return Returns the assocFirstName.
	 */
	public String getAssocFirstName() {
		return assocFirstName;
	}
	/**
	 * @param assocFirstName The assocFirstName to set.
	 */
	public void setAssocFirstName(String assocFirstName) {
		this.assocFirstName = assocFirstName;
	}
	/**
	 * @return Returns the assocLastName.
	 */
	public String getAssocLastName() {
		return assocLastName;
	}
	/**
	 * @param assocLastName The assocLastName to set.
	 */
	public void setAssocLastName(String assocLastName) {
		this.assocLastName = assocLastName;
	}
	/**
	 * @return Returns the assocMiddleName.
	 */
	public String getAssocMiddleName() {
		return assocMiddleName;
	}
	/**
	 * @param assocMiddleName The assocMiddleName to set.
	 */
	public void setAssocMiddleName(String assocMiddleName) {
		this.assocMiddleName = assocMiddleName;
	}
	/**
	 * @return Returns the assocFormattedName.
	 */
	public String getAssocFormattedName() {
		return assocFormattedName;
	}
	/**
	 * @param assocFormattedName The assocFormattedName to set.
	 */
	public void setAssocFormattedName(String assocFormattedName) {
		this.assocFormattedName = assocFormattedName;
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
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the relationshipTypeId.
	 */
	public String getRelationshipTypeId() {
		return relationshipTypeId;
	}
	/**
	 * @param relationshipTypeId The relationshipTypeId to set.
	 */
	public void setRelationshipTypeId(String relationshipTypeId) {
		this.relationshipTypeId = relationshipTypeId;
	}
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @return Returns the otherAddressId.
	 */
	public String getOtherAddressId() {
		return otherAddressId;
	}
	/**
	 * @param otherAddressId The otherAddressId to set.
	 */
	public void setOtherAddressId(String otherAddressId) {
		this.otherAddressId = otherAddressId;
	}
	/**
	 * @return Returns the primaryResidenceAddressId.
	 */
	public String getPrimaryResidenceAddressId() {
		return primaryResidenceAddressId;
	}
	/**
	 * @param primaryResidenceAddressId The primaryResidenceAddressId to set.
	 */
	public void setPrimaryResidenceAddressId(String primaryResidenceAddressId) {
		this.primaryResidenceAddressId = primaryResidenceAddressId;
	}
	
	
	/**
	 * @return Returns the cellPhone.
	 */
	public IPhoneNumber getCellPhone() {
		return cellPhone;
	}
	/**
	 * @param cellPhone The cellPhone to set.
	 */
	public void setCellPhone(IPhoneNumber cellPhone) {
		this.cellPhone = cellPhone;
	}
/**
 * @return Returns the homePhone.
 */
public IPhoneNumber getHomePhone() {
	return homePhone;
}
/**
 * @param homePhone The homePhone to set.
 */
public void setHomePhone(IPhoneNumber homePhone) {
	this.homePhone = homePhone;
}
/**
 * @return Returns the pager.
 */
public IPhoneNumber getPager() {
	return pager;
}
/**
 * @param pager The pager to set.
 */
public void setPager(IPhoneNumber pager) {
	this.pager = pager;
}
	/**
	 * @return Returns the workPhone.
	 */
	public IPhoneNumber getWorkPhone() {
		return workPhone;
	}
	/**
	 * @param workPhone The workPhone to set.
	 */
	public void setWorkPhone(IPhoneNumber workPhone) {
		this.workPhone = workPhone;
	}
	/**
	 * @return Returns the otherAddress.
	 */
	public AssociateAddressResponseEvent getOtherAddress() {
		return otherAddress;
	}
	/**
	 * @param otherAddress The otherAddress to set.
	 */
	public void setOtherAddress(AssociateAddressResponseEvent otherAddress) {
		this.otherAddress = otherAddress;
	}
	/**
	 * @return Returns the primaryAddress.
	 */
	public AssociateAddressResponseEvent getPrimaryAddress() {
		return primaryAddress;
	}
	/**
	 * @param primaryAddress The primaryAddress to set.
	 */
	public void setPrimaryAddress(AssociateAddressResponseEvent primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	/**
	 * @return Returns the updateDate.
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate The updateDate to set.
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
