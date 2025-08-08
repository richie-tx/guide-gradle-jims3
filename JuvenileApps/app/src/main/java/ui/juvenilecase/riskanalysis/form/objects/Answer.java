package ui.juvenilecase.riskanalysis.form.objects;

import java.util.Date;

import naming.RiskAnalysisConstants;

public class Answer {
	
	//Answer
	private String riskAnswerId;
	private String answerEntryDate = RiskAnalysisConstants.DATE_FORMAT.format(new Date());
	private String weight;
	private String subordinateQuestionId;
	private String subordinateQuestionName;
	private String sortOrder;
	private String action;
	private String answerText;
	
	public Answer () {
		
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

	public void setSubordinateQuestionName(String subordinateQuestionName) {
		this.subordinateQuestionName = subordinateQuestionName;
	}

	public String getSubordinateQuestionName() {
		return subordinateQuestionName;
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

	public void setRiskAnswerId(String riskAnswerId) {
		this.riskAnswerId = riskAnswerId;
	}

	public String getRiskAnswerId() {
		return riskAnswerId;
	}
	
}
