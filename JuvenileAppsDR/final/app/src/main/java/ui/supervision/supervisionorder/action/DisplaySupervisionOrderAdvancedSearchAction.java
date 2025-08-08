//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderAdvancedSearchAction.java

package ui.supervision.supervisionorder.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.task.GetTaskEvent;
import messaging.task.domintf.ITaskState;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.SupervisionOrderButtonHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * @author dgibler
 *  
 */
public class DisplaySupervisionOrderAdvancedSearchAction extends JIMSBaseAction {

	/**
	 * @roseuid 438F23DA0287
	 */
	public DisplaySupervisionOrderAdvancedSearchAction() {

	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	  public void addButtonMapping(Map keyMap) {
		keyMap.put("button.advancedSuperviseeSearch", "advancedSearch");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.refresh", "refresh");
        keyMap.put("button.tasks", "progressTask");
	}
	
	
	public ActionForward progressTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
		// The assumption here is that the task is passing the casenumber and court on the URL as CaseNum  and cdi and taskId and orderId
		SupervisionOrderSearchForm supOrderForm = (SupervisionOrderSearchForm) aForm;
		//long timeStart = System.currentTimeMillis();
		String strTaskId=(String)aRequest.getAttribute("taskId");
		supOrderForm.setSpn("");
		supOrderForm.setAction("");
		supOrderForm.setSecondaryAction("");
		GetTaskEvent getTaskEvent = new GetTaskEvent() ; 
        getTaskEvent.setTaskId(strTaskId) ; 
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(getTaskEvent) ; 
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		TaskResponseEvent taskResponseEvent = (TaskResponseEvent) MessageUtil.filterComposite(response, TaskResponseEvent.class);
        
        ITaskState taskState = taskResponseEvent.getTask().getTaskState() ; 
        String mySpn= (String)taskState.get(CaseloadConstants.DEFENDANT_ID) ;
        String myOrderId= (String)taskState.get(CaseloadConstants.SUPERVISION_ORDER_ID) ;
        String myCaseNumber= (String)taskState.get(CaseloadConstants.CASENUMBER) ;
        String myCDI= (String)taskState.get(CaseloadConstants.CDI) ;
        supOrderForm.setCdi(myCDI);
        supOrderForm.setCaseNum(myCaseNumber);
		ActionForward forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		if (supOrderForm.getCaseOrderList() == null || supOrderForm.getCaseOrderList().size() <= 0) {
			sendToErrorPage(aRequest, "error.no.search.results.found");
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("noResults", UIUtil.getCurrentUserAgencyID()));
		}
		SupervisionOrderForm sof = (SupervisionOrderForm)getSessionForm(aMapping,aRequest,"supervisionOrderForm",true);
		sof.setMyStaffPos(null);
		sof.setMyStaffPos(UICommonSupervisionHelper.getUserStaffPosition(null,null));
		sof.setTaskId(strTaskId);
		sof.setTaskOrderId(myOrderId);  // purpousely not made to be set by the URL automatically to control the actual setting
		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total Time(milli seconds) to search SupervisionOrders: " + (timeEnd - timeStart));
		return forward;
    }

	public ActionForward advancedSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SupervisionOrderSearchForm supOrderForm = (SupervisionOrderSearchForm) aForm;
		supOrderForm.clear();
		supOrderForm.setAction("");
		supOrderForm.setSecondaryAction("");
		clearPrimaryKey(aRequest);
		
		// Set default connection
		supOrderForm.setConnectionId("DEF");
		
		ActionForward forward = new ActionForward();
		forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.ADVANCED_SEARCH_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;

	}

	private void clearPrimaryKey(HttpServletRequest aRequest) {
		Object obj = aRequest.getSession().getAttribute("supervisionOrderForm");
		if (obj != null) {
			SupervisionOrderForm supOrderForm = (SupervisionOrderForm) obj;
			supOrderForm.setPrimaryCaseOrderKey("");
			supOrderForm.setAction("");
			supOrderForm.setSecondaryAction("");
			supOrderForm.setTaskId(null);
		}
	}

	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
		//long timeStart = System.currentTimeMillis();
		clearPrimaryKey(aRequest);
		SupervisionOrderSearchForm supOrderForm = (SupervisionOrderSearchForm) aForm;
		SupervisionOrderForm sof = (SupervisionOrderForm)getSessionForm(aMapping,aRequest,"supervisionOrderForm",true);
		sof.setMyStaffPos(null);
		sof.setMyStaffPos(UICommonSupervisionHelper.getUserStaffPosition(null,null));
		supOrderForm.setAction("");
		supOrderForm.setSecondaryAction("");
		ActionForward forward = SupervisionOrderButtonHelper.getCaseOrderSearchResults(aMapping, aRequest);
		if (supOrderForm.getCaseOrderList() == null || supOrderForm.getCaseOrderList().size() <= 0) {
			sendToErrorPage(aRequest, "error.no.search.results.found");
			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward("noResults", UIUtil.getCurrentUserAgencyID()));
		}
		//long timeEnd = System.currentTimeMillis();
		//System.out.println("Total Time(milli seconds) to search SupervisionOrders: " + (timeEnd - timeStart));

		return forward;
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SupervisionOrderSearchForm supOrderForm = (SupervisionOrderSearchForm) aForm;
		supOrderForm.setAction("");
		supOrderForm.setSecondaryAction("");
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SupervisionOrderSearchForm supOrderForm = (SupervisionOrderSearchForm) aForm;
		supOrderForm.setSelectedValue("SPN");
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.REFRESH_SUCCESS, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	
}
