package messaging.administerserviceprovider;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class CreateContactFundSourceEvent extends RequestEvent
{
    	private String profileId;
    	private Date fundEntryDate;
    	private Date fundStartDate;
    	private Date fundEndDate;
    	private String status;
    	private String fundSource;
    	private String comments;
    	
    	
	
	public String getProfileId()
	{
	    return profileId;
	}
	public void setProfileId(String profileId)
	{
	    this.profileId = profileId;
	}
	public Date getFundEntryDate()
	{
	    return fundEntryDate;
	}
	public void setFundEntryDate(Date fundEntryDate)
	{
	    this.fundEntryDate = fundEntryDate;
	}
	public Date getFundStartDate()
	{
	    return fundStartDate;
	}
	public void setFundStartDate(Date fundStartDate)
	{
	    this.fundStartDate = fundStartDate;
	}
	public Date getFundEndDate()
	{
	    return fundEndDate;
	}
	public void setFundEndDate(Date fundEndDate)
	{
	    this.fundEndDate = fundEndDate;
	}
	public String getStatus()
	{
	    return status;
	}
	public void setStatus(String status)
	{
	    this.status = status;
	}
	public String getFundSource()
	{
	    return fundSource;
	}
	public void setFundSource(String fundSource)
	{
	    this.fundSource = fundSource;
	}
	public String getComments()
	{
	    return comments;
	}
	public void setComments(String comments)
	{
	    this.comments = comments;
	}
    	
    	

}
