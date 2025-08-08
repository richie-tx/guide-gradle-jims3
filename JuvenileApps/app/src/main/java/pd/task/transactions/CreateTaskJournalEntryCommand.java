/*
 * Created on Mar 27, 2006
 *
 */
package pd.task.transactions;

import pd.task.TaskJournal;
import messaging.task.CreateTaskJournalEntryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 *
 */
public class CreateTaskJournalEntryCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		CreateTaskJournalEntryEvent event = (CreateTaskJournalEntryEvent) anEvent;
		TaskJournal journalEntry = new TaskJournal();
		
		journalEntry.setTaskId(event.getTaskId());
		
		journalEntry.setJournalEntry(event.getJournalEntry());
				
		journalEntry.setCreateUserID(event.getUserID());
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub
		
	}

}
