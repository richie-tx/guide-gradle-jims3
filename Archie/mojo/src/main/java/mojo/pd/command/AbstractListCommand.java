package mojo.pd.command;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.dispatch.EventManager;
import mojo.km.transaction.ReadOnlyTransactional;

/**
 * Abstract class used as a base class for all List Commands.  <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 *  Egan Royal          08/27/2001  Created.
 * </pre>
 * @author Egan Royal
 * @modelguid {9625A6A8-B616-49EE-9EB3-893F5E458841}
 */
abstract public class AbstractListCommand implements ICommand, ReadOnlyTransactional {

    /**
     * read is to be implemented by the subclass.  Its main responsibility is to create
     * and return the resulting event that contains the needed Persistent Object data.
     * That event will then be posted.
     *
     * @param anEvent The incoming event.
     * @param anEntityHome
     * @exception Exception
     * @return IEvent The resulting event.
     * @modelguid {61461380-ECF6-4083-9A30-71C58A726E52}
     */
	abstract protected IEvent read(IEvent anEvent, IHome aHome) throws Exception;

    /**
    * returns the strategy to which this command's event will be posted.  This
    * method may be over-ridden by the subclass to change the strategy.
    * @return String strategy.
    * @modelguid {4B04D69C-AB21-4E7B-92FD-A32D2BFEDF78}
    */
    protected String getStrategy() { return EventManager.REQUEST; }


    /**
     * execute takes an event, gets a Home, calls read, and posts the resulting event.
     * The event is posted with EventManager.REPLY if the Reply Topic is null else the
     * event is posted with EventManager.REQUEST.
     * @param anEvent The incoming event. (must of mojo.km.messaging.RequestEvent type)
     * @exception Exception
     * @modelguid {55C3D199-2455-4A09-92D4-AE3E3757433B}
     */
    public void execute(IEvent anEvent) throws Exception {
        IHome lHome = new Home();
        IEvent lResultEvent = read(anEvent, lHome);
        if(lResultEvent != null) {
       		EventManager.getSharedInstance(getStrategy()).postEvent(lResultEvent);
        }
    }

    /**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {E053BCA9-8528-4EBC-AB2F-383F2875C1B9}
     */
	public void onRegister(IEvent anEvent) {
	}

	/**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {1D08B06C-F421-4ED6-B7D6-37B82E418C8C}
     */
	public void onUnregister(IEvent anEvent) { }

	/**
     *If command requires data before execute is called context will place in the command
     *@param object - housing input data
     * @modelguid {9D8A023A-19E6-4AB3-83BF-0B0E237B5D19}
     */
	public void update(Object anObject) { }
}
