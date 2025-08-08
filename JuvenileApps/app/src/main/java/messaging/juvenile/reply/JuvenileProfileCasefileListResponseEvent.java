/*
 * Project: JIMS
 * Class:   messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import naming.PDJuvenileConstants;

/**
 * @author athorat To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileProfileCasefileListResponseEvent extends ResponseEvent implements Comparable
{

    // ------------------------------------------------------------------------
    // --- fields                                                           ---
    // ------------------------------------------------------------------------

    private String supervisionNum;

    private String sequenceNum;

    private String juvenileId;

    private String supervisionEndDate;

    private String activationDate;

    private String assignmentAddDate;

    private String caseStatus;

    private String probationOfficer;
    private String probationOfficerId;

    private String supervisionType;

    private String supervisionTypeId; //Added for Task 37996; User story 11077
    private Date activationDateDt;
    private Date supervisionEndDateDt;

    private String controllingReferral;

    private String controllingReferralId;

    private String supervisionOutcome;

    private String supervisionOutcomeDescriptionId;

    private String supervisionCategory;

    private String refNumWithOffense; //added for User Story 14257 get the offense for controlling ref

    // ------------------------------------------------------------------------
    // --- constructor                                                      ---
    // ------------------------------------------------------------------------

    /**
     */
    public JuvenileProfileCasefileListResponseEvent()
    {
	super();
	this.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_CASEFILE_LIST_TOPIC);
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.JuvenileProfileCasefileListResponseEvent

    // ------------------------------------------------------------------------
    // --- methods                                                          ---
    // ------------------------------------------------------------------------

    /**
     * @return The case status.
     */
    public String getCaseStatus()
    {
	return caseStatus;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getCaseStatus

    /**
     * @return The supervision end date.
     */
    public String getSupervisionEndDate()
    {
	return supervisionEndDate;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getSupervisionEndDate

    /**
     * @return The supervision num.
     */
    public String getSupervisionNum()
    {
	return supervisionNum;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getSupervisionNum

    /**
     * @param string
     *            The case status.
     */
    public void setCaseStatus(String string)
    {
	caseStatus = string;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setCaseStatus

    /**
     * @param string
     *            The supervision end date.
     */
    public void setSupervisionEndDate(String string)
    {
	supervisionEndDate = string;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setSupervisionEndDate

    /**
     * @param string
     *            The supervision num.
     */
    public void setSupervisionNum(String string)
    {
	supervisionNum = string;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setSupervisionNum

    /**
     * @return The activation date.
     */
    public String getActivationDate()
    {
	return activationDate;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getActivationDate

    /**
     * @param string
     *            The activation date.
     */
    public void setActivationDate(String string)
    {
	activationDate = string;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setActivationDate

    /**
     * @return The juvenile id.
     */
    public String getJuvenileId()
    {
	return juvenileId;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getJuvenileId

    /**
     * @return the probation officer
     */
    public String getProbationOfficer()
    {
	return probationOfficer;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getProbationOfficer

    /**
     * @return the supervision type
     */
    public String getSupervisionType()
    {
	return supervisionType;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getSupervisionType

    /**
     * @param string
     *            The juvenile id.
     */
    public void setJuvenileId(String string)
    {
	juvenileId = string;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setJuvenileId

    /**
     * @return string probation officer
     */
    public void setProbationOfficer(String officer)
    {
	probationOfficer = officer;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setProbationOfficer

    /**
     * @return the supervision type
     */
    public void setSupervisionType(String supervision)
    {
	supervisionType = supervision;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setSupervisionType

    /**
     * @return the assignmentDate
     */
    public String getAssignmentAddDate()
    {
	return assignmentAddDate;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.getAssignmentDate

    /**
     * @param assignmentDate
     *            the assignmentDate to set
     */
    public void setAssignmentAddDate(String string)
    {
	assignmentAddDate = string;
    } //end of messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent.setAssignmentDate

    /**
     * @return
     */
    public String getSequenceNum()
    {
	return sequenceNum;
    }

    /**
     * @param sequenceNum
     */
    public void setSequenceNum(String sequenceNum)
    {
	this.sequenceNum = sequenceNum;
    }

    /**
     * @return the controllingReferralId
     */
    public String getControllingReferralId()
    {
	return controllingReferralId;
    }

    /**
     * @param controllingReferralId
     *            the controllingReferralId to set
     */
    public void setControllingReferralId(String controllingReferralId)
    {
	this.controllingReferralId = controllingReferralId;
    }

    /**
     * @return the controllingReferral
     */
    public String getControllingReferral()
    {
	return controllingReferral;
    }

    /**
     * @param controllingReferral
     *            the controllingReferral to set
     */
    public void setControllingReferral(String controllingReferral)
    {
	this.controllingReferral = controllingReferral;
    }

    /**
     * @return the refNumWithOffense
     */
    public String getRefNumWithOffense()
    {
	return refNumWithOffense;
    }

    /**
     * @param refNumWithOffense
     *            the refNumWithOffense to set
     */
    public void setRefNumWithOffense(String refNumWithOffense)
    {
	this.refNumWithOffense = refNumWithOffense;
    }

    /**
     * @return the supervisionOutcome
     */
    public String getSupervisionOutcome()
    {
	return supervisionOutcome;
    }

    /**
     * @param supervisionOutcome
     *            the supervisionOutcome to set
     */
    public void setSupervisionOutcome(String supervisionOutcome)
    {
	this.supervisionOutcome = supervisionOutcome;
    }

    /**
     * @return the supervisionOutcomeDescriptionId
     */
    public String getSupervisionOutcomeDescriptionId()
    {
	return supervisionOutcomeDescriptionId;
    }

    /**
     * @param supervisionOutcomeDescriptionId
     *            the supervisionOutcomeDescriptionId to set
     */
    public void setSupervisionOutcomeDescriptionId(String supervisionOutcomeDescriptionId)
    {
	this.supervisionOutcomeDescriptionId = supervisionOutcomeDescriptionId;
    }

    /**
     * @param supervisionCategory
     *            the supervisionCategory to set
     */
    public void setSupervisionCategory(String supervisionCategory)
    {
	this.supervisionCategory = supervisionCategory;
    }

    /**
     * @return the supervisionOutcomeDescriptionId
     */
    public String getSupervisionCategory()
    {
	return supervisionCategory;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object obj)
    {
	JuvenileProfileCasefileListResponseEvent c = (JuvenileProfileCasefileListResponseEvent) obj;
	int i = Integer.parseInt(c.getSupervisionNum());
	int j = Integer.parseInt(this.supervisionNum);
	if (i > j)
	{
	    return 1;
	}
	else
	    if (i < j)
	    {
		return -1;
	    }
	    else
	    {
		return 0;
	    }

    }

    public String getSupervisionTypeId()
    {
	return supervisionTypeId;
    }

    public void setSupervisionTypeId(String supervisionTypeId)
    {
	this.supervisionTypeId = supervisionTypeId;
    }

    public Date getActivationDateDt()
    {
	return activationDateDt;
    }

    public void setActivationDateDt(Date activationDateDt)
    {
	this.activationDateDt = activationDateDt;
    }

    public Date getSupervisionEndDateDt()
    {
	return supervisionEndDateDt;
    }

    public void setSupervisionEndDateDt(Date supervisionEndDateDt)
    {
	this.supervisionEndDateDt = supervisionEndDateDt;
    }

    public String getProbationOfficerId()
    {
	return probationOfficerId;
    }

    public void setProbationOfficerId(String probationOfficerId)
    {
	this.probationOfficerId = probationOfficerId;
    }

} // end JuvenileProfileCasefileListResponseEvent
