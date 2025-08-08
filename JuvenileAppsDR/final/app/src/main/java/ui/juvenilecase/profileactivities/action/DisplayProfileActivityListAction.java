/*
 * Created on Dec 21, 2006
 *
 */
package ui.juvenilecase.profileactivities.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.activities.form.ActivitiesForm;

import ui.juvenilecase.form.JuvenileProfileForm;
import ui.security.SecurityUIHelper;

/**
 * @author C_NAggarwal
 * 
 */
public class DisplayProfileActivityListAction extends LookupDispatchAction
{

    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward viewActivities(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActivitiesForm form = (ActivitiesForm) aForm;
        JuvenileProfileForm juvenileProfileForm = (JuvenileProfileForm) aRequest.getSession().getAttribute(
                "juvenileProfileHeader");
        form.setAction(UIConstants.CONFIRM);
        form.setSecondaryAction("displayActivities");
        form.setSelectedValue("");

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        GetActivitiesEvent reqEvent = (GetActivitiesEvent) EventFactory
                .getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
        
        String serviceProviderId = SecurityUIHelper.getServiceProviderId();
        
        if (juvenileProfileForm.getJuvenileNum() != null && ((serviceProviderId != null && serviceProviderId != "") || (reqEvent.getCasefileID() == null || reqEvent.getCasefileID() == ""))) {           
            reqEvent.setJuvenileNum(juvenileProfileForm.getJuvenileNum());
           }
        
        if (form.getStartDate() != null) {
        	reqEvent.setStartDate(form.getStartDate());
        }
        if (form.getEndDate() != null) {
        	Date endDate = form.getEndDate();
        	endDate = new Date((endDate.getTime()) + 24*3600*1000);
        	reqEvent.setEndDate(endDate);
        }
        


        if (form.getSelectedCategoryId().equalsIgnoreCase("selectAll"))
        {
            reqEvent.setCategoryId("");
        }
        else
        {
            reqEvent.setCategoryId(form.getSelectedCategoryId());
        }

        reqEvent.setActivityTypeId(form.getSelectedTypeId());
        reqEvent.setActivityCodeId(form.getSelectedDescriptionId());
        reqEvent.setActivityTime(form.getActivityTimeStr());

        List activityResults = MessageUtil.postRequestListFilter(reqEvent, ActivityResponseEvent.class);

        form.setActivityResults(activityResults);
        return aMapping.findForward(UIConstants.LISTSUCCESS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.submit", "submit");
        keyMap.put("button.addActivity", "addActivity");
        keyMap.put("button.viewActivities", "viewActivities");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

}
