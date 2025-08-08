package pd.supervision.administerserviceprovider.administerlocation;

import java.util.Iterator;

import messaging.administerlocation.GetAllJuvLocationUnitsByLocationIdEvent;
import messaging.administerlocation.GetJuvLocationUnitsByAgencyEvent;
import messaging.administerlocation.GetJuvLocationUnitsByLocationIdEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
* @roseuid 447357C70049
*/
public class JuvLocationUnit extends PersistentObject {
	private String locationUnitName;
	private String locationId;
	private String juvLocUnitId;
	private String juvUnitCd;
	private String unitStatusId;
	private String locationStatus;
	private Code status = null;	
	private String phoneNumber;
	private String agencyId;
	public int maysiFlag;
	public int drugFlag;
	public int officerProfileFlag;
	public int interviewCalFlag;
	
	/**
	* @roseuid 447357C70049
	*/
	public JuvLocationUnit() {
	}
	/**
	* Access method for the locationUnitName property.
	* @return the current value of the locationUnitName property
	*/
	public String getLocationUnitName() {
		fetch();
		return locationUnitName;
	}
	/**
	* Sets the value of the locationUnitName property.
	* @param aLocationName the new value of the locationUnitName property
	*/
	public void setLocationUnitName(String aLocationUnitName) {
		if (this.locationUnitName == null || !this.locationUnitName.equals(aLocationUnitName)) {
			markModified();
		}
		locationUnitName = aLocationUnitName;
	}
	
	/**
	* Access method for the juvLocUnitId property.
	* @return the current value of the juvLocUnitId property
	*/
	public String getJuvLocUnitId() {
		fetch();
		//return "" + getOID();
		return juvLocUnitId;
	}
	/**
	* Sets the value of the locationCd property.
	* @param aLocationName the new value of the locationCd property
	*/
	public void setJuvLocUnitId(String aJuvLocUnitId) {
		if (this.juvLocUnitId == null || !this.juvLocUnitId.equals(aJuvLocUnitId)) {
			markModified();
		}
		this.juvLocUnitId = aJuvLocUnitId;
	}
	
	/**
	* @roseuid 44734FE40066
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return (Iterator) home.findAll(event, JuvLocationUnit.class);
	}
	
	/**
	* @return 
	* @param locationEvent
	*/
	static public Iterator findLocUnitByAgency(GetJuvLocationUnitsByAgencyEvent locationEvent)
	{
		IHome home = new Home();
		return home.findAll(locationEvent, JuvLocationUnit.class);
	}
	
	/**
	* @return 
	* @param locationEvent
	*/
	static public Iterator findLocUnitByLocationId(GetJuvLocationUnitsByLocationIdEvent locationEvent)
	{
		IHome home = new Home();
		return home.findAll(locationEvent, JuvLocationUnit.class);
	}
	
	/**
	 * method added to fetch all the location units irrespective of the status.
	* @return 
	* @param locationEvent
	*/
	static public Iterator findAllLocUnitsByLocationId(GetAllJuvLocationUnitsByLocationIdEvent locationEvent)
	{
		IHome home = new Home();
		return home.findAll(locationEvent, JuvLocationUnit.class);
	}
	
	 static public Iterator findAll()
    {
        IHome home = new Home();
        Iterator iter = home.findAll(JuvLocationUnit.class);
        return iter;
    }
	/**
	* @return pd.administerLocation.Location
	* @param locationId
	* @roseuid 4107B06D01B5
	*/
	static public JuvLocationUnit find(String juvLocUnitId)
	{
		JuvLocationUnit juvLocUnit = null;
		IHome home = new Home();
		juvLocUnit = (JuvLocationUnit) home.find(juvLocUnitId, JuvLocationUnit.class);
		return juvLocUnit;
	}
	
	public void createOID()
	{
		markModified();
		IHome home = new Home();
		home.bind(this);
	}
		
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator juvLocUnits = null;
		juvLocUnits = home.findAll(attrName, attrValue, JuvLocationUnit.class);
		return juvLocUnits;
	}

	/**
	* Set the reference value to locationId
	*/
	public void setLocationId(String locationId) {
		if (this.locationId == null || !this.locationId.equals(locationId)) {
			markModified();
		}
		this.locationId = locationId;
	}
	
	/**
	* Get the reference value to locationId
	*/
	public String getLocationId() {
		fetch();
		return locationId;
	}

	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(unitStatusId, Code.class, "LOCATION_STATUS").getObject();
		}
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
		setUnitStatusId("" + status.getOID());
		status.setContext("LOCATION_STATUS");
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setUnitStatusId(String statusId) {
		if (this.unitStatusId == null || !this.unitStatusId.equals(statusId)) {
			markModified();
		}
		status = null;
		this.unitStatusId = statusId;
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
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getUnitStatusId() {
		fetch();
		return unitStatusId;
	}

	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		fetch();
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		if (this.phoneNumber == null || !this.phoneNumber.equals(phoneNumber)) {
			markModified();
		}
		this.phoneNumber = phoneNumber;
	}

	
	/**
	 * @return Returns the juvUnitCd.
	 */
	public String getJuvUnitCd() {
		fetch();
		return juvUnitCd;
	}
	/**
	 * @param juvUnitCd The juvUnitCd to set.
	 */
	public void setJuvUnitCd(String juvUnitCd) {
		if (this.juvUnitCd == null || !this.juvUnitCd.equals(juvUnitCd)) {
			markModified();
		}
		this.juvUnitCd = juvUnitCd;
	}
	/**
	 * @return Returns the locationStatus.
	 */
	public String getLocationStatus() {
		fetch();
		return locationStatus;
	}
	/**
	 * @param locationStatus The locationStatus to set.
	 */
	public void setLocationStatus(String locationStatus) {
		if (this.locationStatus == null || !this.locationStatus.equals(locationStatus)) {
			markModified();
		}
		this.locationStatus = locationStatus;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		fetch();
		return agencyId;
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
	public int getMaysiFlag()
	{
	    fetch();
	    return maysiFlag;
	}
	
	public void setMaysiFlag(int maysiFlag)
	{
	    	if (this.maysiFlag != maysiFlag)
		{
			markModified();
		}
		this.maysiFlag = maysiFlag;
	}	
	
	public int getDrugFlag()
	{
	    fetch();
	    return drugFlag;
	}
	public void setDrugFlag(int drugFlag)
	{
	    if (this.drugFlag != drugFlag)
		{
			markModified();
		}
	    this.drugFlag = drugFlag;
	}
	
	
	
	public int getOfficerProfileFlag()
	{
	    fetch();
	    return officerProfileFlag;
	}
	
	public void setOfficerProfileFlag(int officerProfileFlag)
	{
	    if (this.officerProfileFlag != officerProfileFlag ){
		markModified();
	    }
	    this.officerProfileFlag = officerProfileFlag;
	}
	
	public int getInterviewCalFlag()
	{
	    fetch();
	    return interviewCalFlag;
	}
	
	public void setInterviewCalFlag(int interviewCalFlag)
	{
	    if ( this.interviewCalFlag != interviewCalFlag ) {
		markModified();
	    }
	    this.interviewCalFlag = interviewCalFlag;
	}
	
	public LocationResponseEvent getValueObject(){
		LocationResponseEvent loc = new LocationResponseEvent();
		loc.setJuvLocationUnitId(this.getJuvLocUnitId());
		loc.setLocationUnitName(this.getLocationUnitName());
		loc.setJuvLocationUnitPhoneNumber(this.getPhoneNumber());
		loc.setOfficerProfileFlag( this.getOfficerProfileFlag() );
		loc.setInterviewCalFlag( this.getInterviewCalFlag() );
		return loc;
	}
}