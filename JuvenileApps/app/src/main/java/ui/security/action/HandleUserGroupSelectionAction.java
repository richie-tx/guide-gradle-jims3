// Source file:
// C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleUserGroupSelectionAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.agency.GetDepartmentsEvent;
import messaging.agency.GetDepartmentsForASAEvent;
import messaging.codetable.GetCodesEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import messaging.security.GetUserGroupUsersEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.Name;
import ui.security.form.AssignRolesForm;
import ui.security.form.UserGroupForm;

public class HandleUserGroupSelectionAction extends Action
{

    /**
     * @roseuid 425AB74701C5
     */
    public HandleUserGroupSelectionAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4256D6310196
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        UserGroupForm userGroupForm = (UserGroupForm) aForm;
        this.resetDynamicFormValues(userGroupForm);
        
        String action = aRequest.getParameter("action");
        String groupId = aRequest.getParameter("groupId");
        String groupStatus = aRequest.getParameter("status");
        boolean matchFound = false;
        if (action != null && !(action.equals("")))
        {
            userGroupForm.setAction(action);
            userGroupForm.setGroupId(groupId);
            userGroupForm.setGroupStatus(groupStatus);
        }

        // get the user group info here
        Collection userGroups = userGroupForm.getUserGroups();
        Iterator iter = userGroups.iterator();
        UserGroupResponseEvent responseEvent = null;
        while (iter.hasNext())
        {
            responseEvent = (UserGroupResponseEvent) iter.next();
            if (responseEvent.getUserGroupId().equals(groupId))
            {
                userGroupForm.setUserGroupName(responseEvent.getName());
                userGroupForm.setOriginalUserGroupName(responseEvent.getName());
                userGroupForm.setUserGroupDescription(responseEvent.getDescription());
                userGroupForm.setAgencyName(responseEvent.getAgencyName());
                userGroupForm.setAgencyId(responseEvent.getAgencyId());
                userGroupForm.setGroupStatusId(responseEvent.getStatusId());
                userGroupForm.setGroupStatus(responseEvent.getStatus());
                Name creatorName = new Name(responseEvent.getCreatorFirstName(), responseEvent.getCreatorMiddleName(),
                        responseEvent.getCreatorLastName());
                userGroupForm.setCreatorName(creatorName);
                matchFound = true;
                break;
            }
        }
        /**
         * 04/04/2007 C Shimek This should only be true on a hyperlink view from
         * Assign Roles Search Results if user has not previously searched
         * Manage User Profile using same search criteria. Found while tesing
         * changes made for hyperlinks to SI pages. These changes required
         * updating of commands to include userGroupId.
         */
        if (!matchFound)
        {
            AssignRolesForm assignRolesForm = getHeaderForm(aRequest, true);
            userGroups = assignRolesForm.getUserGroups();
            Iterator iter2 = userGroups.iterator();
            while (iter2.hasNext())
            {
                responseEvent = (UserGroupResponseEvent) iter2.next();
                if (responseEvent.getUserGroupId().equals(groupId))
                {
                    userGroupForm.setUserGroupName(responseEvent.getName());
                    userGroupForm.setOriginalUserGroupName(responseEvent.getName());                    
                    userGroupForm.setUserGroupDescription(responseEvent.getDescription());
                    userGroupForm.setAgencyName(responseEvent.getAgencyName());
                    userGroupForm.setAgencyId(responseEvent.getAgencyId());
                    userGroupForm.setGroupStatusId(responseEvent.getStatusId());
                    userGroupForm.setGroupStatus(responseEvent.getStatus());
                    Name creatorName = new Name(responseEvent.getCreatorFirstName(), responseEvent
                            .getCreatorMiddleName(), responseEvent.getCreatorLastName());
                    userGroupForm.setCreatorName(creatorName);
                    matchFound = true;
                    break;
                }
            }
        }
        //get the current users list

        GetUserGroupUsersEvent event = (GetUserGroupUsersEvent) EventFactory
                .getInstance(SecurityAdminControllerServiceNames.GETUSERGROUPUSERS);
        event.setUserGroupId(groupId);

        List userGroupUsers = MessageUtil.postRequestListFilter(event, SecurityUserResponseEvent.class);     

        if (userGroupUsers.size() > 0)
        {
            userGroupForm.setCurrentUsers(userGroupUsers);
        }
        
        String forward = this.computeForward(responseEvent, userGroupForm, action);
        
        return aMapping.findForward(forward);
    }

    /**
     * @param responseEvent
     * @param userGroupForm
     * @return
     */
    private String computeForward(UserGroupResponseEvent responseEvent, UserGroupForm userGroupForm, String action)
    {
        String forward = null;
        if (action.equalsIgnoreCase(UIConstants.VIEW))
        {
            forward = UIConstants.VIEW_SUCCESS;
        }
        else if (action.equalsIgnoreCase(UIConstants.ACTIVATE))
        {
            forward = UIConstants.ACTIVATE_SUCCESS;
        }
        else if (action.equalsIgnoreCase(UIConstants.INACTIVATE))
        {
            forward = UIConstants.INACTIVATE_SUCCESS;
        }
        else if (action.equalsIgnoreCase(UIConstants.DELETE))
        {
            forward = UIConstants.DELETE_SUCCESS;
        }
        else if (action.equalsIgnoreCase(UIConstants.UPDATE))
        {
            if (responseEvent.isRoleExist()
                    || (userGroupForm.getCurrentUsers() != null && !userGroupForm.getCurrentUsers().isEmpty()))
            {
                userGroupForm.setUpdateAgencyFlag(false);
            }
            else
            {
                userGroupForm.setUpdateAgencyFlag(true);
            }
            forward = UIConstants.UPDATE_SUCCESS;
            if (userGroupForm.getUserType().equalsIgnoreCase(UIConstants.MA_ROLETYPE))
            {
                Collection agencyTypes = fetchDropDownCodes(PDCodeTableConstants.AGENCY_TYPE);
                userGroupForm.setAgencyTypes(agencyTypes);
            }
            else if (userGroupForm.getUserType().equalsIgnoreCase(UIConstants.SA_ROLETYPE))
            {
                Collection departments = fetchDepartments(userGroupForm.getAgencyId());
                userGroupForm.setDepartments(departments);
            }
            else if (userGroupForm.getUserType().equalsIgnoreCase(UIConstants.ASA_ROLETYPE))
            {
                Collection departments = fetchASADepartments(userGroupForm.getAgencyId());
                userGroupForm.setDepartments(departments);
                forward = UIConstants.UPDATE_USERS_SUCCESS;
            }
        }
        else
        {
            forward = UIConstants.FAILURE;
        }
        
        return forward;
    }

    /**
     * @param userGroupForm
     */
    private void resetDynamicFormValues(UserGroupForm userGroupForm)
    {
        Collection emptyColl = new ArrayList();
        Collection col = MessageUtil.processEmptyCollection(emptyColl);
        userGroupForm.setCurrentUsers(col);
        userGroupForm.setUsers(col);
        userGroupForm.setAvailableUsers(col);
        userGroupForm.setAvailableAgencies(col);
        userGroupForm.setFirstName("");
        userGroupForm.setLastName("");
        userGroupForm.setDepartment("");
        userGroupForm.setUserId("");
        userGroupForm.setSearchAgencyName("");
        userGroupForm.setAgencySearchErrorMessage("");
        userGroupForm.setUserSearchErrorMessage("");
        userGroupForm.clearStringArray(userGroupForm.getSelectedAgencies());
        userGroupForm.clearStringArray(userGroupForm.getSelectedUsers());
    }

    /**
     * @param codeTableName
     * @return collection codes
     */
    public Collection fetchDropDownCodes(String codeTableName)
    {
        GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETCODES);
        codesRequestEvent.setCodeTableName(codeTableName);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(codesRequestEvent);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        Map dataMap = MessageUtil.groupByTopic(compositeResponse);
        MessageUtil.processReturnException(dataMap);

        Collection codes = (Collection) dataMap.get(PDCodeTableConstants.getCodeTableTopic(codeTableName));
        codes = MessageUtil.processEmptyCollection(codes);
        Collections.sort((ArrayList) codes);
        return codes;
    }

    /**
     * @param agencyId
     * @return collectoin departments
     */
    public Collection fetchDepartments(String agencyId)
    {
        GetDepartmentsEvent deptRequestEvent = (GetDepartmentsEvent) EventFactory
                .getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
        deptRequestEvent.setAgencyId(agencyId);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(deptRequestEvent);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        Map dataMap = MessageUtil.groupByTopic(compositeResponse);
        MessageUtil.processReturnException(dataMap);

        Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
        departments = MessageUtil.processEmptyCollection(departments);
        return departments;
    }

    /**
     * @param agencyId
     * @return collectoin departments
     * @roseuid 428B82BD0158
     */
    public Collection fetchASADepartments(String agencyId)
    {
        GetDepartmentsForASAEvent deptRequestEvent = (GetDepartmentsForASAEvent) EventFactory
                .getInstance(AgencyControllerServiceNames.GETDEPARTMENTSFORASA);
        deptRequestEvent.setAgencyId(agencyId);
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo user = manager.getIUserInfo();
        String logonId = user.getJIMSLogonId();
        deptRequestEvent.setLogonId(logonId);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(deptRequestEvent);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        Map dataMap = MessageUtil.groupByTopic(compositeResponse);
        MessageUtil.processReturnException(dataMap);

        Collection departments = MessageUtil.compositeToCollection(compositeResponse, DepartmentResponseEvent.class);
        departments = MessageUtil.processEmptyCollection(departments);
        return departments;
    }

    public AssignRolesForm getHeaderForm(HttpServletRequest aRequest, boolean isCreate)
    {
        AssignRolesForm myForm = getHeaderForm(aRequest);
        if (myForm == null && isCreate == true)
        {
            HttpSession session = aRequest.getSession();
            myForm = new AssignRolesForm();
            session.setAttribute("assignRolesForm", myForm);
        }
        return myForm;
    }

    /**
     * Added to get the header form for assingRoles
     * 
     * @return HttpServletRequest
     */
    public AssignRolesForm getHeaderForm(HttpServletRequest aRequest)
    {
        HttpSession session = aRequest.getSession();
        AssignRolesForm headerForm = (AssignRolesForm) session.getAttribute("assignRolesForm");
        return headerForm;
    }
}