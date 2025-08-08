package messaging.riskanalysis.reply;

import mojo.km.messaging.ResponseEvent;

public class GetQuestionResponseEvent extends ResponseEvent implements Comparable{
	
	private boolean allowsFutureDates;
	private String assessmentType;
	private String controlCode;
	private String controlCodeDesc;
	private boolean collapsibleHeader;
	private boolean defaultToSystemDate;
	private String helpFileId;
	private String initialAction;
	private boolean numeric;
	private String questionId;
	private String questionText;
	private String questionName;
	private boolean required;
	private int riskCategoryId;
	private String uiControlType;
	private String uiDisplayOrder;
	private boolean hardcoded;
	private String questonEntryDate;
	private String questionUpdateDate;
	private int uiDisplayOrderAsInt;
	private boolean allowPrint;
	
	public void getQuestionReponseEvent () {
		
	}

	public void setAllowsFutureDates(boolean allowsFutureDates) {
		this.allowsFutureDates = allowsFutureDates;
	}

	public boolean isAllowsFutureDates() {
		return allowsFutureDates;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public String getAssessmentType() {
		return assessmentType;
	}

	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}

	public String getControlCode() {
		return controlCode;
	}

	public void setCollapsibleHeader(boolean collapsibleHeader) {
		this.collapsibleHeader = collapsibleHeader;
	}

	public boolean isCollapsibleHeader() {
		return collapsibleHeader;
	}

	public void setDefaultToSystemDate(boolean defaultToSystemDate) {
		this.defaultToSystemDate = defaultToSystemDate;
	}

	public boolean isDefaultToSystemDate() {
		return defaultToSystemDate;
	}

	public void setHelpFileId(String helpFileId) {
		this.helpFileId = helpFileId;
	}

	public String getHelpFileId() {
		return helpFileId;
	}

	public void setInitialAction(String initialAction) {
		this.initialAction = initialAction;
	}

	public String getInitialAction() {
		return initialAction;
	}

	public void setNumeric(boolean numeric) {
		this.numeric = numeric;
	}

	public boolean isNumeric() {
		return numeric;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRiskCategoryId(int riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}

	public int getRiskCategoryId() {
		return riskCategoryId;
	}

	public void setUiControlType(String uiControlType) {
		this.uiControlType = uiControlType;
	}

	public String getUiControlType() {
		return uiControlType;
	}

	public void setHardcoded(boolean hardcoded) {
		this.hardcoded = hardcoded;
	}

	public boolean isHardcoded() {
		return hardcoded;
	}

	public void setQuestonEntryDate(String questonEntryDate) {
		this.questonEntryDate = questonEntryDate;
	}

	public String getQuestonEntryDate() {
		return questonEntryDate;
	}

	public void setUiDisplayOrder(String uiDisplayOrder) {
		this.uiDisplayOrder = uiDisplayOrder;
	}

	public String getUiDisplayOrder() {
		return uiDisplayOrder;
	}

	public void setQuestionUpdateDate(String questionUpdateDate) {
		this.questionUpdateDate = questionUpdateDate;
	}

	public String getQuestionUpdateDate() {
		return questionUpdateDate;
	}
	/** Sort in order of ascending question number.
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object questionEvent)
	{
		if (questionEvent==null)
			return 1;
		final String qName= ((GetQuestionResponseEvent)questionEvent).getQuestionName();
		final String thisQName = questionName;
		final int result = thisQName.compareTo(qName);

		return result;
	}

	public void setUiDisplayOrderAsInt(int uiDisplayOrderAsInt) {
		this.uiDisplayOrderAsInt = uiDisplayOrderAsInt;
	}

	public int getUiDisplayOrderAsInt() {
		return uiDisplayOrderAsInt;
	}

	public void setControlCodeDesc(String controlCodeDesc) {
		this.controlCodeDesc = controlCodeDesc;
	}

	public String getControlCodeDesc() {
		return controlCodeDesc;
	}

	public boolean isAllowPrint()
	{
	    return allowPrint;
	}

	public void setAllowPrint(boolean allowPrint)
	{
	    this.allowPrint = allowPrint;
	}

}
