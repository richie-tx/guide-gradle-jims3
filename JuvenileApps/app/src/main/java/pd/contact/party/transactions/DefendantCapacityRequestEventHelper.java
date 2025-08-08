/*
 * Created on Oct 29, 2004
 *
 */
package pd.contact.party.transactions;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.persistence.PersistentObject;
import pd.helper.ICompositeRequestHelper;

/**
 * @author dgibler
 *
 */
public class DefendantCapacityRequestEventHelper implements ICompositeRequestHelper
{

	/** 
	 * @see ICompositeRequestHelper#update(mojo.km.messaging.Composite.CompositeRequest, mojo.km.messaging.RequestEvent)
	 */
	public void update(CompositeRequest parentEvent, RequestEvent childEvent, PersistentObject persistentObject)
	{
	}

}
