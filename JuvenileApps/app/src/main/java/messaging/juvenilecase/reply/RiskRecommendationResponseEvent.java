/*
 * Created on Oct 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskRecommendationResponseEvent extends ResponseEvent
{
	private int riskAnalysisScore;
	private String riskAnalysisRecommendation;
	private String riskAnalysisId;
	private String resultGroup;
	private String resultGroupDisplayDesc;

	/**
	 * @return
	 */
	public String getRiskAnalysisRecommendation()
	{
		return riskAnalysisRecommendation;
	}

	/**
	 * @return
	 */
	public int getRiskAnalysisScore()
	{
		return riskAnalysisScore;
	}

	/**
	 * @param string
	 */
	public void setRiskAnalysisRecommendation(final String string)
	{
		riskAnalysisRecommendation = string;
	}

	/**
	 * @param i
	 */
	public void setRiskAnalysisScore(final int i)
	{
		riskAnalysisScore = i;
	}

	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}

	public void setResultGroup(String resultGroup) {
		this.resultGroup = resultGroup;
	}

	public String getResultGroup() {
		return resultGroup;
	}

	public void setResultGroupDisplayDesc(String resultGroupDisplayDesc) {
		this.resultGroupDisplayDesc = resultGroupDisplayDesc;
	}

	public String getResultGroupDisplayDesc() {
		return resultGroupDisplayDesc;
	}

}
