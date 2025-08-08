/*
 * Created on Mar 27, 2006
 *
 */
package messaging.task.reply;

import messaging.task.domintf.ITaskJournal;
import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 *
 */
public class TaskJournalResponseEvent extends ResponseEvent
{
	private ITaskJournal taskJournal;
	/**
	 * @return
	 */
	public ITaskJournal getTaskJournal()
	{
		return taskJournal;
	}

	/**
	 * @param journal
	 */
	public void setTaskJournal(ITaskJournal journal)
	{
		taskJournal = journal;
	}

}
