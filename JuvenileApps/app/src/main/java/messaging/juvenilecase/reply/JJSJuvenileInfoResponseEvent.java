package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

public class JJSJuvenileInfoResponseEvent extends ResponseEvent{
	
	private String juvenileNum;
	private String statusId;
	private String propointLevelId;
	private String propointUnitId;
	private String propointJPOpId;
	private String lastName;
	private String firstName;
	private String middleName;
	private String oldStatusId;
	
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getPropointLevelId() {
		return propointLevelId;
	}
	public void setPropointLevelId(String propointLevelId) {
		this.propointLevelId = propointLevelId;
	}
	public String getPropointUnitId() {
		return propointUnitId;
	}
	public void setPropointUnitId(String propointUnitId) {
		this.propointUnitId = propointUnitId;
	}
	public String getPropointJPOpId() {
		return propointJPOpId;
	}
	public void setPropointJPOpId(String propointJPOpIdId) {
		this.propointJPOpId = propointJPOpIdId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the oldStatusId
	 */
	public String getOldStatusId()
	{
	    return oldStatusId;
	}
	/**
	 * @param oldStatusId the oldStatusId to set
	 */
	public void setOldStatusId(String oldStatusId)
	{
	    this.oldStatusId = oldStatusId;
	}
	
}
