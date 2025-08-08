package pd.juvenilecase.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.GetFacilityCurrentPopEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.codetable.SimpleCodeTableHelper;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import pd.km.util.Name;

public class GetFacilityCurrentPopCommand implements ICommand{
	
	/**
	 * @roseuid 42A9A3040091
	 */
	public GetFacilityCurrentPopCommand()
	{

	}
	
	/**
	 * @param event
	 * @roseuid 42A99B990128
	 */
	public void execute(IEvent event)
	{
		GetFacilityCurrentPopEvent evt = (GetFacilityCurrentPopEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		Iterator facIter = JJSFacility.findAll(evt);		
		JJSFacility facility = null;
		JuvenileCore  juvCore = null;
		JJSPetition facilityPetition = null;
		JJSCourt facilityCourt = null;		
		JJSOffense facilityOffense = null;
		JJSReferral facilityReferral = null;
		Date admitDate = null;	
		Date nextHearingDate = null;
		Date courtDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		String formattedAdmitDate = "";	
		String formattedCourtDate = "";
		String formattedNextHearingDate = "";
		Date currDate = new Date();
		int facilityCount=0;
		while (facIter.hasNext()) 
		{
			//iterate and get values for each last sequence number value
			facility = (JJSFacility) facIter.next();
			JuvenileFacilitiesCurrPopResponseEvent resp = new JuvenileFacilitiesCurrPopResponseEvent();
			nextHearingDate = facility.getNextHearingDate();
			if(nextHearingDate != null)
			{
				formattedNextHearingDate = sdf1.format(nextHearingDate);
				resp.setFormattedNextHearingDate(formattedNextHearingDate);
			}			
		
		
			//JuvenileFacilitiesCurrPopResponseEvent resp = new JuvenileFacilitiesCurrPopResponseEvent();
		
			resp.setReferralNumber(facility.getReferralNumber());
			resp.setJuvenileNum(facility.getJuvenileNumber());					
			admitDate = facility.getAdmitDate();					
			formattedAdmitDate = sdf1.format(admitDate); //bug fix: #32059
			resp.setFormattedAdmitDate(formattedAdmitDate);
			resp.setAdmitDate(admitDate);
			resp.setAdmitTime(facility.getAdmitTime().substring(0, 5).replaceAll(":", ""));
			//resp.setFacilityCode(facility.getFacilityCode());
			resp.setAdmitReason(facility.getAdmitReason());
			resp.setBookingSupervisionNum(facility.getBookingSupervisionNum());
			String secureStatus="";
			if(facility.getSecureStatus()!=null)
			{							
				if(facility.getSecureStatus().equalsIgnoreCase("N"))
					secureStatus= "NON-SECURE";
				else
					secureStatus="SECURE";
			}
			resp.setSecureStatus(facility.getSecureStatus());
			resp.setReasonDescription(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_REASON, facility.getAdmitReason())+"-"+secureStatus);
			resp.setFloorNum(facility.getFloorNum());
			resp.setUnit(facility.getUnit());
			resp.setRoomNum(facility.getRoomNum());
			resp.setJuvenileDateOfBirth(facility.getDob());  //added for US 169455
			resp.setMou(facility.getMultipleOccupyUnit());
	
			
				//calculate days difference				
				int diffInDays = 0;
				/*if(releaseDate != null && admitDate != null)
				{
					diffInDays = (int)((releaseDate.getTime() - admitDate.getTime()) / (1000 * 60 * 60 * 24));
				}
				else
				{*/
				if(admitDate != null)
					diffInDays = (int)((currDate.getTime() - admitDate.getTime()) / (1000 * 60 * 60 * 24) );
				//}
				resp.setDiffInDays(diffInDays);			    
				evt.setJuvenileNum(facility.getJuvenileNumber());
				juvCore = JuvenileCore.findCore(facility.getJuvenileNumber());			
			
				if(juvCore!=null)
				{
					//juvCore = (JuvenileCore)juvCoreItr.next();
					// setting all juvanile details respEvent				
					resp.setLastName(juvCore.getLastName());
					resp.setMiddleName(juvCore.getMiddleName());
					resp.setFirstName(juvCore.getFirstName());
					resp.setProbationOfficerId(juvCore.getPropointJPOpIdId());
					String raceId = juvCore.getRaceId();
					String hispanicInd = juvCore.getHispanicInd();
					if(raceId !=null && raceId.equals("W") && hispanicInd !=null && hispanicInd.equals("Y"))
					{
						raceId = "L";
					}
					resp.setRaceId(raceId);
					
					resp.setSexId(juvCore.getSexId());
					resp.setAgeInYears(juvCore.getAgeInYears(juvCore.getDateOfBirth()));
				
			
				
					//calling another event to get offense code from petition allegation
					/*GetPetitionOffenseCdEvent searchPetitionOffenseEvt = new GetPetitionOffenseCdEvent();
					searchPetitionOffenseEvt.setJuvenileNum(facility.getJuvenileNumber());
					searchPetitionOffenseEvt.setReferralNum(facility.getReferralNumber());
					
					Iterator offensePetitionItr = JJSPetition.findAll(searchPetitionOffenseEvt);*/
					
					GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
					petitionEvent.setJuvenileNum(facility.getJuvenileNumber());
					petitionEvent.setReferralNum(facility.getReferralNumber());
					Iterator<JJSPetition> offensePetitionItr = JJSPetition.findAll(petitionEvent);
					
					if(offensePetitionItr.hasNext())
					{
						facilityPetition = (JJSPetition) offensePetitionItr.next();
						String petitionOffenseCd = facilityPetition.getOffenseCodeId();
						resp.setOffenseCodeId(petitionOffenseCd);
						resp.setPetitionNum(facilityPetition.getPetitionNum()); 
					}
					String petitionOffenseCd = resp.getOffenseCodeId();
				    if(petitionOffenseCd == null || petitionOffenseCd.isEmpty())
				    {
						//calling another event to get offense code
						/*GetFacilityAdmitReasonOffenseCdEvent searchOffenseEvt = new GetFacilityAdmitReasonOffenseCdEvent();
						searchOffenseEvt.setJuvenileNum(facility.getJuvenileNumber());
						searchOffenseEvt.setReferralNum(facility.getReferralNumber());
						Iterator offenseItr = JJSOffense.findAll(searchOffenseEvt);	*/
				    	
				    	GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
						offEvent.setJuvenileNum(facility.getJuvenileNumber());
						offEvent.setReferralNum(facility.getReferralNumber());

						Iterator<JJSOffense> offenseItr = JJSOffense.findAll(offEvent);
						if(offenseItr != null && offenseItr.hasNext())
						{	 //taking the first offense code 
							facilityOffense = (JJSOffense) offenseItr.next();	
							resp.setOffenseCodeId(facilityOffense.getOffenseCodeId()); 					
						}
				    }
			    
				    //calling an event to find the court date from cl.court table
				   /* GetCurrentFacilityCalCourtEvent searchCourtDtEvt = new GetCurrentFacilityCalCourtEvent();			    
				    searchCourtDtEvt.setJuvenileNum(facility.getJuvenileNumber());			   
				    searchCourtDtEvt.setReferralNum(facility.getReferralNumber());
				    String petitionNum = resp.getPetitionNum();
				    searchCourtDtEvt.setPetitionNum(petitionNum); 
				    
				    Iterator courtItr = JJSCourt.findAll(searchCourtDtEvt);	*/
				    
				    GetJJSCourtEvent searchCourtDtEvt = new GetJJSCourtEvent();
				    searchCourtDtEvt.setJuvenileNumber(facility.getJuvenileNumber());
				    searchCourtDtEvt.setReferralNumber(facility.getReferralNumber());
					
					String petitionNum = resp.getPetitionNum();
					searchCourtDtEvt.setPetitionNumber(petitionNum); 
					Iterator courtItr = JJSCourt.findAll(searchCourtDtEvt);
					
					int highestSeqNum = 0;
					int highestChainNum = 0;
					int courtSeqNum = 0;
					int courtChainNum = 0;
					//iterate to get the court date of highest chain number (if multiple, with highest sequence number)
				    while(courtItr.hasNext())
				    {
				    	facilityCourt = (JJSCourt) courtItr.next(); 
				    	//courtChainNum = facilityCourt.getCourtChainNumber();	
				    	courtChainNum = Integer.parseInt(facilityCourt.getChainNumber());
						
						if(courtChainNum >= highestChainNum){	
							
							if(courtChainNum > highestChainNum){
								//new highest chain date, then reset highest seq number to zero.
								highestSeqNum = 0;
								highestChainNum = courtChainNum;
							}
							//courtSeqNum = facilityCourt.getCourtSeqNumber();
							courtSeqNum = Integer.parseInt(facilityCourt.getSeqNumber());
							if(courtSeqNum > highestSeqNum){
								highestSeqNum = courtSeqNum;
								courtDate = facilityCourt.getCourtDate();
							}
						}
					}

					if(courtDate != null)
					{
						formattedCourtDate = sdf1.format(courtDate);
						resp.setFormattedCourtDate(formattedCourtDate);
					}
			    /* Taken out for US 14461
				//calling an event to find the first JPO details
				GetFacilityCurrentFacilityOfficerCdEvent searchOfficerEvt = new GetFacilityCurrentFacilityOfficerCdEvent();
				searchOfficerEvt.setJuvenileNum(facility.getJuvenileNumber());
				searchOfficerEvt.setReferralNum(fHeader.getReferralNumber());
				Iterator jpoItr =  JJSReferral.findAll(searchOfficerEvt);
				if(jpoItr != null && jpoItr.hasNext())
				{
					facilityReferral = (JJSReferral)jpoItr.next();
					String probJPOId = facilityReferral.getProbJPOId();
					String ctAssignJPOId = facilityReferral.getCtAssignJPOId();
					String inAssignJPOId = facilityReferral.getInAssignJPOId();
					if(probJPOId != null && probJPOId.trim().length() > 0)
					{
						resp.setProbationOfficerFirstId(probJPOId);
					}
					else if(ctAssignJPOId != null && ctAssignJPOId.trim().length() > 0)
					{
						resp.setProbationOfficerFirstId(ctAssignJPOId);
					}
					else
					{
						resp.setProbationOfficerFirstId(inAssignJPOId);
					}
					
				}*/
		    
					//US 14461
					Iterator casefilesIter=null;
					//04/20/2018 - Changed for US 58305
					if(facility.getCurrentSupervisionNum()!=null && !facility.getCurrentSupervisionNum().equalsIgnoreCase(""))
					{
						casefilesIter=JuvenileCasefile.findAll("OID", facility.getCurrentSupervisionNum());		
						if(casefilesIter.hasNext())
						{
							JuvenileCasefile casefile = (JuvenileCasefile) casefilesIter.next();
							OfficerProfile officer = casefile.getProbationOfficer();
			                Name officerName = new Name(officer.getFirstName(), officer.getMiddleName(), officer.getLastName());
			                resp.setOfficerFullName(officerName.getFormattedName());						 
							resp.setOfficerUVCode(officer.getLogonId());
						}
					}
					else
					{	
						casefilesIter=JuvenileCasefile.findAll("juvenileId",facility.getJuvenileNumber());
						JuvenileCasefile recentCasefile = new JuvenileCasefile();
						Date checkDate= null;
						while(casefilesIter.hasNext())
						{
							JuvenileCasefile casefile = (JuvenileCasefile) casefilesIter.next();
							if(casefile.getCaseStatus()!=null && casefile.getCaseStatus().getCode().equalsIgnoreCase("A"))
							{
								if( (checkDate==null ||  (casefile.getActivationDate()!=null && casefile.getActivationDate().after(checkDate))) && casefile.getSupervisionCategoryId().equalsIgnoreCase("AR") )
								{
									checkDate=casefile.getActivationDate();
									recentCasefile=casefile;
								}	
							}
						}
						if(recentCasefile!=null && recentCasefile.getActivationDate()!=null)
						{
							OfficerProfile officer = recentCasefile.getProbationOfficer();
			                Name officerName = new Name(officer.getFirstName(), officer.getMiddleName(), officer.getLastName());
			                resp.setOfficerFullName(officerName.getFormattedName());						 
							resp.setOfficerUVCode(officer.getLogonId());
						}
					}
				facilityCount++;
				dispatch.postEvent(resp);
			}
				
		}
		
		
		 SearchResultsCountResponseEvent countEvent = new SearchResultsCountResponseEvent();
		 countEvent.setNumberOfResults(facilityCount);	     
	     dispatch.postEvent(countEvent);
	}
	
	/**
	 * @param event
	 * @roseuid 42A99B990131
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990133
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A99B99013A
	 */
	public void update(Object anObject)
	{

	}

}
