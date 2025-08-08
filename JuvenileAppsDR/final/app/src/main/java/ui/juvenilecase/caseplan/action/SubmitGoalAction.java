package ui.juvenilecase.caseplan.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.PersonResponsibleEvent;
import messaging.caseplan.SaveGoalDataEvent;
import messaging.caseplan.reply.GoalOIDEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import ui.exception.GeneralFeedbackMessageException;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

import naming.CasePlanConstants;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.action.JIMSBaseAction;

/**
 * 
 * @author awidjaja
 * 
 */
public class SubmitGoalAction extends JIMSBaseAction
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     * 
     * This method will be called during Create Goal use-case
     */
    public ActionForward saveContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
        boolean update = false;
        CaseplanForm form = (CaseplanForm) aForm;
        CaseplanForm.GoalInfo newGoal = form.getCurrentGoalInfo();
        CaseplanForm.Caseplan caseplanInfo = form.getCurrentCaseplan();
        String endRecomm = newGoal.getEndRecommendations();
        if (endRecomm != null && !endRecomm.equals(""))
        {
            newGoal.setStatusCd(CasePlanConstants.GOAL_STATUS_ENDED);
            newGoal.setGoalEnded(true);
        }
        SaveGoalDataEvent goal = (SaveGoalDataEvent) EventFactory
                .getInstance(JuvenileCasePlanControllerServiceNames.SAVEGOALDATA);
        goal.setPending(false);
        goal.setGoalEnded(false);
        goal.setCaseplanStatus(caseplanInfo.getStatus()); 
        if (newGoal.getStatusCd() != null && newGoal.getStatusCd().equals(PDCodeTableConstants.GOAL_STATUS_APPROVED))
        {
            goal.setPending(newGoal.majorGoalChange());
        }
        String goalID = newGoal.getGoalId();
        goal.setGoalID(goalID);
        if (goalID != null && !goalID.equals(""))
        {
            update = true;
        }

        goal.setDomainTypeID(newGoal.getDomainTypeCd());
        goal.setTimeframeID(newGoal.getTimeFrameCd());
        goal.setExplainOtherTxt(newGoal.getOtherTimeFrameDesc());
        goal.setGoalDescription(newGoal.getGoal());
        goal.setGoalIntervention(newGoal.getIntervention()); //For adding Intervention field for ER JIMS200075816 
        goal.setProgressNotes(newGoal.getProgressNotes());
        goal.setEndRecommendations(newGoal.getEndRecommendations());
        String casefileId = form.getCasefileId();
        if (casefileId == null || casefileId.equals(""))
        {
            casefileId = UIJuvenileCaseworkHelper.getHeaderForm(aRequest).getSupervisionNum();
        }
        goal.setSupervisionNumber(casefileId);
        goal.setGoalEnded(newGoal.isGoalEnded());
        PersonResponsibleEvent prEvt;
        String[] persons = newGoal.getPersonsResponsibleIds();
        for (int i = 0; i < persons.length; i++)
        {
            //iterate thru the personResponsibleList and get the names
            Iterator iter = form.getPersonsResponsibleList().iterator();
            while (iter.hasNext())
            {
                CaseplanForm.PersonResponsible personResp = (CaseplanForm.PersonResponsible) iter.next();
                //String temp =
                // UIJuvenileCaseplanHelper.getDisplayContactNames(personResp.getName());
                if ((personResp.getName().trim()).equalsIgnoreCase(persons[i].trim()))
                {
                    prEvt = new PersonResponsibleEvent();
                    prEvt.setName(personResp.getName());
                    prEvt.setType(personResp.getType());
                    goal.addRequest(prEvt);
                }
            }
        }
        
        CompositeResponse compositeResponse = MessageUtil.postRequest(goal);
        
        Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
        if (errorResponse != null)
        {
            ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
            handleFatalUnexpectedException(myEvt.getMessage());
        }
        
        if (update)
        {
            aRequest.setAttribute("status", "confirm");
            return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
        }
        
        GoalOIDEvent goalEvt = (GoalOIDEvent) MessageUtil.filterComposite(compositeResponse, GoalOIDEvent.class);
        newGoal.setGoalId(goalEvt.getGoalID());
        List ruleResponses = MessageUtil.compositeToList(compositeResponse, RuleResponseEvent.class);
       /* form.setCaseplanExist(UIConstants.YES);  //add new
*/        //if no rules present, forward to the last page directly
        if (ruleResponses.size() == 0)
        {
            aRequest.setAttribute("status", "noRules");
            return aMapping.findForward("noRules");
        }
        ruleResponses = UIJuvenileCaseplanHelper.getRuleDetails(ruleResponses);
        newGoal.setAssociatedRules(ruleResponses);
        form.setStatus(UIConstants.CONFIRM);
        
        aRequest.setAttribute("status", "confirm");

        return aMapping.findForward(UIConstants.CREATE_SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282 This method will be called during Update Goal use-case
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;

        form.setStatus(UIConstants.CONFIRM);
        aRequest.setAttribute("status", "confirm");

        return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	CaseplanForm form = (CaseplanForm) aForm;
    	String forwardStr = UIConstants.BACK;
    	if ("GOALFlOW".equals(form.getSecondaryAction() ) )
    	{
    		forwardStr = "goalBack";
    	}
    	form.setSecondaryAction(UIConstants.EMPTY_STRING);
    	return aMapping.findForward(forwardStr);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected void addButtonMapping(Map keyMap)
    {

        keyMap.put("button.saveContinue", "saveContinue");
        keyMap.put("button.finish", "saveContinue");

    }

}