package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetCaseAssignmentEvent extends RequestEvent
{
    private String caseAssignmentId;
    
    private String programUnitId ; 

    private String[] caseAssignments;

    private String supervisionOrderId;

    private String criminalCaseId;
    
    /**
     * @roseuid 46435F790155
     */
    public GetCaseAssignmentEvent()
    {

    }
    
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	
    /**
     * @return Returns the caseAssignmentId.
     */
    public String getCaseAssignmentId()
    {
        return caseAssignmentId;
    }

    /**
     * @return Returns the caseAssignments.
     */
    public String[] getCaseAssignments()
    {
        return caseAssignments;
    }

    /**
     * @return Returns the supervisionOrderId.
     */
    public String getSupervisionOrderId()
    {
        return supervisionOrderId;
    }

	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}

    /**
     * @param caseAssignmentId
     *        The caseAssignmentId to set.
     */
    public void setCaseAssignmentId(String caseAssignmentId)
    {
        this.caseAssignmentId = caseAssignmentId;
    }

    /**
     * @param caseAssignments
     *        The caseAssignments to set.
     */
    public void setCaseAssignments(String[] caseAssignments)
    {
        this.caseAssignments = caseAssignments;
    }

    /**
     * @param supervisionOrderId
     *        The supervisionOrderId to set.
     */
    public void setSupervisionOrderId(String supervisionOrderId)
    {
        this.supervisionOrderId = supervisionOrderId;
    }
    
	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

}
