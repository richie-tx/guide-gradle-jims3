package messaging.administercaseload.reply;

import messaging.contact.domintf.IName;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class AdditionalCaseInfoResponseEvent extends ResponseEvent
{
    private String officerLogonId;
    private String staffPositionId;
    private IName officerName;
    private String levelOfSupervision;
    
	public String getOfficerLogonId() {
		return officerLogonId;
	}
	public void setOfficerLogonId(String officerLogonId) {
		this.officerLogonId = officerLogonId;
	}
	public String getStaffPositionId() {
		return staffPositionId;
	}
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	public IName getOfficerName() {
		return officerName;
	}
	public void setOfficerName(IName name) {
		this.officerName = name;
	}
	public String getLevelOfSupervision() {
		return levelOfSupervision;
	}
	public void setLevelOfSupervision(String levelOfSupervision) {
		this.levelOfSupervision = levelOfSupervision;
	}	
}
