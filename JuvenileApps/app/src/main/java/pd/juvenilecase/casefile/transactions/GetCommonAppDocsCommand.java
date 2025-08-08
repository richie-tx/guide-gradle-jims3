/*
 * Created on Oct 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import messaging.casefile.GetCommonAppDocsEvent;
import messaging.casefile.reply.CommonAppDocResponseEvent;
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
public class GetCommonAppDocsCommand implements ICommand{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetCommonAppDocsEvent evt = (GetCommonAppDocsEvent)event;
		Iterator commonAppDocs = CommonAppDocMetadata.findAll(evt);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		if(commonAppDocs!=null){
	   		while(commonAppDocs.hasNext()) {
	   			CommonAppDocMetadata review = (CommonAppDocMetadata)commonAppDocs.next();
	   			CommonAppDocResponseEvent myRespEvt=review.getRespEvt();
	   	   		
	   	   		dispatch.postEvent(myRespEvt);
	   		}
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