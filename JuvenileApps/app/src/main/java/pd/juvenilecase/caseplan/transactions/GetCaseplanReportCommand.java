/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.caseplan.transactions;

import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.JPOReviewDocMetadata;
import messaging.caseplan.GetCaseplanReportEvent;
import messaging.caseplan.GetJPOReviewReportEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
import messaging.caseplan.reply.JPOReviewReportResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCaseplanReportCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetCaseplanReportEvent evt = (GetCaseplanReportEvent)event;
		CasePlan cp  = CasePlan.find(evt.getCaseplanId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		if(cp!=null){
   			CaseplanListResponseEvent myRespEvt=new CaseplanListResponseEvent();
   			myRespEvt.setCaseplanID(cp.getCaseplanID());
   			myRespEvt.setReport(cp.getReport());
   			myRespEvt.setCreateDate(cp.getCreateTimestamp());
   			myRespEvt.setReviewDate(cp.getReviewDate());
	   	   		
	   	   		dispatch.postEvent(myRespEvt);
	   		
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
