package ui.juvenilecase.riskanalysis.form.objects;

public class Question {
	private String riskQuestionId;
	private String questionName;
	private String questonEntryDate;
	private String questionText;
	private String uiControlType;
	private String uiControlTypeDesc;
	private String collapsibleHeader;
	private String defaultToSystemDate;
	private String uiDisplayOrder;
	private String allowFutureDates;
	private String numericOnly;
	private String questionInitialAction;
	private String required;
	private String controlCode;
	private String controlCodeName;
	private String answerSource;
	private String modificationReason;
	private String modificationDate;
	private String riskCategoryId;
	private String hardcoded;
	private String allowPrint;

	public Question() {
	}

	public void setRiskQuestionId(String riskQuestionId) {
		this.riskQuestionId = riskQuestionId;
	}

	public String getRiskQuestionId() {
		return riskQuestionId;
	}
	
	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestonEntryDate() {
		return questonEntryDate;
	}

	public void setQuestonEntryDate(String questonEntryDate) {
		this.questonEntryDate = questonEntryDate;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getUiControlType() {
		return uiControlType;
	}

	public void setUiControlType(String uiControlType) {
		this.uiControlType = uiControlType;
	}

	public String getCollapsibleHeader() {
		return collapsibleHeader;
	}

	public void setCollapsibleHeader(String collapsibleHeader) {
		this.collapsibleHeader = collapsibleHeader;
	}

	public String getDefaultToSystemDate() {
		return defaultToSystemDate;
	}

	public void setDefaultToSystemDate(String defaultToSystemDate) {
		this.defaultToSystemDate = defaultToSystemDate;
	}

	public String getUiDisplayOrder() {
		return uiDisplayOrder;
	}

	public void setUiDisplayOrder(String uiDisplayOrder) {
		this.uiDisplayOrder = uiDisplayOrder;
	}

	public String getAllowFutureDates() {
		return allowFutureDates;
	}

	public void setAllowFutureDates(String allowFutureDates) {
		this.allowFutureDates = allowFutureDates;
	}

	public String getNumericOnly() {
		return numericOnly;
	}

	public void setNumericOnly(String numericOnly) {
		this.numericOnly = numericOnly;
	}

	public String getQuestionInitialAction() {
		return questionInitialAction;
	}

	public void setQuestionInitialAction(String questionInitialAction) {
		this.questionInitialAction = questionInitialAction;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getControlCode() {
		return controlCode;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getControlCodeName() {
		return controlCodeName;
	}

	public void setControlCodeName(String controlCodeName) {
		this.controlCodeName = controlCodeName;
	}

	public String getAnswerSource() {
		return answerSource;
	}

	public void setAnswerSource(String answerSource) {
		this.answerSource = answerSource;
	}

	public String getHardcoded() {
		return hardcoded;
	}

	public void setHardcoded(String hardcoded) {
		this.hardcoded = hardcoded;
	}

	public void setUiControlTypeDesc(String uiControlTypeDesc) {
		this.uiControlTypeDesc = uiControlTypeDesc;
	}

	public String getUiControlTypeDesc() {
		return uiControlTypeDesc;
	}

	/**
	 * @return the modificationReason
	 */
	public String getModificationReason() {
		return modificationReason;
	}

	/**
	 * @param modificationReason the modificationReason to set
	 */
	public void setModificationReason(String modificationReason) {
		this.modificationReason = modificationReason;
	}

	/**
	 * @return the modificationDate
	 */
	public String getModificationDate() {
		return modificationDate;
	}

	/**
	 * @param modificationDate the modificationDate to set
	 */
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
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