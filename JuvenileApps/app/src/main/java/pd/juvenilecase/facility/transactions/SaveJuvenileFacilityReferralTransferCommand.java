package pd.juvenilecase.facility.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumRefNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityReferralTransferEvent;
import messaging.facility.reply.JuvenileFacilityReleaseResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSReferral;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class SaveJuvenileFacilityReferralTransferCommand implements ICommand {

	@Override
	public void execute(IEvent event) throws Exception {
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		SaveJuvenileFacilityReferralTransferEvent reqEvent = (SaveJuvenileFacilityReferralTransferEvent) event;
		JuvenileFacilityReleaseResponseEvent respEvent = new JuvenileFacilityReleaseResponseEvent();

		IHome home = new Home();

		// update header and facility.
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(reqEvent.getJuvenileNum());		
		
		JJSFacility facility = JuvenileFacilityHelper.getJJSDetention(reqEvent.getJuvenileNum(), reqEvent.getFacilitySequenceNum());
				
		// code optimization.	
		 if(facility!=null){
			 boolean hasMoreRecs = JuvenileFacilityHelper.checkIfJJSFacilityHasMoreThanOneRecord(reqEvent.getJuvenileNum());
			 if(hasMoreRecs){
				 facility.setFacilitySequenceNumber(String.valueOf(Integer.parseInt(reqEvent.getFacilitySequenceNum())+20));
			 }
			 facility.setBookingSupervisionNum(reqEvent.getBookingSupervision());
			 facility.setCurrentSupervisionNum(reqEvent.getCurrentSupervision());
			 facility.setReferralNumber(reqEvent.getBookingReferral());
			 facility.setCurrentReferral(reqEvent.getCurrentReferral());
			 facility.setCurrentOffense(reqEvent.getCurrentOffenseCode()); //task 148752
		 	}
		 if(header!=null){
			 header.setReferralNumber(reqEvent.getCurrentReferral());
			 header.setBookingSupervisionNum(reqEvent.getCurrentSupervision());	 		 
			 header.setLastSequenceNumber(String.valueOf(Integer.parseInt(reqEvent.getFacilitySequenceNum())+20));
		 }
		 
		  /** 
		   * M204 Legacy Update:  
		     Delete the FinalReleaseDate from the JUVENILE_JJS_REFERRAL record for the BookingReferral of the JUVENILE_FACILITY_ADMISSION_RELEASE record
		   */
		    //update the transferFrom Referral Record. 
			GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
			jjsEvt.setJuvenileNum(reqEvent.getJuvenileNum());
			jjsEvt.setReferralNum(reqEvent.getTransferFromReferral());
			Date admitDate = null;
			
			Iterator<JJSReferral> referralItr = JJSReferral.findAll(jjsEvt);
			if(referralItr.hasNext()) {
				JJSReferral jjsReferral = referralItr.next();
				 admitDate = jjsReferral.getAdmitDate();
				 jjsReferral.setFinalReleaseDate(null);
				 jjsReferral.setAdmitDate(null);
				 try{
			 		home.bind(jjsReferral);
					}catch(Exception ex){
						ErrorResponseEvent evt = new ErrorResponseEvent();
			 			evt.setException(ex);
			 			evt.setMessage("****M204 Exception while updating JJS_REFERRAL-TRANSFER FROM****");
			 			dispatch.postEvent(evt);
					}
				}
			    //update the transferTo Referral Record. 
				jjsEvt.setJuvenileNum(reqEvent.getJuvenileNum());
				jjsEvt.setReferralNum(reqEvent.getBookingReferral());
				
				Iterator<JJSReferral> referralItr2 = JJSReferral.findAll(jjsEvt);
				if(referralItr2.hasNext()) {
					JJSReferral jjsReferral = referralItr2.next();
					if(jjsReferral.getFinalReleaseDate()==null || (jjsReferral.getFinalReleaseDate()!=null)){
						jjsReferral.setAdmitDate(admitDate);
						jjsReferral.setFinalReleaseDate(null);
						try{
							home.bind(jjsReferral);
						}catch(Exception ex){
							ErrorResponseEvent evt = new ErrorResponseEvent();
				 			evt.setException(ex);
				 			evt.setMessage("****M204 Exception while updating JJS_REFERRAL-TRANSFER TO****");
				 			dispatch.postEvent(evt);
						}
					}
				}
				
				/*//Update the detention calendar record. Just the referral no: #40457 //commented for U.S 81390
				ArrayList<JJSCalendarDetention> detentionList = new ArrayList<JJSCalendarDetention>();
				GetJJSCalendarDetentionEvent calDetEvt = new GetJJSCalendarDetentionEvent();
				calDetEvt.setJuvenileNumber(reqEvent.getJuvenileNum());
				calDetEvt.setReferralNumber(reqEvent.getTransferFromReferral());
				
		    	Iterator<JJSCalendarDetention> calDetenItr = JJSCalendarDetention.findAll(calDetEvt);//JJSCalendarDetention.findAll("juvenileNumber",reqEvent.getJuvenileNum());
		    	while(calDetenItr.hasNext()){
		    		JJSCalendarDetention calDet = calDetenItr.next();
		    		if(calDet!=null){
		    			detentionList.add(calDet);
		    		}
		    	}
		    	
		    	//Collections.sort(detentionList,Collections.reverseOrder()); //modify the one with the highest RefNum
		    	Collections.sort((List<JJSCalendarDetention>)detentionList,Collections.reverseOrder(new Comparator<JJSCalendarDetention>() { //highest seq num.
					@Override
					public int compare(JJSCalendarDetention evt1, JJSCalendarDetention evt2) {
						return (evt1.getSeqNumber()).compareTo(evt2.getSeqNumber());
					}
			}));*/
				
			// u.s 81390. Repleaced the event and the pd class from to JJSCLDETENTION AND GetJJSCLDetentionByJuvNumRefNumEvent 	
			//Update the detention calendar record. Just the referral no: #40457
                	ArrayList<JJSCLDetention> detentionList = new ArrayList<JJSCLDetention>();
                	GetJJSCLDetentionByJuvNumRefNumEvent calDetEvt = new GetJJSCLDetentionByJuvNumRefNumEvent();
                	calDetEvt.setJuvenileNumber(reqEvent.getJuvenileNum());
                	calDetEvt.setReferralNumber(reqEvent.getTransferFromReferral());
                
                	Iterator<JJSCLDetention> calDetenItr = JJSCLDetention.findAll(calDetEvt);//JJSCalendarDetention.findAll("juvenileNumber",reqEvent.getJuvenileNum());
                	while (calDetenItr.hasNext())
                	{
                	    JJSCLDetention calDet = calDetenItr.next();
                	    if (calDet != null)
                	    {
                		detentionList.add(calDet);
                	    }
                	}
                
                	//Collections.sort(detentionList,Collections.reverseOrder()); //modify the one with the highest RefNum
                	//Bug 88390 - need to look at the latest chain number and not seq num
                	Collections.sort((List<JJSCLDetention>) detentionList, Collections.reverseOrder(new Comparator<JJSCLDetention>() { //highest seq num.
                	    @Override
                	    public int compare(JJSCLDetention evt1, JJSCLDetention evt2)
                	    {
                		return (evt1.getChainNumber()).compareTo(evt2.getChainNumber());
                	    }
                	}));
                
                	Iterator<JJSCLDetention> detentionItr = detentionList.iterator();
                	//Bug #40457
                	//Bug 88390 - get the highest chain number for that Ref and change the Ref# for all recs in that chain
                	String highestChainNum = "";
                	if ( detentionList != null && detentionList.size() > 0) {
                	    highestChainNum = detentionList.get(0).getChainNumber();
                	}
                	
                	while (detentionItr.hasNext())
                	{
                	    JJSCLDetention calDetention = detentionItr.next();
                	    if (calDetention.getReferralNumber().equalsIgnoreCase(reqEvent.getTransferFromReferral())
                		    && (calDetention.getChainNumber()!=null && calDetention.getChainNumber().equals(highestChainNum)))
                	    {
                		calDetention.setReferralNumber(reqEvent.getBookingReferral());
                		//update calendar detention.
                		try
                		{
                		    home.bind(calDetention);
                		}
                		catch (Exception ex)
                		{
                		    ErrorResponseEvent evt = new ErrorResponseEvent();
                		    evt.setException(ex);
                		    evt.setMessage("****M204 Exception while updating CAL DETENTION - TRANSFER TO****");
                		    dispatch.postEvent(evt);
                		}
                	    }
                	}
                	//update the facility and header information.
                	try
                	{
                	    home.bind(facility);
                	    home.bind(header);
                	}
                	catch (Exception ex)
                	{
                	    ErrorResponseEvent evt = new ErrorResponseEvent();
                	    evt.setException(ex);
                	    evt.setMessage("****Exception while updating JJS_DETENTION / JJS HEADER****");
                	    dispatch.postEvent(evt);
                	}
                	respEvent.setSaveable(true);
                	dispatch.postEvent(respEvent);
                    }
}
