/*
 * Created on Oct 10, 2005
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
public class JuvenileDeliquencyHistoryEvent extends ResponseEvent
{
	private String juvDeliquencyHistoryId = "";
	private String juvenileId = "";
	
	private String riskAnalysisId="";
	
	private String addtlCharges="";
	private String custodyStatus="";
	private String jpReferral="";
	private String onProbation="";
	private String vopPendingCourt="";
	private String totalAttitude="";
	private String totalCurrentStat="";
	private String totalOffenseNature="";
	private String totalSupervision="";
	
	
	private String ageFirstReferred="";
	private String totalFelony1= "";
	private String totalFelony2= "";
	private String totalFelony3= "";
	private String totalLevel= "";
	private String totalClassC= "";
	private String totalClassAB= "";
	private String totalCapitalFelony = "";
	private String seriousnessIndex="";
	private String totalOffenses= "";
	private String totalReferralsHistory= "";
	private String totalStateJailFelony= "";
	private String totalStatusCO= "";	
	
	
	/**
	 * @return
	 */
	public String getJuvDeliquencyHistoryId()
	{
		return juvDeliquencyHistoryId;
	}

	/**
	 * @return
	 */
	public String getJuvenileId()
	{
		return juvenileId;
	}

	/**
	 * @return
	 */
	public String getTotalCapitalFelony()
	{
		return totalCapitalFelony;
	}

	/**
	 * @return
	 */
	public String getTotalClassAB()
	{
		return totalClassAB;
	}

	/**
	 * @return
	 */
	public String getTotalClassC()
	{
		return totalClassC;
	}

	/**
	 * @return
	 */
	public String getTotalFelony1()
	{
		return totalFelony1;
	}

	/**
	 * @return
	 */
	public String getTotalFelony2()
	{
		return totalFelony2;
	}

	/**
	 * @return
	 */
	public String getTotalFelony3()
	{
		return totalFelony3;
	}

	/**
	 * @return
	 */
	public String getTotalLevel()
	{
		return totalLevel;
	}

	/**
	 * @return
	 */
	public String getTotalOffenses()
	{
		return totalOffenses;
	}

	/**
	 * @return
	 */
	public String getTotalReferralsHistory()
	{
		return totalReferralsHistory;
	}

	/**
	 * @return
	 */
	public String getTotalStateJailFelony()
	{
		return totalStateJailFelony;
	}

	/**
	 * @return
	 */
	public String getTotalStatusCO()
	{
		return totalStatusCO;
	}

	/**
	 * @param string
	 */
	public void setJuvDeliquencyHistoryId(final String string)
	{
		if(string != null)
		juvDeliquencyHistoryId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileId(final String string)
	{
		if(string != null)
		juvenileId = string;
	}

	/**
	 * @param string
	 */
	public void setTotalCapitalFelony(final String string)
	{
		if(string != null)
		totalCapitalFelony = string;
	}

	/**
	 * @param string
	 */
	public void setTotalClassAB(final String string)
	{
		if(string != null)
		totalClassAB = string;
	}

	/**
	 * @param string
	 */
	public void setTotalClassC(final String string)
	{
		if(string != null)
		totalClassC = string;
	}

	/**
	 * @param string
	 */
	public void setTotalFelony1(final String string)
	{
		if(string != null)
		totalFelony1 = string;
	}

	/**
	 * @param string
	 */
	public void setTotalFelony2(final String string)
	{
		if(string != null)
		totalFelony2 = string;
	}

	/**
	 * @param string
	 */
	public void setTotalFelony3(final String string)
	{
		if(string != null)
		totalFelony3 = string;
	}

	/**
	 * @param string
	 */
	public void setTotalLevel(final String string)
	{
		if(string != null)
		totalLevel = string;
	}

	/**
	 * @param string
	 */
	public void setTotalOffenses(final String string)
	{
		if(string != null)
		totalOffenses = string;
	}

	/**
	 * @param string
	 */
	public void setTotalReferralsHistory(final String string)
	{
		if(string != null)
		totalReferralsHistory = string;
	}

	/**
	 * @param string
	 */
	public void setTotalStateJailFelony(final String string)
	{
		if(string != null)
		totalStateJailFelony = string;
	}

	/**
	 * @param string
	 */
	public void setTotalStatusCO(final String string)
	{
		if(string != null)
		totalStatusCO = string;
	}

	/**
	 * @return
	 */
	public String getSeriousnessIndex()
	{
		return seriousnessIndex;
	}

	/**
	 * @param i
	 */
	public void setSeriousnessIndex(String i)
	{
		seriousnessIndex = i;
	}

	/**
	 * @return Returns the addtlCharges.
	 */
	public String getAddtlCharges() {
		return addtlCharges;
	}
	/**
	 * @param addtlCharges The addtlCharges to set.
	 */
	public void setAddtlCharges(String addtlCharges) {
		this.addtlCharges = addtlCharges;
	}
	/**
	 * @return Returns the custodyStatus.
	 */
	public String getCustodyStatus() {
		return custodyStatus;
	}
	/**
	 * @param custodyStatus The custodyStatus to set.
	 */
	public void setCustodyStatus(String custodyStatus) {
		this.custodyStatus = custodyStatus;
	}
	/**
	 * @return Returns the jpReferral.
	 */
	public String getJpReferral() {
		return jpReferral;
	}
	/**
	 * @param jpReferral The jpReferral to set.
	 */
	public void setJpReferral(String jpReferral) {
		this.jpReferral = jpReferral;
	}
	/**
	 * @return Returns the onProbation.
	 */
	public String getOnProbation() {
		return onProbation;
	}
	/**
	 * @param onProbation The onProbation to set.
	 */
	public void setOnProbation(String onProbation) {
		this.onProbation = onProbation;
	}
	/**
	 * @return Returns the riskAnalysisId.
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}
	/**
	 * @param riskAnalysisId The riskAnalysisId to set.
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}
	/**
	 * @return Returns the totalAttitude.
	 */
	public String getTotalAttitude() {
		return totalAttitude;
	}
	/**
	 * @param totalAttitude The totalAttitude to set.
	 */
	public void setTotalAttitude(String totalAttitude) {
		this.totalAttitude = totalAttitude;
	}
	/**
	 * @return Returns the totalCurrentStat.
	 */
	public String getTotalCurrentStat() {
		return totalCurrentStat;
	}
	/**
	 * @param totalCurrentStat The totalCurrentStat to set.
	 */
	public void setTotalCurrentStat(String totalCurrentStat) {
		this.totalCurrentStat = totalCurrentStat;
	}
	/**
	 * @return Returns the totalOffenseNature.
	 */
	public String getTotalOffenseNature() {
		return totalOffenseNature;
	}
	/**
	 * @param totalOffenseNature The totalOffenseNature to set.
	 */
	public void setTotalOffenseNature(String totalOffenseNature) {
		this.totalOffenseNature = totalOffenseNature;
	}
	/**
	 * @return Returns the totalSupervision.
	 */
	public String getTotalSupervision() {
		return totalSupervision;
	}
	/**
	 * @param totalSupervision The totalSupervision to set.
	 */
	public void setTotalSupervision(String totalSupervision) {
		this.totalSupervision = totalSupervision;
	}
	/**
	 * @return Returns the vopPendingCourt.
	 */
	public String getVopPendingCourt() {
		return vopPendingCourt;
	}
	/**
	 * @param vopPendingCourt The vopPendingCourt to set.
	 */
	public void setVopPendingCourt(String vopPendingCourt) {
		this.vopPendingCourt = vopPendingCourt;
	}
	/**
	 * @return Returns the ageFirstReferred.
	 */
	public String getAgeFirstReferred() {
		return ageFirstReferred;
	}
	/**
	 * @param ageFirstReferred The ageFirstReferred to set.
	 */
	public void setAgeFirstReferred(String ageFirstReferred) {
		this.ageFirstReferred = ageFirstReferred;
	}
}
