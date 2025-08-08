/*
 * Created on Dec 12, 2005
 *
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.GetGoalListForReviewEvent;
import messaging.caseplan.SaveCLMReviewEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.caseplan.reply.GoalDetailsResponseEvent;
import messaging.caseplan.reply.GoalListResponseEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.juvenile.GetJuvenileContactsEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import messaging.rules.GetJuvenileCasefileSupervisionRulesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasePlanControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileConstants;
import naming.PDJuvenileFamilyConstants;
import ui.common.CodeHelper;
import ui.common.GroupHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.juvenilecase.caseplan.form.CaseplanForm.PlacementInfo;

/**
 * @author jjose
 *  
 */
public class UIJuvenileCaseplanHelper
{

    public static List getFamilyMembers(CaseplanForm form)
    {
        GetActiveFamilyConstellationEvent event = (GetActiveFamilyConstellationEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETACTIVEFAMILYCONSTELLATION);
        event.setJuvenileNum(form.getJuvenileNum());

        // Getting PD Response Event
        CompositeResponse response = UIJuvenileHelper.getCompositeResponse(event);
        // Perform Error handling
        MessageUtil.processReturnException(response);
        List coll = new ArrayList();
        Map dataMap = MessageUtil.groupByTopic(response);
        if (dataMap != null)
        {
            Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
            if (families != null && families.size() > 0)
            {
                Iterator myIter = families.iterator();
                while (myIter.hasNext())
                {
                    FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter
                            .next();
                    if (myFamily.isActive())
                    {

                        Collection currentFamMembers = (Collection) dataMap
                                .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
                        if (currentFamMembers != null && currentFamMembers.size() > 0)
                        {
                            Iterator currFamMemIter = currentFamMembers.iterator();
                            while (currFamMemIter.hasNext())
                            {
                                AbusePerpetrator myAbusePerp = new AbusePerpetrator();
                                FamilyConstellationMemberListResponseEvent resp = (FamilyConstellationMemberListResponseEvent) currFamMemIter
                                        .next();
                                CaseplanForm.PersonResponsible personResp = new CaseplanForm.PersonResponsible();
                                StringBuffer str = new StringBuffer();
                                if (resp.getLastName() != null)
                                    str.append(resp.getLastName());
                                if (resp.getFirstName() != null)
                                    str.append(", " + resp.getFirstName());
                                if (resp.getMiddleName() != null)
                                    str.append(" " + resp.getMiddleName());                              
                                if (resp.getRelationToJuvenile() != null)
                                    str.append(" (" + resp.getRelationToJuvenile() + ")"); //Changed
                                                                                           // this
                                                                                           // to all
                                                                                           // upper
                                                                                           // case
                                                                                           // to
                                                                                           // match
                                                                                           // database,
                                                                                           // because
                                                                                           // it
                                                                                           // defaulted
                                                                                           // to
                                                                                           // uppercase
                                else
                                {
                                    str.append(" (FAMILY MEMBER)");
                                }
                                personResp.setName(str.toString());
                                personResp.setType("F");
                                coll.add(personResp);
                            }
                        }
                    }
                }
            }
        }

        return coll;
    }

    public static List getContactNames(CaseplanForm form)
    {
        GetJuvenileContactsEvent contactsEvent = (GetJuvenileContactsEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILECONTACTS);
        String juvenileNum = form.getJuvenileNum();
        contactsEvent.setJuvenileNum(juvenileNum);
        List coll = new ArrayList();
        CompositeResponse response = UIJuvenileHelper.getCompositeResponse(contactsEvent);
        List contacts = (List) UIJuvenileHelper.fetchCollection(response,
                PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);
        if (contacts != null && contacts.size() > 0)
        {
            Iterator myIter = contacts.iterator();
            while (myIter.hasNext())
            {
                CaseplanForm.PersonResponsible personResp = new CaseplanForm.PersonResponsible();
                JuvenileContactResponseEvent resp = (JuvenileContactResponseEvent) myIter.next();
                StringBuffer str = new StringBuffer();
                if (resp.getLastName() != null)
                    str.append(resp.getLastName());
                if (resp.getFirstName() != null)
                    str.append(", " + resp.getFirstName());
                if (resp.getMiddleName() != null)
                    str.append(" " + resp.getMiddleName());
                if (resp.getRelationship() != null)
                    str.append(" (" + resp.getRelationship() + ")"); //Changed this to all upper
                                                                     // case to match database,
                                                                     // because it defaulted to
                                                                     // uppercase
                else
                {
                    str.append(" (CONTACT)");
                }
                personResp.setName(str.toString());
                personResp.setType("C");
                coll.add(personResp);

            }
        }
        return coll;
    }

    public static String getDomainType(CaseplanForm cp)
    {
	if( cp.getDormainTypeList() == null){
	    
	    Collection coll = CodeHelper.getCodes( "GOAL_DOMAIN_TYPE", true ) ;
	    cp.setDormainTypeList(coll);
	}
        Collection coll = cp.getDormainTypeList();
        Iterator iter = coll.iterator();
        while (iter.hasNext())
        {
            CodeResponseEvent resp = (CodeResponseEvent) iter.next();
            if (resp.getCode().equals(cp.getCurrentGoalInfo().getDomainTypeCd()))
            {
                return resp.getDescription();
            }
        }
        return null;
    }

    public static String getTimeFrame(CaseplanForm cp)
    {
        Collection coll = cp.getTimeFrameList();
        Iterator iter = coll.iterator();
        while (iter.hasNext())
        {
            CodeResponseEvent resp = (CodeResponseEvent) iter.next();
            if (resp.getCode().equals(cp.getCurrentGoalInfo().getTimeFrameCd()))
            {
                return resp.getDescription();
            }
        }
        return null;
    }
   
    public static String getDisplayContactNames(String person)
    {
        String buf = "";
        StringTokenizer strtok = new StringTokenizer(person, "(");
        int count = 0;
        while (strtok.hasMoreTokens())
        {
            String str = strtok.nextToken();
            if (count == 0)
                buf = str;
            count++;
        }

        return buf;
    }

    public static void fillCaseplanDetails(CaseplanForm.Caseplan cp, CaseplanDetailsResponseEvent cpEvt)
    {
        String status = CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes(PDCodeTableConstants.CASEPLAN_STATUS),
                cpEvt.getStatusId());
        cp.setStatus(status);
        cp.setStatusCd(cpEvt.getStatusId()); 
        if (cpEvt.getCreateDate() != null)
            cp.setCreateDate(DateUtil.dateToString(cpEvt.getCreateDate(), "MM/dd/yyyy"));
        if (cpEvt.getReviewDate() != null)
            cp.setReviewDate(DateUtil.dateToString(cpEvt.getReviewDate(), "MM/dd/yyyy"));
        cp.setCaseplanId(cpEvt.getCaseplanID());
    }

    public static void fillGoalDetails(CaseplanForm.GoalInfo goal, GoalDetailsResponseEvent goalEvt)
    {
        goal.setDomainTypeCd(goalEvt.getDomainTypeId());
        goal.setTimeFrameCd(goalEvt.getTimeframeId());
        goal.setStatusCd(goalEvt.getStatusId());
        goal.setGoal(goalEvt.getGoalDescription());
        goal.setProgressNotes(goalEvt.getProgressNotes());
        goal.setIntervention(goalEvt.getIntervention()); //added for ER JIMS200075816 
        goal.setEndRecommendations(goalEvt.getEndRecommendations());
        goal.setPersonsResponsibleDisplay(goalEvt.getNamesOfPersResponsible());
        goal.setGoalId(goalEvt.getGoalID());
        Collection persons = goalEvt.getNamesOfPersResponsible();
        if (persons != null)
        {
            Iterator iter = persons.iterator();
            String[] names = new String[persons.size()];
            int count = 0;
            while (iter.hasNext())
            {
                String name = (String) iter.next();
                //Commented this because the name should match multi-select
                //box so they will be auto-selected during update.
                //names[count] = getDisplayContactNames(name);
                names[count] = name;
                count++;
            }
            goal.setPersonsResponsibleIds(names);
            goal.setOldPersonsResponsibleIds(names);
        }
        goal.setOldDomainTypeCd(goalEvt.getDomainTypeId());
        goal.setOldTimeFrameCd(goalEvt.getTimeframeId());
        goal.setOldGoal(goalEvt.getGoalDescription());
        //added for bug #14827 starts
        goal.setOldProgressNotes(goalEvt.getProgressNotes());
        goal.setOldIntervention(goalEvt.getIntervention());
      //added for bug #14827 ends
    }

    public static List getGoalSummaryList(String caseplanID)
    {
        List summaryList = null;
        GetGoalListForReviewEvent request = new GetGoalListForReviewEvent();
        request.setCaseplanID(caseplanID);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(request);
        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        MessageUtil.processReturnException(compositeResponse);
        List goalEvents = MessageUtil.compositeToList(compositeResponse, GoalDetailsResponseEvent.class);
        Iterator ite = goalEvents.iterator();
        if (ite.hasNext())
        {
            summaryList = new ArrayList();
        }
        while (ite.hasNext())
        {
            GoalDetailsResponseEvent evt = (GoalDetailsResponseEvent) ite.next();
            CaseplanForm.GoalInfo goal = new CaseplanForm.GoalInfo();
            fillGoalDetails(goal, evt);
            summaryList.add(goal);
        }
        return summaryList;
    }

    public static List getRuleDetails(List responses)
    {
        int len = responses.size();
        for(int i=0;i<len;i++)
        {
            RuleResponseEvent evt = (RuleResponseEvent) responses.get(i);
            String grpName = GroupHelper.getGroupName(evt.getCondCategoryId());
            String completionStatus = CodeHelper.getCodeDescriptionByCode(CodeHelper
                    .getCodes(PDCodeTableConstants.COMPLETION_STATUS), evt.getRuleCompletionStatusId());
            String type = GroupHelper.getTypeName(evt.getCondTypeId());
            String subType = GroupHelper.getSubTypeName(evt.getCondSubTypeId());
            String ruleMonitorFreqDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.MONITOR_FREQUENCY, evt
                    .getRuleMonitorFreqId());
            String ruleTypeDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.SUPERVISION_RULES_TYPE, evt
                    .getRuleTypeId());
            evt.setRuleMonitorFreqDesc(ruleMonitorFreqDesc);
            evt.setRuleTypeDesc(ruleTypeDesc);
            evt.setRuleType(ruleTypeDesc);
            evt.setCondCategoryId(grpName);
            evt.setCondTypeId(type);
            evt.setCondSubTypeId(subType);
            evt.setRuleCompletionStatus(completionStatus);
        }
        return responses;
    }

    public static void fillPlacementDetails(PlacementInfo placementInfo,
                                            PlacementInfoResponseEvent placementEvt)
    {
        placementInfo.setEntryDate(DateUtil.dateToString(placementEvt.getEntryDate(), "MM/dd/yyyy"));
        if (placementEvt.isClosestFacilityAvail())
            placementInfo.setClosestFacilityAvailable("YES");
        else
            placementInfo.setClosestFacilityAvailable("NO");
        if (placementEvt.isLeastRestrEnvAvail())
            placementInfo.setLeastRestrictiveEnv("YES");
        else
            placementInfo.setLeastRestrictiveEnv("NO");
        if (placementEvt.isProxToChildsSD())
            placementInfo.setProximityConsidered("YES");
        else
            placementInfo.setProximityConsidered("NO");
        placementInfo.setPlacementID(placementEvt.getPlacementID());
        placementInfo.setReasonChildRequiresPlacement(placementEvt.getReasonPlacementReqd());
        placementInfo.setSpecificServicesProvided(placementEvt.getSpecificServices());
        placementInfo.setReasonChildIsPlacedOutsideOfTexas(placementEvt.getWhyOutsideTexas());
        placementInfo.setFacilityId(placementEvt.getFacilityId());
        placementInfo.setFacilityReleaseReasonId(placementEvt.getFacilityRelReasonId());
        placementInfo.setExpectedReleaseDate(DateUtil.dateToString(placementEvt.getExpReleaseDate(), "MM/dd/yyyy"));
        placementInfo.setLevelOfCareId(placementEvt.getLevelOfCareId());
        placementInfo.setPermanencyPlanId(placementEvt.getPermanencyPlanId());
        placementInfo.setSpecialNotes(placementEvt.getSpecialNotes());
    }

    public static void getPlacementList(CaseplanForm cpf)
    {
        Collection coll = CodeHelper.getCodes("JUVENILE_LEVEL_CARE", true);
        cpf.setLevelOfCareList(coll);
        coll = CodeHelper.getCodes("PERMANENCY_PLAN", true);
        cpf.setPermanencyListPlan(coll);
        coll = CodeHelper.getCodes("FACILITY_RELEASE_REASON", true);
        cpf.setFacilityReleaseReasonList(coll);
    }

    public static Collection getCaseplanActivitiesList(String casefileID)
    {
        GetActivitiesEvent evt = (GetActivitiesEvent) EventFactory
        .getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
        evt.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_CASEPLAN);
        evt.setCasefileID(casefileID);
        evt.setCategoryId(ActivityConstants.ACTIVITY_CATEGORY_REPORTING);
                
        List results = MessageUtil.postRequestListFilter(evt, ActivityResponseEvent.class);
       
        return results;

    }

    public static List getAllRulesForCasefile(String casefileID)
    {        
        GetJuvenileCasefileSupervisionRulesEvent evt = new GetJuvenileCasefileSupervisionRulesEvent();
        evt.setSupervisionNumber(casefileID);
        
        List allRules = MessageUtil.postRequestListFilter(evt, RuleResponseEvent.class);
        allRules = UIJuvenileCaseplanHelper.getRuleDetails(allRules);
        return allRules;
    }

    public static void markCurrentlyAssociatedRules(List allRules, String[] selectedRules)
    {
        Iterator ite = allRules.iterator();
        while (ite.hasNext())
        {
            RuleResponseEvent evt = (RuleResponseEvent) ite.next();
            for (int i = 0; i < selectedRules.length; i++)
            {
                if (selectedRules[i].equalsIgnoreCase(evt.getRuleId()))
                {
                    evt.setCurrentlySelected(true);
                    break;
                }
            }
        }
    }

    public static void fetchCaseplanDetails(CaseplanForm form, boolean isCLMReview)
    {
        GetCaseplanDetailsEvent det = (GetCaseplanDetailsEvent) EventFactory
                .getInstance(JuvenileCasePlanControllerServiceNames.GETCASEPLANDETAILS);
        det.setSupervisionNumber(form.getCasefileId());
        det.setForClmReview(isCLMReview);
        
        CompositeResponse compositeResponse = MessageUtil.postRequest(det);
        
        CaseplanDetailsResponseEvent cpEvt = (CaseplanDetailsResponseEvent) MessageUtil.filterComposite(
                compositeResponse, CaseplanDetailsResponseEvent.class);
        form.setReqReviewComments("");
        form.setCaseplanExist("N");
        if (cpEvt != null)
        {
            form.setReqReviewComments(cpEvt.getJPORequestReviewComments());
            if (cpEvt.getCaseplanID() != null)
            {
                UIJuvenileCaseplanHelper.fillCaseplanDetails(form.getCurrentCaseplan(), cpEvt);
                form.setCaseplanExist("Y");
                List goalResponses = MessageUtil.compositeToList(compositeResponse,
                        GoalListResponseEvent.class);
                List temp = new ArrayList();
                if (goalResponses != null)
                {
                    int len = goalResponses.size();
                    for(int i=0;i<len;i++)
                    {
                        GoalListResponseEvent respEvt = (GoalListResponseEvent) goalResponses.get(i);
                        String status = CodeHelper.getCodeDescriptionByCode(CodeHelper
                                .getCodes(PDCodeTableConstants.GOAL_STATUS), respEvt.getStatusId());
                        respEvt.setStatusId(status);
                        temp.add(respEvt);
                    }
                    Collections.sort(temp);
                    form.getCurrentCaseplan().setGoalList(temp);
                }
                PlacementInfoResponseEvent placementResp = (PlacementInfoResponseEvent) MessageUtil.filterComposite(
                        compositeResponse, PlacementInfoResponseEvent.class);
                if (placementResp != null)
                {
                    form.setPlacementInfoExist("Y");
                    UIJuvenileCaseplanHelper.getPlacementList(form);
                    UIJuvenileCaseplanHelper.fillPlacementDetails(form.getCurrentPlacementInfo(), placementResp);
                }
                else
                {
                    form.setPlacementInfoExist("N");
                }
            }
        }

    }

    public static void fetchCaseplanDetails(CaseplanForm form)
    {
        fetchCaseplanDetails(form, false);
    }

    public static void submitCLMReview(boolean accept, String caseplanID)
    {
        SaveCLMReviewEvent saveEvt = new SaveCLMReviewEvent();
        saveEvt.setAccept(true);
        saveEvt.setCaseplanID(caseplanID);
        
        MessageUtil.postRequest(saveEvt);        
    }
    
   public static PlacementInfo getCurrentPlacementInfo(String casefileId, String juvenileNum){
	   	GetAssignmentsByCasefileIdEvent getAssignments = new GetAssignmentsByCasefileIdEvent();
		getAssignments.setCasefileId(casefileId);
		CompositeResponse getAssignmentsResp = MessageUtil
		.postRequest(getAssignments);
		Collection assignments = MessageUtil.compositeToCollection(
				getAssignmentsResp, AssignmentResponseEvent.class);

		List facilityHistoryList = new ArrayList();
		
		if (assignments != null)
			Collections.sort((List) assignments);
		Iterator iter = assignments.iterator();
		while (iter.hasNext()) {
			AssignmentResponseEvent assignmentResponseEvent = (AssignmentResponseEvent) iter
			.next();
			IDispatch dispatch = EventManager
			.getSharedInstance(EventManager.REQUEST);
			GetJuvenileDetentionFacilitiesEvent event = (GetJuvenileDetentionFacilitiesEvent) EventFactory
			.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
			event.setReferralNum(assignmentResponseEvent.getReferralNum());
			event.setJuvenileNum(juvenileNum);
			dispatch.postEvent(event);
			// Getting PD Response Event
			CompositeResponse response = (CompositeResponse) dispatch
			.getReply();
			// Perform Error handling
			MessageUtil.processReturnException(response);
			Map dataMap = MessageUtil.groupByTopic(response);
			if (dataMap != null) {
				Collection facilityHistory = (Collection) dataMap
				.get(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
				if (facilityHistory != null) {
					facilityHistoryList.addAll(facilityHistory);										
				}
			}

		}
		PlacementInfo lastPlacement = new PlacementInfo( );
		if(facilityHistoryList != null && facilityHistoryList.size() > 0){
			Collections.sort((List) facilityHistoryList);
			JuvenileDetentionFacilitiesResponseEvent facilityResponseEvent =  (JuvenileDetentionFacilitiesResponseEvent) facilityHistoryList.get(0);
			lastPlacement.setFacilityId(facilityResponseEvent.getDetainedFacility());
		}else {
			lastPlacement.setFacilityId("");
		}
	   return lastPlacement;
	   
   }

}// END CLASS
