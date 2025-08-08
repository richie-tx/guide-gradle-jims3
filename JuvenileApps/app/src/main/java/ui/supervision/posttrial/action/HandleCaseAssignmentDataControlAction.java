//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\HandleCaseAssignmentDataControlAction.java

package ui.supervision.posttrial.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.posttrial.form.CaseAssignmentDataControlForm;

/*
 * 
 * @author cshimek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class HandleCaseAssignmentDataControlAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public HandleCaseAssignmentDataControlAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.update", "update");
		keyMap.put("button.correct", "correct");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.cancel", "cancel");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		cadsForm.setPgmUnitAssignmentDateStr("");
		cadsForm.setPositionAssignmentDateStr("");
		cadsForm.setCaseAssignmentState("");
		cadsForm.setDivisionPgmUnitsList(new ArrayList());
		if (cadsForm.getDivisionPgmUnitsList() == null || cadsForm.getDivisionPgmUnitsList().isEmpty()){
			cadsForm.setDivisionPgmUnitsList(new ArrayList(UIAdminStaffHelper.getActiveOrganizationalHeirarchy()));
		}
		if (cadsForm.getCurrentCaseAssignment().getProgramUnitAssignDate() != null){
			cadsForm.setPgmUnitAssignmentDateStr(DateUtil.dateToString(cadsForm.getCurrentCaseAssignment().getProgramUnitAssignDate(), DateUtil.DATE_FMT_1));
		}
		if (cadsForm.getCurrentCaseAssignment().getOfficerAssignDate() != null){
			cadsForm.setPositionAssignmentDateStr(DateUtil.dateToString(cadsForm.getCurrentCaseAssignment().getOfficerAssignDate(), DateUtil.DATE_FMT_1));
		}	
		if (cadsForm.getCurrentCaseAssignment().getCaseAssignmentState() != null){
			cadsForm.setCaseAssignmentState(cadsForm.getCurrentCaseAssignment().getCaseAssignmentState());
		}
	  	String existingDPUIds = getExistingDivPgmUnitIds(cadsForm.getDivisionPgmUnitsList(), cadsForm.getCurrentCaseAssignment().getProgramUnitId());
		cadsForm.setCurrentDivisionPgmUnitId(existingDPUIds);
		cadsForm.setCurrentOfficerId(cadsForm.getCurrentCaseAssignment().getAssignedStaffPositionId());
		cadsForm.setSecondaryAction(UIConstants.UPDATE);
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward correct(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		cadsForm.setDivisionPgmUnitsList(new ArrayList());
		if (cadsForm.getDivisionPgmUnitsList() == null || cadsForm.getDivisionPgmUnitsList().isEmpty()){
			cadsForm.setDivisionPgmUnitsList(new ArrayList(UIAdminStaffHelper.getActiveOrganizationalHeirarchy()));
		}
		cadsForm.setPgmUnitAssignmentDateStr("");
		cadsForm.setCurrentDivisionPgmUnitId("");
		cadsForm.setCaseAssignmentState("");
		cadsForm.setPositionAssignmentDateStr("");
		int len = cadsForm.getCaseAssignmentHistoryList().size(); 
		for (int x=0; x<len; x++){
			CaseAssignmentTO cat = (CaseAssignmentTO) cadsForm.getCaseAssignmentHistoryList().get(x);
			if (cat.getCaseAssignmentHistId().equals(cadsForm.getSelectedValue())){
				if (cat.getProgramUnitAssignDate() != null){
					cadsForm.setPgmUnitAssignmentDateStr(DateUtil.dateToString(cat.getProgramUnitAssignDate(), DateUtil.DATE_FMT_1));
				}
				if (cat.getOfficerAssignDate() != null){
					cadsForm.setPositionAssignmentDateStr(DateUtil.dateToString(cat.getOfficerAssignDate(), DateUtil.DATE_FMT_1));
				}
				String existingDPUIds = getExistingDivPgmUnitIds(cadsForm.getDivisionPgmUnitsList(), cat.getProgramUnitId());
				cadsForm.setCurrentDivisionPgmUnitId(existingDPUIds);
				if (cat.getCaseAssignmentState() != null){
					cadsForm.setCaseAssignmentState(cat.getCaseAssignmentState());
				}
				if (cat.getAssignedStaffPositionId() != null){
					cadsForm.setCurrentOfficerId(cat.getAssignedStaffPositionId());
				}
				break;
			}
		}
		cadsForm.setSecondaryAction(UIConstants.CORRECT);
		return aMapping.findForward(UIConstants.CORRECT_SUCCESS);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		if (cadsForm.getDivisionPgmUnitsList() == null || cadsForm.getDivisionPgmUnitsList().isEmpty()){
			cadsForm.setDivisionPgmUnitsList(new ArrayList(UIAdminStaffHelper.getActiveOrganizationalHeirarchy()));
		}
		cadsForm.setPgmUnitAssignmentDateStr("");
		cadsForm.setCurrentDivisionPgmUnitId("");
		int len = cadsForm.getCaseAssignmentHistoryList().size(); 
		for (int x=0; x<len; x++){
			CaseAssignmentTO cat = (CaseAssignmentTO) cadsForm.getCaseAssignmentHistoryList().get(x);
			if (cat.getCaseAssignmentHistId().equals(cadsForm.getSelectedValue())){
				if (cat.getProgramUnitAssignDate() != null){
					cadsForm.setPgmUnitAssignmentDateStr(DateUtil.dateToString(cat.getProgramUnitAssignDate(), DateUtil.DATE_FMT_1));
				}
				if (cadsForm.getDivisionPgmUnitsList() != null){
					int len2 = cadsForm.getDivisionPgmUnitsList().size(); 
					for (int y=0; y<len2; y++){
						OrganizationResponseEvent ore = (OrganizationResponseEvent) cadsForm.getDivisionPgmUnitsList().get(y);
						if (ore.getChildren() != null){
							Iterator cIter = ore.getChildren().iterator();
							while(cIter.hasNext()){
								OrganizationResponseEvent core = (OrganizationResponseEvent) cIter.next();
								if (core.getOrganizationId().equals(cat.getProgramUnitId())){
									cadsForm.setDivisionPgmUnitDesc(core.getDescription());
									x = len;
									break;
								}
							}
						}
					}					
				}
				if (cat.getOfficerAssignDate() != null){
					cadsForm.setPositionAssignmentDateStr(DateUtil.dateToString(cat.getOfficerAssignDate(), DateUtil.DATE_FMT_1));
				}
				cadsForm.setOfficerName("");
				StringBuffer aStr = new StringBuffer();
				if (cat.getOfficerName() != null){
					aStr.append(cat.getOfficerName().getFormattedName());
				}	
				aStr.append("|");
				if (cat.getAssignedStaffPositionName() != null){
					aStr.append(cat.getAssignedStaffPositionName());
				}	
				if (aStr.toString().length() > 3){
					cadsForm.setOfficerName(aStr.toString());	
				}	
				break;
			}
		}
		cadsForm.setSecondaryAction(UIConstants.DELETE);
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}
	/**
	 * @param list
	 * @param string
	 * @return returnStr
	 */	
	private String getExistingDivPgmUnitIds( List pgmUnitsList, String divPgmUnitId){
		String returnStr = "";
		if (pgmUnitsList != null){
			int len = pgmUnitsList.size(); 
			for (int x=0; x<len; x++){
				OrganizationResponseEvent ore = (OrganizationResponseEvent) pgmUnitsList.get(x);
				if (ore.getChildren() != null){
					Iterator cIter = ore.getChildren().iterator();
					while(cIter.hasNext()){
						OrganizationResponseEvent core = (OrganizationResponseEvent) cIter.next();
						if (core.getOrganizationId().equals(divPgmUnitId ) ){
							returnStr = core.getParentId() + "/" + core.getOrganizationId();
							x = len;
							break;
						}
					}
				}
			}			
		}
		return returnStr;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		cadsForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}
}
