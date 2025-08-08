package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportIntakeHistoryEvent extends RequestEvent
{
    	private String referralNumber;
	private String juvenileNumber;
	private Date assignmentDate;
	private Date intakeDate;
	private String intakeJPO;
	private String intakeDecision;	
	private String intakeHistoryId;
	private String assignmentType;
	private String supervisionTypeCode;

	
	
	public String getReferralNumber()
	{
	    return referralNumber;
	}
	public void setReferralNumber(String referralNumber)
	{
	    this.referralNumber = referralNumber;
	}
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}
	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}
	public Date getAssignmentDate()
	{
	    return assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate)
	{
	    this.assignmentDate = assignmentDate;
	}
	public Date getIntakeDate()
	{
	    return intakeDate;
	}
	public void setIntakeDate(Date intakeDate)
	{
	    this.intakeDate = intakeDate;
	}
	public String getIntakeJPO()
	{
	    return intakeJPO;
	}
	public void setIntakeJPO(String intakeJPO)
	{
	    this.intakeJPO = intakeJPO;
	}
	public String getIntakeDecision()
	{
	    return intakeDecision;
	}
	public void setIntakeDecision(String intakeDecision)
	{
	    this.intakeDecision = intakeDecision;
	}
	public String getIntakeHistoryId()
	{
	    return intakeHistoryId;
	}
	public void setIntakeHistoryId(String intakeHistoryId)
	{
	    this.intakeHistoryId = intakeHistoryId;
	}
	public String getAssignmentType()
	{
	    return assignmentType;
	}
	public void setAssignmentType(String assignmentType)
	{
	    this.assignmentType = assignmentType;
	}
	public String getSupervisionTypeCode()
	{
	    return supervisionTypeCode;
	}
	public void setSupervisionTypeCode(String supervisionTypeCode)
	{
	    this.supervisionTypeCode = supervisionTypeCode;
	}
}
