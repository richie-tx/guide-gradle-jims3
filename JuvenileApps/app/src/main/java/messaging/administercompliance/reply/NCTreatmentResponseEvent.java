/*
 * Created on Apr 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance.reply;

import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NCTreatmentResponseEvent extends CSProgramReferralResponseEvent{
	private String ncResponseId;
	private String reasonForDischargeId;
	private boolean manualAdded;


	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return the reasonForDischargeId
	 */
	public String getReasonForDischargeId() {
		return reasonForDischargeId;
	}
	/**
	 * @param reasonForDischargeId the reasonForDischargeId to set
	 */
	public void setReasonForDischargeId(String reasonForDischargeId) {
		this.reasonForDischargeId = reasonForDischargeId;
	}
}
