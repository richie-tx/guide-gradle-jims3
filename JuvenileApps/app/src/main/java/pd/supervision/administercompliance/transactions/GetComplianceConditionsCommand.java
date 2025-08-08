//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetComplianceConditionsCommand.java

package pd.supervision.administercompliance.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.administercompliance.GetComplianceConditionsEvent;
import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.administercompliance.reply.LikeComplianceConditionResponseEvent;
import messaging.supervisionoptions.reply.OrderConditionAgencyPolicyResponseEvent;
import messaging.supervisionoptions.reply.OrderConditionCourtPolicyResponseEvent;
import messaging.supervisionorder.GetOrderConditionAgencyPolicyEvent;
import messaging.supervisionorder.GetOrderConditionCourtPolicyEvent;
import messaging.supervisionorder.GetOrderConditionsBySPNEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionoptions.SupervisionOrderConditionAgencyPolicyView;
import pd.supervision.supervisionoptions.SupervisionOrderConditionCourtPolicyView;
import pd.supervision.supervisionorder.SupervisionOrderConditionView;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetComplianceConditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 473B887D0238
    */
   public GetComplianceConditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B7555012A
    */
   public void execute(IEvent event) 
   {
   	   GetComplianceConditionsEvent gEvent = (GetComplianceConditionsEvent) event;
	   
   	   GetOrderConditionsBySPNEvent spnEvent = new GetOrderConditionsBySPNEvent();
   	   spnEvent.setSpnId(gEvent.getDefendantId());
   	   Iterator iterator = SupervisionOrderConditionView.findAll(spnEvent);
   	   Map orderConditions = new HashMap();
   	   Map likeOrderConditions = new HashMap();
   	   Set removalList = new HashSet();
       while(iterator.hasNext()){
   	       SupervisionOrderConditionView view = (SupervisionOrderConditionView) iterator.next();
   	       ComplianceConditionResponseEvent resp = view.getResonseEvent();
   	       String orderConditionName = resp.getOrderConditionName();
   	       if(!orderConditions.containsKey(orderConditionName)){
   	       	   orderConditions.put(orderConditionName, resp);
   	       }else{
   	           ComplianceConditionResponseEvent oldResp = (ComplianceConditionResponseEvent) orderConditions.get(orderConditionName);
   	           removalList.add(oldResp.getSprOrderConditionId());
   	           
   	           if(!likeOrderConditions.containsKey(orderConditionName)){
   	               LikeComplianceConditionResponseEvent lResp = view.getLikeConditionsResponseEvent();
   	               List complianceResponses = new ArrayList();
   	           	   complianceResponses.add(oldResp);
   	           	   complianceResponses.add(resp);
	           	   lResp.setComplianceConditionResponseEvents(complianceResponses);
   	           	   likeOrderConditions.put(orderConditionName,lResp);
   	           }else{
   	               LikeComplianceConditionResponseEvent lResp = (LikeComplianceConditionResponseEvent) likeOrderConditions.get(orderConditionName);
   	               List complianceConditionResponses = lResp.getComplianceConditionResponseEvents();
   	               if(!complianceConditionResponses.contains(oldResp)){
   	                   lResp.getComplianceConditionResponseEvents().add(oldResp);
   	               }
   	               
   	               if(!complianceConditionResponses.contains(resp)){
   	                  lResp.getComplianceConditionResponseEvents().add(resp);
	               }
	               likeOrderConditions.put(orderConditionName,lResp);
   	           }
   	       }
   	   }
   	   
   	   GetOrderConditionAgencyPolicyEvent aEvent = new GetOrderConditionAgencyPolicyEvent();
   	   aEvent.setDefendantId(gEvent.getDefendantId());
   	   Iterator agencyPolicies = SupervisionOrderConditionAgencyPolicyView.findAll(aEvent);
   	   Map aPolicies = new HashMap();
   	   while(agencyPolicies.hasNext()){
   	       SupervisionOrderConditionAgencyPolicyView aView = (SupervisionOrderConditionAgencyPolicyView) agencyPolicies.next();
   	       OrderConditionAgencyPolicyResponseEvent aResp = aView.getResponseEvent();
   	       StringBuffer orderConditionId = new StringBuffer("");
	       orderConditionId.append(aView.getOrderConditionId());
	       if(!aPolicies.containsKey(orderConditionId.toString())){
   	       	   List agPolicies = new ArrayList();
   	       	   agPolicies.add(aResp);
   	   	       aPolicies.put(orderConditionId.toString(), agPolicies);
   	       }else{
   	           List agPolicies = (List) aPolicies.get(orderConditionId.toString());
   	           agPolicies.add(aResp);
   	           aPolicies.put(orderConditionId.toString(), agPolicies);
   	       }
   	   }
   	   
   	   GetOrderConditionCourtPolicyEvent cEvent = new GetOrderConditionCourtPolicyEvent();
	   cEvent.setDefendantId(gEvent.getDefendantId());
	   Iterator courtPolicies = SupervisionOrderConditionCourtPolicyView.findAll(cEvent);
	   Map cPolicies = new HashMap();
	   while(courtPolicies.hasNext()){
   	       SupervisionOrderConditionCourtPolicyView cView = (SupervisionOrderConditionCourtPolicyView) courtPolicies.next();
   	       OrderConditionCourtPolicyResponseEvent cResp = cView.getResponseEvent();
   	       StringBuffer oderConditionId = new StringBuffer("");
 	       oderConditionId.append(cView.getOrderConditionId());
   	       if(!cPolicies.containsKey(oderConditionId.toString())){
	       	   List ctPolicies = new ArrayList();
	       	   ctPolicies.add(cResp);
	   	       cPolicies.put(oderConditionId.toString(), ctPolicies);
	       }else{
	           List ctPolicies = (List) cPolicies.get(oderConditionId.toString());
	           ctPolicies.add(cResp);
	           cPolicies.put(oderConditionId.toString(), ctPolicies);
	       }   	   
   	   }
   	   
   	   Iterator ocIterator = orderConditions.values().iterator();
   	   while(ocIterator.hasNext()){
   	       ComplianceConditionResponseEvent cResp = (ComplianceConditionResponseEvent) ocIterator.next();
   	       // If it contains in the list, that means it is in the like list, so no need to have it in this list
   	       if(!removalList.contains(cResp.getSprOrderConditionId())){
	           List agPolicies = (List) aPolicies.get(cResp.getSprOrderConditionId());
	   	       cResp.setDepartmentPolicies(agPolicies);
	   	       
	   	       List ctPolicies = (List) cPolicies.get(cResp.getSprOrderConditionId());
		       cResp.setCourtPolicies(ctPolicies);
	   	       MessageUtil.postReply(cResp);
   	       }
   	   }
   	   
   	   Iterator lIterator = likeOrderConditions.values().iterator();
	   while(lIterator.hasNext()){
	       LikeComplianceConditionResponseEvent lResp = (LikeComplianceConditionResponseEvent) lIterator.next();
	       List agPolicies = (List) aPolicies.get(lResp.getSprOrderConditionId());
	       lResp.setDepartmentPolicies(agPolicies);
	       
	       List ctPolicies = (List) cPolicies.get(lResp.getSprOrderConditionId());
	       lResp.setCourtPolicies(ctPolicies);
	       MessageUtil.postReply(lResp);
	   }
   }
   
   /**
    * @param event
    * @roseuid 473B7555012C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75550138
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B7555013A
    */
   public void update(Object anObject) 
   {
    
   }
}
