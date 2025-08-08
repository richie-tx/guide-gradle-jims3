/*
 * Created on Jun 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.CSTask;

import messaging.supervisionoptions.GetCSTaskEvent;
import messaging.supervisionoptions.reply.CSTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCSTaskCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetCSTaskEvent evt = (GetCSTaskEvent)event;
		/* IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		CSTaskResponseEvent respEvt=null;	
		Iterator taskIter=null;
			
		if(evt.getTaskId()!=null)
		{
			CSTask task=CSTask.find(evt.getTaskId());	
			respEvt=task.getResponseEvent();
			dispatch.postEvent(respEvt);
			
		}
		else if(evt.getConditionId()!=null){
			taskIter = CSTask.findAll("conditionId",evt.getConditionId());
			
		}
		else if(evt.getCourtId()!=null){
			taskIter = CSTask.findAll("courtId",evt.getCourtId());
		}
		else{
					
		}
		if(taskIter!=null && taskIter.hasNext()){
			while ( taskIter.hasNext() )
			{
				CSTask task = (CSTask)taskIter.next();
				respEvt = null;
				respEvt = task.getResponseEvent();
				dispatch.postEvent(respEvt);
			}
		}
		return;
		*/
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
