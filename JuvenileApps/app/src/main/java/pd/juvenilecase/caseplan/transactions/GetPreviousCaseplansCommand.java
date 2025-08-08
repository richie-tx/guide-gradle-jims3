/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.caseplan.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.caseplan.CasePlan;
import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.GetPreviousCaseplansEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.caseplan.reply.CaseplanListResponseEvent;
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
public class GetPreviousCaseplansCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetPreviousCaseplansEvent request = (GetPreviousCaseplansEvent)event;
   		String casefileID = request.getCasefileId();
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		Iterator cps = CasePlan.findAll(request);
   		if(cps!=null){
	   		while(cps.hasNext()) {
	   			CasePlan cp = (CasePlan)cps.next();
	   			CaseplanListResponseEvent cpResponse = new CaseplanListResponseEvent();
	   			cpResponse.setCaseplanID(cp.getCaseplanID());
	   			cpResponse.setSupervisionNumber(cp.getSupervisionNumber());
	   			cpResponse.setCreateDate(cp.getCreateTimestamp());
	   			cpResponse.setReviewDate(cp.getReviewDate());
	   			cpResponse.setStatusId(cp.getStatusId());
	   			cpResponse.setStatus(cp.getStatus().getDescription());
	   			cpResponse.setReport(cp.getReport());
	   			dispatch.postEvent(cpResponse);
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
