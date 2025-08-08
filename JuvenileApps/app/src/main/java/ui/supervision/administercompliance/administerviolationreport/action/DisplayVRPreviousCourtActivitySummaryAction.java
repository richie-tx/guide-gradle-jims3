//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRPreviousCourtActivitySummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NCPreviousCourtActivityResponseEvent;
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

public class DisplayVRPreviousCourtActivitySummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3A03B0
	 */
	public DisplayVRPreviousCourtActivitySummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addViolationReport", "addViolationReport");
		keyMap.put("button.addMotion", "addMotion");
		keyMap.put("button.addOther", "addOther");		
		keyMap.put("button.removeSelected", "remove");
		keyMap.put("button.refreshCourtActivity", "refresh");	
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
		addNewViolationReport(vrForm, false);
		addNewMotion(vrForm, false);
		addNewOther(vrForm, false);
		vrForm.setCursorPosition("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addViolationReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewViolationReport(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addMotion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewMotion(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addOther(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewOther(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewViolationReport(ViolationReportsForm vrForm, boolean showAdd)
	{
		if (vrForm.isShowVRAddFields() == true){
			if ((vrForm.getCourtActivityVRDateStr() != null && !vrForm.getCourtActivityVRDateStr().equals("")) ||
				(vrForm.getCreate1Comments() != null && !vrForm.getCreate1Comments().equals("")) ||
				(vrForm.getCourtActivityVRSummaryOfCourtActions() != null && !vrForm.getCourtActivityVRSummaryOfCourtActions().equals(""))){
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NCPreviousCourtActivityResponseEvent newEvent = new NCPreviousCourtActivityResponseEvent();
					newEvent.setType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION);
					if (vrForm.getCourtActivityVRDateStr() != null && !vrForm.getCourtActivityVRDateStr().equals("")){
						newEvent.setOccurenceDate(UIViolationReportHelper.convertDateToTimeStamp(vrForm.getCourtActivityVRDateStr(), "00:00", "AM"));
					}
			//		newEvent.setActivity("VIOLATION");
					newEvent.setTypeOfCourtActionComment(vrForm.getCreate1Comments());
					newEvent.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION);
					newEvent.setSummaryOfCourtAction(vrForm.getCourtActivityVRSummaryOfCourtActions());
					newEvent.setPreviousCourtActivityId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate1ElementsList().add(newEvent);
			}	
		}
		vrForm.setShowVRAddFields(showAdd);
		vrForm.setCursorPosition("VR");
		vrForm.setCourtActivityVRDateStr("");
		vrForm.setCreate1Comments("");
		vrForm.setCourtActivityVRSummaryOfCourtActions("");
	}	

	private void addNewMotion(ViolationReportsForm vrForm, boolean showAdd )
	{
		if (vrForm.isShowMotionAddFields() == true){
			if ((vrForm.getMotionDateStr() != null && !vrForm.getMotionDateStr().equals("")) ||
				(vrForm.getCreate2Comments() != null && !vrForm.getCreate2Comments().equals("")) ||
				(vrForm.getMotionSummaryOfCourtActions() != null && !vrForm.getMotionSummaryOfCourtActions().equals("")) ||		
				(vrForm.getSelectedMotionDispositionId() != null && !vrForm.getSelectedMotionDispositionId().equals("")) ){
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NCPreviousCourtActivityResponseEvent newEvent = new NCPreviousCourtActivityResponseEvent();
					newEvent.setType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_MOTION);
					if (vrForm.getMotionDateStr() != null && !vrForm.getMotionDateStr().equals("")){
						newEvent.setOccurenceDate(UIViolationReportHelper.convertDateToTimeStamp(vrForm.getMotionDateStr(), "00:00", "AM"));
					}
		//			newEvent.setActivity("");
					newEvent.setTypeOfCourtActionComment(vrForm.getCreate2Comments());
					newEvent.setDisposition("");
		//			if (vrForm.getSelectedMotionActivityId() != null && !vrForm.getSelectedMotionActivityId().equals("")){
		//	    	  for (int x=0; x<vrForm.getMotionsActivities().size(); x++){
		//				  CodeResponseEvent cre1 = (CodeResponseEvent) vrForm.getMotionsActivities().get(x);
		//				  if (cre1.getCode().equals(vrForm.getSelectedMotionActivityId())){
		//					  newEvent.setActivity(cre1.getDescription());
		//					  break;	
		//				  }
		//			  }
		//			} 
					if (vrForm.getSelectedMotionDispositionId() != null && !vrForm.getSelectedMotionDispositionId().equals("")){			
						  for (int x=0; x<vrForm.getMotionsDispositions().size(); x++){
							  CodeResponseEvent cre2 = (CodeResponseEvent) vrForm.getMotionsDispositions().get(x);
							  if (cre2.getCode().equals(vrForm.getSelectedMotionDispositionId())){
								  newEvent.setDisposition(cre2.getDescription());
								  break;	
							  }
						  }
					}
					newEvent.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION);
					newEvent.setSummaryOfCourtAction(vrForm.getMotionSummaryOfCourtActions());
					newEvent.setPreviousCourtActivityId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate2ElementsList().add(newEvent);	
			}		
		}		
		vrForm.setShowMotionAddFields(showAdd);	
		vrForm.setCursorPosition("MOT");
		vrForm.setMotionDateStr("");
//		vrForm.setSelectedMotionActivityId("");
		vrForm.setCreate2Comments("");
		vrForm.setMotionSummaryOfCourtActions("");
		vrForm.setSelectedMotionDispositionId("");
	}	
	
	private void addNewOther(ViolationReportsForm vrForm, boolean showAdd)
		{
		if (vrForm.isShowAddFields() == true){
			if ((vrForm.getOtherDateStr() != null && !vrForm.getOtherDateStr().equals("")) ||
			    (vrForm.getCreate3Comments() != null && !vrForm.getCreate3Comments().equals("")) ||
			    (vrForm.getOtherSummaryOfCourtActions() != null && !vrForm.getOtherSummaryOfCourtActions().equals("")) ){
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					NCPreviousCourtActivityResponseEvent newEvent = new NCPreviousCourtActivityResponseEvent();
					newEvent.setType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER);
					if (vrForm.getOtherDateStr() != null && !vrForm.getOtherDateStr().equals("")){
						newEvent.setOccurenceDate(UIViolationReportHelper.convertDateToTimeStamp(vrForm.getOtherDateStr(), "00:00", "AM"));
					}
//					newEvent.setActivity("");
//					if (vrForm.getSelectedOtherActivityId() != null && !vrForm.getSelectedOtherActivityId().equals("")){
//						for (int x=0; x<vrForm.getOtherActivities().size(); x++){
//							CodeResponseEvent cre1 = (CodeResponseEvent) vrForm.getOtherActivities().get(x);
//							if (cre1.getCode().equals(vrForm.getSelectedOtherActivityId())){
//							  newEvent.setActivity(cre1.getDescription());
//							  break;	
//							}
//						}
//					} 
					newEvent.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER);
					newEvent.setPreviousCourtActivityId(vrForm.getAddItemIndex());
					newEvent.setTypeOfCourtActionComment(vrForm.getCreate3Comments());
					newEvent.setSummaryOfCourtAction(vrForm.getOtherSummaryOfCourtActions());
					newEvent.setManualAdded(true);
					vrForm.getCreate3ElementsList().add(newEvent);
			}		
		}			
		vrForm.setShowAddFields(showAdd);
		vrForm.setCursorPosition("OTH");
		vrForm.setOtherDateStr("");
		vrForm.setCreate3Comments("");
		vrForm.setOtherSummaryOfCourtActions("");
//		vrForm.setSelectedOtherActivityId("");
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
		int len = 0;
		int listSize = 0;
		boolean itemMatched = false;
		List reducedList = new ArrayList();
// 		process Violation Reports
		if (vrForm.getSelectedCourtActivityVRIds() != null){
			len = vrForm.getSelectedCourtActivityVRIds().length;
			listSize = vrForm.getCreate1ElementsList().size();
			for (int x =0; x < listSize; x++){
				NCPreviousCourtActivityResponseEvent lvre = (NCPreviousCourtActivityResponseEvent) vrForm.getCreate1ElementsList().get(x);
				itemMatched = false;
				for (int y =0; y < len; y++){
					if (lvre.getPreviousCourtActivityId().equalsIgnoreCase(vrForm.getSelectedCourtActivityVRIds()[y])){
						itemMatched = true;
						break;
					}	
				}
				if (!itemMatched){
					reducedList.add(lvre);
				}
			}
			vrForm.setCreate1ElementsList(reducedList);
		}	
// 		process Motions
		if (vrForm.getSelectedMotionsIds() != null){
			len = vrForm.getSelectedMotionsIds().length;
			listSize = vrForm.getCreate2ElementsList().size();
			itemMatched = false;
			reducedList = new ArrayList();
			for (int x =0; x < listSize; x++){
				NCPreviousCourtActivityResponseEvent lvre = (NCPreviousCourtActivityResponseEvent) vrForm.getCreate2ElementsList().get(x);
				itemMatched = false;
				for (int y =0; y < len; y++){
					if (lvre.getPreviousCourtActivityId().equalsIgnoreCase(vrForm.getSelectedMotionsIds()[y])){
						itemMatched = true;
						break;
					}	
				}
				if (!itemMatched){
					reducedList.add(lvre);
				}
			}	
			vrForm.setCreate2ElementsList(reducedList);
		}	
//		 process Others	
		if (vrForm.getSelectedOthersIds() != null){
			len = vrForm.getSelectedOthersIds().length;
			listSize = vrForm.getCreate3ElementsList().size();
			itemMatched = false;
			reducedList = new ArrayList();
			for (int x =0; x < listSize; x++){
				NCPreviousCourtActivityResponseEvent lvre = (NCPreviousCourtActivityResponseEvent) vrForm.getCreate3ElementsList().get(x);
				itemMatched = false;
				for (int y =0; y < len; y++){
					if (lvre.getPreviousCourtActivityId().equalsIgnoreCase(vrForm.getSelectedOthersIds()[y])){
						itemMatched = true;
						break;
					}	
				}
				if (!itemMatched){
					reducedList.add(lvre);
				}
			}	
			vrForm.setCreate3ElementsList(reducedList);	
		}
		vrForm.setSelectedCourtActivityVRIds(null);
		vrForm.setSelectedMotionsIds(null);
		vrForm.setSelectedOthersIds(null);
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
		List theVList = new ArrayList();
		List theMList = new ArrayList();
		List theOList = new ArrayList();		
   	    RefreshNCResponseComponentsEvent event = (RefreshNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.REFRESHNCRESPONSECOMPONENTS);
        event.setRequestType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
    	event.setNcResponseId(vrForm.getViolationReportId());
    	event.setCaseId(vrForm.getCaseNum());
		event.setCdi(vrForm.getCdi());    
        
        List list = MessageUtil.postRequestListFilter(event, NCPreviousCourtActivityResponseEvent.class);
        if (list != null){
        	Iterator rptIter = list.iterator();
        	while(rptIter.hasNext()){
        		NCPreviousCourtActivityResponseEvent prare = (NCPreviousCourtActivityResponseEvent) rptIter.next();
        		if (prare.getSubType().equalsIgnoreCase(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION)){ 
        			theVList.add(prare);
        		}
        		if (prare.getSubType().equalsIgnoreCase(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION)){
        			theMList.add(prare);
        		}
        		if (prare.getSubType().equalsIgnoreCase(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER)){
        			theOList.add(prare);
        		}
        	}
        }
        vrForm.setCreate1ElementsList(theVList);
        vrForm.setCreate2ElementsList(theMList);
        vrForm.setCreate3ElementsList(theOList);
        vrForm.setCreate1Comments("");
        vrForm.setCreate2Comments("");
        vrForm.setCreate3Comments("");
        vrForm.setCurrentCourtActivityVRList(new ArrayList());
        vrForm.setCurrentMotionsList(new ArrayList());
        vrForm.setCurrentOthersList(new ArrayList());
        vrForm.setCurrentCourtActivityVRComments("");
        vrForm.setCurrentMotionsComments("");
        vrForm.setCurrentOthersComments("");
		vrForm.setShowVRAddFields(false);
		vrForm.setShowMotionAddFields(false);	
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