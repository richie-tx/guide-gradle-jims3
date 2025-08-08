/*
 * Created on Mar 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar;

import java.util.Date;
import java.util.List;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSGroupOVBean {
	private String[] superviseeIds;
	private List supervisees;
	
	private Date officeVisitDate;
	private String officeVisitName;
	private String startTime;
	private String endTime;
	private String purpose;
	private String outcomeCd;
	private String narrative;
	
	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return Returns the narrative.
	 */
	public String getNarrative() {
		return narrative;
	}
	/**
	 * @param narrative The narrative to set.
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	/**
	 * @return Returns the officeVisitDate.
	 */
	public Date getOfficeVisitDate() {
		return officeVisitDate;
	}
	/**
	 * @param officeVisitDate The officeVisitDate to set.
	 */
	public void setOfficeVisitDate(Date officeVisitDate) {
		this.officeVisitDate = officeVisitDate;
	}
	/**
	 * @return Returns the officeVisitName.
	 */
	public String getOfficeVisitName() {
		return officeVisitName;
	}
	/**
	 * @param officeVisitName The officeVisitName to set.
	 */
	public void setOfficeVisitName(String officeVisitName) {
		this.officeVisitName = officeVisitName;
	}
	/**
	 * @return Returns the outcomeCd.
	 */
	public String getOutcomeCd() {
		return outcomeCd;
	}
	/**
	 * @param outcomeCd The outcomeCd to set.
	 */
	public void setOutcomeCd(String outcomeCd) {
		this.outcomeCd = outcomeCd;
	}
	/**
	 * @return Returns the purpose.
	 */
	public String getPurpose() {
		return purpose;
	}
	/**
	 * @param purpose The purpose to set.
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return Returns the superviseeIds.
	 */
	public String[] getSuperviseeIds() {
		return superviseeIds;
	}
	/**
	 * @param superviseeIds The superviseeIds to set.
	 */
	public void setSuperviseeIds(String[] superviseeIds) {
		this.superviseeIds = superviseeIds;
	}
	/**
	 * @return Returns the supervisees.
	 */
	public List getSupervisees() {
		return supervisees;
	}
	/**
	 * @param supervisees The supervisees to set.
	 */
	public void setSupervisees(List supervisees) {
		this.supervisees = supervisees;
	}
}
