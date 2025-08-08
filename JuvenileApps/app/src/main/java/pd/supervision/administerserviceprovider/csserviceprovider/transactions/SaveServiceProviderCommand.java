/*
 * Created on Dec 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider.transactions;

import pd.supervision.administerserviceprovider.csserviceprovider.*;
import messaging.csserviceprovider.SaveServiceProviderEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveServiceProviderCommand implements ICommand {

    /* Save a CS Service Provider
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event)
    {
        	// save cs service provider using the specified event info
        SaveServiceProviderEvent save_sp_event = 
            (SaveServiceProviderEvent)event;
        CSServiceProvider cs_service_provider = 
            	CSServiceProviderHelper.saveServiceProvider(save_sp_event);

        if (cs_service_provider != null)
        {
        	//create a service provider response event for the given service provider
            CSServiceProviderResponseEvent sp_response_event =
                CSServiceProviderHelper.getServiceProviderResponseEvent(cs_service_provider);
            MessageUtil.postReply(sp_response_event);            
        }
    }//end of execute()

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {
        // TODO Auto-generated method stub

    }

}
