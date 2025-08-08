package messaging.riskanalysis.reply;

import mojo.km.messaging.ResponseEvent;

public class SaveQuestionResponseEvent extends ResponseEvent{
	
	private String riskQuestionId;
	private String questionName;
	private String questonEntryDate;
	private String questionText;
	private String uiControlType;
	private String collapsibleHeader;
	private String uiDisplayOrder;
	private String allowFutureDates;
	private String numericOnly;
	private String questionInitialAction;
	private String required;
	private String controlCode;
	private String answerSource;
	private String hardcoded;
	private String defaultToSystemDate;
	private String riskCategoryId;
	private String allowPrint;
	
	public SaveQuestionResponseEvent() {
		
	}

	public void setRiskQuestionId(String riskQuestionId) {
		this.riskQuestionId = riskQuestionId;
	}

	public String getRiskQuestionId() {
		return riskQuestionId;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	public String getQuestionName() {
		return questionName;
	}

	public void setQuestonEntryDate(String questonEntryDate) {
		this.questonEntryDate = questonEntryDate;
	}

	public String getQuestonEntryDate() {
		return questonEntryDate;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setUiControlType(String uiControlType) {
		this.uiControlType = uiControlType;
	}

	public String getUiControlType() {
		return uiControlType;
	}

	public void setCollapsibleHeader(String collapsibleHeader) {
		this.collapsibleHeader = collapsibleHeader;
	}

	public String getCollapsibleHeader() {
		return collapsibleHeader;
	}

	public void setUiDisplayOrder(String uiDisplayOrder) {
		this.uiDisplayOrder = uiDisplayOrder;
	}

	public String getUiDisplayOrder() {
		return uiDisplayOrder;
	}

	public void setAllowFutureDates(String allowFutureDates) {
		this.allowFutureDates = allowFutureDates;
	}

	public String getAllowFutureDates() {
		return allowFutureDates;
	}

	public void setNumericOnly(String numericOnly) {
		this.numericOnly = numericOnly;
	}

	public String getNumericOnly() {
		return numericOnly;
	}

	public void setQuestionInitialAction(String questionInitialAction) {
		this.questionInitialAction = questionInitialAction;
	}

	public String getQuestionInitialAction() {
		return questionInitialAction;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getRequired() {
		return required;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getControlCode() {
		return controlCode;
	}

	public void setAnswerSource(String answerSource) {
		this.answerSource = answerSource;
	}

	public String getAnswerSource() {
		return answerSource;
	}

	public void setHardcoded(String hardcoded) {
		this.hardcoded = hardcoded;
	}

	public String getHardcoded() {
		return hardcoded;
	}

	public void setDefaultToSystemDate(String defaultToSystemDate) {
		this.defaultToSystemDate = defaultToSystemDate;
	}

	public String getDefaultToSystemDate() {
		return defaultToSystemDate;
	}

	public void setRiskCategoryId(String riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}

	public String getRiskCategoryId() {
		return riskCategoryId;
	}

	public String getAllowPrint()
	{
	    return allowPrint;
	}

	public void setAllowPrint(String allowPrint)
	{
	    this.allowPrint = allowPrint;
	}

}
