/*
 * Created on Mar 15, 2007
 *
 */
package ui.supervision.adminstaff.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.AssignStaffPositionEvent;
import messaging.cscdstaffposition.RetireStaffPositionEvent;
import messaging.cscdstaffposition.UpdateStaffPositionEvent;
import messaging.cscdstaffposition.VacateStaffPositionEvent;
import messaging.cscdstaffposition.reply.ActiveCaseloadEvent;
import messaging.cscdstaffposition.reply.ActiveSubordinatesEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.cscdstaffposition.reply.NoLegacyProgramUnitOnOrganizationEvent;
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
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.supervision.adminstaff.form.AdminStaffForm;


/**
 * @author jjose
 *
 */
public class SubmitPositionUpdateAction extends JIMSBaseAction {
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.submit", "positionVacate");
		keyMap.put("button.backToPositionSearch", "backToSearch");
		
	}

	public ActionForward positionVacate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}
	public ActionForward finish(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		String mappingForward=UIConstants.SUCCESS;
		if(myForm.getAction().equals(UIConstants.CREATE) || myForm.getAction().equals(UIConstants.UPDATE) ){
			 UpdateStaffPositionEvent updateEvent = (UpdateStaffPositionEvent) EventFactory
	         .getInstance(CSCDStaffPositionControllerServiceNames.UPDATESTAFFPOSITION);
			 myForm.getPosition().setSupervisor(myForm.getUpdatedSupervisor());
			 UIAdminStaffHelper.populateUpdateStaffPositionEvent(myForm.getPosition(),updateEvent);
			 CompositeResponse cr= postRequestEvent(updateEvent);
		     CSCDSupervisionStaffResponseEvent sre = (CSCDSupervisionStaffResponseEvent) MessageUtil.filterComposite(cr, CSCDSupervisionStaffResponseEvent.class);
		     if(sre!=null && myForm.getAction().equals(UIConstants.CREATE)){
		     	myForm.getPosition().setPositionId(sre.getStaffPositionId());
		     }
		     NoLegacyProgramUnitOnOrganizationEvent re = (NoLegacyProgramUnitOnOrganizationEvent) MessageUtil.filterComposite(cr, NoLegacyProgramUnitOnOrganizationEvent.class);
		     if (re != null){
		         sendToErrorPage(aRequest,"error.positionCRM26NotUpdated");
		     }
		}
		else if(myForm.getAction().equals(UIAdminStaffHelper.VACATE) ){
			VacateStaffPositionEvent vacateEvent = (VacateStaffPositionEvent) EventFactory
	         .getInstance(CSCDStaffPositionControllerServiceNames.VACATESTAFFPOSITION);
			 vacateEvent.setSupervisionStaffId(myForm.getPosition().getPositionId());
			 vacateEvent.setEffectiveDate(
					 DateUtil.stringToDate(
							 myForm.getPosition().getEffectiveDateAsStr(),
							 DateUtil.DATE_FMT_1));
			 CompositeResponse cr= postRequestEvent(vacateEvent);
		     NoLegacyProgramUnitOnOrganizationEvent re = (NoLegacyProgramUnitOnOrganizationEvent) MessageUtil.filterComposite(cr, NoLegacyProgramUnitOnOrganizationEvent.class);
		     if (re != null){
		         sendToErrorPage(aRequest,"error.positionCRM26NotUpdated");
		     } else {
		     }
		}
		else if(myForm.getAction().equals(UIAdminStaffHelper.RETIRE)){
			RetireStaffPositionEvent staffEvent = (RetireStaffPositionEvent) EventFactory
			.getInstance(CSCDStaffPositionControllerServiceNames.RETIRESTAFFPOSITION);
			staffEvent.setSupervisionStaffId(myForm.getPosition().getPositionId());
			if(myForm.getPosition().getUser()!=null && myForm.getPosition().getUser().getUserId()!=null){
				staffEvent.setSupervisionStaffId(myForm.getPosition().getUser().getUserId());
			}
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
			     errorResponse = (NoLegacyProgramUnitOnOrganizationEvent) MessageUtil.filterComposite(myResp, NoLegacyProgramUnitOnOrganizationEvent.class);
			     if (errorResponse != null){
			         sendToErrorPage(aRequest,"error.positionCRM26NotUpdated");
			     }

			}
		}
		else if(myForm.getAction().equals(UIAdminStaffHelper.ASSIGN) || myForm.getAction().equals(UIAdminStaffHelper.REASSIGN)){
		        myForm.getPosition().setUser(myForm.getReassignedUser());

			AssignStaffPositionEvent staffEvent = (AssignStaffPositionEvent) EventFactory
			.getInstance(CSCDStaffPositionControllerServiceNames.ASSIGNSTAFFPOSITION);
			//staffEvent.setCasenoteDate(myForm.getCasenote().getCasenoteDate());
			if(myForm.getPosition().isHasCaseload()){
				staffEvent.setNotes(myForm.getCasenote().getCasenoteText());
			}
			
			//staffEvent.setUserID(SecurityUIHelper.getLogonId());
			if(myForm.getPosition().getUser()!=null && myForm.getPosition().getUser()!=null){
				staffEvent.setStaffLogonId(myForm.getPosition().getUser().getUserId());
				staffEvent.setCjadNum(myForm.getPosition().getUser().getCjad());
				staffEvent.setEffectiveDate(
						DateUtil.stringToDate(
								myForm.getPosition().getEffectiveDateAsStr(), 
									DateUtil.DATE_FMT_1));
			}
			staffEvent.setStaffPositionId(myForm.getPosition().getPositionId());
			CompositeResponse myResp=postRequestEvent(staffEvent);
			NoLegacyProgramUnitOnOrganizationEvent crm26Error = (NoLegacyProgramUnitOnOrganizationEvent) MessageUtil.filterComposite(myResp, NoLegacyProgramUnitOnOrganizationEvent.class);
			if (crm26Error != null){
			    sendToErrorPage(aRequest,"error.positionCRM26NotUpdated");
			} 
		}
		if(mappingForward.equals(UIConstants.SUCCESS)){
	        myForm.setSecondaryAction(UIConstants.CONFIRM);
		}
		return aMapping.findForward(mappingForward);
	}
	
	public ActionForward backToSearch(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		AdminStaffForm myForm=(AdminStaffForm)aForm;
		myForm.clearAll();
		return aMapping.findForward(UIConstants.BACK_TO_SEARCH);
	}
}
