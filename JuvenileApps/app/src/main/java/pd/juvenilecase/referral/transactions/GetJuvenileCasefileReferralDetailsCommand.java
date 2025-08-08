//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileReferralDetailsCommand.java

package pd.juvenilecase.referral.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.juvenile.reply.JJSReferralResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJuvenileCasefileAssignmentsEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileReferralDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import naming.PDJuvenileCaseConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileFacility;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.juvenilewarrant.JJSPetition;
import pd.juvenilewarrant.JuvenileOffenderTrackingCharge;
import ui.juvenilecase.referral.JuvenileReferralOffenseBean;

public class GetJuvenileCasefileReferralDetailsCommand implements ICommand
{

    /**
     * @roseuid 42A9A3070063
     */
    public GetJuvenileCasefileReferralDetailsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42A99B97035F
     */
    public void execute(IEvent event)
    {
	/*
	 * Only expect one result, so take the first referral wrap it up into a
	 * response event, post and return out of the command
	 */

	//List<JuvenileReferralDispositionCode> juvReferralDispList = JuvenileCaseHelper.getReferralDecisions();
	List<JuvenileReferralDispositionCode> juvReferralDispList = JuvenileCaseHelper.getAllReferralDecisions();
	List<JuvenileDispositionCode> juvDispList = JuvenileCaseHelper.getCourtDecisions();

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetJuvenileCasefileReferralDetailsEvent e = (GetJuvenileCasefileReferralDetailsEvent) event;
	GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	refEvent.setJuvenileNum(e.getJuvenileNum());
	refEvent.setReferralNum(e.getReferralNum());

	Iterator<JJSReferral> i = JJSReferral.findAll(refEvent);
	while (i.hasNext())
	{
	    JJSReferral ref = (JJSReferral) i.next();
	    JJSReferralResponseEvent resp = (JJSReferralResponseEvent) new JJSReferralResponseEvent();
	    resp.setCourtId(ref.getCourtId());
	    resp.setCloseDate(ref.getCloseDate());
	    resp.setCourtDate(ref.getCourtDate());
	    resp.setPIACode(ref.getPIACode());
	    resp.setPIADescription(ref.getPIADescription());
	    resp.setReferralDate(ref.getReferralDate());
	    resp.setProbationStartDate(ref.getProbationStartDate());
	    resp.setProbationEndDate(ref.getProbationEndDate());
	    resp.setReferralTypeInd(ref.getReferralTypeInd());
	    JuvenileDispositionCode courtDisposition = ref.getCourtDisposition();
	    resp.setCourtDispositionCd(ref.getCourtDispositionId());
	    resp.setRefSource(ref.getReferralSource());
	    if (courtDisposition != null)
		resp.setCourtDispositionDesc(courtDisposition.getLongDesc());

	    // get the Juvenile record for level of care
	    JJSJuvenile juv = JJSJuvenile.find(ref.getJuvenileNum());

	    // get the level of care from Juvenile record
	    JuvenileFacility facility = null;
	    resp.setLevelOfCare("");
	    if (juv.getDetentionFacilityId() != null && !juv.getDetentionFacilityId().equalsIgnoreCase(""))
	    {
		// Defect 53065 - JJS only displays facility if there is a status. Modified this code to do the same.
		if (juv.getDetentionStatusId() != null && !juv.getDetentionStatusId().equals(""))
		{
		    facility = JuvenileFacility.find("code",juv.getDetentionFacilityId());
		    if (facility != null)
		    {
			if (facility.getLevelOfCareId() != null && (facility.getLevelOfCareId().equalsIgnoreCase(PDJuvenileCaseConstants.JUVENILE_LEVEL_CARE_B) || facility.getLevelOfCareId().equalsIgnoreCase(PDJuvenileCaseConstants.JUVENILE_LEVEL_CARE_M) || facility.getLevelOfCareId().equalsIgnoreCase(PDJuvenileCaseConstants.JUVENILE_LEVEL_CARE_S) || facility.getLevelOfCareId().equalsIgnoreCase(PDJuvenileCaseConstants.JUVENILE_LEVEL_CARE_I)))
			{
			    resp.setLevelOfCare(facility.getLevelOfCare().getDescription());
			}
		    }
		}
	    }

	    //task 13220/11029 Changes starts.
	    //JJSSERVICE & referralDecision
	    GetJuvenileCasefileAssignmentsEvent getAssignmentsEvent = new GetJuvenileCasefileAssignmentsEvent();
	    getAssignmentsEvent.setJuvenileNum(e.getJuvenileNum());
	    getAssignmentsEvent.setReferralNum(e.getReferralNum());
	    // replace this with intake history table.
	    //81747 changes.
	    Iterator<JJSSVIntakeHistory> serviceItr = JJSSVIntakeHistory.findAll(getAssignmentsEvent);

	    //avoid duplicates
	    Map<String, JJSSVIntakeHistory> uniqueServiceRecords = new HashMap<String, JJSSVIntakeHistory>();
	    //Map<Date,String> uniqueDispRecords = new HashMap<Date,String>();

	    while (serviceItr.hasNext())
	    {
		JJSSVIntakeHistory jjsservice = serviceItr.next();
		Iterator<JuvenileReferralDispositionCode> juvReferralDispListItr = juvReferralDispList.iterator();

		//					//checks for same disp code,same refferal  but different service date
		//					if(uniqueDispRecords.containsValue(jjsservice.getServiceTypeId())&& !uniqueDispRecords.containsKey(jjsservice.getServiceDate())){
		//							uniqueServiceRecords.remove(jjsservice.getServiceTypeId());
		//					}
		//					
		if (jjsservice != null && jjsservice.getIntakeDecisionId() != null && !jjsservice.getIntakeDecisionId().isEmpty())
		{
		    while (juvReferralDispListItr.hasNext())
		    {
			uniqueServiceRecords.put(jjsservice.getIntakeDecisionId(), jjsservice);
			JuvenileReferralDispositionCode juvReferralDispCode = juvReferralDispListItr.next();
			if (juvReferralDispCode != null && juvReferralDispCode.getCode().equalsIgnoreCase(jjsservice.getIntakeDecisionId()))
			{
			    if (juvReferralDispCode.getDispositionCode() != null && !juvReferralDispCode.getDispositionCode().isEmpty())
			    {
				if (jjsservice.getIntakeDate()!= null)
				{
				    resp.getDispositions().put(jjsservice.getIntakeDate(), juvReferralDispCode.getDispositionCode());
				    //	uniqueDispRecords.put(jjsservice.getServiceDate(), jjsservice.getServiceTypeId()); // check for disp code and service date combination.
				    break;
				}
			    }
			}
		    }
		}
	    }
	    //JJSCOURT && CourtDecision
	    GetJJSCourtEvent jjsevent = new GetJJSCourtEvent();
	    jjsevent.setJuvenileNumber(refEvent.getJuvenileNum());
	    jjsevent.setReferralNumber(ref.getReferralNum());
	    Iterator<JJSCourt> courtIter = JJSCourt.findAll(jjsevent);

	    //avoid duplicates
	    Map<String, JJSCourt> uniqueCourtRecords = new HashMap<String, JJSCourt>();
	    //	uniqueDispRecords = new HashMap<Date,String>(); //re-initialize
	    while (courtIter.hasNext())
	    {
		Iterator<JuvenileDispositionCode> juvDispListItr = juvDispList.iterator();
		JJSCourt court = courtIter.next();

		//					//checks for same disp code,same refferal  but different service date
		//					if(uniqueDispRecords.containsValue(court.getHearingDisposition())&& !uniqueDispRecords.containsKey(court.getCourtDate())){
		//						uniqueServiceRecords.remove(court.getHearingDisposition());
		//					}

		if (court != null && court.getHearingDisposition() != null && !court.getHearingDisposition().isEmpty())
		{
		    while (juvDispListItr.hasNext())
		    {
			uniqueCourtRecords.put(court.getHearingDisposition(), court);
			JuvenileDispositionCode juvDispCode = juvDispListItr.next();
			if (juvDispCode != null && juvDispCode.getCodeAlpha().equalsIgnoreCase(court.getHearingDisposition()))
			{
			    if (juvDispCode != null && juvDispCode.getDispositionCode() != null && !juvDispCode.getDispositionCode().isEmpty())
			    {
				if (juvDispCode.getDispositionCode().equals("RD") && court.getHearingType() != null && court.getHearingType().equals("AB"))
				    continue;

				if (court.getCourtDate() != null)
				{
				    resp.getDispositions().put(court.getCourtDate(), juvDispCode.getDispositionCode());
				    resp.setCourtPetitionAllegation(court.getPetitionAllegation()); //BUG 156574
				    //	uniqueDispRecords.put(court.getCourtDate(), court.getHearingDisposition()); // check for hearing disp code and court date combination.
				    break;
				}
			    }
			}
		    }
		}
	    }
	    //task 13220 Changes ends.

	    if (ref.getCourtResultId() != null)
	    {
		JuvenileDispositionCode courtResult = ref.getCourtResult();
		if (courtResult != null)
		{
		    resp.setCourtResultDesc(courtResult.getShortDesc());
		}

	    }

	    if (ref.getIntakeDecisionId() != null)
	    {
		Code decision = ref.getIntakeDecision();
		if (decision != null)
		{

		    resp.setIntakeDecisionDescr(decision.getDescription());
		}

	    }
	    resp.setIntakeDecision(ref.getIntakeDecisionId());

	    resp.setIntakeDate(ref.getIntakeDate());

	    resp.setJuvenileNum(ref.getJuvenileNum());
	    resp.setReferralNum(ref.getReferralNum());
	    resp.setSequenceNum(ref.getSequenceNum());
	    resp.setTransactionNum(ref.getTransactionNum());
	    resp.setDaLogNum(ref.getDaLogNum());
	    if (ref.getDaLogNum() != null && !ref.getDaLogNum().equals(""))
	    {
		GetJJSPetitionsEvent getJJSPetitions = new GetJJSPetitionsEvent();
		getJJSPetitions.setJuvenileNum(ref.getJuvenileNum());
		getJJSPetitions.setReferralNum(ref.getReferralNum());

		Iterator petitions = JJSPetition.findAll(getJJSPetitions);
		while (petitions.hasNext())
		{
		    JJSPetition petition = (JJSPetition) petitions.next();
		    if( petition.getTerminationDate() != null ){
			
			resp.setTerminationDate(new SimpleDateFormat("MM/dd/yyyy").format( petition.getTerminationDate() ));
		    }

		    String petitionNum = petition.getPetitionNum();
		    if (!petitionNum.equals(""))
		    {
			AttributeEvent attEvent = new AttributeEvent();
			attEvent.setAttributeName("petitionNum");
			attEvent.setAttributeValue(petitionNum);
			Iterator charges = JuvenileOffenderTrackingCharge.findAll(attEvent);
			while (charges.hasNext())
			{
			    JuvenileOffenderTrackingCharge charge = (JuvenileOffenderTrackingCharge) charges.next();
			    resp.setTransactionNum(charge.getTransactionNum());
			    resp.setDaLogNum(charge.getDaLogNum());
			}
		    }
		}
	    }

	    //US 71177
	    // Profile stripping fix task 97546
	    //Juvenile juvenile = Juvenile.find(ref.getJuvenileNum());
	    Juvenile juvenile = Juvenile.findJCJuvenile(ref.getJuvenileNum());
	    //
	    //List<JJSOffense> offenses = InterviewHelper.getOffensesForReferral(juvenile, ref);
	    List<JJSOffense> offenses= InterviewHelper.getOffensesForReferral(ref.getJuvenileNum(), ref.getReferralNum() );
	    Iterator offenseIter = offenses.iterator();
	    ArrayList<JuvenileReferralOffenseBean> offenseResps = new ArrayList<JuvenileReferralOffenseBean>();
	    while (offenseIter.hasNext())
	    {
		JJSOffense o = (JJSOffense) offenseIter.next();
		JuvenileReferralOffenseBean offenseResp = new JuvenileReferralOffenseBean();
		offenseResp.setTopic(PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC);
		offenseResp.setJuvenileNum(o.getJuvenileNum());
		offenseResp.setOffenseDate(o.getOffenseDate());
		offenseResp.setOffenseDescription(o.getOffenseDescription());
		offenseResp.setReferralNum(o.getReferralNum());
		offenseResp.setSequenceNum(o.getSequenceNum());
		offenseResp.setSequenceNum(o.getSequenceNum());
		offenseResp.setInvestigationNum(o.getInvestigationNumber());
		offenseResp.setOffenseCodeId(o.getOffenseCodeId());
		offenseResp.setOffenseCode(o.getOffenseCode().getOffenseCode());
		offenseResp.setOffenseID(o.getOID());
		offenseResps.add(offenseResp);
	    }
	    resp.setOffenses(offenseResps);
	    dispatch.postEvent(resp);
	    return;
	}
    }

    /**
     * @param event
     * @roseuid 42A99B970361
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42A99B970369
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42A99B97036B
     */
    public void update(Object anObject)
    {

    }
}
