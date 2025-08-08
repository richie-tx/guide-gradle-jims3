/*
 * Created on Sep 16, 2005
 *
 */
package ui.juvenilecase.form.riskanalysis;

import naming.RiskAnalysisConstants;

import org.apache.struts.action.ActionForm;

/**
 * @author dwilliamson
 * 
 */
public class RiskAssessmentReferralForm extends ActionForm
{
	
	// action will be one of UIConstants.EDIT, UIConstants.SUMMARY, or UIConstants.CONFIRM
	private String action;
	private String secondaryAction = "";

	private String riskMandatoryDetentionCd;
	private String riskMandatoryDetentionDesc;
	private String capitalFelonyTotal;
	private String felony1Total;
	private String felony2Total;
	private String felony3Total;
	private String levelTotal;
	private String misdClassABTotal;
	private String misdClassCTotal;
	private String referralHistoryTotal;
	private String offensesTotal;
	private String statusCityOrdOffensesTotal;
	private String stateJailFelonyTotal;

	// generic risk assessment attributes
	//private Date referralAssessmentDate;
	private String assessmentType = RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL;
//	private String juvenileNum;
//	private String casefileID;
//	private String recommendation;
//    private List recommendations;
//	private String recommendationScore;
//	private String riskAnalysisId;
	private String currentlyOnProbation;
	private String numberOfCharges;
	private String pendingCourt;
	private String pendingCourtVOP;
	private String ageFirstReferred;
	private String seriousnessIndex;
	
//	private List questionAnswers = new ArrayList();
//	private List processedQuestionAnswers;
//	private List processedViewQuestionAnswers;

	private String isNewReferral;
	private boolean moreThanOneFailure;
	private String moreThanOneFailureString;	
	//private String mode;
	//private String modReason;
	
	/**
	 * @return
	 */
	public String getAssessmentType()
	{
		return assessmentType;
	}

	/**
	 * @param assessmentType
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	/**
	 * @return
	 */
	public String getCapitalFelonyTotal()
	{
		return capitalFelonyTotal;
	}

	/**
	 * @return
	 */
	public String getCurrentlyOnProbation()
	{
		return currentlyOnProbation;
	}

	/**
	 * @return
	 */
	public String getFelony1Total()
	{
		return felony1Total;
	}

	/**
	 * @return
	 */
	public String getFelony2Total()
	{
		return felony2Total;
	}

	/**
	 * @return
	 */
	public String getFelony3Total()
	{
		return felony3Total;
	}

	/**
	 * @return
	 */
	public String getLevelTotal()
	{
		return levelTotal;
	}

	/**
	 * @return
	 */
	public String getMisdClassABTotal()
	{
		return misdClassABTotal;
	}

	/**
	 * @return
	 */
	public String getMisdClassCTotal()
	{
		return misdClassCTotal;
	}

	/**
	 * @return
	 */
	public String getNumberOfCharges()
	{
		return numberOfCharges;
	}

	/**
	 * @return
	 */
	public String getOffensesTotal()
	{
		return offensesTotal;
	}

	/**
	 * @return
	 */
	public String getPendingCourt()
	{
		return pendingCourt;
	}

	/**
	 * @return
	 */
	public String getPendingCourtVOP()
	{
		return pendingCourtVOP;
	}

	/**
	 * @return
	 */
	public String getReferralHistoryTotal()
	{
		return referralHistoryTotal;
	}

	/**
	 * @return
	*/
	public String getStateJailFelonyTotal()
	{
		return stateJailFelonyTotal;
	}

	/**
	 * @return
	 */
	public String getStatusCityOrdOffensesTotal()
	{
		return statusCityOrdOffensesTotal;
	}

	/**
	 * @param i
	 */
	public void setCapitalFelonyTotal(String i)
	{
		capitalFelonyTotal = i;
	}

	/**
	 * @param string
	 */
	public void setCurrentlyOnProbation(String string)
	{
		currentlyOnProbation = string;
	}

	/**
	 * @param i
	 */
	public void setFelony1Total(String i)
	{
		felony1Total = i;
	}

	/**
	 * @param i
	 */
	public void setFelony2Total(String i)
	{
		felony2Total = i;
	}

	/**
	 * @param i
	 */
	public void setFelony3Total(String i)
	{
		felony3Total = i;
	}

	/**
	 * @param i
	 */
	public void setLevelTotal(String i)
	{
		levelTotal = i;
	}

	/**
	 * @param i
	 */
	public void setMisdClassABTotal(String i)
	{
		misdClassABTotal = i;
	}

	/**
	 * @param i
	 */
	public void setMisdClassCTotal(String i)
	{
		misdClassCTotal = i;
	}

	/**
	 * @param string
	 */
	public void setNumberOfCharges(String string)
	{
		numberOfCharges = string;
	}

	/**
	 * @param i
	 */
	public void setOffensesTotal(String i)
	{
		offensesTotal = i;
	}

	/**
	 * @param string
	 */
	public void setPendingCourt(String string)
	{
		pendingCourt = string;
	}

	/**
	 * @param string
	 */
	public void setPendingCourtVOP(String string)
	{
		pendingCourtVOP = string;
	}

	/**
	 * @param i
	 */
	public void setReferralHistoryTotal(String i)
	{
		referralHistoryTotal = i;
	}

	/**
	 * @param i
	 */
	public void setStateJailFelonyTotal(String i)
	{
		stateJailFelonyTotal = i;
	}

	/**
	 * @param i
	 */
	public void setStatusCityOrdOffensesTotal(String i)
	{
		statusCityOrdOffensesTotal = i;
	}

	/**
	 * @return Returns the ageFirstReferred.
	 */
	public String getAgeFirstReferred()
	{
		return ageFirstReferred;
	}

	/**
	 * @param ageFirstReferred
	 *          The ageFirstReferred to set.
	 */
	public void setAgeFirstReferred(String ageFirstReferred)
	{
		this.ageFirstReferred = ageFirstReferred;
	}

	/**
	 * @return Returns the seriousnessIndex.
	 */
	public String getSeriousnessIndex()
	{
		return seriousnessIndex;
	}

	/**
	 * @param seriousnessIndex
	 *          The seriousnessIndex to set.
	 */
	public void setSeriousnessIndex(String seriousnessIndex)
	{
		this.seriousnessIndex = seriousnessIndex;
	}

	/**
	 * @return isNewReferral
	 */
	public String getIsNewReferral()
	{
		return isNewReferral;
	}

	/**
	 * @param isNewReferral
	 */
	public void setIsNewReferral(String isNewReferral)
	{
		this.isNewReferral = isNewReferral;
	}

	/**
	 * @return riskMandatoryDetentionCd
	 */
	public String getRiskMandatoryDetentionCd()
	{
		return riskMandatoryDetentionCd;
	}

	/**
	 * @param riskMandatoryDetentionCd
	 */
	public void setRiskMandatoryDetentionCd(String riskMandatoryDetentionCd)
	{
		this.riskMandatoryDetentionCd = riskMandatoryDetentionCd;
	}

	/**
	 * @return riskMandatoryDetentionDesc
	 */
	public String getRiskMandatoryDetentionDesc()
	{
		return riskMandatoryDetentionDesc;
	}

	/**
	 * @param riskMandatoryDetentionDesc
	 */
	public void setRiskMandatoryDetentionDesc(String riskMandatoryDetentionDesc)
	{
		this.riskMandatoryDetentionDesc = riskMandatoryDetentionDesc;
	}

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * @return the secondaryAction
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @param secondaryAction the secondaryAction to set
	 */
	public void setSecondaryAction(String secondaryAction)
	{
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return moreThanOneFailure
	 */
	public boolean isMoreThanOneFailure() {
		return moreThanOneFailure;
	}
	/**
	 * @param moreThanOneFailure
	 */
	public void setMoreThanOneFailure(boolean moreThanOneFailure) {
		this.moreThanOneFailure = moreThanOneFailure;
	}
	
	/**
	 * @return moreThanOneFailureString
	 */
	public String getMoreThanOneFailureString() {
		return moreThanOneFailureString;
	}

	/**
	 * @param moreThanOneFailureString
	 */
	public void setMoreThanOneFailureString(String moreThanOneFailureString) {
		this.moreThanOneFailureString = moreThanOneFailureString;
	}
	
}
