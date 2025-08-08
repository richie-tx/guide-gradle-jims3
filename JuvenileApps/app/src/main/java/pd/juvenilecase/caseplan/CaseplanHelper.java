/*
 * Created on Nov 15, 2006
 *
 */
package pd.juvenilecase.caseplan;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.caseplan.RetrieveCasePlanDocumentDataEvent;
import messaging.caseplan.reply.CaseplanDocJuvDetailsResponseEvent;
import messaging.caseplan.reply.GoalDetailsResponseEvent;
import messaging.caseplan.reply.GoalListResponseEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.utilities.CollectionUtil;
import naming.ActivityConstants;
import pd.address.Address;
import pd.codetable.criminal.JuvenileFacility;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileBenefit;
import pd.juvenile.JuvenileContact;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileHelper;
import pd.juvenile.JuvenileSchoolHistory;
import pd.juvenilecase.casefile.Activity;

/**
 * @author dapte
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class CaseplanHelper
{

    public static void retrieveJuvenileInfo(String juvenileNum)
    {
	// Profile stripping fix task 97537
        //Juvenile juv = Juvenile.find(juvenileNum);
	JuvenileCore juv = JuvenileCore.findCore(juvenileNum);
        CaseplanDocJuvDetailsResponseEvent resp = new CaseplanDocJuvDetailsResponseEvent();
        resp.setJuvNum(juvenileNum);
        resp.setDob(juv.getDateOfBirth());
        resp.setJuvFirstName(juv.getFirstName());
        resp.setJuvMiddleName(juv.getMiddleName());
        resp.setJuvLastName(juv.getLastName());

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        dispatch.postEvent(resp);
    }

    public static void retrieveCaseplanData(String caseplanID)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        CasePlan cp = CasePlan.find(caseplanID);
        // get goals and create goaldetailsresponsevent
        Iterator ite = cp.getTheGoal().iterator();
        while (ite.hasNext())
        {
            Goal goal = (Goal) ite.next();
            GoalDetailsResponseEvent goalResponse = CaseplanHelper.getGoalDetailsResponseEventFromGoal(goal);
            if (goal.getTimeFrame() != null)
            {
                goalResponse.setTimeframeDescription(goal.getTimeFrame().getDescription());
                String GoalIntervention = goal.getGoalIntervention();
                if(GoalIntervention != null){
                goalResponse.setIntervention(GoalIntervention);
                }//added by ER JIMS200075816 MJCW
            }
            dispatch.postEvent(goalResponse);
        }
        // get the placement info
        Placement placement = cp.getThePlacement();
        if (placement != null)
        {
            PlacementInfoResponseEvent pResponse = CaseplanHelper.getPlacementInfoResponseFromPlacement(placement);
            JuvenileFacility facility = placement.getFacility();
            if (facility != null)
            {
            	pResponse.setFacilityName(facility.getDescription());
            	pResponse.setFacilityAddress(facility.getAddress());
            	String cityStZip = new StringBuffer(facility.getCity()).append(", ").append(facility.getZip()).toString();
            	pResponse.setFacilityStateZip(cityStZip);
            	pResponse.setPermanencyPlan(placement.getPermanencyPlan().getDescription());
            	pResponse.setFacilityPhone(facility.getFacilityPhone());
            	pResponse.setJuvenilePlacementType(facility.getJuvenilePlacementType());
            }
            	dispatch.postEvent(pResponse);
        }

    }

    public static void retrieveMedicalDentalContacts(String juvenileNum)
    {
        Iterator contacts = JuvenileContact.findAll("juvenileId", juvenileNum);
        JuvenileContact contact = null;
        ArrayList drList = new ArrayList();
        ArrayList dnList = new ArrayList();
        if (contacts.hasNext())
        {
            //	Get the IDispatch to post to
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            //drList = new ArrayList();
            //dnList = new ArrayList();
            while (contacts.hasNext())
            {
                contact = (JuvenileContact) contacts.next();
                if (contact.getRelationshipId().equals("DR"))
                {
                    drList.add(contact);
                    continue;
                }
                if (contact.getRelationshipId().equals("DN"))
                {
                    dnList.add(contact);
                    continue;
                }
            }
            // sort by entry date to get the latest record
            Collections.sort(drList);
            Collections.sort(dnList);
            if (drList.size() != 0)
            {
                JuvenileContact latestDr = (JuvenileContact) drList.get(drList.size() - 1);
                JuvenileContactResponseEvent drResp = JuvenileHelper.getJuvenileContactResponseEvent(latestDr);
                dispatch.postEvent(drResp);
            }
            if (dnList.size() != 0)
            {
                JuvenileContact latestDn = (JuvenileContact) dnList.get(dnList.size() - 1);
                JuvenileContactResponseEvent dnResp = JuvenileHelper.getJuvenileContactResponseEvent(latestDn);
                dispatch.postEvent(dnResp);
            }
        }

    }

    public static void retrieveJuvenileSchoolInfo(String juvenileNum)
    {
        GetJuvenileSchoolEvent evt = new GetJuvenileSchoolEvent();
        evt.setJuvenileNum(juvenileNum);
        Iterator ite = JuvenileSchoolHistory.findJuvenileSchoolHistory(evt);
        JuvenileSchoolHistory latest = null;
        while (ite.hasNext())
        {
            JuvenileSchoolHistory history = (JuvenileSchoolHistory) ite.next();
            if (latest == null)
            {
                latest = history;
                continue;
            }
            if (history.getCreateDate().after(latest.getCreateDate()))
            {
                latest = history;
                continue;
            }
        }
        if (latest != null)
        {
            // educational provider's name, ph#, address,
            // child's current grade placement
            // child's current grade performance
        	String addrIdStr = latest.getAddressId();
        	Address address = null;
        	if (addrIdStr != null && !"".equals(addrIdStr))
        	{
        		address = Address.find(new Integer(addrIdStr).intValue());
        	}
            JuvenileSchoolHistoryResponseEvent resp = new JuvenileSchoolHistoryResponseEvent();
            JuvenileSchoolDistrictCode school = latest.getSchool();
            if (school != null)            {
                resp.setSchool(school.getSchoolDescription());
                resp.setGradeLevelCode(latest.getGradeLevelId());
                resp.setGradeAverage(latest.getGradeAverage());
                resp.setCreateDate(latest.getCreateDate());
                resp.setSchoolPhone(school.getPhoneNum());
                if (address != null){
                	resp.setSchoolStreetNum(address.getStreetNum());
                	resp.setSchoolStreetName(address.getStreetName());
                	resp.setSchoolStreet(address.getStreetTypeId());
                	resp.setSchoolCity(address.getCity());
                	resp.setSchoolState(address.getStateId());
                } else {
                	resp.setSchoolStreetName(school.getStreetName());
                	resp.setSchoolCity(school.getCity());
                	resp.setSchoolState(school.getState());
                	resp.setSchoolZip(school.getZip());
                }
                IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
                dispatch.postEvent(resp);
            }
        }

    }

    public static void retrieveResidentialCaseplanData(RetrieveCasePlanDocumentDataEvent evt)
    {
        String juvenileNum = evt.getJuvenileNum();
        String caseplanID = evt.getCaseplanID();
        // retrieve identifying info: juvenile name, dob, county, caseworker PID
        retrieveJuvenileInfo(juvenileNum);
        // retrieve prior services: CURRENTLY NOT IMPLEMENTED
        // retrieve name, address and phone number of contact = medical and dental
        retrieveMedicalDentalContacts(juvenileNum);
        // retrieve child's current medical info: current meds, what they are for,
        //				other concers, date records were provided to caregiver: CURRENTLY NOT IMPLEMENTED
        // retrieve child's education related info: most recent
        // 			educational provider's name, ph#, address, child's current grade placement
        //			child's current grade performance
        retrieveJuvenileSchoolInfo(juvenileNum);
        // retrieve facility information: placement stuff
        // retrieve goals for all domains.
        if(caseplanID != null && caseplanID.length() >0)  
        {
        retrieveCaseplanData(caseplanID);
        }
    }

    public static void retrieveOtherCaseplanData(RetrieveCasePlanDocumentDataEvent evt)
    {
        String juvenileNum = evt.getJuvenileNum();
        String caseplanID = evt.getCaseplanID();
        // retrieve identifying info: juvenile name, dob, county, caseworker PID
        retrieveJuvenileInfo(juvenileNum);
        // retrieve prior services: CURRENTLY NOT IMPLEMENTED

        // retrieve name, address and phone number of contact = medical and dental
        retrieveMedicalDentalContacts(juvenileNum);
        // retrieve child's current medical info: current meds, what they are for,
        //				type of medical coverage: CURRENTLY NOT IMPLEMENTED

        // retrieve child's education related info: most recent
        // 			educational provider's name, ph#, address, child's current grade placement
        //			child's current grade performance
        retrieveJuvenileSchoolInfo(juvenileNum);
        // retrieve goals for all domains.
        if(caseplanID != null && caseplanID.length() >0)	
        {
        retrieveCaseplanData(caseplanID);
        }
        retrieveJuvenileBenefits(juvenileNum);
    }

    private static void retrieveJuvenileBenefits(String juvenileNum)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        Iterator iterator = JuvenileBenefit.findAll("juvenileNum", juvenileNum);
        while (iterator.hasNext())
        {
            JuvenileBenefit benefit = (JuvenileBenefit) iterator.next();
            if (benefit != null)
            {
                JuvenileBenefitResponseEvent reply = JuvenileHelper.getJuvenileBenefitResponseEvent(benefit);
                dispatch.postEvent(reply);
            }
        }
    }

    public static GoalListResponseEvent getGoalListResponseEventFromGoal(Goal goal)
    {
        GoalListResponseEvent goalResponse = new GoalListResponseEvent();
        goalResponse.setGoalID(goal.getOID().toString());
        goalResponse.setEntryDate(goal.getEntryDate());
        //goalResponse.setClosedDate(goal.getClosedDate()); 
        goalResponse.setGoalStatusChangeDate(goal.getGoalStatusChangeDate()); //added newly 
        goalResponse.setStatusId(goal.getStatusId());
        goalResponse.setGoalDescription(goal.getGoalDescription());
        goalResponse.setGoalIntervention(goal.getGoalIntervention()); //added by ER JIMS200075816 MJCW

        return goalResponse;
    }

    public static GoalDetailsResponseEvent getGoalDetailsResponseEventFromGoal(Goal goal)
    {
        GoalDetailsResponseEvent gEvent = new GoalDetailsResponseEvent();
        gEvent.setGoalID(goal.getOID().toString());
        gEvent.setEntryDate(goal.getEntryDate());
        gEvent.setClosedDate(goal.getClosedDate());
        gEvent.setGoalStatusChangeDate(goal.getGoalStatusChangeDate()); //newly added Status change date
        gEvent.setStatusId(goal.getStatusId());
        gEvent.setDomainTypeId(goal.getDomainTypeId());
        gEvent.setTimeframeId(goal.getTimeFrameId());
        gEvent.setGoalDescription(goal.getGoalDescription());
        gEvent.setIntervention(goal.getGoalIntervention()); //added by ER JIMS200075816 MJCW
        Iterator ite = goal.getPersonResponsible().iterator();
        ArrayList prNames = new ArrayList();
        while (ite.hasNext())
        {
            PersonResponsible pers = (PersonResponsible) ite.next();
            prNames.add(pers.getName());
        }
        gEvent.setNamesOfPersResponsible(prNames);
        gEvent.setProgressNotes(goal.getProgressNotes());
        gEvent.setIntervention(goal.getGoalIntervention()); //added by ER JIMS200075816 MJCW
        gEvent.setEndRecommendations(goal.getEndRecommendations());

        return gEvent;
    }

    public static PlacementInfoResponseEvent getPlacementInfoResponseFromPlacement(Placement placement)
    {
        PlacementInfoResponseEvent pResponse = new PlacementInfoResponseEvent();
        pResponse.setPlacementID(placement.getOID().toString());
        pResponse.setEntryDate(placement.getEntryDate());
        pResponse.setClosestFacilityAvail(placement.getClosestFacilityAvailable());
        pResponse.setLeastRestrEnvAvail(placement.getLeaseRestrEnvAvailable());
        pResponse.setProxToChildsSD(placement.getProximityToSchoolDist());
        pResponse.setReasonPlacementReqd(placement.getReasonForPlacement());
        pResponse.setSpecificServices(placement.getSpecificServices());
        pResponse.setWhyOutsideTexas(placement.getWhyOutsideTexas());
        pResponse.setFacilityId(placement.getFacilityId());
        pResponse.setFacilityRelReasonId(placement.getFacilityReleaseReasonId());
        pResponse.setExpReleaseDate(placement.getExpectedReleaseDate());
        pResponse.setLevelOfCareId(placement.getLevelOfCareId());
        pResponse.setPermanencyPlanId(placement.getPermanencyPlanId());
        pResponse.setSpecialNotes(placement.getSpecialnotes());
        return pResponse;
    }

    public static String getLatestJPOReviewRequestComments(String casefileID)
    {
        String comments = "";
        ActivityResponseEvent actResp = null;
        Activity activity = null;
        // find the latest activity where jpo requested review
        GetActivitiesEvent activityEvt = new GetActivitiesEvent();
        activityEvt.setCasefileID(casefileID);
        activityEvt.setActivityCodeId(ActivityConstants.CASE_PLAN_FORWARDED_FOR_CLM_REVIEW);

        List activityList = CollectionUtil.iteratorToList( Activity.findAll( activityEvt ));
        List actResposeList = new ArrayList();
        
        for ( int j = 0; j < activityList.size(); j++ )
        {
        	activity = (Activity) activityList.get(j);
            actResp = activity.valueObject();
            actResposeList.add( actResp );

        }
        
        ActivityResponseEvent recent = null;
        int total = actResposeList.size();
        if (total > 1)
        {
            Collections.sort(actResposeList);
            recent = (ActivityResponseEvent) actResposeList.get(total - 1);
        }
        else if (total == 1)
        {
            recent = (ActivityResponseEvent) actResposeList.get(0);
        }
        if (recent != null)
            comments = recent.getComments();
        
        activity = null;
        actResp = null;
        actResposeList = null;
        
        return comments;
    }

}
