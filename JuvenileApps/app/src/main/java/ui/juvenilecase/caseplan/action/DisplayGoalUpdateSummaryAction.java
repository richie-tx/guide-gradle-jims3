package ui.juvenilecase.caseplan.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayGoalUpdateSummaryAction extends JIMSBaseAction
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     */
    public ActionForward displayGoalSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;
        form.setSecondaryAction(UIConstants.EMPTY_STRING);
        CaseplanForm.GoalInfo newGoal = form.getCurrentGoalInfo();
        if (newGoal.getStatusCd() != null && newGoal.getStatusCd().equals(PDCodeTableConstants.GOAL_STATUS_APPROVED))
        {
            if (newGoal.majorGoalChange())
            {
                String endRecomm = newGoal.getEndRecommendations();
                if (endRecomm != null && endRecomm.trim().length() != 0)
                {
                    sendToErrorPage(aRequest, "error.generic", "This goal cannot be both modified and have end recommendations at the same time");
                    return aMapping.findForward(UIConstants.FAILURE);
                }
            }
        }
        form.setSecondaryAction("GOALFLOW");
        aRequest.setAttribute("status", "summary");
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.next", "displayGoalSummary");

    }
}