// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import messaging.administercasenotes.GetConditionCasenotesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.CasenoteConstants;
import pd.common.DAOContextFactory;
import pd.common.DAOHandler;
import pd.exception.ReflectionException;

public class GetConditionCasenotesCommand implements ICommand {

	/**
	 * @roseuid 44F4632F0377
	 */
	public GetConditionCasenotesCommand() {

	}

	/**
	 * @param event
	 * @roseuid 44EE113903B6
	 */
	public void execute(IEvent event) {
		GetConditionCasenotesEvent gEvent = (GetConditionCasenotesEvent) event;
   	    DAOContextFactory daoFac = new DAOContextFactory();
	    DAOHandler handler =  null;
	    try {
	   	    handler = (DAOHandler) daoFac.lookup(CasenoteConstants.GET_CASENOTE_CONDITIONS_DAO_LOCATOR);
	    } catch (ReflectionException e) {
		    e.printStackTrace();
	    }
	    handler.execute(gEvent);
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
