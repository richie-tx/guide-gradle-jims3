//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayLawViolationSummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NCLawViolationResponseEvent;
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
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;

public class DisplayLawViolationSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D390268
	 */
	public DisplayLawViolationSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addLawViolation", "addLawViolation");
		keyMap.put("button.removeSelected", "remove");
		keyMap.put("button.refreshLawViolations", "refresh");
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
		addNewLawViolation(csForm, false);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addLawViolation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewLawViolation(csForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}
	
	private void addNewLawViolation(CaseSummaryForm csForm, boolean showAdd )
	{
		if (csForm.isShowAddFields() == true){
			if ((csForm.getLvCaseNum() != null && !csForm.getLvCaseNum().equals("")) ||
				(csForm.getCourtNum() != null && !csForm.getCourtNum().equals("")) ||
				(csForm.getOffenseDateStr() != null && !csForm.getOffenseDateStr().equals("")) ||
				(csForm.getOffenseLiteral() != null && !csForm.getOffenseLiteral().equals("")) ||
				(csForm.getOffenseLevelId() != null && !csForm.getOffenseLevelId().equals("")) ) {
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NCLawViolationResponseEvent newEvent = new NCLawViolationResponseEvent();
					newEvent.setCaseId(csForm.getLvCaseNum());
					newEvent.setCourtId(csForm.getCourtNum());
					newEvent.setOffenseDate(UIAdministerComplianceHelper.convertToDateTime(csForm.getOffenseDateStr(), "00:00"));
					newEvent.setOffenseLitrel(csForm.getOffenseLiteral());
					newEvent.setOffenseLevel(csForm.getOffenseLevelId());
					newEvent.setOffenseDegree(csForm.getOffenseDegreeId());
					newEvent.setLawViolationId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		csForm.clearLawViolationsAdds();
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
		int len = csForm.getSelectedLawViolationsIds().length;
		int listSize = csForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NCLawViolationResponseEvent lvre = (NCLawViolationResponseEvent) csForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (lvre.getLawViolationId().equalsIgnoreCase(csForm.getSelectedLawViolationsIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(lvre);
			}
		}
		csForm.setCreate1ElementsList(reducedList);
		csForm.clearLawViolationsAdds();
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
    	event.setRequestType(ViolationReportConstants.REQUEST_LAW_VIOLATION);
    	event.setNcResponseId(csForm.getViolationReportId());
    	event.setDefendantId(csForm.getSuperviseeId());   	
    	event.setActivationDate(csForm.getActivationDate());
        List list = MessageUtil.postRequestListFilter(event, NCLawViolationResponseEvent.class);		
		if (list != null){
			csForm.setCreate1ElementsList(list);
		} else {
			csForm.setCreate1ElementsList(new ArrayList());
		}
		csForm.setCreate1Comments("");
		csForm.setCurrentLawViolationsList(new ArrayList());
		csForm.setCurrentLawViolationsComments(""); 
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