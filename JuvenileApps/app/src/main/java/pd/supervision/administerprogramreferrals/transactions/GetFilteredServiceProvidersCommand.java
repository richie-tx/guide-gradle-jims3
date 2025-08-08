package pd.supervision.administerprogramreferrals.transactions;

import java.util.List;

import messaging.administerprogramreferrals.GetFilteredServiceProvidersEvent;
import messaging.csserviceprovider.FilterByServiceProviderEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProviderHelper;

public class GetFilteredServiceProvidersCommand implements ICommand
{
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		GetFilteredServiceProvidersEvent filterEvent = (GetFilteredServiceProvidersEvent)event;
		
		FilterByServiceProviderEvent filterServiceProviderEvent = new FilterByServiceProviderEvent();
		filterServiceProviderEvent.setReferralTypes(filterEvent.getReferralTypes());
		filterServiceProviderEvent.setRegiondCdsList(filterEvent.getRegionCdsList());
		filterServiceProviderEvent.setLanguagesOfferedList(filterEvent.getLanguagesOfferedList());
		filterServiceProviderEvent.setSexSpecific(filterEvent.getSexSpecific());
		filterServiceProviderEvent.setContractProgram(filterEvent.getContractProgram());
		
		List csServiceProviderList = CSServiceProviderHelper.searchServiceProvider(filterServiceProviderEvent);
		List sp_responses = CSServiceProviderHelper.getServiceProviderReftypeResponses(csServiceProviderList);
		
		MessageUtil.postReplies(sp_responses);
	}

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
