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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.rules.SaveJuvenileCasefileSupervisionRulesEvent;
import messaging.rules.SaveSupervisonRuleEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.SupervisionRulesForm;

/**
 * @author awidjaja
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class SubmitCasefileSupervisionRuleAction extends LookupDispatchAction
{
	/*
	 * 
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		aRequest.setAttribute("action","confirmation");
		JuvenileCasefileForm headerForm = UIJuvenileCasefileSupervisionRulesHelper.getHeaderForm(aRequest);
		SupervisionRulesForm form = (SupervisionRulesForm)aForm;
		SaveJuvenileCasefileSupervisionRulesEvent rulesEvent = new SaveJuvenileCasefileSupervisionRulesEvent();
		
		rulesEvent.setSupervisionNum(headerForm.getSupervisionNum());
		
		//populate SaveSupervisionRuleEvent with date from selectedRule
		ui.juvenilecase.Rule rule = form.getSelectedRule();
		SaveSupervisonRuleEvent saveSupervisonRuleEvent = new SaveSupervisonRuleEvent();
		saveSupervisonRuleEvent.setResolvedDesc(rule.getRuleLiteralResolved());
		saveSupervisonRuleEvent.setUnformattedDesc(UIUtil.removeXMLtags(rule.getRuleLiteralResolved(),true));
		saveSupervisonRuleEvent.setConditionId(rule.getConditionId());
		saveSupervisonRuleEvent.setStatusChanged(rule.hasStatusChanged());
		// set monitor freq id
		saveSupervisonRuleEvent.setMonitorFrequencyId(rule.getMonitorFreqId());
		// completion status	
		saveSupervisonRuleEvent.setCompletionStatusId(rule.getCompletionStatusId());
		// completion date
		saveSupervisonRuleEvent.setCompletionDate(rule.getCompletionDate());
		saveSupervisonRuleEvent.setAdditionalInformation(rule.getAdditionalInformation());
		// add rulesave request
		rulesEvent.addRequest(saveSupervisonRuleEvent);

		
		//Send PD Request Event
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rulesEvent);
		// Get PD Response Event	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		
		// Add record in activity table
		if(rule.hasStatusChanged()) 
		{
			ui.juvenilecase.Rule tRule = form.getSelectedRule() ;
			
			if( tRule != null )
			{
				String lit = checkString( UIUtil.removeXMLtags(form.getSelectedRule().getRuleLiteralResolved(),true)) ; //changed for Bug #39144
				String stat = checkString( form.getSelectedRule().getCompletionStatus() ) ;
				
				String comments = form.getSelectedRule().getAdditionalInformation() +
						"Rule Literal : " + lit + " Status : " + stat;

				UIJuvenileHelper.createActivity( headerForm.getSupervisionNum(), 
						ActivityConstants.MODIFY_SUPERVISION_RULES, comments);
			}
		}
		
		// Perform Error handling
		MessageUtil.processReturnException(response); 
		
		return( aMapping.findForward("success") );
	}

	/*
	 * 
	 */
	private String checkString( String str )
	{
		return( (str != null) ? str : "" ) ;		
	}
	
	/*
	 * non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.mainPage", "mainPage");
		return keyMap;
	}
}
