/*
 * Created on Jun 19, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.supervisionoptions.transactions;

import java.util.Iterator;

import pd.supervision.supervisionoptions.CSTask;
import messaging.supervisionoptions.DeleteCSTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DeleteCSTaskCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		DeleteCSTaskEvent evt = (DeleteCSTaskEvent)event;
			
		CSTask task=null;
		Iterator taskIter=null;
		if(evt.getTaskId()!=null)
		{
			task=CSTask.find(evt.getTaskId());	
			if ( task != null )
			{
				task.delete();
			}
			
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
				task = (CSTask)taskIter.next();
				if ( task != null )
				{
					task.delete();
				}
			}
		}
		return;
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
