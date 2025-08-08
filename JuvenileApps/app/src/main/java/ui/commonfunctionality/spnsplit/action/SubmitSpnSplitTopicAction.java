//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\SubmitSpnSplitTopicAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.spnsplit.UpdateSpnSplitTopicEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.SpinSplitConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.commonfunctionality.spnsplit.form.SpnSplitForm;


public class SubmitSpnSplitTopicAction  extends JIMSBaseAction
{
   
   /**
    * @roseuid 4561E28702F2
    */
   public SubmitSpnSplitTopicAction() 
   {
    
   }
   
   /**
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    * @return Map
    */
	protected void addButtonMapping(Map keyMap) {
       keyMap.put("button.finish", "finish");
	}
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		SpnSplitForm ssForm = (SpnSplitForm) aForm;
    	UpdateSpnSplitTopicEvent gEvent = new UpdateSpnSplitTopicEvent();
      
        gEvent.setErrDefendantId(ssForm.getErroneousSpn().getSpn());  
        gEvent.setValidDefendantId(ssForm.getValidSpn().getSpn());
   		if (ssForm.getSelectedTopic().equalsIgnoreCase(UIConstants.ASSESSMENT)){
   			gEvent.setTopic(SpinSplitConstants.ASSESSMENT);
   			gEvent.setTopicIds(ssForm.getSelectedAssessmentIds());
   		} else {
   			if (ssForm.getSelectedTopic().equalsIgnoreCase(UIConstants.ASSOCIATE)){
   				gEvent.setTopic(SpinSplitConstants.ASSOCIATE);
   				gEvent.setTopicIds(ssForm.getSelectedAssociateIds());
   			} else {
//   		if (ssForm.getSelectedTopic().equalsIgnoreCase(UIConstants.LOS)){
//        		gEvent.setTopic(SpinSplitConstants.LOS);
//   			gEvent.setTopicIds(ssForm.getSelectedLOSIds());
//  		} else {  		
   				if (ssForm.getSelectedTopic().equalsIgnoreCase(UIConstants.SUPERVISION_PLAN)){
   					gEvent.setTopic(SpinSplitConstants.SUPERVISION_PLAN);
   					gEvent.setTopicIds(ssForm.getSelectedSupervisionPlanIds());
   				}
   			}
   		}	
        
        CompositeResponse response = MessageUtil.postRequest(gEvent);
        ReturnException error = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if(error != null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			return aMapping.findForward(UIConstants.FAILURE);
		} 
   		String msg = "SPN Split - Assessments processed";
   		if (ssForm.getSelectedTopic().equalsIgnoreCase(UIConstants.ASSOCIATE)){
   			msg += " - Associates processed";
   		} else {
//			if (ssForm.getSelectedTopic().equalsIgnoreCase(UIConstants.LOS)){
//				msg += " - LOS history processed";
//			} else {
				if (ssForm.getSelectedTopic().equalsIgnoreCase(UIConstants.SUPERVISION_PLAN)){
					msg += " - Supervision Plans processed";
				}
			}	

        ssForm.setConfirmationMessage(msg);
   	    return aMapping.findForward(UIConstants.SUCCESS);
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
}