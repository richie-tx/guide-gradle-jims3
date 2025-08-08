package pd.juvenilecase.riskanalysis;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.RiskAnalysisConstants;

/**
* Properties for riskAnalysis
* @referencedType pd.juvenilecase.RiskAnalysis
* @detailerDoNotGenerate false
*/
public class RiskAnalysisTest extends RiskAnalysis {
	public int totalEducation;
	public int totalAppearence;
	/**
	* Properties for riskAnalysis
	* @referencedType pd.juvenilecase.RiskAnalysis
	* @detailerDoNotGenerate false
	*/
	private RiskAnalysis riskAnalysis = null;
	public int totalFamilyRelationship;
	public int totalDevelopmental;
	public int totalBehviorHistory;
	public int totalSchoolAttendance;
	public int totalPeerRelationship;
	public int totalSelfImage;
	public int totalParentalSupervision;
	public int totalSchoolBehavior;
	public int totalAbuseHistory;
	public int totalSchoolAcademic;
	public int totalFamilyProblems;
	public int totalSubstance;
	public int totalStrengths;
	private String riskAnalysisId;
	/**
	* @roseuid 433D86DA033D
	*/
	public RiskAnalysisTest() {
	}
	/**
	* @roseuid 433C3D3E025B
	*/
	public void None() {
		markModified();
	}
	/**
	* @return 
	*/
	public int getTotalAbuseHistory() {
		fetch();
		return totalAbuseHistory;
	}
	/**
	* @return 
	*/
	public int getTotalAppearence() {
		fetch();
		return totalAppearence;
	}
	/**
	* @return 
	*/
	public int getTotalBehviorHistory() {
		fetch();
		return totalBehviorHistory;
	}
	/**
	* @return 
	*/
	public int getTotalDevelopmental() {
		fetch();
		return totalDevelopmental;
	}
	/**
	* @return 
	*/
	public int getTotalEducation() {
		fetch();
		return totalEducation;
	}
	/**
	* @return 
	*/
	public int getTotalFamilyProblems() {
		fetch();
		return totalFamilyProblems;
	}
	/**
	* @return 
	*/
	public int getTotalFamilyRelationship() {
		fetch();
		return totalFamilyRelationship;
	}
	/**
	* @return 
	*/
	public int getTotalParentalSupervision() {
		fetch();
		return totalParentalSupervision;
	}
	/**
	* @return 
	*/
	public int getTotalPeerRelationship() {
		fetch();
		return totalPeerRelationship;
	}
	/**
	* @return 
	*/
	public int getTotalSchoolAcademic() {
		fetch();
		return totalSchoolAcademic;
	}
	/**
	* @return 
	*/
	public int getTotalSchoolAttendance() {
		fetch();
		return totalSchoolAttendance;
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
	public int getTotalSelfImage() {
		fetch();
		return totalSelfImage;
	}
	/**
	* @return 
	*/
	public int getTotalStrengths() {
		fetch();
		return totalStrengths;
	}
	/**
	* @return 
	*/
	public int getTotalSubstance() {
		fetch();
		return totalSubstance;
	}

	/**
	* @param i
	*/
	public void setTotalAbuseHistory(int i) {
		if (this.totalAbuseHistory != i) {
			markModified();
		}
		totalAbuseHistory = i;
	}
	/**
	* @param i
	*/
	public void setTotalAppearence(int i) {
		if (this.totalAppearence != i) {
			markModified();
		}
		totalAppearence = i;
	}
	/**
	* @param i
	*/
	public void setTotalBehviorHistory(int i) {
		if (this.totalBehviorHistory != i) {
			markModified();
		}
		totalBehviorHistory = i;
	}
	/**
	* @param i
	*/
	public void setTotalDevelopmental(int i) {
		if (this.totalDevelopmental != i) {
			markModified();
		}
		totalDevelopmental = i;
	}
	/**
	* @param i
	*/
	public void setTotalEducation(int i) {
		if (this.totalEducation != i) {
			markModified();
		}
		totalEducation = i;
	}
	/**
	* @param i
	*/
	public void setTotalFamilyProblems(int i) {
		if (this.totalFamilyProblems != i) {
			markModified();
		}
		totalFamilyProblems = i;
	}
	/**
	* @param i
	*/
	public void setTotalFamilyRelationship(int i) {
		if (this.totalFamilyRelationship != i) {
			markModified();
		}
		totalFamilyRelationship = i;
	}
	/**
	* @param i
	*/
	public void setTotalParentalSupervision(int i) {
		if (this.totalParentalSupervision != i) {
			markModified();
		}
		totalParentalSupervision = i;
	}
	/**
	* @param i
	*/
	public void setTotalPeerRelationship(int i) {
		if (this.totalPeerRelationship != i) {
			markModified();
		}
		totalPeerRelationship = i;
	}
	/**
	* @param i
	*/
	public void setTotalSchoolAcademic(int i) {
		if (this.totalSchoolAcademic != i) {
			markModified();
		}
		totalSchoolAcademic = i;
	}
	/**
	* @param i
	*/
	public void setTotalSchoolAttendance(int i) {
		if (this.totalSchoolAttendance != i) {
			markModified();
		}
		totalSchoolAttendance = i;
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
	public void setTotalSelfImage(int i) {
		if (this.totalSelfImage != i) {
			markModified();
		}
		totalSelfImage = i;
	}
	/**
	* @param i
	*/
	public void setTotalStrengths(int i) {
		if (this.totalStrengths != i) {
			markModified();
		}
		totalStrengths = i;
	}
	/**
	* @param i
	*/
	public void setTotalSubstance(int i) {
		if (this.totalSubstance != i) {
			markModified();
		}
		totalSubstance = i;
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
	static public RiskAnalysisTest findByRiskAnalysisId(String attributeValue) {
		IHome home = new Home();
		return (RiskAnalysisTest) home.find(RiskAnalysisConstants.RISK_ANALYSIS_ID, attributeValue, RiskAnalysisTest.class);
	}

}
