//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayReportingHistorySummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NCLastKnownAddressResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
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
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;

public class DisplayReportingHistorySummaryAction extends JIMSBaseAction {

	/**
	 *@roseuid 47DA9D3C00C2
	 */
	public DisplayReportingHistorySummaryAction() {

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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewReportingHistory(csForm, false);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewReportingHistory(csForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}

	private void addNewReportingHistory(CaseSummaryForm csForm, boolean showAdd)
	{
		if (csForm.isShowAddFields() == true){
			if ((csForm.getOccurrenceDateStr() != null && !csForm.getOccurrenceDateStr().equals("")) ||
		        (csForm.getSelectedEventTypeIds().length != 0) ||
		        (csForm.getReportingHistoryDetails() != null && !csForm.getReportingHistoryDetails().equals("")) ){ 
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NonComplianceEventResponseEvent newEvent = new NonComplianceEventResponseEvent();
					if (csForm.getOccurrenceDateStr() != null && !csForm.getOccurrenceDateStr().equals("")) { 
						newEvent.setDateTime(UIViolationReportHelper.convertDateToTimeStamp(csForm.getOccurrenceDateStr(), "00:00", "AM"));
					}
					newEvent.setDetails(csForm.getReportingHistoryDetails());
					newEvent.setEventTypes(this.constructEventTypeStr(csForm.getSelectedEventTypeIds(), csForm.getEventTypes()));
					StringBuffer selectedEventTypes = new StringBuffer();
					for (int x = 0; x <csForm.getSelectedEventTypeIds().length; x++){
						selectedEventTypes.append(csForm.getSelectedEventTypeIds()[x]);
						if(x< csForm.getSelectedEventTypeIds().length - 1) {
							selectedEventTypes.append(", ");
						}	
					}
					newEvent.setEventTypesId(selectedEventTypes.toString());
					newEvent.setNonComplianceEventId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		csForm.clearReportingHistoryAdds();
		csForm.setShowAddFields(showAdd);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		int len = csForm.getSelectedReportingHistoryIds().length;
		int listSize = csForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NonComplianceEventResponseEvent ncre = (NonComplianceEventResponseEvent) csForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncre.getNonComplianceEventId().equalsIgnoreCase(csForm.getSelectedReportingHistoryIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncre);
			}
		}
		csForm.setCreate1ElementsList(reducedList);
		csForm.clearReportingHistoryAdds();		
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
	    event.setRequestType(ViolationReportConstants.REQUEST_REPORTING);
    	event.setDefendantId(csForm.getSuperviseeId());
		event.setCaseId(csForm.getCaseNum());
		event.setCdi(csForm.getCdi());
		event.setNcResponseId(csForm.getViolationReportId());
        
		CompositeResponse response = MessageUtil.postRequest(event);
		List list = MessageUtil.compositeToList(response, NonComplianceEventResponseEvent.class);        
	    if (list != null && !list.isEmpty()){
			csForm.setCreate1ElementsList(list);
		    NCLastKnownAddressResponseEvent addressResp = (NCLastKnownAddressResponseEvent) MessageUtil.filterComposite(response, NCLastKnownAddressResponseEvent.class);
	        if (addressResp != null){
	        	csForm.setLastContactDate(addressResp.getLastContactDate());
	        	csForm.setAddressNumber(addressResp.getStreetNumber());
	        	csForm.setAddressName(addressResp.getStreetName());
	        	csForm.setAddressCity(addressResp.getCity());        	
	        	csForm.setAddressState(addressResp.getState());
	        	csForm.setAddressStateId(addressResp.getStateId());
	        	csForm.setAddressZipCode(addressResp.getZip());
	        	csForm.setAddressType(addressResp.getAddressType());
	        	csForm.setAddressTypeId(addressResp.getAddressTypeId());
	        	csForm.setShowAddress(true);
	        }			
		} else {
			csForm.setCreate1ElementsList(new ArrayList());
		}
		csForm.setCreate1Comments("");
		csForm.setCurrentReportingHistoryList(new ArrayList());
		csForm.setCurrentReportingHistoryComments("");
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
