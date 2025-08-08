package messaging.riskanalysis.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class SaveAnswerResponseEvent extends ResponseEvent{
	
	private String riskAnswerId;
	private String answerEntryDate;
	private String weight;
	private String subordinateQuestionId;
	private String subordinateQuestionName;
	private String sortOrder;
	private String action;
	private String answerText;
	
	public void getQuestionsReponseEvent () {
		
	}
	
	public void setRiskAnswerId(String riskAnswerId) {
		this.riskAnswerId = riskAnswerId;
	}

	public String getRiskAnswerId() {
		return riskAnswerId;
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

	public void setSubordinateQuestionName(String subordinateQuestionName) {
		this.subordinateQuestionName = subordinateQuestionName;
	}

	public String getSubordinateQuestionName() {
		return subordinateQuestionName;
	}

}
