package pd.codetable.criminal;

import java.util.Iterator;
import pd.codetable.ICodetable;
import messaging.codetable.GetServiceTypeCdEvent;
import messaging.codetable.GetServiceTypeCdByGroupEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 44806B1001E9
*/
public class JuvenileEventTypeCode extends PersistentObject implements ICodetable {
	
	private String agencyCodeId;
	private String agencyId;
	private String code;
	private String codeTableName;
	private String description;
	
	private String group;
	private String status;
	/**
	* @roseuid 44806B1001E9
	*/
	public JuvenileEventTypeCode() {
	}
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#findAll()
	 */
	public Iterator findAll() {
		return new Home().findAll(JuvenileEventTypeCode.class);
	}
	/**
	 * @return Returns the agencyCodeId.
	 */
	public String getAgencyCodeId() {
		fetch();
		return this.getOID().toString();
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Access method for the code property.
	* @return the current value of the code property
	*/
	public String getCode() {
		fetch();
		return code;
	}

	public String getCodeTableName() {
		fetch();
		return codeTableName;
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
	* Access method for the group property.
	* @return the current value of the group property
	*/
	public String getGroup() {
		fetch();
		return group;
	}
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
	}	
	/**
	 * @param agencyCodeId The agencyCodeId to set.
	 */
	public void setAgencyCodeId(String agencyCodeId) {
		if (this.agencyCodeId == null || !this.agencyCodeId.equals(agencyCodeId)) {
			markModified();
		}
		this.setOID(agencyCodeId);
		this.agencyCodeId = agencyCodeId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		if (this.agencyId == null || !this.agencyId.equals(agencyId)) {
			markModified();
		}
		this.agencyId = agencyId;
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

	public void setCodeTableName(String aCodeTableName) {
		if (this.codeTableName == null || !this.codeTableName.equals(aCodeTableName)) {
			markModified();
		}
		codeTableName = aCodeTableName;
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
	* Sets the value of the group property.
	* @param aGroup the new value of the group property
	*/
	public void setGroup(String aGroup) {
		if (this.group == null || !this.group.equals(aGroup)) {
			markModified();
		}
		group = aGroup;
	}

	static public JuvenileEventTypeCode find(String agencyCodeId)
	{
		return (JuvenileEventTypeCode) new Home().find(agencyCodeId, JuvenileEventTypeCode.class);
	}	

	/**
	   * Finds all JuvenileEventTypeCode by an attribute value
	   * @param attributeName
	   * @param attributeValue
	   * @return 
	   */
	   static public Iterator findAll(String attributeName, String attributeValue) {
		   IHome home = new Home();
		   return home.findAll(attributeName, attributeValue, JuvenileEventTypeCode.class);
	   }
	
	static public Iterator findServiceTypeCodes(GetServiceTypeCdEvent event)
	{
		return new Home().findAll(event, JuvenileEventTypeCode.class);
	}	
	
	static public Iterator findServiceTypeCodesByGroup(GetServiceTypeCdByGroupEvent event)
	{
		return new Home().findAll(event, JuvenileEventTypeCode.class);
	}
	
	public String getStatus() {
		fetch();
		return status;
	}
	
	public void setStatus(String status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		this.status = status;
	}	
	
}
