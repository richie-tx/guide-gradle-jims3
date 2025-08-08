//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\ResequenceConditionsAction.java

package ui.supervision.supervisionorder.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
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

import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author hrodriguez
 */
public class ResequenceConditionsAction extends LookupDispatchAction
{

	public ResequenceConditionsAction()
	{

	}
	/**
	* @see LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.saveContinue", "saveContinue");
		/**keyMap.put("button.previewOrder", "previewOrder"); */
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.reset", "reset");
		return keyMap;
	}
		
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward saveContinue(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		Collection originalList=sof.getConditionSelectedList();
		Collection newList=UISupervisionOrderHelper.setResequenceCondition(sof.getConditionSelectedList(),sof.getResequencedOrderValue(),false);
		sof.setConditionSelectedList(newList);
		sof.setSecondaryAction(UIConstants.RESEQUENCE_SUCCESS);
		sof.setResequencedOrderValue("");
		processUpdateEvent(sof);
		if(sof.getIsPretrialInterventionOrder()){
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONTINUE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		}
		else
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONTINUE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	/**ER#62100 remove Preview Order button
	 * public ActionForward previewOrder(
	 
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PREVIEW_SUCCESS, UIUtil.getCurrentUserAgencyID()));
	}
	*/
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		ActionForward forward = new ActionForward();
		
		if (!sof.getIsPretrialInterventionOrder()){
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backAddRemove", UIUtil.getCurrentUserAgencyID()));
		}else {
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
		}
		
		return forward;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}


	/**
	 * @param selectedList
	 * @return
	 */
	private Map createConditionMap(Collection selectedList)
	{
		HashMap map = new HashMap();

		if (selectedList != null)
		{
			ConditionDetailResponseEvent cre = null;
			Iterator iter = selectedList.iterator();
			while (iter.hasNext())
			{
				cre = (ConditionDetailResponseEvent) iter.next();
				map.put(cre.getConditionId(), cre);
			}
		}
		return map;
	}
	/**
	 * @param aRequest
	 * @param msg
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
	
	private void processUpdateEvent(SupervisionOrderForm sof){
		//UpdateSupervisionOrderEvent updateEvent = updateSupervisionOrder(sof);
		UpdateSupervisionOrderEvent updateEvent = UISupervisionOrderHelper.updateSupervisionOrder(sof);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		dispatch.postEvent(updateEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);
//		Collection searchResults =
//			MessageUtil.compositeToCollection((CompositeResponse) dispatch.getReply(), CaseOrderResponseEvent.class);
		Collection searchResults =
			MessageUtil.compositeToCollection(response, CaseOrderResponseEvent.class);
		
		MessageUtil.processEmptyCollection(searchResults);

		Iterator iter = searchResults.iterator(); // only one record
		CaseOrderResponseEvent orderRespEvent = null;
		if (iter.hasNext())
		{
			orderRespEvent = (CaseOrderResponseEvent) iter.next();
		}
		//set orderId in the form as we might have created a new version as a result of save/Continue
		sof.setOrderId(orderRespEvent.getOrderId());
		sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
		sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward reset(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward("reset");
	}
}
