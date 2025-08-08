package pd.juvenilecase.riskanalysis;

import java.util.Date;

import naming.RiskAnalysisConstants;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
* @roseuid 433D86B703A0
*/
public class RiskAnalysisCommunity extends RiskAnalysis {
	/**
	* Properties for riskAnalysis
	* @referencedType pd.juvenilecase.RiskAnalysis
	* @detailerDoNotGenerate false
	*/
	private RiskAnalysis riskAnalysis = null;
	public int totalEducationStatus;
	public int totalChildParent;
	public int totalClassesFailing;
	public int totalPeer;
	public int totalGradeLevel;
	public int totalSubstanceAbuse;
	public int totalChild;
	public int totalSchoolBehavior;
	public int totalAttitude;
	public int totalFamilyAttitude;
	//public String pOComments;
	private String riskAnalysisId;
	private Date enteredDate;
	
	/**
	* @roseuid 433D86B703A0
	*/
	public RiskAnalysisCommunity() {
	}
	/**
	* @roseuid 433C3D3E03D5
	*/
	public void None() {
		markModified();
	}

	/**
	* @return 
	*/
	public int getTotalAttitude() {
		fetch();
		return totalAttitude;
	}
	/**
	* @return 
	*/
	public int getTotalChild() {
		fetch();
		return totalChild;
	}
	/**
	* @return 
	*/
	public int getTotalChildParent() {
		fetch();
		return totalChildParent;
	}
	/**
	* @return 
	*/
	public int getTotalClassesFailing() {
		fetch();
		return totalClassesFailing;
	}
	/**
	* @return 
	*/
	public int getTotalEducationStatus() {
		fetch();
		return totalEducationStatus;
	}
	/**
	* @return 
	*/
	public int getTotalFamilyAttitude() {
		fetch();
		return totalFamilyAttitude;
	}
	/**
	* @return 
	*/
	public int getTotalGradeLevel() {
		fetch();
		return totalGradeLevel;
	}
	/**
	* @return 
	*/
	public int getTotalPeer() {
		fetch();
		return totalPeer;
	}
	/**
	* @return 
	*/
	public int getTotalSchoolBehavior() {
		fetch();
		return totalSchoolBehavior;
	}
	/**
	* @return 
	*/
	public int getTotalSubstanceAbuse() {
		fetch();
		return totalSubstanceAbuse;
	}

	/**
	* @param i
	*/
	public void setTotalAttitude(int i) {
		if (this.totalAttitude != i) {
			markModified();
		}
		totalAttitude = i;
	}
	/**
	* @param i
	*/
	public void setTotalChild(int i) {
		if (this.totalChild != i) {
			markModified();
		}
		totalChild = i;
	}
	/**
	* @param i
	*/
	public void setTotalChildParent(int i) {
		if (this.totalChildParent != i) {
			markModified();
		}
		totalChildParent = i;
	}
	/**
	* @param i
	*/
	public void setTotalClassesFailing(int i) {
		if (this.totalClassesFailing != i) {
			markModified();
		}
		totalClassesFailing = i;
	}
	/**
	* @param i
	*/
	public void setTotalEducationStatus(int i) {
		if (this.totalEducationStatus != i) {
			markModified();
		}
		totalEducationStatus = i;
	}
	/**
	* @param i
	*/
	public void setTotalFamilyAttitude(int i) {
		if (this.totalFamilyAttitude != i) {
			markModified();
		}
		totalFamilyAttitude = i;
	}
	/**
	* @param i
	*/
	public void setTotalGradeLevel(int i) {
		if (this.totalGradeLevel != i) {
			markModified();
		}
		totalGradeLevel = i;
	}
	/**
	* @param i
	*/
	public void setTotalPeer(int i) {
		if (this.totalPeer != i) {
			markModified();
		}
		totalPeer = i;
	}
	/**
	* @param i
	*/
	public void setTotalSchoolBehavior(int i) {
		if (this.totalSchoolBehavior != i) {
			markModified();
		}
		totalSchoolBehavior = i;
	}
	/**
	* @param i
	*/
	public void setTotalSubstanceAbuse(int i) {
		if (this.totalSubstanceAbuse != i) {
			markModified();
		}
		totalSubstanceAbuse = i;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.RiskAnalysis
	*/
	public void setRiskAnalysisId(String riskAnalysisId) {
		if (this.riskAnalysisId == null || !this.riskAnalysisId.equals(riskAnalysisId)) {
			markModified();
		}
		riskAnalysis = null;
		this.riskAnalysisId = riskAnalysisId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.RiskAnalysis
	*/
	public String getRiskAnalysisId() {
		fetch();
		return riskAnalysisId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.RiskAnalysis
	*/
	private void initRiskAnalysis() {
		if (riskAnalysis == null) {
			riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(riskAnalysisId, RiskAnalysis.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.RiskAnalysis
	*/
	public RiskAnalysis getRiskAnalysis() {
		initRiskAnalysis();
		return riskAnalysis;
	}
	/**
	* set the type reference for class member riskAnalysis
	*/
	public void setRiskAnalysis(RiskAnalysis riskAnalysis) {
		if (this.riskAnalysis == null || !this.riskAnalysis.equals(riskAnalysis)) {
			markModified();
		}
		if (riskAnalysis.getOID() == null) {
			new mojo.km.persistence.Home().bind(riskAnalysis);
		}
		setRiskAnalysisId("" + riskAnalysis.getOID());
		this.riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(riskAnalysis).getObject();
	}
	/**
	 * @return
	 */
	public Date getEnteredDate()
	{
		fetch();
		return enteredDate;
	}

	public void setEnteredDate(Date date) {
		if (this.enteredDate == null || !this.enteredDate.equals(date)) {
			markModified();
		}
		enteredDate = date;
	}
	static public RiskAnalysisCommunity findByRiskAnalysisId(String attributeValue) {
		IHome home = new Home();
		return (RiskAnalysisCommunity)home.find(RiskAnalysisConstants.RISK_ANALYSIS_ID, attributeValue, RiskAnalysisCommunity.class);
	}

}
