package messaging.casefile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetGangAssessmentReferralEvent extends RequestEvent {
	
	private String assessmentStatus;
	
	private Date fromDate;
	private Date toDate;
	
	private String fromDateStr;
	private String toDateStr;
	
	public GetGangAssessmentReferralEvent(){
		
	}

	public String getAssessmentStatus()
	{
	    return assessmentStatus;
	}

	public void setAssessmentStatus(String assessmentStatus)
	{
	    this.assessmentStatus = assessmentStatus;
	}

	public Date getFromDate()
	{
	    return fromDate;
	}

	public void setFromDate(Date fromDate)
	{
	    this.fromDate = fromDate;
	}

	public Date getToDate()
	{
	    return toDate;
	}

	public void setToDate(Date toDate)
	{
	    this.toDate = toDate;
	}

	public String getFromDateStr()
	{
	    return fromDateStr;
	}

	public void setFromDateStr(String fromDateStr)
	{
	    this.fromDateStr = fromDateStr;
	}

	public String getToDateStr()
	{
	    return toDateStr;
	}

	public void setToDateStr(String toDateStr)
	{
	    this.toDateStr = toDateStr;
	}

	
	
}
