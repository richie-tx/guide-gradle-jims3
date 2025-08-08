package pd.juvenilecase.riskanalysis;

import java.util.Iterator;

import pd.codetable.Code;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.RiskAnalysisConstants;

public class RiskAnswer extends PersistentObject {
	
	//private int riskResponseId;
	private int riskAnalysisId;
	private String assessmentType;
	private Code assessmentTypeCode = null;
	
	//Question Info
	private int questionId;
	private String questionNumber;
	private String questionText;
	private String uiControlType;
	private Code uiControlTypeCode = null;
	
	//Answer Info
	private int weightedResponseID;
	private String answerText;
	private int weight;
	private int sortOrder;
	
	//Possible Text Comment or Date for answer
	private String text;
	
	private int suggestedCasePlainDomiainId;
	private String suggestedCasePlainDomiainName;
	private int suggestedCasePlainDomiainNumericRule;
	private int subordinateQuestionId;
	
	public RiskAnswer() {
	}
	
	/**
	* @return RiskResponse
	* @param questionId
	*/
	static public RiskAnswer find(String riskResponseId) {
		IHome home = new Home();
		RiskAnswer riskQues = (RiskAnswer) home.find(riskResponseId, RiskAnswer.class);
		return riskQues;
	}
	
	/**
	* Finds all RiskResponse by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator riskAnswers = home.findAll(attributeName, attributeValue, RiskAnswer.class);
		return riskAnswers;
	}
	
	//public int getRiskResponseId() {
	//	fetch();
	//	return riskResponseId;
	//}

	//public void setRiskResponseId(int riskResponseId) {
	//	this.riskResponseId = riskResponseId;
	//}

	public int getRiskAnalysisId() {
		fetch();
		return riskAnalysisId;
	}

	public void setRiskAnalysisId(int riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	public String getAssessmentType() {
		fetch();
		return assessmentType;
	}

	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	public int getQuestionId() {
		fetch();
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.RiskQuestions
	*/
	public void setSubordinateQuestionId(int subordinateQuestionId) {
		this.subordinateQuestionId = subordinateQuestionId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.RiskQuestions
	*/
	public int getSubordinateQuestionId() {
		fetch();
		return subordinateQuestionId;
	}
	
	/**
	* Set the reference value to class :: pd.juvenilecase.RiskQuestions
	*/
	public void setSuggestedCasePlainDomiainId(int suggestedCasePlainDomiainId) {
		this.suggestedCasePlainDomiainId = suggestedCasePlainDomiainId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.RiskQuestions
	*/
	public int getSuggestedCasePlainDomiainId() {
		fetch();
		return suggestedCasePlainDomiainId;
	}

	public String getQuestionNumber() {
		fetch();
		return questionNumber;
	}

	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionText() {
		fetch();
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getUiControlType() {
		fetch();
		return uiControlType;
	}

	public void setUiControlType(String uiControlType) {
		this.uiControlType = uiControlType;
	}

	public int getWeightedResponseID() {
		fetch();
		return weightedResponseID;
	}

	public void setWeightedResponseID(int weightedResponseID) {
		this.weightedResponseID = weightedResponseID;
	}

	public String getAnswerText() {
		fetch();
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public int getWeight() {
		fetch();
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSortOrder() {
		fetch();
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getText() {
		fetch();
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSuggestedCasePlainDomiainName(String suggestedCasePlainDomiainName) {
		this.suggestedCasePlainDomiainName = suggestedCasePlainDomiainName;
	}

	public String getSuggestedCasePlainDomiainName() {
		fetch();
		return suggestedCasePlainDomiainName;
	}

	public void setSuggestedCasePlainDomiainNumericRule(int suggestedCasePlainDomiainNumericRule) {
		this.suggestedCasePlainDomiainNumericRule = suggestedCasePlainDomiainNumericRule;
	}

	public int getSuggestedCasePlainDomiainNumericRule() {
		fetch();
		return suggestedCasePlainDomiainNumericRule;
	}
	private void initUiControlTypeCode()
	{
		if (uiControlTypeCode == null)
		{
			uiControlTypeCode = (Code) new mojo.km.persistence.Reference(uiControlType,
					Code.class, RiskAnalysisConstants.JUV_RISK_UI_CONTROL_TYPE).getObject();
		}
	}
	public Code getUiControlTypeCode()
	{
		initUiControlTypeCode();
		return uiControlTypeCode;
	}
	public void setUiControlTypeCode(Code uiControlTypeCode)
	{
		if (this.uiControlTypeCode == null || !this.uiControlTypeCode.equals(uiControlTypeCode))
		{
			markModified();
		}
		if (uiControlTypeCode.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(uiControlTypeCode);
		}
		setUiControlType("" + uiControlTypeCode.getOID());
		this.uiControlTypeCode = (Code) new mojo.km.persistence.Reference(uiControlTypeCode).getObject();
	}
	private void initAssessmentTypeCode()
	{
		if (assessmentTypeCode == null)
		{
			assessmentTypeCode = (Code) new mojo.km.persistence.Reference(assessmentType,
					Code.class, RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE).getObject();
		}
	}
	public Code getAssessmentTypeCode()
	{
		initAssessmentTypeCode();
		return assessmentTypeCode;
	}
	public void setAssessmentTypeCode(Code assessmentTypeCode)
	{
		if (this.assessmentTypeCode == null || !this.assessmentTypeCode.equals(assessmentTypeCode))
		{
			markModified();
		}
		if (assessmentTypeCode.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(assessmentTypeCode);
		}
		setAssessmentType("" + assessmentTypeCode.getOID());
		this.assessmentTypeCode = (Code) new mojo.km.persistence.Reference(assessmentTypeCode).getObject();
	}
}
