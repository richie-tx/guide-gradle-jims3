//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\SaveReferralAssessmentEvent.java

package messaging.riskanalysis;

public class SaveReferralAssessmentEvent extends SaveRiskAssessmentEvent 
{
	
	private int additionalCharges;
	private int seriousnessIndex;
	private String vopPendingCourt;
	private String pendingCourt;
	private String probationStatus;
	private String riskMandatoryDetentionCd;
	private boolean moreThanOneFailure;
	private boolean isNewReferral;
	
	/**
    * @roseuid 433D83FA03C0
    */
   public SaveReferralAssessmentEvent() 
   {
    
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
	public String getProbationStatus()
	{
		return probationStatus;
	}

	/**
	 * @return
	 */
	public String getVopPendingCourt()
	{
		return vopPendingCourt;
	}

	/**
	 * @param string
	 */
	public void setProbationStatus(String string)
	{
		probationStatus = string;
	}

	/**
	 * @param string
	 */
	public void setVopPendingCourt(String string)
	{
		vopPendingCourt = string;
	}

	/**
	 * @return Returns the pendingCourt.
	 */
	public String getPendingCourt() {
		return pendingCourt;
	}
	/**
	 * @param pendingCourt The pendingCourt to set.
	 */
	public void setPendingCourt(String pendingCourt) {
		this.pendingCourt = pendingCourt;
	}
	/**
	 * @return newReferral
	 */
	public void setNewReferral(boolean isNewReferral) {
		this.isNewReferral = isNewReferral;
	}

	public boolean isNewReferral() {
		return isNewReferral;
	}	/**
	 * @return riskMandatoryDetentionCd
	 */
	public String getRiskMandatoryDetentionCd() {
		return riskMandatoryDetentionCd;
	}
	
	/**
	 * @param riskMandatoryDetentionCd
	 */
	public void setRiskMandatoryDetentionCd(String riskMandatoryDetentionCd) {
		this.riskMandatoryDetentionCd = riskMandatoryDetentionCd;
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

}
