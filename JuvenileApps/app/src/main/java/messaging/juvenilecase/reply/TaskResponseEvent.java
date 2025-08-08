
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 */
public class TaskResponseEvent extends ResponseEvent
{
	private String taskID;
	private String title;
	private String details;
	private String typeId;
	private String type;
	private String taskStatusId;
	private String taskStatus;
	private Date creationDate;
	private String userId;
	private String URL;
	private String juvenileName;

	public TaskResponseEvent()
	{
	}
	
	/**
	 * @return
	 */
	public Date getCreationDate()
	{
		return creationDate;
	}

	/**
	 * @return
	 */
	public String getDetails()
	{
		return details;
	}

	/**
	 * @return
	 */
	public String getTaskStatus()
	{
		return taskStatus;
	}

	/**
	 * @return
	 */
	public String getTaskStatusId()
	{
		return taskStatusId;
	}

	/**
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @return
	 */
	public String getTypeId()
	{
		return typeId;
	}

	/**
	 * @return
	 */
	public String getURL()
	{
		return URL;
	}

	/**
	 * @return
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param date
	 */
	public void setCreationDate(Date date)
	{
		creationDate = date;
	}

	/**
	 * @param string
	 */
	public void setDetails(String string)
	{
		details = string;
	}

	/**
	 * @param string
	 */
	public void setTaskStatus(String string)
	{
		taskStatus = string;
	}

	/**
	 * @param string
	 */
	public void setTaskStatusId(String string)
	{
		taskStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string)
	{
		title = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string)
	{
		type = string;
	}

	/**
	 * @param string
	 */
	public void setTypeId(String string)
	{
		typeId = string;
	}

	/**
	 * @param string
	 */
	public void setURL(String string)
	{
		URL = string;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string)
	{
		userId = string;
	}

	/**
	 * @return
	 */
	public String getTaskID()
	{
		return taskID;
	}

	/**
	 * @param string
	 */
	public void setTaskID(String string)
	{
		taskID = string;
	}

	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
}
