// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\DeleteCasenoteCommand.java

package pd.supervision.administercasenotes.transactions;

import messaging.administercasenotes.DeleteCasenoteEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.supervision.administercasenotes.Casenote;

public class DeleteCasenoteCommand implements ICommand {

    /**
     * @roseuid 44F4632D0283
     */
    public DeleteCasenoteCommand() {

    }

    /**
     * @param event
     * @roseuid 44F459640026
     */
    public void execute(IEvent event) {
        DeleteCasenoteEvent dcEvent = (DeleteCasenoteEvent) event;

        // retrieve the casenote
        Casenote casenote = (Casenote) new Home().find(dcEvent.getCasenoteId(), Casenote.class);

        if (casenote != null) {
        	
        	casenote.delete();
            //casenote.delete(dcEvent);
        } 
    }

    /**
     * @param event
     * @roseuid 44F459640028
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 44F459640030
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param updateObject
     * @roseuid 44F4632D028D
     */
    public void update(Object updateObject) {

    }
}
