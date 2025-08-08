
package pd.juvenilecase;

import java.util.Iterator;

import messaging.juvenilecase.GetSupervisionTypeEvent; 
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
* @referencedType pd.codetable.person.JuvenileLevelUnitCode
* @detailerDoNotGenerate false
*/
public class SupervisionTypeTJJDMap extends PersistentObject {
	
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionTypeTJJDMap.class);
	}
	
	
	/**
	* @referencedType pd.codetable.Code
	* @contextKey SUPERVISION_TYPE
	* @detailerDoNotGenerate false
	*/
	private Code supervisionType = null;
	private String supervisionTypeId;
	private String supervisionStatus;
	private Code supTJJDType = null; 
	private String supTJJDTypeId;
	private Code specialityCategory = null;
	private JuvLocationUnit juvLocUnit = null;
	private String juvLocUnitId; 
	private String splCategoryId;
	private String tjjdIndicator;
	
	/**
	* @roseuid 42768538032C
	*/
	public SupervisionTypeTJJDMap() {
	}
	
	static public SupervisionTypeTJJDMap find(String oid) {
		IHome home = new Home();
		SupervisionTypeTJJDMap supervisionType = (SupervisionTypeTJJDMap) home.find(oid, SupervisionTypeTJJDMap.class);
		return supervisionType;
	}
	
	static public Iterator findAll() {
		return new Home().findAll(SupervisionTypeTJJDMap.class);
	}
	
	
	
	/**
	* Access method for the supervisionType property.
	* @return the current value of the supervisionType property
	*/
	public Code getSupervisionType() {
		fetch();
		initSupervisionType();
		return supervisionType;
	}

	/**
	* Access method for the supervisionType property.
	* @return the current value of the supervisionType property
	*/
	public Code getSupTJJDType() {
		fetch();
		initSupTJJDType();
		return supTJJDType;
	}
	
	
	/**
	* Access method for the supervisionStatus property.
	* @return the current value of the supervisionStatus property
	*/
	public String getSupervisionStatus() {
		fetch();		
		return supervisionStatus;
	}
	
	
	/**
	* @roseuid 427676D702F0
	*/
	public void find() {
		fetch();
	}

	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setSupervisionTypeId(String aSupervisionTypeId) {
		if (this.supervisionTypeId == null || !this.supervisionTypeId.equals(aSupervisionTypeId)) {
			markModified();
		}
		supervisionType = null;
		this.supervisionTypeId = aSupervisionTypeId;
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setSupTJJDTypeId(String aSupTJJDTypeId) {
		if (this.supTJJDTypeId == null || !this.supTJJDTypeId.equals(aSupTJJDTypeId)) {
			markModified();
		}
		supTJJDType = null;
		this.supTJJDTypeId = aSupTJJDTypeId;
	}
	
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSupervisionTypeId() {
		fetch();
		return this.supervisionTypeId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSupTJJDTypeId() {
		fetch();
		return this.supTJJDTypeId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSupervisionType() {
		if (supervisionType == null) {
			try {
				supervisionType = (Code) new mojo.km.persistence.Reference(supervisionTypeId, Code.class, "SUPERVISION_TYPE").getObject();
			} catch (Throwable t) {
				supervisionType = null;
			}
		}
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSupTJJDType() {
		if (supTJJDType == null) {
			try {
				supTJJDType = (Code) new mojo.km.persistence.Reference(supTJJDTypeId, Code.class, "TJJD_SUPERVISION_TYPE").getObject();
			} catch (Throwable t) {
				supTJJDType = null;
			}
		}
	}
	
	
	/**
	* set the type reference for class member supervisionType
	*/
	public void setSupervisionType(Code aSupervisionType) {
		if (this.supervisionType == null || !this.supervisionType.equals(aSupervisionType)) {
			markModified();
		}
		if (aSupervisionType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSupervisionType);
		}
		setSupervisionTypeId("" + aSupervisionType.getOID());
		this.supervisionType = (Code) new mojo.km.persistence.Reference(aSupervisionType).getObject();
	}
	/**
	* set the type reference for class member supervisionType
	*/
	public void setSupTJJDType(Code aSupTJJDType) {
		if (this.supTJJDType == null || !this.supTJJDType.equals(aSupTJJDType)) {
			markModified();
		}
		if (aSupTJJDType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSupTJJDType);
		}
		setSupTJJDTypeId("" + aSupTJJDType.getOID());
		this.supTJJDType = (Code) new mojo.km.persistence.Reference(aSupTJJDType).getObject();
	}
	
	/**
	 * @param supervisionStatus The supervisionStatus to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupervisionStatus(String supervisionStatus)
	{
		if (this.supervisionStatus == null || !this.supervisionStatus.equals(supervisionStatus))
		{
			markModified();
		}
		this.supervisionStatus = supervisionStatus;
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
	
	
	
	public String getTjjdIndicator()
	{
	    fetch();
	    return tjjdIndicator;
	}

	public void setTjjdIndicator(String tjjdIndicator)
	{
	    if (this.tjjdIndicator == null || !this.tjjdIndicator.equals(tjjdIndicator))
		{
			markModified();
		}
	    this.tjjdIndicator = tjjdIndicator;
	}

	/**
	* Finds all SupervisionTypeMap by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, SupervisionTypeTJJDMap.class);
	}
	
	public SupervisionTypeMapResponseEvent getResponseEvent(){
		SupervisionTypeMapResponseEvent myRespEvt=new SupervisionTypeMapResponseEvent();		
		myRespEvt.setSupervisionTypeId(this.getSupervisionTypeId());
		myRespEvt.setSupervisionTypeMapId(this.getOID().toString());
		myRespEvt.setSplCategoryId( this.getSplCategoryId());
		myRespEvt.setSupTJJDTypeId(this.getSupTJJDTypeId());
		myRespEvt.setTjjdIndicator(this.getTjjdIndicator());
		return myRespEvt;
	}
}
