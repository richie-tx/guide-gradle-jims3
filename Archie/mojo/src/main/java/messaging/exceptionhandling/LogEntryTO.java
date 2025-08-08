package messaging.exceptionhandling;

public class LogEntryTO
{
	private String category;

	private String longDescription;

	private long milliseconds;

	private String shortDescription;

	private String sourceId;
	
	private String sourceName;

	private String subsystem;

	private String type;

	public String getCategory()
	{
		return category;
	}

	public String getLongDescription()
	{
		return longDescription;
	}

	public long getMilliseconds()
	{
		return milliseconds;
	}

	public String getShortDescription()
	{
		return shortDescription;
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

	public void setCategory(String category)
	{
		this.category = category;
	}

	public void setLongDescription(String longDescription)
	{
		this.longDescription = longDescription;
	}

	public void setMilliseconds(long milliseconds)
	{
		this.milliseconds = milliseconds;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
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
