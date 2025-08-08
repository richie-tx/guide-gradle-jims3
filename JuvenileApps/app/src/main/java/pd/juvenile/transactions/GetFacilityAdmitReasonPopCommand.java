package pd.juvenile.transactions;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.juvenile.GetFacilityAdmitReasonPopEvent;
import messaging.juvenilecase.GetFacilityAdmitReasonOffenseCdEvent;
import messaging.juvenilecase.reply.FacilityAdmitReasonResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileAdmitReasons;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.referral.JJSOffense;

public class GetFacilityAdmitReasonPopCommand implements ICommand 

{

 /**
  * @roseuid 45702FB40066
  */
 public GetFacilityAdmitReasonPopCommand() 
 {
 }

 /**
  * @param event
  * @roseuid 456F2D850264
  */


 
	public void execute(IEvent event) 
	{
		GetFacilityAdmitReasonPopEvent evt = (GetFacilityAdmitReasonPopEvent) event;
		//Iterator headItr = JJSHeader.findAll("headerFacility", evt.getFacilityId());
		Iterator facilityItr = JJSFacility.findAll(evt);
		Date releaseDate = null;
		Date admitDate = null;
		Date currDate = new Date();
		FacilityAdmitReasonResponseEvent respEvent = null;		
		JJSFacility facility = null;
		JuvenileCore  juvCore = null;
		JJSOffense facilityOffense = null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
		String formattedAdmitDate = "";	
		//int count = 0;
		while (facilityItr.hasNext()) 
		{
			//iterate and get values for each last sequence number value
			facility = (JJSFacility) facilityItr.next();					
			respEvent = new FacilityAdmitReasonResponseEvent();
							
			respEvent.setReleaseDate(facility.getReleaseDate()); 
			admitDate = facility.getAdmitDate();
			formattedAdmitDate = sdf.format(admitDate);
			respEvent.setFormattedAdmitDate(formattedAdmitDate);
			respEvent.setAdmitDate(admitDate);
			respEvent.setReasonCode(facility.getAdmitReason());
			String secureStatus="";
			if(facility.getSecureStatus()!=null)
			{							
				if(facility.getSecureStatus().equalsIgnoreCase("N"))
					secureStatus= "NON-SECURE";
				else
					secureStatus="SECURE";
			}
			respEvent.setSecureStatus(facility.getSecureStatus());
			
			// added code to load description of admit reason Bug14118
			JuvenileAdmitReasons juvAdmitReasons = JuvenileAdmitReasons.find("code",facility.getAdmitReason());					
			respEvent.setReasonDescription(juvAdmitReasons.getDescription()+"-"+secureStatus);
			//respEvent.setReasonDescription(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_REASON, facility.getAdmitReason())+"-"+secureStatus);
		
			
				//calculate days difference
				int diffInDays = 0;
				//changed for User Story 22935
				/*if(releaseDate != null && admitDate != null)
				{
					diffInDays = (int)((releaseDate.getTime() - admitDate.getTime()) / (1000 * 60 * 60 * 24) ); 
				}
				else
				{*/
				if(admitDate != null)
					diffInDays = (int)((currDate.getTime() - admitDate.getTime()) / (1000 * 60 * 60 * 24) +1 );
				//}
				respEvent.setDiffInDays(diffInDays);	 
				evt.setJuvenileNum(facility.getJuvenileNumber());
				juvCore = new JuvenileCore();
				juvCore = JuvenileCore.findCore(facility.getJuvenileNumber());
				if (juvCore!=null)
				{
					//juvCore = (JuvenileCore)juvCoreItr.next(); 
								
				
					// setting all juvenile details in respEvent
					respEvent.setJuvenileNum(juvCore.getJuvenileNum());
					respEvent.setLastName(juvCore.getLastName()); 
					respEvent.setMiddleName(juvCore.getMiddleName());
					respEvent.setFirstName(juvCore.getFirstName());
					
					String raceId = juvCore.getRaceId();
					String hispanicInd = juvCore.getHispanicInd();
					if(raceId !=null && raceId.equals("W") && hispanicInd !=null && hispanicInd.equals("Y"))
					{
						raceId = "L";
					}
					respEvent.setRaceId(raceId);					
					respEvent.setSexId(juvCore.getSexId());
					respEvent.setAgeInYears(juvCore.getAgeInYears(juvCore.getDateOfBirth()));
				
				
				
					//calling another event to get offense code
					/*GetFacilityAdmitReasonOffenseCdEvent searchOffenseEvt = new GetFacilityAdmitReasonOffenseCdEvent();
					searchOffenseEvt.setJuvenileNum(facility.getJuvenileNumber());
					searchOffenseEvt.setReferralNum(facility.getReferralNumber());
					Iterator offenseItr = JJSOffense.findAll(searchOffenseEvt);	*/
					
					
			    	GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
					offEvent.setJuvenileNum(facility.getJuvenileNumber());
					offEvent.setReferralNum(facility.getReferralNumber());

					Iterator<JJSOffense> offenseItr = JJSOffense.findAll(offEvent);
					if(offenseItr.hasNext())
					{	 //taking the first offense code 
						facilityOffense = (JJSOffense) offenseItr.next();	
						respEvent.setOffenseCodeId(facilityOffense.getOffenseCodeId()); 					
						// added code to load short desc of offense code
						JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",facilityOffense.getOffenseCodeId());
						respEvent.setOffenseDescription(offenseCode.getShortDescription());
					}	
					dispatch.postEvent(respEvent); 
				}
			
		}//end of facility iteration
	}

 
 
 /**
  * @param event
  * @roseuid 456F2D850272
  */
 public void onRegister(IEvent event)
 { 

 }

 
 /**
  * @param event 
  * @roseuid 456F2D850274
  */

 public void onUnregister(IEvent event) 
 {

 }

 /**
  * @param anObject
  * @roseuid 456F2D850276 
  */

 public void update(Object anObject) 
 { 

 }

 
}