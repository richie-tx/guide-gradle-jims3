/*
 * Created on Mar 27, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.task.to;

import java.util.Date;

import messaging.contact.domintf.IName;
import messaging.task.domintf.ITaskJournal;

/**
 * @author jfisher
 *
 */
public class TaskJournalBean implements ITaskJournal
{
	private String journalEntry;
	private Date createDate;
	private IName creator;
	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		return createDate;
	}

	/**
	 * @return
	 */
	public IName getCreator()
	{
		return creator;
	}

	/**
	 * @return
	 */
	public String getJournalEntry()
	{
		return journalEntry;
	}

	/**
	 * @param date
	 */
	public void setCreateDate(Date date)
	{
		createDate = date;
	}

	/**
	 * @param name
	 */
	public void setCreator(IName name)
	{
		creator = name;
	}

	/**
	 * @param string
	 */
	public void setJournalEntry(String string)
	{
		journalEntry = string;
	}

	public String toString()
	{
		return "Journal Entry Creator: " + this.creator + " : " + this.journalEntry;
	}

}
