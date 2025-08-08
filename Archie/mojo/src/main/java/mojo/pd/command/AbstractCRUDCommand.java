package mojo.pd.command;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * Abstract class used as the base class for all CRUD Commands.  <pre>
 * Change History:
 *
 *  Author          	Date        Explanation
 *  Egan Royal          08/27/2001  Created.
 * </pre>
 * @author Egan Royal
 * @modelguid {EFDC75B4-4E11-421E-9FFB-8278829A3FB4}
 */
abstract public class AbstractCRUDCommand implements ICommand {

	/**
	* Returns the Class Object for the PersistentObject that is being created,
	* updated, or deleted.
	* @return Class The Class Object.
	* @modelguid {94D92910-D48B-446B-A61F-F3A0B30143CD}
	*/
	abstract protected Class getPersistentObjectClass();

    /**
    * returns the strategy to which this command's event will be posted.  This
    * method may be over-ridden by the subclass to change the strategy.
    * @return String strategy.
    * @modelguid {6ABFD209-EB04-498C-876F-02FF14CEB590}
    */
    // MIA: 26 June 02. Changed the Strategy to REPLY rather than Request.
    protected String getStrategy() { return mojo.km.dispatch.EventManager.REPLY; }

	/**
     *Upon command registration with context this method will get executed
     *@param event - sample event data.
     * @modelguid {892131FE-9700-4A76-836C-6DC7CB0BA295}
     */
	public void onRegister(IEvent anEvent) {
	}

	/**
     *Upon command unregistration from context this method will get executed
     *@param event - sample event
     * @modelguid {56C65326-1AA8-41CA-9923-8557AB5960EF}
     */
	public void onUnregister(IEvent anEvent) { }

	/**
     *If command requires data before execute is called context will place in the command
     *@param object - housing input data
     * @modelguid {3DEE8684-DC06-49A3-ACCF-F9D82AF8900F}
     */
	public void update(Object anObject) { }
}