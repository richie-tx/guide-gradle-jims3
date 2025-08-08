// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import messaging.administercasenotes.GetCasenoteByOIDEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteHelper;

public class GetCasenoteByOIDCommand implements ICommand {

	/**
	 * @roseuid 44F4632F0377
	 */
	public GetCasenoteByOIDCommand() {

	}

	/**
	 * @param event
	 * @roseuid 44EE113903B6
	 */
	public void execute(IEvent event) {
		GetCasenoteByOIDEvent inEvent = (GetCasenoteByOIDEvent) event;

        Casenote casenote = Casenote.find( inEvent.getCasenoteId());
        CasenoteHelper helper = CasenoteHelper.getInstance();
        CasenoteResponseEvent response = helper.getCasenoteResponse( casenote );
        
        MessageUtil.postReply( response );
	}

	/**
	 * @param event
	 * @roseuid 44EE113903BF
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 44EE113903C1
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 44F4632F038B
	 */
	public void update(Object updateObject) {

	}
}
