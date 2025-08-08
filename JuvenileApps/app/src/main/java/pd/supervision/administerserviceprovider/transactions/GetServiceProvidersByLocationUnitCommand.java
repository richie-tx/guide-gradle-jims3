//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderServicesCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList ;
import java.util.Collection ;
import java.util.HashMap ;
import java.util.Iterator ;

import messaging.administerserviceprovider.GetServiceProviderServicesEvent ;
import messaging.administerserviceprovider.GetServiceProvidersByLocationUnitEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent ;
import messaging.administerserviceprovider.reply.ServiceResponseEvent ;
import mojo.km.context.ICommand ;
import mojo.km.dispatch.EventManager ;
import mojo.km.dispatch.IDispatch ;
import mojo.km.messaging.IEvent ;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider ;

public class GetServiceProvidersByLocationUnitCommand implements ICommand
{
    
	/**
	 * @roseuid 44805C7E0327
	 */
	public GetServiceProvidersByLocationUnitCommand( )
	{
	}

	/**
	 * @param event
	 * @roseuid 447F49A502A4
	 */
	public void execute( IEvent event )
	{
	    GetServiceProvidersByLocationUnitEvent sps = (GetServiceProvidersByLocationUnitEvent)event ;
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY ) ;
		dispatch.postEvent( sps ) ;
		
	}

	/**
	 * @param sp
	 * @return resp
	 */
	private ServiceResponseEvent createServiceResponseEvent( JuvenileServiceProvider sp )
	{
		ServiceResponseEvent resp = new ServiceResponseEvent() ;
		
		resp.setServiceName( sp.getServiceName() ) ;
		resp.setServiceId( "" + sp.getServiceId() ) ;
		resp.setMaxEnrollment( sp.getMaxEnrollment() ) ;
		resp.setProgramStartDate( sp.getProgramStartDate() ) ;
		resp.setProgramEndDate( sp.getProgramEndDate() ) ;
		
		return resp ;
	}

	/**
	 * @param map
	 */
	private void postResponseEvent( HashMap map )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REPLY ) ;
		Iterator iter = map.values().iterator() ;
		while( iter.hasNext() )
		{
			ServiceProviderResponseEvent spEvent = (ServiceProviderResponseEvent)iter.next() ;
			dispatch.postEvent( spEvent ) ;
		}
	}


}