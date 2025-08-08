/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReferralTypeBean {

	 public String referralTypeId;
	 public String referralTypeDesc;
	 public String referralTypeCode;
	 public String referralGroupDesc;
	 public boolean selected = false;
	 public List referralFormsList = new ArrayList(); // ReferralFormBean
	 public boolean notProgressedForSP;
	 private boolean notProgressedForPgmLoc;
	 public String referralTypeNum;
	
	public String getReferralTypeCode() {
		return referralTypeCode;
	}
	public void setReferralTypeCode(String referralTypeCode) {
		this.referralTypeCode = referralTypeCode;
	}
	
	public String getReferralGroupDesc() {
		return referralGroupDesc;
	}
	public void setReferralGroupDesc(String referralGroupDesc) {
		this.referralGroupDesc = referralGroupDesc;
	}
	/**
	 * @return Returns the referralFormsList.
	 */
	public List getReferralFormsList() {
		return referralFormsList;
	}
	/**
	 * @param referralFormsList The referralFormsList to set.
	 */
	public void setReferralFormsList(List referralFormsList) {
		this.referralFormsList = referralFormsList;
	}
	/**
	 * @return Returns the referralTypeDesc.
	 */
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}
	/**
	 * @param referralTypeDesc The referralTypeDesc to set.
	 */
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
	}
	/**
	 * @return Returns the referralTypeId.
	 */
	public String getReferralTypeId() {
		return referralTypeId;
	}
	/**
	 * @param referralTypeId The referralTypeId to set.
	 */
	public void setReferralTypeId(String referralTypeId) {
		this.referralTypeId = referralTypeId;
	}
	/**
	 * @return Returns the referralTypeNum.
	 */
	public String getReferralTypeNum() {
		return referralTypeNum;
	}
	/**
	 * @param referralTypeNum The referralTypeNum to set.
	 */
	public void setReferralTypeNum(String referralTypeNum) {
		this.referralTypeNum = referralTypeNum;
	}
	/**
	 * @return Returns the selected.
	 */
	public boolean isSelected() {
		return selected;
	}
	/**
	 * @param selected The selected to set.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	/**
	 * @return the notProgressedForSP
	 */
	public boolean isNotProgressedForSP() {
		return notProgressedForSP;
	}
	/**
	 * @param notProgressedForSP the notProgressedForSP to set
	 */
	public void setNotProgressedForSP(boolean notProgressedForSP) {
		this.notProgressedForSP = notProgressedForSP;
	}
	/**
	 * @return the notProgressedForPgmLoc
	 */
	public boolean isNotProgressedForPgmLoc() {
		return notProgressedForPgmLoc;
	}
	/**
	 * @param notProgressedForPgmLoc the notProgressedForPgmLoc to set
	 */
	public void setNotProgressedForPgmLoc(boolean notProgressedForPgmLoc) {
		this.notProgressedForPgmLoc = notProgressedForPgmLoc;
	}
	
}// END Class;
