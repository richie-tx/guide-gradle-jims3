package pd.common.refvariable.transactions;

import messaging.refvariable.ResolveReferencesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.common.refvariable.ReferenceResolver;

/**
 * @author bschwartz
 *
 * General reference resolver.
 */
public class ResolveReferencesCommand implements ICommand, ReadOnlyTransactional
{

	/**
	@roseuid 411295740136
	 */
	public ResolveReferencesCommand()
	{
	}

	/**
	@param event
	@roseuid 4112952C02DA
	 */
	public void execute(IEvent event)
	{
		ResolveReferencesEvent request = (ResolveReferencesEvent) event;
		ReferenceResolver resolver = new ReferenceResolver( );
		ReferenceResolver.postReferenceVariables(request.getCasefileId(),request.getReferenceNames());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	}

	/**
	@param event
	@roseuid 4112952C02E3
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 4112952C02E5
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 4112952C02ED
	 */
	public void update(Object updateObject)
	{

	}
}
