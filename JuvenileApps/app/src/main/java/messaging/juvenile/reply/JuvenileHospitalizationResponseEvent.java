/*
 * Created on Apr 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileHospitalizationResponseEvent extends ResponseEvent implements Comparable{

	private Date entryDate;
	private String hospitalizationId;
	private Date admissionDate;
	private String facilityName;
	private String hospitalizationReason;
	private Date releaseDate;
	private String physicianName;
	private String physicianPhoneNum;
	private String juvenileNum;
	private String admissionTypeId;
	
	public JuvenileHospitalizationResponseEvent(){}
	/**
	 * @return Returns the admissionDate.
	 */
	public Date getAdmissionDate() {
		return admissionDate;
	}
	/**
	 * @param admissionDate The admissionDate to set.
	 */
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return Returns the facilityName.
	 */
	public String getFacilityName() {
		return facilityName;
	}
	/**
	 * @param facilityName The facilityName to set.
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	/**
	 * @return Returns the hospitalizationId.
	 */
	public String getHospitalizationId() {
		return hospitalizationId;
	}
	/**
	 * @param hospitalizationId The hospitalizationId to set.
	 */
	public void setHospitalizationId(String hospitalizationId) {
		this.hospitalizationId = hospitalizationId;
	}
	/**
	 * @return Returns the hospitalizationReason.
	 */
	public String getHospitalizationReason() {
		return hospitalizationReason;
	}
	/**
	 * @param hospitalizationReason The hospitalizationReason to set.
	 */
	public void setHospitalizationReason(String hospitalizationReason) {
		this.hospitalizationReason = hospitalizationReason;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return Returns the physicianName.
	 */
	public String getPhysicianName() {
		return physicianName;
	}
	/**
	 * @param physicianName The physicianName to set.
	 */
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	/**
	 * @return Returns the physicianPhoneNum.
	 */
	public String getPhysicianPhoneNum() {
		return physicianPhoneNum;
	}
	/**
	 * @param physicianPhoneNum The physicianPhoneNum to set.
	 */
	public void setPhysicianPhoneNum(String physicianPhoneNum) {
		this.physicianPhoneNum = physicianPhoneNum;
	}
	/**
	 * @return Returns the releaseDate.
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	/**
	 * @param releaseDate The releaseDate to set.
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	/**
	 * @return Returns the admissionTypeId.
	 */
	public String getAdmissionTypeId() {
		return admissionTypeId;
	}
	/**
	 * @param admissionTypeId The admissionTypeId to set.
	 */
	public void setAdmissionTypeId(String admissionTypeId) {
		this.admissionTypeId = admissionTypeId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		JuvenileHospitalizationResponseEvent evt =(JuvenileHospitalizationResponseEvent)o;
		return evt.getEntryDate().compareTo(entryDate);		
	}
	

}
