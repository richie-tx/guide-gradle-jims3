package messaging.posttrial.reply;

import java.util.Date;
import java.util.List;

import messaging.administercaseload.domintf.ICaseAssignment;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class CaseAssignmentDataControlResponseEvent extends ResponseEvent
{
    private List caseAssignmentHistories;

    private ICaseAssignment currentAssignment;

    private String defendantId;

    private String defendantName;
    
    private String ssn;
    
    private String sexId;
    
    private Date dob;
    
    private String court;
    
    private String cdi;
    
    private String caseNumber;
    
    private boolean superviseeSupervised;
    
    private boolean caseSupervised;  
    
    public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getSexId() {
		return sexId;
	}

	public void setSexId(String sexId) {
		this.sexId = sexId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getCdi() {
		return cdi;
	}

	public void setCdi(String cdi) {
		this.cdi = cdi;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	
	public boolean isSuperviseeSupervised() {
		return superviseeSupervised;
	}

	public void setSuperviseeSupervised(boolean superviseeSupervised) {
		this.superviseeSupervised = superviseeSupervised;
	}

	public boolean isCaseSupervised() {
		return caseSupervised;
	}

	public void setCaseSupervised(boolean caseSupervised) {
		this.caseSupervised = caseSupervised;
	} 
    
    /**
     * @return Returns the currentAssignment.
     */
    public ICaseAssignment getCurrentAssignment()
    {
        return currentAssignment;
    }

   /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @param currentAssignment
     *        The currentAssignment to set.
     */
    public void setCurrentAssignment(ICaseAssignment currentAssignment)
    {
        this.currentAssignment = currentAssignment;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }


	public String getDefendantName() {
		return defendantName;
	}

	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	
	public List getCaseAssignmentHistories() {
		return caseAssignmentHistories;
	}


	public void setCaseAssignmentHistories(List caseAssignmentHistories) {
		this.caseAssignmentHistories = caseAssignmentHistories;
	}
}



