package messaging.districtCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author nemathew
 * Added for US 58302, Task 58795
 */
public class SaveAncillarySettingEvent extends RequestEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date courtDate;
	private String courtId;
	private String courtTime;
	private String petitionNum;
	private String issueFlag;
	private String typeCase;
	private String respondentName;
	private String settingReason;
	private String barNum;
	private String attorneyName;
	private String attorneyConnection;
	private Date filingDate;
	private String chainNum;
	private String seqNumber;
	private Date	lcDate;
	private Date	lcTime;
	private String	lcUser;
	private String recType;
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
	/**
	 * @return the courtId
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId the courtId to set
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	/**
	 * @return
	 */
	public String getCourtTime() {
		return courtTime;
	}
	public void setCourtTime(String courtTime) {
		this.courtTime = courtTime;
	}
	public String getPetitionNum() {
		return petitionNum;
	}
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	public String getIssueFlag() {
		return issueFlag;
	}
	public void setIssueFlag(String issueFlag) {
		this.issueFlag = issueFlag;
	}
	public String getTypeCase() {
		return typeCase;
	}
	public void setTypeCase(String typeCase) {
		this.typeCase = typeCase;
	}
	public String getRespondentName() {
		return respondentName;
	}
	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}
	public String getSettingReason() {
		return settingReason;
	}
	public void setSettingReason(String settingReason) {
		this.settingReason = settingReason;
	}
	public String getBarNum() {
		return barNum;
	}
	public void setBarNum(String barNum) {
		this.barNum = barNum;
	}
	public String getAttorneyName() {
		return attorneyName;
	}
	public void setAttorneyName(String attorneyName) {
		this.attorneyName = attorneyName;
	}
	public String getAttorneyConnection() {
		return attorneyConnection;
	}
	public void setAttorneyConnection(String attorneyConnection) {
		this.attorneyConnection = attorneyConnection;
	}
	public Date getFilingDate() {
		return filingDate;
	}
	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}
	public String getChainNum() {
		return chainNum;
	}
	public void setChainNum(String chainNum) {
		this.chainNum = chainNum;
	}
	public String getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}
	public Date getLcDate() {
		return lcDate;
	}
	public void setLcDate(Date lcDate) {
		this.lcDate = lcDate;
	}
	public Date getLcTime() {
		return lcTime;
	}
	public void setLcTime(Date lcTime) {
		this.lcTime = lcTime;
	}
	public String getLcUser() {
		return lcUser;
	}
	public void setLcUser(String lcUser) {
		this.lcUser = lcUser;
	}
	public String getRecType() {
		return recType;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
	
}
