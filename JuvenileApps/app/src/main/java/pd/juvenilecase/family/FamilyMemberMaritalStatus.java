package pd.juvenilecase.family;

import java.util.Date;
import java.util.Iterator;

import pd.juvenilecase.family.*;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class FamilyMemberMaritalStatus extends mojo.km.persistence.PersistentObject {
	private String noOfChildren;
	private Date marriageDate;
	private Date divorceDate;
	/**
	* Properties for FamilyMember
	* @referencedType pd.juvenilecase.family.FamilyMember
	* @detailerDoNotGenerate false
	*/
	public FamilyMember theFamilyMember = null;
	private Date entryDate;
	private String theFamilyMemberId;
	private String maritalStatusId;
	private String theRelatedFamMemId;
	public FamilyMember theRelatedFamMem = null;
	/**
	* @return 
	*/
	public Date getDivorceDate() {
		fetch();
		return divorceDate;
	}
	/**
	* @return 
	*/
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}
	/**
	* @return 
	*/
	public String getMaritalStatusId() {
		fetch();
		return maritalStatusId;
	}
	/**
	* @return 
	*/
	public Date getMarriageDate() {
		fetch();
		return marriageDate;
	}
	/**
	* @return 
	*/
	public String getNoOfChildren() {
		fetch();
		return noOfChildren;
	}
	/**
	* @param date
	*/
	public void setDivorceDate(Date date) {
		if (this.divorceDate == null || !this.divorceDate.equals(date)) {
			markModified();
		}
		divorceDate = date;
	}
	/**
	* @param date
	*/
	public void setEntryDate(Date date) {
		if (this.entryDate == null || !this.entryDate.equals(date)) {
			markModified();
		}
		entryDate = date;
	}
	/**
	* @param string
	*/
	public void setMaritalStatusId(String string) {
		if (this.maritalStatusId == null || !this.maritalStatusId.equals(string)) {
			markModified();
		}
		maritalStatusId = string;
	}
	/**
	* @param date
	*/
	public void setMarriageDate(Date date) {
		if (this.marriageDate == null || !this.marriageDate.equals(date)) {
			markModified();
		}
		marriageDate = date;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public void setTheFamilyMemberId(String theFamilyMemberId) {
		if (this.theFamilyMemberId == null || !this.theFamilyMemberId.equals(theFamilyMemberId)) {
			markModified();
		}
		theFamilyMember = null;
		this.theFamilyMemberId = theFamilyMemberId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public String getTheFamilyMemberId() {
		fetch();
		return theFamilyMemberId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.family.FamilyMember
	*/
	private void initTheFamilyMember() {
		if (theFamilyMember == null) {
			theFamilyMember = (FamilyMember) new mojo.km.persistence.Reference(theFamilyMemberId, FamilyMember.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.family.FamilyMember
	*/
	public FamilyMember getTheFamilyMember() {
		fetch();
		initTheFamilyMember();
		return theFamilyMember;
	}
	/**
	* set the type reference for class member theFamilyMember
	*/
	public void setTheFamilyMember(FamilyMember theFamilyMember) {
		if (this.theFamilyMember == null || !this.theFamilyMember.equals(theFamilyMember)) {
			markModified();
		}
		if (theFamilyMember.getOID() == null) {
			new mojo.km.persistence.Home().bind(theFamilyMember);
		}
		setTheFamilyMemberId("" + theFamilyMember.getOID());
		this.theFamilyMember = (FamilyMember) new mojo.km.persistence.Reference(theFamilyMember).getObject();
	}
	/**
	* Finds all marital status by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator maritalStatus = home.findAll(attributeName, attributeValue, FamilyMemberMaritalStatus.class);
		return maritalStatus;
	}
	/**
	* @param string
	*/
	public void setNoOfChildren(String string) {
		if (this.noOfChildren == null || !this.noOfChildren.equals(string)) {
			markModified();
		}
		noOfChildren = string;
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public void setTheRelatedFamMemId(String theRelatedFamMemId) {
		if (this.theRelatedFamMemId == null || !this.theRelatedFamMemId.equals(theRelatedFamMemId)) {
			markModified();
		}
		theRelatedFamMem = null;
		this.theRelatedFamMemId = theRelatedFamMemId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public String getTheRelatedFamMemId() {
		fetch();
		return theRelatedFamMemId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.family.FamilyMember
	*/
	private void initTheRelatedFamMem() {
		if (theRelatedFamMem == null) {
			if(theRelatedFamMemId!=null && !theRelatedFamMemId.equals("")){
				theRelatedFamMem = (FamilyMember) new mojo.km.persistence.Reference(theRelatedFamMemId, FamilyMember.class).getObject();
			}
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.family.FamilyMember
	*/
	public FamilyMember getTheRelatedFamMem() {
		fetch();
		initTheRelatedFamMem();
		return theRelatedFamMem;
	}
	/**
	* set the type reference for class member theFamilyMember
	*/
	public void setTheRelatedFamMem(FamilyMember theRelatedFamMem) {
		if (this.theRelatedFamMem == null || !this.theRelatedFamMem.equals(theRelatedFamMem)) {
			markModified();
		}
		if (theRelatedFamMem.getOID() == null) {
			new mojo.km.persistence.Home().bind(theRelatedFamMem);
		}
		setTheRelatedFamMemId("" + theRelatedFamMem.getOID());
		this.theRelatedFamMem = (FamilyMember) new mojo.km.persistence.Reference(theRelatedFamMem).getObject();
	}
}
