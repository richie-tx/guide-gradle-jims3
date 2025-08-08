package messaging.posttrial.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class CaseAssignmentOfficerResponseEvent extends ResponseEvent
{
    private String officerLastName;
    private String officerFirstName;
    private String officerMiddleName;
    private String officerPosition;
    private String staffPositionId;
    private String displayLiteral;
    
	/**
	 * @return the displayLiteral
	 */
	public String getDisplayLiteral() {
		StringBuffer str = new StringBuffer();
		str.append(this.getOfficerLastName() == null?"":(this.getOfficerLastName() + ", "));
		str.append(this.getOfficerFirstName() == null?"":(this.getOfficerFirstName() + " "));
		str.append((this.getOfficerMiddleName() == null?"":this.getOfficerMiddleName()));
		if(str.toString().length() < 2){
			str.append("No Officer Assigned");
		}
		str.append(" | ");
		str.append(this.getOfficerPosition());
		return str.toString();
	}
	public String getOfficerPosition() {
		return officerPosition;
	}
	public String getOfficerLastName() {
		return officerLastName;
	}
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}
	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}
	public String getStaffPositionId() {
		return staffPositionId;
	}
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
}



