//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileProfileReferralListCommand.java

package pd.juvenilecase.referral.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.codetable.criminal.JuvenileReferralSource;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.juvenilewarrant.JJSPetition;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class GetJuvenileProfileReferralListCommand implements ICommand
{

    /**
     * @roseuid 4328435B0083
     */
    public GetJuvenileProfileReferralListCommand()
    {

    }

    /**
     * @param event
     * @roseuid 432836080271
     */
    public void execute(IEvent event)
    {
	GetJuvenileProfileReferralListEvent refEvent = (GetJuvenileProfileReferralListEvent) event;
	Iterator<JJSReferral> referralList = JJSReferral.findAll(refEvent);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	while (referralList.hasNext())
	{
	    JJSReferral ref = (JJSReferral) referralList.next();
	    JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
	    resp.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
	    resp.setCourtDisposition(ref.getCourtDispositionId());
	    resp.setCourtResult(ref.getCourtResultId());
	    resp.setCloseDate(ref.getCloseDate());
	    resp.setJpoId(ref.getJpoid());
	    resp.setIntakeDecDate(DateUtil.dateToString(ref.getIntakeDate(), DateUtil.DATE_FMT_1));
	    if (ref.getIntakeDecisionId() != null && !ref.getIntakeDecisionId().equals(""))
	    {
		JuvenileReferralDispositionCode intakeDescision = ref.getIntakeDecisionComplex();
		resp.setIntakeDecision(intakeDescision.getDescription());
		resp.setIntakeDecisionId(intakeDescision.getCode());
		resp.setFund(intakeDescision.getFund());
	    }

	    resp.setReferralDate(ref.getReferralDate());
	    resp.setReferralNumber(ref.getReferralNum());
	    resp.setCourtDate(ref.getCourtDate());
	    resp.setCourtId(ref.getCourtId());
	    resp.setCtAssignJpoId(ref.getCtAssignJPOId());
	    resp.setCtAssignLevel(ref.getCtAssignLevel());
	    resp.setPiaStatus(ref.getPIACode());
	    resp.setTJJDDate(DateUtil.dateToString(ref.getTJJDReferralDate(), DateUtil.DATE_FMT_1));
	    resp.setProbationJPOId(ref.getProbJPOId());
	    resp.setProbationLevel(ref.getProbLevel());
	    resp.setProbationStartDate(ref.getProbationStartDate());
	    resp.setProbationEndDate(ref.getProbationEndDate());
	    resp.setRecType(ref.getRecType());
	    resp.setDaLogNum(ref.getDaLogNum());
	    resp.setLcUser(ref.getLcUser());
	    if (ref.getCloseDate() != null)
		resp.setReferralStatus("CLOSED");
	    else
		resp.setReferralStatus("ACTIVE");
	    if (ref.getReferralTypeInd() != null)
	    {
		resp.setReferralTypeInd(ref.getReferralTypeInd());
		//ER changes 11054
		resp.setReferralTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.REFERRAL_TYPE, ref.getReferralTypeInd()));
	    }
	    resp.setTjpcDisp( ref.getTJPCDisp() );
	    resp.setDispositionDate(ref.getCourtDate());
	    resp.setPdaDate(ref.getPDADate());
	    GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
	    getEvent.setJuvenileNumber(ref.getJuvenileNum());
	    getEvent.setReferralNumber(ref.getReferralNum());
	    Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent); 
	    if (ref.getDaLogNum() != null && !ref.getDaLogNum().isEmpty())
	    {
		JuvenileOffenderTracking jot = JuvenileOffenderTracking.find( ref.getDaLogNum() );
		if ( jot != null )
		{
		    resp.setDaStatus( getStatusDescripton( jot.getLogStatus() ));
		    try
		    {
			if ( jot.getDaDateOut() > 0 )
			{
			    resp.setDaDateOut(new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(String.valueOf( jot.getDaDateOut() )));
			}
		    }
		    catch (Exception e)
		    {
		    }
		}
	    }

	    //User Story 11030 - JJS Court
	    List<JJSCourt> courtList = new ArrayList<JJSCourt>();

	    GetJJSCourtEvent jjsevent = new GetJJSCourtEvent();
	    jjsevent.setJuvenileNumber(refEvent.getJuvenileNum());
	    jjsevent.setReferralNumber(ref.getReferralNum());
	    Iterator<JJSCourt> courtIter = JJSCourt.findAll(jjsevent);
	    while (courtIter.hasNext())
	    {
		JJSCourt court = courtIter.next();
		courtList.add(court);
	    }
	    resp.setCourts(courtList);

	    //Added for facility starts
	    if (ref.getReferralSource() != null)
	    {
		resp.setReferralSource(ref.getReferralSource());
		/*JuvenileReferralSource referralSrc = JuvenileReferralSource.find(ref.getReferralSource());
		if (referralSrc != null)
		{
		    resp.setReferralSourceDesc(referralSrc.getDescription());
		}*/
		//added for BUG 170985 STARTS
		Iterator juvRefSource = JuvenileReferralSource.findAll("code", ref.getReferralSource());
		while (juvRefSource.hasNext())
		{
		    JuvenileReferralSource refSource = (JuvenileReferralSource) juvRefSource.next();
		    if (refSource != null)
		    {
			resp.setReferralSourceDesc(refSource.getDescription());
		    }

		} //added for BUG 170985 ENDS
	    }
	    if (ref.getReferralOfficer() != null)
	    {
		resp.setReferralOfficer(ref.getReferralOfficer());
	    }
	    //Added for facility ends

	    if (ref.getCourtDispositionId() != null)
	    {
		resp.setFinalDisposition(ref.getCourtDispositionId());
		JuvenileDispositionCode courtDisposition = ref.getCourtDisposition();
		if (courtDisposition != null)
		{
		    resp.setFinalDispositionDescription(courtDisposition.getLongDesc());
		    //added for userstory 11030
		    resp.setTjpcCode(courtDisposition.getJPCCode());
		    JuvenileDispositionCodeResponseEvent dispCodeRespEvt = new JuvenileDispositionCodeResponseEvent();
		    dispCodeRespEvt.setCodeAlpha(courtDisposition.getCodeAlpha());
		    dispCodeRespEvt.setDispositionCode(courtDisposition.getDispositionCode());
		    dispCodeRespEvt.setSubGroupInd(courtDisposition.getSubGroupInd()); //added for user-story 11449.
		    resp.setJuvDispositionCode(dispCodeRespEvt);
		    //added for userstory 11030
		}

	    }
	    //Added for District Court - Referral Inquiry
	    //need description for the Court Result

	    if (ref.getCourtResultId() != null)
	    {
		resp.setCourtResult(ref.getCourtResultId());
		JuvenileDispositionCode courtResult = ref.getCourtResult();
		if (courtResult != null)
		{
		    resp.setCourtResultDesc(courtResult.getLongDesc());
		    JuvenileDispositionCodeResponseEvent dispCodeRespEvt = new JuvenileDispositionCodeResponseEvent();
		    dispCodeRespEvt.setCodeAlpha(courtResult.getCodeAlpha());
		    dispCodeRespEvt.setDispositionCode(courtResult.getDispositionCode());
		    resp.setCourtResultDisposition(dispCodeRespEvt); //TODO Nekha wilk revisit this later. Modified for common app bug.
		}

	    }
	    //Added for District Court - Referral Inquiry ends
	    // Profile stripping fix task 97546
	    //Juvenile juvenile = Juvenile.find(ref.getJuvenileNum());
	    //Juvenile juvenile = Juvenile.findJCJuvenile(ref.getJuvenileNum());
	    //
	    //Petition
	    if (ref.getJuvenileNum() != null && ref.getReferralNum() != null)
	    {
		List<JJSPetition> petitions = InterviewHelper.getPetitionsForReferral(ref.getJuvenileNum(), ref);

		//Bug #80120
		/*if ( petitions.size() > 0) {
			resp.setPetitionsAvailable(true);
			resp.setPetitions(petitions);
		} else {
			resp.setPetitionsAvailable(false);
		}*/

		if (petitions != null && petitions.size() > 0)
		{
		    //add sorting to order desc order of seqnum bug fix for 129112
		    Collections.sort((List<JJSPetition>) petitions, Collections.reverseOrder(new Comparator<JJSPetition>() {
			@Override
			public int compare(JJSPetition evt1, JJSPetition evt2)
			{
			    if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
				//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
				return evt1.getSequenceNum().compareTo(evt2.getSequenceNum());
			    else
				return -1;
			}
		    }));
		    Iterator<JJSPetition> petitionsItr = petitions.iterator();
		    //Bug #80120
		    if (petitionsItr.hasNext())
		    {
			resp.setPetitionsAvailable(true);
			resp.setPetitions(petitions);
		    }

		    //JJSPetition lastPetition = petitions.get(petitions.size()-1);
		    JJSPetition lastPetition = petitions.get(0);
		    resp.setPetitionAllegation(lastPetition.getOffenseCodeId());
		    resp.setPetitionAllegationDescr(lastPetition.getOffenseCode().getDescription());
		    resp.setPetitionNumber(lastPetition.getPetitionNum());
		    resp.setPetitionStatus(lastPetition.getStatus());
		    
		    if (StringUtils.isNotEmpty(lastPetition.getOffenseCodeId()))
		    {
			//change for US180514
			 
			if(ref.getReferralTypeInd()!=null&&transOffenseReferralsIter.hasNext()&&(ref.getReferralTypeInd().equalsIgnoreCase("TR")||ref.getReferralTypeInd().equalsIgnoreCase("IC")))
			{			   
	                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();                	    
	                	    if (transOffenseReferral.getOffenseCode() != null)
	                	    {
	                		JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", transOffenseReferral.getOffenseCode());
	                		if (offenseCode != null)
	                		{
	                		    resp.setPetitionAllegationDescr(offenseCode.getLongDescription());                		    
	                		}
	                	    }
	                	    resp.setPetitionallegationCategory(transOffenseReferral.getCategoryCode());
	        		    resp.setPetitionAllegationDescrExtended(resp.getPetitionAllegationDescr() + ", " + resp.getPetitionallegationCategory());	                	
			}
			else
			{
        			JuvenileOffenseCode off = JuvenileOffenseCode.find("offenseCode",lastPetition.getOffenseCodeId());
        			if (off != null)
        			{
        			    resp.setPetitionallegationCategory(off.getCategory());
        			    resp.setPetitionAllegationDescrExtended(resp.getPetitionAllegationDescr() + ", " + resp.getPetitionallegationCategory());
        			}
			}
		    }
		    if (lastPetition.getStatus() != null && lastPetition.getStatus().equalsIgnoreCase("R"))
		    {
			resp.setPetitionStatusDescr("REOPENED");
		    }
		    else
			if (lastPetition.getStatus() != null && lastPetition.getStatus().equalsIgnoreCase("F"))
			{
			    resp.setPetitionStatusDescr("FILED");
			}
		    resp.setSequenceNum(lastPetition.getSequenceNum());

		}// end of petition

		//Offenses
		//JJSOffense tempOffense = new JJSOffense();
		int numericCode = 0;
		List<JJSOffense> offenses = InterviewHelper.getOffensesForReferral(ref.getJuvenileNum(), ref.getReferralNum());

		if (offenses != null)
		{
		    Iterator<JJSOffense> offensesItr = offenses.iterator();
		    while (offensesItr.hasNext())
		    {
			JJSOffense offense = (JJSOffense) offensesItr.next();
			if (offense != null)
			{
			    JuvenileOffenseCode offenseCode = offense.getOffenseCode();			    
			    if (offenseCode != null && !offenseCode.getNumericCode().equals(""))
			    {
				resp.setSeveritySubtype(offenseCode.getSeveritySubtype());
				int numCodeFrmOfCode = Integer.parseInt(offenseCode.getNumericCode());
				if (numericCode == 0 || numCodeFrmOfCode < numericCode)
				{
				    numericCode = numCodeFrmOfCode;
				    resp.setMostSevereOffense(JuvenileFacilityHelper.getOffenseResp(offense));
				    //tempOffense=offense;
				}
			    }
			    //task 160565
			    if(offense.getSequenceNum().equalsIgnoreCase("1"))
			    {
				JuvenileOffenseCode off = JuvenileOffenseCode.find("offenseCode",offense.getOffenseCodeId());
				    if (offenseCode != null) 
				    {
						if (off.getNcicCode() != null) 
						    resp.setNcicCode(off.getNcicCode());
						else
						    resp.setNcicCode(null);
				    }
			    }
			    
			}
			//Really bad *** Do not do this RRY
			//offense.setOffenseID(offense.getOID());  //US 71177
			JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", offense.getOffenseCodeId());
			if (offenseCode != null)
			{
				JuvenileOffenseCodeResponseEvent replyEvent = new JuvenileOffenseCodeResponseEvent();
				replyEvent.setOffenseCode(offenseCode.getOffenseCode());
				replyEvent.setNumericCode(offenseCode.getNumericCode());
				replyEvent.setShortDescription(offenseCode.getShortDescription());
				//if TR or IC get description from transferred offense
				
				if(ref.getReferralTypeInd()!=null&&transOffenseReferralsIter.hasNext()&&(ref.getReferralTypeInd().equalsIgnoreCase("TR")||ref.getReferralTypeInd().equalsIgnoreCase("IC")))
				{
								JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();                	    
								if (transOffenseReferral.getOffenseCode() != null)
								{
								JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode", transOffenseReferral.getOffenseCode());
								if (offCode != null)
								{
									replyEvent.setLongDescription(offCode.getLongDescription()+ ", " + transOffenseReferral.getCategoryCode());  
									//offenseCode.setLongDescription(offCode.getLongDescription()+ ", " + transOffenseReferral.getCategoryCode());
								}
								}
				}
				else
					replyEvent.setLongDescription(offenseCode.getLongDescription());
				replyEvent.setCategory(offenseCode.getCategory());
				replyEvent.setSeverity(offenseCode.getSeverity());
				replyEvent.setSeveritySubtype(offenseCode.getSeveritySubtype());			
				resp.getOffenseCodes().add(replyEvent);			
			}
			//Really bad *** Do not do this RRY
			//offense.setOffenseID(offense.getOID());  //US 71177
		    } //end of while(1)
		}

		if (offenses.size() > 0)
		{
		    resp.setOffensesAvailable(true);
		    resp.setOffenses(offenses);
		}
		else
		{
		    resp.setOffensesAvailable(false);
		}

		//if(tempOffense!=null && tempOffense.getOffenseCode()!=null)
		// resp.setMostSevereOffense(JuvenileFacilityHelper.getOffenseResp(tempOffense)); 
		/*// first referral;first petition num
		JJSPetition petition=InterviewHelper.getPetitionForReferral(juvenile,ref);
		if(petition!=null){
			resp.setPetitionNumber(petition.getPetitionNum());
		}*/
		//Referral Found

		resp.setReferralFound(true);
	    }
	    dispatch.postEvent(resp);
	}

    }

	public String getStatusDescripton(String status){
	    String description="";
	   
	    switch ( status ){
	    case "A":
		description = "DA ACCEPT";
		break;
	    case "C":
		description = "PRINTED";
		break;
	    case "F":
		description = "REFER TO FIRST PROGRAM";
		break;
	    case "J":
		description = "JUVENILE IDENTIFIED";
		break;
	    case "M":
		description = "MENTAL HEALTH HOLD";
		break;
	    case "D":
		description = "DISTRICT CLERK";
		break;
	    case "O":
		description = "DIVERSION 180";
		break;
	    case "P":
		description = "REFERRED TO JUV PROB DP90";
		break;
	    case "Q":
		description = "REFERRED TO JUV PROB DP180";
		break;
	    case "R":
		description = "REJECTED";
		break;
	    case "S":
		description = "SECOND CHANCE PROGRAM";
		break;
	    case "V":
		description = "VOP/PUNISHMENT";
		break;
	    case "W":
		description = "5TH WARD PROGRAM";
		break;
	    default:
		description = "";
		break;	    
	    }	    
	    return description;
	}
    /**
     * @param event
     * @roseuid 432836080273
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 432836080275
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 4328435B0093
     */
    public void update(Object updateObject)
    {

    }
}
