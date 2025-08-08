// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import java.util.Iterator;

import messaging.administercasenotes.GetConditionCasenotesForCaseOrderChainEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteConditions;
import pd.supervision.administercasenotes.CasenoteHelper;

public class GetConditionCasenotesForCaseOrderChainCommand implements ICommand {

	/**
	 * @roseuid 44F4632F0377
	 */
	public GetConditionCasenotesForCaseOrderChainCommand() {

	}

	/**
	 * @param event
	 * @roseuid 44EE113903B6
	 */
	public void execute(IEvent event) {
		GetConditionCasenotesForCaseOrderChainEvent gEvent = (GetConditionCasenotesForCaseOrderChainEvent) event;
    	Iterator ccIter = CasenoteConditions.findAll(gEvent);
    	CasenoteHelper helper = CasenoteHelper.getInstance();
    	
		while(ccIter.hasNext()){
		    CasenoteConditions cc = (CasenoteConditions) ccIter.next(); 
	   	    if(cc != null){
	   	   	    Casenote c = Casenote.find(new StringBuffer("").append(cc.getCasenoteId()).toString());
	   	   	    if(c != null){
	   	   	        CasenoteResponseEvent resp = helper.getCasenoteResponse(c);	
	   	   	        helper.postCasenoteResponse(resp);
	   	   	    }
	   	    }
	    }
	}

	/**
	 * @param event
	 * @roseuid 44EE113903BF
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 44EE113903C1
	 */
	public void onUnregister(IEvent event) {

	}


	/**
	 * @param updateObject
	 * @roseuid 44F4632F038B
	 */
	public void update(Object updateObject) {

	}
}
