//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\GetJuvenileWarrantServicesCommand.java

/*
 * Created on Feb 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilewarrant.transactions;

import java.util.Iterator;

import pd.juvenilewarrant.JuvenileWarrantService;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;

import messaging.juvenilewarrant.GetJuvenileWarrantServicesEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author asrvastava
 *
 * This command posts Service Response Events for each JuvenileWarrantService object searched. 
 */
public class GetJuvenileWarrantServicesCommand implements ICommand 
{
   
   /**
    * @roseuid 420A641401B5
    */
   public GetJuvenileWarrantServicesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 420A6144007E
    */
   public void execute(IEvent event) 
   {
		GetJuvenileWarrantServicesEvent requestEvent = (GetJuvenileWarrantServicesEvent)event;

		Iterator services = JuvenileWarrantService.findAll(requestEvent);

		//	Get the IDispatch to post to
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		// Iterate through the warrants and post the JuvenileWarrantServiceResponseEvent for each
		while (services.hasNext())
		{
			JuvenileWarrantService service = (JuvenileWarrantService) services.next();
			JuvenileWarrantServiceResponseEvent warrantServiceEvent = PDJuvenileWarrantHelper.getJuvenileWarrantServiceResponseEvent(service);
			if (warrantServiceEvent != null)
			{
				dispatch.postEvent(warrantServiceEvent);
			}
		}    
   }
   
   /**
    * @param event
    * @roseuid 420A61440080
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 420A61440082
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 420A61440084
    */
   public void update(Object anObject) 
   {
    
   }
   
}
