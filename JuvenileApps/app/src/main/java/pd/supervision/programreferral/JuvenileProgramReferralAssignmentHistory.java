package pd.supervision.programreferral;

import java.util.Date;
import java.util.Iterator;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileProgramReferralAssignmentHistory extends PersistentObject {
	
	private String programReferralId;
	private String casefileId;
	private Date programReferralAssignmentDate;

	/**
	 * 
	 */
	public JuvenileProgramReferralAssignmentHistory() {
	}

	public String getCasefileId() {
		fetch();
		return casefileId;
	}

	public void setCasefileId(String casefileId) {
		if (this.casefileId == null || !this.casefileId.equals(casefileId)) {
			markModified();
		}
		this.casefileId = casefileId;
	}

	public Date getProgramReferralAssignmentDate() {
		fetch();
		return programReferralAssignmentDate;
	}

	public void setProgramReferralAssignmentDate(Date programReferralAssignmentDate) {
		if (this.programReferralAssignmentDate == null
				|| !this.programReferralAssignmentDate
						.equals(programReferralAssignmentDate)) {
			markModified();
		}
		this.programReferralAssignmentDate = programReferralAssignmentDate;
	}

	public String getProgramReferralId() {
		fetch();
		return programReferralId;
	}

	public void setProgramReferralId(String programReferralId) {
		if (this.programReferralId == null
				|| !this.programReferralId.equals(programReferralId)) {
			markModified();
		}
		this.programReferralId = programReferralId;
	}
	/**
	* @return JuvenileProgramReferralAssignmentHistory
	* @param juvenileProgramReferralAssignmentHistoryId
	*/
	static public JuvenileProgramReferralAssignmentHistory find(String juvenileProgramReferralAssignmentHistoryId)
	{
		IHome home = new Home();
		JuvenileProgramReferralAssignmentHistory juvenileProgramReferralAssignmentHistory = (JuvenileProgramReferralAssignmentHistory) home.find(juvenileProgramReferralAssignmentHistoryId, JuvenileProgramReferralAssignmentHistory.class);
		return juvenileProgramReferralAssignmentHistory;
	}
	/**
	* @return Iterator histories
	* @param attrName name for the attribute for where clause
	* @param attrValue value to be checked in the where clause
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator histories = home.findAll(attrName, attrValue, JuvenileProgramReferralAssignmentHistory.class);
		return histories;
	}
	
	/**
	* Finds JuvenileProgramReferralAssignmentHistories by a certain event
	* @param event
	* @return Iterator of JuvenileProgramReferralAssignmentHistories
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator histories = home.findAll(event, JuvenileProgramReferralAssignmentHistory.class);
		return histories;
	}
	
	public ProgramReferralAssignmentHistoryResponseEvent getValueObject( boolean detailNeeded )
	{
		ProgramReferralAssignmentHistoryResponseEvent resp = new ProgramReferralAssignmentHistoryResponseEvent();

		resp.setProgramReferralId(this.getProgramReferralId()) ;
		resp.setCasefileId( this.getCasefileId() );
		resp.setProgramReferralAssignmentDate( this.getProgramReferralAssignmentDate() );
		return resp;
	}

}
