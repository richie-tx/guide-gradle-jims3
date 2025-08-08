//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVREmploymentHistorySummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

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
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class DisplayVREmploymentHistorySummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D380130
	 */
	public DisplayVREmploymentHistorySummaryAction() {

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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewEmployment(vrForm, false);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewEmployment(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewEmployment(ViolationReportsForm vrForm, boolean showAdd)
	{
		String lowestCls = "999";
		if (vrForm.isShowAddFields() == true){
			
				// Get the lowest employment cls
				List employHist = new ArrayList();
				List empHistList = vrForm.getCreate1ElementsList();
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
			
			if ((vrForm.getEmployerName()!= null && !vrForm.getEmployerName().equals("")) ||
		        (vrForm.getJobTitle()!= null && !vrForm.getJobTitle().equals("")) ||
				(vrForm.getJobStartDateStr() != null && !vrForm.getJobStartDateStr().equals("")) ||
				(vrForm.getJobStatusId().length() != 0) ){
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NCEmploymentResponseEvent newEvent = new NCEmploymentResponseEvent();
					newEvent.setEmployerName(vrForm.getEmployerName());
					newEvent.setJobTitle(vrForm.getJobTitle());
					newEvent.setStartDateStr(vrForm.getJobStartDateStr());
					newEvent.setEmploymentId(vrForm.getAddItemIndex());
					newEvent.setStatusId(vrForm.getJobStatusId());
					newEvent.setStatusDesc("");
					newEvent.setSeqNum( lowestCls );
					int len = vrForm.getEmploymentStatusList().size();
					for (int x=0; x<len; x++){
						CodeResponseEvent cre = (CodeResponseEvent) vrForm.getEmploymentStatusList().get(x);
						if (cre.getCodeId().equals(vrForm.getJobStatusId())){
							newEvent.setStatusDesc(cre.getDescription());
							break;	
						}
					}
					newEvent.setNcResponseId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate1ElementsList().add(newEvent);
			}		  
		}
		vrForm.clearEmploymentHistoryAdds();
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
		int len = vrForm.getSelectedEmploymentHistoryIds().length;
		int listSize = vrForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			NCEmploymentResponseEvent ncere = (NCEmploymentResponseEvent) vrForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (ncere.getEmploymentId().equalsIgnoreCase(vrForm.getSelectedEmploymentHistoryIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(ncere);
			}
		}
		vrForm.setCreate1ElementsList(reducedList);
		vrForm.clearEmploymentHistoryAdds();		
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
        event.setRequestType(ViolationReportConstants.REQUEST_EMPLOYMENT);
    	event.setNcResponseId(vrForm.getViolationReportId());
    	event.setCaseId(vrForm.getCaseNum());
		event.setCdi(vrForm.getCdi());   
		event.setDefendantId(vrForm.getSuperviseeId());

        List list = MessageUtil.postRequestListFilter(event, NCEmploymentResponseEvent.class);
		if (list != null){
    	    for (int t=0; t< list.size(); t++){
    		    NCEmploymentResponseEvent ncre = (NCEmploymentResponseEvent) list.get(t);
    		    if ((ncre.getStatusDesc() == null || ncre.getStatusDesc().equals("") && ncre.getStatusId() != null)){
    			    for (int x=0; x<vrForm.getEmploymentStatusList().size(); x++){
    			    	CodeResponseEvent cre = (CodeResponseEvent) vrForm.getEmploymentStatusList().get(x);
    			    	if (cre.getCodeId().equals(ncre.getStatusId())){
    			    		ncre.setStatusDesc(cre.getDescription());
    			    		break;	
    			    	}
    			    }
    		    }
    	    }				
			vrForm.setCreate1ElementsList(list);
		} else {
			vrForm.setCreate1ElementsList(new ArrayList());
		}
		vrForm.setCreate1Comments("");
		vrForm.setCurrentEmploymentHistoryList(new ArrayList());
		vrForm.setCurrentEmploymentHistoryComments("");          
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
