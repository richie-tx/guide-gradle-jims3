// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\SubmitCasefileClosingRejectionDecisionAction.java

package ui.juvenilecase.casefile.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.CreateNotificationEvent;
import messaging.task.UpdateTaskStatusEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.TaskControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.security.SecurityUIHelper;
import ui.security.authentication.form.LoginForm;
import ui.task.TaskHelper;

public class SubmitCasefileClosingRejectionDecisionAction extends LookupDispatchAction
{

    /**
     * @roseuid 4396048101F9
     */
    public SubmitCasefileClosingRejectionDecisionAction()
    {
 
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        myClosingForm.setRejectReason("");
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward saveAndContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        myClosingForm.setAction(UIConstants.CONFIRM_UPDATE);
        myClosingForm.setSecondaryAction("");
        myClosingForm.setSelectedValue("");       
       UpdateCasefileClosingEvent myEvent = UIJuvenileCasefileClosingHelper.getCasefileClosingEventFROMClosingForm(
                myClosingForm, UIConstants.CASEFILE_CASE_STATUS_ACTIVE);
       myEvent.setCasefileID(casefileForm.getSupervisionNum());
       myEvent.setApprovalRejected(true);
       
        CompositeResponse compositeResponse = MessageUtil.postRequest(myEvent);
        
        CasefileClosingResponseEvent event = (CasefileClosingResponseEvent) MessageUtil.filterComposite(compositeResponse,
                CasefileClosingResponseEvent.class);
        
        // CHANGE STATUS
        casefileForm.setCaseStatusId(UIConstants.CASEFILE_CASE_STATUS_ACTIVE);
        
		// record activity
		UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(), ActivityConstants.CASEFILE_CLOSING_REJECTED, myClosingForm.getRejectReason()); //also passing the reject reason as last param added by srutitwisha DEFECT JIMS200076279
		UIJuvenileHelper.createActivity(casefileForm.getSupervisionNum(), ActivityConstants.CASE_REVIEWED_FOR_CLOSING, "");        
        
		//start send notification
		event.setSubject("Casefile closing has been rejected for Juvenile # " + casefileForm.getJuvenileNum());
		event.setIdentity(casefileForm.getProbationOfficerLogonId());
		
		String s = casefileForm.getJuvenileName().getFormattedName() + 
					" " + casefileForm.getJuvenileNum() + ", " + "Supervision# " + casefileForm.getSupervisionNum() +
					", has been rejected for closing due to the following reason: " + myClosingForm.getRejectReason();
		
		event.setNotificationMessage(s);
		CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
		EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		notificationEvent.setNotificationTopic("JC.REJECT.CASEFILE.CLOSING.REQUEST");
		notificationEvent.setSubject(event.getSubject());
		notificationEvent.addIdentity("juvenileProbationOfficer", (IAddressable)event);
		notificationEvent.addContentBean(event);
		IDispatch dispatchNotification = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatchNotification.postEvent(notificationEvent);        
        //end send notification		
		//remove task from list
		removeTask(aRequest);
        return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
		
	
    }
    
    private void removeTask(HttpServletRequest aRequest)
    {
    	LoginForm myForm = getLoginForm(aRequest);
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    	UpdateTaskStatusEvent updateEvent = 
			(UpdateTaskStatusEvent) EventFactory.getInstance(TaskControllerServiceNames.UPDATETASKSTATUS);
    	updateEvent.setTaskId(myForm.getTaskId());
		updateEvent.setStatusCode(UIConstants.CLOSED_STATUS_ID);
		dispatch.postEvent(updateEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map map = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(map);
	
		this.callNotificationFramework(SecurityUIHelper.getLogonId(),myForm);
    }
    private LoginForm getLoginForm(HttpServletRequest aRequest)
    {
    	HttpSession session = aRequest.getSession();
    	LoginForm myForm = (LoginForm) session.getAttribute("loginForm");    
        if (myForm == null)
        {    
        	myForm = new LoginForm();
        	 session.setAttribute("loginForm", myForm);
        }
        return myForm;
    }
	private void callNotificationFramework(String logonId, LoginForm loginForm) {
		Collection tasks = TaskHelper.getNotClosedUserTasks(logonId, UIConstants.CLOSED_STATUS_ID);
		Iterator tasksIter = tasks.iterator();
		int submittedTaskCount = 0;
		while(tasksIter.hasNext()){
			TaskResponseEvent resp = (TaskResponseEvent) tasksIter.next();
			String statusId = resp.getTask().getStatusCode();
			if(statusId != null && statusId.equalsIgnoreCase(UIConstants.SUBMITTED_STATUS_ID)){
				submittedTaskCount++;
			}
		}
		loginForm.setTaskCount("" + submittedTaskCount + " / " + tasks.size());
		loginForm.setTaskList(tasks);
	}
    private ActionForward dispatch(String returnPath){
		ActionForward newForward = new ActionForward(returnPath);
		newForward.setRedirect(true);
		return newForward;
	}

    public ActionForward returnToNotifications(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CasefileClosingForm myClosingForm = (CasefileClosingForm) aForm;
        myClosingForm.setAction("");        
        return dispatch(getPath(aRequest,"/appshell/displayHome.do"));
    }
    private String getPath(HttpServletRequest aRequest, String path)
    {
    	String portName = ""+aRequest.getServerPort();   	
    	String url = aRequest.getScheme()+"://"+ aRequest.getServerName()+":"+ portName+path;
    	return url;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4395C2370351
     */
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.saveAndContinue", "saveAndContinue");
        buttonMap.put("button.returnToNotifications", "returnToNotifications");
        return buttonMap;
    }
}
