// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\commonfunctionality\\spnconsolidation\\transactions\\UpdateSpnConsolidationLogCommand.java

package pd.commonfunctionality.spnconsolidation.transactions;

import pd.commonfunctionality.spnconsolidation.SpnConsolidationLog;
import messaging.spnconsolidation.UpdateSpnConsolidationLogEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class UpdateSpnConsolidationLogCommand implements ICommand {

	/**
	 * @roseuid 452BA25700B2
	 */
	public UpdateSpnConsolidationLogCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4526B0830320
	 */
	public void execute(IEvent event) {

		UpdateSpnConsolidationLogEvent updateEvent = (UpdateSpnConsolidationLogEvent) event;

		SpnConsolidationLog spnLog = new SpnConsolidationLog();
		// check for create or update

		//write log record
		spnLog.setStatus(updateEvent.getStatus());
		spnLog.setAliasSpn(updateEvent.getAliasSpn());
		spnLog.setBaseSpn(updateEvent.getBaseSpn());
		spnLog.setLogDetail( updateEvent.getLogDetail() );
		spnLog.setInvocSource(updateEvent.getInvocSource());
	}

	/**
	 * @param event
	 * @roseuid 4526B083032E
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4526B0830330
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4526B0830332
	 */
	public void update(Object anObject) {

	}

}
