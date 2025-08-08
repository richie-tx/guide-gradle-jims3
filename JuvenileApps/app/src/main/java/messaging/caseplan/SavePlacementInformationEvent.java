//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\caseplan\\SavePlacementInformationEvent.java

package messaging.caseplan;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SavePlacementInformationEvent extends RequestEvent 
{
	// The caseplan ID to which this placement info will be associated.
	// If null, the PD command assumes 
	// that there is no caseplan and creates one 
	// for this casefile using the casefile ID.
	private String caseplanID;
	private String casefileID; // cannot be null
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
	private String specialNotes;
	
	
   /**
    * @roseuid 4533BD26021A
    */
   public SavePlacementInformationEvent() 
   {
    
   }
	/**
	 * @return Returns the closestFacilityAvail.
	 */
	public boolean getClosestFacilityAvail() {
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
	 * @return Returns the facilityRelReason.
	 */
	public String getFacilityRelReasonId() {
		return facilityRelReasonId;
	}
	/**
	 * @param facilityRelReason The facilityRelReason to set.
	 */
	public void setFacilityRelReasonId(String facilityRelReason) {
		this.facilityRelReasonId = facilityRelReason;
	}
	/**
	 * @return Returns the leastRestrEnvAvail.
	 */
	public boolean getLeastRestrEnvAvail() {
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
	 * @return Returns the proxToChildsSD.
	 */
	public boolean getProxToChildsSD() {
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
	/**
	 * @return Returns the casefileID.
	 */
	public String getCasefileID() {
		return casefileID;
	}
	/**
	 * @param casefileID The casefileID to set.
	 */
	public void setCasefileID(String casefileID) {
		this.casefileID = casefileID;
	}
	/**
	 * @return Returns the caseplanID.
	 */
	public String getCaseplanID() {
		return caseplanID;
	}
	/**
	 * @param caseplanID The caseplanID to set.
	 */
	public void setCaseplanID(String caseplanID) {
		this.caseplanID = caseplanID;
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
}
