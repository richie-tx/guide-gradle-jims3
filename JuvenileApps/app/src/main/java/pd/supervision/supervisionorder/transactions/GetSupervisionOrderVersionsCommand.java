/*
 * Created on May 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;

import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.GetSupervisionOrderVersionsEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetSupervisionOrderVersionsCommand implements ICommand
{
	/**
	 * @roseuid 43B2E77C03C8
	 */
	public GetSupervisionOrderVersionsCommand() 
	{
    
	}
   
	/**
	 * @param event
	 * @roseuid 43B2B6ED0244
	 */
	public void execute(IEvent event) 
	{
		 IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		
		 Iterator orderVersions = SupervisionOrderHelper.getOrderVersions((GetSupervisionOrderVersionsEvent)event);
		
		 while(orderVersions.hasNext()){
			SupervisionOrderDetailResponseEvent respEvent = (SupervisionOrderDetailResponseEvent)orderVersions.next();
			dispatch.postEvent(respEvent);
		 }
	}
   
	/**
	 * @param event
	 * @roseuid 43B2B6ED0246
	 */
	public void onRegister(IEvent event) 
	{
    
	}
   
	/**
	 * @param event
	 * @roseuid 43B2B6ED0252
	 */
	public void onUnregister(IEvent event) 
	{
    
	}
   
	/**
	 * @param updateObject
	 * @roseuid 43B2E77C03D8
	 */
	public void update(Object updateObject) 
	{
    
	}

}
