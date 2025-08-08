/*
 * Created on Jul 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveDelinquencyHistEvent extends RequestEvent {

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
	/**
	 * @return Returns the seriousnessIndex.
	 */
	public String getSeriousnessIndex() {
		return seriousnessIndex;
	}
	/**
	 * @param seriousnessIndex The seriousnessIndex to set.
	 */
	public void setSeriousnessIndex(String seriousnessIndex) {
		this.seriousnessIndex = seriousnessIndex;
	}
	/**
	 * @return Returns the totalCapitalFelony.
	 */
	public String getTotalCapitalFelony() {
		return totalCapitalFelony;
	}
	/**
	 * @param totalCapitalFelony The totalCapitalFelony to set.
	 */
	public void setTotalCapitalFelony(String totalCapitalFelony) {
		this.totalCapitalFelony = totalCapitalFelony;
	}
	/**
	 * @return Returns the totalClassAB.
	 */
	public String getTotalClassAB() {
		return totalClassAB;
	}
	/**
	 * @param totalClassAB The totalClassAB to set.
	 */
	public void setTotalClassAB(String totalClassAB) {
		this.totalClassAB = totalClassAB;
	}
	/**
	 * @return Returns the totalClassC.
	 */
	public String getTotalClassC() {
		return totalClassC;
	}
	/**
	 * @param totalClassC The totalClassC to set.
	 */
	public void setTotalClassC(String totalClassC) {
		this.totalClassC = totalClassC;
	}
	/**
	 * @return Returns the totalFelony1.
	 */
	public String getTotalFelony1() {
		return totalFelony1;
	}
	/**
	 * @param totalFelony1 The totalFelony1 to set.
	 */
	public void setTotalFelony1(String totalFelony1) {
		this.totalFelony1 = totalFelony1;
	}
	/**
	 * @return Returns the totalFelony2.
	 */
	public String getTotalFelony2() {
		return totalFelony2;
	}
	/**
	 * @param totalFelony2 The totalFelony2 to set.
	 */
	public void setTotalFelony2(String totalFelony2) {
		this.totalFelony2 = totalFelony2;
	}
	/**
	 * @return Returns the totalFelony3.
	 */
	public String getTotalFelony3() {
		return totalFelony3;
	}
	/**
	 * @param totalFelony3 The totalFelony3 to set.
	 */
	public void setTotalFelony3(String totalFelony3) {
		this.totalFelony3 = totalFelony3;
	}
	/**
	 * @return Returns the totalLevel.
	 */
	public String getTotalLevel() {
		return totalLevel;
	}
	/**
	 * @param totalLevel The totalLevel to set.
	 */
	public void setTotalLevel(String totalLevel) {
		this.totalLevel = totalLevel;
	}
	/**
	 * @return Returns the totalOffenses.
	 */
	public String getTotalOffenses() {
		return totalOffenses;
	}
	/**
	 * @param totalOffenses The totalOffenses to set.
	 */
	public void setTotalOffenses(String totalOffenses) {
		this.totalOffenses = totalOffenses;
	}
	/**
	 * @return Returns the totalReferralsHistory.
	 */
	public String getTotalReferralsHistory() {
		return totalReferralsHistory;
	}
	/**
	 * @param totalReferralsHistory The totalReferralsHistory to set.
	 */
	public void setTotalReferralsHistory(String totalReferralsHistory) {
		this.totalReferralsHistory = totalReferralsHistory;
	}
	/**
	 * @return Returns the totalStateJailFelony.
	 */
	public String getTotalStateJailFelony() {
		return totalStateJailFelony;
	}
	/**
	 * @param totalStateJailFelony The totalStateJailFelony to set.
	 */
	public void setTotalStateJailFelony(String totalStateJailFelony) {
		this.totalStateJailFelony = totalStateJailFelony;
	}
	/**
	 * @return Returns the totalStatusCO.
	 */
	public String getTotalStatusCO() {
		return totalStatusCO;
	}
	/**
	 * @param totalStatusCO The totalStatusCO to set.
	 */
	public void setTotalStatusCO(String totalStatusCO) {
		this.totalStatusCO = totalStatusCO;
	}
}
