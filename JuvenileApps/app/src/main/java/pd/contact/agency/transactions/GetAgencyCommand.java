/*
 * Created on Jun 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.contact.agency.transactions;

import naming.ResponseLocatorConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.PDContactHelper;
import pd.contact.agency.Agency;
import messaging.agency.GetAgencyEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetAgencyCommand extends ResponseCommonUtil implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetAgencyEvent thisEvent = (GetAgencyEvent) event;
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator aCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.AGENCY_RESPONSE_LOCATOR,respFac);
				
		Agency agency = Agency.find(thisEvent.getAgencyId());
		if (agency != null)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			AgencyResponseEvent agencyEvent = (AgencyResponseEvent) aCreator.create(agency);
			dispatch.postEvent(agencyEvent);
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
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}
}