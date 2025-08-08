//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\SubmitSpnSplitsAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.spnsplit.ProcessSpnSplitEvent;
import messaging.spnsplit.reply.SpnSplitErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SPNSplitControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.commonfunctionality.spnsplit.form.SpnSplitForm;
import ui.supervision.CaseInfo;


public class SubmitSpnSplitAction  extends LookupDispatchAction
{
   
   /**
    * @roseuid 4561E28702F2
    */
   public SubmitSpnSplitAction() 
   {
    
   }
   
   /**
    * @see LookupDispatchAction#getKeyMethodMap()
    * @return Map
    */
   protected Map getKeyMethodMap() {
       Map keyMap = new HashMap();
       keyMap.put("button.finish", "finish");
       keyMap.put("button.cancel", "cancel");
       keyMap.put("button.back", "back");
       keyMap.put("button.newSplit", "newSplit");
       keyMap.put("button.newSplitTopic", "newSplitTopic");
       keyMap.put("button.mainPage", "mainMenu");
       return keyMap;
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 455E28BC0129
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		SpnSplitForm myForm = (SpnSplitForm) aForm;
   		myForm.setAction(UIConstants.CONFIRM);
   		// Create Event and populate it
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		ProcessSpnSplitEvent requestEvent = (ProcessSpnSplitEvent) EventFactory.getInstance(SPNSplitControllerServiceNames.PROCESSSPNSPLIT);
        requestEvent.setErroneousSpn(myForm.getErroneousSpn().getSpn());
        requestEvent.setValidSpn(myForm.getValidSpn().getSpn());
      //  requestEvent.setSupPeriodId(myForm.getCurrentSupPeriod().getSupPeriodId());
        ArrayList myList=new ArrayList();
        requestEvent.setCases(myList);
        if(myForm.getCurrentSupPeriod().getCases()!=null && myForm.getCurrentSupPeriod().getCases().size()>0){
        	Iterator iter= myForm.getCurrentSupPeriod().getCases().iterator();
        	while(iter.hasNext()){
        		CaseInfo myCaseInfo=(CaseInfo)iter.next();
        		if(myCaseInfo!=null && myCaseInfo.isSelected()){
        			
        			myList.add(myCaseInfo.getCdi() + myCaseInfo.getCaseNum());
        		}
        	}
        }
        requestEvent.setAgencyId(UIUtil.getCurrentUserAgencyID());
        requestEvent.setErrPeriodId(myForm.getCurrentSupPeriod().getSupPeriodId());
        dispatch.postEvent(requestEvent);
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        MessageUtil.processReturnException(response);
        // error handling
        Collection periodRespEvents = MessageUtil.compositeToCollection(response, SpnSplitErrorResponseEvent.class);
        if(periodRespEvents!=null && periodRespEvents.size()>0){
        	Iterator iterErr=periodRespEvents.iterator();
        	SpnSplitErrorResponseEvent myRespEvt=(SpnSplitErrorResponseEvent)iterErr.next();
        	sendToErrorPage(aRequest,"error.generic",myRespEvt.getErroneousSpn() + ": " + myRespEvt.getMsg());
        	return aMapping.findForward(UIConstants.FAILURE);
        }
        myForm.setConfirmationMessage("SPN Split successful and casenote recorded.");
   	    return aMapping.findForward(UIConstants.SUCCESS);
   }
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */   
   public ActionForward newSplit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		SpnSplitForm myForm = (SpnSplitForm) aForm;
   		myForm.clear();
   	    return aMapping.findForward(UIConstants.CANCEL);
   }
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
   public ActionForward newSplitTopic(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
 		SpnSplitForm ssForm = (SpnSplitForm) aForm;
 		ssForm.setCurrentAssessments(new ArrayList());
 		ssForm.setCurrentAssociates(new ArrayList());
 		ssForm.setCurrentSupervisionPlans(new ArrayList());
	    ssForm.setSelectedTopic("");
	    ssForm.setShowBackButton(UIConstants.NO);
 	    return aMapping.findForward(UIConstants.SUCCESS_SUPERVISEE);
   }
   
   public ActionForward mainMenu(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		SpnSplitForm myForm = (SpnSplitForm) aForm;
   		myForm.clear();
   	    return aMapping.findForward(UIConstants.CANCEL_MAIN_PAGE_HOME);
   }
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */   
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
    return aMapping.findForward(UIConstants.BACK);
   }
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */   
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse) {
	   return aMapping.findForward(UIConstants.CANCEL);
   } 
   
//   private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
//    ActionErrors errors = new ActionErrors();
//    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage(msg));
//    saveErrors(aRequest, errors);
//}
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
   private void sendToErrorPage(HttpServletRequest aRequest, String msg, String param) {
	   ActionErrors errors = new ActionErrors();
	   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg,param));
	   saveErrors(aRequest, errors);
   }
}
