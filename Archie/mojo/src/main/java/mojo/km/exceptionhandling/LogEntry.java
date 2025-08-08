package mojo.km.exceptionhandling;

import messaging.exceptionhandling.LogEntryTO;
import mojo.km.persistence.PersistentObject;

public class LogEntry extends PersistentObject
{
	private static final int MAX_LONG_DESC = 1000;
	
	private static final int MAX_SHORT_DESC = 30;
	
	private static final int MAX_SOURCEID = 20;
	
	private static final int MAX_SOURCENAME = 50;
	
	private String category;
	
	private int count;
	
	private String ipAddress;	
	
	private String longDescription;
	
	private int milliseconds;
	
	private String shortDescription;
	
	private String sourceId;
	
	private String sourceName;

	private String subsystem;
	
	private String threadName;

	private String type;

	public String getCategory()
	{
		return category;
	}

	public int getCount()
	{
		return count;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public String getLongDescription()
	{
		return longDescription;
	}

	public int getMilliseconds()
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

	public String getThreadName()
	{
		return threadName;
	}
	
	public String getType()
	{
		return type;
	}

	public void setCategory(String category)
	{
		if (this.category == null || !this.category.equals(category))
		{
			markModified();
		}
		this.category = category;
	}

	public void setCount(int aCount)
	{
		if (this.count != aCount)
		{
			markModified();
		}		
		this.count = aCount;
	}

	public void setIpAddress(String ipAddress)
	{
		if (this.ipAddress != ipAddress)
		{
			markModified();
		}		
		this.ipAddress = ipAddress;
	}

	public void setLongDescription(String aLongDesc)
	{
		if (this.longDescription == null || !this.longDescription.equals(aLongDesc))
		{
			markModified();
		}
		if (aLongDesc != null && aLongDesc.length() > MAX_LONG_DESC - 1)
		{
			this.longDescription = aLongDesc.substring(0, MAX_LONG_DESC - 1);
		}
		else
		{
			this.longDescription = aLongDesc;
		}
	}

	public void setMilliseconds(int aMilliseconds)
	{
		if (this.milliseconds != aMilliseconds)
		{
			markModified();
		}		
		this.milliseconds = aMilliseconds;
	}

	public void setMilliseconds(long aMilliseconds)
	{
		this.setMilliseconds((int) aMilliseconds);
	}

	public void setShortDescription(String aShortDesc)
	{
		if (this.shortDescription == null || !this.shortDescription.equals(aShortDesc))
		{
			markModified();
		}
		if (aShortDesc != null && aShortDesc.length() > MAX_SHORT_DESC - 1)
		{
			this.shortDescription = aShortDesc.substring(0, MAX_SHORT_DESC - 1);
		}
		else
		{
			this.shortDescription = aShortDesc;
		}
	}

	public void setSourceId(String aSourceId)
	{
		if (this.sourceId == null || !this.sourceId.equals(sourceId))
		{
			markModified();
		}
		if (aSourceId != null && aSourceId.length() > MAX_SOURCEID - 1)
		{
			this.sourceId = aSourceId.substring(0, MAX_SOURCEID - 1);
		}
		else
		{
			this.sourceId = aSourceId;
		}
	}

	public void setSourceName(String aSourceName)
	{
		if (this.sourceName == null || !this.sourceName.equals(aSourceName))
		{
			markModified();
		}
		if (aSourceName != null && aSourceName.length() > MAX_SOURCENAME - 1)
		{
			this.sourceName = aSourceName.substring(0, MAX_SOURCENAME - 1);
		}
		else
		{
			this.sourceName = aSourceName;
		}
	}

	public void setSubsystem(String subsystem)
	{
		if (this.subsystem == null || !this.subsystem.equals(subsystem))
		{
			markModified();
		}
		this.subsystem = subsystem;
	}

	public void setThreadName(String aThreadName)
	{
		if (this.threadName == null || !this.threadName.equals(aThreadName))
		{
			markModified();
		}
		this.threadName = aThreadName;
	}

	public void setType(String type)
	{
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}

	public void update(LogEntryTO aLogEntry)
	{
		this.setCategory(aLogEntry.getCategory());
		this.setSourceId(aLogEntry.getSourceId());
		this.setSubsystem(aLogEntry.getSubsystem());
		this.setType(aLogEntry.getType());
		this.setSourceName(aLogEntry.getSourceName());
		this.setMilliseconds(aLogEntry.getMilliseconds());
		this.setShortDescription(aLogEntry.getShortDescription());
		this.setLongDescription(aLogEntry.getLongDescription());
	}
}
