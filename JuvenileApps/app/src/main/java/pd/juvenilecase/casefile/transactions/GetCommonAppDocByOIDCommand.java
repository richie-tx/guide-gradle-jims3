/*
 * Created on Oct 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.casefile.transactions;

import messaging.casefile.GetCommonAppDocByOIDEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CommonAppDocMetadata;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCommonAppDocByOIDCommand implements ICommand{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetCommonAppDocByOIDEvent evt = (GetCommonAppDocByOIDEvent)event;
		CommonAppDocMetadata commonAppDoc = CommonAppDocMetadata.find(evt.getCommonAppDocId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		if(commonAppDoc!=null){
	   		dispatch.postEvent(commonAppDoc.getRespEvt());
   		}
   	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}
}