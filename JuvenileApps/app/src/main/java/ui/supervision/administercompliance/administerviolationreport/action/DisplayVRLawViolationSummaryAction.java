//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRLawViolationSummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

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
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class DisplayVRLawViolationSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D390268
	 */
	public DisplayVRLawViolationSummaryAction() {

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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewLawViolation(vrForm, false);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewLawViolation(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewLawViolation(ViolationReportsForm vrForm, boolean showAdd)
	{
		if (vrForm.isShowAddFields() == true){
			if ((vrForm.getLvCaseNum() != null && !vrForm.getLvCaseNum().equals("")) ||
				(vrForm.getCourtNum() != null && !vrForm.getCourtNum().equals("")) ||
				(vrForm.getOffenseDateStr() != null && !vrForm.getOffenseDateStr().equals("")) ||
				(vrForm.getOffenseLiteral() != null && !vrForm.getOffenseLiteral().equals("")) ||
				(vrForm.getOffenseLevelId() != null && !vrForm.getOffenseLevelId().equals("")) ) {
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NCLawViolationResponseEvent newEvent = new NCLawViolationResponseEvent();
					newEvent.setCaseId(vrForm.getLvCaseNum());
					newEvent.setCourtId(vrForm.getCourtNum());
					newEvent.setOffenseDate(UIAdministerComplianceHelper.convertToDateTime(vrForm.getOffenseDateStr(), "00:00"));
					newEvent.setOffenseLitrel(vrForm.getOffenseLiteral());
					newEvent.setOffenseLevel(vrForm.getOffenseLevelId());
					newEvent.setOffenseDegree(vrForm.getOffenseDegreeId());
					newEvent.setLawViolationId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		vrForm.clearLawViolationsAdds();
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
		int len = vrForm.getSelectedLawViolationsIds().length;
		int listSize = vrForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NCLawViolationResponseEvent lvre = (NCLawViolationResponseEvent) vrForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (lvre.getLawViolationId().equalsIgnoreCase(vrForm.getSelectedLawViolationsIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(lvre);
			}
		}
		vrForm.setCreate1ElementsList(reducedList);
		vrForm.clearLawViolationsAdds();
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
    	event.setRequestType(ViolationReportConstants.REQUEST_LAW_VIOLATION);
    	event.setNcResponseId(vrForm.getViolationReportId());
    	event.setDefendantId(vrForm.getSuperviseeId()); 
    	
    	StringBuffer sb = new StringBuffer();
		sb.append( vrForm.getCdi()).append( vrForm.getCaseNum() );
    	event.setCaseId( sb.toString() );
    	event.setActivationDate(vrForm.getOrderActivationDate());
    	
        List list = MessageUtil.postRequestListFilter(event, NCLawViolationResponseEvent.class);		
		if (list != null){
			vrForm.setCreate1ElementsList(list);
		} else {
			vrForm.setCreate1ElementsList(new ArrayList());
		}
		vrForm.setCreate1Comments("");
		vrForm.setCurrentLawViolationsList(new ArrayList());
		vrForm.setCurrentLawViolationsComments(""); 
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
