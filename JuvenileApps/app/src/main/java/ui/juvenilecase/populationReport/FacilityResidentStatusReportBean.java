package ui.juvenilecase.populationReport;

import java.util.List;

public class FacilityResidentStatusReportBean {
	
	private List residentsList;
	private List residentsWithOtherChanges;
	private int facilityTotal;
	private String facilityName;
	
	private String reportName = "";
	
	/**
	 * @return the facilityTotal
	 */
	public int getFacilityTotal() {
		return facilityTotal;
	}

	/**
	 * @param facilityTotal the facilityTotal to set
	 */
	public void setFacilityTotal(int facilityTotal) {
		this.facilityTotal = facilityTotal;
	}
	/**
	 * @return the residentsList
	 */
	public List getResidentsList() {
		return residentsList;
	}
	
	/**
	 * @param residentsList the residentsList to set
	 */
	public void setResidentsList(List residentsList) {
		this.residentsList = residentsList;
	}
	/**
	 * @return the residentsWithOtherChanges
	 */
	public List getResidentsWithOtherChanges() {
		return residentsWithOtherChanges;
	}
	
	/**
	 * @param residentsWithOtherChanges the residentsWithOtherChanges to set
	 */
	public void setResidentsWithOtherChanges(List residentsWithOtherChanges) {
		this.residentsWithOtherChanges = residentsWithOtherChanges;
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

}
