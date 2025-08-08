// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\UpdateCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import messaging.administercasenotes.UpdateCasenoteConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.CasenoteConstants;
import pd.common.DAOContextFactory;
import pd.common.DAOHandler;
import pd.exception.ReflectionException;

public class UpdateCasenoteConditionsCommand implements ICommand {

    /**
     * @roseuid 44F462BC016A
     */
    public UpdateCasenoteConditionsCommand() {
    }

    /**
     * @param event
     * @roseuid 44EE113F0211
     */
    public void execute(IEvent event) {
       UpdateCasenoteConditionsEvent sEvent = (UpdateCasenoteConditionsEvent) event;
   	   DAOContextFactory daoFac = new DAOContextFactory();
	   DAOHandler handler =  null;
	   try {
	   	   handler = (DAOHandler) daoFac.lookup(CasenoteConstants.UPDATE_CASENOTE_CONDITIONS_DAO_LOCATOR);
	   } catch (ReflectionException e) {
		   e.printStackTrace();
	   }
	   handler.execute(sEvent);
    }

    /**
     * @param event
     * @roseuid 44EE113F0219
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 44EE113F021B
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 44F462BC0174
     */
    public void update(Object updateObject) {

    }
}
