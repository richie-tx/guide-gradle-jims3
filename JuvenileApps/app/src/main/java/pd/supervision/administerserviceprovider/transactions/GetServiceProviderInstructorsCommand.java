//Source file: \\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderInstructorsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Hashtable;
import java.util.Iterator;

import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.OutSourcedSP_Profile;
import pd.supervision.administerserviceprovider.PDAdminsterServiceProviderHelper;
import messaging.administerserviceprovider.GetServiceProviderInstructorsEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetServiceProviderInstructorsCommand implements ICommand
{
	/**
	 * @roseuid 44805C7D0133
	 */
	public GetServiceProviderInstructorsCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 447F49A60071
	 */
	public void execute(IEvent event)
	{
		GetServiceProviderInstructorsEvent insEvent = (GetServiceProviderInstructorsEvent)event;
		
		Hashtable<String,String> uniqueInstructor = new Hashtable<String,String>();
		
		if( notNullNotEmptyString(insEvent.getServiceProviderId()) )
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

			if( insEvent.isInHouse() )
			{
				Iterator<InhouseSP_Profile> iSPIter = InhouseSP_Profile.findAll(
						"serviceProviderId", "" + insEvent.getServiceProviderId());
				while( iSPIter.hasNext() )
				{
					InhouseSP_Profile in = iSPIter.next();
					if( in != null && !"I".equalsIgnoreCase(in.getStatusId()))
					{	
					    	String uniqueName = in.getFirstName()+ in.getLastName();
					    	if(!uniqueInstructor.containsKey(uniqueName))
					    	{
					    	    ServiceProviderContactResponseEvent cResp = 
					    		    PDAdminsterServiceProviderHelper.getInHouseContactRespnseEvent(in);
					    	    dispatch.postEvent(cResp);
					    	    uniqueInstructor.put(uniqueName,"");
					    	}
						
					}
				}
			}
			else
			{
				Iterator<OutSourcedSP_Profile> oSPIter = OutSourcedSP_Profile.findAll(
						"serviceProviderId", "" + insEvent.getServiceProviderId());
				while( oSPIter.hasNext() )
				{
					OutSourcedSP_Profile out = oSPIter.next();
					if( out != null && !"I".equalsIgnoreCase(out.getStatusId()))
					{	String uniqueName = out.getFirstName()+ out.getLastName();
						if(!uniqueInstructor.containsKey(uniqueName))
						{
						    ServiceProviderContactResponseEvent cResp = 
							    PDAdminsterServiceProviderHelper.getInHouseContactRespnseEvent(out);
						    dispatch.postEvent(cResp);
						    uniqueInstructor.put(uniqueName,"");
						}
					}	
				}
			}
		} // if notNullNotEmptyString
	}

	/*
	 * given a string, return true if it's not null and not empty.
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) );
	}
	
	/**
	 * @param event
	 * @roseuid 447F49A6007F
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 447F49A60081
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param anObject
	 * @roseuid 447F49A6008E
	 */
	public void update(Object anObject)
	{
	}
}