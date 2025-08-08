/*
 * Created on Jul 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetServiceTypeCdEvent;
import pd.codetable.criminal.JuvenileEventTypeCode;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetServiceTypeCdCommand implements ICommand
{

	/**
	 * 
	 */
	public GetServiceTypeCdCommand()
	{

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetServiceTypeCdEvent getCodesEvent = (GetServiceTypeCdEvent) event;

		Iterator i = JuvenileEventTypeCode.findServiceTypeCodes(getCodesEvent); 
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		int count = 0;
		while (i.hasNext())
		{
			count++;
			JuvenileEventTypeCode juv = (JuvenileEventTypeCode) i.next();
			if (juv.getStatus() == null ||
				!juv.getStatus().equals("I")) {
				ServiceTypeCdResponseEvent replyEvent = new ServiceTypeCdResponseEvent();
				replyEvent.setServiceTypeId(juv.getOID().toString());
				replyEvent.setServiceTypeCode(juv.getCode());
				replyEvent.setDescription(juv.getDescription());
				replyEvent.setCategory(juv.getGroup());
				replyEvent.setStatus(juv.getStatus());
				dispatch.postEvent(replyEvent);
			}
		}		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	
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
