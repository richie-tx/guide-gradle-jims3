/*
 * Created on Feb 21, 2006
 */
package ui.supervision.supervisionorder.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.UpdateSuperviseeSignatureEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.SupervisionOrderButtonHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author hrodriguez
 */
public class SubmitSupervisionOrderDefendantSignatureAction extends JIMSBaseAction
{

	/**
	 * 
	 */
	public SubmitSupervisionOrderDefendantSignatureAction()
	{

	}
	/**
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");		
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.backToCaseOrderSearchResults", "backToCaseOrderSearchResults");
		keyMap.put("button.backToSearch", "backToSearch");
	}

	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateSuperviseeSignatureEvent event = new UpdateSuperviseeSignatureEvent();
			event.setSupervisionOrderId(sof.getOrderId());
		    event.setSignedDate(sof.getSignedByDefendantDate());  
		    event.setJudgeSignedDate(sof.getSignedByJudgeDate());
		    event.setUserLogonId(UIUtil.getCurrentUserID());
		    event.setUserIsCLO(sof.isUserClo());
		    event.setCasenum(sof.getCaseNum());
		    event.setCdi(sof.getCdi());
		    if ( sof.getDefendantSignature() != null && sof.getDefendantSignature().equalsIgnoreCase("signed"))
				   {
					event.setDefendantSignatureInd(true);
			
				   }
		    else 
				   {
					event.setDefendantSignatureInd(false);
				   } 				   
			dispatch.postEvent(event);		
			// dp exception
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
		    //sof.setSuccessMesage("sucess");
		    sof.setAction(UIConstants.DEFENDENT_SIG_AQUIRED_CONFIRMATION);

		    // JMcNabb - Defect 34617 ... we need to refresh the list
		    //forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.DEFENDENT_SIG_AQUIRED_SUCCESS);
		    forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
			//forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		    return forward;
	}
	
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	
	public ActionForward backToCaseOrderSearchResults(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
	        //setting this for print
	        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
	        sof.setAction("");
	        sof.setPrintAction("");
	        //End 
	        
			ActionForward forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
			return forward;
		}

		public ActionForward mainPage(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ActionForward forward = new ActionForward();
			SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.MAIN_MENU, UIUtil.getCurrentUserAgencyID()));
			return forward;
		}

		public ActionForward backToSearch(
					ActionMapping aMapping,
					ActionForm aForm,
					HttpServletRequest aRequest,
					HttpServletResponse aResponse)
				{
					ActionForward forward = new ActionForward();
					SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
					sof.setAction("");
					forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK_TO_SEARCH, UIUtil.getCurrentUserAgencyID()));
					return forward;
				}
}
