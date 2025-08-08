package messaging.transferobjects;/*
 * Created on Aug 20, 2007
 *
 
package messaging.transferobjects;

import java.util.Collection;
import java.util.Date;

*//**
 * @author cc_mdsouza
 *
 *//*
public class DepartmentTO 
extends PersistentObjectTO
{

	private Date subscriberCivilTerminationDate;
	private String countyId;
	private Date setcicInactiveDate;
	private Date setcicDate;
	private String comments;
	private String setcicAccessId;
	private String departmentId;
	private String fax;
	private String mailingAddressId;
	private String agencyName;
	private String originatingAgencyId;
	private String warrantConfirmationPhoneExt;
	private String statusId;
	private String departmentName;
	private Date setcicRenewDate;
	private Date terminationDate;
	private String labelInd;
	private String gritsInd;
	private String addressId;
	private Date activationDate;
	private String agencyId;
	private String warrantConfirmationPhone;
	private Date subscriberCivilActivationDate;
	private Date subscriberCriminalActivationDate;
	private String agencyTypeId;
	private String createOfficerProfileInd;
	private Date subscriberCriminalTerminationDate;
	private String billingAddressId;
	private String orgCode;
	private String inUseInd;
	private String accessTypeId;
	private String departmentUserCount;
	
	private AgencyTO agency = null;
	private CodeTO agencyType = null;
	private CodeTO accessType = null;
	private CodeTO setcicAccess = null;
	private CodeTO status = null;
	private AddressTO mailingAddress = null;
	private AddressTO address = null;
	private CodeTO county = null;
	private AddressTO billingAddress = null;
	
	private Collection contacts = null;
	
	
	public DepartmentTO()
	{
	}
 
	
	
	
	
	*//**
	 * @return Returns the accessType.
	 *//*
	public CodeTO getAccessType() {
		return accessType;
	}
	*//**
	 * @param accessType The accessType to set.
	 *//*
	public void setAccessType(CodeTO accessType) {
		this.accessType = accessType;
	}
	*//**
	 * @return Returns the accessTypeId.
	 *//*
	public String getAccessTypeId() {
		return accessTypeId;
	}
	*//**
	 * @param accessTypeId The accessTypeId to set.
	 *//*
	public void setAccessTypeId(String accessTypeId) {
		this.accessTypeId = accessTypeId;
	}
	*//**
	 * @return Returns the activationDate.
	 *//*
	public Date getActivationDate() {
		return activationDate;
	}
	*//**
	 * @param activationDate The activationDate to set.
	 *//*
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	*//**
	 * @return Returns the address.
	 *//*
	public AddressTO getAddress() {
		return address;
	}
	*//**
	 * @param address The address to set.
	 *//*
	public void setAddress(AddressTO address) {
		this.address = address;
	}
	*//**
	 * @return Returns the addressId.
	 *//*
	public String getAddressId() {
		return addressId;
	}
	*//**
	 * @param addressId The addressId to set.
	 *//*
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	*//**
	 * @return Returns the agency.
	 *//*
	public AgencyTO getAgency() {
		return agency;
	}
	*//**
	 * @param agency The agency to set.
	 *//*
	public void setAgency(AgencyTO agency) {
		this.agency = agency;
	}
	*//**
	 * @return Returns the agencyId.
	 *//*
	public String getAgencyId() {
		return agencyId;
	}
	*//**
	 * @param agencyId The agencyId to set.
	 *//*
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	*//**
	 * @return Returns the agencyName.
	 *//*
	public String getAgencyName() {
		return agencyName;
	}
	*//**
	 * @param agencyName The agencyName to set.
	 *//*
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	*//**
	 * @return Returns the agencyType.
	 *//*
	public CodeTO getAgencyType() {
		return agencyType;
	}
	*//**
	 * @param agencyType The agencyType to set.
	 *//*
	public void setAgencyType(CodeTO agencyType) {
		this.agencyType = agencyType;
	}
	*//**
	 * @return Returns the agencyTypeId.
	 *//*
	public String getAgencyTypeId() {
		return agencyTypeId;
	}
	*//**
	 * @param agencyTypeId The agencyTypeId to set.
	 *//*
	public void setAgencyTypeId(String agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}
	*//**
	 * @return Returns the billingAddress.
	 *//*
	public AddressTO getBillingAddress() {
		return billingAddress;
	}
	*//**
	 * @param billingAddress The billingAddress to set.
	 *//*
	public void setBillingAddress(AddressTO billingAddress) {
		this.billingAddress = billingAddress;
	}
	*//**
	 * @return Returns the billingAddressId.
	 *//*
	public String getBillingAddressId() {
		return billingAddressId;
	}
	*//**
	 * @param billingAddressId The billingAddressId to set.
	 *//*
	public void setBillingAddressId(String billingAddressId) {
		this.billingAddressId = billingAddressId;
	}
	*//**
	 * @return Returns the comments.
	 *//*
	public String getComments() {
		return comments;
	}
	*//**
	 * @param comments The comments to set.
	 *//*
	public void setComments(String comments) {
		this.comments = comments;
	}
	*//**
	 * @return Returns the contacts.
	 *//*
	public Collection getContacts() {
		return contacts;
	}
	*//**
	 * @param contacts The contacts to set.
	 *//*
	public void setContacts(Collection contacts) {
		this.contacts = contacts;
	}
	*//**
	 * @return Returns the county.
	 *//*
	public CodeTO getCounty() {
		return county;
	}
	*//**
	 * @param county The county to set.
	 *//*
	public void setCounty(CodeTO county) {
		this.county = county;
	}
	*//**
	 * @return Returns the countyId.
	 *//*
	public String getCountyId() {
		return countyId;
	}
	*//**
	 * @param countyId The countyId to set.
	 *//*
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	*//**
	 * @return Returns the createOfficerProfileInd.
	 *//*
	public String getCreateOfficerProfileInd() {
		return createOfficerProfileInd;
	}
	*//**
	 * @param createOfficerProfileInd The createOfficerProfileInd to set.
	 *//*
	public void setCreateOfficerProfileInd(String createOfficerProfileInd) {
		this.createOfficerProfileInd = createOfficerProfileInd;
	}
	*//**
	 * @return Returns the departmentId.
	 *//*
	public String getDepartmentId() {
		return departmentId;
	}
	*//**
	 * @param departmentId The departmentId to set.
	 *//*
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	*//**
	 * @return Returns the departmentName.
	 *//*
	public String getDepartmentName() {
		return departmentName;
	}
	*//**
	 * @param departmentName The departmentName to set.
	 *//*
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	*//**
	 * @return Returns the departmentUserCount.
	 *//*
	public String getDepartmentUserCount() {
		return departmentUserCount;
	}
	*//**
	 * @param departmentUserCount The departmentUserCount to set.
	 *//*
	public void setDepartmentUserCount(String departmentUserCount) {
		this.departmentUserCount = departmentUserCount;
	}
	*//**
	 * @return Returns the fax.
	 *//*
	public String getFax() {
		return fax;
	}
	*//**
	 * @param fax The fax to set.
	 *//*
	public void setFax(String fax) {
		this.fax = fax;
	}
	*//**
	 * @return Returns the gritsInd.
	 *//*
	public String getGritsInd() {
		return gritsInd;
	}
	*//**
	 * @param gritsInd The gritsInd to set.
	 *//*
	public void setGritsInd(String gritsInd) {
		this.gritsInd = gritsInd;
	}
	*//**
	 * @return Returns the inUseInd.
	 *//*
	public String getInUseInd() {
		return inUseInd;
	}
	*//**
	 * @param inUseInd The inUseInd to set.
	 *//*
	public void setInUseInd(String inUseInd) {
		this.inUseInd = inUseInd;
	}
	*//**
	 * @return Returns the labelInd.
	 *//*
	public String getLabelInd() {
		return labelInd;
	}
	*//**
	 * @param labelInd The labelInd to set.
	 *//*
	public void setLabelInd(String labelInd) {
		this.labelInd = labelInd;
	}
	*//**
	 * @return Returns the mailingAddress.
	 *//*
	public AddressTO getMailingAddress() {
		return mailingAddress;
	}
	*//**
	 * @param mailingAddress The mailingAddress to set.
	 *//*
	public void setMailingAddress(AddressTO mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	*//**
	 * @return Returns the mailingAddressId.
	 *//*
	public String getMailingAddressId() {
		return mailingAddressId;
	}
	*//**
	 * @param mailingAddressId The mailingAddressId to set.
	 *//*
	public void setMailingAddressId(String mailingAddressId) {
		this.mailingAddressId = mailingAddressId;
	}
	*//**
	 * @return Returns the orgCode.
	 *//*
	public String getOrgCode() {
		return orgCode;
	}
	*//**
	 * @param orgCode The orgCode to set.
	 *//*
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	*//**
	 * @return Returns the originatingAgencyId.
	 *//*
	public String getOriginatingAgencyId() {
		return originatingAgencyId;
	}
	*//**
	 * @param originatingAgencyId The originatingAgencyId to set.
	 *//*
	public void setOriginatingAgencyId(String originatingAgencyId) {
		this.originatingAgencyId = originatingAgencyId;
	}
	*//**
	 * @return Returns the setcicAccess.
	 *//*
	public CodeTO getSetcicAccess() {
		return setcicAccess;
	}
	*//**
	 * @param setcicAccess The setcicAccess to set.
	 *//*
	public void setSetcicAccess(CodeTO setcicAccess) {
		this.setcicAccess = setcicAccess;
	}
	*//**
	 * @return Returns the setcicAccessId.
	 *//*
	public String getSetcicAccessId() {
		return setcicAccessId;
	}
	*//**
	 * @param setcicAccessId The setcicAccessId to set.
	 *//*
	public void setSetcicAccessId(String setcicAccessId) {
		this.setcicAccessId = setcicAccessId;
	}
	*//**
	 * @return Returns the setcicDate.
	 *//*
	public Date getSetcicDate() {
		return setcicDate;
	}
	*//**
	 * @param setcicDate The setcicDate to set.
	 *//*
	public void setSetcicDate(Date setcicDate) {
		this.setcicDate = setcicDate;
	}
	*//**
	 * @return Returns the setcicInactiveDate.
	 *//*
	public Date getSetcicInactiveDate() {
		return setcicInactiveDate;
	}
	*//**
	 * @param setcicInactiveDate The setcicInactiveDate to set.
	 *//*
	public void setSetcicInactiveDate(Date setcicInactiveDate) {
		this.setcicInactiveDate = setcicInactiveDate;
	}
	*//**
	 * @return Returns the setcicRenewDate.
	 *//*
	public Date getSetcicRenewDate() {
		return setcicRenewDate;
	}
	*//**
	 * @param setcicRenewDate The setcicRenewDate to set.
	 *//*
	public void setSetcicRenewDate(Date setcicRenewDate) {
		this.setcicRenewDate = setcicRenewDate;
	}
	*//**
	 * @return Returns the status.
	 *//*
	public CodeTO getStatus() {
		return status;
	}
	*//**
	 * @param status The status to set.
	 *//*
	public void setStatus(CodeTO status) {
		this.status = status;
	}
	*//**
	 * @return Returns the statusId.
	 *//*
	public String getStatusId() {
		return statusId;
	}
	*//**
	 * @param statusId The statusId to set.
	 *//*
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	*//**
	 * @return Returns the subscriberCivilActivationDate.
	 *//*
	public Date getSubscriberCivilActivationDate() {
		return subscriberCivilActivationDate;
	}
	*//**
	 * @param subscriberCivilActivationDate The subscriberCivilActivationDate to set.
	 *//*
	public void setSubscriberCivilActivationDate(Date subscriberCivilActivationDate) {
		this.subscriberCivilActivationDate = subscriberCivilActivationDate;
	}
	*//**
	 * @return Returns the subscriberCivilTerminationDate.
	 *//*
	public Date getSubscriberCivilTerminationDate() {
		return subscriberCivilTerminationDate;
	}
	*//**
	 * @param subscriberCivilTerminationDate The subscriberCivilTerminationDate to set.
	 *//*
	public void setSubscriberCivilTerminationDate(Date subscriberCivilTerminationDate) {
		this.subscriberCivilTerminationDate = subscriberCivilTerminationDate;
	}
	*//**
	 * @return Returns the subscriberCriminalActivationDate.
	 *//*
	public Date getSubscriberCriminalActivationDate() {
		return subscriberCriminalActivationDate;
	}
	*//**
	 * @param subscriberCriminalActivationDate The subscriberCriminalActivationDate to set.
	 *//*
	public void setSubscriberCriminalActivationDate(Date subscriberCriminalActivationDate) {
		this.subscriberCriminalActivationDate = subscriberCriminalActivationDate;
	}
	*//**
	 * @return Returns the subscriberCriminalTerminationDate.
	 *//*
	public Date getSubscriberCriminalTerminationDate() {
		return subscriberCriminalTerminationDate;
	}
	*//**
	 * @param subscriberCriminalTerminationDate The subscriberCriminalTerminationDate to set.
	 *//*
	public void setSubscriberCriminalTerminationDate(Date subscriberCriminalTerminationDate) {
		this.subscriberCriminalTerminationDate = subscriberCriminalTerminationDate;
	}
	*//**
	 * @return Returns the terminationDate.
	 *//*
	public Date getTerminationDate() {
		return terminationDate;
	}
	*//**
	 * @param terminationDate The terminationDate to set.
	 *//*
	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}
	*//**
	 * @return Returns the warrantConfirmationPhone.
	 *//*
	public String getWarrantConfirmationPhone() {
		return warrantConfirmationPhone;
	}
	*//**
	 * @param warrantConfirmationPhone The warrantConfirmationPhone to set.
	 *//*
	public void setWarrantConfirmationPhone(String warrantConfirmationPhone) {
		this.warrantConfirmationPhone = warrantConfirmationPhone;
	}
	*//**
	 * @return Returns the warrantConfirmationPhoneExt.
	 *//*
	public String getWarrantConfirmationPhoneExt() {
		return warrantConfirmationPhoneExt;
	}
	*//**
	 * @param warrantConfirmationPhoneExt The warrantConfirmationPhoneExt to set.
	 *//*
	public void setWarrantConfirmationPhoneExt(String warrantConfirmationPhoneExt) {
		this.warrantConfirmationPhoneExt = warrantConfirmationPhoneExt;
	}
}
*/