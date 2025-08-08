package pd.supervision.administerserviceprovider.administerlocation;

import java.util.Iterator;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.ICodetable;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

public class SupervisionTypeCode extends PersistentObject implements ICodetable, ICode{

	private String code;
	private String description;
	private String status;
	private Code specialityCategory = null;
	private JuvLocationUnit juvLocUnit = null;
	private String juvLocUnitId; 
	private String splCategoryId;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @roseuid 45771CA9003F
	 */
	public SupervisionTypeCode()
	{
	}


	@Override
	public Iterator findAll() {
		return new Home().findAll(SupervisionTypeCode.class);
	}

	/*
	 * @param supervisionTypeId
	 * @return JuvenileActivityTypeCode
	 */
	static public SupervisionTypeCode find(String supervisionTypeId)
	{
		return (SupervisionTypeCode) new Home().find(supervisionTypeId,SupervisionTypeCode.class);
	}
	
	/*
	 * @param event
	 * @return Iterator
	 */
	public static Iterator findAll(IEvent event)
	{
		return new Home().findAll(event, SupervisionTypeCode.class);
	}
	
	
	@Override
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}


	public String getCode() {
		fetch();
		return code;
	}


	public String getDescription() {
		fetch();
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		fetch();
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
		this.status = status;
	}

	/**
	* Finds all SupervisionTypeCode by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, SupervisionTypeCode.class);
	}
	
	/**
	 * set the type reference for class member juvLocUnit
	 */
	public void setJuvLocUnit(JuvLocationUnit juvLocUnit)
	{
		if (this.juvLocUnit == null || !this.juvLocUnit.equals(juvLocUnit))
		{
			markModified();
		}
		if (juvLocUnit.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(juvLocUnit);
		}
		setJuvLocUnitId("" + juvLocUnit.getOID());
		this.juvLocUnit = (JuvLocationUnit) new mojo.km.persistence.Reference(
				juvLocUnit).getObject();
	}

	
	/**
	 * Gets referenced type pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	public JuvLocationUnit getJuvLocUnit()
	{
		initJuvLocUnit();
		return juvLocUnit;
	}
	
	/**
	 * Initialize class relationship to class pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	private void initJuvLocUnit()
	{
		if (juvLocUnit == null)
		{
			juvLocUnit = (JuvLocationUnit) new mojo.km.persistence.Reference(
					juvLocUnitId, JuvLocationUnit.class)
					.getObject();
		}
	}


	/**
	 * Set the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	public void setJuvLocUnitId(String juvLocUnitId)
	{
		if (this.juvLocUnitId == null || !this.juvLocUnitId.equals(juvLocUnitId))
		{
			markModified();
		}
		juvLocUnit = null;
		this.juvLocUnitId = juvLocUnitId;
	}
	
	/**
	 * Get the reference value to class :: pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit
	 */
	public String getJuvLocUnitId()
	{
		fetch();
		return juvLocUnitId;
	}
	
	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSpecialityCategory()
	{
		if (specialityCategory == null)
		{
			specialityCategory = (Code) new mojo.km.persistence.Reference(splCategoryId, Code.class,"SPECIALITY_CATEGORY").getObject();
		}
	}
	
	/**
	 * @return the specialityCategory
	 */
	public Code getSpecialityCategory() {
		initSpecialityCategory();
		return specialityCategory;
	}
	
	/**
	 * @param specialityCategory the specialityCategory to set
	 */
	public void setSpecialityCategory(Code specialityCategory) {
		if (this.specialityCategory == null || !this.specialityCategory.equals(specialityCategory))
		{
			markModified();
		}
		if (specialityCategory.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(specialityCategory);
		}
		setSplCategoryId("" + specialityCategory.getOID());
		specialityCategory.setContext("SPECIALITY_CATEGORY");
		this.specialityCategory = (Code) new mojo.km.persistence.Reference(specialityCategory).getObject();
	}
	
	
	
	/**
	 * @return the specialityCategoryId
	 */
	public String getSplCategoryId() {
		fetch();
		return splCategoryId;
	}

	/**
	 * @param specialityCategoryId the specialityCategoryId to set
	 */
	public void setSplCategoryId(String splCategoryId) {
		if (this.splCategoryId == null || !this.splCategoryId.equals(splCategoryId))
		{
			markModified();
		}
		specialityCategory = null;
		this.splCategoryId = splCategoryId;
	}
}
