/*
 * Created on Oct 25, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.helper;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.persistence.PersistentObject;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ICompositeRequestHelper
{
	/**
	 * 
	 * @param parentEvent
	 * @param childEvent
	 * @param persistentObject
	 */
	 void update(CompositeRequest parentEvent, RequestEvent childEvent, PersistentObject persistentObject);
}
