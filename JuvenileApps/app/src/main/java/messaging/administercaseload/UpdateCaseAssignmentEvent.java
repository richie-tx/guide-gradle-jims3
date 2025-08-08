package messaging.administercaseload;

import java.util.Date;

import messaging.administercaseload.domintf.ICaseAssignment;
import mojo.km.messaging.RequestEvent;

public class UpdateCaseAssignmentEvent extends RequestEvent
{
    private ICaseAssignment caseAssignment;

    private String caseAssignmentTransactionCode;

    private Date changeDate;

    /**
     * @roseuid 46435FB8028F
     */
    public UpdateCaseAssignmentEvent()
    {

    }

    /**
     * @return Returns the caseAssignment.
     */
    public ICaseAssignment getCaseAssignment()
    {
        return caseAssignment;
    }

    /**
     * @param caseAssignment
     *        The caseAssignment to set.
     */
    public void setCaseAssignment(ICaseAssignment caseAssignment)
    {
        this.caseAssignment = caseAssignment;
    }

    /**
     * @return Returns the caseAssignmentTransactionCode.
     */
    public String getCaseAssignmentTransactionCode()
    {
        return caseAssignmentTransactionCode;
    }

    /**
     * @param caseAssignmentTransactionCode
     *        The caseAssignmentTransactionCode to set.
     */
    public void setCaseAssignmentTransactionCode(String caseAssignmentTransactionCode)
    {
        this.caseAssignmentTransactionCode = caseAssignmentTransactionCode;
    }

    /**
     * @return Returns the changeDate.
     */
    public Date getChangeDate()
    {
        return changeDate;
    }

    /**
     * @param changeDate
     *        The changeDate to set.
     */
    public void setChangeDate(Date changeDate)
    {
        this.changeDate = changeDate;
    }
}
