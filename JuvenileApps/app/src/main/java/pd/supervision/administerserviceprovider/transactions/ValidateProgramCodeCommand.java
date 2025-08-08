//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\ValidateProgramCodeCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import pd.supervision.administerserviceprovider.ProviderProgram;
import messaging.administerserviceprovider.ValidateProgramCodeEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDAdministerServiceProviderConstants;

public class ValidateProgramCodeCommand implements ICommand
{

	/**
	 * @roseuid 4473538D01D0
	 */
	public ValidateProgramCodeCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FEC000C
	 */
	public void execute(IEvent event)
	{
		ValidateProgramCodeEvent validateEvent = (ValidateProgramCodeEvent) event;

		// check programCode
		if(validateEvent.getProgramCode() != null && !(validateEvent.getProgramCode().equals(""))){
			if(this.checkProgramCode(validateEvent)){
				return;
			}
		}		
	}
	
	private boolean checkProgramCode(ValidateProgramCodeEvent validateEvent)
	{
		Iterator iter = ProviderProgram.findAll(PDAdministerServiceProviderConstants.PROGRAM_CODE,validateEvent.getProgramCode());
		if(iter.hasNext()){
		   this.sendServiceProviderErrorResponseEvent("error.exist.programCode");
		   return true;
		}
		else {
			return false;
		}	
	}	

	private void sendServiceProviderErrorResponseEvent(String errorKey)
	{
		ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();		
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}
	/**
	 * @param event
	 * @roseuid 44734FEC0019
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FEC001B
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 44734FEC001D
	 */
	public void update(Object anObject)
	{

	}
}