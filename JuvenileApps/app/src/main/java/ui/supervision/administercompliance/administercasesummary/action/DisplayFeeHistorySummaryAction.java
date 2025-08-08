//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayFeeHistorySummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

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
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;

public class DisplayFeeHistorySummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3803C0
	 */
	public DisplayFeeHistorySummaryAction() {

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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewFee(csForm, false);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewFee(csForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	
	private void addNewFee(CaseSummaryForm csForm, boolean showAdd)
	{
		if (csForm.isShowAddFields() == true){
			if ((csForm.getPayTypeId()!= null && !csForm.getPayTypeId().equals("")) ||
		        (csForm.getAmountOrdered()!= null && !csForm.getAmountOrdered().equals("")) ||
				(csForm.getPaidToDate()!= null && !csForm.getPaidToDate().equals("")) ||
				(csForm.getDeliquentAmount() != null && !csForm.getDeliquentAmount().equals("")) ){
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NCFeeResponseEvent newEvent = new NCFeeResponseEvent();
					for (int x=0; x<csForm.getPayTypes().size(); x++){
						CodeResponseEvent cre = (CodeResponseEvent) csForm.getPayTypes().get(x);
						if (cre.getCode().equals(csForm.getPayTypeId())){
							newEvent.setPayType(cre.getDescription());
							break;	
						}
					}
					newEvent.setAmountOrdered(csForm.getAmountOrdered());
					newEvent.setPaidToDate(csForm.getPaidToDate());
					newEvent.setDelinquentAmount(csForm.getDeliquentAmount());
					newEvent.setFeeId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate1ElementsList().add(newEvent);
			}		  
		}
		csForm.clearFeeHistoryAdds();
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
		int len = csForm.getSelectedFeeHistoryIds().length;
		int listSize = csForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NCFeeResponseEvent ncere = (NCFeeResponseEvent) csForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncere.getFeeId().equalsIgnoreCase(csForm.getSelectedFeeHistoryIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncere);
			}
		}
		csForm.setCreate1ElementsList(reducedList);
		csForm.clearFeeHistoryAdds();		
		
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
        event.setRequestType(ViolationReportConstants.REQUEST_DELINQUENT_FEE);
    	event.setNcResponseId(csForm.getViolationReportId());
    	event.setCaseId(csForm.getCaseNum());
		event.setCdi(csForm.getCdi());    
		event.setDefendantId( csForm.getSuperviseeId() );
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
			csForm.setCreate1ElementsList(fList);
		} else {
			csForm.setCreate1ElementsList(new ArrayList());
		}
		csForm.setCreate1Comments("");
		csForm.setCurrentFeeHistoryList(new ArrayList());
		csForm.setCurrentFeeHistoryComments("");         
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