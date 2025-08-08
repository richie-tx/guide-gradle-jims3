/*
 * Created on Apr 12, 2007
 
 */
package ui.supervision.managetasks.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.transferobjects.CSCDStaffPositionTO;
import messaging.transferobjects.OrganizationTO;
import messaging.transferobjects.UserProfileTO;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.managetasks.form.TasksSearchForm;

/**
 * @author hrodriguez
 *  
 */
public class DisplayTasksAdvancedSearchAction extends JIMSBaseAction
{

    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.basicSearch", "basicSearch");
        keyMap.put("button.advancedSearch", "advancedSearch");
        keyMap.put("button.getSupervisors", "getSupervisors");
        keyMap.put("button.submit", "submit");
        keyMap.put("button.refresh", "refresh");
        keyMap.put("button.continueTask", "continueTask");
        keyMap.put("button.acceptTask", "acceptTask");
        keyMap.put("button.closeTask", "closeTask");
        keyMap.put("button.viewOrder", "viewOrder");
        keyMap.put("button.taskDetails", "taskDetails");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 45DB5B2501CD
     */

    //Pass to Basic search (default) page
    public ActionForward basicSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        tsForm.clearAll();

        tsForm.setAction(UIConstants.BASIC);
        return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
    }

    //Display AdvancedSearch page
    public ActionForward advancedSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        tsForm.clearAll();
        // set defaults up

        Collection orgs = new ArrayList();
//      PD Commenting :         OrganizationService organizationService = OrganizationService.getOrganizationService();
//      PD Commenting :         orgs = organizationService.getDivisionForAgency(SecurityUIHelper.getUserAgencyId());

        orgs = new ArrayList() ; 
        Iterator orgIter = orgs.iterator();
        Collection staffMembers = new ArrayList();

        OrganizationTO division = null;
        CSCDStaffPositionTO divManager = null;
        
         while (orgIter.hasNext())
        {
            division = (OrganizationTO) orgIter.next();
            divManager = division.getManager();
     
            if (divManager != null)
            {
                CSCDSupervisionStaffResponseEvent positionManager = getPositionObject(divManager);
                staffMembers.add(positionManager);
              
            }

        }

        tsForm.setDivisionCollection(staffMembers);

        tsForm.setAction(UIConstants.ADVANCED);
        forward = aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
        return forward;
    }

    
    public static CSCDSupervisionStaffResponseEvent  getPositionObject(CSCDStaffPositionTO staff){
        
        CSCDSupervisionStaffResponseEvent supStaff = new CSCDSupervisionStaffResponseEvent();
        
        UserProfileTO userProfile = (UserProfileTO) staff.getUserProfile();
        String userName = "NO OFFICER ASSIGNED";
        String positionName = "";
        if(userProfile!=null){
            
            userName =  userProfile.getLastName()+ ", "+ userProfile.getFirstName()+ " " + userProfile.getMiddleName();
            positionName = staff.getPositionName();
            if(positionName != null && !positionName.equals("")){
                supStaff.setPositionName(userName + " | " + positionName);
            }
        }else{
            supStaff.setPositionName(userName + " |" + staff.getPositionName());
        }
        
        supStaff.setDivisionId(staff.getOrganizationId());
       // supStaff.setPositionName(staff.getPositionName());
        
        Collection children = staff.getChildStaffPositions();
        
       // supStaff.setChildren(staff.getChildStaffPositions());
        
        
        
        return supStaff;
    }
   
    //Process the Advanced Search
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        Collection taskList = new ArrayList();

       /* Collection advanceSearchTasks = UIManagetasksHelper.getAdvancedSearchTasks(tsForm);
        if (!(advanceSearchTasks.size() == 0))
        {
            taskList.addAll(advanceSearchTasks);
        }*/

        tsForm.setTaskResultList(taskList);
        forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
        return forward;
    }
    
    //Advanced search options
    public ActionForward getSupervisors(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        tsForm.clearAll();

        tsForm.setAction(UIConstants.BASIC);
        return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
    }

    //Reset previous search criteria
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        TasksSearchForm tsForm = (TasksSearchForm) aForm;
        tsForm.refreshAdvancedSearch();

        return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
    }

    //Future option
    public ActionForward continueTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        forward = aMapping.findForward(UIConstants.CONTINUE_SUCCESS);
        return forward;
    }

    //Future option
    public ActionForward acceptTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        forward = aMapping.findForward(UIConstants.ACCEPT_SUCCESS);
        return forward;
    }

    //Future option
    public ActionForward closeTask(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        forward = aMapping.findForward(UIConstants.CLOSE_SUCCESS);
        return forward;
    }

    //Future option
    public ActionForward viewOrder(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        forward = aMapping.findForward(UIConstants.VIEW_ORDER_SUCCESS);
        return forward;
    }

    //Display Task Detail page
    public ActionForward taskDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();
        TasksSearchForm tsForm = (TasksSearchForm) aForm;

        forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
        return forward;
    }

}
