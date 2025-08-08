//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\HandleConditionAssociateToCourtPolicyAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import messaging.supervisionoptions.GetCourtPoliciesEvent;
import messaging.supervisionoptions.GetDepartmentPoliciesEvent;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import ui.supervision.SupervisionOptions.form.AssociateBean;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class AssociationHelper
{

	/**
	 * @param aForm
	 */
	public static void filterConditionCourtPolicies( SupervisionConditionForm form )
	{
		AssociateBean associateBean = form.getAssocSearchCriteria();

		// Clear current query
		Map asscBeansMap = form.getAssocSearchResultsMap();
		asscBeansMap.clear();
		form.getAssocSearchResults().clear();

		// Query 
		GetCourtPoliciesEvent postEvt = new GetCourtPoliciesEvent();
		postEvt.setAgencyId( form.getAgencyId() );
		postEvt.setName( associateBean.getObjName() );
		postEvt.setGroup1( associateBean.getGroup1Id() );
		postEvt.setGroup2( associateBean.getGroup2Id() );
		postEvt.setGroup3( associateBean.getGroup3Id() );
		postEvt.setCourts( form.getSelectedCourtIds() );
		postEvt.setSearchForAssociation(true);
		
		// post event to fetch court policies
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(postEvt);
		
		Collection searchResults =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CourtPolicyResponseEvent.class );
		
		// Update results list
		int count = 0;
		Iterator policies = searchResults.iterator();
		while(policies.hasNext()){
			CourtPolicyResponseEvent cpre = (CourtPolicyResponseEvent)policies.next();
			AssociateBean asscBean = new AssociateBean();
			asscBean.setGroup1Id(cpre.getGroup1Id());
			asscBean.setGroup1Name(cpre.getGroup1Name());
			asscBean.setGroup2Id(cpre.getGroup2Id());
			asscBean.setGroup2Name(cpre.getGroup2Name());
			asscBean.setGroup3Id(cpre.getGroup3Id());
			asscBean.setGroup3Name(cpre.getGroup3Name());
			asscBean.setObjId(cpre.getPolicyId());
			asscBean.setObjName(cpre.getName());
			
			if ( ! form.getAssociatedCourtPolicies().contains(asscBean) )
			{
				form.insertAssocSearchResult(asscBean);
				// add this bean in a map for faster searching
				asscBeansMap.put(asscBean.getObjId(), asscBean);
				count++;
			}
		}
		
		String resultStr = "" + count + " results for Group 1: " + associateBean.getGroup1Name();
		if ( associateBean.getGroup2Id() != null && associateBean.getGroup2Id().length() > 0)
		{
			
			String groupName = getGroupNameFromGroupId(form.getGroups(), associateBean.getGroup2Id());
			if(groupName != null)
			{
				resultStr += " Group 2: " + groupName;
			}
		}
		form.setSearchResultText( resultStr );
	}

	/**
	 * @param aForm
	 */
	public static void filterConditionDepartmentPolicies( SupervisionConditionForm form )
	{
		AssociateBean associateBean = form.getAssocSearchCriteria();

		// Clear current query
		Map asscBeansMap = form.getAssocSearchResultsMap();
		asscBeansMap.clear();
		form.getAssocSearchResults().clear();

		// Query 
		GetDepartmentPoliciesEvent postEvt = new GetDepartmentPoliciesEvent();
		postEvt.setAgencyId( form.getAgencyId() );
		postEvt.setName( associateBean.getObjName() );
		postEvt.setGroup1( associateBean.getGroup1Id() );
		postEvt.setGroup2( associateBean.getGroup2Id() );
		postEvt.setGroup3( associateBean.getGroup3Id() );
		postEvt.setCourts( form.getSelectedCourtIds() );
		postEvt.setSearchForAssociation(true);

		// post event to fetch court policies
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(postEvt);
		
		Collection searchResults =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), DepartmentPolicyResponseEvent.class );
		
		// Update results list
		int count = 0;
		Iterator policies = searchResults.iterator();
		while(policies.hasNext()){
			DepartmentPolicyResponseEvent cpre = (DepartmentPolicyResponseEvent)policies.next();
			AssociateBean asscBean = new AssociateBean();
			asscBean.setGroup1Id(cpre.getGroup1Id());
			asscBean.setGroup1Name(cpre.getGroup1Name());
			asscBean.setGroup2Id(cpre.getGroup2Id());
			asscBean.setGroup2Name(cpre.getGroup2Name());
			asscBean.setGroup3Id(cpre.getGroup3Id());
			asscBean.setGroup3Name(cpre.getGroup3Name());
			asscBean.setObjId(cpre.getAgencyPolicyId());
			asscBean.setObjName(cpre.getName());
			
			if ( ! form.getAssociatedDeptPolicies().contains(asscBean) )
			{
				form.insertAssocSearchResult(asscBean);
				// add this bean in a map for faster searching
				asscBeansMap.put(asscBean.getObjId(), asscBean);
				count++;
			}
		}
		
		String resultStr = "" + count + " results for Group 1: " + associateBean.getGroup1Name();
		
		if ( associateBean.getGroup2Id() != null && associateBean.getGroup2Id().length() > 0)
		{
			
			String groupName = getGroupNameFromGroupId(form.getGroups(), associateBean.getGroup2Id());
			if(groupName != null)
			{
				resultStr += " Group 2: " + groupName;
			}
		}
		
		form.setSearchResultText( resultStr );
	}


	/**
	 * @param aForm
	 */
	public static void filterCourtPolicyCondition( CourtPolicyForm form )
	{
		AssociateBean associateBean = form.getAssocSearchCriteria();

		// Clear current query
		Map asscBeansMap = form.getAssocSearchResultsMap();
		asscBeansMap.clear();
		form.getAssocSearchResults().clear();

		// Query 
		GetSupervisionConditionsEvent postEvt = new GetSupervisionConditionsEvent();
		postEvt.setAgencyId( form.getAgencyId() );
		postEvt.setName( associateBean.getObjName() );
		postEvt.setGroup1( associateBean.getGroup1Id() );
		postEvt.setGroup2( associateBean.getGroup2Id() );
		postEvt.setGroup3( associateBean.getGroup3Id() );
		postEvt.setCourts( form.getSelectedCourtIds() );
		postEvt.setSearchForAssociation(true);

		// post event to fetch court policies
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(postEvt);
		
		Collection searchResults =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), ConditionResponseEvent.class );
		
		// Update results list
		int count = 0;
		Iterator policies = searchResults.iterator();
		while(policies.hasNext()){
			ConditionResponseEvent cpre = (ConditionResponseEvent)policies.next();
			AssociateBean asscBean = new AssociateBean();
			asscBean.setGroup1Id(cpre.getGroup1Id());
			asscBean.setGroup1Name(cpre.getGroup1Name());
			asscBean.setGroup2Id(cpre.getGroup2Id());
			asscBean.setGroup2Name(cpre.getGroup2Name());
			asscBean.setGroup3Id(cpre.getGroup3Id());
			asscBean.setGroup3Name(cpre.getGroup3Name());
			asscBean.setObjId(cpre.getConditionId());
			asscBean.setObjName(cpre.getName());
			
			if ( ! form.getAssociatedConditions().contains(asscBean) )
			{
				form.insertAssocSearchResult(asscBean);
				// add this bean in a map for faster searching
				asscBeansMap.put(asscBean.getObjId(), asscBean);
				count++;
			}
		}
		
		String resultStr = "" + count + " results for Group 1: " + associateBean.getGroup1Name();
		if ( associateBean.getGroup2Id() != null && associateBean.getGroup2Id().length() > 0)
		{
					
			String groupName = getGroupNameFromGroupId(form.getGroups(), associateBean.getGroup2Id());
			if(groupName != null)
			{
				resultStr += " Group 2: " + groupName;
			}
		}
		form.setSearchResultText( resultStr );
	}


	/**
	 * @param aForm
	 */
	public static void filterDepartmentPolicyCondition( DepartmentPolicyForm form )
	{
		AssociateBean associateBean = form.getAssocSearchCriteria();

		// Clear current query
		Map asscBeansMap = form.getAssocSearchResultsMap();
		asscBeansMap.clear();
		form.getAssocSearchResults().clear();

		// Query 
		GetSupervisionConditionsEvent postEvt = new GetSupervisionConditionsEvent();
		postEvt.setAgencyId( form.getAgencyId() );
		postEvt.setName( associateBean.getObjName() );
		postEvt.setGroup1( associateBean.getGroup1Id() );
		postEvt.setGroup2( associateBean.getGroup2Id() );
		postEvt.setGroup3( associateBean.getGroup3Id() );
		postEvt.setCourts( form.getSelectedCourtIds() );
		postEvt.setSearchForAssociation(true);

		// post event to fetch court policies
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(postEvt);
		
		Collection searchResults =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), ConditionResponseEvent.class );
		
		// Update results list
		int count = 0;
		Iterator policies = searchResults.iterator();
		while(policies.hasNext()){
			ConditionResponseEvent cpre = (ConditionResponseEvent)policies.next();
			AssociateBean asscBean = new AssociateBean();
			asscBean.setGroup1Id(cpre.getGroup1Id());
			asscBean.setGroup1Name(cpre.getGroup1Name());
			asscBean.setGroup2Id(cpre.getGroup2Id());
			asscBean.setGroup2Name(cpre.getGroup2Name());
			asscBean.setGroup3Id(cpre.getGroup3Id());
			asscBean.setGroup3Name(cpre.getGroup3Name());
			asscBean.setObjId(cpre.getConditionId());
			asscBean.setObjName(cpre.getName());
			
						
			
			if ( ! form.getAssociatedConditions().contains(asscBean) )
			{
				form.insertAssocSearchResult(asscBean);
				// add this bean in a map for faster searching
				asscBeansMap.put(asscBean.getObjId(), asscBean);
				count++;
			}
		}
		
		String resultStr = "" + count + " results for Group 1: " + associateBean.getGroup1Name();
		
		if ( associateBean.getGroup2Id() != null && associateBean.getGroup2Id().length() > 0)
		{
			
			String groupName = getGroupNameFromGroupId(form.getGroups(), associateBean.getGroup2Id());
			if(groupName != null)
			{
				resultStr += " Group 2: " + groupName;
			}
		}
		form.setSearchResultText( resultStr );
	}


	/**
	 * @param aForm
	 */
	public static void addSelectedConditionCourtPolicies( SupervisionConditionForm form )
	{
		String[] objIds = form.getSelectInd();
		Map asscBeansMap = form.getAssocSearchResultsMap();
		if(objIds != null && objIds.length > 0 && asscBeansMap != null)
		{
			for(int i = 0; i < objIds.length; i++){
				AssociateBean asscBean = (AssociateBean)asscBeansMap.get(objIds[i]);
				if(asscBean != null)
				{
					form.insertAssociatedCourtPolicy(asscBean);
					// remove it from the search result
					form.removeAssocSearchResult(asscBean);
				}
			}
		}
	}

	/**
	 * @param aForm
	 */
	public static void addSelectedConditionDepartmentPolicies( SupervisionConditionForm form )
	{
		String[] objIds = form.getSelectInd();
		Map asscBeansMap = form.getAssocSearchResultsMap();
		if(objIds != null && objIds.length > 0 && asscBeansMap != null)
		{
			for(int i = 0; i < objIds.length; i++){
				AssociateBean asscBean = (AssociateBean)asscBeansMap.get(objIds[i]);
				if(asscBean != null)
				{
					form.insertAssociatedDeptPolicy(asscBean);
					// remove it from the search result
					form.removeAssocSearchResult(asscBean);
				}
			}
		}
	}

	
	/**
	 * @param aForm
	 */
	public static void addSelectedCourtPolicy( CourtPolicyForm form )
	{
		String[] objIds = form.getSelectInd();
		Map asscBeansMap = form.getAssocSearchResultsMap();
		if(objIds != null && objIds.length > 0 && asscBeansMap != null)
		{
			for(int i = 0; i < objIds.length; i++){
				AssociateBean asscBean = (AssociateBean)asscBeansMap.get(objIds[i]);
				
				if(asscBean != null)
				{
					form.insertAssociatedCondition(asscBean);
					// remove it from the search result
					form.removeAssocSearchResult(asscBean);
				}
			}
		}
	}
	
	/**
	 * @param aForm
	 */
	public static void addSelectedDepartmentPolicy( DepartmentPolicyForm form )
	{
		String[] objIds = form.getSelectInd();
		Map asscBeansMap = form.getAssocSearchResultsMap();
		if(objIds != null && objIds.length > 0 && asscBeansMap != null)
		{
			for(int i = 0; i < objIds.length; i++){
				AssociateBean asscBean = (AssociateBean)asscBeansMap.get(objIds[i]);
				
				if(asscBean != null)
				{
					form.insertAssociatedCondition(asscBean);
					// remove it from the search result
					form.removeAssocSearchResult(asscBean);
				}
			}
		}
	}
	
	private static String getGroupNameFromGroupId( Collection groups, String groupId)
	{
		if( groups == null || groups.size() < 1)
		{
			return null;
		}
		else
		{
		
			for(Iterator iter = groups.iterator(); iter.hasNext();)
			{
				GroupResponseEvent gre = (GroupResponseEvent)iter.next();
				if(gre.getGroupId().equalsIgnoreCase(groupId))
				{
					return gre.getName();
				}
				else
				{
					String groupName = getGroupNameFromGroupId(gre.getSubgroups(), groupId);
					if(groupName != null)
						return groupName;
						
				}
			}
			return null;
		}
	}
	
}
