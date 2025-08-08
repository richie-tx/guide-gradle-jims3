/*
 * Created on Mar 13, 2007
 *
 */
package ui.supervision.adminstaff.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.GetStaffPositionHistoryEvent;
import messaging.cscdstaffposition.RetireStaffPositionEvent;
import messaging.cscdstaffposition.reply.ActiveCaseloadEvent;
import messaging.cscdstaffposition.reply.ActiveSubordinatesEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.adminstaff.Position;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffForm;
import ui.supervision.adminstaff.form.AdminStaffReportForm;
import ui.supervision.adminstaff.form.AdminStaffSearchForm;

/**
 * @author jjose
 *
  */
public class HandlePositionSelectionAction extends JIMSBaseAction {

	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.createNewPosition", "create");
		keyMap.put("button.assignPosition", "assign");
		keyMap.put("button.positionHistory", "positionHistory");
		keyMap.put("button.programUnitHistory", "puHistory");
		keyMap.put("button.reassignPosition", "reassign");
		keyMap.put("button.retiredPositions", "retiredPositions");
		keyMap.put("button.staffHistory", "staffHistory");
		keyMap.put("button.updatePosition", "updatePosition");
		keyMap.put("button.retirePosition", "dateRetirement");
		keyMap.put("button.update", "update");
		keyMap.put("button.vacatePosition", "vacate");
		keyMap.put("button.viewPosition", "view");
		keyMap.put("button.link", "positionHistory");
		keyMap.put("button.popup", "positionHistoryPopup");
		keyMap.put("button.next", "next");
		keyMap.put("button.finish", "retire");
	}
	
	public ActionForward retiredPositions(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		
		AdminStaffReportForm myReportForm=(AdminStaffReportForm)getSessionForm(aMapping,aRequest,UIConstants.ADMIN_STAFF_REPORT_FORM,true);
		myReportForm.clearAll();
		myReportForm.setReportType(UIAdminStaffHelper.REPORT_TYPE_RETIRE);
		myReportForm.resetDatesForSearch();
		GetStaffPositionHistoryEvent staffHistoryEvent = (GetStaffPositionHistoryEvent) EventFactory
		.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONHISTORY);
		UIAdminStaffHelper.populateGetStaffPosHistoryEvent(myReportForm,staffHistoryEvent);
		CompositeResponse myResp=postRequestEvent(staffHistoryEvent);
		ArrayList list=UIAdminStaffHelper.processReportResults(myResp);
		if(list==null || list.size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
		}
		myReportForm.setFilteredList(list);
		myReportForm.setPositionList(list);
		return aMapping.findForward(UIAdminStaffHelper.REPORT);
	}

	public ActionForward dateRetirement(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		myForm.getPosition().setRetirementDateAsStr("");
		return aMapping.findForward("dateRetirement");
	}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward("retireSummary");
	}

	public ActionForward retire(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		String mappingForward="retireSuccess";
		RetireStaffPositionEvent staffEvent = (RetireStaffPositionEvent) EventFactory
		.getInstance(CSCDStaffPositionControllerServiceNames.RETIRESTAFFPOSITION);
		staffEvent.setSupervisionStaffId(myForm.getPosition().getPositionId());
//		if(myForm.getPosition().getUser()!=null && myForm.getPosition().getUser().getUserId()!=null){
//			staffEvent.setSupervisionStaffId(myForm.getPosition().getUser().getUserId());
//		}
		staffEvent.setRetirementDate(
				DateUtil.stringToDate(
						myForm.getPosition().getRetirementDateAsStr(), 
							DateUtil.DATE_FMT_1)
				);
		
		CompositeResponse myResp=postRequestEvent(staffEvent);
		Object errorResponse=MessageUtil.compositeToCollection(myResp, ErrorResponseEvent.class);
		if(errorResponse!=null){
	    	errorResponse = MessageUtil.filterComposite(myResp, ActiveCaseloadEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.staffActiveCaseload");
	        	sendToErrorPage(aRequest,"error.positionNotRetirable");
	        	mappingForward=("retireFailure");
	        }
	    	errorResponse = MessageUtil.filterComposite(myResp, ActiveSubordinatesEvent.class);
	    	if(errorResponse!=null){
	        	sendToErrorPage(aRequest,"error.positionSubordinatesExist");
	        	sendToErrorPage(aRequest,"error.positionNotRetirable");
	        	mappingForward=("retireFailure");
	        }
		}
		else{
			myForm.setSecondaryAction(UIConstants.CONFIRM);
		}
		myForm.setAction(UIAdminStaffHelper.RETIRE);
		return aMapping.findForward(mappingForward);
	}
	
	public ActionForward create(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		myForm.clearAll();
		myForm.setAction(UIConstants.CREATE);
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.CREATE);
	}
	
	public ActionForward updatePosition(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String mappingForward=UIConstants.UPDATE;
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		Position myPos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(), mySearchForm.getFoundPositions());
		
		if(myPos==null || myPos.getPositionId()==null || myPos.getPositionId().equals("")){
			if(myForm.getSelectedValue()==null || myForm.getSelectedValue().equals("")){
				sendToErrorPage(aRequest,"error.generic","The position could not be found to update.");
				mappingForward=UIConstants.FAILURE;
			}else{
				// TODO: do search for individual position
				mappingForward=UIConstants.FAILURE;
			}
		}
		else{
			myForm.clearAll();
			myForm.setPosition(myPos);
			myForm.setAction(UIConstants.UPDATE);
			myForm.setSecondaryAction(UIConstants.SUMMARY);
		}
		return aMapping.findForward(mappingForward);
	}
	
	public ActionForward update(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		myForm.setAction(UIConstants.UPDATE);
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.UPDATE);
	}
	
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		if(myForm.getAction().equals(UIConstants.VIEW)){
			myForm.setAction("");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward view(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String mappingForward=UIConstants.VIEW;
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		Position myPos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(), mySearchForm.getFoundPositions());
		
		if(myPos==null || myPos.getPositionId()==null || myPos.getPositionId().equals("")){
			if(myForm.getSelectedValue()==null || myForm.getSelectedValue().equals("")){
				sendToErrorPage(aRequest,"error.generic","The position could not be found to view.");
				mappingForward=UIConstants.FAILURE;
			}else{
				// TODO: do search for individual position
				mappingForward=UIConstants.FAILURE;
			}
		}
		else{
			myForm.clearAll();
			myForm.setPosition(myPos);
			myForm.setAction(UIConstants.VIEW);
		}
		myForm.setSecondaryAction("");
		return aMapping.findForward(mappingForward);
	}
	
	
	
	public ActionForward vacate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
			
		String mappingForward=UIAdminStaffHelper.VACATE;
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		Position myPos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(), mySearchForm.getFoundPositions());
		
		if(myPos==null || myPos.getPositionId()==null || myPos.getPositionId().equals("")){
			if(myForm.getSelectedValue()==null || myForm.getSelectedValue().equals("")){
				sendToErrorPage(aRequest,"error.generic","The position could not be found to vacate.");
				mappingForward=UIConstants.FAILURE;
			}else{
				// TODO: do search for individual position
				mappingForward=UIConstants.FAILURE;
			}
		}
		else{
			myForm.clearAll();
			myForm.setPosition(myPos);
			myForm.setAction(UIAdminStaffHelper.VACATE);
			myForm.setSecondaryAction(UIConstants.SUMMARY);
		}
		return aMapping.findForward(mappingForward);
	}
	
	
	public ActionForward assign(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String mappingForward=UIAdminStaffHelper.ASSIGN;
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		Position myPos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(), mySearchForm.getFoundPositions());
		
		if(myPos==null || myPos.getPositionId()==null || myPos.getPositionId().equals("")){
			if(myForm.getSelectedValue()==null || myForm.getSelectedValue().equals("")){
				sendToErrorPage(aRequest,"error.generic","The position could not be found to assign.");
				mappingForward=UIConstants.FAILURE;
			}else{
				// TODO: do search for individual position
				mappingForward=UIConstants.FAILURE;
			}
		}
		else{
			myForm.clearAll();
			myForm.setPosition(myPos);
			mySearchForm.setUserSearchUserId("");
			mySearchForm.setUserSearchName(new Name());
			mySearchForm.setUserSearchCjad("");
			mySearchForm.setUserSearchOffTypeId("");
			mySearchForm.setUserSearchWorkgroupName("");
			myForm.setAction(UIAdminStaffHelper.ASSIGN);
			myForm.setSecondaryAction(UIConstants.SUMMARY);
		}
		return aMapping.findForward(mappingForward);
	}
	
	public ActionForward reassign(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String mappingForward=UIAdminStaffHelper.ASSIGN;
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		Position myPos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(), mySearchForm.getFoundPositions());
		
		if(myPos==null || myPos.getPositionId()==null || myPos.getPositionId().equals("")){
			if(myForm.getSelectedValue()==null || myForm.getSelectedValue().equals("")){
				sendToErrorPage(aRequest,"error.generic","The position could not be found to reassign.");
				mappingForward=UIConstants.FAILURE;
			}else{
				// TODO: do search for individual position
				mappingForward=UIConstants.FAILURE;
			}
		}
		else{
			myForm.clearAll();
			myForm.setPosition(myPos);
			myForm.setAction(UIAdminStaffHelper.REASSIGN);
			myForm.setSecondaryAction(UIConstants.SUMMARY);
			
			mySearchForm.clearUserSearch();
		}
		return aMapping.findForward(mappingForward);
	}
	
	public ActionForward positionHistory(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffReportForm myReportForm=(AdminStaffReportForm)getSessionForm(aMapping,aRequest,UIConstants.ADMIN_STAFF_REPORT_FORM,true);
		myReportForm.clearAll();
		myReportForm.setReportType(UIAdminStaffHelper.REPORT_TYPE_POSITION);
		myReportForm.setPositionId(myForm.getSelectedValue());
		myReportForm.resetDatesForSearch();
		GetStaffPositionHistoryEvent staffHistoryEvent = (GetStaffPositionHistoryEvent) EventFactory
		.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONHISTORY);
		UIAdminStaffHelper.populateGetStaffPosHistoryEvent(myReportForm,staffHistoryEvent);
		CompositeResponse myResp=postRequestEvent(staffHistoryEvent);
		ArrayList list=UIAdminStaffHelper.processReportResults(myResp);
		if(list==null || list.size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
		}
		myReportForm.setFilteredList(list);
		myReportForm.setPositionList(list);
		
		return aMapping.findForward(UIAdminStaffHelper.REPORT);
	}

	public ActionForward positionHistoryPopup(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		positionHistory(aMapping, aForm, aRequest, aResponse);
		return aMapping.findForward("reportPopup");		
	}

	public ActionForward puHistory(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffReportForm myReportForm=(AdminStaffReportForm)getSessionForm(aMapping,aRequest,UIConstants.ADMIN_STAFF_REPORT_FORM,true);
		myReportForm.clearAll();
		myReportForm.setReportType(UIAdminStaffHelper.REPORT_TYPE_PU);
		myReportForm.resetDatesForSearch();
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		Position myPos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(), mySearchForm.getFoundPositions());
		ArrayList list=null;
		if(myPos!=null){
			myReportForm.setProgramUnitId(myPos.getProgramUnitId());
		//else{
			GetStaffPositionHistoryEvent staffHistoryEvent = (GetStaffPositionHistoryEvent) EventFactory
			.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONHISTORY);
			UIAdminStaffHelper.populateGetStaffPosHistoryEvent(myReportForm,staffHistoryEvent);
			CompositeResponse myResp=postRequestEvent(staffHistoryEvent);
			list=UIAdminStaffHelper.processReportResults(myResp);
		}
		if(list==null || list.size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
		}
		myReportForm.setFilteredList(list);
		myReportForm.setPositionList(list);
			
		return aMapping.findForward(UIAdminStaffHelper.REPORT);
	}
	
	public ActionForward staffHistory(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		AdminStaffReportForm myReportForm=(AdminStaffReportForm)getSessionForm(aMapping,aRequest,UIConstants.ADMIN_STAFF_REPORT_FORM,true);
		myReportForm.clearAll();
		myReportForm.setReportType(UIAdminStaffHelper.REPORT_TYPE_STAFF);
		myReportForm.resetDatesForSearch();
		AdminStaffSearchForm mySearchForm=(AdminStaffSearchForm)getSessionForm(aMapping, aRequest,UIConstants.ADMIN_STAFF_SEARCH_FORM,true);
		Position myPos=UIAdminStaffHelper.findPositionInList(myForm.getSelectedValue(), mySearchForm.getFoundPositions());
		ArrayList list=null;
		if(myPos!=null && myPos.getUser()!=null && myPos.getUser().getUserId()!=null)
		{
			myReportForm.setStaffId(myPos.getUser().getUserId());
			GetStaffPositionHistoryEvent staffHistoryEvent = (GetStaffPositionHistoryEvent) EventFactory
			.getInstance(CSCDStaffPositionControllerServiceNames.GETSTAFFPOSITIONHISTORY);
			UIAdminStaffHelper.populateGetStaffPosHistoryEvent(myReportForm,staffHistoryEvent);
			CompositeResponse myResp=postRequestEvent(staffHistoryEvent);
			list=UIAdminStaffHelper.processReportResults(myResp);
		}
		if(list==null || list.size()<1){
			sendToErrorPage(aRequest,"error.no.search.results.found");
		}
		myReportForm.setFilteredList(list);
		myReportForm.setPositionList(list);
		return aMapping.findForward(UIAdminStaffHelper.REPORT);
	}
	
}