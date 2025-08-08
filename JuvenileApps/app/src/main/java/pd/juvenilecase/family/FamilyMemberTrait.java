package pd.juvenilecase.family;

import java.util.Iterator;


import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;
import pd.juvenilecase.family.*;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class FamilyMemberTrait extends mojo.km.persistence.PersistentObject {
	private String comments;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey FAMILY_TRAIT_LEVEL
	*/
	private Code level = null;
	private String typeId;
	private String levelId;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey MEMBER_TRAIT_TYPE
	*/
	private Code type = null;
	private String familyMemberId;
	/**
	* Properties for familyMember
	* @referencedType pd.juvenilecase.family.FamilyMember
	* @detailerDoNotGenerate false
	*/
	private FamilyMember familyMember = null;
	private String statusId;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey FAMILY_TRAIT_STATUS
	*/
	private Code status = null;
	/**
	* @return 
	*/
	public String getComments() {
		fetch();
		return comments;
	}
	/**
	* @param string
	*/
	public void setComments(String string) {
		if (this.comments == null || !this.comments.equals(string)) {
			markModified();
		}
		comments = string;
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setLevelId(String levelId) {
		if (this.levelId == null || !this.levelId.equals(levelId)) {
			markModified();
		}
		level = null;
		this.levelId = levelId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getLevelId() {
		fetch();
		return levelId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLevel() {
		if (level == null) {
			level = (Code) new mojo.km.persistence.Reference(levelId, Code.class, "FAMILY_TRAIT_LEVEL").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getLevel() {
		fetch();
		initLevel();
		return level;
	}
	/**
	* set the type reference for class member level
	*/
	public void setLevel(Code level) {
		if (this.level == null || !this.level.equals(level)) {
			markModified();
		}
		if (level.getOID() == null) {
			new mojo.km.persistence.Home().bind(level);
		}
		setLevelId("" + level.getOID());
		level.setContext("FAMILY_TRAIT_LEVEL");
		this.level = (Code) new mojo.km.persistence.Reference(level).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setTypeId(String typeId) {
		if (this.typeId == null || !this.typeId.equals(typeId)) {
			markModified();
		}
		type = null;
		this.typeId = typeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getTypeId() {
		fetch();
		return typeId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initType() {
		if (type == null) {
			type = (Code) new mojo.km.persistence.Reference(typeId, Code.class, "MEMBER_TRAIT_TYPE").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getType() {
		fetch();
		initType();
		return type;
	}
	/**
	* set the type reference for class member type
	*/
	public void setType(Code type) {
		if (this.type == null || !this.type.equals(type)) {
			markModified();
		}
		if (type.getOID() == null) {
			new mojo.km.persistence.Home().bind(type);
		}
		setTypeId("" + type.getOID());
		type.setContext("MEMBER_TRAIT_TYPE");
		this.type = (Code) new mojo.km.persistence.Reference(type).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId)) {
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId() {
		fetch();
		return statusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "FAMILY_TRAIT_STATUS").getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus() {
		fetch();
		initStatus();
		return status;
	}
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		if (status.getOID() == null) {
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		status.setContext("FAMILY_TRAIT_STATUS");
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public void setFamilyMemberId(String familyMemberId) {
		if (this.familyMemberId == null || !this.familyMemberId.equals(familyMemberId)) {
			markModified();
		}
		familyMember = null;
		this.familyMemberId = familyMemberId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	*/
	public String getFamilyMemberId() {
		fetch();
		return familyMemberId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.family.FamilyMember
	*/
	private void initFamilyMember() {
		if (familyMember == null) {
			familyMember = (FamilyMember) new mojo.km.persistence.Reference(familyMemberId, FamilyMember.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.family.FamilyMember
	*/
	public FamilyMember getFamilyMember() {
		initFamilyMember();
		return familyMember;
	}
	/**
	* set the type reference for class member familyMember
	*/
	public void setFamilyMember(FamilyMember familyMember) {
		if (this.familyMember == null || !this.familyMember.equals(familyMember)) {
			markModified();
		}
		if (familyMember.getOID() == null) {
			new mojo.km.persistence.Home().bind(familyMember);
		}
		setFamilyMemberId("" + familyMember.getOID());
		this.familyMember = (FamilyMember) new mojo.km.persistence.Reference(familyMember).getObject();
	}

	/**
	* Finds all traits by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator traits = home.findAll(attributeName, attributeValue, FamilyMemberTrait.class);
		return traits;
	}	
}
