package pd.juvenilecase.riskanalysis;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.RiskAnalysisConstants;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * @roseuid 433D86B40189
 */
public class RiskAnalysis extends PersistentObject 
{

	private String assessmentType;
	private int casefileID;
	private boolean custody;
	private boolean completed;
	private Date enteredDate;
	private int finalScore;
	private Collection finalScores = null;;
	private String jpoUserID;
	private String juvenileNum;
	private Collection riskResponses = null;
	private Collection recommendations = null;;
	private boolean recommendationOveridden;
	private String overiddenReasonCd;
	private String overiddenReasonOther;
	private String modReason;
	private int riskFormulaId;
	private String recommendation;
	
	public RiskAnalysis() 
	{
		
	}

	public static Iterator findAllByJuvenileNum(String juvenileNum) 
	{
		IHome home = new Home();
		Iterator riskAnalysisIter = home.findAll("juvenileNum", juvenileNum, RiskAnalysis.class);
		return riskAnalysisIter;

	}

	public static Iterator findAllByCasefileID(String caseFileId) 
	{
		IHome home = new Home();
		Iterator riskAnalysisIter = home.findAll("casefileID", Integer.valueOf(caseFileId), RiskAnalysis.class);
		return riskAnalysisIter;

	}

	public static Iterator findAllByJuvenileNum(Object juvenileNum) 
	{
		IHome home = new Home();
		Iterator riskAnalysisIter = home.findAll("juvenileNum", juvenileNum, RiskAnalysis.class);
		return riskAnalysisIter;
	}

	public static RiskAnalysis findByJuvenileNumAssessmentAge(String juvenileNum) 
	{
		IHome home = new Home();
		return (RiskAnalysis) home.find("juvenileNum", juvenileNum, RiskAnalysis.class);
	}

	public static RiskAnalysis find(String riskAnalysisId) 
	{
		IHome home = new Home();
		return (RiskAnalysis) home.find(riskAnalysisId, RiskAnalysis.class);
	}

	static public Iterator findAll(IEvent event) 
	{
		IHome home = new Home();
		return home.findAll(event, RiskAnalysis.class);
	}

	public String getAssessmentType() 
	{
		fetch();
		return assessmentType;
	}

	public int getCasefileID() 
	{
		fetch();
		return casefileID;
	}

	public Date getEnteredDate() 
	{
		fetch();
		return enteredDate;
	}

/*	public int getFinalScore() 
	{
		fetch();
		return finalScore;
	}
*/
	public String getJpoUserID() 
	{
		fetch();
		return jpoUserID;
	}

	public String getJuvenileNum() 
	{
		fetch();
		return juvenileNum;
	}

/*	public String getRecommendation() 
	{
		fetch();
		return recommendation;
	}
*/	
/*	public void setRecommendation(String string) {
		if (this.recommendation == null || !this.recommendation.equals(string)) {
			markModified();
		}
		recommendation = string;
	}
*/

	public void setAssessmentType(String string) 
	{
		if (this.assessmentType == null || !this.assessmentType.equals(string)) 
		{
			markModified();
		}
		assessmentType = string;
	}

	public void setCasefileID(int i) 
	{
		if (this.casefileID != i) 
		{
			markModified();
		}
		casefileID = i;
	}

	public void setEnteredDate(Date date) 
	{
		if (this.enteredDate == null || !this.enteredDate.equals(date)) 
		{
			markModified();
		}
		enteredDate = date;
	}

/*	public void setFinalScore(int i) 
	{
		if (this.finalScore != i) 
		{
			markModified();
		}
		finalScore = i;
	}
*/
	public void setJpoUserID(String string) 
	{
		if (this.jpoUserID == null || !this.jpoUserID.equals(string)) 
		{
			markModified();
		}
		jpoUserID = string;
	}

	public void setJuvenileNum(String string) 
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(string)) 
		{
			markModified();
		}
		juvenileNum = string;
	}
	
	public boolean isCustody() 
	{
		fetch();
		return custody;
	}

	public void setCustody(boolean b) 
	{
		if (this.custody != b) 
		{
			markModified();
		}
		custody = b;
	}
	
	public boolean isCompleted() 
	{
		fetch();
		return completed;
	}

	public void setCompleted(boolean b) 
	{
		if (this.completed != b) 
		{
			markModified();
		}
		completed = b;
	}

	private void initRiskResponses() 
	{
		if (riskResponses == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			riskResponses = new mojo.km.persistence.ArrayList(
					RiskResponse.class,
					RiskAnalysisConstants.RISK_ANALYSIS_ID, "" + getOID());
		}
	}

	public Collection getRiskResponses()
	{
		initRiskResponses();
		return riskResponses;
	}

	public void insertRiskResponses(
			RiskResponse anObject)
	{
		initRiskResponses();
		riskResponses.add(anObject);
	}

	public void removeRiskResponses(
			RiskResponse anObject)
	{
		initRiskResponses();
		riskResponses.remove(anObject);
	}

	public void clearRiskResponses() 
	{
		//initRiskResponses();
		//riskResponses.clear();
		riskResponses = null;
	}
	
	private void initRiskRecommendations() 
	{
		if (recommendations == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			recommendations = new mojo.km.persistence.ArrayList(
					RiskAnalysisRecommendation.class,
					RiskAnalysisConstants.RISK_ANALYSIS_ID, "" + getOID());
		}
	}
	
	public Collection getRecommendations() 
	{
		initRiskRecommendations();
		return recommendations;
	}
	
	public void insertRecommendations(
			RiskAnalysisRecommendation anObject)
	{
		initRiskRecommendations();
		recommendations.add(anObject);
	}

	public void removeRecommendations(
			RiskAnalysisRecommendation anObject)
	{
		initRiskRecommendations();
		recommendations.remove(anObject);
	}

	public void clearRecommendations() 
	{
		//initRiskRecommendations();
		//recommendations.clear();
		recommendations = null;
	}
	
	private void initFinalScores() 
	{
		if (finalScores == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			finalScores = new mojo.km.persistence.ArrayList(
					RiskFinalScore.class,
					RiskAnalysisConstants.RISK_ANALYSIS_ID, "" + getOID());
		}
	}
	
	public Collection getFinalScores() 
	{
		initFinalScores();
		return finalScores;
	}
	
	public void insertFinalScores(
			RiskFinalScore anObject)
	{
		initFinalScores();
		finalScores.add(anObject);
	}

	public void removeFinalScores(
			RiskFinalScore anObject)
	{
		initFinalScores();
		finalScores.remove(anObject);
	}

	public void clearFinalScores() 
	{
		//initFinalScores();
		//finalScores.clear();
		finalScores = null;
	}

	public boolean isRecommendationOveridden() 
	{
		return recommendationOveridden;
	}

	public void setRecommendationOveridden(boolean recommendationOveridden)
	{
		this.recommendationOveridden = recommendationOveridden;
	}

	public String getOveriddenReasonCd() 
	{
		return overiddenReasonCd;
	}

	public void setOveriddenReasonCd(String overiddenReasonCd) 
	{
		this.overiddenReasonCd = overiddenReasonCd;
	}

	public String getOveriddenReasonOther() 
	{
		return overiddenReasonOther;
	}

	public void setOveriddenReasonOther(String overiddenReasonOther) 
	{
		this.overiddenReasonOther = overiddenReasonOther;
	}

	public void setModReason(String modReason) 
	{
		this.modReason = modReason;
	}

	public String getModReason() 
	{
		return modReason;
	}

	public void setRiskFormulaId(int riskFormulaId) {
		if (this.riskFormulaId != riskFormulaId) 
		{
			markModified();
		}
		this.riskFormulaId = riskFormulaId;
	}

	public int getRiskFormulaId() {
		fetch();
		return riskFormulaId;
	}

	/**
	 * @return the finalScore
	 */
	public int getFinalScore() {
		fetch();
		return finalScore;
	}

	/**
	 * @param finalScore the finalScore to set
	 */
	public void setFinalScore(int finalScore) {
		if (this.finalScore != finalScore) 
		{
			markModified();
		}
		this.finalScore = finalScore;
	}

	/**
	 * @return the recommendation
	 */
	public String getRecommendation() {
		return recommendation;
	}

	/**
	 * @param recommendation the recommendation to set
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	

}