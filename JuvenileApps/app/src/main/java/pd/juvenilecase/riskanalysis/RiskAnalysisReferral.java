package pd.juvenilecase.riskanalysis;

import java.util.Iterator;

import naming.RiskAnalysisConstants;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* Properties for riskAnalysis
* @referencedType pd.juvenilecase.RiskAnalysis
* @detailerDoNotGenerate false
*/
/**
 * @author PAlcocer
 *
 */
public class RiskAnalysisReferral extends PersistentObject {
	/**
	* Properties for riskAnalysis
	* @referencedType pd.juvenilecase.RiskAnalysis
	* @detailerDoNotGenerate false
	*/
	private RiskAnalysis riskAnalysis = null;
	private int totalOffenses;
	private boolean jPReferral;
	private int totalOffenseNature;
	private int totalClassAB;
	private int additionalCharges; //
	private int totalStateJailFelony;
	private int totalStatusCO;
	private boolean vOPPendingCourt; //
	private int totalCapitalFelony;
	private int seriousnessIndex;
	private int totalLevel;
	private int referralNumber;
	private int totalReferralsHistory;
	private boolean warrant;
	private boolean pendingCourt; //
	private boolean onProbation; //
	private int totalFelony3;
	private int totalFelony2;
	private int totalFelony1;
	private int totalCurrentStatus;
	private String custodyStatus;
	private int totalClassC;
	private int totalAttitude;
	private String riskAnalysisId;
	private int totalSupervision;
	private boolean newReferral;
	private String riskMandatoryDetentionCd;
	private boolean moreThanOneFailure;
	
	/**
	* @roseuid 433D86CB00B0
	*/
	public RiskAnalysisReferral() {
	}
	/**
	* @roseuid 433C3D3D001C
	*/
	public void bind() {
		markModified();
	}
	/**
	* @return RiskAnalysisReferral
	* @param juvenileNum
	*/
	static public RiskAnalysisReferral find(String assessmentId) {
		IHome home = new Home();
		return (RiskAnalysisReferral) home.find(assessmentId, RiskAnalysisReferral.class);
	}
	
	/**
	* Finds all RiskResponse by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public RiskAnalysisReferral findByRiskAnalysisId(String attributeValue) {
		IHome home = new Home();
		return (RiskAnalysisReferral)home.find(RiskAnalysisConstants.RISK_ANALYSIS_ID, attributeValue, RiskAnalysisReferral.class);
	}
	
	/**
	* @return 
	*/
	public int getAdditionalCharges() {
		fetch();
		return additionalCharges;
	}
	/**
	* @return 
	*/
	public String getCustodyStatus() {
		fetch();
		return custodyStatus;
	}
	/**
	* @return 
	*/
	public boolean isJPReferral() {
		fetch();
		return jPReferral;
	}

	/**
	* @return 
	*/
	public boolean isPendingCourt() {
		fetch();
		return pendingCourt;
	}
	/**
	* @return 
	*/
	public int getReferralNumber() {
		fetch();
		return referralNumber;
	}
	/**
	* @return 
	*/
	public int getSeriousnessIndex() {
		fetch();
		return seriousnessIndex;
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
	public int getTotalCapitalFelony() {
		fetch();
		return totalCapitalFelony;
	}
	/**
	* @return 
	*/
	public int getTotalClassAB() {
		fetch();
		return totalClassAB;
	}
	/**
	* @return 
	*/
	public int getTotalClassC() {
		fetch();
		return totalClassC;
	}
	/**
	* @return 
	*/
	public int getTotalCurrentStatus() {
		fetch();
		return totalCurrentStatus;
	}
	/**
	* @return 
	*/
	public int getTotalFelony1() {
		fetch();
		return totalFelony1;
	}
	/**
	* @return 
	*/
	public int getTotalFelony2() {
		fetch();
		return totalFelony2;
	}
	/**
	* @return 
	*/
	public int getTotalFelony3() {
		fetch();
		return totalFelony3;
	}
	/**
	* @return 
	*/
	public int getTotalLevel() {
		fetch();
		return totalLevel;
	}
	/**
	* @return 
	*/
	public int getTotalOffenseNature() {
		fetch();
		return totalOffenseNature;
	}
	/**
	* @return 
	*/
	public int getTotalOffenses() {
		fetch();
		return totalOffenses;
	}
	/**
	* @return 
	*/
	public int getTotalReferralsHistory() {
		fetch();
		return totalReferralsHistory;
	}
	/**
	* @return 
	*/
	public int getTotalStateJailFelony() {
		fetch();
		return totalStateJailFelony;
	}
	/**
	* @return 
	*/
	public int getTotalStatusCO() {
		fetch();
		return totalStatusCO;
	}
	/**
	* @return 
	*/
	public int getTotalSupervision() {
		fetch();
		return totalSupervision;
	}
	/**
	* @return 
	*/
	public boolean isVOPPendingCourt() {
		fetch();
		return vOPPendingCourt;
	}
	/**
	* @return 
	*/
	public boolean isWarrant() {
		fetch();
		return warrant;
	}
	/**
	* @param i
	*/
	public void setAdditionalCharges(int i) {
		if (this.additionalCharges != i) {
			markModified();
		}
		additionalCharges = i;
	}
	/**
	* @param string
	*/
	public void setCustodyStatus(String string) {
		if (string != null && this.custodyStatus != null && !this.custodyStatus.equals(string)) {
			markModified();
		}
		custodyStatus = string;
	}
	/**
	* @param b
	*/
	public void setJPReferral(boolean b) {
		if (this.jPReferral != b) {
			markModified();
		}
		jPReferral = b;
	}
	
	/**
	* @param b
	*/
	public void setPendingCourt(boolean b) {
		if (this.pendingCourt != b) {
			markModified();
		}
		pendingCourt = b;
	}
	/**
	* @param i
	*/
	public void setReferralNumber(int i) {
		if (this.referralNumber != i) {
			markModified();
		}
		referralNumber = i;
	}
	/**
	* @param i
	*/
	public void setSeriousnessIndex(int i) {
		if (this.seriousnessIndex != i) {
			markModified();
		}
		seriousnessIndex = i;
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
	public void setTotalCapitalFelony(int i) {
		if (this.totalCapitalFelony != i) {
			markModified();
		}
		totalCapitalFelony = i;
	}
	/**
	* @param i
	*/
	public void setTotalClassAB(int i) {
		if (this.totalClassAB != i) {
			markModified();
		}
		totalClassAB = i;
	}
	/**
	* @param i
	*/
	public void setTotalClassC(int i) {
		if (this.totalClassC != i) {
			markModified();
		}
		totalClassC = i;
	}
	/**
	* @param i
	*/
	public void setTotalCurrentStatus(int i) {
		if (this.totalCurrentStatus != i) {
			markModified();
		}
		totalCurrentStatus = i;
	}
	/**
	* @param i
	*/
	public void setTotalFelony1(int i) {
		if (this.totalFelony1 != i) {
			markModified();
		}
		totalFelony1 = i;
	}
	/**
	* @param i
	*/
	public void setTotalFelony2(int i) {
		if (this.totalFelony2 != i) {
			markModified();
		}
		totalFelony2 = i;
	}
	/**
	* @param i
	*/
	public void setTotalFelony3(int i) {
		if (this.totalFelony3 != i) {
			markModified();
		}
		totalFelony3 = i;
	}
	/**
	* @param i
	*/
	public void setTotalLevel(int i) {
		if (this.totalLevel != i) {
			markModified();
		}
		totalLevel = i;
	}
	/**
	* @param i
	*/
	public void setTotalOffenseNature(int i) {
		if (this.totalOffenseNature != i) {
			markModified();
		}
		totalOffenseNature = i;
	}
	/**
	* @param i
	*/
	public void setTotalOffenses(int i) {
		if (this.totalOffenses != i) {
			markModified();
		}
		totalOffenses = i;
	}
	/**
	* @param i
	*/
	public void setTotalReferralsHistory(int i) {
		if (this.totalReferralsHistory != i) {
			markModified();
		}
		totalReferralsHistory = i;
	}
	/**
	* @param i
	*/
	public void setTotalStateJailFelony(int i) {
		if (this.totalStateJailFelony != i) {
			markModified();
		}
		totalStateJailFelony = i;
	}
	/**
	* @param i
	*/
	public void setTotalStatusCO(int i) {
		if (this.totalStatusCO != i) {
			markModified();
		}
		totalStatusCO = i;
	}
	/**
	* @param i
	*/
	public void setTotalSupervision(int i) {
		if (this.totalSupervision != i) {
			markModified();
		}
		totalSupervision = i;
	}
	/**
	* @param b
	*/
	public void setVOPPendingCourt(boolean b) {
		markModified();
		vOPPendingCourt = b;
	}
	/**
	* @param b
	*/
	public void setWarrant(boolean b) {
		if (this.warrant != b) {
			markModified();
		}
		warrant = b;
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
	public boolean isOnProbation()
	{
		fetch();
		return onProbation;
	}

	/**
	 * @param b
	 */
	public void setOnProbation(boolean b)
	{
		if (this.onProbation != b) {
			markModified();
		}
		onProbation = b;
	}
	
	/**
	 * @return newReferral
	 */
	public boolean isNewReferral() {
		
		fetch();
		return newReferral;
	}
	/**
	 * @param newReferral
	 */
	public void setNewReferral(boolean newReferral) {
		if (this.newReferral != newReferral) {
			markModified();
		}
		this.newReferral = newReferral;
	}
	
	/**
	 * @return mandatoryDetentionCd
	 */
	public String getRiskMandatoryDetentionCd() {
		fetch();
		return riskMandatoryDetentionCd;
	}
	
	/**
	 * @param mandatoryDetentionCd
	 */
	public void setRiskMandatoryDetentionCd(String string) {
		if (this.riskMandatoryDetentionCd == null || !this.riskMandatoryDetentionCd.equals(string)) {
			markModified();
		}
		riskMandatoryDetentionCd = string;
	}	
	
	/**
	 * @return newReferral
	 */
	public boolean isMoreThanOneFailure() {
		
		fetch();
		return moreThanOneFailure;
	}
	/**
	 * @param newReferral
	 */
	public void setMoreThanOneFailure(boolean moreThanOneFailure) {
		if (this.moreThanOneFailure != moreThanOneFailure) {
			markModified();
		}
		this.moreThanOneFailure = moreThanOneFailure;
	}
	
}
