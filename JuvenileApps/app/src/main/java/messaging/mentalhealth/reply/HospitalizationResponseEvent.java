/*
 * Created on Jan 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.mentalhealth.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HospitalizationResponseEvent  extends ResponseEvent implements Comparable{
	
	private String facilityName;
	
	private String admissionType;
	
	private Date admissionDate;

	private Date releaseDate;

	private String admittingPhysicianName;

	private String physicianPhone;
	
	private String hospitalizationReason;
	
	private String hospitalizationId;
	
	
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
	 * @return Returns the admissionType.
	 */
	public String getAdmissionType() {
		return admissionType;
	}
	/**
	 * @param admissionType The admissionType to set.
	 */
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
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
	 * @return Returns the physicianPhone.
	 */
	public String getPhysicianPhone() {
		return physicianPhone;
	}
	/**
	 * @param physicianPhone The physicianPhone to set.
	 */
	public void setPhysicianPhone(String physicianPhone) {
		this.physicianPhone = physicianPhone;
	}
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
	 * @return Returns the admittingPhysicianName.
	 */
	public String getAdmittingPhysicianName() {
		return admittingPhysicianName;
	}
	/**
	 * @param admittingPhysicianName The admittingPhysicianName to set.
	 */
	public void setAdmittingPhysicianName(String admittingPhysicianName) {
		this.admittingPhysicianName = admittingPhysicianName;
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
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		HospitalizationResponseEvent rsp = (HospitalizationResponseEvent)obj;
		Date eventDateA = getAdmissionDate();
		Date eventDateB = rsp.getAdmissionDate();
		
		if(obj==null)
			return -1;
		if(eventDateA==null)
			return 1;		
		if(eventDateB == null)
			return -1;
		return eventDateB.compareTo(eventDateA);
	}



}
