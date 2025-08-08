package pd.juvenilecase.family;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.Code;
import java.util.Date;
import java.util.Iterator;

/**
* @roseuid 431F3ED10280
* @author Anand Thorat
* @version 1.0.0
*/
public class FamilyTrait extends mojo.km.persistence.PersistentObject {
	/**
	* Properties for dynamic
	*/
	private Code dynamic = null;
	/**
	* Properties for level
	*/
	private Code level = null;
	private String levelId;
	private String dynamicId;
	private String notes;
	private String familyConstellationId;
	private Date entryDate;
	/**
	* Properties for status
	*/
	private Code status = null;
	private String statusId;
	/**
	* @roseuid 431F3ED10280
	*/
	public FamilyTrait() {
	}

	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyConstellation
	* @param familyConstellationId The family constellation id.
	*/
	public void setFamilyConstellationId(String familyConstellationId) {
		if (this.familyConstellationId == null || !this.familyConstellationId.equals(familyConstellationId)) {
			markModified();
		}
		this.familyConstellationId = familyConstellationId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyConstellation
	* @return The family constellation id.
	*/
	public String getFamilyConstellationId() {
		fetch();
		return familyConstellationId;
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
			level = (Code) new mojo.km.persistence.Reference(levelId, Code.class).getObject();
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
		setLevelId("" + level.getOID());
		this.level = (Code) new mojo.km.persistence.Reference(level).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setDynamicId(String dynamicId) {
		if (this.dynamicId == null || !this.dynamicId.equals(dynamicId)) {
			markModified();
		}
		dynamic = null;
		this.dynamicId = dynamicId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getDynamicId() {
		fetch();
		return dynamicId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initDynamic() {
		if (dynamic == null) {
			dynamic = (Code) new mojo.km.persistence.Reference(dynamicId, Code.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getDynamic() {
		fetch();
		initDynamic();
		return dynamic;
	}
	/**
	* set the type reference for class member dynamic
	*/
	public void setDynamic(Code dynamic) {
		if (this.dynamic == null || !this.dynamic.equals(dynamic)) {
			markModified();
		}
		setDynamicId("" + dynamic.getOID());
		this.dynamic = (Code) new mojo.km.persistence.Reference(dynamic).getObject();
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
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class).getObject();
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
		setStatusId("" + status.getOID());
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Finds all family traits by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator familyConstellationMembers = home.findAll(attributeName, attributeValue, FamilyTrait.class);
		return familyConstellationMembers;
	}
	/**
	* @roseuid 431F18020070
	* @param traitNum The traitNum num.
	*/
	static public FamilyTrait find(String traitNum) {
		IHome home = new Home();
		FamilyTrait trait = (FamilyTrait) home.find(traitNum, FamilyTrait.class);
		return trait;
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
	public String getNotes() {
		fetch();
		return notes;
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
	public void setNotes(String string) {
		if (this.notes == null || !this.notes.equals(string)) {
			markModified();
		}
		notes = string;
	}
}
