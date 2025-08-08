package mojo.pd.command;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.dispatch.EventManager;
import mojo.km.transaction.Transactional;

/**
 * Abstract class used as a parent class for all commands that create Persistent Objects.  <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 *  Egan Royal          08/27/2001  Created.
 * </pre>
 * @author Egan Royal
 * @modelguid {7D63069C-9A21-4CD5-AEE1-C5E53B77E595}
 */
abstract public class AbstractCreateCommand extends AbstractCRUDCommand implements Transactional {

    /**
    * create is to be implemented by the subclass.  Its main responsibilities include
    * creating the PersistentObject and returning a result event.
    * @param anEvent The incoming event.
    * @param anEntityHome
    * @exception Exception
    * @return IEvent The resulting event.
    * @modelguid {0DC3FF06-FD4D-479D-BAD0-680B46847C68}
    */
    abstract protected IEvent create(IEvent anEvent) throws Exception;

    /**
     * execute takes an event, gets a Home, calls create, and posts the resulting event.
     * @param anEvent The incoming event.
     * @exception Exception
     * @modelguid {52CC4655-50DB-436E-BF9B-E750E19EDE71}
     */
    public void execute(IEvent anEvent) throws Exception {
        IEvent lResultEvent = create(anEvent);
        if(lResultEvent != null) {
        	EventManager.getSharedInstance(getStrategy()).postEvent(lResultEvent);
        }
    }
}
