package ui.juvenilecase.populationReport;

import java.util.List;

public class FacilityPopulationTotalsReportBean {
	
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
        private String reportName = "";
        private String facilityName;
        
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
    
    private List facilityPopTots;
    
    
	/**
	 * @return the facilityPopTots
	 */
	public List getFacilityPopTots() {
		return facilityPopTots;
	}
	
	/**
	 * @param facilityPopTots the facilityPopTots to set
	 */
	public void setFacilityPopTots(List facilityPopTots) {
		this.facilityPopTots = facilityPopTots;
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
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		if (reportName != null){
			this.reportName = reportName;
		}else{
			this.reportName = "";
		}
	}
	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
	
			this.facilityName = facilityName;
		
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


}
