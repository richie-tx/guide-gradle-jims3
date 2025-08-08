package messaging.referral.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class LegacyCourtOrderResponseEvent extends ResponseEvent{
	private Date courtDate;
	private String courtOrderID;
	private String hearingTypeCode;
	private String hearingTypeDescription;
	private String juvenileCourt;
	private String juvenileCourtName;
	private String juvenileOffenseCode;
	private String juvenileOffenseCodeDescription;
	private String respondentAttorneyName;
	private String petitionNum;
	
	public Date getCourtDate() {
		return courtDate;
	}
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	public String getCourtOrderID() {
		return courtOrderID;
	}
	public void setCourtOrderID(String courtOrderID) {
		this.courtOrderID = courtOrderID;
	}
	public String getHearingTypeCode() {
		return hearingTypeCode;
	}
	public void setHearingTypeCode(String hearingTypeCode) {
		this.hearingTypeCode = hearingTypeCode;
	}
	public String getHearingTypeDescription() {
		return hearingTypeDescription;
	}
	public void setHearingTypeDescription(String hearingTypeDescription) {
		this.hearingTypeDescription = hearingTypeDescription;
	}
	public String getJuvenileCourt() {
		return juvenileCourt;
	}
	public void setJuvenileCourt(String juvenileCourt) {
		this.juvenileCourt = juvenileCourt;
	}
	public String getJuvenileCourtName() {
		return juvenileCourtName;
	}
	public void setJuvenileCourtName(String juvenileCourtName) {
		this.juvenileCourtName = juvenileCourtName;
	}
	public String getJuvenileOffenseCode() {
		return juvenileOffenseCode;
	}
	public void setJuvenileOffenseCode(String juvenileOffenseCode) {
		this.juvenileOffenseCode = juvenileOffenseCode;
	}
	public String getJuvenileOffenseCodeDescription() {
		return juvenileOffenseCodeDescription;
	}
	public void setJuvenileOffenseCodeDescription(
			String juvenileOffenseCodeDescription) {
		this.juvenileOffenseCodeDescription = juvenileOffenseCodeDescription;
	}
	public String getRespondentAttorneyName() {
		return respondentAttorneyName;
	}
	public void setRespondentAttorneyName(String respondentAttorneyName) {
		this.respondentAttorneyName = respondentAttorneyName;
	}
	public String getPetitionNum() {
		return petitionNum;
	}
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}

}
