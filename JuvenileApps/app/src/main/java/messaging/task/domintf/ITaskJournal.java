/*
 * Created on Mar 14, 2006
 *
 */
package messaging.task.domintf;

import java.util.Date;

import messaging.contact.domintf.IName;

/**
 * @author Jim Fisher
 *
 */
public interface ITaskJournal
{
	public Date getCreateDate();
	public IName getCreator();
	public String getJournalEntry();
	public void setCreateDate(Date date);
	public void setCreator(IName name);
	public void setJournalEntry(String string);
}
