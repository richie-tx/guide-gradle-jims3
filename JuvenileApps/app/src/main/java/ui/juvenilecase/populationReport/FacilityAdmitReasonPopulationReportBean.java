package ui.juvenilecase.populationReport;

import java.util.Date;
import java.util.List;

public class FacilityAdmitReasonPopulationReportBean {
	
	
	
	private String reasonCode;
	private String secureStatus;
	private String reportName = "";
	private String facilityName;    
	private List juvenileAdmits;
	private String selectedReasonCodes;
    
  

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getSecureStatus() {
		return secureStatus;
	}

	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}
	/**
	 * @return the facilityPopTots
	 */
	public List getJuvenileAdmits() {
		return juvenileAdmits;
	}
	
	/**
	 * @param facilityPopTots the facilityPopTots to set
	 */
	public void setJuvenileAdmits(List juvenileAdmits) {
		this.juvenileAdmits = juvenileAdmits;
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

	public String getSelectedReasonCodes()
	{
	    return selectedReasonCodes;
	}

	public void setSelectedReasonCodes(String selectedReasonCodes)
	{
	    this.selectedReasonCodes = selectedReasonCodes;
	}

	
	
	

}
