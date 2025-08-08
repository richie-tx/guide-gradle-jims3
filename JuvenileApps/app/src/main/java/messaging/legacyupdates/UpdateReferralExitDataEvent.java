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
public class UpdateReferralExitDataEvent extends LegacyUpdatesRequestEvent {
    private String action;
    private String ctsCode;
    private String designator;
    private String dischargeReason;
    private Date progBeginDate;
    private Date progEnDate;
    private String recType;
    
	public String getAction() {
		return action;
	}
	public String getCtsCode() {
		return ctsCode;
	}
	public String getDesignator() {
		return designator;
	}
	public String getDischargeReason() {
		return dischargeReason;
	}
	public Date getProgBeginDate() {
		return progBeginDate;
	}
	public Date getProgEnDate() {
		return progEnDate;
	}
	public String getRecType() {
		return recType;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setCtsCode(String ctsCode) {
		this.ctsCode = ctsCode;
	}
	public void setDesignator(String designator) {
		this.designator = designator;
	}
	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}
	public void setProgBeginDate(Date progBeginDate) {
		this.progBeginDate = progBeginDate;
	}
	public void setProgEnDate(Date progEnDate) {
		this.progEnDate = progEnDate;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
}
