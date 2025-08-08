//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\DisplaySpnSplitInfoAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.spnsplit.GetSpnSplitExceptionEvent;
import messaging.spnsplit.reply.SpnSplitErrorResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.commonfunctionality.spnsplit.form.SpnSplitForm;



public class DisplaySpnSplitAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4561E28201AA
    */
   public DisplaySpnSplitAction() 
   {
    
   }
   
   protected Map getKeyMethodMap() {
    Map keyMap = new HashMap();
    keyMap.put("button.link", "link");
    return keyMap;
   }
   
   public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse) {
    SpnSplitForm myForm = (SpnSplitForm) aForm;
    myForm.clear();
    ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
    
    GetSpnSplitExceptionEvent ev = new GetSpnSplitExceptionEvent();
    CompositeResponse cr = MessageUtil.postRequest(ev);
    
    List list = MessageUtil.compositeToList(cr, SpnSplitErrorResponseEvent.class);
    if(list != null && !list.isEmpty()){
    	myForm.setSpnSplitErrors(list);
    }    
    return forward;
	}
	
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse) {
//	   	SpnSplitForm myForm = (SpnSplitForm) aForm;
	    return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	        HttpServletResponse aResponse) {
		SpnSplitForm myForm = (SpnSplitForm) aForm;
	    myForm.clear();
	    ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
	    return forward;
	}
/*	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(msg));
	    saveErrors(aRequest, errors);
	} */
  
}