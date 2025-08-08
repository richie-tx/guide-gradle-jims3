//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileReferralsCommand.java

package pd.juvenilecase.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileReferralsEvent;
import messaging.juvenilecase.GetJuvenileCasefileRiskNeedLevelEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.juvenile.Juvenile;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.PACTRiskNeedLevel;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import ui.common.SimpleCodeTableHelper;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileCasefileReferralsCommand implements ICommand 
{
   
   /**
    * @roseuid 4278CAAB00DD
    */
   public GetJuvenileCasefileReferralsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4278C7B90289
    */
   public void execute(IEvent event) 
   {
   		GetJuvenileCasefileReferralsEvent refEvent = (GetJuvenileCasefileReferralsEvent) event;
   		Iterator referrals = Assignment.findAll("caseFileId", refEvent.getSupervisionNum());
   		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		while (referrals.hasNext())
   		{
   			Assignment ref = (Assignment) referrals.next();
   			
			JuvenileCasefileReferralsResponseEvent resp = new JuvenileCasefileReferralsResponseEvent();
			resp.setTopic(PDJuvenileCaseConstants.JUVENILE_CASEFILE_REFERRAL_TOPIC);
			resp.setAssignmentId((String) ref.getOID());
			resp.setAssignmentDate(ref.getAssignmentAddDate());
			resp.setRefSeqNum(ref.getSeqNum());
			if(ref.getCaseFile()!=null)
				resp.setProbationOfficerId(ref.getCaseFile().getProbationOfficerId());
			// get the jjs referral and get the referral date
			GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
			jjsEvt.setJuvenileNum(refEvent.getJuvenileNum());
			jjsEvt.setReferralNum(ref.getReferralNumber());
			Iterator i = JJSReferral.findAll(jjsEvt);
			if(i.hasNext()) {
				JJSReferral jjs = (JJSReferral)i.next();
				resp.setReferralDate(jjs.getReferralDate());
				resp.setCourtDate(jjs.getCourtDate());
				resp.setRefCloseDate(DateUtil.dateToString(jjs.getCloseDate(), DateUtil.DATE_FMT_1));
				resp.setProbationstartDate(jjs.getProbationStartDate());
				resp.setProbationendDate(jjs.getProbationEndDate());
				resp.setRecType(jjs.getRecType());
				if(jjs.getIntakeDecision()!=null)
				{
					resp.setIntakeDecision(jjs.getIntakeDecision().getDescription());
					resp.setIntakeDecisionCode(jjs.getIntakeDecision().getCode());
					resp.setIntakeDate(DateUtil.dateToString(jjs.getIntakeDate(), DateUtil.DATE_FMT_1));
					JuvenileReferralDispositionCode juvReferralDispCode = jjs.getIntakeDecisionComplex();
					if (juvReferralDispCode != null) {
						resp.setIntakeDecisionTJPCCode(juvReferralDispCode.getTjpcCode());
					}
				}
				resp.setCourtId(jjs.getCourtId());
				resp.setPdaDate(DateUtil.dateToString(jjs.getPDADate(), DateUtil.DATE_FMT_1));
				if (jjs.getCourtDispositionId() != null) {
					resp.setFinalDisposition(jjs.getCourtDispositionId());
					resp.setDispositionCode(jjs.getCourtDispositionId());
					JuvenileDispositionCode courtDisposition = jjs.getCourtDisposition();
					if(courtDisposition != null) {
						resp.setFinalDispositionDescription(courtDisposition.getLongDesc());
						resp.setFinalDispositionTJPCCode(courtDisposition.getJPCCode());
						// add subgroupind to resp task 124903
						resp.setDispositionSubgroup(courtDisposition.getSubGroupInd());
					}
					
					
				}
				//add code to assign disposition subgroup to response
				//US 71184 need description for the Court Result
				
				if (jjs.getCourtResultId() != null) {
					resp.setCourtResult(jjs.getCourtResultId());
					//resp.setDispositionCode(jjs.getCourtDispositionId());
					JuvenileDispositionCode courtResult = jjs.getCourtResult();
					if(courtResult != null) {
						resp.setCourtResultDesc(courtResult.getLongDesc());
						JuvenileDispositionCodeResponseEvent dispCodeRespEvt = new JuvenileDispositionCodeResponseEvent();
						dispCodeRespEvt.setCodeAlpha(courtResult.getCodeAlpha());
						dispCodeRespEvt.setDispositionCode(courtResult.getDispositionCode());
						//resp.setDispositionCode(courtResult.getDispositionCode());
						resp.setJuvDispositionCode(dispCodeRespEvt);
					}
					
				}
				if (jjs.getReferralTypeInd() != null) {
					resp.setReferralTypeInd(jjs.getReferralTypeInd());
					//ER changes 11054
					resp.setReferralTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.REFERRAL_TYPE,jjs.getReferralTypeInd()));
				}
				
				//Profile stripping fix task 97546
				//Juvenile juvenile = Juvenile.find( jjs.getJuvenileNum() );
				Juvenile juvenile = Juvenile.findJCJuvenile( jjs.getJuvenileNum() );
				//
				
				//Petition
				List<PetitionResponseEvent>  petitions = InterviewHelper.getPetitionsRespForReferral( juvenile, jjs );
											
				if ( petitions.size() > 0) {
					resp.setPetitionsAvailable(true);
					resp.setPetitions(petitions);
				} else {
					resp.setPetitionsAvailable(false);
				}
				
				 if(petitions!=null&& petitions.size() > 0){
				     //sorting in desc order of seq num in petition to get the latest update task 140341
				     Collections.sort((List<PetitionResponseEvent>) petitions, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
						@Override
						public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
						{
						    if (evt1.getSequenceNum()!= null && evt2.getSequenceNum() != null)							
							return evt1.getSequenceNum().compareTo(evt2.getSequenceNum());
						    else
							return -1;
						}
					    }));				     
				     	     PetitionResponseEvent lastPetition = petitions.get(0);			    		  
					     resp.setPetitionNumber(lastPetition.getPetitionNum());
					     resp.setPetitionAllegation(lastPetition.getOffenseCodeId());
					     resp.setPetitionAllegDscr(lastPetition.getOffenseShortDesc());
					     resp.setPetitionStatus(lastPetition.getPetitionStatus());
					     if(lastPetition.getPetitionStatus()!= null && lastPetition.getPetitionStatus().equalsIgnoreCase("R"))
			    			 resp.setPetitionStatusDescr("REOPENED");
					     else if (lastPetition.getPetitionStatus()!= null && lastPetition.getPetitionStatus().equalsIgnoreCase("F"))
			    			 resp.setPetitionStatusDescr("FILED");			    			  
				 }// end of petition
				//Offenses
				//List offenses = InterviewHelper.getOffensesForReferral(juvenile, jjs );
				List offenses= InterviewHelper.getOffensesForReferral(jjs.getJuvenileNum(), jjs.getReferralNum() );
				//List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(refEvent.getJuvenileNum());
				
				
				if ( offenses.size() > 0) {
					
					
					List offenseResponseEvents = new ArrayList();
					Iterator iter = offenses.iterator();
					while (iter.hasNext())
			    	{
			    		JJSOffense o = (JJSOffense) iter.next();
			    		
			    		JJSOffenseResponseEvent jjsOffenseResponseEvent = new JJSOffenseResponseEvent();
			    		
			    		jjsOffenseResponseEvent.setTopic(PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC);
			    		jjsOffenseResponseEvent.setJuvenileNum(o.getJuvenileNum());
			    		jjsOffenseResponseEvent.setOffenseDate(o.getOffenseDate());
			    		jjsOffenseResponseEvent.setOffenseDescription(o.getOffenseDescription());
			    		jjsOffenseResponseEvent.setReferralNum(o.getReferralNum());
			    		jjsOffenseResponseEvent.setSequenceNum(o.getSequenceNum());
			    		jjsOffenseResponseEvent.setCatagory(o.getCatagory());
			    		jjsOffenseResponseEvent.setCitationCode(o.getCitationCode());
			    		jjsOffenseResponseEvent.setCitationSource(o.getCitationSource());
			    		jjsOffenseResponseEvent.setSequenceNum(o.getSequenceNum());
			    		jjsOffenseResponseEvent.setInvestigationNum(o.getInvestigationNumber());
			    		jjsOffenseResponseEvent.setOffenseReportGroup(o.getOffenseReportGroup());
			    		jjsOffenseResponseEvent.setOffenseCodeId(o.getOffenseCodeId());
			    		jjsOffenseResponseEvent.setOffenseLevelId(o.getOID());
			    		JuvenileOffenseCode juvOffCode = o.getOffenseCode();
			    		if (juvOffCode != null) {
							jjsOffenseResponseEvent.setSeveritySubtype(juvOffCode.getSeveritySubtype());
					}
			    		offenseResponseEvents.add(jjsOffenseResponseEvent);
			    		if (o.getSequenceNum().equalsIgnoreCase("1")) {
			    		    	resp.setOffense(o.getOffenseCodeId());
			    		    	resp.setOffenseDesc(o.getOffenseDescription());
					}
			    		
			    		JuvenileOffenseCode offenseCode = o.getOffenseCode();
		    			
			    	}
					
					resp.setOffenseResponseEvents(offenseResponseEvents);
					resp.setOffensesAvailable(true);
					resp.setOffenses(offenses);
				} else {
					resp.setOffensesAvailable(false);
				}
				
				//Referral Found
				resp.setReferralFound(true);
				
			}
			
			resp.setCasefileId(ref.getCaseFileId());
			resp.setReferralNumber(ref.getReferralNumber());
			
			
			// Get Assignment Level
			JuvenileLevelUnitCode level = ref.getAssignmentLevel();
			if (level != null)
			{
				resp.setAssignmentLevel(level.getLevelDescription());
			}
			
			// Get the Service Unit
			JuvenileLevelUnitCode service = ref.getServiceUnit();
			if (service != null)
			{
				resp.setServiceUnit(service.getUnitDescription());	
			}	
			
			//added for US 41483 - Get the Risk/Need Level
			GetJuvenileCasefileRiskNeedLevelEvent riskNeedLvlEvt = new GetJuvenileCasefileRiskNeedLevelEvent();
			riskNeedLvlEvt.setCaseFileId(refEvent.getSupervisionNum());
			riskNeedLvlEvt.setJuvenileNum(refEvent.getJuvenileNum());
			riskNeedLvlEvt.setReferralNumber(ref.getReferralNumber());
			
			Iterator riskNeedIter = PACTRiskNeedLevel.findAll(riskNeedLvlEvt);
			PACTRiskNeedLevel tempRiskNeedLevel = new PACTRiskNeedLevel();
			while(riskNeedIter.hasNext())
			{
				PACTRiskNeedLevel rnLevel = (PACTRiskNeedLevel) riskNeedIter.next();
				if(rnLevel.getStatus()!=null && rnLevel.getStatus().equalsIgnoreCase("CURRENT"))
				{
					tempRiskNeedLevel= rnLevel;
					break;
				}
				else if(rnLevel.getStatus()!=null && !rnLevel.getStatus().equalsIgnoreCase("CURRENT"))
				{
					if(tempRiskNeedLevel.getCreateTimestamp()== null || tempRiskNeedLevel.getCreateTimestamp().before(rnLevel.getCreateTimestamp()))
						tempRiskNeedLevel= rnLevel;
					
				}
				
			}
			if(tempRiskNeedLevel.getRiskLvl() !=null)
			{
				resp.setRiskNeedLvl(tempRiskNeedLevel.getRiskLvl()+'/'+tempRiskNeedLevel.getNeedsLvl());
				resp.setRiskNeedLvlDesc(SimpleCodeTableHelper.getDescrByCode("RISKNEEDSLVL", tempRiskNeedLevel.getRiskLvl()) + '/' +SimpleCodeTableHelper.getDescrByCode("RISKNEEDSLVL", tempRiskNeedLevel.getNeedsLvl()));
			}
			resp.setPactStatus(tempRiskNeedLevel.getStatus());
			resp.setLastPactDate(DateUtil.dateToString(tempRiskNeedLevel.getLastPactDate(), DateUtil.DATE_FMT_1));
			
			dispatch.postEvent(resp);			
   		}  
   }
   
   /**
    * @param event
    * @roseuid 4278C7B90292
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4278C7B90294
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4278C7B902A6
    */
   public void update(Object anObject) 
   {
    
   }
}
