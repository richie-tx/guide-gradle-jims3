package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetCaseloadEvent extends RequestEvent
{
    private String defendantId;

    private String officerPositionId;

    private String workflowInd;

    private String supervisorFirstName;

    private String supervisorLastName;
    
    private String quadrantId;
    
    private String zipCode;

    /**
     * @roseuid 464DFE9003DD
     */
    public GetCaseloadEvent()
    {

    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
     * @return Returns the supervisorPositionId.
     */
    public String getOfficerPositionId()
    {
        return officerPositionId;
    }

    /**
     * @return Returns the firstName.
     */
    public String getSupervisorFirstName()
    {
        return supervisorFirstName;
    }

    /**
     * @return Returns the lastName.
     */
    public String getSupervisorLastName()
    {
        return supervisorLastName;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

    /**
     * @param supervisorPositionId
     *        The supervisorPositionId to set.
     */
    public void setOfficerPositionId(String supervisorPositionId)
    {
        this.officerPositionId = supervisorPositionId;
    }

        /**
     * @param firstName
     *        The firstName to set.
     */
    public void setSupervisorFirstName(String aSupervisorFirstName)
    {
        this.supervisorFirstName = aSupervisorFirstName;
    }

    /**
     * @param lastName
     *        The lastName to set.
     */
    public void setSupervisorLastName(String aSupervisorLastName)
    {
        this.supervisorLastName = aSupervisorLastName;
    }
    /**
     * @return Returns the workflowInd.
     */
    public String getWorkflowInd()
    {
        return workflowInd;
    }
    /**
     * @param workflowInd The workflowInd to set.
     */
    public void setWorkflowInd(String workflowInd)
    {
        this.workflowInd = workflowInd;
    }

	public String getQuadrantId() {
		return quadrantId;
	}

	public void setQuadrantId(String quadrantId) {
		this.quadrantId = quadrantId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
