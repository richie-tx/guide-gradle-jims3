/*
 * Created on Jun 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.supervisionoptions.transactions;

import java.sql.Timestamp;
import java.util.Date;

import pd.supervision.supervisionoptions.CSTask;
import messaging.supervisionoptions.SaveCSTaskEvent;
import messaging.supervisionoptions.reply.CSTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveCSTaskCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		SaveCSTaskEvent evt = (SaveCSTaskEvent)event;
		CSTask task=null;
    	
		if ( evt.getTaskId() == null || evt.getTaskId().trim().equalsIgnoreCase(""))
		{
			// new task
			task = new CSTask();
			task.setCreateTimestamp(new Timestamp((new Date()).getTime()));
		}
		else
		{
			// existing task
			task = CSTask.find( evt.getTaskId() );
		}
		task.setConditionId(evt.getConditionId());
		task.setCourtId(evt.getCourtId());
		task.setUpdateTimestamp(new Timestamp((new Date()).getTime()));
		if(evt.getDueBy()==null || evt.getDueBy().trim().equals("")){
			task.setDueBy(0);
		}
		else
			task.setDueBy(Integer.parseInt(evt.getDueBy()));
		task.setEmailAddress(evt.getEmailAddress());
		task.setRecipientId(evt.getRecipientId());
		task.setSubject2(evt.getSubject2());
		task.setTaskListTypeId(evt.getTaskListTypeId());
		task.setTaskNotificationTypeId(evt.getTaskNotificationTypeId());
		IHome home = new Home();
		home.bind(task);
		CSTaskResponseEvent reply = task.getResponseEvent();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);

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
