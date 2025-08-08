//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\CreateJuvenileAssociateCommand.java

package pd.juvenilewarrant.transactions;

import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.juvenilewarrant.CreateJuvenileAssociateEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateJuvenileAssociateCommand implements ICommand
{

	/**
	 * @roseuid 421607D101A5
	 */
	public CreateJuvenileAssociateCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 4216049C0177
	 */
	public void execute(IEvent event)
	{
		CreateJuvenileAssociateEvent associateEvent = (CreateJuvenileAssociateEvent) event;
		PDJuvenileWarrantHelper.createJuvenileAssociate(associateEvent);
	}

	/**
	 * @param event
	 * @roseuid 4216049C0179
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4216049C017B
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 421607D101B5
	 */
	public void update(Object anObject)
	{

	}

}
