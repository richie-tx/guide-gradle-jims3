//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\DisplayCaseAssignmentDataControlOfficerAction.java

package ui.supervision.posttrial.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.cscdstaffposition.reply.OrganizationResponseEvent;
import messaging.posttrial.GetCaseAssignmentOfficersEvent;
import messaging.posttrial.reply.CaseAssignmentOfficerResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.posttrial.form.CaseAssignmentDataControlForm;

/*
 * 
 * @author cshimek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayCaseAssignmentDataControlOfficerAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayCaseAssignmentDataControlOfficerAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
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
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		String forward = UIConstants.NEXT_SUCCESS;
		cadsForm.setLatestPositionAssignmentDate(DateUtil.stringToDate("01/01/1900", DateUtil.DATE_FMT_1));
		if (cadsForm.getDivisionPgmUnitsList() != null){
			int len = cadsForm.getDivisionPgmUnitsList().size(); 
			for (int x=0; x<len; x++){
				OrganizationResponseEvent ore = (OrganizationResponseEvent) cadsForm.getDivisionPgmUnitsList().get(x);
				if (ore.getChildren() != null){
					Iterator cIter = ore.getChildren().iterator();
					while(cIter.hasNext()){
						OrganizationResponseEvent core = (OrganizationResponseEvent) cIter.next();
						if (core.getOrganizationId().equals(cadsForm.getDivisionPgmUnitId() ) ){
							cadsForm.setDivisionPgmUnitDesc(core.getDescription());
							String existingDPUIds = core.getParentId() + "/" + core.getOrganizationId();
							cadsForm.setCurrentDivisionPgmUnitId(existingDPUIds);
							x = len;
							break;
						}
					}
				}
			}			
		}	
		cadsForm.setOfficerList(new ArrayList());
//  this state should only be true if no officer is assigned		
      if (cadsForm.getCaseAssignmentState().equalsIgnoreCase(CaseloadConstants.PROGRAM_UNIT_ASSIGNED) ){
			 forward = UIConstants.SUMMARY_SUCCESS;
      } else {
	  		GetCaseAssignmentOfficersEvent gEvent = new GetCaseAssignmentOfficersEvent();
			gEvent.setOrganizationId(cadsForm.getDivisionPgmUnitId());
			CompositeResponse response = MessageUtil.postRequest(gEvent);        
			List list = MessageUtil.compositeToList(response, CaseAssignmentOfficerResponseEvent.class);
			if (list != null && list.size() > 0){
				cadsForm.setOfficerList(sortOfficersList(list));
			} else {
				sendToErrorPage(aRequest,"error.generic","No officers found for selected Program Unit");
				forward = UIConstants.FAILURE;
			}
			if (cadsForm.getSecondaryAction().equals(UIConstants.UPDATE)){
				if (cadsForm.getCaseAssignmentHistoryList() != null){
					int len = cadsForm.getCaseAssignmentHistoryList().size(); 
					for (int x=0; x<len; x++){
						CaseAssignmentTO cat = (CaseAssignmentTO) cadsForm.getCaseAssignmentHistoryList().get(x);
						if (cat.getOfficerAssignDate() != null){
							if (cat.getOfficerAssignDate().after(cadsForm.getLatestPositionAssignmentDate())){								
								cadsForm.setLatestPositionAssignmentDate(cat.getOfficerAssignDate());
							}
						}
					}		
				}
			}
		}
		return aMapping.findForward(forward);
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
	
	public static List sortOfficersList(List officerList){
		if (officerList.size() > 1){
			SortedMap map = new TreeMap();
			String lastName = "";
			String firstName = "";
			String middleName = "";
			String posName = "";
			for (int x = 0; x < officerList.size(); x++){
				CaseAssignmentOfficerResponseEvent caore = (CaseAssignmentOfficerResponseEvent) officerList.get(x);
				lastName = "";
				firstName = "";
				middleName = "";
				posName = "";

				if (caore.getOfficerLastName() != null){
					lastName = caore.getOfficerLastName().toUpperCase();
				}
				if (caore.getOfficerFirstName() != null){
					firstName = caore.getOfficerFirstName().toUpperCase();
				}
				if (caore.getOfficerMiddleName() != null){
					middleName = caore.getOfficerMiddleName().toUpperCase();
				}
				if (caore.getOfficerPosition() != null){
					posName = caore.getOfficerPosition();
				}
				if (caore.getOfficerLastName() == null){
					lastName = "NO OFFICER ASSIGNED";
				}
				map.put(lastName + firstName + middleName + posName, caore);
			}
			officerList = new ArrayList(map.values());
			} 
		return officerList;
	}
}