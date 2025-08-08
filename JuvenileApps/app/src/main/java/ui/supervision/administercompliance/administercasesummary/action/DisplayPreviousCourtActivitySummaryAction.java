//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayPreviousCourtActivitySummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

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
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;

public class DisplayPreviousCourtActivitySummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3A03B0
	 */
	public DisplayPreviousCourtActivitySummaryAction() {

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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewViolationReport(csForm, false);
		addNewMotion(csForm, false);
		addNewOther(csForm, false);
		csForm.setCursorPosition("");
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewViolationReport(csForm, true);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewMotion(csForm, true);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewOther(csForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewViolationReport(CaseSummaryForm csForm, boolean showAdd)
	{
		if (csForm.isShowVRAddFields() == true){
			if ((csForm.getCourtActivityVRDateStr() != null && !csForm.getCourtActivityVRDateStr().equals("")) ||
				(csForm.getCreate1Comments() != null && !csForm.getCreate1Comments().equals("")) ||
				(csForm.getCourtActivityVRSummaryOfCourtActions() != null && !csForm.getCourtActivityVRSummaryOfCourtActions().equals(""))){
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NCPreviousCourtActivityResponseEvent newEvent = new NCPreviousCourtActivityResponseEvent();
					newEvent.setType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION);
					if (csForm.getCourtActivityVRDateStr() != null && !csForm.getCourtActivityVRDateStr().equals("")){
						newEvent.setOccurenceDate(UICaseSummaryHelper.convertDateToTimeStamp(csForm.getCourtActivityVRDateStr(), "00:00", "AM"));
					}
//					newEvent.setActivity("VIOLATION");
					newEvent.setTypeOfCourtActionComment(csForm.getCreate1Comments());
					newEvent.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION);
					newEvent.setSummaryOfCourtAction(csForm.getCourtActivityVRSummaryOfCourtActions());
					newEvent.setPreviousCourtActivityId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate1ElementsList().add(newEvent);
			}	
		}
		csForm.setShowVRAddFields(showAdd);
		csForm.setCursorPosition("VR");
		csForm.setCourtActivityVRDateStr("");
		csForm.setCreate1Comments("");
		csForm.setCourtActivityVRSummaryOfCourtActions("");
	}	

	private void addNewMotion(CaseSummaryForm csForm, boolean showAdd)
	{
		if (csForm.isShowMotionAddFields() == true){
			if ((csForm.getMotionDateStr() != null && !csForm.getMotionDateStr().equals("")) ||
					(csForm.getCreate2Comments() != null && !csForm.getCreate2Comments().equals("")) ||
				(csForm.getMotionSummaryOfCourtActions() != null && !csForm.getMotionSummaryOfCourtActions().equals("")) ||		
				(csForm.getSelectedMotionDispositionId() != null && !csForm.getSelectedMotionDispositionId().equals("")) ){
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NCPreviousCourtActivityResponseEvent newEvent = new NCPreviousCourtActivityResponseEvent();
					newEvent.setType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_MOTION);
					if (csForm.getMotionDateStr() != null && !csForm.getMotionDateStr().equals("")){
						newEvent.setOccurenceDate(UICaseSummaryHelper.convertDateToTimeStamp(csForm.getMotionDateStr(), "00:00", "AM"));
					}
//					newEvent.setActivity("");
					newEvent.setTypeOfCourtActionComment(csForm.getCreate2Comments());
					newEvent.setDisposition("");
//					if (csForm.getSelectedMotionActivityId() != null && !csForm.getSelectedMotionActivityId().equals("")){
//			    	  for (int x=0; x<csForm.getMotionsActivities().size(); x++){
//						  CodeResponseEvent cre1 = (CodeResponseEvent) csForm.getMotionsActivities().get(x);
//						  if (cre1.getCode().equals(csForm.getSelectedMotionActivityId())){
//							  newEvent.setActivity(cre1.getDescription());
//							  break;	
//						  }
//					  }
//					} 
					if (csForm.getSelectedMotionDispositionId() != null && !csForm.getSelectedMotionDispositionId().equals("")){			
						  for (int x=0; x<csForm.getMotionsDispositions().size(); x++){
							  CodeResponseEvent cre2 = (CodeResponseEvent) csForm.getMotionsDispositions().get(x);
							  if (cre2.getCode().equals(csForm.getSelectedMotionDispositionId())){
								  newEvent.setDisposition(cre2.getDescription());
								  break;	
							  }
						  }
					}
					newEvent.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION);
					newEvent.setSummaryOfCourtAction(csForm.getMotionSummaryOfCourtActions());
					newEvent.setPreviousCourtActivityId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate2ElementsList().add(newEvent);	
			}		
		}		
		csForm.setShowMotionAddFields(showAdd);	
		csForm.setCursorPosition("MOT");
		csForm.setMotionDateStr("");
//		csForm.setSelectedMotionActivityId("");
		csForm.setCreate2Comments("");
		csForm.setMotionSummaryOfCourtActions("");
		csForm.setSelectedMotionDispositionId("");
	}	
	
	private void addNewOther(CaseSummaryForm csForm, boolean showAdd)
		{
		if (csForm.isShowAddFields() == true){
			if ((csForm.getOtherDateStr() != null && !csForm.getOtherDateStr().equals("")) ||
					(csForm.getCreate3Comments() != null && !csForm.getCreate3Comments().equals("")) ||
			    (csForm.getOtherSummaryOfCourtActions() != null && !csForm.getOtherSummaryOfCourtActions().equals("")) ){
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					NCPreviousCourtActivityResponseEvent newEvent = new NCPreviousCourtActivityResponseEvent();
					newEvent.setType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER);
					if (csForm.getOtherDateStr() != null && !csForm.getOtherDateStr().equals("")){
						newEvent.setOccurenceDate(UICaseSummaryHelper.convertDateToTimeStamp(csForm.getOtherDateStr(), "00:00", "AM"));
					}
//					newEvent.setActivity("");
//					if (csForm.getSelectedOtherActivityId() != null && !csForm.getSelectedOtherActivityId().equals("")){
//						for (int x=0; x<csForm.getOtherActivities().size(); x++){
//							CodeResponseEvent cre1 = (CodeResponseEvent) csForm.getOtherActivities().get(x);
//							if (cre1.getCode().equals(csForm.getSelectedOtherActivityId())){
//							  newEvent.setActivity(cre1.getDescription());
//							  break;	
//							}
//						}
//					} 
					newEvent.setSummaryOfCourtAction(csForm.getOtherSummaryOfCourtActions());
					newEvent.setSubType(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER);
					newEvent.setPreviousCourtActivityId(csForm.getAddItemIndex());
					newEvent.setTypeOfCourtActionComment(csForm.getCreate3Comments());
					newEvent.setManualAdded(true);
					csForm.getCreate3ElementsList().add(newEvent);
			}		
		}			
		csForm.setShowAddFields(showAdd);
		csForm.setCursorPosition("OTH");
		csForm.setOtherDateStr("");
		csForm.setCreate3Comments("");
		csForm.setOtherSummaryOfCourtActions("");
//		csForm.setSelectedOtherActivityId("");
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
		int len = 0;
		int listSize = 0;
		boolean itemMatched = false;
		List reducedList = new ArrayList();
// 		process Violation Reports
		if (csForm.getSelectedCourtActivityVRIds() != null){
			len = csForm.getSelectedCourtActivityVRIds().length;
			listSize = csForm.getCreate1ElementsList().size();
			for (int x =0; x < listSize; x++){
				NCPreviousCourtActivityResponseEvent lvre = (NCPreviousCourtActivityResponseEvent) csForm.getCreate1ElementsList().get(x);
				itemMatched = false;
				for (int y =0; y < len; y++){
					if (lvre.getPreviousCourtActivityId().equalsIgnoreCase(csForm.getSelectedCourtActivityVRIds()[y])){
						itemMatched = true;
						break;
					}	
				}
				if (!itemMatched){
					reducedList.add(lvre);
				}
			}
			csForm.setCreate1ElementsList(reducedList);
		}	
// 		process Motions
		if (csForm.getSelectedMotionsIds() != null){
			len = csForm.getSelectedMotionsIds().length;
			listSize = csForm.getCreate2ElementsList().size();
			itemMatched = false;
			reducedList = new ArrayList();
			for (int x =0; x < listSize; x++){
				NCPreviousCourtActivityResponseEvent lvre = (NCPreviousCourtActivityResponseEvent) csForm.getCreate2ElementsList().get(x);
				itemMatched = false;
				for (int y =0; y < len; y++){
					if (lvre.getPreviousCourtActivityId().equalsIgnoreCase(csForm.getSelectedMotionsIds()[y])){
						itemMatched = true;
						break;
					}	
				}
				if (!itemMatched){
					reducedList.add(lvre);
				}
			}	
			csForm.setCreate2ElementsList(reducedList);
		}	
//		 process Others	
		if (csForm.getSelectedOthersIds() != null){
			len = csForm.getSelectedOthersIds().length;
			listSize = csForm.getCreate3ElementsList().size();
			itemMatched = false;
			reducedList = new ArrayList();
			for (int x =0; x < listSize; x++){
				NCPreviousCourtActivityResponseEvent lvre = (NCPreviousCourtActivityResponseEvent) csForm.getCreate3ElementsList().get(x);
				itemMatched = false;
				for (int y =0; y < len; y++){
					if (lvre.getPreviousCourtActivityId().equalsIgnoreCase(csForm.getSelectedOthersIds()[y])){
						itemMatched = true;
						break;
					}	
				}
				if (!itemMatched){
					reducedList.add(lvre);
				}
			}	
			csForm.setCreate3ElementsList(reducedList);	
		}
		csForm.setSelectedCourtActivityVRIds(null);
		csForm.setSelectedMotionsIds(null);
		csForm.setSelectedOthersIds(null);
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
		List theVList = new ArrayList();
		List theMList = new ArrayList();
		List theOList = new ArrayList();		
   	    RefreshNCResponseComponentsEvent event = (RefreshNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.REFRESHNCRESPONSECOMPONENTS);
        event.setRequestType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
    	event.setNcResponseId(csForm.getViolationReportId());
    	event.setCaseId(csForm.getCaseNum());
		event.setCdi(csForm.getCdi());    
        
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
        csForm.setCreate1ElementsList(theVList);
        csForm.setCreate2ElementsList(theMList);
        csForm.setCreate3ElementsList(theOList);
        csForm.setCreate1Comments("");
        csForm.setCreate2Comments("");
        csForm.setCreate3Comments("");
        csForm.setCurrentCourtActivityVRList(new ArrayList());
        csForm.setCurrentMotionsList(new ArrayList());
        csForm.setCurrentOthersList(new ArrayList());
        csForm.setCurrentCourtActivityVRComments("");
        csForm.setCurrentMotionsComments("");
        csForm.setCurrentOthersComments("");
		csForm.setShowVRAddFields(false);
		csForm.setShowMotionAddFields(false);	
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