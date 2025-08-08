//Source file: C:\\views\\maint_mjcw\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\GetCaseplanAcknowledgementByCaseplanIdCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;

import messaging.caseplan.GetCaseplanAcknowledgementByCaseplanIdEvent;
import messaging.caseplan.reply.CaseplanAcknowledgementResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.caseplan.CaseplanAcknowledgement;


public class GetCaseplanAcknowledgementByCaseplanIdCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B81502D2
    */
   public GetCaseplanAcknowledgementByCaseplanIdCommand() 
   {
   		
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6401F3
    */
   public void execute(IEvent event) 
   {
	   GetCaseplanAcknowledgementByCaseplanIdEvent request = (GetCaseplanAcknowledgementByCaseplanIdEvent)event;
   	
	   //String caseplanId = request.getCaseplanId();
   		Iterator iter = CaseplanAcknowledgement.findAll(request);
   		
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		
   		while(iter.hasNext()) {
   			CaseplanAcknowledgement acknowledgement = (CaseplanAcknowledgement)iter.next();
   			
   			CaseplanAcknowledgementResponseEvent respEvt = new CaseplanAcknowledgementResponseEvent();
   			respEvt.setAcknowledgementId(acknowledgement.getOID());
   			respEvt.setCaseplanId(acknowledgement.getCaseplanID());
   			respEvt.setEntryDate(acknowledgement.getEntryDate());
   			respEvt.setSignatureStatus(acknowledgement.getSignatureStatus());
   			respEvt.setExplanation(acknowledgement.getExplanation());
   			respEvt.setCreateDate(acknowledgement.getCreateTimestamp());
   			dispatch.postEvent(respEvt);
   			
   		}
   		
   		
   		
		
   		/*
   		String goalID = request.getGoalID();
   		Goal goal = Goal.find(goalID);
   		GoalDetailsResponseEvent gEvent = CaseplanHelper.getGoalDetailsResponseEventFromGoal(goal);
   		
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(gEvent);
   		
   		// get the rules
   		Iterator ruleIte = goal.getRules().iterator();
   		IJuvenileCaseworkResponseFactory responseFactory =  new JuvenileCaseworkResponseFactory();
   		while(ruleIte.hasNext()) {
   			JuvenileCaseSupervisionRule supRule = (JuvenileCaseSupervisionRule) ruleIte.next(); 
			RuleGroupConditionView ruleView = RuleGroupConditionView.find(supRule.getOID().toString());
			RuleResponseEvent response = responseFactory.getRuleResponseEvent(ruleView);
			dispatch.postEvent(response);
   		}*/
   		
   }
   
   /**
    * @param event
    * @roseuid 45119A6401FB
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A6401FD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45119A640205
    */
   public void update(Object anObject) 
   {
    
   }

}
