package pd.juvenilecase.riskanalysis;

import messaging.juvenilecase.reply.JuvenileDeliquencyHistoryEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.RiskAnalysisConstants;

/**
* @roseuid 434C00C201D7
*/
public class JuvenileDelinquencyHistory extends PersistentObject {
	
	private RiskAnalysis riskAnalysis = null;
	private String riskAnalysisId;
	private int ageFirstReferral;
	private int jailFelonyTotal;
	private int felony3Total;
	private int levelTotal;
	private int misdABTotal;
	private int referralHistoryTotal;
	private int totalOffenses;
	private int felony1Total;
	private int capFelonyTotal;
//	private String juvenileNumber;
	private int scoOffensesTotal;
	private int misdCTotal;
	private int felony2Total;
	private int seriousnessIndex;

	/**
	* @roseuid 434C00C201D7
	*/
	public JuvenileDelinquencyHistory() {
	}
	/**
	* @param juvenileNumber
	* @roseuid 4346D35E00B5
	*/
	public static JuvenileDelinquencyHistory findbyRiskAnalysisId(String riskAnalysisId) {
		IHome home = new Home();
		return (JuvenileDelinquencyHistory) home.find(RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysisId, JuvenileDelinquencyHistory.class);
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
	public int getCapFelonyTotal() {
		fetch();
		return capFelonyTotal;
	}
	/**
	* @return 
	*/
	public int getFelony1Total() {
		fetch();
		return felony1Total;
	}
	/**
	* @return 
	*/
	public int getFelony2Total() {
		fetch();
		return felony2Total;
	}
	/**
	* @return 
	*/
	public int getFelony3Total() {
		fetch();
		return felony3Total;
	}
	/**
	* @return 
	*/
	public int getJailFelonyTotal() {
		fetch();
		return jailFelonyTotal;
	}
	/**
	* @return 
	*/
//	public String getJuvenileNumber() {
//		fetch();
//		return juvenileNumber;
//	}
	/**
	* @return 
	*/
	public int getLevelTotal() {
		fetch();
		return levelTotal;
	}
	/**
	* @return 
	*/
	public int getMisdABTotal() {
		fetch();
		return misdABTotal;
	}
	/**
	* @return 
	*/
	public int getMisdCTotal() {
		fetch();
		return misdCTotal;
	}
	/**
	* @return 
	*/
	public int getReferralHistoryTotal() {
		fetch();
		return referralHistoryTotal;
	}
	/**
	* @return 
	*/
	public int getScoOffensesTotal() {
		fetch();
		return scoOffensesTotal;
	}
	/**
	* @return 
	*/
	public int getTotalOffenses() {
		fetch();
		return totalOffenses;
	}
	/**
	* @param i
	*/
	public void setCapFelonyTotal(int i) {
		if (this.capFelonyTotal != i) {
			markModified();
		}
		capFelonyTotal = i;
	}
	/**
	* @param i
	*/
	public void setFelony1Total(int i) {
		if (this.felony1Total != i) {
			markModified();
		}
		felony1Total = i;
	}
	/**
	* @param i
	*/
	public void setFelony2Total(int i) {
		if (this.felony2Total != i) {
			markModified();
		}
		felony2Total = i;
	}
	/**
	* @param i
	*/
	public void setFelony3Total(int i) {
		if (this.felony3Total != i) {
			markModified();
		}
		felony3Total = i;
	}
	/**
	* @param i
	*/
	public void setJailFelonyTotal(int i) {
		if (this.jailFelonyTotal != i) {
			markModified();
		}
		jailFelonyTotal = i;
	}
	/**
	* @param string
	*/
//	public void setJuvenileNumber(String string) {
//		if (this.juvenileNumber == null || !this.juvenileNumber.equals(string)) {
//			markModified();
//		}
//		juvenileNumber = string;
//	}
	/**
	* @param i
	*/
	public void setLevelTotal(int i) {
		if (this.levelTotal != i) {
			markModified();
		}
		levelTotal = i;
	}
	/**
	* @param i
	*/
	public void setMisdABTotal(int i) {
		if (this.misdABTotal != i) {
			markModified();
		}
		misdABTotal = i;
	}
	/**
	* @param i
	*/
	public void setMisdCTotal(int i) {
		if (this.misdCTotal != i) {
			markModified();
		}
		misdCTotal = i;
	}
	/**
	* @param i
	*/
	public void setReferralHistoryTotal(int i) {
		if (this.referralHistoryTotal != i) {
			markModified();
		}
		referralHistoryTotal = i;
	}
	/**
	* @param i
	*/
	public void setScoOffensesTotal(int i) {
		if (this.scoOffensesTotal != i) {
			markModified();
		}
		scoOffensesTotal = i;
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
	 * @return
	 */
	public int getSeriousnessIndex()
	{
		fetch();
		return seriousnessIndex;
	}

	/**
	 * @param i
	 */
	public void setSeriousnessIndex(int i)
	{
		if (this.seriousnessIndex != i) {
			markModified();
		}
		seriousnessIndex = i;
	}

	/**
	 * @return
	 */
	public int getAgeFirstReferral()
	{
		return ageFirstReferral;
	}

	/**
	 * @param i
	 */
	public void setAgeFirstReferral(int i)
	{
		ageFirstReferral = i;
	}

	public JuvenileDeliquencyHistoryEvent getResponseEvent(){
		JuvenileDeliquencyHistoryEvent myRespEvt=new JuvenileDeliquencyHistoryEvent();
		myRespEvt.setAgeFirstReferred(new Integer(this.getAgeFirstReferral()).toString());
		if(this.getOID()!=null)
			myRespEvt.setJuvDeliquencyHistoryId(this.getOID().toString());
	//	myRespEvt.setJuvenileId(this.getJuvenileNumber());
		myRespEvt.setRiskAnalysisId(this.getRiskAnalysisId());
		myRespEvt.setSeriousnessIndex(new Integer(this.getSeriousnessIndex()).toString());
		myRespEvt.setTotalCapitalFelony(new Integer(this.getCapFelonyTotal()).toString());
		myRespEvt.setTotalClassAB(new Integer(this.getMisdABTotal()).toString());
		myRespEvt.setTotalClassC(new Integer(this.getMisdCTotal()).toString());
		myRespEvt.setTotalFelony1(new Integer(this.getFelony1Total()).toString());
		myRespEvt.setTotalFelony2(new Integer(this.getFelony2Total()).toString());
		myRespEvt.setTotalFelony3(new Integer(this.getFelony3Total()).toString());
		myRespEvt.setTotalLevel(new Integer(this.getLevelTotal()).toString());
		myRespEvt.setTotalOffenses(new Integer(this.getTotalOffenses()).toString());
		myRespEvt.setTotalReferralsHistory(new Integer(this.getReferralHistoryTotal()).toString());
		myRespEvt.setTotalStateJailFelony(new Integer(this.getJailFelonyTotal()).toString());
		myRespEvt.setTotalStatusCO(new Integer(this.getScoOffensesTotal()).toString());
		return myRespEvt;
	}
}
