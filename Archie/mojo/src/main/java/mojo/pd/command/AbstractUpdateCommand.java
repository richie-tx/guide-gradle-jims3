package mojo.pd.command;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.persistence.IPersistentObject;
import mojo.km.persistence.ObjectNotFoundException;
import mojo.km.dispatch.EventManager;
import mojo.km.transaction.Transactional;
import mojo.km.utilities.IOIDEvent;

/**
 * Abstract class used as a parent class for all commands that update Persistent Objects.  <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 *  Egan Royal          08/27/2001  Created.
 * </pre>
 * @author Egan Royal
 * @modelguid {CB9BBA02-590A-4BA5-8CFF-0886C3261E5A}
 */
abstract public class AbstractUpdateCommand extends AbstractCRUDCommand implements Transactional {

    /**
     * update is to be implemented by the subclass.  Its main responsibilities include
     * updating the PersistentObject and creating and returning the resulting event.
     * @param anEvent The incoming event.
     * @param aPersistentObject The PersistentObject to be read.
     * @param anEntityHome
     * @exception Exception
     * @return IEvent The resulting event.
     * @modelguid {BFD45E7C-72D9-4BF3-9AAB-0B3B7D8F8CF6}
     */
	protected IEvent update(IEvent anEvent, IPersistentObject aPeristentObject) throws Exception{
		return null;
    }

    /**
     * execute takes an event, gets a Home, calls update, and posts the resulting event.
     * @param anEvent The incoming event. (must implement event.utility.IOIDEvent)
     * @exception Exception
     * @modelguid {DC8ACCF8-FCDD-4D12-8CFA-E37704550B14}
     */
    public void execute(IEvent anEvent) throws Exception {
        IHome lHome = new Home();
        IOIDEvent lOIDEvent = null;
        try {
            lOIDEvent = (IOIDEvent)anEvent;
        } catch (ClassCastException e) {
            throw new Exception("AbstractUpdateCommands must use an implementation of IOIDEvent.\n" + e);
        }
        IPersistentObject lObject = null;
        IEvent lResultEvent = null;
        try {
            lObject = (IPersistentObject)lHome.find(lOIDEvent.getOID(), getPersistentObjectClass());
            lResultEvent = update(anEvent, lObject);
        } catch (ObjectNotFoundException e) {
            throw new Exception("Object Not Found - Unable to find " +
            	getPersistentObjectClass().getName() + " for update.");
        }
        if(lResultEvent != null) {
        	EventManager.getSharedInstance(getStrategy()).postEvent(lResultEvent);
        }
    }
}
