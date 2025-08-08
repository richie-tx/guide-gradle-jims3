/*
 * Created on Aug 28, 2008
 *
 */
package pd.supervision.legacyupdates;

import messaging.legacyupdates.ProcessLegacyUpdatesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.LegacyUpdatesControllerServiceNames;

/**
 * @author mchowdhury
 *
 */

public class LegacyUpdatesScheduler
{
	private boolean done = false;
	private static final String SLEEP_TIME = "60000"; //5 minutes
	private static LegacyUpdatesScheduler schedular = null;

	/**
	 * Private Constructor
	 */
	public LegacyUpdatesScheduler() 
	{		
	}

	public void execute() throws InterruptedException
	{
        //Process process = new Process();
		/*try{
			Iterator iter = Process.findAll(LegacyUpdateConstants.ATTRIBUTE_NAME, PDConstants.PROCESS_SCHEDULER);
			while(iter.hasNext()){
				process = (Process) iter.next();
				break;
			}
			if(process == null || !process.isActive()){
				System.out.println("There is no process in the data store or the process is inactive.");
				return;			
		    }
		}catch(Exception e){
			e.printStackTrace();
			return;
		}*/
		
		while(done == false)
		{			
			this.processRequest();
	        try {
				Thread.sleep(Long.parseLong(SLEEP_TIME));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void processRequest() {
		ProcessLegacyUpdatesEvent requestEvent =
        	(ProcessLegacyUpdatesEvent) EventFactory.getInstance(
        LegacyUpdatesControllerServiceNames.PROCESSLEGACYUPDATES);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);        

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        MessageUtil.filterComposite(compositeResponse, ReturnException.class);        
	}

	public static void main(String[] args)
	{	    
        mojo.km.context.ContextManager.currentContext();
        
		if (schedular == null)
        {
			schedular = new LegacyUpdatesScheduler();
        }
		
		try
		{
			schedular.execute();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	

}
