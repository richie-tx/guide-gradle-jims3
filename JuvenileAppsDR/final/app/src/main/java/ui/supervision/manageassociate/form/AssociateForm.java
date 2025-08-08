/*
 * Created on Nov 18, 2005
 */
package ui.supervision.manageassociate.form;

import java.util.ArrayList;
import java.util.Collection;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.manageassociate.reply.AssociateAddressResponseEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDCommonSupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.Address;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;

/**
 * @author cc_rsojitrawala
 * 
 * The form to hold all the data elements corresponding to an "Associate".
 */
public class AssociateForm extends ActionForm {

//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";

	private String fromPath="";
	
	//	Associate Information
	private Name associateName;
	private String relationship;
	private String relationshipId;
	private String comments;

	//	Contact Information
	private IPhoneNumber homePhone;
	private IPhoneNumber workPhone;
	private IPhoneNumber cellPhone;
	private IPhoneNumber pager;
	private String email;
	
	private boolean status;
	private String spn;
	private String associateId;
	
	//	Primary Residence Address
	private AssociateAddress primaryResidenceAddress = new AssociateAddress();
	
	// 	Other Address
	private AssociateAddress otherAddress = new AssociateAddress();
	
	private Collection associatesList = new ArrayList();
	
	private IName updateJIMS2User;
	private String updateDate;
	
	public AssociateForm() {
		associateName = new Name();
		homePhone = new PhoneNumber("");
		workPhone = new PhoneNumber("");
		cellPhone = new PhoneNumber("");
		pager = new PhoneNumber("");
		updateJIMS2User = new Name();
	}

	public void clear() {
	//	action="";
	//	secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
		
	//	fromPath="";
		
		associateName = new Name();
		relationship = "";
		relationshipId = "";
		comments = "";
		homePhone = new PhoneNumber("");
		workPhone = new PhoneNumber("");
		cellPhone = new PhoneNumber("");
		pager = new PhoneNumber("");
		email = "";
		status = true;
		
		primaryResidenceAddress = new AssociateAddress();
		otherAddress = new AssociateAddress();
		
		updateJIMS2User = new Name();
		updateDate = null;
	}
	
	public void setupNewAssociate(String spn, String fromPath) {
		
		this.clear();
		this.setAction("create");
		
		this.setSpn(spn);
		this.setFromPath(fromPath);
		
		//	Always set the primary residence address type field to residence 
		this.getPrimaryResidenceAddress().setAddressTypeId(PDCommonSupervisionConstants.RESIDENCE_ADDRESS_TYPE);
		
		this.getPrimaryResidenceAddress().setIsPrimaryResidenceAddress(true);
		this.getOtherAddress().setIsPrimaryResidenceAddress(false);
		
		//	New associate always has a GOOD status
		this.setStatus(true);
	}
	
	public void copySuperviseeResidenceAddress(PartyResponseEvent partyAddressResponse)
	{
		if (this.getSecondaryAction().equals(UIConstants.COPY_TO_PRIMARY_ADDRESS)){
			this.getPrimaryResidenceAddress().setStreetNum(partyAddressResponse.getCurrentAddressStreetNum());
			this.getPrimaryResidenceAddress().setStreetName(partyAddressResponse.getCurrentAddressStreetName());
			this.getPrimaryResidenceAddress().setCity(partyAddressResponse.getCurrentAddressCity());
			this.getPrimaryResidenceAddress().setStateId(partyAddressResponse.getCurrentAddressStateId());
			this.getPrimaryResidenceAddress().setZipCode(partyAddressResponse.getCurrentAddressZipCode());
		}
		else{//this.getSecondaryAction().equals(UIConstants.COPY_TO_OTHER_ADDRESS)
			this.getOtherAddress().setStreetNum(partyAddressResponse.getCurrentAddressStreetNum());
			this.getOtherAddress().setStreetName(partyAddressResponse.getCurrentAddressStreetName());
			this.getOtherAddress().setCity(partyAddressResponse.getCurrentAddressCity());
			this.getOtherAddress().setStateId(partyAddressResponse.getCurrentAddressStateId());
			this.getOtherAddress().setZipCode(partyAddressResponse.getCurrentAddressZipCode());
		}
			
		this.setSecondaryAction(UIConstants.COPY_SUCCESS);
	}
	
	/**
	 * @param AssociateResponseEvent
	 */
	public void setForm(AssociateResponseEvent resp) {
		this.clear();
		this.setAssociateId(resp.getAssociateId());
		this.setSpn(resp.getSpn());
		this.getAssociateName().setLastName(resp.getAssocLastName());
		this.getAssociateName().setFirstName(resp.getAssocFirstName());
		this.getAssociateName().setMiddleName(resp.getAssocMiddleName());
		this.setRelationshipId(resp.getRelationshipTypeId());
		this.setStatus(resp.getStatus());
		this.setComments(resp.getComments());
		this.setHomePhone(resp.getHomePhone());
		this.setWorkPhone(resp.getWorkPhone());
		this.setCellPhone(resp.getCellPhone());
		this.setPager(resp.getPager());
		this.setEmail(resp.getEmail());
		
		AssociateAddress primaryAddress =  AssociateAddress.convertAssociateAddressResponseEvent(
				resp.getPrimaryAddress());
		
		
		AssociateAddress otherAddress = AssociateAddress.convertAssociateAddressResponseEvent(
														resp.getOtherAddress());
		
		this.setPrimaryResidenceAddress(primaryAddress);		
		this.setOtherAddress(otherAddress);
		
		IUserInfo userInfo = SecurityUIHelper.getUser();
		this.setUpdateJIMS2User(new Name(userInfo.getFirstName(), userInfo.getMiddleName(), userInfo.getLastName()));
		
		this.setUpdateDate(DateUtil.dateToString(resp.getUpdateDate(),UIConstants.DATE_FMT_1));
		
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @return Returns the cellPhone.
	 */
	public IPhoneNumber getCellPhone() {
		return cellPhone;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return Returns the homePhone.
	 */
	public IPhoneNumber getHomePhone() {
		return homePhone;
	}

	/**
	 * @return Returns the pager.
	 */
	public IPhoneNumber getPager() {
		return pager;
	}

	/**
	 * @return Returns the relationship.
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * @return Returns the relationshipId.
	 */
	public String getRelationshipId() {
		return relationshipId;
	}

	/**
	 * @return Returns the workPhone.
	 */
	public IPhoneNumber getWorkPhone() {
		return workPhone;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	
		/**
	 * @param cellPhone
	 *            The cellPhone to set.
	 */
	public void setCellPhone(IPhoneNumber cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @param email
	 *            The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param homePhone
	 *            The homePhone to set.
	 */
	public void setHomePhone(IPhoneNumber homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @param pager
	 *            The pager to set.
	 */
	public void setPager(IPhoneNumber pager) {
		this.pager = pager;
	}

	/**
	 * @param relationshipId
	 *            The relationshipId to set.
	 */
	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
		this.relationship = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSCD_RELATIONSHIP, this.relationshipId);
	}

	/**
	 * @param workPhone
	 *            The workPhone to set.
	 */
	public void setWorkPhone(IPhoneNumber workPhone) {
		this.workPhone = workPhone;
	}

	public static class AssociateAddress extends Address
	{
		Address address=new Address();
		String addressComplexName;
		boolean isPrimaryResidenceAddress;
		
		/**
		 * @return
		 */
		public Address getAddress()
		{
			return address;
		}

		public static AssociateAddress convertAssociateAddressResponseEvent(AssociateAddressResponseEvent responseEvent) {
			AssociateAddress address = new AssociateAddress();
			if (responseEvent == null)
				return address;
			
			address.setStreetNumber(responseEvent.getStreetNum());
			address.setStreetName(responseEvent.getStreetName());
			address.setStreetTypeId(responseEvent.getStreetTypeId());
			address.setAptNumber(responseEvent.getAptNum());
			address.setCity(responseEvent.getCity());
			address.setStateId(responseEvent.getStateId());
			address.setZipCode(responseEvent.getZipCode());
			address.setAdditionalZipCode(responseEvent.getAdditionalZipCode());
			address.setAddressComplexName(responseEvent.getComplexName());
			address.setAddressTypeId(responseEvent.getAddressTypeId());
			address.setCountyId(responseEvent.getCountyId());
			
			address.setIsPrimaryResidenceAddress(responseEvent.getIsPrimaryResidenceAddress());
			
			return address;
		} 
		
		/**
		 * @param address
		 */
		public void setAddress(Address address)
		{
			this.address = address;
		}	
		
		/**
		 * @param addressComplexName The addressComplexName to set.
		 */
		public void setAddressComplexName(String addressComplexName) {
			this.addressComplexName = addressComplexName;
		}
		/**
		 * @param isPrimaryResidenceAddress The isPrimaryResidenceAddress to set.
		 */
		public void setIsPrimaryResidenceAddress(boolean isPrimaryResidenceAddress) {
			this.isPrimaryResidenceAddress = isPrimaryResidenceAddress;
		}
		
		public void setStreetTypeId(String streetTypeId)
		{
		  super.setStreetTypeId(streetTypeId);
		  setStreetType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STREET_TYPE, getStreetTypeId()));
		}
		public void setStateId(String stateId)
		{
			super.setStateId(stateId);
			 setState(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR,getStateId()));
		}
		
		public void setAddressTypeId(String addressTypeId)
		{
		  super.setAddressTypeId(addressTypeId);
		  setAddressType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ADDRESS_TYPE, getAddressTypeId()));
		}
		
		public void setCountyId(String countyId)
		{
		  super.setCountyId(countyId);
		  setCounty(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.COUNTY, getCountyId()));
		}
		/**
		 * @return Returns the addressComplexName.
		 */
		public String getAddressComplexName() {
			return addressComplexName;
		}
		/**
		 * @return Returns the isPrimaryResidenceAddress.
		 */
		public boolean getIsPrimaryResidenceAddress() {
			return isPrimaryResidenceAddress;
		}
		
	}
	/**
	 * @return Returns the otherAddress.
	 */
	public AssociateAddress getOtherAddress() {
		return otherAddress;
	}
	/**
	 * @return Returns the primaryResidenceAddress.
	 */
	public AssociateAddress getPrimaryResidenceAddress() {
		return primaryResidenceAddress;
	}
	/**
	 * @param otherAddress The otherAddress to set.
	 */
	public void setOtherAddress(AssociateAddress otherAddress) {
		this.otherAddress = otherAddress;
	}
	/**
	 * @param primaryResidenceAddress The primaryResidenceAddress to set.
	 */
	public void setPrimaryResidenceAddress(AssociateAddress primaryResidenceAddress) {
		this.primaryResidenceAddress = primaryResidenceAddress;
	}
	/**
	 * @return Returns the associateName.
	 */
	public Name getAssociateName() {
		return associateName;
	}
	/**
	 * @param associateName The associateName to set.
	 */
	public void setAssociateName(Name associateName) {
		this.associateName = associateName;
	}
	/**
	 * @return Returns the status.
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
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
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the fromPath.
	 */
	public String getFromPath() {
		return fromPath;
	}
	/**
	 * @param fromPath The fromPath to set.
	 */
	public void setFromPath(String fromPath) {
		this.fromPath = fromPath;
	}
	/**
	 * @return Returns the associatesList.
	 */
	public Collection getAssociatesList() {
		return associatesList;
	}
	/**
	 * @param associatesList The associatesList to set.
	 */
	public void setAssociatesList(Collection associatesList) {
		this.associatesList = associatesList;
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
	 * @return Returns the updateDate.
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate The updateDate to set.
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return Returns the updateJIMS2User.
	 */
	public IName getUpdateJIMS2User() {
		return updateJIMS2User;
	}
	/**
	 * @param updateJIMS2User The updateJIMS2User to set.
	 */
	public void setUpdateJIMS2User(IName updateJIMS2User) {
		this.updateJIMS2User = updateJIMS2User;
	}
}
