//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\officer\\action\\DisplayDepartmentSearchResultsAction.java

package ui.contact.officer.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.officer.form.OfficerForm;

public class DisplayDepartmentSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42E6765E02D5
	 */
	public DisplayDepartmentSearchResultsAction()
	{

	}
	
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.findDepartments", "findDepartments");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42E65EA50288
	 */
	public ActionForward findDepartments(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OfficerForm officerForm = (OfficerForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		officerForm.setOfficerProfiles((List) emptyColl);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetDepartmentsEvent departmentEvent = (GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
		departmentEvent.setDepartmentName(officerForm.getDepartmentNamePrompt());
		departmentEvent.setDepartmentId(officerForm.getDepartmentIdPrompt());
		dispatch.postEvent(departmentEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
		if (departments != null && departments.size() > 0)
		{
			officerForm.setDepartments(departments);
		}
		else
		{
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       		return aMapping.findForward(UIConstants.FAILURE);
	       	} 
	       	else
	       	{
				officerForm.setDepartments(new ArrayList());
	       		this.sendToErrorPage(aRequest, "error.no.department.found");
	       	}	
		}
		return aMapping.findForward(UIConstants.FIND_MANAGE_PROFILE_DEPARTMENT_SUCCESS);
	}
	
	public ActionForward refresh(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			OfficerForm officerForm = (OfficerForm) aForm;

			//empty all collection so that the display page does not display it
			Collection emptyColl = new ArrayList();
			emptyColl = MessageUtil.processEmptyCollection(emptyColl);
			officerForm.setDepartments(emptyColl);
			//reset all input search values
			officerForm.setDepartmentNamePrompt("");
			officerForm.setDepartmentIdPrompt("");
						
			return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		}

	/**
	* @param aRequest
	*/
	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}
}