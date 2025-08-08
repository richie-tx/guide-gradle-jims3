/*
 * Created on Dec 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.legacyupdates;

import java.util.Date;



/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateReferralSubmitDataEvent extends LegacyUpdatesRequestEvent {
    private String action;
    private int confineDays;
    private int confineMonths;
    private int confineYears;
    private boolean contractProg;
    private String ctsCode;
    private String pfsCode;
    private String designator;
    private boolean inHouse;
    private String placementReason;
    private Date progBeginDate;
    private String recType;
    
	public String getAction() {
		return action;
	}
	/**
	 * @return the confineDays
	 */
	public int getConfineDays() {
		return confineDays;
	}
	/**
	 * @return the confineMonths
	 */
	public int getConfineMonths() {
		return confineMonths;
	}
	/**
	 * @return the confineYears
	 */
	public int getConfineYears() {
		return confineYears;
	}
	/**
	 * @return the ctsCode
	 */
	public String getCtsCode() {
		return ctsCode;
	}
	/**
	 * 
	 * @return
	 */
	public String getPfsCode() {
		return pfsCode;
	}
	/**
	 * 
	 * @param pfsCode
	 */
	public void setPfsCode(String pfsCode) {
		this.pfsCode = pfsCode;
	}
	/**
	 * @return the designator
	 */
	public String getDesignator() {
		return designator;
	}
	/**
	 * @return the placementReason
	 */
	public String getPlacementReason() {
		return placementReason;
	}
	/**
	 * @return the progBeginDate
	 */
	public Date getProgBeginDate() {
		return progBeginDate;
	}
	public String getRecType() {
		return recType;
	}
	/**
	 * @return the contractProg
	 */
	public boolean isContractProg() {
		return contractProg;
	}
	/**
	 * @return the inHouse
	 */
	public boolean isInHouse() {
		return inHouse;
	}
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @param confineDays the confineDays to set
	 */
	public void setConfineDays(int confineDays) {
		this.confineDays = confineDays;
	}
	/**
	 * @param confineMonths the confineMonths to set
	 */
	public void setConfineMonths(int confineMonths) {
		this.confineMonths = confineMonths;
	}
	/**
	 * @param confineYears the confineYears to set
	 */
	public void setConfineYears(int confineYears) {
		this.confineYears = confineYears;
	}
	/**
	 * @param contractProg the contractProg to set
	 */
	public void setContractProg(boolean contractProg) {
		this.contractProg = contractProg;
	}
	/**
	 * @param ctsCode the ctsCode to set
	 */
	public void setCtsCode(String ctsCode) {
		this.ctsCode = ctsCode;
	}
	/**
	 * @param designator the designator to set
	 */
	public void setDesignator(String designator) {
		this.designator = designator;
	}
	/**
	 * @param inHouse the inHouse to set
	 */
	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
	}
	/**
	 * @param placementReason the placementReason to set
	 */
	public void setPlacementReason(String placementReason) {
		this.placementReason = placementReason;
	}
	/**
	 * @param progBeginDate the progBeginDate to set
	 */
	public void setProgBeginDate(Date progBeginDate) {
		this.progBeginDate = progBeginDate;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
}
