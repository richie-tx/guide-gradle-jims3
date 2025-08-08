/*
 * Project: JIMS2
 * Class:   ui.security.action.DisplaySAUsersSummaryAction
 * Version: 1.0.0
 *
 * Date:    2005-9-28
 *
 * Author:  surya prakash
 * Email:   sprakash@jims.hctx.net
 */
package ui.security.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.security.UpdateDepartmentConstraintEvent;
import messaging.security.UpdateUserTypeAndDepartmentConstraintsEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.form.SAUsersForm;

public class DisplaySAUserSummaryAction extends LookupDispatchAction
{

	/**
	 * 
	 */
	public DisplaySAUserSummaryAction()
	{
	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.back", "back");
		return buttonMap;
	}
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		SAUsersForm saForm = (SAUsersForm) aForm;
		// get the selected user
		UserResponseforUserAdministrationEvent selUser = saForm.getSelectedUser();
		aForm.reset(aMapping, aRequest);

		UpdateUserTypeAndDepartmentConstraintsEvent rqEvent = (UpdateUserTypeAndDepartmentConstraintsEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.UPDATEUSERTYPEANDDEPARTMENTCONSTRAINTS);
		rqEvent.setLogonId(selUser.getLogonId());
		// user type		
		rqEvent.setUserTypeId(saForm.getSelectedUserTypeId());

		// fetch selected departments
		Collection selDepartments = saForm.getSelDepartments();
		if (selDepartments != null)
		{
			Iterator deptIter = selDepartments.iterator();
			while (deptIter.hasNext())
			{
				DepartmentResponseEvent deptEvent = (DepartmentResponseEvent) deptIter.next();
				UpdateDepartmentConstraintEvent event = new UpdateDepartmentConstraintEvent();
				event.setDepartmentId(deptEvent.getDepartmentId());
				rqEvent.addRequest(event);
			}
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rqEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		forward = UIConstants.SUCCESS;
		return aMapping.findForward(forward);
	}

	/**
	 * Returns the update department constraint event.
	 *  
	 * @param divId The div id.
	 * @return  The update department constraint event.
	 */
	private UpdateDepartmentConstraintEvent getUpdateDepartmentConstraintEvent(String divId)
	{
		UpdateDepartmentConstraintEvent event = new UpdateDepartmentConstraintEvent();
		event.setDepartmentId(divId);
		return event;

	}
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SAUsersForm saForm = (SAUsersForm) aForm;
		// fetch selected departments -- not sure why value on form is null
		Collection selDepts = saForm.getSelDepartments();
		
		String[] deptSel = new String[selDepts.size()];
		int i = 0;
		if (selDepts != null)
		{
			Iterator deptIter = selDepts.iterator();
			while (deptIter.hasNext())
			{
				DepartmentResponseEvent deptEvent = (DepartmentResponseEvent) deptIter.next();
				deptSel[i++] = deptEvent.getDepartmentId();
			}
		}
		saForm.setSelectedDepartments(deptSel);	

		return aMapping.findForward(UIConstants.BACK);	
	}	
}