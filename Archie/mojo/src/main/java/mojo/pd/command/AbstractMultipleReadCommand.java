package mojo.pd.command;


import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.persistence.*;
import mojo.km.dispatch.EventManager;
import mojo.km.context.ContextManager;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.transaction.ReadOnlyTransactional;
import java.util.Iterator;
import mojo.km.utilities.IOIDEvent;

/**
 * Abstract class used as a parent class for all commands that read multiple Persistent Objects.  <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 * </pre>
 * @modelguid {1EC8B8EB-2938-481C-BAF2-1B84FCDA60D8}
 */
abstract public class AbstractMultipleReadCommand extends AbstractCRUDCommand implements ReadOnlyTransactional {
    /**
     * read is to be implemented by the subclass.  Its main responsibility is to create
     * and return the resulting event that contains the needed Persistent Object data.
     * @param anEvent The incoming event.
     * @param aPersistentObject The PersistentObject to be read.
     * @exception <{Exception}>
     * @return IEvent The resulting event.
     * @modelguid {82409C41-6BEF-4E1B-A208-F4C7D9676DF4}
     */
    abstract protected IEvent read(IEvent anEvent, IPersistentObject aPeristentObject) throws Exception;

    /**
     * getIOIDEvents is to be implemented by the subclass.  Its main responsibility is to
     * return an iterator on a collection of IOIDEvents that correspond to the OIDs of the
     * persistent object being requested
     * @param anEvent The incoming event.
     * @exception <{Exception}>
     * @return Iterator The resulting iterator.
     * @modelguid {A650E2AB-EECF-437B-BF9A-1A7622E75B72}
     */
    abstract protected Iterator getIOIDEvents(IEvent anEvent) throws Exception;


    /**
     * execute takes an event, gets a Home, calls read, and posts the resulting event(s).
     * @param anEvent The incoming event. (must implement event.utility.IOIDEvent)
     * @exception <{Exception}>
     * @modelguid {43346A6B-BA81-453A-BD38-54EB88096CA5}
     */
    public void execute(IEvent anEvent) throws Exception {
        IHome lHome = new Home();
        IOIDEvent lOIDEvent = null;
		for (Iterator i = getIOIDEvents(anEvent); i.hasNext(); ) {
			try {
				lOIDEvent = (IOIDEvent) i.next();
			} catch (ClassCastException e) {
				throw new Exception("AbstractReadCommands must use an implementation of IOIDEvent.\n" + e);
			}
			IPersistentObject lObject = null;
			IEvent lResultEvent = null;
			lObject = (IPersistentObject)lHome.find(lOIDEvent.getOID(), getPersistentObjectClass());
	
			lResultEvent = read(anEvent, lObject);
			if (lResultEvent != null) {
				EventManager.getSharedInstance(EventManager.REPLY).postEvent(lResultEvent);
			}
        }
    }
}
