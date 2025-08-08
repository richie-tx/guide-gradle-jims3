package ui.juvenilecase.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileCasefileRulesResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.rules.GetJuvenileSupervisionRulesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.Casefile;
import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory;
import ui.juvenilecase.Factory.SupervisionRuleRequestEventFactory;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.JuvenileProfileSupervisionRulesForm;

/**
 * 
 * @author Anand Thorat
 */
public class DisplayJuvenileProfileRuleListAction extends org.apache.struts.action.Action
{

    // ------------------------------------------------------------------------
    // --- constructor ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42B88AAF0186
     */
    public DisplayJuvenileProfileRuleListAction()
    {

    } //end of

    // ui.juvenilecase.action.DisplayJuvenileProfileCasefileListAction.DisplayJuvenileProfileCasefileListAction

    // ------------------------------------------------------------------------
    // --- methods ---
    // ------------------------------------------------------------------------

    /**
     * 
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42B888920198
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileProfileSupervisionRulesForm supervisionRulesForm = (JuvenileProfileSupervisionRulesForm) aForm;
        supervisionRulesForm.clearAll();
        ActionForward forward = null;
        ISupervisionRuleRequestEventFactory eventFactory = new SupervisionRuleRequestEventFactory();

        JuvenileProfileForm form = UIJuvenileHelper.getHeaderForm(aRequest);
        String juvenileId = form.getJuvenileNum();
        Collection casefiles = supervisionRulesForm.getCasefiles();

        GetJuvenileSupervisionRulesEvent event = eventFactory.buildGetJuvenileSupervisionRulesEvent(juvenileId);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(event);

        List casefileEvents = MessageUtil.postRequestListFilter(event, JuvenileCasefileRulesResponseEvent.class);

        Iterator casefileIter = casefileEvents.iterator();
        while (casefileIter.hasNext())
        {
            JuvenileCasefileRulesResponseEvent casefileEvent = (JuvenileCasefileRulesResponseEvent) casefileIter.next();
            Casefile casefile = new Casefile();
            casefiles.add(casefile);

            casefile.setJuvenileNum(juvenileId);
            casefile.setSupervisionNum(casefileEvent.getSupervisionNum());
            Collection rules = casefile.getRules();

            Collection ruleEvents = casefileEvent.getRules();
            for (Iterator i = ruleEvents.iterator(); i.hasNext();)
            {
                RuleResponseEvent ruleResponseEvent = (RuleResponseEvent) i.next();
                Rule rule = UIJuvenileCasefileSupervisionRulesHelper.getBasicRule(ruleResponseEvent);
                rules.add(rule);
            }
        }

        forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }

    /**
     * 
     * @param response
     *            The response.
     * @return The collection.
     */
    private Collection fetchCaseFiles(CompositeResponse response)
    {
        Map map = MessageUtil.groupByTopic(response);
        Collection profiles = (Collection) map.get(PDJuvenileConstants.JUVENILE_PROFILE_CASEFILE_LIST_TOPIC);
        profiles = MessageUtil.processEmptyCollection(profiles);
        return profiles;

    } //end of ui.juvenilecase.action.DisplayJuvenileProfileCasefileListAction.fetchCaseFiles

} // end DisplayJuvenileProfileCasefileListAction
