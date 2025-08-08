/*
 * Created on Dec 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.rules.SaveJuvenileCasefileSupervisionRulesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory;
import ui.juvenilecase.Factory.SupervisionRuleRequestEventFactory;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.SupervisionConditionForm;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitAssignStandardRulesAction extends LookupDispatchAction
{

	/**
	 * 
	 */
	public SubmitAssignStandardRulesAction()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}

	public ActionForward cancel(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
		SupervisionConditionForm conditionForm = (SupervisionConditionForm)aForm;
				ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
				return forward;
			}
		
			
	
		
		public ActionForward back(
					ActionMapping aMapping,
					ActionForm aForm,
					HttpServletRequest aRequest,
					HttpServletResponse aResponse)
				{
			SupervisionConditionForm conditionForm = (SupervisionConditionForm)aForm;
			conditionForm.setAction(UIConstants.SUMMARY);
					ActionForward forward = aMapping.findForward(UIConstants.BACK);
					return forward;
				}
			
		public ActionForward finish(
						ActionMapping aMapping,
						ActionForm aForm,
						HttpServletRequest aRequest,
						HttpServletResponse aResponse)
			{
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				SupervisionConditionForm conditionForm = (SupervisionConditionForm)aForm;
				ISupervisionRuleRequestEventFactory eventFactory = new SupervisionRuleRequestEventFactory();
				JuvenileCasefileForm headerForm = UIJuvenileCasefileSupervisionRulesHelper.getHeaderForm(aRequest);
				SaveJuvenileCasefileSupervisionRulesEvent saveEvent = eventFactory.buildSaveSupervisionRulesEvent(conditionForm,headerForm.getSupervisionNum());
				// Sending PD Request Event
				dispatch.postEvent(saveEvent);
				// Getting PD Response Event	
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				// Perform Error handling
				MessageUtil.processReturnException(response); 
				ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
				conditionForm.setAction(UIConstants.CONFIRM);
				return forward;
			}

	
				
		/**
		* @param aRequest
		*/
	   private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	   {
		   ActionErrors errors = new ActionErrors();
		   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		   saveErrors(aRequest, errors);
	   }	
}
