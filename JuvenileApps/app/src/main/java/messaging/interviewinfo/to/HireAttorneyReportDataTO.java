package messaging.interviewinfo.to;

import java.util.ArrayList;
import java.util.List;


/**
 * @author awidjaja
 * This class is used to communicate information between PD - UI - UJAC, 
 * mainly for Conduct Interview - Parental Rights (HireAttorney) print template.
 */
public class HireAttorneyReportDataTO 
{
	private List guardianRelatedInformation = new ArrayList();
	private List jjsOffenses;
	private List jotRecord;
	private String dateInformationGiven;
	private String victimsPhysicalInjuries;
	private String probationOfficer;
	
	public List getGuardianRelatedInformation() {
		return guardianRelatedInformation;
	}
	public void setGuardianRelatedInformation(List guardianRelatedInformation) {
		this.guardianRelatedInformation = guardianRelatedInformation;
	}
	public List getJjsOffenses() {
		return jjsOffenses;
	}
	public void setJjsOffenses(List jjsOffenses) {
		this.jjsOffenses = jjsOffenses;
	}
	public List getJotRecord() {
		return jotRecord;
	}
	public void setJotRecord(List jotRecord) {
		this.jotRecord = jotRecord;
	}
	public String getDateInformationGiven() {
		return dateInformationGiven;
	}
	public void setDateInformationGiven(String dateInformationGiven) {
		this.dateInformationGiven = dateInformationGiven;
	}
	public String getProbationOfficer() {
		return probationOfficer;
	}
	public void setProbationOfficer(String probationOfficer) {
		this.probationOfficer = probationOfficer;
	}
	public String getVictimsPhysicalInjuries() {
		return victimsPhysicalInjuries;
	}
	public void setVictimsPhysicalInjuries(String victimsPhysicalInjuries) {
		this.victimsPhysicalInjuries = victimsPhysicalInjuries;
	}
}
