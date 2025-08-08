/*
 * Created on Mar 29, 2006
 *
 */
package pd.supervision.supervisionorder.transactions;

import pd.supervision.supervisionorder.SupervisionOrderReferenceVariableHelper;
import messaging.supervisionorder.GetSupervisionOrderVariableElementReferencesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 * Creates VariableElementResponseEvents for criminal supervision reference variables.
 */
public class GetSupervisionOrderVariableElementReferencesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		GetSupervisionOrderVariableElementReferencesEvent requestEvent =
			(GetSupervisionOrderVariableElementReferencesEvent) event;

		SupervisionOrderReferenceVariableHelper.postSupervisionVariableElementReferenceResponseEvents(requestEvent);
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}

}
