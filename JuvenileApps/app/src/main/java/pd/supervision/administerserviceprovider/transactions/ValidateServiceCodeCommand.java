//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\ValidateServiceCodeCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import pd.supervision.administerserviceprovider.Service;
import messaging.administerserviceprovider.ValidateServiceCodeEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDAdministerServiceProviderConstants;

public class ValidateServiceCodeCommand implements ICommand
{

	/**
	 * @roseuid 4473538E00B6
	 */
	public ValidateServiceCodeCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE400C5
	 */
	public void execute(IEvent event)
	{
		ValidateServiceCodeEvent validateEvent = (ValidateServiceCodeEvent) event;

		// check programCode
		if(validateEvent.getServiceCode() != null && !(validateEvent.getServiceCode().equals(""))){
			if(this.checkServiceCode(validateEvent)){
				return;
			}
		}
	}
	
	private boolean checkServiceCode(ValidateServiceCodeEvent validateEvent)
	{
		Iterator iter = Service.findAll(PDAdministerServiceProviderConstants.SERVICE_CODE,validateEvent.getServiceCode());
		if(iter.hasNext()){
		   this.sendServiceProviderErrorResponseEvent("error.exist.serviceCode");
		   return true;
		}
		else {
			return false;
		}	
	}	

	private void sendServiceProviderErrorResponseEvent(String errorKey)
	{
//		DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
		ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();		
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}	

	/**
	 * @param event
	 * @roseuid 44734FE400C7
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE400C9
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 44734FE400D5
	 */
	public void update(Object anObject)
	{

	}
}