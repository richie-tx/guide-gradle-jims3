//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayDepartmentSearchResultsAction.java

package ui.contact.department.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import messaging.info.reply.CountInfoMessage;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.securitytransactionsevents.reply.AuthenticationFailedResponseEvent;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;
import mojo.km.messaging.InfoMessageEvent;

public class DisplayDepartmentSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630EA0355
	 */
	public DisplayDepartmentSearchResultsAction()
	{

	}

	/**
	 * @return Map
	 */
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
	 * @roseuid 430628E50216
	 */
	public ActionForward findDepartments(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		departmentForm.setDepartmentList(emptyColl);
		departmentForm.setAgencies(emptyColl);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetDepartmentsEvent departmentEvent = (GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);

		departmentEvent.setAgencyName(departmentForm.getAgencyName());
		departmentEvent.setDepartmentName(departmentForm.getDepartmentName());
		departmentEvent.setDepartmentId(departmentForm.getDepartmentId());
		departmentEvent.setStatusId(departmentForm.getStatusId());
		departmentEvent.setOriginatingAgencyId(departmentForm.getOriginatingAgencyId());
		departmentEvent.setCreateOfficerProfileInd(departmentForm.getCreateOfficerProfileInd());
		departmentEvent.setSetcicAccessId(departmentForm.getSetcicAccessId());
		dispatch.postEvent(departmentEvent);
		
		// Check the Response to see if we have results
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
		
		if (departments != null && departments.size() > 0)
		{
			Collection agencies = UIDepartmentHelper.generateAgencyDepartmentResponseEvent(departments);
			departmentForm.setAgencies(agencies);
			departmentForm.setDepartmentList(departments);
		}else {
			
//			RAC check for too many records
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
			this.sendToErrorPage(aRequest, "error.no.department.found");
		}
		}
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
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
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		departmentForm.setDepartmentList(emptyColl);
		departmentForm.setAgencies(emptyColl);
		departmentForm.setAgencyName("");
		departmentForm.setDepartmentName("");
		departmentForm.setDepartmentId("");
		departmentForm.setStatusId("");
		departmentForm.setOriginatingAgencyId("");
		departmentForm.setSetcicAccessId("");

		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}	
}
