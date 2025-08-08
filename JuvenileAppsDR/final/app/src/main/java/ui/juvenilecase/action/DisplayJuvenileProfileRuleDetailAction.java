/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayCasefileSupervisionListAction
 *
 * Date:    2005-11-22
 *
 * Author:  Uma Gopinath
 * Email:   ugopinath@jims.hctx.net
 */

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RuleDetailResponseEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRuleDetailsEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory;
import ui.juvenilecase.Factory.SupervisionRuleRequestEventFactory;
import ui.juvenilecase.form.SupervisionRulesForm;

/**
 * @author ugopinath
 *  
 */
public class DisplayJuvenileProfileRuleDetailAction extends Action
{

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = null;
        Rule selectedRule = null;

        SupervisionRulesForm supRulesForm = (SupervisionRulesForm) aForm;
        String selectedValue = supRulesForm.getSelectedValue();
        supRulesForm.setSelectedRule(null);

        ISupervisionRuleRequestEventFactory eventFactory = new SupervisionRuleRequestEventFactory();
        GetJuvenileCasefileSupervisionRuleDetailsEvent event = eventFactory
                .buildGetJuvenileCasefileSupervisionRuleDetailsEvent(selectedValue);

        CompositeResponse response = MessageUtil.postRequest(event);
        
        RuleDetailResponseEvent ruleResponse = (RuleDetailResponseEvent) MessageUtil.filterComposite(response,
                RuleDetailResponseEvent.class);

        // Populate rule found
        if (response != null)
        {
            selectedRule = UIJuvenileCasefileSupervisionRulesHelper.getDetailRule(ruleResponse);
            supRulesForm.setSelectedRule(selectedRule);
        }

       	supRulesForm.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));

        if (selectedRule != null)
        {
            forward = aMapping.findForward(UIConstants.SUCCESS);
            aRequest.setAttribute("action","display");
        }
        return forward;
    }

}