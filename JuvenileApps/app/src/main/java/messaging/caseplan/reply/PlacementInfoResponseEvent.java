/*
 * Created on Nov 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan.reply;

import java.util.Date;

import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PlacementInfoResponseEvent extends ResponseEvent {
	
	private String placementID;
	private Date entryDate;
	private boolean closestFacilityAvail;
	private boolean leastRestrEnvAvail;
	private boolean proxToChildsSD;
	private String reasonPlacementReqd;
	private String specificServices;
	private String whyOutsideTexas;
	private String facilityId;
	private String facilityRelReasonId;
	private Date expReleaseDate;
	private String levelOfCareId;
	private String permanencyPlanId;
	private String permanencyPlan;
	private String specialNotes;
	private String facilityName;
	private String facilityAddress;
	private String facilityStateZip;
	private String facilityPhone;
	private String juvenilePlacementType;
	
	

	/**
	 * @return Returns the closestFacilityAvail.
	 */
	public boolean isClosestFacilityAvail() {
		return closestFacilityAvail;
	}
	/**
	 * @param closestFacilityAvail The closestFacilityAvail to set.
	 */
	public void setClosestFacilityAvail(boolean closestFacilityAvail) {
		this.closestFacilityAvail = closestFacilityAvail;
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
	 * @return Returns the expReleaseDate.
	 */
	public Date getExpReleaseDate() {
		return expReleaseDate;
	}
	/**
	 * @param expReleaseDate The expReleaseDate to set.
	 */
	public void setExpReleaseDate(Date expReleaseDate) {
		this.expReleaseDate = expReleaseDate;
	}
	/**
	 * @return Returns the facilityId.
	 */
	public String getFacilityId() {
		return facilityId;
	}
	/**
	 * @param facilityId The facilityId to set.
	 */
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	/**
	 * @return Returns the facilityRelReasonId.
	 */
	public String getFacilityRelReasonId() {
		return facilityRelReasonId;
	}
	/**
	 * @param facilityRelReasonId The facilityRelReasonId to set.
	 */
	public void setFacilityRelReasonId(String facilityRelReasonId) {
		this.facilityRelReasonId = facilityRelReasonId;
	}
	/**
	 * @return Returns the leastRestrEnvAvail.
	 */
	public boolean isLeastRestrEnvAvail() {
		return leastRestrEnvAvail;
	}
	/**
	 * @param leastRestrEnvAvail The leastRestrEnvAvail to set.
	 */
	public void setLeastRestrEnvAvail(boolean leastRestrEnvAvail) {
		this.leastRestrEnvAvail = leastRestrEnvAvail;
	}
	/**
	 * @return Returns the levelOfCareId.
	 */
	public String getLevelOfCareId() {
		return levelOfCareId;
	}
	/**
	 * @param levelOfCareId The levelOfCareId to set.
	 */
	public void setLevelOfCareId(String levelOfCareId) {
		this.levelOfCareId = levelOfCareId;
	}
	/**
	 * @return Returns the permanencyPlanId.
	 */
	public String getPermanencyPlanId() {
		return permanencyPlanId;
	}
	/**
	 * @param permanencyPlanId The permanencyPlanId to set.
	 */
	public void setPermanencyPlanId(String permanencyPlanId) {
		this.permanencyPlanId = permanencyPlanId;
	}
	/**
	 * @return Returns the placementID.
	 */
	public String getPlacementID() {
		return placementID;
	}
	/**
	 * @param placementID The placementID to set.
	 */
	public void setPlacementID(String placementID) {
		this.placementID = placementID;
	}
	/**
	 * @return Returns the proxToChildsSD.
	 */
	public boolean isProxToChildsSD() {
		return proxToChildsSD;
	}
	/**
	 * @param proxToChildsSD The proxToChildsSD to set.
	 */
	public void setProxToChildsSD(boolean proxToChildsSD) {
		this.proxToChildsSD = proxToChildsSD;
	}
	/**
	 * @return Returns the reasonPlacementReqd.
	 */
	public String getReasonPlacementReqd() {
		return reasonPlacementReqd;
	}
	/**
	 * @param reasonPlacementReqd The reasonPlacementReqd to set.
	 */
	public void setReasonPlacementReqd(String reasonPlacementReqd) {
		this.reasonPlacementReqd = reasonPlacementReqd;
	}
	/**
	 * @return Returns the specialNotes.
	 */
	public String getSpecialNotes() {
		return specialNotes;
	}
	/**
	 * @param specialNotes The specialNotes to set.
	 */
	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
	}
	/**
	 * @return Returns the specificServices.
	 */
	public String getSpecificServices() {
		return specificServices;
	}
	/**
	 * @param specificServices The specificServices to set.
	 */
	public void setSpecificServices(String specificServices) {
		this.specificServices = specificServices;
	}
	/**
	 * @return Returns the whyOutsideTexas.
	 */
	public String getWhyOutsideTexas() {
		return whyOutsideTexas;
	}
	/**
	 * @param whyOutsideTexas The whyOutsideTexas to set.
	 */
	public void setWhyOutsideTexas(String whyOutsideTexas) {
		this.whyOutsideTexas = whyOutsideTexas;
	}
	
	public String getFacilityName()
	{
		return facilityName;
	}
	
	public void setFacilityName(String nm) {
		this.facilityName = nm;
	}
	
	public void setPermanencyPlan(String plan) {
		this.permanencyPlan = plan;
	}
	
	public String getPermanencyPlan()
	{
		return permanencyPlan;
	}
	
	
	
	/**
	 * @return Returns the facilityAddress.
	 */
	public String getFacilityAddress() {
		return facilityAddress;
	}
	/**
	 * @param facilityAddress The facilityAddress to set.
	 */
	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}
	/**
	 * @return Returns the facilityStateZip.
	 */
	public String getFacilityStateZip() {
		return facilityStateZip;
	}
	/**
	 * @param facilityStateZip The facilityStateZip to set.
	 */
	public void setFacilityStateZip(String facilityStateZip) {
		this.facilityStateZip = facilityStateZip;
	}
	public String getFacilityPhone() {
		return facilityPhone;
	}
	public void setFacilityPhone(String facilityPhone) {
		this.facilityPhone = facilityPhone;
	}
	public String getJuvenilePlacementType() {
		return juvenilePlacementType;
	}
	public void setJuvenilePlacementType(String juvenilePlacementType) {
		this.juvenilePlacementType = juvenilePlacementType;
	}
	public String getFormattedFacilityPhone() {
		if(this.facilityPhone != null && this.facilityPhone.length() > 0 ) {
			PhoneNumberBean phone = new PhoneNumberBean(this.facilityPhone);
			return phone.getFormattedPhoneNumber();
		}
		else {
			return "";
		}
	}
}
