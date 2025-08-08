//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import java.sql.Timestamp;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCPositiveUAEvent extends RequestEvent 
{
	
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
	 * @return Returns the occurenceDate.
	 */
	public Timestamp getOccurenceDate() {
		return occurenceDate;
	}
	/**
	 * @param occurenceDate The occurenceDate to set.
	 */
	public void setOccurenceDate(Timestamp occurenceDate) {
		this.occurenceDate = occurenceDate;
	}
	/**
	 * @return Returns the positiveUAId.
	 */
	public String getPositiveUAId() {
		return positiveUAId;
	}
	/**
	 * @param positiveUAId The positiveUAId to set.
	 */
	public void setPositiveUAId(String positiveUAId) {
		this.positiveUAId = positiveUAId;
	}
	/**
	 * @return Returns the substance.
	 */
	public String getSubstance() {
		return substance;
	}
	/**
	 * @param substance The substance to set.
	 */
	public void setSubstance(String substance) {
		this.substance = substance;
	}
	
    private Timestamp occurenceDate;
    private String substance;
    private String positiveUAId;
    private boolean manualAdded;
}
