//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\SubmitPreviousCourtActivityCreateUpdateAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.UpdateNCCourtActivityEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCPreviousCourtActivityResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;

public class SubmitPreviousCourtActivityCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D4200A3
	 */
	public SubmitPreviousCourtActivityCreateUpdateAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");		
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setConfirmationMessage("");
		String forwardStr = UIConstants.FINISH;
       	UpdateNCResponseEvent event = (UpdateNCResponseEvent) EventFactory.getInstance(ComplianceControllerServiceNames.UPDATENCRESPONSE);
    	event.setRequestType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
    	event.setNcResponseId(csForm.getViolationReportId());
    	if (csForm.getViolationReportId() == null || csForm.getViolationReportId().equals("")){
    		event.setUserID(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
			event.setStatusId("DR");
			event.setOrderId(Integer.parseInt(csForm.getOrderId()));
    	}
		event.setReportType(ViolationReportConstants.REPORTTYPE_CASESUMMARY);
		event.setCaseId(new StringBuffer().append(csForm.getCdi()).append(csForm.getCaseNum()).toString());

		// violatoin reports    			
    	UpdateNCCourtActivityEvent ncReqEvent = null;
//    	UpdateNCCommentEvent commentx = null;
		int listSize = csForm.getCreate1ElementsList().size();
		for (int g = 0; g < listSize; g++){
			NCPreviousCourtActivityResponseEvent pca1 = (NCPreviousCourtActivityResponseEvent) csForm.getCreate1ElementsList().get(g);
			ncReqEvent = new UpdateNCCourtActivityEvent();
//			 REMOVE 'M' VALUES IN LAWVIOLATIONID FIELD BEFORE PRESISTING.  THESE VALUES ONLY NEEDED
//			 TO UNIQUELY IDENTIFY MANUALLY ADDED VALUES FOR REMOVAL UPDATE PAGE				
			if (pca1.getPreviousCourtActivityId().indexOf('M') > -1 || pca1.getPreviousCourtActivityId().indexOf('L') > -1){
				ncReqEvent.setCourtActivityId("");
			}else{
				ncReqEvent.setCourtActivityId(pca1.getPreviousCourtActivityId());
			}
			if(pca1.getOccurenceDate() != null){
				ncReqEvent.setOccurenceDate(new Timestamp(pca1.getOccurenceDate().getTime()));
			}
//	    	ncReqEvent.setActivity(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION);
	    	ncReqEvent.setTypeOfCourtActionComment(pca1.getTypeOfCourtActionComment());
	    	ncReqEvent.setSubType(pca1.getSubType());
	    	ncReqEvent.setSummaryOfCourtActivity(pca1.getSummaryOfCourtAction());
	    	ncReqEvent.setType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
	    	ncReqEvent.setManualAdded(pca1.isManualAdded());
	    	event.addRequest(ncReqEvent);
		}

//		 motions     	
		listSize = csForm.getCreate2ElementsList().size();
		for (int g = 0; g < listSize; g++){
			NCPreviousCourtActivityResponseEvent pca2 = (NCPreviousCourtActivityResponseEvent) csForm.getCreate2ElementsList().get(g);
			ncReqEvent = new UpdateNCCourtActivityEvent();
			if (pca2.getPreviousCourtActivityId().indexOf('M') > -1 || pca2.getPreviousCourtActivityId().indexOf('L') > -1){
				ncReqEvent.setCourtActivityId("");
			}else{
				ncReqEvent.setCourtActivityId(pca2.getPreviousCourtActivityId());
			}
			if(pca2.getOccurenceDate() != null){
				ncReqEvent.setOccurenceDate(new Timestamp(pca2.getOccurenceDate().getTime()));
			}
/**			for (int x=0; x<csForm.getMotionsActivities().size(); x++){
				CodeResponseEvent cre = (CodeResponseEvent) csForm.getMotionsActivities().get(x);
				if (cre.getDescription().equals(pca2.getActivity())){  
					pca2.setActivity(cre.getCode());
					break;	
				}
		    }
	    	ncReqEvent.setActivity(pca2.getActivity()); */
			ncReqEvent.setTypeOfCourtActionComment(pca2.getTypeOfCourtActionComment());
	    	ncReqEvent.setSubType(pca2.getSubType());
	    	ncReqEvent.setSummaryOfCourtActivity(pca2.getSummaryOfCourtAction());
			for (int x=0; x<csForm.getMotionsDispositions().size(); x++){
				CodeResponseEvent cre = (CodeResponseEvent) csForm.getMotionsDispositions().get(x);
				if (cre.getDescription().equals(pca2.getDisposition())){  
					pca2.setDisposition(cre.getCode());
					break;	
				}
		    }	    	
	    	ncReqEvent.setDisposition(pca2.getDisposition());
	    	ncReqEvent.setType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
	    	ncReqEvent.setManualAdded(pca2.isManualAdded());
	    	event.addRequest(ncReqEvent);
		}
    	
// others
		listSize = csForm.getCreate3ElementsList().size();
		for (int g = 0; g < listSize; g++){
			NCPreviousCourtActivityResponseEvent pca3 = (NCPreviousCourtActivityResponseEvent) csForm.getCreate3ElementsList().get(g);
			ncReqEvent = new UpdateNCCourtActivityEvent();
			if (pca3.getPreviousCourtActivityId().indexOf('M') > -1 || pca3.getPreviousCourtActivityId().indexOf('L') > -1){
				ncReqEvent.setCourtActivityId("");
			}else{
				ncReqEvent.setCourtActivityId(pca3.getPreviousCourtActivityId());
			}
			if(pca3.getOccurenceDate() != null){
				ncReqEvent.setOccurenceDate(new Timestamp(pca3.getOccurenceDate().getTime()));
			}
/**			for (int x=0; x<csForm.getOtherActivities().size(); x++){
				CodeResponseEvent cre = (CodeResponseEvent) csForm.getOtherActivities().get(x);
				if (cre.getDescription().equals(pca3.getActivity())){  
					pca3.setActivity(cre.getCode());
					break;	
				}
		    } 
			ncReqEvent.setActivity(pca3.getActivity()); */
			ncReqEvent.setTypeOfCourtActionComment(pca3.getTypeOfCourtActionComment());
	    	ncReqEvent.setSubType(pca3.getSubType());
	    	ncReqEvent.setSummaryOfCourtActivity(pca3.getSummaryOfCourtAction());
	    	ncReqEvent.setType(ViolationReportConstants.REQUEST_PREVIOUS_COURT_ACTIVITY);
	    	ncReqEvent.setManualAdded(pca3.isManualAdded());
	    	event.addRequest(ncReqEvent);
		}	

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException == null) {
			csForm.setConfirmationMessage("Previous Court Activity successfully processed.");
			List theVList = new ArrayList();
			List theMList = new ArrayList();
			List theOList = new ArrayList();
			Collection reports = MessageUtil.compositeToList(response, NCPreviousCourtActivityResponseEvent.class);
			if (reports != null){
	        	Iterator rptIter = reports.iterator();
	        	while(rptIter.hasNext()){
	        		NCPreviousCourtActivityResponseEvent prare = (NCPreviousCourtActivityResponseEvent) rptIter.next();
	        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_VIOLATION.equalsIgnoreCase(prare.getSubType())){ 
	        			theVList.add(prare);
	        		}
	        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_MOTION.equalsIgnoreCase(prare.getSubType())){
	        			theMList.add(prare);
	        		}
	        		if (ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_TYPE_OTHER.equalsIgnoreCase(prare.getSubType())){
	        			theOList.add(prare);
	        		}
	        	}
	            for (int m = 0; m < theMList.size(); m++){
	            	NCPreviousCourtActivityResponseEvent maEvent = (NCPreviousCourtActivityResponseEvent) theMList.get(m);
/**	            	for (int x=0; x<csForm.getMotionsActivities().size(); x++){
	            		CodeResponseEvent cre1 = (CodeResponseEvent) csForm.getMotionsActivities().get(x);
	            		if (cre1.getCode().equals(maEvent.getActivity())){
	            			maEvent.setActivity(cre1.getDescription());
	            			break;	
	            		}
	            	} */
	            	for (int x=0; x<csForm.getMotionsDispositions().size(); x++){
	            		CodeResponseEvent cre2 = (CodeResponseEvent) csForm.getMotionsDispositions().get(x);
	            		if (cre2.getCode().equals(maEvent.getDisposition())){
	            			maEvent.setDisposition(cre2.getDescription());
	            			break;	
	            		}
	            	}
	            }

/**	            for (int t = 0; t < theOList.size(); t++){
	            	NCPreviousCourtActivityResponseEvent othEvent = (NCPreviousCourtActivityResponseEvent) theOList.get(t);
	            	for (int x=0; x<csForm.getOtherActivities().size(); x++){
	            		CodeResponseEvent cre3 = (CodeResponseEvent) csForm.getOtherActivities().get(x);
	            		if (cre3.getCode().equals(othEvent.getActivity())){
	            			othEvent.setActivity(cre3.getDescription());
	            			break;
	            		}	  
	            	}
	            }   */	        	
	        }

			// remove sorts once sortResult tag is fixed			
			csForm.setCurrentCourtActivityVRList(UICaseSummaryHelper.sortPreviousCourtActivityListDescending(theVList));
			csForm.setCurrentMotionsList(UICaseSummaryHelper.sortPreviousCourtActivityListDescending(theMList));
			csForm.setCurrentOthersList(UICaseSummaryHelper.sortPreviousCourtActivityListDescending(theOList));
//	        csForm.setCurrentCourtActivityVRComments(csForm.getCreate1Comments());
//	        csForm.setCurrentMotionsComments(csForm.getCreate2Comments());
//	        csForm.setCurrentOthersComments(csForm.getCreate3Comments());

//	this should only be true on initial create of case summary report	
			if (csForm.getStatusId() == null || csForm.getStatusId().equals("")){
				csForm.setStatusDesc(ViolationReportConstants.DRAFT); 
				csForm.setStatusId(ViolationReportConstants.STATUS_DRAFT);
				csForm.setStatusChangedDate(DateUtil.getCurrentDate());
				csForm.setCreatedByName(SecurityUIHelper.getUserName(SecurityUIHelper.getJIMSLogonId()).toString());
				csForm.setCreateDate(DateUtil.getCurrentDate());	
				
				NCResponseResponseEvent resp = (NCResponseResponseEvent) MessageUtil
				.filterComposite(response, NCResponseResponseEvent.class);
				if(resp != null){
					csForm.setViolationReportId(resp.getNcResponseId());
				}
			}	
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured saving Previous Court Activities information."));
			saveErrors(aRequest, errors);
			csForm.setCreate1ElementsList(new ArrayList());
			csForm.setCreate2ElementsList(new ArrayList());
			csForm.setCreate3ElementsList(new ArrayList());
//			csForm.setCreate1Comments("");
//			csForm.setCreate2Comments("");
//			csForm.setCreate3Comments("");
		}
			
		if (csForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_FINISH;
		}
		return aMapping.findForward(forwardStr);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.CANCEL;
		if (csForm.getTaskId() != null && !csForm.getTaskId().equals("")){
			forwardStr = UIConstants.TASKFLOW_CANCEL;
		}
		return aMapping.findForward(forwardStr);
	}
}
