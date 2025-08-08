package pd.juvenilecase;

import java.util.Iterator;
import messaging.juvenilecase.GetSupervisionTypeEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.codetable.Code;


/**
* @referencedType pd.codetable.person.JuvenileLevelUnitCode
* @detailerDoNotGenerate false
*/
public class SupervisionTypeMap extends PersistentObject {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String assignmentLevelId;
	private String serviceUnitId;
	private String supervisionCatId;
	private Code supervisionCat = null;
	private String inactiveInd;
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionTypeMap.class);
	}
	
	/**
	* @referencedType pd.codetable.person.JuvenileLevelUnitCode
	* @detailerDoNotGenerate false
	*/
	private JuvenileLevelUnitCode assignmentLevel = null;
	private String supervisionTypeId;
	/**
	* @referencedType pd.codetable.person.JuvenileLevelUnitCode
	* @detailerDoNotGenerate false
	*/
	private JuvenileLevelUnitCode serviceUnit = null;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey SUPERVISION_TYPE
	* @detailerDoNotGenerate false
	*/
	private Code supervisionType = null;
	/**
	* @roseuid 42768538032C
	*/
	public SupervisionTypeMap() {
	}
	/**
	* This method returns a SupervisionTypeMap instance from the serviceUnit and assignmentLevel.
	* @return SupervisionTypeMap
	* @param serviceUnitId
	* @param assignmentLevelId
	*/
	static public SupervisionTypeMap find(String serviceUnitId, String assignmentLevelId) {
		GetSupervisionTypeEvent dataEvent = new GetSupervisionTypeEvent();
		dataEvent.setAssignmentLevelId(assignmentLevelId);
		dataEvent.setServiceUnitId(serviceUnitId);
		IHome home = new Home();
		Iterator supervisionTypes = home.findAll(dataEvent, SupervisionTypeMap.class);
		SupervisionTypeMap supervisionType = null;
		if (supervisionTypes.hasNext()) {
			supervisionType = (SupervisionTypeMap) supervisionTypes.next();
		}
		return supervisionType;
	}
	static public SupervisionTypeMap find(String oid) {
		IHome home = new Home();
		SupervisionTypeMap supervisionType = (SupervisionTypeMap) home.find(oid, SupervisionTypeMap.class);
		return supervisionType;
	}
	
	static public Iterator findAll() {
		return new Home().findAll(SupervisionTypeMap.class);
	}
	
	/**
	* @return Code SupervisionType
	* @param serviceUnitId This is fetched from M204
	* @param assignmentLevelId This is fetched from M204
	*/
	static public Code determineSupervisionType(String serviceUnitId, String assignmentLevelId) {
		SupervisionTypeMap supervisionTypeMap = find(serviceUnitId, assignmentLevelId);
		if (supervisionTypeMap == null) {
			return null;
		}
		return supervisionTypeMap.getSupervisionType();
	}
	/**
	* Access method for the serviceUnit property.
	* @return the current value of the serviceUnit property
	*/
	public JuvenileLevelUnitCode getServiceUnit() {
		fetch();
		initServiceUnit();
		return serviceUnit;
	}
	/**
	* Access method for the assignmentLevel property.
	* @return the current value of the assignmentLevel property
	*/
	public JuvenileLevelUnitCode getAssignmentLevel() {
		fetch();
		initAssignmentLevel();
		return assignmentLevel;
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
	
	public Code getSupervisionCat() {
		fetch();
		initSupervisionCat();
		return supervisionCat;
	}
	/**
	* @roseuid 427676D702F0
	*/
	public void find() {
		fetch();
	}
	/**
	* Set the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public void setAssignmentLevelId(String aAssignmentLevelId) {
		if (this.assignmentLevelId == null || !this.assignmentLevelId.equals(aAssignmentLevelId)) {
			markModified();
		}
		assignmentLevel = null;
		this.assignmentLevelId = aAssignmentLevelId;
	}
	/**
	* Get the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public String getAssignmentLevelId() {
		fetch();
		return assignmentLevelId;
	}
	/**
	* Initialize class relationship to class pd.codetable.person.JuvenileLevelUnitCode
	*/
	private void initAssignmentLevel() {
		if (assignmentLevel == null) {
			try {
				assignmentLevel =
					(JuvenileLevelUnitCode) new mojo.km.persistence.Reference(assignmentLevelId, JuvenileLevelUnitCode.class).getObject();
			} catch (Throwable t) {
				assignmentLevel = null;
			}
		}
	}
	/**
	* set the type reference for class member assignmentLevel
	*/
	public void setAssignmentLevel(JuvenileLevelUnitCode aAssignmentLevel) {
		if (this.assignmentLevel == null || !this.assignmentLevel.equals(aAssignmentLevel)) {
			markModified();
		}
		if (aAssignmentLevel.getOID() == null) {
			new mojo.km.persistence.Home().bind(aAssignmentLevel);
		}
		setAssignmentLevelId("" + aAssignmentLevel.getOID());
		this.assignmentLevel = (JuvenileLevelUnitCode) new mojo.km.persistence.Reference(aAssignmentLevel).getObject();
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
	public void setSupervisionCatId(String aSupervisionCatId) {
		if (this.supervisionCatId == null || !this.supervisionCatId.equals(aSupervisionCatId)) {
			markModified();
		}
		supervisionCat = null;
		this.supervisionCatId = aSupervisionCatId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getSupervisionTypeId() {
		fetch();
		return this.supervisionTypeId;
	}
	public String getSupervisionCatId() {
		fetch();
		return this.supervisionCatId;
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
	
	private void initSupervisionCat() {
		if (supervisionCat == null) {
			try {
				supervisionCat = (Code) new mojo.km.persistence.Reference(supervisionCatId, Code.class, "SUPERVISION_CATEGORY").getObject();
			} catch (Throwable t) {
				supervisionCat = null;
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
	
	public void setSupervisionCat(Code aSupervisionCat) {
		if (this.supervisionCat == null || !this.supervisionCat.equals(aSupervisionCat)) {
			markModified();
		}
		if (aSupervisionCat.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSupervisionCat);
		}
		setSupervisionCatId("" + aSupervisionCat.getOID());
		this.supervisionCat = (Code) new mojo.km.persistence.Reference(aSupervisionCat).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public void setServiceUnitId(String aServiceUnitId) {
		if (this.serviceUnitId == null || !this.serviceUnitId.equals(aServiceUnitId)) {
			markModified();
		}
		serviceUnit = null;
		this.serviceUnitId = aServiceUnitId;
	}
	/**
	* Get the reference value to class :: pd.codetable.person.JuvenileLevelUnitCode
	*/
	public String getServiceUnitId() {
		fetch();
		return serviceUnitId;
	}
	/**
	* Initialize class relationship to class pd.codetable.person.JuvenileLevelUnitCode
	*/
	private void initServiceUnit() {
		if (serviceUnit == null) {
			try {
				serviceUnit =
					(JuvenileLevelUnitCode) new mojo.km.persistence.Reference(serviceUnitId, JuvenileLevelUnitCode.class).getObject();
			} catch (Throwable t) {
				serviceUnit = null;
			}
		}
	}
	/**
	* set the type reference for class member serviceUnit
	*/
	public void setServiceUnit(JuvenileLevelUnitCode aServiceUnit) {
		if (this.serviceUnit == null || !this.serviceUnit.equals(aServiceUnit)) {
			markModified();
		}
		if (aServiceUnit.getOID() == null) {
			new mojo.km.persistence.Home().bind(aServiceUnit);
		}
		setServiceUnitId("" + aServiceUnit.getOID());
		this.serviceUnit = (JuvenileLevelUnitCode) new mojo.km.persistence.Reference(aServiceUnit).getObject();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getInactiveInd()
	{
	    fetch();
	    return inactiveInd;
	}
	
	/**
	 * 
	 * @param inActiveInd
	 */
	public void setInactiveInd(String inActiveInd)
	{
	    if (this.inactiveInd == null || !this.inactiveInd.equals(inActiveInd)) {
		markModified();
	}
	    inactiveInd = null;
	    this.inactiveInd = inActiveInd;
	}
	/**
	* Finds all SupervisionTypeMap by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, SupervisionTypeMap.class);
	}
	
	public SupervisionTypeMapResponseEvent getResponseEvent(){
		SupervisionTypeMapResponseEvent myRespEvt=new SupervisionTypeMapResponseEvent();
		myRespEvt.setAssignmentLevelId(this.getAssignmentLevelId());
		myRespEvt.setServiceUnitId(this.getServiceUnitId());
		myRespEvt.setSupervisionCatId(this.getSupervisionCatId());
		myRespEvt.setSupervisionTypeId(this.getSupervisionTypeId());
		myRespEvt.setSupervisionTypeMapId(this.getOID().toString());
		//added for bug 50021
		myRespEvt.setSupervisionType(this.getSupervisionType().getDescription());
		myRespEvt.setSupervisionTypeStatus(this.getSupervisionType().getStatus());
		myRespEvt.setInactiveInd(this.getInactiveInd());
		//added for bug 50021
		return myRespEvt;
	}
}
