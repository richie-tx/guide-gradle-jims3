package ui.supervision.programReferral.action;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import messaging.party.GetPartyDataEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSTaskControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PartyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.managetasks.helper.UIManagetasksHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;


/**
 * 
 * @author cc_bjangay
 *
 */
public class HandleTaskAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link","link");
		keyMap.put("button.submit","submit");
	}
	
	
	
		public ActionForward link(ActionMapping aMapping, ActionForm aForm,
						HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
			progRefForm.setProgramIssueTaskText("");
			
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
		
		
		public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
				HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
			String taskText = progRefForm.getProgramIssueTaskText();
			
			createNtTaskForWorkgroup( progRefForm, taskText );
			
			return aMapping.findForward(UIConstants.CLOSE_SUCCESS);
		}
		
		
		private void createNtTaskForWorkgroup(CSCProgRefForm progRefForm, String taskText)
		{
			String workgroupId = "";
			String workgroupName = CSAdministerProgramReferralsConstants.SERVICE_PROVIDER_COMMITTE_WORKGROUP_NAME;
			
			UIManagetasksHelper taskHelper = UIManagetasksHelper.getInstance();
			
			List workgroupList = taskHelper.fetchWorkgroupByName(PDCodeTableConstants.CSCD_AGENCY, "", workgroupName);
			if(workgroupList != null)
			{
				WorkGroupResponseEvent responseEvent = (WorkGroupResponseEvent)workgroupList.get(0);
				workgroupId = responseEvent.getWorkgroupId();
			}
			
			Calendar calendar = Calendar.getInstance();
			CreateCSTaskEvent createTaskEvent = (CreateCSTaskEvent)EventFactory.getInstance(CSTaskControllerServiceNames.CREATECSTASK);
			
			createTaskEvent.setTaskSubject(CSAdministerProgramReferralsConstants.PROGRAM_ISSUES);
			createTaskEvent.setWorkGroupId( workgroupId );
			createTaskEvent.setTopic(CSAdministerProgramReferralsConstants.CSTASK_TOPIC_PROGRAM_REPORT_ISSUES);
			createTaskEvent.setDueDate( calendar.getTime() );
			createTaskEvent.setCriminalCaseId( progRefForm.getCriminalCaseId() );
			createTaskEvent.setSubject2( CSAdministerProgramReferralsConstants.PROGRAM_ISSUES );
			createTaskEvent.setTastText( taskText );
			String defendantId =  progRefForm.getSpn();
			createTaskEvent.setDefendantId( defendantId );
			
			GetPartyDataEvent event =
				(GetPartyDataEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTYDATA);
			event.setSpn( defendantId );
			event.setThinResponse(true);
			
			PartyResponseEvent party = (PartyResponseEvent) MessageUtil.postRequest( event ,  PartyResponseEvent.class);
			StringBuffer sb = new StringBuffer();
	        if (party != null){
	        	
	        	sb.append( party.getLastName()).append( ", ")
	        				.append( party.getFirstName()).append( " ").append( party.getMiddleName() );
	        	createTaskEvent.setSuperviseeName( sb.toString() );
	        	
	        }

			MessageUtil.postRequest( createTaskEvent );


		}

}
