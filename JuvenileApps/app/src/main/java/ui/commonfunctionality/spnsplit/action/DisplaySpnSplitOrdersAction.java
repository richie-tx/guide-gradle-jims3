//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\DisplaySpnSplitOrdersAction.java

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
import ui.supervision.SupervisionPeriodInfo;



public class DisplaySpnSplitOrdersAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4561E28400EE
    */
   public DisplaySpnSplitOrdersAction() 
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
    * @roseuid 455E28BB035B
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
        SpnSplitForm myForm = (SpnSplitForm) aForm;
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        // Check to see if at least one supervision period has been selected if not fail
        myForm.setCurrentSupPeriod(null);
        if(myForm.getSelectedValue()==null || myForm.getSelectedValue().trim().equals("")){
        	sendToErrorPage(aRequest,"errors.selectOnlyOneSupPeriod");
            return aMapping.findForward("failureSupPeriod");
        }
        if(myForm.getSupPeriods()!=null && myForm.getSupPeriods().size()>0){
        	Iterator iter= myForm.getSupPeriods().iterator();
        	while(iter.hasNext()){
        		SupervisionPeriodInfo mySupPeriodInfo=(SupervisionPeriodInfo)iter.next();
        		if(mySupPeriodInfo!=null && mySupPeriodInfo.getSupPeriodId().equals(myForm.getSelectedValue())){
        			mySupPeriodInfo.setSelected(true);
        			myForm.setCurrentSupPeriod(mySupPeriodInfo);
        			myForm.clearOrdersSelection(false);
        			return forward;
        		}
        	}
        }
        // No Sup period selected
    	sendToErrorPage(aRequest,"errors.selectOnlyOneSupPeriod");
        return aMapping.findForward("failureSupPeriod");
   }
   
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse) {
//	   	SpnSplitForm myForm = (SpnSplitForm) aForm;
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
   
/*    private void sendToErrorPage(HttpServletRequest aRequest, String msg, String param) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage(msg,param));
        saveErrors(aRequest, errors);
    } */
   
}