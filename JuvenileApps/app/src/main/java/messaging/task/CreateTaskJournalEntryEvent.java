/*
 * Created on Mar 27, 2006
 *
 */
package messaging.task;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class CreateTaskJournalEntryEvent extends RequestEvent
{
	private String taskId;
	private String journalEntry;

	/**
	 * @return
	 */
	public String getJournalEntry()
	{
		return journalEntry;
	}

	/**
	 * @param string
	 */
	public void setJournalEntry(String string)
	{
		journalEntry = string;
	}

	/**
	 * @return
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * @param string
	 */
	public void setTaskId(String string)
	{
		taskId = string;
	}
}
