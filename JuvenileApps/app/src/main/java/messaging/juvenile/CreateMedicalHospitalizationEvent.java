//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\CreateMedicalHospitalizationEvent.java

package messaging.juvenile;

import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import mojo.km.messaging.RequestEvent;

public class CreateMedicalHospitalizationEvent extends RequestEvent 
{
   public String juvenileNum;
   public Date entryDate;
   public Date admissionDate;
   public String hospitalizationReason;
   public IName physicianName;
   public IPhoneNumber physicianPhone;
   public String facilityName;
   public Date releaseDate;
   public String admissionTypeId;
   public String admitYear;
   public String lengthOfStay;
   
   /**
    * @roseuid 462CE3AA02F2
    */
   public CreateMedicalHospitalizationEvent() 
   {
    
   }
   
/**
 * @roseuid 462CE3AA02F2
 * @return Returns the admissionDate.
 */
public Date getAdmissionDate() {
	return admissionDate;
}
/**
 * @roseuid 462CE3AA02F2
 * @param admissionDate The admissionDate to set.
 */
public void setAdmissionDate(Date admissionDate) {
	this.admissionDate = admissionDate;
}
/**
 * @roseuid 462CE3AA02F2
 * @return Returns the admissionTypeId.
 */
public String getAdmissionTypeId() {
	return admissionTypeId;
}
/**
 * @roseuid 462CE3AA02F2
 * @param admissionTypeId The admissionTypeId to set.
 */
public void setAdmissionTypeId(String admissionTypeId) {
	this.admissionTypeId = admissionTypeId;
}

/**
 * @roseuid 462CE3AA02F2
 * @return Returns the facilityName.
 */
public String getFacilityName() {
	return facilityName;
}
/**
 * @roseuid 462CE3AA02F2
 * @param facilityName The facilityName to set.
 */
public void setFacilityName(String facilityName) {
	this.facilityName = facilityName;
}
/**
 * @roseuid 462CE3AA02F2
 * @return Returns the hospitalizationReason.
 */
public String getHospitalizationReason() {
	return hospitalizationReason;
}
/**
 * @roseuid 462CE3AA02F2
 * @param hospitalizationReason The hospitalizationReason to set.
 */
public void setHospitalizationReason(String hospitalizationReason) {
	this.hospitalizationReason = hospitalizationReason;
}
/**
 * @roseuid 462CE3AA02F2
 * @return Returns the juvenileNum.
 */
public String getJuvenileNum() {
	return juvenileNum;
}
/**
 * @roseuid 462CE3AA02F2
 * @param juvenileNum The juvenileNum to set.
 */
public void setJuvenileNum(String juvenileNum) {
	this.juvenileNum = juvenileNum;
}
/**
 * @roseuid 462CE3AA02F2
 * @return Returns the physicianName.
 */
public String getPhysicianName() {
	return physicianName.getFormattedName();
}
/**
 * @roseuid 462CE3AA02F2
 * @param physicianName The physicianName to set.
 */
public void setPhysicianName(IName physicianName) {
	this.physicianName = physicianName;
}
/**
 * @roseuid 462CE3AA02F2
 * @return Returns the physicianPhone.
 */
public String getPhysicianPhone() {
	return physicianPhone.getPhoneNumber();
}
/**
 * @roseuid 462CE3AA02F2
 * @param physicianPhone The physicianPhone to set.
 */
public void setPhysicianPhone(IPhoneNumber physicianPhone) {
	this.physicianPhone = physicianPhone;
}
/**
 * @roseuid 462CE3AA02F2
 * @return Returns the releaseDate.
 */
public Date getReleaseDate() {
	return releaseDate;
}
/**
 * @roseuid 462CE3AA02F2
 * @param releaseDate The releaseDate to set.
 */
public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
}

/**
 * @roseuid 462CE3AA02F2
 * @return Returns the entryDate.
 */
public Date getEntryDate() {
	return entryDate;
}
/**
 * @roseuid 462CE3AA02F2
 * @param entryDate The entryDate to set.
 */
public void setEntryDate(Date entryDate) {
	this.entryDate = entryDate;
}

public String getAdmitYear()
{
    return admitYear;
}

public void setAdmitYear(String admitYear)
{
    this.admitYear = admitYear;
}

public String getLengthOfStay()
{
    return lengthOfStay;
}

public void setLengthOfStay(String lengthOfStay)
{
    this.lengthOfStay = lengthOfStay;
}


}