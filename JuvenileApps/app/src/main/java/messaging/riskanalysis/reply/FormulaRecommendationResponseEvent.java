package messaging.riskanalysis.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class FormulaRecommendationResponseEvent extends ResponseEvent implements Comparable{

	private String assessmentTypeName;
	private boolean custody;
	private Date entryDate;
	private String formulaId;
	private int maxScore;
	private int minScore;
	private String recommendationId;
	private String recommendationName;
	private String recommendationDesc;
	private String resultGroup;
	private String resultGroupDisplayDesc;
	
	public String getAssessmentTypeName() {
		return assessmentTypeName;
	}
	public void setAssessmentTypeName(String assessmentTypeName) {
		this.assessmentTypeName = assessmentTypeName;
	}
	public boolean isCustody() {
		return custody;
	}
	public void setCustody(boolean custody) {
		this.custody = custody;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getFormulaId() {
		return formulaId;
	}
	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	public int getMinScore() {
		return minScore;
	}
	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}
	
	public String getRecommendationId() {
		return recommendationId;
	}
	public void setRecommendationId(String recommendationId) {
		this.recommendationId = recommendationId;
	}
	public String getRecommendationName() {
		return recommendationName;
	}
	public void setRecommendationName(String recommendationName) {
		this.recommendationName = recommendationName;
	}
	public String getRecommendationDesc() {
		return recommendationDesc;
	}
	public void setRecommendationDesc(String recommendationDesc) {
		this.recommendationDesc = recommendationDesc;
	}
	public String getResultGroup() {
		return resultGroup;
	}
	public void setResultGroup(String resultGroup) {
		this.resultGroup = resultGroup;
	}
	public String getResultGroupDisplayDesc() {
		return resultGroupDisplayDesc;
	}
	public void setResultGroupDisplayDesc(String resultGroupDisplayDesc) {
		this.resultGroupDisplayDesc = resultGroupDisplayDesc;
	}
	
	/** Sort in order of ascending question number.
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object formulaRecommendationResponseEvent)
	{
		if (formulaRecommendationResponseEvent==null)
			return 1;
		final String rName= ((FormulaRecommendationResponseEvent)formulaRecommendationResponseEvent).getRecommendationName();
		final String thisRName = recommendationName;
		final int result = thisRName.compareTo(rName);

		return result;
	}
}
