package messaging.interviewinfo;

import java.util.List;

import messaging.interviewinfo.to.FamilyInformationTO;
import mojo.km.messaging.reporting.ReportRequestEvent;


public class CreateRequestAttorneyReportEvent extends ReportRequestEvent 
{
	private String casefileId;
	private String notes = "";
	private FamilyInformationTO guardian1 = null;
	private FamilyInformationTO guardian2 = null;
	private List stepParentEmploymentIds = null; 
	
	/**
	* 
	*/
	public CreateRequestAttorneyReportEvent() 
	{
    
	}
	
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}

	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * @return Returns the guardian1.
	 */
	public FamilyInformationTO getGuardian1() {
		return guardian1;
	}
	/**
	 * @param guardian1 The guardian1 to set.
	 */
	public void setGuardian1(FamilyInformationTO guardian1) {
		this.guardian1 = guardian1;
	}
	/**
	 * @return Returns the guardian2.
	 */
	public FamilyInformationTO getGuardian2() {
		return guardian2;
	}
	/**
	 * @param guardian2 The guardian2 to set.
	 */
	public void setGuardian2(FamilyInformationTO guardian2) {
		this.guardian2 = guardian2;
	}
	/**
	 * @return Returns the stepParentEmploymentIds.
	 */
	public List getStepParentEmploymentIds() {
		return stepParentEmploymentIds;
	}
	/**
	 * @param stepParentEmploymentIds The stepParentEmploymentIds to set.
	 */
	public void setStepParentEmploymentIds(List stepParentEmploymentIds) {
		this.stepParentEmploymentIds = stepParentEmploymentIds;
	}
}
