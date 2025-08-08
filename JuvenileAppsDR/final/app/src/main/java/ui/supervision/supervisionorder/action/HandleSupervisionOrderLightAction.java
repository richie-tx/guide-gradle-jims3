//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderAddRemoveConditionsAction.java

package ui.supervision.supervisionorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.SupervisionOrderConditionEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.JudgeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author dgibler
 *
 */
public class HandleSupervisionOrderLightAction extends JIMSBaseAction
{
	
	/**
	 * @roseuid 438F23B6012F
	 */
	public HandleSupervisionOrderLightAction()
	{

	}
	/**
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.removeSelected", "removeSelected");
		keyMap.put("button.addMoreConditions", "addMoreConditions");
		keyMap.put("button.resequenceConditions","resequenceConditions");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.prepareToFile", "prepareToFile");
		
	}


	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward removeSelected(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		boolean someRemoved=false;
		boolean atLeastOneRemains=false;
		Collection selectedList=sof.getConditionSelectedList();
		Collection remainingList=new ArrayList();
		Collection deletedList=new ArrayList();
		Iterator iterSelected=selectedList.iterator();
		int x=0;
		while(iterSelected.hasNext()){
			ConditionDetailResponseEvent myRespEvt=(ConditionDetailResponseEvent)iterSelected.next();
			myRespEvt.setLikeConditionInd(false);
			if(myRespEvt.isDeleted()){
				someRemoved=true;
				deletedList.add(myRespEvt);
				
			}
			else{
				atLeastOneRemains=true;
				x++;
				myRespEvt.setSequenceNum(x);
				remainingList.add(myRespEvt);
			}
		}
		if(!atLeastOneRemains){
			sendToErrorPage(aRequest,"error.generic","All conditions cannot be removed. There must be at least one condition remaining.");
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		}
		if(someRemoved){
			
			Collections.sort((List) remainingList, ConditionDetailResponseEvent.SeqNumComparator);
			sof.setConditionSelectedList(remainingList);
			processUpdateEvent(sof);
		}
		
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}
	
	

	
	
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
        sof.clearValueMap();
		
		String action = sof.getAction();
		ActionForward forward = new ActionForward();
		if (action.equals(UIConstants.CREATE))
		{
			forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backToOrderCreateSuccess", UIUtil.getCurrentUserAgencyID()));
			if (sof.getWorkflowFrom().equals(SupervisionConstants.PASO_WORKFLOWFROM)){	
				forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backToOrderUpdateSuccess", UIUtil.getCurrentUserAgencyID()));
			}
		}else if (sof.getWorkflowFrom() != null){
			if (sof.getWorkflowFrom().equals(SupervisionConstants.PASO_WORKFLOWFROM)){				
				forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backToOrderPresentation", UIUtil.getCurrentUserAgencyID()));
			}
		}else if(action.equals(UIConstants.UPDATE))
		{
			forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("backToOrderUpdateSuccess", UIUtil.getCurrentUserAgencyID()));
		}
		else{
			forward=aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
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
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL+ UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward prepareToFile(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		sof.setSignedDate(null);
        sof.setDefendantSignature("");
        sof.setDefendantSignedDateAsString("");
        SupervisionStaffResponseEvent ssre = UISupervisionOrderHelper.getSupervisionStaff(sof.getPresentedByList());
        
        if (ssre != null) {
            sof.setPresentedById(ssre.getSupervisionStaffId());
        }
        JudgeResponseEvent jre = UISupervisionOrderHelper.getJudge(sof.getJudgeSelectList(), sof.getCourtId());
        if (jre != null) {
            sof.setJudgeSelectId(jre.getCourtId());
        }

		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.PREPARE_TO_FILE_SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addMoreConditions(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		UISupervisionOrderHelper.addNewPasoLightConditions(sof);	
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward resequenceConditions(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		processUpdateEvent(sof);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.RESEQUENCE_SUCCESS +UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
	}



	private void processUpdateEvent(SupervisionOrderForm sof){
		UpdateSupervisionOrderEvent updateEvent = updateSupervisionOrder(sof);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		dispatch.postEvent(updateEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);
//		Collection searchResults =
//			MessageUtil.compositeToCollection((CompositeResponse) dispatch.getReply(), CaseOrderResponseEvent.class);
		Collection searchResults =
			MessageUtil.compositeToCollection(response, CaseOrderResponseEvent.class);
		
		MessageUtil.processEmptyCollection(searchResults);

		Iterator iter = searchResults.iterator(); // only ome record
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
	 * This method posts event to pd to update SupervisionOrder
	 * @param sof
	 */
	public  UpdateSupervisionOrderEvent updateSupervisionOrder(SupervisionOrderForm sof)
	{
		//UpdateSupervisionOrderEvent supervisionOrder = new UpdateSupervisionOrderEvent();
		UpdateSupervisionOrderEvent supervisionOrder = UISupervisionOrderHelper.updateSupervisionOrder(sof);
		supervisionOrder.setOrderId(sof.getOrderId());
		supervisionOrder.setAddRemoveCondition(true);

		//get conditions
		Collection conditions = sof.getConditionSelectedList();
		if (conditions != null)
		{
			Iterator condIter = conditions.iterator();
			while (condIter.hasNext())
			{
				ConditionDetailResponseEvent cdre = (ConditionDetailResponseEvent) condIter.next();
				SupervisionOrderConditionEvent conditionOrderEvent =
					UISupervisionOrderHelper.convertToSave(cdre, sof.getReferenceVariableMap());

				// add Condition Rel values (Variable Elements)
				supervisionOrder.addCondition(conditionOrderEvent);
			}
		}
		return supervisionOrder;
	}




	


}
