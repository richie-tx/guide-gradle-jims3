//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\DisplayAssignRolesSearchAction.java

package ui.security.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.form.AssignRolesForm;

/**
 * 
 * 
 * @author awidjaja
 * @description Check if current logon user is SA. For SA only, department is only available
 * as drop drown box (restricted by the user's agency).  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayAssignRolesSearchAction extends Action
{

	/**
	 * description public constructor
	 * @roseuid 4297201202FE
	 */
	public DisplayAssignRolesSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4294862303BD
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;
		assignRolesForm.clear();

		if (SecurityUIHelper.isUserMA())
		{
			assignRolesForm.setUserType(UIConstants.MA_ROLETYPE);
		}
		else
			if (SecurityUIHelper.isUserSA() || SecurityUIHelper.isUserASA())
			{
				assignRolesForm.setUserType(UIConstants.SA_ROLETYPE);

				//department is a dropdown box for SA, therefore it needs to be pre-populated
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				GetDepartmentsEvent requestEvent = (GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
				requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
				dispatch.postEvent(requestEvent);
				
				// Check the reply to see if we have results
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);

				Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
				assignRolesForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
				if (departments != null && departments.size() > 0)
				{
					assignRolesForm.setDepartments(departments);
					Iterator iter = departments.iterator();
					while(iter.hasNext()){
						DepartmentResponseEvent dEvent = (DepartmentResponseEvent) iter.next();
						if(dEvent != null){
							assignRolesForm.setAgencyName(dEvent.getAgencyName());
							break;	
						}
					}
				}
				else
				{
					//unexpected error, because each user must belong to a department
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.common"));
					saveErrors(aRequest, errors);
					return aMapping.findForward(UIConstants.FAILURE);
				}
				assignRolesForm.setErrorMessage("");
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.not.logged.in"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			}

		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
