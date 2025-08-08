package pd.codetable.criminal;

import java.util.Iterator;
import pd.codetable.ICodetable;
import messaging.codetable.GetJuvenileAdmissionTypeCdEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * Properties for type
 * @referencedType pd.codetable.Code
 * @detailerDoNotGenerate false
 * @contextKey ACTIVITY_TYPE
 */
public class JuvenileHospitalizationAdmissionTypeCode extends PersistentObject implements ICodetable
{
	
	private String categoryId;
	private String code;
	private String description;
	private String hospitalAdmissionTypeId;

	/**
	 * 
	 * @roseuid 45771CA9003F
	 */
	public JuvenileHospitalizationAdmissionTypeCode()
	{
	}

	/**
	 * 
	 * @roseuid 45771BE501A2
	 */
	public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileHospitalizationAdmissionTypeCode.class);
	}

	/**
	 * 
	 * @return Returns the categoryId.
	 */
	public String getCategoryId()
	{
		fetch();
		return categoryId;
	}

	/**
	 * 
	 * @return Returns the code.
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	 * 
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}
	/**
	 * @return Returns the hospitalAdmissionTypeId.
	 */
	public String getHospitalAdmissionTypeId() {
		fetch();
		return this.getOID().toString();
	}

	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId)
	{
		if (this.categoryId == null || !this.categoryId.equals(categoryId))
		{
			markModified();
		}
		this.categoryId = categoryId;
	}

	/**
	 * 
	 * @param code The code to set.
	 */
	public void setCode(String code)
	{
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}

	/**
	 * 
	 * @param description The description to set.
	 */
	public void setDescription(String description)
	{
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}
	/**
	 * @param hospitalAdmissionTypeId The hospitalAdmissionTypeId to set.
	 */
	public void setHospitalAdmissionTypeId(String hospitalAdmissionTypeId) {
		if (this.hospitalAdmissionTypeId == null || !this.hospitalAdmissionTypeId.equals(hospitalAdmissionTypeId))
		{
			markModified();
		}
		this.setOID(hospitalAdmissionTypeId);
		this.hospitalAdmissionTypeId = hospitalAdmissionTypeId;
	}
	/*
	 * @param hospitalAdmissionTypeId
	 * @return JuvenileHospitalizationAdmissionTypeCode
	 */
	static public JuvenileHospitalizationAdmissionTypeCode find(String hospitalAdmissionTypeId)
	{
		return (JuvenileHospitalizationAdmissionTypeCode) new Home().find(hospitalAdmissionTypeId,JuvenileHospitalizationAdmissionTypeCode.class);
	}
	
	/**
	 * @param event
	 * @return
	 */
	static public Iterator findAdmissionTypeCodes(GetJuvenileAdmissionTypeCdEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileHospitalizationAdmissionTypeCode.class);
	}
	
	/**
	* Finds all JuvenileHospitalizationAdmissionTypeCode by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, JuvenileHospitalizationAdmissionTypeCode.class);
	}	
}
