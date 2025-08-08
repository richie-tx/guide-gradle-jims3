// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\UpdateCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.supervision.administercasenotes.Casenote;

public class UpdateCasenoteCommand implements ICommand {

    /**
     * @roseuid 44F462BC016A
     */
    public UpdateCasenoteCommand() {
    }

    /**
     * @param event
     * @roseuid 44EE113F0211
     */
    public void execute(IEvent event) {
        UpdateCasenoteEvent ucEvent = (UpdateCasenoteEvent) event;

        IHome home = new Home();
        Casenote casenote = null;
        String casenoteId = ucEvent.getCasenoteId();
        // check for create or update
        if (casenoteId == null || casenoteId.equals("")) {
            casenote = new Casenote();
            //casenote.updateCasenote(ucEvent);
        } else {
            casenote = (Casenote) home.find(casenoteId, Casenote.class);
        }
        if (casenote != null) {
            casenote.updateCasenote(ucEvent);
            // post a return with the new casenote ID and its status
            CasenoteResponseEvent caseResponse = new CasenoteResponseEvent();
            caseResponse.setCasenoteId(casenote.getOID().toString());
            caseResponse.setCasenoteStatusId(casenote.getCasenoteStatusId());
            caseResponse.setCasenoteTypeCodeId(ucEvent.getCasenoteTypeCodeId());
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(caseResponse);
        } else {
            ReturnException re = new ReturnException();
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(re);
        }

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
