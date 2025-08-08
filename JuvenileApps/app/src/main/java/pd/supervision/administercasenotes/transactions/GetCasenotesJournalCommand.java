// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import java.util.Iterator;

import messaging.administercasenotes.GetCasenotesForActivePeriod;
import messaging.administercasenotes.GetCasenotesJournalEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.SpnCasenotesHandler;

public class GetCasenotesJournalCommand implements ICommand {

    /**
     * This command retrieves casenotes for a Supervisee and returns the last 20
     * by entry date.
     */
    public GetCasenotesJournalCommand() {

    }

    /**
     * @param event
     */
    public void execute(IEvent event) {
        GetCasenotesJournalEvent inEvent = (GetCasenotesJournalEvent) event;
        Iterator iter = null;
        inEvent.setSpn(SpnCasenotesHandler.padSpn(inEvent.getSpn()));
        if (inEvent.isActiveSupervisionPeriod()) {
            GetCasenotesForActivePeriod getActiveEvent = new GetCasenotesForActivePeriod();
            getActiveEvent.setSpn(inEvent.getSpn());
            getActiveEvent.setUserAgencyId(inEvent.getUserAgencyId());
            iter = Casenote.sortByEntryDate(Casenote.findAll(getActiveEvent));
        } else {
            iter = Casenote.sortByEntryDate(Casenote.findAll(inEvent));
        }

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        int numberOfCasenotes = 0;
        if (iter != null && iter.hasNext()) {
            while (iter.hasNext()) {
                Casenote casenote = (Casenote) iter.next();
                CasenoteResponseEvent response = new CasenoteResponseEvent();

                casenote.fillCasenote(response);
                dispatch.postEvent(response);
                numberOfCasenotes++;
                // only want the last 20
                if (numberOfCasenotes == 20) {
                    break;
                }
            }
        }

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
