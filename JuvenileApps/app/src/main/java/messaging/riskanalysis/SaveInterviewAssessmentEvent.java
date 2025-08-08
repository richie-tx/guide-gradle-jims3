//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\SaveInterviewAssessmentEvent.java

package messaging.riskanalysis;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

public class SaveInterviewAssessmentEvent extends CompositeRequest//RequestEvent 
{
	private String assessmentID;
	private boolean updateOverRiddenStatus = false;
	private boolean recommendationOveridden;
	private String overiddenReasonCd;
	private String overiddenReasonOther;
	private boolean isUpdate;
	private String modReason;
	
	private String casefileID = "";
	private String juvenileNum = "";
	
	private Date assessmentDate = null;
	private String jpoUserID = "";
	private int additionalCharges;
	private int seriousnessIndex;
	private String sex;
	private boolean isProbableCauseHearingSelected = false;
	
	private int onsetAge;
	
	private String riskAnalysisId;
	private int latestReferralFinalScore;
	
   
   /**
    * @roseuid 433D83E10071
    */
   public SaveInterviewAssessmentEvent() 
   {
    
   }
   
   /**
	* @return
	*/
   public Date getAssessmentDate()
   {
	   return assessmentDate;
   }

   /**
	* @return
	*/
   public String getCasefileID()
   {
	   return casefileID;
   }

   /**
	* @return
	*/
   public String getJpoUserID()
   {
	   return jpoUserID;
   }

   /**
	* @return
	*/
   public String getJuvenileNum()
   {
	   return juvenileNum;
   }

   /**
	* @param date
	*/
   public void setAssessmentDate(final Date date)
   {
	   assessmentDate = date;
   }

   /**
	* @param string
	*/
   public void setCasefileID(final String string)
   {
	   casefileID = string;
   }

   /**
	* @param string
	*/
   public void setJpoUserID(final String string)
   {
	   jpoUserID = string;
   }

   /**
	* @param string
	*/
   public void setJuvenileNum(final String string)
   {
	   juvenileNum = string;
   }

   /**
	* @return
	*/
   public int getAdditionalCharges()
   {
	   return additionalCharges;
   }

   /**
	* @return
	*/
   public int getSeriousnessIndex()
   {
	   return seriousnessIndex;
   }

   /**
	* @param i
	*/
   public void setAdditionalCharges(final int i)
   {
	   additionalCharges = i;
   }

   /**
	* @param i
	*/
   public void setSeriousnessIndex(final int i)
   {
	   seriousnessIndex = i;
   }

   
	/**
	 * @return
	 */
	public int getOnsetAge()
	{
		return onsetAge;
	}

	/**
	 * @param i
	 */
	public void setOnsetAge(final int i)
	{
		onsetAge = i;
	}

	/**
	 * @return
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @param string
	 */
	public void setSex(String string)
	{
		sex = string;
	}

	/**
	 * @return Returns the isProbableCauseHearingSelected.
	 */
	public boolean isProbableCauseHearingSelected() {
		return isProbableCauseHearingSelected;
	}
	/**
	 * @param isProbableCauseHearingSelected The isProbableCauseHearingSelected to set.
	 */
	public void setProbableCauseHearingSelected(boolean isProbableCauseHearingSelected) {
		this.isProbableCauseHearingSelected = isProbableCauseHearingSelected;
	}
	/**
	 * @return riskAnalysisId
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
	/**
	 * @param riskAnalysisId
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	/**
	 * @return latestReferralFinalScore
	 */
	public int getLatestReferralFinalScore() {
		return latestReferralFinalScore;
	}

	/**
	 * @param latestReferralFinalScore
	 */
	public void setLatestReferralFinalScore(int latestReferralFinalScore) {
		this.latestReferralFinalScore = latestReferralFinalScore;
	}

	public void setAssessmentID(String assessmentID) {
		this.assessmentID = assessmentID;
	}

	public String getAssessmentID() {
		return assessmentID;
	}

	public void setUpdateOverRiddenStatus(boolean updateOverRiddenStatus) {
		this.updateOverRiddenStatus = updateOverRiddenStatus;
	}

	public boolean isUpdateOverRiddenStatus() {
		return updateOverRiddenStatus;
	}

	public void setRecommendationOveridden(boolean recommendationOveridden) {
		this.recommendationOveridden = recommendationOveridden;
	}

	public boolean isRecommendationOveridden() {
		return recommendationOveridden;
	}

	public void setOveriddenReasonCd(String overiddenReasonCd) {
		this.overiddenReasonCd = overiddenReasonCd;
	}

	public String getOveriddenReasonCd() {
		return overiddenReasonCd;
	}

	public void setOveriddenReasonOther(String overiddenReasonOther) {
		this.overiddenReasonOther = overiddenReasonOther;
	}

	public String getOveriddenReasonOther() {
		return overiddenReasonOther;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setModReason(String modReason) {
		this.modReason = modReason;
	}

	public String getModReason() {
		return modReason;
	}
	
}
