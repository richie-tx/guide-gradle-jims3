//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\SubmitMedicalHealthIssuesUpdateAction.java

package ui.juvenilecase.medical.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.CreateMedicalHealthIssueEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.medical.UIJuvenileProfileMedicalHelper;
import ui.juvenilecase.medical.form.MedicalForm;


public class SubmitMedicalHealthIssuesUpdateAction extends JIMSBaseAction 
{
   
   /**
    * @roseuid 462CE3DC03DD
    */
   public SubmitMedicalHealthIssuesUpdateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 462CBCA6035E
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		MedicalForm medForm = (MedicalForm) aForm;
   		MedicalForm.HealthIssue issue = medForm.getHsRec();
   		CreateMedicalHealthIssueEvent issueEvent=(CreateMedicalHealthIssueEvent)EventFactory.getInstance(JuvenileControllerServiceNames.CREATEMEDICALHEALTHISSUE);
   		issueEvent.setConditionLevelId(issue.getConditionLevelId());
   	   		
	   	 if(issue.getAction().equals(UIConstants.UPDATE)){	   		 
	   		issueEvent.setHealthIssuesListId(medForm.getSelectedValue());	   		
	     }	     
  	 //ended		
   		
   		issueEvent.setConditionSeverityId(issue.getConditionSeverityId());
   		issueEvent.setIssueId(issue.getIssueId());
   		issueEvent.setIssueStatusId(issue.getIssueStatusId());
   		issueEvent.setHealthStatusId(issue.getHealthStatusId());
   		issueEvent.setModificationReason(issue.getModificationReason());
   		issueEvent.setJuvenileNum(medForm.getJuvenileNum());
   		issueEvent.setEntryDate(new Date());
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(issueEvent);	   		
   		CompositeResponse response = (CompositeResponse) dispatch.getReply();
   		// Perform Error handling
   		MessageUtil.processReturnException(response); 
   		medForm.setActionType("confirm");
   		medForm.setConfirmMessage("Health Issues Information successfully added.");
   		ActionForward forward = aMapping.findForward(UIConstants.FINISH);
   	    return forward;
   }
   
   public ActionForward returnToMedical(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
   		MedicalForm medForm = (MedicalForm) aForm;   
   		if(!medForm.getActionType().equalsIgnoreCase("view"))
   			medForm.setHealthIssuesList(UIJuvenileProfileMedicalHelper.getHealthIssuesList(medForm.getJuvenileNum()));
   		ActionForward forward = aMapping.findForward(UIConstants.RETURN_SUCCESS);
   		return forward;
	}
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

   public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);		
		return forward;
	}
   
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");	
		keyMap.put("button.returnToMedical", "returnToMedical");
	}	
}
