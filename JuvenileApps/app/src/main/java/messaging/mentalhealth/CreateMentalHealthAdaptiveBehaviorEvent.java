package messaging.mentalhealth;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class CreateMentalHealthAdaptiveBehaviorEvent extends RequestEvent
{
	private Date testDate;
	private String testSessId;
	private String communicationScore;
	private String livingScore;
	private String socialScore;
	private String compositeScore;
	private String testNameId;
	
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getTestSessId() {
		return testSessId;
	}
	public void setTestSessId(String testSessId) {
		this.testSessId = testSessId;
	}
	public String getCommunicationScore() {
		return communicationScore;
	}
	public void setCommunicationScore(String communicationScore) {
		this.communicationScore = communicationScore;
	}
	public String getLivingScore() {
		return livingScore;
	}
	public void setLivingScore(String livingScore) {
		this.livingScore = livingScore;
	}
	public String getSocialScore() {
		return socialScore;
	}
	public void setSocialScore(String socialScore) {
		this.socialScore = socialScore;
	}
	public String getCompositeScore() {
		return compositeScore;
	}
	public void setCompositeScore(String compositeScore) {
		this.compositeScore = compositeScore;
	}
	public String getTestNameId() {
		return testNameId;
	}
	public void setTestNameId(String testNameId) {
		this.testNameId = testNameId;
	}
	
}
