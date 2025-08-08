//Source file: \\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderInstructorsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Hashtable;
import java.util.Iterator;

import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.PDAdminsterServiceProviderHelper;
import messaging.administerserviceprovider.GetServiceProviderActiveInstructorsEvent;
import messaging.administerserviceprovider.GetServicesByServiceProviderEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetServiceProviderActiveInstructorsCommand implements ICommand
{

    @Override
    public void execute(IEvent event)
    {
	//Hashtable<String, String> uniqueInstructor = new Hashtable<String, String>();
	GetServiceProviderActiveInstructorsEvent spEvent = (GetServiceProviderActiveInstructorsEvent) event;

	Iterator iter = InhouseSP_Profile.findAll(spEvent);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	while (iter.hasNext())
	{
	    InhouseSP_Profile juvenileServiceProvider = (InhouseSP_Profile) iter.next();	   
	    //String uniqueName = juvenileServiceProvider.getFirstName() + juvenileServiceProvider.getLastName();
	    /*if (!uniqueInstructor.containsKey(uniqueName))
	    {*/
		ServiceProviderContactResponseEvent cResp = PDAdminsterServiceProviderHelper.getInHouseContactRespnseEvent(juvenileServiceProvider);
		dispatch.postEvent(cResp);	
		//uniqueInstructor.put(uniqueName, "");
	    //}
	}
    }

    /**
     * @param event
     * @roseuid 447F49960170
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 447F4996017D
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 447F4996017F
     */
    public void update(Object anObject)
    {

    }
}