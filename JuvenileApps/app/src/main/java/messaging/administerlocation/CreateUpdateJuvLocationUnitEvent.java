// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\CreateUpdateJuvLocationUnitEvent.java

package messaging.administerlocation;

import messaging.contact.domintf.IPhoneNumber;
import mojo.km.messaging.RequestEvent;

public class CreateUpdateJuvLocationUnitEvent extends RequestEvent {
	public boolean isCreate;

	public String locationUnitName;

	public String juvUnitCd;

	public String unitStatusCd;

	public String locationId;

	public IPhoneNumber phone;
	
	public String locationUnitId;
	public int maysiFlag;
	public int drugFlag;
	public int officerProfileFlag;
	public int interviewCalFlag;
	

	/**
	 * @roseuid 4664669803C6
	 */
	public CreateUpdateJuvLocationUnitEvent() {

	}

	/**
	 * @return Returns the isCreate.
	 */
	public boolean isCreate() {
		return isCreate;
	}

	/**
	 * @param isCreate
	 *            The isCreate to set.
	 */
	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	/**
	 * @return Returns the juvUnitCd.
	 */
	public String getJuvUnitCd() {
		return juvUnitCd;
	}

	/**
	 * @param juvUnitCd
	 *            The juvUnitCd to set.
	 */
	public void setJuvUnitCd(String juvUnitCd) {
		this.juvUnitCd = juvUnitCd;
	}

	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *            The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return Returns the locationUnitName.
	 */
	public String getLocationUnitName() {
		return locationUnitName;
	}

	/**
	 * @param locationUnitName
	 *            The locationUnitName to set.
	 */
	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}

	/**
	 * @return Returns the phone.
	 */
	public IPhoneNumber getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            The phone to set.
	 */
	public void setPhone(IPhoneNumber phone) {
		this.phone = phone;
	}

	/**
	 * @return Returns the unitStatusCd.
	 */
	public String getUnitStatusCd() {
		return unitStatusCd;
	}

	/**
	 * @param unitStatusCd
	 *            The unitStatusCd to set.
	 */
	public void setUnitStatusCd(String unitStatusCd) {
		this.unitStatusCd = unitStatusCd;
	}
	/**
	 * @return Returns the locationUnitId.
	 */
	public String getLocationUnitId() {
		return locationUnitId;
	}
	/**
	 * @param locationUnitId The locationUnitId to set.
	 */
	public void setLocationUnitId(String locationUnitId) {
		this.locationUnitId = locationUnitId;
	}
	public int getMaysiFlag()
	{
	    return maysiFlag;
	}

	public void setMaysiFlag(int maysiFlag)
	{
	    this.maysiFlag = maysiFlag;
	}
	

	public int getDrugFlag()
	{
	    return drugFlag;
	}

	public void setDrugFlag(int drugFlag)
	{
	    this.drugFlag = drugFlag;
	}

	public int getOfficerProfileFlag()
	{
	    return officerProfileFlag;
	}

	public void setOfficerProfileFlag(int officerProfileFlag)
	{
	    this.officerProfileFlag = officerProfileFlag;
	}

	public int getInterviewCalFlag()
	{
	    return interviewCalFlag;
	}

	public void setInterviewCalFlag(int interviewCalFlag)
	{
	    this.interviewCalFlag = interviewCalFlag;
	}
	
	
}
