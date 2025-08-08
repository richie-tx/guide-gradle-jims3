package messaging.programreferral;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class TransferProgramReferralEvent extends RequestEvent {
	private String programReferralId;
	
	private String casefileId;
	
	private Date programReferralAssignmentDate;
	
	private List juvenileServiceEvents ;  

	public String getProgramReferralId() {
		return programReferralId;
	}

	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}

	public String getCasefileId() {
		return casefileId;
	}

	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	public Date getProgramReferralAssignmentDate() {
		return programReferralAssignmentDate;
	}

	public void setProgramReferralAssignmentDate(Date programReferralAssignmentDate) {
		this.programReferralAssignmentDate = programReferralAssignmentDate;
	}
	
	/**
	 * @return Returns the juvenileServiceEvents.
	 */
	public List getJuvenileServiceEvents( )
	{
		return juvenileServiceEvents ;
	}

	/**
	 * @param juvenileServiceEvents
	 * The juvenileServiceEvents to set.
	 */
	public void setJuvenileServiceEvents( List juvenileServiceEvents )
	{
		this.juvenileServiceEvents = juvenileServiceEvents ;
	}
	
}
