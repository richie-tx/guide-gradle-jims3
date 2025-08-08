//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\SaveReferralAssessmentEvent.java

package messaging.riskanalysis;

public class SaveProgressAssessmentEvent extends SaveRiskAssessmentEvent 
	{
	
	private boolean updateOverRiddenStatus = false;
	private boolean recommendationOveridden;
	private String overiddenReasonCd;
	private String overiddenReasonOther;
   /**
    * @roseuid 433D83FA03C0
    */
   
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
}
