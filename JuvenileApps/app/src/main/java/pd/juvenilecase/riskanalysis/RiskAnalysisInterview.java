package pd.juvenilecase.riskanalysis;

import naming.RiskAnalysisConstants;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 433D86BE0210
*/
public class RiskAnalysisInterview extends PersistentObject {
	
	public int totalSchool;
	/**
	* Properties for riskAnalysis
	* @referencedType pd.juvenilecase.RiskAnalysis
	* @detailerDoNotGenerate false
	*/
	private RiskAnalysis riskAnalysis = null;
	public int totalFamily;
	public int totalDeceasedParents;
	public int totalSiblings;
	public int totalChildHomeAttitude;
	public int totalGradesRepeated;
	public int totalFamilyDynamics;
	public int totalChild;
	public int totalSchoolBehavior;
	public int totalExpulsions;
	public int totalSuspensions;
	public String riskAnalysisIntvId = "";
	private String riskAnalysisId;
	public int totalFailingClasses;
	public int onsetAge;
	public String sexCd;
	
	/**
	* @roseuid 433D86BE0210
	*/
	public RiskAnalysisInterview() {
	}
	static public RiskAnalysisInterview find(String assessmentId) {
		IHome home = new Home();
		return (RiskAnalysisInterview) home.find(assessmentId, RiskAnalysisInterview.class);
	}
	
	/**
	* Finds all RiskResponse by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public RiskAnalysisInterview findByRiskAnalysisId(String attributeValue) {
		IHome home = new Home();
		return (RiskAnalysisInterview)home.find(RiskAnalysisConstants.RISK_ANALYSIS_ID, attributeValue, RiskAnalysisInterview.class);
	}
	
	/**
	* @roseuid 433C3D3D001C
	*/
	public void bind() {
		markModified();
	}
	/**
	* @return 
	*/
	public int getOnsetAge() {
		fetch();
		return onsetAge;
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
	public int getTotalChildHomeAttitude() {
		fetch();
		return totalChildHomeAttitude;
	}
	/**
	* @return 
	*/
	public int getTotalDeceasedParents() {
		fetch();
		return totalDeceasedParents;
	}
	/**
	* @return 
	*/
	public int getTotalExpulsions() {
		fetch();
		return totalExpulsions;
	}
	/**
	* @return 
	*/
	public int getTotalFailingClasses() {
		fetch();
		return totalFailingClasses;
	}
	/**
	* @return 
	*/
	public int getTotalFamily() {
		fetch();
		return totalFamily;
	}
	/**
	* @return 
	*/
	public int getTotalFamilyDynamics() {
		fetch();
		return totalFamilyDynamics;
	}
	/**
	* @return 
	*/
	public int getTotalGradesRepeated() {
		fetch();
		return totalGradesRepeated;
	}
	/**
	* @return 
	*/
	public int getTotalSchool() {
		fetch();
		return totalSchool;
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
	public int getTotalSiblings() {
		fetch();
		return totalSiblings;
	}
	/**
	* @return 
	*/
	public int getTotalSuspensions() {
		fetch();
		return totalSuspensions;
	}
	/**
	* @param i
	*/
	public void setOnsetAge(int i) {
		if (this.onsetAge != i) {
			markModified();
		}
		onsetAge = i;
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
	public void setTotalChildHomeAttitude(int i) {
		if (this.totalChildHomeAttitude != i) {
			markModified();
		}
		totalChildHomeAttitude = i;
	}
	/**
	* @param i
	*/
	public void setTotalDeceasedParents(int i) {
		if (this.totalDeceasedParents != i) {
			markModified();
		}
		totalDeceasedParents = i;
	}
	/**
	* @param i
	*/
	public void setTotalExpulsions(int i) {
		if (this.totalExpulsions != i) {
			markModified();
		}
		totalExpulsions = i;
	}
	/**
	* @param i
	*/
	public void setTotalFailingClasses(int i) {
		if (this.totalFailingClasses != i) {
			markModified();
		}
		totalFailingClasses = i;
	}
	/**
	* @param i
	*/
	public void setTotalFamily(int i) {
		if (this.totalFamily != i) {
			markModified();
		}
		totalFamily = i;
	}
	/**
	* @param i
	*/
	public void setTotalFamilyDynamics(int i) {
		if (this.totalFamilyDynamics != i) {
			markModified();
		}
		totalFamilyDynamics = i;
	}
	/**
	* @param i
	*/
	public void setTotalGradesRepeated(int i) {
		if (this.totalGradesRepeated != i) {
			markModified();
		}
		totalGradesRepeated = i;
	}
	/**
	* @param i
	*/
	public void setTotalSchool(int i) {
		if (this.totalSchool != i) {
			markModified();
		}
		totalSchool = i;
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
	public void setTotalSiblings(int i) {
		if (this.totalSiblings != i) {
			markModified();
		}
		totalSiblings = i;
	}
	/**
	* @param i
	*/
	public void setTotalSuspensions(int i) {
		if (this.totalSuspensions != i) {
			markModified();
		}
		totalSuspensions = i;
	}

	/**
	* @return 
	*/
	public RiskAnalysis getRiskAnalysis() {
		initRiskAnalysis();
		fetch();
		return riskAnalysis;
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
	public String getSexCd() {
		fetch();
		return sexCd;
	}
	
	/**
	 * @param string
	 */
	public void setSexCd(String string) {
		if (this.sexCd == null || !this.sexCd.equals(string)) {
			markModified();
		}
		sexCd = string;
	}
	
}
