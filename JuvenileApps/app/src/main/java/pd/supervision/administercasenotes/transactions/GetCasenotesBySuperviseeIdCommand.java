// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import java.util.Iterator;

import messaging.administercasenotes.GetCasenotesBySuperviseeIdEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteHelper;

public class GetCasenotesBySuperviseeIdCommand implements ICommand {

	/**
	 * @roseuid 44F4632F0377
	 */
	public GetCasenotesBySuperviseeIdCommand() {

	}

	/**
	 * @param event
	 * @roseuid 44EE113903B6
	 */
	public void execute(IEvent event) {
		GetCasenotesBySuperviseeIdEvent inEvent = (GetCasenotesBySuperviseeIdEvent) event;
		String superviseeId = inEvent.getSuperviseeId();
		while(superviseeId.length() < 8){
			superviseeId = "0" + superviseeId;
		}
		inEvent.setSuperviseeId(superviseeId);
		
        Iterator casenotesIter = Casenote.findAll(inEvent);
        CasenoteHelper helper = CasenoteHelper.getInstance();
        helper.postCasenoteResponses(casenotesIter);
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
