package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import naming.PDAdministerServiceProviderConstants;
import pd.supervision.administerserviceprovider.ProviderProgram;
import messaging.administerserviceprovider.GetProgramByProgramCodeEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetProgramByProgramCodeCommand implements ICommand {
	
	public void execute(IEvent event) {
		GetProgramByProgramCodeEvent reqEvent = (GetProgramByProgramCodeEvent)event; 
		Iterator iter = ProviderProgram.findAll(PDAdministerServiceProviderConstants.PROGRAM_CODE,reqEvent.getProgramCode());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			ProviderProgram program = (ProviderProgram) iter.next();
			if (program != null) {
				ProviderProgramResponseEvent respEvent = new ProviderProgramResponseEvent();
				respEvent.setProgramName(program.getProgramName());
				respEvent.setProgramCodeId(program.getProgramCode());
				respEvent.setStateProgramCodeId(program.getStateProgramCodeId());
				respEvent.setTargetInterventionId(program.getTargetInterventionId());
				respEvent.setProgramStatusId(program.getStatusId());
				respEvent.setStartDate(program.getStartDate());
				respEvent.setEndDate(program.getEndDate());
				respEvent.setProgramDescription(program.getDescription());
				respEvent.setProviderProgramId(program.getOID().toString());
				respEvent.setProgramTypeId(program.getProgramTypeId());
				dispatch.postEvent(respEvent);
			}
		}
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
