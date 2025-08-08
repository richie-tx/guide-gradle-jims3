//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\DisplaySpnSplitSummaryAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.commonfunctionality.spnsplit.form.SpnSplitForm;
import ui.supervision.CaseInfo;


public class DisplaySpnSplitSummaryAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4561E28602A4
    */
   public DisplaySpnSplitSummaryAction() 
   {
   }

   /**
    * @see LookupDispatchAction#getKeyMethodMap()
    * @return Map
    */
   protected Map getKeyMethodMap() {
       Map keyMap = new HashMap();
       keyMap.put("button.next", "next");
       keyMap.put("button.cancel", "cancel");
       keyMap.put("button.back", "back");
       return keyMap;
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 455E28BC00E8
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
        ActionForward forward = new ActionForward();
        SpnSplitForm myForm = (SpnSplitForm) aForm;
        myForm.setAction(UIConstants.SUMMARY);
        forward = aMapping.findForward(UIConstants.SUCCESS);
        
        // Check to see if at least one set of cases has been selected if not fail
        if(myForm.getCurrentSupPeriod().getCases()!=null && myForm.getCurrentSupPeriod().getCases().size()>0){
        	Iterator iter= myForm.getCurrentSupPeriod().getCases().iterator();
        	while(iter.hasNext()){
        		CaseInfo myCaseInfo=(CaseInfo)iter.next();
        		if(myCaseInfo!=null && myCaseInfo.isSelected()){
        			return forward;
        		}
        	}
        }
        // No Orders selected
    	sendToErrorPage(aRequest,"errors.selectOneOrder");
        return aMapping.findForward(UIConstants.FAILURE);
   }
   
   
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
    return aMapping.findForward(UIConstants.BACK);
   }
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse) {
    return aMapping.findForward(UIConstants.CANCEL);
   }    

	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
	    saveErrors(aRequest, errors);
	}
  
/*   private void sendToErrorPage(HttpServletRequest aRequest, String msg, String param) {
       ActionErrors errors = new ActionErrors();
       errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage(msg,param));
       saveErrors(aRequest, errors);
   } */
}