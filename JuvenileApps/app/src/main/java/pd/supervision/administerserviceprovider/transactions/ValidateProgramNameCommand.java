/*
 * Created on Oct 4, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import naming.PDAdministerServiceProviderConstants;
import pd.supervision.administerserviceprovider.ProviderProgram;
import messaging.administerserviceprovider.ValidateProgramNameEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateProgramNameCommand  implements ICommand {

	
	   /**
	    * @param event
	    * @roseuid 450AA17901AA
	    */
	public void execute(IEvent event) {
		ValidateProgramNameEvent validateEvent = (ValidateProgramNameEvent) event;
		
		// check serviceProviderId
		if(validateEvent.getServiceProviderId() != null && !(validateEvent.getServiceProviderId().equals(""))){
			if(this.checkProgramName(validateEvent)){
				return;
			}
		}
		
	}
	private boolean checkProgramName(ValidateProgramNameEvent validateEvent) {
		Iterator iter = ProviderProgram.findAll(PDAdministerServiceProviderConstants.JUV_SERVICE_PROVIDER_ID, validateEvent.getServiceProviderId());
		ArrayList listProgramNames = new ArrayList();
		while(iter.hasNext()){
			ProviderProgram program = (ProviderProgram)iter.next();
			if(program.getStatusId() != PDAdministerServiceProviderConstants.INACTIVE) {
				listProgramNames.add(program.getProgramName());
			}
		}
		for(int j=0; j<listProgramNames.size(); j++){
			if(listProgramNames.get(j).toString().equalsIgnoreCase(validateEvent.getProgramName())) {
				this.sendServiceProviderErrorResponseEvent("error.duplicate.programName");
				return true;
			}					
		}
		return false;
	}
		/**
	 * @param errorKey
	 */
	private void sendServiceProviderErrorResponseEvent(String errorKey)
	{
		ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();		
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
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
