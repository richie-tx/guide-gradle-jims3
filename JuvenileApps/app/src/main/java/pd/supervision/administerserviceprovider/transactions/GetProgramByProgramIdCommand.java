/*
 * Created on Oct 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.transactions;

import pd.supervision.administerserviceprovider.ProviderProgram;
import messaging.administerserviceprovider.GetProgramByProgramIdEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
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
public class GetProgramByProgramIdCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetProgramByProgramIdEvent reqEvent = (GetProgramByProgramIdEvent)event; 
		
		ProviderProgram program = ProviderProgram.find(reqEvent.getProviderProgramId());
				
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		if (program != null) {
			ProviderProgramResponseEvent respEvent = new ProviderProgramResponseEvent();
		    respEvent.setProgramName(program.getProgramName());
		    respEvent.setTjjdEdiCode(program.getTjjdEdiCode());
		    respEvent.setProgramCodeId(program.getProgramCode());
		    respEvent.setStateProgramCodeId(program.getStateProgramCodeId());
		    respEvent.setTargetInterventionId(program.getTargetInterventionId());
		    respEvent.setProgramStatusId(program.getStatusId());
		    respEvent.setStartDate(program.getStartDate());
		    respEvent.setEndDate(program.getEndDate());
		    respEvent.setProgramDescription(program.getDescription());
		    respEvent.setProviderProgramId(program.getOID().toString());
		    respEvent.setProgramTypeId(program.getProgramTypeId());
		    respEvent.setTransferredProgRef(program.getTransferredProgRef());
		    respEvent.setDiscontinueDate(program.getDiscontinueDate());
		    respEvent.setSupervisionCategory(program.getSupervisionCategory());
		    respEvent.setMaxYouth(program.getMaxYouth());  //added for US 190589
		    dispatch.postEvent(respEvent);
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
