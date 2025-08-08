package mojo.pd.command;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.persistence.IPersistentObject;
import mojo.km.persistence.ObjectNotFoundException;
import mojo.km.dispatch.EventManager;
import mojo.km.transaction.ReadOnlyTransactional;
import mojo.km.utilities.IOIDable;

/**
 * Abstract class used as a parent class for all commands that read Persistent Objects.  <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 *  Egan Royal          08/27/2001  Created.
 * </pre>
 * @author Egan Royal
 * @modelguid {7EF9A3B3-6828-4811-9E3C-83A054839325}
 */
abstract public class AbstractReadCommand extends AbstractCRUDCommand implements ReadOnlyTransactional {

    /**
     * read is to be implemented by the subclass.  Its main responsibility is to create
     * and return the resulting event that contains the needed Persistent Object data.
     * @param anEvent The incoming event.
     * @param aPersistentObject The PersistentObject to be read.
     * @param anEntityHome
     * @exception Exception
     * @return IEvent The resulting event.
     * @modelguid {7F030C72-787A-4416-90F6-964CD7CC289B}
     */
    protected IEvent read(IEvent anEvent, IPersistentObject aPeristentObject) throws Exception{
		return null;
    }

    /**
     * execute takes an event, gets a Home, calls read, and posts the resulting event.
     * @param anEvent The incoming event. (must implement event.utility.IOIDEvent)
     * @exception Exception
     * @modelguid {8042C911-66CF-4670-B505-86206D7EB77E}
     */
    public void execute(IEvent anEvent) throws Exception {
        IHome lHome = new Home();
        IOIDable lOIDEvent = null;
        try {
            lOIDEvent = (IOIDable)anEvent;
        } catch (ClassCastException e) {
            throw new Exception("AbstractReadCommand must use an implementation of IOIDEvent.\n" + e);
        }
        IPersistentObject lObject = null;
        IEvent lResultEvent = null;
        try {
            lObject = (IPersistentObject)lHome.find(lOIDEvent.getOID(), getPersistentObjectClass());
            lResultEvent = read(anEvent, lObject);
        } catch (ObjectNotFoundException e) {
            throw new Exception("Object Not Found - Unable to find " +
                getPersistentObjectClass().getName() + " for read.");
        }
        if(lResultEvent != null) {
        	EventManager.getSharedInstance(getStrategy()).postEvent(lResultEvent);
        }
    }
}
