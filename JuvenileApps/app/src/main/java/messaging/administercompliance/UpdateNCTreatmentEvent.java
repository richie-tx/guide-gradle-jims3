//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCTreatmentEvent extends RequestEvent 
{
    private String referalType;
    private String serviceProviderName;
    private Date beginDate;
    private Date exitDate;
    private String dischargeResonId;
    private String treatmentId;    
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
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate The beginDate to set.
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return Returns the dischargeResonId.
	 */
	public String getDischargeResonId() {
		return dischargeResonId;
	}
	/**
	 * @param dischargeResonId The dischargeResonId to set.
	 */
	public void setDischargeResonId(String dischargeResonId) {
		this.dischargeResonId = dischargeResonId;
	}
	/**
	 * @return Returns the exitDate.
	 */
	public Date getExitDate() {
		return exitDate;
	}
	/**
	 * @param exitDate The exitDate to set.
	 */
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	/**
	 * @return Returns the referalType.
	 */
	public String getReferalType() {
		return referalType;
	}
	/**
	 * @param referalType The referalType to set.
	 */
	public void setReferalType(String referalType) {
		this.referalType = referalType;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return Returns the treatmentId.
	 */
	public String getTreatmentId() {
		return treatmentId;
	}
	/**
	 * @param treatmentId The treatmentId to set.
	 */
	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}
}
