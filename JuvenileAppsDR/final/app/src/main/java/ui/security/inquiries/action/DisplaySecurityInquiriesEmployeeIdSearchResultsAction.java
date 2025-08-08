//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesEmployeeIdSearchResultsAction.java

package ui.security.inquiries.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.inquiries.GetUserSecurityInfoEvent;
import messaging.security.inquiries.reply.UserSecurityInfoResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.InquiriesAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesEmployeeIdSearchResultsAction extends Action
{
   
   /**
    * @roseuid 44E9D1DE0177
    */
   public DisplaySecurityInquiriesEmployeeIdSearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2218701B1
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	   	String forward = UIConstants.FAILURE;
	   	String msg = null;
//	   	int size = 0;
	   	Collection emptyColl = new ArrayList();
	   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
	   	securityInquiriesForm.setAgencies(emptyColl);
	   	
	   	GetUserSecurityInfoEvent requestEvent = (GetUserSecurityInfoEvent) EventFactory.getInstance(InquiriesAdminControllerServiceNames.GETUSERSECURITYINFO);
	   	String employeeId = securityInquiriesForm.getEmployeeId();
	   	if (employeeId != null && !employeeId.equals("")){
	   		requestEvent.setEmployeeId(employeeId);
	   	}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		UserSecurityInfoResponseEvent uRespEvent = (UserSecurityInfoResponseEvent) MessageUtil.filterComposite(compositeResponse, UserSecurityInfoResponseEvent.class);
		if (uRespEvent != null){
			securityInquiriesForm.setLogonId(uRespEvent.getLogonId());
			//securityInquiriesForm.setJims2LogonId(uRespEvent.getJims2LogonId());//79250
			forward = UIConstants.EMPLOYEE_ID_SUCCESS;
		} else {
			ActionErrors errors = new ActionErrors();
			if (msg == null)
			{
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.employeeId.found"));
			}
			saveErrors(aRequest, errors);
		}	
		return aMapping.findForward(forward);
   }
}
