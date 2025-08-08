package ui.juvenilecase.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.casefile.CreateActivityEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRulesEvent;
import messaging.rules.SaveTransferCasefileRulesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileRulesControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory;
import ui.juvenilecase.Factory.SupervisionRuleRequestEventFactory;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.SupervisionConditionForm;
import ui.juvenilecase.form.SupervisionRulesForm;

public class SubmitCasefileRulesTransferAction extends JIMSBaseAction{
	/**
	 * @roseuid 463BA574000E
	 */
	public SubmitCasefileRulesTransferAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 463A437A03DB
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
		//get the casefile record using selected supervision number
		Iterator casefiles = conditionForm.getActiveCasefiles().iterator();
		while(casefiles.hasNext())
		{
			JuvenileCasefileSearchResponseEvent casefile = (JuvenileCasefileSearchResponseEvent) casefiles.next();
			if(casefile.getSupervisionNum().equals(conditionForm.getSelectedCasefile()))
			{
				conditionForm.setRulesTransferCaseile(casefile);
				break;
			}
		}
		conditionForm.setAction(UIConstants.SUMMARY);
		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
		
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward returnToRulesList( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		
		//SupervisionRulesForm supervisionRulesForm = (SupervisionRulesForm)aForm;
		SupervisionRulesForm supervisionRulesForm = (SupervisionRulesForm) aRequest.getSession().getAttribute("supervisionRulesForm");
		supervisionRulesForm.clearAll();

		ISupervisionRuleRequestEventFactory eventFactory = new SupervisionRuleRequestEventFactory();
		JuvenileCasefileForm headerForm = UIJuvenileCasefileSupervisionRulesHelper.getHeaderForm(aRequest);
		GetJuvenileCasefileSupervisionRulesEvent event = 
				eventFactory.buildGetJuvenileCasefileSupervisionRulesEvent(headerForm.getSupervisionNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		Collection<RuleResponseEvent> rules = MessageUtil.compositeToCollection(
				(CompositeResponse)dispatch.getReply(), RuleResponseEvent.class);
		if( rules != null )
		{
			for( RuleResponseEvent ruleResponseEvent : rules )
			{
				Rule rule = UIJuvenileCasefileSupervisionRulesHelper.getBasicRule(ruleResponseEvent);
				supervisionRulesForm.addAssignedRulesList(rule);
			}
		}
       	supervisionRulesForm.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
       	HttpSession session = aRequest.getSession();
		session.setAttribute("supervisionRulesForm", supervisionRulesForm);
		return( aMapping.findForward( UIConstants.RETURN_SUCCESS ) ) ;

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 463A437A03DB
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
		JuvenileCasefileForm casefileForm = UIJuvenileCaseworkHelper.getHeaderForm(aRequest);
		SaveTransferCasefileRulesEvent saveEvent = (SaveTransferCasefileRulesEvent)EventFactory.getInstance(JuvenileRulesControllerServiceNames.SAVETRANSFERCASEFILERULES);
		Iterator rulesIter = conditionForm.getSelectedRules().iterator();
		String ruleIDs = "";
		while(rulesIter.hasNext())
		{
			Rule transferRule = (Rule)rulesIter.next();
			saveEvent.setRuleID(transferRule.getRuleId());
			saveEvent.setSupervisionNum(conditionForm.getSelectedCasefile());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(saveEvent);
			if(ruleIDs!="")
				ruleIDs = ruleIDs + ", ";
			ruleIDs = ruleIDs + transferRule.getRuleId();
			
		}
		conditionForm.setAction(UIConstants.CONFIRM);	
		
		String activityComments = "Rule ID(s) [" + ruleIDs +"] transferred from supervision # [" + casefileForm.getSupervisionNum() +"] to [" + conditionForm.getSelectedCasefile() + "]." ;
		
		CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);

		reqEvent.setSupervisionNumber(casefileForm.getSupervisionNum());
		reqEvent.setActivityDate(DateUtil.getCurrentDate());
		reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_SYSTEM_GENERATED);
		reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_INFO_TRANSFER);
		reqEvent.setActivityCodeId(ActivityConstants.SUPERVISION_RULES_TRANSFER);
		reqEvent.setComments(activityComments);
		MessageUtil.postRequest(reqEvent);
		
		return( aMapping.findForward( UIConstants.SUCCESS) ) ;

    }
	
	
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.next", "next" ) ;
		keyMap.put( "button.returnToCasefileRules", "returnToRulesList" ) ;
	}
}
