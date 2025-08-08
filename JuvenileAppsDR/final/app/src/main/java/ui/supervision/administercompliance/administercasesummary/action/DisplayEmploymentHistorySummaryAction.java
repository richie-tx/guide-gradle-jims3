//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayEmploymentHistorySummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NCEmploymentResponseEvent;
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
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;

public class DisplayEmploymentHistorySummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D380130
	 */
	public DisplayEmploymentHistorySummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addEmployment", "addEmployment");
		keyMap.put("button.removeSelected", "remove");
		keyMap.put("button.refreshEmployment", "refresh");	
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
		addNewEmployment(csForm, false);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addEmployment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewEmployment(csForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	
		
	private void addNewEmployment(CaseSummaryForm csForm, boolean showAdd)
	{
		String lowestCls = "999";
		if (csForm.isShowAddFields() == true){
			
			// Get the lowest employment cls
			List employHist = new ArrayList();
			List empHistList = csForm.getCreate1ElementsList();
			Map empMap = new TreeMap();
			
			for ( int z = 0; z < empHistList.size(); z++  ){
				
				NCEmploymentResponseEvent empResponse = (NCEmploymentResponseEvent) empHistList.get( z );
				empMap.put( empResponse.getSeqNum(), empResponse.getSeqNum() );
				
			}
			employHist = new ArrayList( empMap.values());
			if ( employHist.size() > 0 ){
				lowestCls = (String) employHist.get( 0 );
				int tempVal = Integer.parseInt( lowestCls );
				tempVal --;
				lowestCls = String.valueOf( tempVal );
			}
			
			if ((csForm.getEmployerName()!= null && !csForm.getEmployerName().equals("")) ||
		        (csForm.getJobTitle()!= null && !csForm.getJobTitle().equals("")) ||
				(csForm.getJobStartDateStr() != null && !csForm.getJobStartDateStr().equals("")) ||
				(csForm.getJobStatusId().length() != 0) ){
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NCEmploymentResponseEvent newEvent = new NCEmploymentResponseEvent();
					newEvent.setEmployerName(csForm.getEmployerName());
					newEvent.setJobTitle(csForm.getJobTitle());
					newEvent.setStartDateStr(csForm.getJobStartDateStr());
					newEvent.setEmploymentId(csForm.getAddItemIndex());
					newEvent.setStatusId(csForm.getJobStatusId());
					newEvent.setSeqNum( lowestCls );
					newEvent.setStatusDesc("");
					int len = csForm.getEmploymentStatusList().size();
					for (int x=0; x<len; x++){
						CodeResponseEvent cre = (CodeResponseEvent) csForm.getEmploymentStatusList().get(x);
						if (cre.getCodeId().equals(csForm.getJobStatusId())){
							newEvent.setStatusDesc(cre.getDescription());
							break;	
						}
					}
					newEvent.setNcResponseId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate1ElementsList().add(newEvent);
			}		  
		}
		csForm.clearEmploymentHistoryAdds();
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
		int len = csForm.getSelectedEmploymentHistoryIds().length;
		int listSize = csForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NCEmploymentResponseEvent ncere = (NCEmploymentResponseEvent) csForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncere.getEmploymentId().equalsIgnoreCase(csForm.getSelectedEmploymentHistoryIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncere);
			}
		}
		csForm.setCreate1ElementsList(reducedList);
		csForm.clearEmploymentHistoryAdds();		
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
        event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
    	event.setNcResponseId(csForm.getViolationReportId());
    	event.setCaseId(csForm.getCaseNum());
		event.setCdi(csForm.getCdi());   
		event.setDefendantId(csForm.getSuperviseeId());

        List list = MessageUtil.postRequestListFilter(event, NCEmploymentResponseEvent.class);
		if (list != null){
    	    for (int t=0; t< list.size(); t++){
    		    NCEmploymentResponseEvent ncre = (NCEmploymentResponseEvent) list.get(t);
    		    if ((ncre.getStatusDesc() == null || ncre.getStatusDesc().equals("") && ncre.getStatusId() != null)){
    			    for (int x=0; x<csForm.getEmploymentStatusList().size(); x++){
    			    	CodeResponseEvent cre = (CodeResponseEvent) csForm.getEmploymentStatusList().get(x);
    			    	if (cre.getCodeId().equals(ncre.getStatusId())){
    			    		ncre.setStatusDesc(cre.getDescription());
    			    		break;	
    			    	}
    			    }
    		    }
    	    }				
    	    csForm.setCreate1ElementsList(list);
		} else {
			csForm.setCreate1ElementsList(new ArrayList());
		}
		csForm.setCreate1Comments("");
		csForm.setCurrentEmploymentHistoryList(new ArrayList());
		csForm.setCurrentEmploymentHistoryComments("");          
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
