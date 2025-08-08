package pd.codetable.supervision;

import java.util.Iterator;

import pd.codetable.ICodetable;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 43039DC001D4
*/
public class SupervisionCode extends PersistentObject implements ICodetable{
	private String codeTableName;
	private String agencyId;
	private String code;
	private String description;
	private String codeId;
	/**
	* @return Iterator SupervisionCode
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, SupervisionCode.class);
	}
	
	/**
	* @return Iterator SupervisionCode
	* @param event
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, attrValue, SupervisionCode.class);
	}
	
	/**
	* @param codeId
	* @roseuid 42F79A3902DE
	*/
	static public SupervisionCode find(String codeId) {
		IHome home = new Home();
		return (SupervisionCode) home.find(codeId, SupervisionCode.class);
	}
	/**
	* @roseuid 43039DC001D4
	*/
	public SupervisionCode() {
	}
	/**
	* Access method for the code property.
	* @return the current value of the code property
	*/
	public String getCode() {
		fetch();
		return code;
	}
	/**
	* Sets the value of the code property.
	* @param aCode the new value of the code property
	*/
	public void setCode(String aCode) {
		if (this.code == null || !this.code.equals(aCode)) {
			markModified();
		}
		code = aCode;
	}
	/**
	* Access method for the codeId property.
	* @return the current value of the codeId property
	*/
	public String getCodeId() {
		fetch();
		return codeId;
	}
	/**
	* Sets the value of the codeId property.
	* @param aCodeId the new value of the codeId property
	*/
	public void setCodeId(String aCodeId) {
		if (this.codeId == null || !this.codeId.equals(aCodeId)) {
			markModified();
		}
		codeId = aCodeId;
	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription() {
		fetch();
		return description;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}
	/**
	* Access method for the agencyId property.
	* @return the current value of the agencyId property
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Sets the value of the agencyId property.
	* @param aAgencyId the new value of the agencyId property
	*/
	public void setAgencyId(String aAgencyId) {
		if (this.agencyId == null || !this.agencyId.equals(aAgencyId)) {
			markModified();
		}
		agencyId = aAgencyId;
	}
	/**
	* @roseuid 43039B2701A5
	*/
	public void find() {
		fetch();
	}
	/**
	* @return 
	*/
	public String getCodeTableName() {
		fetch();
		return codeTableName;
	}
	/**
	* @param string
	*/
	public void setCodeTableName(String string) {
		if (this.codeTableName == null || !this.codeTableName.equals(string)) {
			markModified();
		}
		codeTableName = string;
	}
	
	public String toString(){
	    return getOID().toString();
	}
	public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(SupervisionCode.class);
		
	}
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
}
