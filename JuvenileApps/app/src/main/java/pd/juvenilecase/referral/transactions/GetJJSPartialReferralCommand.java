package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJJSPartialReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileReferralSource;
import pd.juvenile.Juvenile;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class GetJJSPartialReferralCommand implements ICommand {
	

	/**
	 * @roseuid 4328435B0083
	 */
	public GetJJSPartialReferralCommand()
	{

	}
	

	public void execute(IEvent event)
	{
		GetJJSPartialReferralEvent refEvent = (GetJJSPartialReferralEvent) event;
		Iterator<JJSReferral> iter = JJSReferral.findAll(refEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while ( iter.hasNext() )
		{
		    	JuvenileProfileReferralListResponseEvent resp = new JuvenileProfileReferralListResponseEvent();
			JJSReferral ref = (JJSReferral) iter.next();
			resp.setTopic(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
			resp.setCourtDisposition(ref.getCourtDispositionId());
			resp.setCourtResult(ref.getCourtResultId());
			resp.setCloseDate(ref.getCloseDate());
			resp.setCtAssignJpoId(ref.getCtAssignJPOId());
			resp.setJpoId( ref.getJpoId());
			resp.setRecType( ref.getRecType());
			
			if (ref.getIntakeDecisionId() != null  && !ref.getIntakeDecisionId().equals(""))
			{
				Code intakeDescision = ref.getIntakeDecision();
				resp.setIntakeDecision(intakeDescision.getDescription());
			}
			
			resp.setReferralDate(ref.getReferralDate());
			resp.setReferralNumber(ref.getReferralNum());
			resp.setCourtDate(ref.getCourtDate());
			resp.setCourtId(ref.getCourtId());
			resp.setPiaStatus(ref.getPIACode());
			if (ref.getReferralTypeInd() != null) {
				resp.setReferralTypeInd(ref.getReferralTypeInd());
				resp.setReferralTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.REFERRAL_TYPE,ref.getReferralTypeInd()));
			}
			/*List<JJSCourt> courtList = new ArrayList<JJSCourt>();

			GetJJSCourtEvent jjsevent = new GetJJSCourtEvent();
			jjsevent.setJuvenileNumber(refEvent.getJuvenileNum());
			jjsevent.setReferralNumber(ref.getReferralNum());
			Iterator<JJSCourt> courtIter =  JJSCourt.findAll(jjsevent);
			while (courtIter.hasNext())
			{
			    JJSCourt court = courtIter.next();
				courtList.add(court);
			}
			resp.setCourts(courtList);*/
                	    if (ref.getReferralSource() != null)
                    	    {
                    		resp.setReferralSource(ref.getReferralSource());
                    		/*JuvenileReferralSource referralSrc =JuvenileReferralSource.find(ref.getReferralSource());
                    		if(referralSrc!=null){
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
			if(ref.getReferralOfficer()!=null){
				resp.setReferralOfficer(ref.getReferralOfficer());
			}
			/*if (ref.getCourtResultId() != null) {
				JuvenileDispositionCode courtDisposition = ref.getCourtResult();
				if(courtDisposition != null) {
					resp.setFinalDispositionDescription(courtDisposition.getLongDesc());
					resp.setTjpcCode(courtDisposition.getJPCCode());
					JuvenileDispositionCodeResponseEvent dispCodeRespEvt = new JuvenileDispositionCodeResponseEvent();
					dispCodeRespEvt.setCodeAlpha(courtDisposition.getCodeAlpha());
					dispCodeRespEvt.setDispositionCode(courtDisposition.getDispositionCode());
					dispCodeRespEvt.setSubGroupInd(courtDisposition.getSubGroupInd()); //added for user-story 11449.
					dispCodeRespEvt.setOptionCode(courtDisposition.getOptionCode());
					dispCodeRespEvt.setCategoryCode(courtDisposition.getCategoryCode());
					resp.setCourtResultDisposition(dispCodeRespEvt);
				}
			}
			if (ref.getCourtDispositionId() != null) {
				resp.setFinalDisposition(ref.getCourtDispositionId());
				JuvenileDispositionCode courtDisposition = ref.getCourtDisposition();
				if(courtDisposition != null) {
					resp.setFinalDispositionDescription(courtDisposition.getLongDesc());
					resp.setTjpcCode(courtDisposition.getJPCCode());
					JuvenileDispositionCodeResponseEvent dispCodeRespEvt = new JuvenileDispositionCodeResponseEvent();
					dispCodeRespEvt.setCodeAlpha(courtDisposition.getCodeAlpha());
					dispCodeRespEvt.setDispositionCode(courtDisposition.getDispositionCode());
					dispCodeRespEvt.setSubGroupInd(courtDisposition.getSubGroupInd()); //added for user-story 11449.
					dispCodeRespEvt.setOptionCode(courtDisposition.getOptionCode());
					dispCodeRespEvt.setCategoryCode(courtDisposition.getCategoryCode());
					resp.setJuvDispositionCode(dispCodeRespEvt);
				}
				
			}*/
			// Profile stripping fix task 97546
			//Juvenile juvenile = Juvenile.find( ref.getJuvenileNum() );
			Juvenile juvenile = Juvenile.findJCJuvenile( ref.getJuvenileNum() );
			//
			
			//Petition for juvenile and referral.
			/*List<JJSPetition> petitions = InterviewHelper.getPetitionsForReferral( juvenile, ref );
			if (petitions.size() > 0) {
				resp.setPetitionsAvailable(true);
				resp.setPetitions(petitions);
			} else {
				resp.setPetitionsAvailable(false);
			}
			 if(petitions!=null){
				 Iterator<JJSPetition> petitionsItr = petitions.iterator();
				 while(petitionsItr.hasNext())
		    	 {
		    		   JJSPetition petition = (JJSPetition)petitionsItr.next();
		    		   //if the referral has multiple petitions, pick the one with seq num 1
		    		   if(petition.getSequenceNum().equals("1")){
		    			   resp.setPetitionNumber(petition.getPetitionNum());
		    			   resp.setSequenceNum(petition.getSequenceNum());
		    			   break;
		    		   }
		    	 }
			 }// end of petition
*/			

			//Offenses
			 JJSOffense tempOffense = null;
			 int numericCode=0; 
			  
			/*List<JJSOffense> offenses = InterviewHelper.getOffensesForReferral(juvenile, ref );
			
			if ( offenses.size() > 0) {
				resp.setOffensesAvailable(true);
				resp.setOffenses(offenses);
			} else {
				resp.setOffensesAvailable(false);
			}
			if(offenses!=null){
				Iterator<JJSOffense> offensesItr = offenses.iterator();
				 while(offensesItr.hasNext())
		    	  {
		    		 JJSOffense offense = (JJSOffense)offensesItr.next();
		    		   if(offense!=null)
		    		   {
		    			   JuvenileOffenseCode offenseCode = offense.getOffenseCode();
		    			   if(offenseCode != null && !offenseCode.getNumericCode().equals(""))
		    			   {
			   				  int numCodeFrmOfCode=Integer.parseInt(offenseCode.getNumericCode());
			   				  if(numericCode==0 || numCodeFrmOfCode<numericCode)
			   				  {
			  					   numericCode = numCodeFrmOfCode;
			   					   tempOffense=offense;
			   				  }
		    			   }
		    		   } 
		    	  } //end of while(1)
			}*/
			if(tempOffense!=null)
			resp.setMostSevereOffense(JuvenileFacilityHelper.getOffenseResp(tempOffense)); 
			//Referral Found
			resp.setReferralFound(true);
			dispatch.postEvent(resp);
		}
		
	}
}
