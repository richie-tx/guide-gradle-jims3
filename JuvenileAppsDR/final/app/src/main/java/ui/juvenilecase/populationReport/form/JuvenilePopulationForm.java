/*
 * Created on May 3, 2005
 *
 */
package ui.juvenilecase.populationReport.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;

import org.apache.struts.action.ActionForm;

/**
 * @author cshimek
 * 
 *  
 */
public class JuvenilePopulationForm extends ActionForm
{
	private String juvenileNum;
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	private String admitReasonId;
	private String [] admitReasonIds;
	private String facilityId;
	private String reasonId;
	private String reasonDesc;
	private String searchTypeId;
   
	private String securedFacility;
	
	private List currentPopulations;
	private List juvenileAdmits;
	private List otherStatusChanges;
	private List searchTypes;
	private List statusList;   
    	private Date currentTime;
       // this event is just a place holder until PD coding is done
       // the event should have individual total values for race and sex combinations
       // grouped by secure, nonsecure and diversion  
        private JuvenileFacilityResponseEvent	populationEvent;
    
        // values for search page
        private List admitReasons;
        private List facilities;
        
        //for Facility Population Totals Report
        private int totalSecureMalecount;
        private int totalFemaleSecureCount;
        private int totalNonSecMaleCount;
        private int totalNonSecFemaleCount;
        private int totalDivMaleCount;
        private int totalDivFemaleCount;
        private int totalTempReleaseMaleCount;
        private int totalTempReleaseFemaleCount;
        private int totalSecureInmates;
        private int totalNonSecInmates;
        private int totalDivInmates;
        private int totalTempReleaseInmates;
        private int listTotal;
        private List facilityPopTots;
        
        
        private double highHighPercent 		= 0.00;
        private double highModeratePercent 	= 0.00;
        private double highLowPercent		= 0.00;
	    
        private double moderateHighPercent 	= 0.00;
        private double moderateModeratePercent	= 0.00;
        private double moderateLowPercent	= 0.00;
	    
        private double lowHighPercent		= 0.00;
        private double lowModeratePercent	= 0.00;
	private double lowLowPercent		= 0.00;
	    
        private double missingScorePercent	= 0.00;
        
        //for Resident Status Report
        private List residentsStatusList;
        private List residentWithOtherStatusChanges;

	public JuvenilePopulationForm()
	{

	}

	public void clear()
	{
		admitReasonId = "";
		facilityId = "";
		reasonId = "";
		reasonDesc = "";
		securedFacility = "";
		searchTypeId = "";		
		searchTypes = new ArrayList();
		juvenileAdmits = new ArrayList();
		otherStatusChanges = new ArrayList();
		statusList = new ArrayList();
		listTotal=0;
		admitReasonIds = null;
	}

	/**
	 * @return the admitReasonId
	 */
	public String getAdmitReasonId() {
		return admitReasonId;
	}

	/**
	 * @param admitReasonId the admitReasonId to set
	 */
	public void setAdmitReasonId(String admitReasonId) {
		this.admitReasonId = admitReasonId;
	}

	/**
	 * @return the facilityId
	 */
	public String getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the reasonId
	 */
	public String getReasonId() {
		return reasonId;
	}

	/**
	 * @param reasonId the reasonId to set
	 */
	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}

	/**
	 * @return the reasonDesc
	 */
	public String getReasonDesc() {
		return reasonDesc;
	}

	/**
	 * @param reasonDesc the reasonDesc to set
	 */
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	/**
	 * @return the securedFacility
	 */
	public String getSecuredFacility() {
		return securedFacility;
	}

	/**
	 * @param securedFacility the securedFacility to set
	 */
	public void setSecuredFacility(String securedFacility) {
		this.securedFacility = securedFacility;
	}

	/**
	 * @return the searchTypeId
	 */
	public String getSearchTypeId() {
		return searchTypeId;
	}

	/**
	 * @param searchTypeId the searchTypeId to set
	 */
	public void setSearchTypeId(String searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	/**
	 * @return the totalSecureMalecount
	 */
	public int getTotalSecureMalecount() {
		return totalSecureMalecount;
	}

	/**
	 * @param totalSecureMalecount the totalSecureMalecount to set
	 */
	public void setTotalSecureMalecount(int totalSecureMalecount) {
		this.totalSecureMalecount = totalSecureMalecount;
	}
	/**
	 * @return the totalFemaleSecureCount
	 */
	public int getTotalFemaleSecureCount() {
		return totalFemaleSecureCount;
	}

	/**
	 * @param totalFemaleSecureCount the totalFemaleSecureCount to set
	 */
	public void setTotalFemaleSecureCount(int totalFemaleSecureCount) {
		this.totalFemaleSecureCount = totalFemaleSecureCount;
	}
	/**
	 * @return the totalNonSecMaleCount
	 */
	public int getTotalNonSecMaleCount() {
		return totalNonSecMaleCount;
	}

	/**
	 * @param totalNonSecMaleCount the totalNonSecMaleCount to set
	 */
	public void setTotalNonSecMaleCount(int totalNonSecMaleCount) {
		this.totalNonSecMaleCount = totalNonSecMaleCount;
	}
	/**
	 * @return the totalNonSecFemaleCount
	 */
	public int getTotalNonSecFemaleCount() {
		return totalNonSecFemaleCount;
	}

	/**
	 * @param totalNonSecFemaleCount the totalNonSecFemaleCount to set
	 */
	public void setTotalNonSecFemaleCount(int totalNonSecFemaleCount) {
		this.totalNonSecFemaleCount = totalNonSecFemaleCount;
	}

	/**
	 * @return the totalDivMaleCount
	 */
	public int getTotalDivMaleCount() {
		return totalDivMaleCount;
	}

	/**
	 * @param totalDivMaleCount the totalDivMaleCount to set
	 */
	public void setTotalDivMaleCount(int totalDivMaleCount) {
		this.totalDivMaleCount = totalDivMaleCount;
	}
	/**
	 * @return the totalDivFemaleCount
	 */
	public int getTotalDivFemaleCount() {
		return totalDivFemaleCount;
	}

	/**
	 * @param totalDivFemaleCount the totalDivFemaleCount to set
	 */
	public void setTotalDivFemaleCount(int totalDivFemaleCount) {
		this.totalDivFemaleCount = totalDivFemaleCount;
	}

	/**
	 * @param listTotal the listTotal to set
	 */
	public void setListTotal(int listTotal) {
		this.listTotal = listTotal;
	}
	/**
	 * @return the listTotal
	 */
	public int getListTotal() {
		return listTotal;
	}

	/**
	 * @return the admitReasons
	 */
	public List getAdmitReasons() {
		return admitReasons;
	}

	/**
	 * @param admitReasons the admitReasons to set
	 */
	public void setAdmitReasons(List admitReasons) {
		this.admitReasons = admitReasons;
	}

	/**
	 * @return the currentPopulations
	 */
	public List getCurrentPopulations() {
		return currentPopulations;
	}

	/**
	 * @param currentPopulations the currentPopulations to set
	 */
	public void setCurrentPopulations(List currentPopulations) {
		this.currentPopulations = currentPopulations;
	}

	/**
	 * @return the facilities
	 */
	public List getFacilities() {
		return facilities;
	}

	/**
	 * @param facilities the facilities to set
	 */
	public void setFacilities(List facilities) {
		this.facilities = facilities;
	}

	/**
	 * @return the juvenileAdmits
	 */
	public List getJuvenileAdmits() {
		return juvenileAdmits;
	}

	/**
	 * @param juvenileAdmits the juvenileAdmits to set
	 */
	public void setJuvenileAdmits(List juvenileAdmits) {
		this.juvenileAdmits = juvenileAdmits;
	}

	/**
	 * @return the otherStatusChanges
	 */
	public List getOtherStatusChanges() {
		return otherStatusChanges;
	}

	/**
	 * @param otherStatusChanges the otherStatusChanges to set
	 */
	public void setOtherStatusChanges(List otherStatusChanges) {
		this.otherStatusChanges = otherStatusChanges;
	}

	/**
	 * @return the searchTypes
	 */
	public List getSearchTypes() {
		return searchTypes;
	}

	/**
	 * @param searchTypes the searchTypes to set
	 */
	public void setSearchTypes(List searchTypes) {
		this.searchTypes = searchTypes;
	}

	/**
	 * @return the statusList
	 */
	public List getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(List statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the currentTime
	 */
	public Date getCurrentTime() {
		return currentTime;
	}

	/**
	 * @param currentTime the currentTime to set
	 */
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	
	/**
	 * @return the populationEvent
	 */
	public JuvenileFacilityResponseEvent getPopulationEvent() {
		return populationEvent;
	}

	/**
	 * @param populationEvent the populationEvent to set
	 */
	public void setPopulationEvent(JuvenileFacilityResponseEvent populationEvent) {
		this.populationEvent = populationEvent;
	}
	
	/**
	 * @return the facilityPopTots
	 */
	public List getFacilityPopTots() {
		return facilityPopTots;
	}
	
	/**
	 * @return the totalSecureInmates
	 */
	public int getTotalSecureInmates() {
		return totalSecureInmates;
	}

	/**
	 * @param totalSecureInmates the totalSecureInmates to set
	 */
	public void setTotalSecureInmates(int totalSecureInmates) {
		this.totalSecureInmates = totalSecureInmates;
	}
	/**
	 * @return the totalNonSecInmates
	 */
	public int getTotalNonSecInmates() {
		return totalNonSecInmates;
	}

	/**
	 * @param totalNonSecInmates the totalNonSecInmates to set
	 */
	public void setTotalNonSecInmates(int totalNonSecInmates) {
		this.totalNonSecInmates = totalNonSecInmates;
	}

	/**
	 * @return the totalDivInmates
	 */
	public int getTotalDivInmates() {
		return totalDivInmates;
	}

	/**
	 * @param totalNonSecInmates the totalNonSecInmates to set
	 */
	public void setTotalDivInmates(int totalDivInmates) {
		this.totalDivInmates = totalDivInmates;
	}

	/**
	 * @param facilityPopTots the facilityPopTots to set
	 */
	public void setFacilityPopTots(List facilityPopTots) {
		this.facilityPopTots = facilityPopTots;
	}
	
	/**
	 * @return the residentsStatusList
	 */
	public List getResidentsStatusList() {
		return residentsStatusList;
	}

	/**
	 * @param residentsStatusList the residentsStatusList to set
	 */
	public void setResidentsStatusList(List residentsStatusList) {
		this.residentsStatusList = residentsStatusList;
	}
	/**
	 * @return the residentWithOtherStatusChanges
	 */
	public List getResidentWithOtherStatusChanges() {
		return residentWithOtherStatusChanges;
	}

	/**
	 * @param residentWithOtherStatusChanges the residentWithOtherStatusChanges to set
	 */
	public void setResidentWithOtherStatusChanges(List residentWithOtherStatusChanges) {
		this.residentWithOtherStatusChanges = residentWithOtherStatusChanges;
	}
	public int getTotalTempReleaseMaleCount()
	{
	    return totalTempReleaseMaleCount;
	}

	public void setTotalTempReleaseMaleCount(int totalTempReleaseMaleCount)
	{
	    this.totalTempReleaseMaleCount = totalTempReleaseMaleCount;
	}
	public int getTotalTempReleaseFemaleCount()
	{
	    return totalTempReleaseFemaleCount;
	}

	public void setTotalTempReleaseFemaleCount(int totalTempReleaseFemaleCount)
	{
	    this.totalTempReleaseFemaleCount = totalTempReleaseFemaleCount;
	}
	public int getTotalTempReleaseInmates()
	{
	    return totalTempReleaseInmates;
	}

	public void setTotalTempReleaseInmates(int totalTempReleaseInmates)
	{
	    this.totalTempReleaseInmates = totalTempReleaseInmates;
	}
	
	public double getHighHighPercent()
	{
	    return highHighPercent;
	}

	public void setHighHighPercent(double highHighPercent)
	{
	    this.highHighPercent = highHighPercent;
	}

	public double getHighModeratePercent()
	{
	    return highModeratePercent;
	}

	public void setHighModeratePercent(double highModeratePercent)
	{
	    this.highModeratePercent = highModeratePercent;
	}

	public double getHighLowPercent()
	{
	    return highLowPercent;
	}

	public void setHighLowPercent(double highLowPercent)
	{
	    this.highLowPercent = highLowPercent;
	}

	public double getModerateHighPercent()
	{
	    return moderateHighPercent;
	}

	public void setModerateHighPercent(double moderateHighPercent)
	{
	    this.moderateHighPercent = moderateHighPercent;
	}

	public double getModerateModeratePercent()
	{
	    return moderateModeratePercent;
	}

	public void setModerateModeratePercent(double moderateModeratePercent)
	{
	    this.moderateModeratePercent = moderateModeratePercent;
	}

	public double getModerateLowPercent()
	{
	    return moderateLowPercent;
	}

	public void setModerateLowPercent(double moderateLowPercent)
	{
	    this.moderateLowPercent = moderateLowPercent;
	}

	public double getLowHighPercent()
	{
	    return lowHighPercent;
	}

	public void setLowHighPercent(double lowHighPercent)
	{
	    this.lowHighPercent = lowHighPercent;
	}

	public double getLowModeratePercent()
	{
	    return lowModeratePercent;
	}

	public void setLowModeratePercent(double lowModeratePercent)
	{
	    this.lowModeratePercent = lowModeratePercent;
	}

	public double getLowLowPercent()
	{
	    return lowLowPercent;
	}

	public void setLowLowPercent(double lowLowPercent)
	{
	    this.lowLowPercent = lowLowPercent;
	}

	public double getMissingScorePercent()
	{
	    return missingScorePercent;
	}

	public void setMissingScorePercent(double missingScorePercent)
	{
	    this.missingScorePercent = missingScorePercent;
	}
	
	
	
	public String[] getAdmitReasonIds()
	{
	    return admitReasonIds;
	}

	public void setAdmitReasonIds(String[] admitReasonIds)
	{
	    this.admitReasonIds = admitReasonIds;
	}



	public static class FacilityTotal{
		private int maleCount;
		private int femaleCount;
		private String race;
		
		public FacilityTotal(String race, int maleCount, int femaleCount)
		{
			this.maleCount = maleCount;
			this.femaleCount = femaleCount;
			this.race = race;
		}
		/**
		 * @return the maleCount
		 */
		public int getMaleCount() {
			return maleCount;
		}

		/**
		 * @param maleCount the maleCount to set
		 */
		public void setMaleCount(int maleCount) {
			this.maleCount = maleCount;
		}

		/**
		 * @return the femaleCount
		 */
		public int getFemaleCount() {
			return femaleCount;
		}

		/**
		 * @param maleCount the maleCount to set
		 */
		public void setFemaleCount(int femaleCount) {
			this.femaleCount = femaleCount;
		}
		/**
		 * @return the race
		 */
		public String getRace() {
			return race;
		}

		/**
		 * @param race the race to set
		 */
		public void setRace(String race) {
			this.race = race;
		}
	
	}
}
