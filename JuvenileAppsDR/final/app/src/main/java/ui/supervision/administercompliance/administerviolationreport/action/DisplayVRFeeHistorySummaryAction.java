//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRFeeHistorySummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NCFeeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
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

public class DisplayVRFeeHistorySummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3803C0
	 */
	public DisplayVRFeeHistorySummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addFee", "addFeeHistory");
		keyMap.put("button.removeSelected", "remove");
		keyMap.put("button.refreshFees", "refresh");
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
		addNewFee(vrForm, false);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addFeeHistory(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewFee(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewFee(ViolationReportsForm vrForm, boolean showAdd)
	{
		if (vrForm.isShowAddFields() == true){
			if ((vrForm.getPayTypeId()!= null && !vrForm.getPayTypeId().equals("")) ||
		        (vrForm.getAmountOrdered()!= null && !vrForm.getAmountOrdered().equals("")) ||
				(vrForm.getPaidToDate()!= null && !vrForm.getPaidToDate().equals("")) ||
				(vrForm.getDeliquentAmount() != null && !vrForm.getDeliquentAmount().equals("")) ){
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NCFeeResponseEvent newEvent = new NCFeeResponseEvent();
					for (int x=0; x<vrForm.getPayTypes().size(); x++){
						CodeResponseEvent cre = (CodeResponseEvent) vrForm.getPayTypes().get(x);
						if (cre.getCode().equals(vrForm.getPayTypeId())){
							newEvent.setPayType(cre.getDescription());
							break;	
						}
					}
					newEvent.setAmountOrdered(vrForm.getAmountOrdered());
					newEvent.setPaidToDate(vrForm.getPaidToDate());
					newEvent.setDelinquentAmount(vrForm.getDeliquentAmount());
					newEvent.setFeeId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate1ElementsList().add(newEvent);
			}		  
		}
		vrForm.clearFeeHistoryAdds();
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
		int len = vrForm.getSelectedFeeHistoryIds().length;
		int listSize = vrForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NCFeeResponseEvent ncere = (NCFeeResponseEvent) vrForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncere.getFeeId().equalsIgnoreCase(vrForm.getSelectedFeeHistoryIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncere);
			}
		}
		vrForm.setCreate1ElementsList(reducedList);
		vrForm.clearFeeHistoryAdds();		
		
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
        event.setRequestType(ViolationReportConstants.REQUEST_DELINQUENT_FEE);
    	event.setNcResponseId(vrForm.getViolationReportId());
    	event.setCaseId(vrForm.getCaseNum());
		event.setCdi(vrForm.getCdi()); 
		event.setDefendantId( vrForm.getSuperviseeId() );

        List fList = MessageUtil.postRequestListFilter(event, NCFeeResponseEvent.class);
		if (fList != null){
			if (!fList.isEmpty() ){
	        	for (int f = 0; f < fList.size(); f++){
	        		NCFeeResponseEvent fre = (NCFeeResponseEvent) fList.get(f);
	        		if ("0.00".equals(fre.getAmountOrdered() ) ) {
	        			fList.remove(f);
	        		}
	        	}
	        }
			vrForm.setCreate1ElementsList(fList);
		} else {
			vrForm.setCreate1ElementsList(new ArrayList());
		}
		vrForm.setCreate1Comments("");
		vrForm.setCurrentFeeHistoryList(new ArrayList());
		vrForm.setCurrentFeeHistoryComments("");         
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
