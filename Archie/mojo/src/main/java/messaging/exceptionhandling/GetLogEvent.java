package messaging.exceptionhandling;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetLogEvent extends RequestEvent
{
	private Date beginDate;

	private String category;

	private Date endDate;

	private String sourceId;

	private String sourceName;

	private String subsystem;

	private String type;

	public Date getBeginDate()
	{
		return beginDate;
	}

	public String getCategory()
	{
		return category;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public String getSourceId()
	{
		return sourceId;
	}

	public String getSourceName()
	{
		return sourceName;
	}

	public String getSubsystem()
	{
		return subsystem;
	}

	public String getType()
	{
		return type;
	}

	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public void setSourceId(String sourceId)
	{
		this.sourceId = sourceId;
	}

	public void setSourceName(String sourceName)
	{
		this.sourceName = sourceName;
	}

	public void setSubsystem(String subsystem)
	{
		this.subsystem = subsystem;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
