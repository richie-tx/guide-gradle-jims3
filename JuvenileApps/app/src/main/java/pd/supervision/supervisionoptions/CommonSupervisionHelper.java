/*
 * Created on Aug 15, 2005
 *
 */
package pd.supervision.supervisionoptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import naming.PDConstants;

import pd.codetable.Code;
import pd.codetable.SimpleCodeTableHelper;
import pd.criminalcase.CriminalCase;
import pd.supervision.Court;
import pd.supervision.Group;
import pd.supervision.supervisionorder.SupervisionOrder;
import messaging.info.reply.CountInfoMessage;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.utilities.CollectionUtil;

/**
 * @author bschwartz
 *
 */
public class CommonSupervisionHelper
{
	public static Map buildCourtsMap(){
		Map map = new HashMap();
		Iterator iter = Court.findAll();
		if (iter != null && iter.hasNext()){
			Court court = null;
			while (iter.hasNext()){
				court = (Court) iter.next();
				map.put(court.getOID().toString(), court);
			}
		}
		return map;
	}
	public static ConditionResponseEvent getConditionResponseEvent( Condition cond )
	{
		ConditionResponseEvent reply = new ConditionResponseEvent();

		reply.setConditionId(cond.getOID().toString());
		reply.setAgencyId(cond.getAgencyId());
		reply.setName(cond.getName());
					
		Group group = cond.getGroup();
		if(group != null){			
			Group[] groups = group.getGroupList();
			if (groups[0] != null)
			{
				reply.setGroup1Id(groups[0].getOID().toString());
				reply.setGroup1Name(groups[0].getGroupName());
			}
			if (groups[1] != null)
			{
				reply.setGroup2Id(groups[1].getOID().toString());
				reply.setGroup2Name(groups[1].getGroupName());
			}
			if (groups[2] != null)
			{
				reply.setGroup3Id(groups[2].getOID().toString());
				reply.setGroup3Name(groups[2].getGroupName());
			}
		}
		
		reply.setStandard(cond.getIsStandard());
		reply.setEffectiveDate(cond.getEffectiveDate());
		reply.setInactiveDate(cond.getInactiveDate());
		reply.setStatus(cond.getStatus());
		reply.setDescription(cond.getDescription());
		
		return reply;
	}
	
	public static ConditionResponseEvent getSpecialConditionResponseEvent( Condition cond )
	{
		ConditionResponseEvent reply = new ConditionResponseEvent();

		reply.setConditionId(cond.getOID().toString());
		reply.setAgencyId(cond.getAgencyId());
		reply.setName(cond.getName());
		
		Group group = cond.getGroup();
		if(group != null){			
			Group[] groups = group.getGroupList();
			if (groups!=null && groups[0] != null)
			{
				reply.setGroup1Id(groups[0].getOID().toString());
				reply.setGroup1Name(groups[0].getGroupName());
			}
			if (groups!=null && groups.length>=2 && groups[1] != null)
			{
				reply.setGroup2Id(groups[1].getOID().toString());
				reply.setGroup2Name(groups[1].getGroupName());
			}
			if (groups!=null && groups.length>=3 && groups[2] != null)
			{
				reply.setGroup3Id(groups[2].getOID().toString());
				reply.setGroup3Name(groups[2].getGroupName());
			}
		}

		reply.setStandard(cond.getIsStandard());
		reply.setEffectiveDate(cond.getEffectiveDate());
		reply.setStatus(cond.getStatus());
		reply.setArchived(cond.getIsArchived());
//		reply.setNotes(cond.getNotes());

		// get attributes from Order
//		SupervisionOrderCondition orderCond = SupervisionOrderCondition.findBySpecialCondition(cond.getOID().toString());
//		reply.setResolvedDescription(orderCond.getResolvedDescription());
		Iterator iterator = SupervisionOrder.findAllByCondition(cond.getOID().toString());
		// special Condition.. only one order for condition
		if(iterator.hasNext()){
			SupervisionOrder order = (SupervisionOrder)iterator.next();
			CriminalCase criminalCase = order.getCriminalCase();
			reply.setCdi(criminalCase.getCourtDivisionId());
			reply.setSuperviseeName(criminalCase.getDefendantName());
			reply.setCaseNum(criminalCase.getCaseNum());
			reply.setCourtId(order.getOrderCourtId());
			// get Court category from the code table
			Code courtCode = Code.find("CDI_COURTCTG", criminalCase.getCourtDivisionId()); 
			reply.setCourtCategory(courtCode.getDescription());
		}
		return reply;
	}

	public static CourtPolicyResponseEvent getCourtPolicyResponseEvent(CourtPolicy policy){
		CourtPolicyResponseEvent reply = new CourtPolicyResponseEvent();

		reply.setPolicyId(policy.getOID().toString());
		reply.setAgencyId(policy.getAgencyId());
		reply.setName(policy.getName());
					
		Group[] groups = policy.getGroup().getGroupList();
		if (groups[0] != null)
		{
			reply.setGroup1Id(groups[0].getOID().toString());
			reply.setGroup1Name(groups[0].getGroupName());
		}
		if (groups[1] != null)
		{
			reply.setGroup2Id(groups[1].getOID().toString());
			reply.setGroup2Name(groups[1].getGroupName());
		}
		if (groups[2] != null)
		{
			reply.setGroup3Id(groups[2].getOID().toString());
			reply.setGroup3Name(groups[2].getGroupName());
		}
		
		return reply;
	}
	
	public static DepartmentPolicyResponseEvent getDepartmentPolicyResponseEvent(AgencyPolicy policy){
		DepartmentPolicyResponseEvent reply = new DepartmentPolicyResponseEvent(); 
				
		reply.setAgencyPolicyId( policy.getOID().toString() );
		reply.setAgencyId( policy.getAgencyId() );
		reply.setName( policy.getName() );
			    	
		Group[] groups = policy.getGroup().getGroupList();
		if ( groups[0] != null )
		{
			reply.setGroup1Id(groups[0].getOID().toString());
			reply.setGroup1Name(groups[0].getGroupName());
		}
		if ( groups[1] != null )
		{
			reply.setGroup2Id(groups[1].getOID().toString());
			reply.setGroup2Name(groups[1].getGroupName());
		}
		if ( groups[2] != null )
		{
			reply.setGroup3Id(groups[2].getOID().toString());
			reply.setGroup3Name(groups[2].getGroupName());
		}
		
		return reply;
	}
	
	public static void postAssociatedAgencyPolicies(String conditionId){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		
		Iterator policies = CourtConditionAgencyPolicy.findAll("conditionId", conditionId);
	
		Set oidSet = new HashSet();
		
		while(policies.hasNext()){
		    CourtConditionAgencyPolicy courtConditionAgencyPolicy = (CourtConditionAgencyPolicy)policies.next();
			// This is a view and it can return duplicate policiess. Let's not pass duplicate policies to ui.
			if(!oidSet.contains(courtConditionAgencyPolicy.getAgencyPolicyId())){
				oidSet.add(courtConditionAgencyPolicy.getAgencyPolicyId());
			}
		}

		// retrieve all the CourtPolicies together
		if(oidSet.size() > 0){
			Iterator agencyPolicies = AgencyPolicy.findAllByIds(oidSet);
			while(agencyPolicies.hasNext()){
				// get AgencyPolicy
			    AgencyPolicy policy = (AgencyPolicy)agencyPolicies.next();

				DepartmentPolicyResponseEvent reply = new DepartmentPolicyResponseEvent();
				
				reply.setAgencyPolicyId(policy.getOID().toString());
				reply.setAgencyId(policy.getAgencyId());
				reply.setName(policy.getName());
							
				Group[] groups = policy.getGroup().getGroupList();
				if (groups[0] != null)
				{
					reply.setGroup1Id(groups[0].getOID().toString());
					reply.setGroup1Name(groups[0].getGroupName());
				}
				if (groups[1] != null)
				{
					reply.setGroup2Id(groups[1].getOID().toString());
					reply.setGroup2Name(groups[1].getGroupName());
				}
				if (groups[2] != null)
				{
					reply.setGroup3Id(groups[2].getOID().toString());
					reply.setGroup3Name(groups[2].getGroupName());
				}

				dispatch.postEvent(reply);
			}			
		}
		
//		// iterate through the list and get AgencyPolic for each CourtConditionCourtPolicy
//		while(policies.hasNext()){
//			CourtConditionAgencyPolicy courtConditionAgencyPolicy = (CourtConditionAgencyPolicy)policies.next();
//			// This is a view and it can return duplicate policiess. Let's not pass duplicate policies to ui.
//			if(!oidSet.contains(courtConditionAgencyPolicy.getAgencyPolicyId())){
//				oidSet.add(courtConditionAgencyPolicy.getAgencyPolicyId());
//				
//				// get CourtPolicy
//				AgencyPolicy policy = AgencyPolicy.find(courtConditionAgencyPolicy.getAgencyPolicyId());
//				DepartmentPolicyResponseEvent reply = new DepartmentPolicyResponseEvent();
//		
//				reply.setAgencyPolicyId(policy.getOID().toString());
//				reply.setAgencyId(policy.getAgencyId());
//				reply.setName(policy.getName());
//							
//				Group[] groups = policy.getGroup().getGroupList();
//				if (groups[0] != null)
//				{
//					reply.setGroup1Id(groups[0].getOID().toString());
//					reply.setGroup1Name(groups[0].getGroupName());
//				}
//				if (groups[1] != null)
//				{
//					reply.setGroup2Id(groups[1].getOID().toString());
//					reply.setGroup2Name(groups[1].getGroupName());
//				}
//				if (groups[2] != null)
//				{
//					reply.setGroup3Id(groups[2].getOID().toString());
//					reply.setGroup3Name(groups[2].getGroupName());
//				}
//		
//				dispatch.postEvent(reply);
//			}			
//		}
	}

	public static void postAssociatedCourtPolicies(String conditionId){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		
		Iterator policies = CourtConditionCourtPolicy.findAll("conditionId", conditionId);

		Set oidSet = new HashSet();
		// iterate through the list and get CourtPolic for each CourtConditionCourtPolicy
//		while(policies.hasNext()){
//			CourtConditionCourtPolicy courtConditionCourtPolicy = (CourtConditionCourtPolicy)policies.next();
//			// This is a view and it can return duplicate policiess. Let's not pass duplicate policies to ui.
//			if(!oidSet.contains(courtConditionCourtPolicy.getCourtPolicyId())){
//				oidSet.add(courtConditionCourtPolicy.getCourtPolicyId());
//
//				// get CourtPolicy
//				CourtPolicy policy = CourtPolicy.find(courtConditionCourtPolicy.getCourtPolicyId());
//				CourtPolicyResponseEvent reply = new CourtPolicyResponseEvent();
//	
//				reply.setPolicyId(policy.getOID().toString());
//				reply.setAgencyId(policy.getAgencyId());
//				reply.setName(policy.getName());
//						
//				Group[] groups = policy.getGroup().getGroupList();
//				if (groups[0] != null)
//				{
//					reply.setGroup1Id(groups[0].getOID().toString());
//					reply.setGroup1Name(groups[0].getGroupName());
//				}
//				if (groups[1] != null)
//				{
//					reply.setGroup2Id(groups[1].getOID().toString());
//					reply.setGroup2Name(groups[1].getGroupName());
//				}
//				if (groups[2] != null)
//				{
//					reply.setGroup3Id(groups[2].getOID().toString());
//					reply.setGroup3Name(groups[2].getGroupName());
//				}
//	
//				dispatch.postEvent(reply);
//			}			
//		}
		
		while(policies.hasNext()){
		    CourtConditionCourtPolicy courtConditionCourtPolicy = (CourtConditionCourtPolicy)policies.next();
			// This is a view and it can return duplicate policiess. Let's not pass duplicate policies to ui.
			if(!oidSet.contains(courtConditionCourtPolicy.getCourtPolicyId())){
				oidSet.add(courtConditionCourtPolicy.getCourtPolicyId());
			}
		}

		// retrieve all the CourtPolicies together
		if(oidSet.size() > 0){
			Iterator courtPolicies = CourtPolicy.findAllByIds(oidSet);
			while(courtPolicies.hasNext()){
				// get CourtPolicy
				CourtPolicy policy = (CourtPolicy)courtPolicies.next();

				CourtPolicyResponseEvent reply = new CourtPolicyResponseEvent();
				reply.setPolicyId(policy.getOID().toString());
				reply.setAgencyId(policy.getAgencyId());
				reply.setName(policy.getName());
						
				Group[] groups = policy.getGroup().getGroupList();
				if (groups[0] != null)
				{
					reply.setGroup1Id(groups[0].getOID().toString());
					reply.setGroup1Name(groups[0].getGroupName());
				}
				if (groups[1] != null)
				{
					reply.setGroup2Id(groups[1].getOID().toString());
					reply.setGroup2Name(groups[1].getGroupName());
				}
				if (groups[2] != null)
				{
					reply.setGroup3Id(groups[2].getOID().toString());
					reply.setGroup3Name(groups[2].getGroupName());
				}

				dispatch.postEvent(reply);
			}			
		}
	}

	public static void postAssociatedConditionsForCourtPolicy(String policyId){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		
		Iterator conditions = CourtConditionCourtPolicy.findAll("courtPolicyId", policyId);
	
		Set oidSet = new HashSet();
		// iterate through the list and get Conditions for each CourtConditionCourtPolicy
		while(conditions.hasNext()){
			CourtConditionCourtPolicy courtConditionCourtPolicy = (CourtConditionCourtPolicy)conditions.next();
			// This is a view and it can return duplicate conditions. Let's not pass duplicate conditions to ui.
			if(!oidSet.contains(courtConditionCourtPolicy.getConditionId())){
				oidSet.add(courtConditionCourtPolicy.getConditionId());
				
				// get CourtPolicy
				Condition cond = Condition.find(courtConditionCourtPolicy.getConditionId());
//				if(cond.getIsDeleted()){
//					continue;
//				}
				ConditionResponseEvent reply = new ConditionResponseEvent();
		
				reply.setConditionId(cond.getOID().toString());
				reply.setAgencyId(cond.getAgencyId());
				reply.setName(cond.getName());
							
				Group group = cond.getGroup();
				if(group != null){			
					Group[] groups = group.getGroupList();
					if (groups[0] != null)
					{
						reply.setGroup1Id(groups[0].getOID().toString());
						reply.setGroup1Name(groups[0].getGroupName());
					}
					if (groups[1] != null)
					{
						reply.setGroup2Id(groups[1].getOID().toString());
						reply.setGroup2Name(groups[1].getGroupName());
					}
					if (groups[2] != null)
					{
						reply.setGroup3Id(groups[2].getOID().toString());
						reply.setGroup3Name(groups[2].getGroupName());
					}
				}
						
				dispatch.postEvent(reply);
			}
		}
	}
	
	public static void postAssociatedConditionsForDepartmentPolicy(String policyId){
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		
		Iterator conditions = CourtConditionAgencyPolicy.findAll("agencyPolicyId", policyId);
		Set oidSet = new HashSet();
		// iterate through the list and get Conditions for each CourtConditionCourtPolicy
		while(conditions.hasNext()){
			CourtConditionAgencyPolicy courtConditionAgencyPolicy = (CourtConditionAgencyPolicy)conditions.next();
			// This is a view and it can return duplicate conditions. Let's not pass duplicate conditions to ui.
			if(!oidSet.contains(courtConditionAgencyPolicy.getConditionId())){
				oidSet.add(courtConditionAgencyPolicy.getConditionId());
				// get CourtPolicy
				Condition cond = Condition.find(courtConditionAgencyPolicy.getConditionId());
				ConditionResponseEvent reply = new ConditionResponseEvent();
			
				reply.setConditionId(cond.getOID().toString());
				reply.setAgencyId(cond.getAgencyId());
				reply.setName(cond.getName());
								
				Group[] groups = cond.getGroup().getGroupList();
				if (groups[0] != null)
				{
					reply.setGroup1Id(groups[0].getOID().toString());
					reply.setGroup1Name(groups[0].getGroupName());
				}
				if (groups[1] != null)
				{
					reply.setGroup2Id(groups[1].getOID().toString());
					reply.setGroup2Name(groups[1].getGroupName());
				}
				if (groups[2] != null)
				{
					reply.setGroup3Id(groups[2].getOID().toString());
					reply.setGroup3Name(groups[2].getGroupName());
				}
			
				dispatch.postEvent(reply);
			}
		}
	}
	public static List getSupervisionConditions(GetSupervisionConditionsEvent evt){
		
		Iterator condIter = Condition.findAll( evt );
		List responseEvents = new ArrayList();
		List condList = CollectionUtil.iteratorToList(condIter);

		if (evt.isLimitSearchResults() && condList.size() > PDConstants.SEARCH_LIMIT){
		     CountInfoMessage infoEvent = new CountInfoMessage();
		     infoEvent.setMessage("error.max.limit.exceeded");
		     responseEvents.add(infoEvent);
		} else {		
			for (int i = 0; i < condList.size(); i++) {

				Condition cond = (Condition) condList.get(i);
				ConditionResponseEvent reply = null;
				if(evt.isSpecialCondition()){ // special condition
					reply = CommonSupervisionHelper.getSpecialConditionResponseEvent(cond); 
				}else{ // normal condition
					reply = CommonSupervisionHelper.getConditionResponseEvent(cond); 
				}
				if("JUV".equals(evt.getAgencyId())){
					List SupervisionCds = (ArrayList) cond.getSupervisionTypes();
					if(SupervisionCds != null && SupervisionCds.size() > 0) {
						reply.setSupervisionType(SimpleCodeTableHelper.getDescsFromSelCodes("SUPERVISION_TYPE", SupervisionCds));
					}
				}
				responseEvents.add(reply);
			}
		}
		return responseEvents;
	}
}
