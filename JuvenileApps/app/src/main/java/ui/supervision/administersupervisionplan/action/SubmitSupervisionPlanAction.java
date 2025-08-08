//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisionplan\\action\\SubmitSupervisionPlanAction.java

package ui.supervision.administersupervisionplan.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administersupervisionplan.DeleteSupervisionPlanEvent;
import messaging.administersupervisionplan.UpdateSupervisionPlanEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanResponseEvent;
import messaging.managetask.SendSupervisionPlanReviewTaskEvent;
import messaging.managetask.UpdateCSTaskEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.naming.CalendarConstants;
import naming.CSTaskControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administersupervisionplan.AdminSupervisionPlanHelper;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class SubmitSupervisionPlanAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.saveAsDraft", "saveAsDraft");
	}
	
	
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		
		if(supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			DeleteSupervisionPlanEvent deleteSupPlanEvent = (DeleteSupervisionPlanEvent)AdminSupervisionPlanHelper.getDeleteSupervisionPlanEvent(supervisionPlanForm);
			this.postRequestEvent(deleteSupPlanEvent);
			
			aRequest.setAttribute("confirmMessage","Supervision Plan successfully deleted.");
			return aMapping.findForward(UIConstants.DELETE_SUCCESS);
		}
//		In case of Create, Update, Copy
		else
		{
			supervisionPlanForm.setStatusCd(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_ACTIVE);
			if((!supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
			{
				supervisionPlanForm.setSupervisionPlanId("");
			}
			UpdateSupervisionPlanEvent updateSupPlanEvent = (UpdateSupervisionPlanEvent) AdminSupervisionPlanHelper.getUpdateSupervisionPlanEvent(supervisionPlanForm);
			
			CompositeResponse compositeResponse = this.postRequestEvent(updateSupPlanEvent);
			SupervisionPlanResponseEvent supPlanResponseEvt = (SupervisionPlanResponseEvent)MessageUtil.filterComposite(compositeResponse, SupervisionPlanResponseEvent.class);
			if(supPlanResponseEvt==null)
			{
				if(supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
				{
					sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to update the Supervision Plan");
				}
//				create / copy
				else
				{
					sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to create the Supervision Plan");
				}
				return aMapping.findForward(UIConstants.FAILURE);
			}
			supervisionPlanForm.setSupervisionPlanId(supPlanResponseEvt.getSupervisionPlanId());
			
			if(supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
			{
				aRequest.setAttribute("confirmMessage","Supervision Plan successfully updated.");
			}
			else 
			{
				aRequest.setAttribute("confirmMessage","Supervision Plan successfully created.");
			}
			supervisionPlanForm.setSecondaryAction(UIConstants.CONFIRM);	
			
			if ( StringUtils.isNotEmpty( supervisionPlanForm.getTaskId() ) ){
				
				UpdateCSTaskEvent updateTaskEvent = 
					(UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
				
				updateTaskEvent.setCsTaskId( supervisionPlanForm.getTaskId() );
				updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
				updateTaskEvent.setCloseTask(true);
				MessageUtil.postRequest(updateTaskEvent);
			}
			
			return aMapping.findForward(UIConstants.CREATE_UPDATE_COPY_SUCCESS);
		}
	}
	
	
	public ActionForward saveAsDraft(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)aForm;
		
		supervisionPlanForm.setStatusCd(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_DRAFT);
		if((!supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			supervisionPlanForm.setSupervisionPlanId("");
		}
		UpdateSupervisionPlanEvent updateSupPlanEvent = (UpdateSupervisionPlanEvent) AdminSupervisionPlanHelper.getUpdateSupervisionPlanEvent(supervisionPlanForm);
		
		CompositeResponse compositeResponse = this.postRequestEvent(updateSupPlanEvent);
		SupervisionPlanResponseEvent supPlanResponseEvt = (SupervisionPlanResponseEvent)MessageUtil.filterComposite(compositeResponse, SupervisionPlanResponseEvent.class);
		if(supPlanResponseEvt==null)
		{
			if(supervisionPlanForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
			{
				sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to update the Supervision Plan");
			}
//			create / copy
			else
			{
				sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to create the Supervision Plan");
			}
			return aMapping.findForward(UIConstants.FAILURE);
		}
		SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		aRequest.setAttribute("confirmMessage","Supervision Plan saved as draft.");
		if(supPlanResponseEvt != null){
			SendSupervisionPlanReviewTaskEvent supervisionplanTaskEvent = (SendSupervisionPlanReviewTaskEvent) EventFactory
					.getInstance(CSTaskControllerServiceNames.SENDSUPERVISIONPLANREVIEWTASK);
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR, 168);
			Date dueDate = cal.getTime();
			supervisionplanTaskEvent.setDueDate(dueDate);
			
			// Get tasks for userId and Position
	        String userPosition = "";
			GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
			gEvent.setLogonId(SecurityUIHelper.getLogonId());
			CompositeResponse resp = MessageUtil.postRequest(gEvent);
			
			LightCSCDStaffResponseEvent response = (LightCSCDStaffResponseEvent) MessageUtil.filterComposite(resp, LightCSCDStaffResponseEvent.class);

	        if (response != null)
	        {
	            userPosition = response.getStaffPositionId();
	        }
	        
			supervisionplanTaskEvent.setStaffPositionId( userPosition );
			supervisionplanTaskEvent.setSupervisionPlanId(supPlanResponseEvt.getSupervisionPlanId());			
			
			supervisionplanTaskEvent.setTaskTopic("REVIEW SUPERVISION PLAN NOTIFICATION");
			supervisionplanTaskEvent.setTaskSubject("SUPERVISION PLAN IN DRAFT STATUS GT 7 DAYS");
			supervisionplanTaskEvent.setDefendantId(supervisionPlanForm.getDefendantId());
			supervisionplanTaskEvent.setSuperviseeName( myHeaderForm.getSuperviseeNameDesc() );
			
			//Registering the task with the scheduler
			RegisterTaskEvent rtEvent = new RegisterTaskEvent();
			rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
			rtEvent.setFirstNotificationDate(dueDate);
			rtEvent.setNextNotificationDate(dueDate); 
			String taskName = supervisionplanTaskEvent.getClass().getName() +"-"+ Math.random();
			rtEvent.setTaskName(taskName);
			rtEvent.setNotificationEvent(supervisionplanTaskEvent);
			EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
			
		}
		
		return aMapping.findForward(UIConstants.SAVE_AS_DRAFT_SUCCESS);
	}
	
}
