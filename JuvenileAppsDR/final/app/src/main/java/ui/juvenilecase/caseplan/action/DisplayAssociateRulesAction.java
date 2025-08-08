package ui.juvenilecase.caseplan.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RuleResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * 
 * @author awidjaja
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DisplayAssociateRulesAction extends LookupDispatchAction
{
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     */
    public ActionForward associateGoalToRules(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CaseplanForm form = (CaseplanForm) aForm;
        Collection coll = form.getCurrentGoalInfo().getAssociatedRules();
        Iterator ite = coll.iterator();
        String[] selectedRules = new String[coll.size()];
        int counter = 0;
        while (ite.hasNext())
        {
            RuleResponseEvent ruleEvt = (RuleResponseEvent) ite.next();
            selectedRules[counter++] = ruleEvt.getRuleId();
        }

        form.getCurrentGoalInfo().setSelectedRules(selectedRules);
        // get all the rules for the casefile... to present a list to choose from.
        List allRules = UIJuvenileCaseplanHelper.getAllRulesForCasefile(form.getCasefileId());
        // mark all the rules that are already associated.
        UIJuvenileCaseplanHelper.markCurrentlyAssociatedRules(allRules, selectedRules);
        form.getCurrentGoalInfo().setAssociatedRules(allRules);
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
        return aMapping.findForward(UIConstants.BACK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.associateGoalToRules", "associateGoalToRules");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

}