/*
 * Created on Nov 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.refvariable.ResolveReferencesEvent;
import messaging.supervisionoptions.SearchSupervisionConditionsDetailsEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.juvenilecase.Rule;
import ui.juvenilecase.UIJuvenileCasefileSupervisionRulesHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.Factory.ISupervisionRuleRequestEventFactory;
import ui.juvenilecase.Factory.SupervisionRuleRequestEventFactory;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.SupervisionConditionForm;
import ui.juvenilecase.form.SupervisionRulesForm;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayCasefileSupervisionConditionsAction extends LookupDispatchAction
{
	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
		if(conditionForm.getAction()!= null && 
				conditionForm.getAction().equals(UIConstants.CONFIRM))
		{
			conditionForm.setAction(UIConstants.SUMMARY);
		}
		
		// Setup referenceValues
		JuvenileCasefileForm headerForm = UIJuvenileCasefileSupervisionRulesHelper.getHeaderForm(aRequest);
		resolveReferences( conditionForm.getSelectedConditions(), headerForm.getSupervisionNum() );

		Collection rules = conditionForm.getRules();
		rules.clear();

		Collection<ConditionDetailResponseEvent> conditions = conditionForm.getSelectedConditions();
		if( conditions != null )
		{
			for( ConditionDetailResponseEvent condition : conditions )
			{
				Rule rule = UIJuvenileCasefileSupervisionRulesHelper.createRule( condition );
				rules.add(rule);
			}		
		}

		return aMapping.findForward("success");
	}

	/**
	 * Get the initial set of custom conditions 
	 */
	public ActionForward getCustomConditions(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
		if(conditionForm.getAction() != null && 
				conditionForm.getAction().equals(UIConstants.CONFIRM))
		{
			conditionForm.setAction(UIConstants.SUMMARY);
		}
		
		conditionForm.getSelectedConditions().clear();
		conditionForm.getConditions().clear();
		conditionForm.setGroup1Id( "" );
		conditionForm.setGroup2Id( "" );
		conditionForm.setGroup3Id( "" );
		
		return aMapping.findForward("customSuccess");
	}

	/**
	 * Get the initial set of custom conditions 
	 */
	public ActionForward goCustomConditions( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
		ActionForward forward = null;
		SupervisionRulesForm rulesForm = (SupervisionRulesForm) aRequest.getSession().getAttribute("supervisionRulesForm");
		Collection conditions = searchConditions(conditionForm, aRequest, false);

		if( conditions != null )
		{
			setConditions( conditions, conditionForm, rulesForm );
		}
		else
		{
			setConditions(new ArrayList(),conditionForm,rulesForm);
		}
		
		if( conditionForm.getConditions().size() > 0 )
		{
			forward = aMapping.findForward("customSuccess");
			conditionForm.setStandard(false);	
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
			saveErrors(aRequest, errors);
			forward = aMapping.findForward("customFailure");
		}
		
		return forward;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addCustomConditions( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
		String[] selectedIds = (String[])aRequest.getParameterValues("condSel");
		
		if(selectedIds == null || selectedIds.length < 1)
		{
			return aMapping.findForward("customSuccess");	
		}
		
		Collection items = conditionForm.getConditions(); 
		Map itemsIndex = indexConditions( items );
		Collection cart = conditionForm.getSelectedConditions(); 
			
		for ( int i = 0; i < selectedIds.length; i++ )
		{
			ConditionDetailResponseEvent cond =
					(ConditionDetailResponseEvent)itemsIndex.get(selectedIds[i]);
			items.remove( cond );
			cart.add( cond );
		}
		
		return aMapping.findForward("customSuccess");		
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward removeCustomConditions( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
		String[] selectedIds = (String[])aRequest.getParameterValues("condRem");
		
		if(selectedIds == null || selectedIds.length < 1)
		{
			return aMapping.findForward("customSuccess");	
		}
		
		Collection items = conditionForm.getConditions(); 
		Collection cart = conditionForm.getSelectedConditions(); 
		Map cartIndex = indexConditions( cart );
			
		for( int i = 0; i < selectedIds.length; i++ )
		{
			ConditionDetailResponseEvent cond = 
					(ConditionDetailResponseEvent)cartIndex.get(selectedIds[i]);
			cart.remove( cond );
			items.add( cond );
		}
		
		return aMapping.findForward("customSuccess");		
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4278C9BB0044
	 */
	public ActionForward getStandardConditions(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = null;
		SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;

		if(conditionForm.getAction()!= null && 
				conditionForm.getAction().equals(UIConstants.CONFIRM))
		{
			conditionForm.setAction(UIConstants.SUMMARY);
		}
		
		SupervisionRulesForm rulesForm = (SupervisionRulesForm) aRequest.getSession().getAttribute("supervisionRulesForm");
		
		conditionForm.getSelectedConditions().clear();
		conditionForm.getConditions().clear();
		
		Collection conditions = searchConditions(conditionForm, aRequest, true);
		if( conditions != null )
		{
			setConditions( conditions, conditionForm, rulesForm );
			// Move item to the selected collection.
			conditionForm.getSelectedConditions().addAll( conditionForm.getConditions() );
			conditionForm.getConditions().clear();
		}
		
		if( conditionForm.getSelectedConditions().size() > 0 )
		{
			conditionForm.setStandard(true);
			return next( aMapping, aForm, aRequest, aResponse );
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
			saveErrors(aRequest, errors);
			forward = aMapping.findForward("standardFailure");

		}
		return forward;
	}


	/**
	 * 
	 * @param conditions The conditions to add.
	 * @param conditionForm The for to add tehm too.
	 * @param rulesForm The form containing the exiting conditions that need to be removed 
	 * from the 'add list'.
	 */
	private void setConditions(Collection<ConditionDetailResponseEvent> conditions, 
			SupervisionConditionForm conditionForm, SupervisionRulesForm rulesForm )
	{
		Map ruleIndex = indexRules(rulesForm.getAssignedRulesList());
		Map cartIndex = indexConditions(conditionForm.getSelectedConditions());

		conditionForm.getConditions().clear();
		for( ConditionDetailResponseEvent conditionResponse : conditions )
		{
			if( ! ruleIndex.containsKey(conditionResponse.getConditionId()) &&
					! cartIndex.containsKey(conditionResponse.getConditionId()) )
			{
				conditionForm.addCondition(conditionResponse);
			}
			else if(ruleIndex.containsKey(conditionResponse.getConditionId()) && 
					!cartIndex.containsKey(conditionResponse.getConditionId()))
			{
				Rule myRule = (Rule)ruleIndex.get( conditionResponse.getConditionId() );
				if(myRule.getCompletionStatusId().equals(UIConstants.RULE_STATUS_INACTIVE))
				{
					conditionForm.addCondition(conditionResponse);
				}
			}
		}// for
	}
	
	/*
	 * @param conditionForm
	 * @param aRequest
	 * @param standard
	 * @return
	 */
	private Collection searchConditions(SupervisionConditionForm conditionForm, 
			HttpServletRequest aRequest, boolean standard)
	{
		ISupervisionRuleRequestEventFactory eventFactory = new SupervisionRuleRequestEventFactory();
		JuvenileCasefileForm headerForm = UIJuvenileCasefileSupervisionRulesHelper.getHeaderForm(aRequest);

		SearchSupervisionConditionsDetailsEvent event =
			eventFactory.buildSearchSupervisionConditionsDetailsEvent(
				conditionForm, headerForm.getSupervisionTypeId(), standard);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(response);
		Collection conditions = (Collection) dataMap.get(PDJuvenileCaseConstants.CONDITION_DETAIL_TOPIC);
		
		return conditions;
	}

	/*
	 * @param rules
	 * @return
	 */
	private Map indexRules( Collection<Rule> rules )
	{
		Map index = new HashMap();
		
		if( rules != null )
		{
			for( Rule rule : rules )
			{
				if( index.containsKey(rule.getConditionId()))
				{
					Rule myRule = (Rule)index.get(rule.getConditionId());
					
					// don't put inactive rules over any other types of rules status on the same condition
					// that way if there is an inactive its the only incactive.			
					if( myRule.getCompletionStatusId()!= null && 
							myRule.getCompletionStatusId().equals(UIConstants.RULE_STATUS_INACTIVE))
					{
						index.put( rule.getConditionId(), rule );
					}
				}
				else
				{
					index.put( rule.getConditionId(), rule );
				}
			}
		}
		
		return index;
	}

	/*
	 * @param conditions
	 * @return
	 */
	private Map indexConditions( Collection<ConditionDetailResponseEvent> conditions )
	{
		Map index = new HashMap();

		if( conditions != null )
		{
			for( ConditionDetailResponseEvent cond : conditions)
			{
				try
				{
					index.put( cond.getConditionId(), cond );
				}
				catch( Exception e )
				{
				}
			}
		}
		
		return index;
	}
	

	/*
	 * @param conditions
	 * @param supervisionNum
	 */
	private void resolveReferences( 
			Collection<ConditionDetailResponseEvent> conditions, String supervisionNum )
	{
		ResolveReferencesEvent event = new ResolveReferencesEvent();
		
		event.setContextName( "casefile" );
		event.setProperty( "casefileId", supervisionNum );
		event.setCasefileId(supervisionNum);
		
		// Collect the refernese names we need to resolve.
		if( conditions != null )
		{
			for( ConditionDetailResponseEvent cond : conditions )
			{
				Collection<VariableElementResponseEvent> varElems = cond.getVariableElements() ;
				for( VariableElementResponseEvent var : varElems )
				{
					if ( var.isReference() )
					{
						event.addReferenceName( var.getName() );
					}
				}
			}
		}

		// Resolve values and build index.
		if ( event.getReferenceNames().size() > 0 )
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);

			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Map referenceValues = new HashMap();

			Collection<VariableElementResponseEvent> responses = 
					MessageUtil.compositeToCollection(response, VariableElementResponseEvent.class);
			if( responses != null && responses.size() > 0 )
			{
				for( VariableElementResponseEvent myRespEvt : responses )
				{
					referenceValues.put(myRespEvt.getName(),myRespEvt.getValue());
				}

				for( ConditionDetailResponseEvent cond : conditions )
				{
					Collection<VariableElementResponseEvent> varElems = cond.getVariableElements() ;
					if( varElems != null )
					{
						for( VariableElementResponseEvent var : varElems )
						{
							if ( var.isReference() )
							{
								if(referenceValues.containsKey(var.getName())){
									var.setValue( referenceValues.get(var.getName()).toString() );
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4278C9BB0044
	 */
	public ActionForward transferRules(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = null;
		SupervisionRulesForm rulesForm = (SupervisionRulesForm) aRequest.getSession().getAttribute("supervisionRulesForm");
		String[] selectedRules=aRequest.getParameterValues("selectedRulesToTransfer");
		Iterator rulesIter = rulesForm.getAssignedRulesList().iterator();
		ArrayList selectedRulesList = new ArrayList();
		 for(int i=0;i<selectedRules.length;i++)
		 {
			 rulesIter = rulesForm.getAssignedRulesList().iterator();
			 while(rulesIter.hasNext())
			 {
				 Rule assignedRule = (Rule) rulesIter.next();
				 if(assignedRule != null && (assignedRule.getRuleId().equals(selectedRules[i])))
				 {
					 selectedRulesList.add(assignedRule);
					 break;
				 }
			 }
		 }
		
		
		JuvenileCasefileForm casefileForm = UIJuvenileCaseworkHelper.getHeaderForm(aRequest);
		
		//get casefiles list so that user can choose which one to operate on
        SearchJuvenileCasefilesEvent searchEvent = (SearchJuvenileCasefilesEvent) EventFactory
                .getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

        searchEvent.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);

        searchEvent.setJuvenileNum(casefileForm.getJuvenileNum());

        List casefiles = MessageUtil.postRequestListFilter(searchEvent, JuvenileCasefileSearchResponseEvent.class);

        List activeCasefiles = new ArrayList();
        
        Iterator iter = casefiles.iterator();
		while (iter.hasNext())
		{
			JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) iter.next();
//			if (casefileResp.getCaseStatus().equals(PDJuvenileCaseConstants.CASESTATUS_ACTIVE_DESCRIPTION)) {
// 04/23/2012 Activity #3232 -- allow all casefile status except Closed and Closed Approved 
			if ( (casefileResp.getCaseStatus().equals(PDJuvenileCaseConstants.CASESTATUS_ACTIVE) || casefileResp.getCaseStatus().equals("ACTIVE"))&&
				!casefileResp.getSupervisionNum().equals(casefileForm.getSupervisionNum())) {
					activeCasefiles.add(casefileResp);
			}
		}        
		
       Collections.sort(activeCasefiles);
       
       SupervisionConditionForm conditionForm = (SupervisionConditionForm) aForm;
       conditionForm.setSelectedRules(selectedRulesList);
       conditionForm.setActiveCasefiles(activeCasefiles);
		return aMapping.findForward("transferSuccess");
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.next", "next");
		keyMap.put("button.assignStandardRules", "getStandardConditions");
		keyMap.put("button.assignCustomRules", "getCustomConditions");
		keyMap.put("button.goCustomRules", "goCustomConditions");
		keyMap.put("button.addCustomRules", "addCustomConditions");
		keyMap.put("button.removeCustomRules", "removeCustomConditions");
		keyMap.put("button.transferRules", "transferRules");
		return keyMap;
	}
}
