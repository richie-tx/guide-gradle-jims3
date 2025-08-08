//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayPositiveUrinalysisSummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;

public class DisplayPositiveUrinalysisSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3A0101
	 */
	public DisplayPositiveUrinalysisSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addPositiveUrinalysis", "addPositiveUrinalysis");
		keyMap.put("button.removeSelected", "remove");
		keyMap.put("button.refreshPositiveUrinalysis", "refresh");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "back");  // cancel functions same as back
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewPositiveUrinalysis(csForm, false);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addPositiveUrinalysis(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewPositiveUrinalysis(csForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewPositiveUrinalysis(CaseSummaryForm csForm, boolean showAdd )
	{
		if (csForm.isShowAddFields() == true){
			if ((csForm.getTestDateStr() != null && !csForm.getTestDateStr().equals("")) ||
				(csForm.getSubstance() != null && !csForm.getSubstance().equals("")) ) {
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NonComplianceEventResponseEvent newEvent = new NonComplianceEventResponseEvent();
					if (csForm.getTestDateStr() != null && !csForm.getTestDateStr().equals("")){
						newEvent.setDateTime(UICaseSummaryHelper.convertDateToTimeStamp(csForm.getTestDateStr(), "00:00", "AM"));
					}
					newEvent.setDetails(csForm.getSubstance());
					newEvent.setNonComplianceEventId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		csForm.clearPositiveUrinalysisAdds();
		csForm.setShowAddFields(showAdd);
	}	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward remove(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		int len = csForm.getSelectedPositiveUrinalysisIds().length;
		int listSize = csForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NonComplianceEventResponseEvent ncre = (NonComplianceEventResponseEvent) csForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncre.getNonComplianceEventId().equalsIgnoreCase(csForm.getSelectedPositiveUrinalysisIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncre);
			}
		}
		csForm.setCreate1ElementsList(reducedList);
		csForm.clearPositiveUrinalysisAdds();			
		return aMapping.findForward(UIConstants.REMOVE_FROM_LIST_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
   	    RefreshNCResponseComponentsEvent event = (RefreshNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.REFRESHNCRESPONSECOMPONENTS);
        event.setRequestType(ViolationReportConstants.REQUEST_POSITIVE_UA);
    	event.setNcResponseId(csForm.getViolationReportId());
    	event.setCaseId(csForm.getCaseNum());
		event.setCdi(csForm.getCdi());    
        
        List list = MessageUtil.postRequestListFilter(event, NonComplianceEventResponseEvent.class);
		if (list != null){
			csForm.setCreate1ElementsList(list);
		} else {
			csForm.setCreate1ElementsList(new ArrayList());
		}
		csForm.setCreate1Comments("");
		csForm.setCurrentPositiveUrinalysisList(new ArrayList());
		csForm.setCurrentPositiveUrinalysisComments("");
		csForm.setCurrentTotalSpecimensAnalyzed("");
// assign to value returned from postRequest		
		csForm.setTotalSpecimensAnalyzed("");
		csForm.setShowAddFields(false);      
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.BACK;
		if (csForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}	
}
