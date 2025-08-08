package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

public class SaveAnswerEvent extends CompositeRequest//RequestEvent
{
	
	//Answer
	private String riskAnswerId;
	private String riskQuestionId;
	private String answerEntryDate;
	private String weight;
	private String subordinateQuestionId;
	private String sortOrder;
	private String action;
	private String answerText;
	private String createUserID;
	private boolean aCreate;
	
	public SaveAnswerEvent() {
		
	}

	public void setAnswerEntryDate(String answerEntryDate) {
		this.answerEntryDate = answerEntryDate;
	}

	public String getAnswerEntryDate() {
		return answerEntryDate;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeight() {
		return weight;
	}

	public void setSubordinateQuestionId(String subordinateQuestionId) {
		this.subordinateQuestionId = subordinateQuestionId;
	}

	public String getSubordinateQuestionId() {
		return subordinateQuestionId;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setRiskQuestionId(String riskQuestionId) {
		this.riskQuestionId = riskQuestionId;
	}

	public String getRiskQuestionId() {
		return riskQuestionId;
	}

	public void setACreate(boolean aCreate) {
		this.aCreate = aCreate;
	}

	public boolean isACreate() {
		return aCreate;
	}

	public void setRiskAnswerId(String riskAnswerId) {
		this.riskAnswerId = riskAnswerId;
	}

	public String getRiskAnswerId() {
		return riskAnswerId;
	}
	
}
