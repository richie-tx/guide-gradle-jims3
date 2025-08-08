package messaging.districtCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author nemathew
 *
 */
public class GetJJSCLAncillarySettingEvent extends RequestEvent {

	private String courtId;
	private Date courtDate;
	private String courtTime;
	private String petitionNumber;
	private String issueFlag;
	
	
	public String getCourtId() {
		return courtId;
	}
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	
	public String getCourtTime() {
		return courtTime;
	}
	public void setCourtTime(String courtTime) {
		this.courtTime = courtTime;
	}
	public String getPetitionNumber() {
		return petitionNumber;
	}
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	public String getIssueFlag() {
		return issueFlag;
	}
	public void setIssueFlag(String issueFlag) {
		this.issueFlag = issueFlag;
	}
	/**
	 * @return the courtDate
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate the courtDate to set
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
}
