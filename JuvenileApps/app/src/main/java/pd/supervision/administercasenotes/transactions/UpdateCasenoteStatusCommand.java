/*
 * Created on Nov 15, 2006
 *
 */
package pd.supervision.administercasenotes.transactions;

import naming.CasenoteConstants;
import pd.supervision.administercasenotes.Casenote;
import messaging.administercasenotes.UpdateCasenoteStatusEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *  
 */
public class UpdateCasenoteStatusCommand implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		UpdateCasenoteStatusEvent updateEvent = (UpdateCasenoteStatusEvent) event;
		Casenote casenote = Casenote.find(updateEvent.getCasenoteId());
		if (casenote != null) {
			//Set autoSaved if casenote status is draft
			if (updateEvent.getAutoSaveAsDraft() == true && casenote.getCasenoteStatusId().equals(CasenoteConstants.STATUS_DRAFT)) {
				casenote.setAutoSaved(true);
			}
			if (!updateEvent.getStatusId().equals(casenote.getCasenoteStatusId())) {
				casenote.setCasenoteStatusId(updateEvent.getStatusId());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}

}
