package pd.contact.agency;

import pd.codetable.Code;
import pd.contact.Contact;

/**
* Properties for contactType
* @referencedType pd.codetable.Code
* @contextKey CONTACT_TYPE
* @detailerDoNotGenerate false
*/
public class DepartmentContact extends Contact {
	private String liaisonTrainingInd;
	/**
	* Properties for contactType
	* @referencedType pd.codetable.Code
	* @contextKey CONTACT_TYPE
	* @detailerDoNotGenerate false
	*/
	private Code contactType = null;
	private String departmentId;
	private String contactTypeId;
	private String primaryContact;
	private String logonId;
	/**
	* @roseuid 430E201C0224
	*/
	public DepartmentContact() {
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getContactType() {
		//fetch(); //87191
		initContactType();
		return contactType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getContactTypeId() {
		//fetch();
		return contactTypeId;
	}
	/**
	* Access method for the departmentId property.
	* @return the current value of the departmentId property
	*/
	public String getDepartmentId() {
		//fetch();
		return departmentId;
	}
	/**
	* Access method for the liaisonTrainingInd property.
	* @return the current value of the liaisonTrainingInd property
	*/
	public String getLiaisonTrainingInd() {
		//fetch();
		return liaisonTrainingInd;
	}
	/**
	* Access method for the logonId property.
	* @return the current value of the logonId property
	*/
	public String getLogonId() {
		//fetch();
		return logonId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initContactType() {
		if (contactType == null) {
			contactType = (Code) new mojo.km.persistence.Reference(contactTypeId, Code.class, "CONTACT.TYPE").getObject();
		}
	}
	/**
	* set the type reference for class member contactType
	*/
	public void setContactType(Code contactType) {
		if (this.contactType == null || !this.contactType.equals(contactType)) {
			//markModified();
		}
		if (contactType.getOID() == null) {
			new mojo.km.persistence.Home().bind(contactType);
		}
		setContactTypeId("" + contactType.getOID());
		contactType.setContext("CONTACT.TYPE");
		this.contactType = (Code) new mojo.km.persistence.Reference(contactType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setContactTypeId(String contactTypeId) {
		/*if (this.contactTypeId == null || !this.contactTypeId.equals(contactTypeId)) {
			//markModified();
		}*/
		contactType = null;
		this.contactTypeId = contactTypeId;
	}
	/**
	* Sets the value of the departmentId property.
	* @param aDepartmentId the new value of the departmentId property
	*/
	public void setDepartmentId(String aDepartmentId) {
		/*if (this.departmentId == null || !this.departmentId.equals(aDepartmentId)) {
			//markModified();
		}*/
		departmentId = aDepartmentId;
	}
	/**
	* Sets the value of the liaisonTrainingInd property.
	* @param aLiaisonTrainingInd the new value of the liaisonTrainingInd property
	*/
	public void setLiaisonTrainingInd(String aLiaisonTrainingInd) {
		/*if (this.liaisonTrainingInd == null || !this.liaisonTrainingInd.equals(aLiaisonTrainingInd)) {
			//markModified();
		}*/
		liaisonTrainingInd = aLiaisonTrainingInd;
	}
	/**
	* Sets the value of the logonId property.
	* @param aLogonId the new value of the logonId property
	*/
	public void setLogonId(String aLogonId) {
		/*if (this.logonId == null || !this.logonId.equals(aLogonId)) {
			//markModified();
		}*/
		logonId = aLogonId;
	}
	/**
	* @return DepartmentContact
	* @param oid
	*/
	
	//referred in three places. WIll not be called./87191
	static public DepartmentContact find(String oid) {
		DepartmentContact contact = null;
		//IHome home = new Home();
		//contact = (DepartmentContact) home.find(oid, DepartmentContact.class);
		
		return contact;
	}
	/**
	* @param contactRequestEvent
	*/
/*	public void setDepartmentContact(UpdateContactEvent contactRequestEvent) {
	    Department dept = DepartmentHelper.getDepartmentFromSecurityManager(departmentId);
	    if(dept!=null){
		this.setLastName(contactRequestEvent.getLastName());
		this.setFirstName(contactRequestEvent.getFirstName());
		this.setMiddleName(contactRequestEvent.getMiddleName());
		this.setTitle(contactRequestEvent.getTitle());
		this.setLogonId(contactRequestEvent.getLogonId());
		this.setPhoneNum(contactRequestEvent.getPhone());
		this.setPhoneExt(contactRequestEvent.getPhoneExt());
		this.setEmail(contactRequestEvent.getEmail());
		this.setLiaisonTrainingInd(contactRequestEvent.getLiaisonTrainingInd());
		this.setContactId(contactRequestEvent.getContactId());
		this.setContactTypeId(contactRequestEvent.getContactTypeId());
		this.setDepartmentId(contactRequestEvent.getDepartmentId());
		this.setPrimaryContact(contactRequestEvent.getPrimaryContact());
	    }
	}*/
	/**
	* @return 
	*/
	public String getPrimaryContact() {
		//fetch();
		return primaryContact;
	}
	/**
	* @param string
	*/
	public void setPrimaryContact(String primaryContact) {
		/*if (this.primaryContact == null || !this.primaryContact.equals(primaryContact)) {
			//markModified();
		}*/
		this.primaryContact = primaryContact;
	}
}
