//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRReportingHistorySummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NCLastKnownAddressResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
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

public class DisplayVRReportingHistorySummaryAction extends JIMSBaseAction {

	/**
	 *@roseuid 47DA9D3C00C2
	 */
	public DisplayVRReportingHistorySummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addReportingHistory", "addReportingHistory");
		keyMap.put("button.removeSelected", "remove");
		keyMap.put("button.refreshReporting", "refresh");
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
		addNewReportingHistory(vrForm, false);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addReportingHistory(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewReportingHistory(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}

	private void addNewReportingHistory(ViolationReportsForm vrForm, boolean showAdd)
	{
		if (vrForm.isShowAddFields() == true){
			if ((vrForm.getOccurrenceDateStr() != null && !vrForm.getOccurrenceDateStr().equals("")) ||
		        (vrForm.getSelectedEventTypeIds().length != 0) ||
		        (vrForm.getReportingHistoryDetails() != null && !vrForm.getReportingHistoryDetails().equals("")) ){ 
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NonComplianceEventResponseEvent newEvent = new NonComplianceEventResponseEvent();
					if (vrForm.getOccurrenceDateStr() != null && !vrForm.getOccurrenceDateStr().equals("")) { 
						newEvent.setDateTime(UIViolationReportHelper.convertDateToTimeStamp(vrForm.getOccurrenceDateStr(), "00:00", "AM"));
					}
					newEvent.setDetails(vrForm.getReportingHistoryDetails());
					newEvent.setEventTypes(this.constructEventTypeStr(vrForm.getSelectedEventTypeIds(), vrForm.getEventTypes()));
					StringBuffer selectedEventTypes = new StringBuffer();
					for (int x = 0; x <vrForm.getSelectedEventTypeIds().length; x++){
						selectedEventTypes.append(vrForm.getSelectedEventTypeIds()[x]);
						if(x< vrForm.getSelectedEventTypeIds().length - 1) {
							selectedEventTypes.append(", ");
						}	
					}
					newEvent.setEventTypesId(selectedEventTypes.toString());
					newEvent.setNonComplianceEventId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		vrForm.clearReportingHistoryAdds();
		vrForm.setShowAddFields(showAdd);
	}
	
	
	/**
	 * @param selectedEventTypeIds
	 * @param eventTypes
	 * @return eventTypeString
	 */
	public String constructEventTypeStr(String[] selectedEventTypeIds, List eventTypes){
		String eventTypeStr = "";
		if(selectedEventTypeIds != null && selectedEventTypeIds.length > 0){
			StringBuffer selectedEventTypes = new StringBuffer();
			int len = eventTypes.size();
			
			for(int x=0; x<selectedEventTypeIds.length; x++) {
				for(int y=0; y< len; y++){
					CodeResponseEvent et = (CodeResponseEvent) eventTypes.get(y);
					if(et.getSupervisionCode().equals(selectedEventTypeIds[x])){
						selectedEventTypes.append(et.getDescription());
						if(x<selectedEventTypeIds.length - 1) {
							selectedEventTypes.append(", ");
							break;
						}	
					}
				}	
			}
			eventTypeStr = selectedEventTypes.toString();
		}
		return eventTypeStr;
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
		int len = vrForm.getSelectedReportingHistoryIds().length;
		int listSize = vrForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NonComplianceEventResponseEvent ncre = (NonComplianceEventResponseEvent) vrForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncre.getNonComplianceEventId().equalsIgnoreCase(vrForm.getSelectedReportingHistoryIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncre);
			}
		}
		vrForm.setCreate1ElementsList(reducedList);
		vrForm.clearReportingHistoryAdds();		
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
	    event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
    	event.setDefendantId(vrForm.getSuperviseeId());
		event.setCaseId(vrForm.getCaseNum());
		event.setCdi(vrForm.getCdi());
		event.setNcResponseId(vrForm.getViolationReportId());
        
	    CompositeResponse response = MessageUtil.postRequest(event);	    
	        
	    List list = MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class);
    	if (list != null && !list.isEmpty()){
			vrForm.setCreate1ElementsList(list);
			NCLastKnownAddressResponseEvent addressResp = (NCLastKnownAddressResponseEvent) MessageUtil.filterComposite(response, NCLastKnownAddressResponseEvent.class);
	        if (addressResp != null){
	        	vrForm.setLastContactDate(addressResp.getLastContactDate());
	        	vrForm.setAddressNumber(addressResp.getStreetNumber());
	        	vrForm.setAddressName(addressResp.getStreetName());
	        	vrForm.setAddressCity(addressResp.getCity());        	
	        	vrForm.setAddressState(addressResp.getState());
	        	vrForm.setAddressStateId(addressResp.getStateId());
	        	vrForm.setAddressZipCode(addressResp.getZip());
	        	vrForm.setAddressType(addressResp.getAddressType());
	        	vrForm.setAddressTypeId(addressResp.getAddressTypeId());
	        	vrForm.setShowAddress(true);
	        }			
		} else {
			vrForm.setCreate1ElementsList(new ArrayList());
		}
		vrForm.setCreate1Comments("");
		vrForm.setCurrentReportingHistoryList(new ArrayList());
		vrForm.setCurrentReportingHistoryComments("");
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
