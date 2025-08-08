//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageassociate\\SaveAssociateEvent.java

package messaging.manageassociate;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import mojo.km.messaging.Composite.CompositeRequest;

public class CreateUpdateSuperviseeAssociateEvent extends CompositeRequest 
{
   private String associateId;
   private IName associateName;
   private String relationshipId;
   private String comments;
   private IPhoneNumber homePhone;
   private IPhoneNumber workPhone;
   private IPhoneNumber cellPhone;
   private IPhoneNumber pager;
   private String email;
   private boolean status;
   private String spn;
   private String primaryResidenceAddressId;
   private String otherAddressId;
   
   private boolean isCreate;
   private boolean isUpdate;
   private boolean isDelete;
   
   /**
    * @roseuid 45E5E812002E
    */
   public CreateUpdateSuperviseeAssociateEvent() 
   {
    
   }
	/**
	 * @return Returns the associateId.
	 */
	public String getAssociateId() {
		return associateId;
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
	 * @return Returns the relationshipId.
	 */
	public String getRelationshipId() {
		return relationshipId;
	}
	/**
	 * @return Returns the status.
	 */
	public boolean getStatus() {
		return status;
	}
	/**
	 * @return Returns the workphone.
	 */
	public IPhoneNumber getWorkPhone() {
		return workPhone;
	}
	/**
	 * @param associateId The associateId to set.
	 */
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	/**
	 * @param cellPhone The cellPhone to set.
	 */
	public void setCellPhone(IPhoneNumber cellPhone) {
		this.cellPhone = cellPhone;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param homePhone The homePhone to set.
	 */
	public void setHomePhone(IPhoneNumber homePhone) {
		this.homePhone = homePhone;
	}
	/**
	 * @param pager The pager to set.
	 */
	public void setPager(IPhoneNumber pager) {
		this.pager = pager;
	}
	/**
	 * @param relationshipId The relationshipId to set.
	 */
	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @param workphone The workphone to set.
	 */
	public void setWorkPhone(IPhoneNumber workPhone) {
		this.workPhone = workPhone;
	}
		
	/**
	 * @return Returns the associateName.
	 */
	public IName getAssociateName() {
		return associateName;
	}
	/**
	 * @param associateName The associateName to set.
	 */
	public void setAssociateName(IName associateName) {
		this.associateName = associateName;
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
	 * @return Returns the isCreate.
	 */
	public boolean isCreate() {
		return isCreate;
	}
	/**
	 * @param isCreate The isCreate to set.
	 */
	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}
	/**
	 * @return Returns the isUpdate.
	 */
	public boolean isUpdate() {
		return isUpdate;
	}
	/**
	 * @param isUpdate The isUpdate to set.
	 */
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	/**
	 * @return Returns the isDelete.
	 */
	public boolean isDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete The isDelete to set.
	 */
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}
