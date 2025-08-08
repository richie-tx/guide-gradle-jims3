/*
 * Created on May 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security.action;

/**
 * @author sprakash
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.security.GetDepartmentConstraintsForUserAdministrationEvent;
import messaging.security.reply.DepartmentConstraintsForUserAdministrationResponseEvent;
import messaging.security.reply.UserResponseforUserAdministrationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDSecurityConstants;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.form.SAUsersForm;

public class DisplaySAUserDeptAction extends Action
{

	/**
	 * 
	 */
	public DisplaySAUserDeptAction()
	{
	}

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		String forward = UIConstants.FAILURE;
		SAUsersForm saForm = (SAUsersForm) aForm;

		// get the selected user
		UserResponseforUserAdministrationEvent responseEvent = saForm.getSelectedUser();

		// get the selected userType
		String[] selUserTypes = saForm.getSelectedUserTypes();
		if (selUserTypes == null || selUserTypes.length == 0)
		{
			forward = UIConstants.SUCCESS_SUMMARY;
		}
		else
		{
			String userTypeId = selUserTypes[0];
			saForm.setSelectedUserTypeId(userTypeId);
			if (userTypeId.equals(PDSecurityConstants.USER_TYPE_SA)
				|| userTypeId.equals(PDSecurityConstants.USER_TYPE_MA))
			{
				forward = UIConstants.SUCCESS_SUMMARY;
			}
			else
			{
				// get the departments for the agency
				Collection agencyDepts = fetchAgencyDepts(responseEvent.getAgencyId());
				Collections.sort((List)agencyDepts); 
				saForm.setAllDepartments(agencyDepts);
				// get the departments for the user
				Collection userDepts = fetchUserDepts(responseEvent.getLogonId());
				saForm.setSelectedDepartments(convertIntoArray(userDepts));
				forward = UIConstants.SUCCESS;
			}
		}

		return aMapping.findForward(forward);
	}

	private Collection fetchUserDepts(String logonId)
	{
		GetDepartmentConstraintsForUserAdministrationEvent requestEvent = new GetDepartmentConstraintsForUserAdministrationEvent();
		requestEvent.setLogonId(logonId);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection depts = (Collection) MessageUtil.compositeToCollection(compositeResponse, DepartmentConstraintsForUserAdministrationResponseEvent.class);
		depts = MessageUtil.processEmptyCollection(depts);
		return depts;
	}

	private Collection fetchAgencyDepts(String agencyId)
	{
		GetDepartmentsEvent requestEvent = new GetDepartmentsEvent();
		requestEvent.setAgencyId(agencyId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection depts = (Collection) MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
		depts = MessageUtil.processEmptyCollection(depts);
		return depts;
	}

	private String[] convertIntoArray(Collection objs)
	{
		Iterator iter = objs.iterator();
		String[] strings = new String[objs.size()];
		int i = 0;
		while (iter.hasNext())
		{
			DepartmentConstraintsForUserAdministrationResponseEvent respEvent =	(DepartmentConstraintsForUserAdministrationResponseEvent) iter.next();
			strings[i++] = respEvent.getDepartmentId();
		}
		return strings;
	}
}