//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRPositiveUrinalysisSummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

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
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class DisplayVRPositiveUrinalysisSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3A0101
	 */
	public DisplayVRPositiveUrinalysisSummaryAction() {

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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewPositiveUrinalysis(vrForm, false);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewPositiveUrinalysis(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewPositiveUrinalysis(ViolationReportsForm vrForm, boolean showAdd)
	{
		if (vrForm.isShowAddFields() == true){
			if ((vrForm.getTestDateStr() != null && !vrForm.getTestDateStr().equals("")) ||
				(vrForm.getSubstance() != null && !vrForm.getSubstance().equals("")) ) {
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NonComplianceEventResponseEvent newEvent = new NonComplianceEventResponseEvent();
					if (vrForm.getTestDateStr() != null && !vrForm.getTestDateStr().equals("")){
						newEvent.setDateTime(UIViolationReportHelper.convertDateToTimeStamp(vrForm.getTestDateStr(), "00:00", "AM"));
					} 
					newEvent.setDetails(vrForm.getSubstance());
					newEvent.setNonComplianceEventId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		vrForm.clearPositiveUrinalysisAdds();
		vrForm.setShowAddFields(showAdd);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		int len = vrForm.getSelectedPositiveUrinalysisIds().length;
		int listSize = vrForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NonComplianceEventResponseEvent ncre = (NonComplianceEventResponseEvent) vrForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncre.getNonComplianceEventId().equalsIgnoreCase(vrForm.getSelectedPositiveUrinalysisIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncre);
			}
		}
		vrForm.setCreate1ElementsList(reducedList);
		vrForm.clearPositiveUrinalysisAdds();			
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
   	    RefreshNCResponseComponentsEvent event = (RefreshNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.REFRESHNCRESPONSECOMPONENTS);
        event.setRequestType(ViolationReportConstants.REQUEST_POSITIVE_UA);
    	event.setNcResponseId(vrForm.getViolationReportId());
    	event.setCaseId(vrForm.getCaseNum());
		event.setCdi(vrForm.getCdi());    
        
        List list = MessageUtil.postRequestListFilter(event, NonComplianceEventResponseEvent.class);
		if (list != null){
			vrForm.setCreate1ElementsList(list);
		} else {
			vrForm.setCreate1ElementsList(new ArrayList());
		}
		vrForm.setCreate1Comments("");
		vrForm.setCurrentPositiveUrinalysisList(new ArrayList());
		vrForm.setCurrentPositiveUrinalysisComments("");
		vrForm.setCurrentTotalSpecimensAnalyzed("");
// assign to value returned from postRequest		
		vrForm.setTotalSpecimensAnalyzed("");
		vrForm.setShowAddFields(false);      
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forwardStr = UIConstants.BACK;
		if (vrForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}	
}
