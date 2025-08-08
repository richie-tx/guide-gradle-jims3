 package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumRefNumEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetPetitionBySequenceEvent;
import messaging.juvenilewarrant.SaveJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.juvenilewarrant.JJSPetition;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class SaveJuvenilePetitionInformationCommand implements ICommand
{
    /**
     * execute - called by default.
     */
    public void execute(IEvent event) throws Exception
    {
	// no update always create new record with new sequence number.discussed on mjcw meeting 3/27/2018.
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	IHome home = new Home();
	JJSPetition petition = null;
	JJSPetition newPetition = new JJSPetition();
	String updateFlg="false";
	
	PetitionResponseEvent evt = new PetitionResponseEvent();
	SaveJuvenilePetitionInformationEvent petitionEvent = (SaveJuvenilePetitionInformationEvent) event;
	
	
	GetPetitionBySequenceEvent getPetitionEvt = new GetPetitionBySequenceEvent();
	getPetitionEvt.setJuvenileNum(petitionEvent.getJuvenileNum());
	getPetitionEvt.setReferralNum(petitionEvent.getReferralNum());
	getPetitionEvt.setSequenceNum(petitionEvent.getSequenceNum());

	Iterator<JJSPetition> petitionItr = new ArrayList().iterator(); // empty iterator

	
	petitionItr = JJSPetition.findAll(getPetitionEvt); // regular find. //juvenile,referral and seq num.
	
	if (petitionItr.hasNext())
	{
	    petition = petitionItr.next();	   
	}
	if(petition==null){
	    newPetition.setJuvenileNum(petitionEvent.getJuvenileNum());
	    newPetition.setLcDate(petitionEvent.getLcDate());
	    newPetition.setLcTime(petitionEvent.getLcTime());
	    newPetition.setLcUser(petitionEvent.getLcUser());
	    newPetition.setReferralNum(petitionEvent.getReferralNum());
	    newPetition.setOffenseCodeId(petitionEvent.getOffenseCodeId());
	    newPetition.setSeverity(petitionEvent.getSeverity()); 
	    newPetition.setPetitionDate(petitionEvent.getPetitionDate());
	    newPetition.setPetitionNum(petitionEvent.getPetitionNum());
	    newPetition.setStatus(petitionEvent.getStatus());
	    newPetition.setType(petitionEvent.getType());
	    newPetition.setRecType("PETITION");
	    newPetition.setAmend(petitionEvent.getAmend());
	    newPetition.setSequenceNum(String.valueOf(Integer.valueOf(petitionEvent.getSequenceNum())));
	    newPetition.setCJISNumber(petitionEvent.getCJISNum());
	    newPetition.setTjpcSeqNum(null);
	    setPetSeverity(newPetition, petitionEvent);
	    home.bind(newPetition);
	    updateFlg="true";  
	    
	}
	else
	{
	    
	    if(!petition.getStatus().equals(petitionEvent.getStatus())){
		updateFlg="true";
	    }
	    if(!petition.getPetitionDate().equals(petitionEvent.getPetitionDate())){
		updateFlg="true";
	    }
	    if(!petition.getType().equals(petitionEvent.getType())){
		updateFlg="true";
	    }
	    if(!petition.getPetitionNum().equals(petitionEvent.getPetitionNum())){
		updateFlg="true";
	    }
	    if(!petition.getOffenseCodeId().equals(petitionEvent.getOffenseCodeId())){
		updateFlg="true";
	    }
	    if(!petition.getSeverity().trim().equals(petitionEvent.getSeverity().trim())){
		updateFlg="true";
	    }
	    if(petition.getAmend()!=null && !petition.getAmend().equals(petitionEvent.getAmend())){
		updateFlg="true";
	    }
	    if(petition.getAmend()==null && (petitionEvent.getAmend()!=null && !petitionEvent.getAmend().isEmpty())){
		updateFlg="true";
	    }
	    if (updateFlg.equals("true"))
	    {
		newPetition.setJuvenileNum(petitionEvent.getJuvenileNum());
		newPetition.setLcDate(petitionEvent.getLcDate());
		newPetition.setLcTime(petitionEvent.getLcTime());
		newPetition.setLcUser(petitionEvent.getLcUser());
		newPetition.setReferralNum(petitionEvent.getReferralNum());
		newPetition.setOffenseCodeId(petitionEvent.getOffenseCodeId());
		newPetition.setSeverity(petitionEvent.getSeverity());
		newPetition.setPetitionDate(petitionEvent.getPetitionDate());
		newPetition.setPetitionNum(petitionEvent.getPetitionNum());
		newPetition.setStatus(petitionEvent.getStatus());
		newPetition.setType(petitionEvent.getType());
		newPetition.setRecType("PETITION");
		newPetition.setAmend(petitionEvent.getAmend());
		newPetition.setSequenceNum(String.valueOf(Integer.valueOf(petitionEvent.getSequenceNum()) + 1));
		newPetition.setCJISNumber(petitionEvent.getCJISNum());
		newPetition.setTjpcSeqNum(null);
		setPetSeverity(newPetition, petitionEvent);
		home.bind(newPetition);
	    }
	}
		
	GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
	courtEvt.setJuvenileNumber(petitionEvent.getJuvenileNum());
	courtEvt.setReferralNumber(petitionEvent.getReferralNum());
	Iterator<JJSCourt> courtItr = JJSCourt.findAll(courtEvt);

	JJSCourt court = null;
	while (courtItr.hasNext())
	{
	    court = courtItr.next();
	    if (court != null)
	    {
		if (court.getChainNumber().equals(petitionEvent.getCrtChainNum()) && court.getSeqNumber().equals(petitionEvent.getCrtSeqNum()))
		{
		    if (court.getAmendmentDate() != null)
		    {
			if (!court.getAmendmentDate().equals(petitionEvent.getAmendmentDate()))
			{
			    court.setAmendmentDate(petitionEvent.getAmendmentDate());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    } 
		    else
		    {
			if(petitionEvent.getAmendmentDate()!=null){
			    court.setAmendmentDate(petitionEvent.getAmendmentDate());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    }
		    // add amendment number bug 125575
		    if (court.getPetitionAmendment() != null)
		    {
			if (!court.getPetitionAmendment().equals(petitionEvent.getAmend()))
			{
			    court.setPetitionAmendment(petitionEvent.getAmend());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    } 
		    else
		    {
			if(petitionEvent.getAmend()!=null){
			    court.setPetitionAmendment(petitionEvent.getAmend());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    }
		    //bug fix for 129313
		    if (court.getFilingDate() != null)
		    {
			if (!court.getFilingDate().equals(petitionEvent.getPetitionDate()))
			{
			    court.setFilingDate(petitionEvent.getPetitionDate());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    } 
		    else
		    {
			if(petitionEvent.getPetitionDate()!=null){
			    court.setFilingDate(petitionEvent.getPetitionDate());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    }
		    if (court.getPetitionStatus() != null)
		    {
			if (!court.getPetitionStatus().equals(petitionEvent.getStatus()))
			{
			    court.setPetitionStatus(petitionEvent.getStatus());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    } 
		    else
		    {
			if(petitionEvent.getStatus()!=null){
			    court.setPetitionStatus(petitionEvent.getStatus());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    }
		    if (court.getPetitionNumber() != null)
		    {
			if (!court.getPetitionNumber().equals(petitionEvent.getPetitionNum()))
			{
			    court.setPetitionNumber(petitionEvent.getPetitionNum());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    } 
		    else
		    {
			if(petitionEvent.getPetitionNum()!=null){
			    court.setPetitionNumber(petitionEvent.getPetitionNum());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    }
		    if (court.getPetitionAllegation() != null)
		    {
			if (!court.getPetitionAllegation().equals(petitionEvent.getOffenseCodeId()))
			{
			    court.setPetitionAllegation(petitionEvent.getOffenseCodeId());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    } 
		    else
		    {
			if(petitionEvent.getOffenseCodeId()!=null){
			    court.setPetitionAllegation(petitionEvent.getOffenseCodeId());
			    home.bind(court);
			    updateFlg="true";
			    //break;
			}
		    }
		    //
		    if(updateFlg.equalsIgnoreCase("true"))
			break;
		}
		// uncomment this part if they wanna modify all future district settings from that petition date with modified pet num
    		/*//can add it into same while loop for court then detention outside of while loop
    		// task 129603
    		//check chainnumber==petchainnumber&&hearingtype DT and courtdate>petition date
    		if (court.getChainNumber().equals(petitionEvent.getCrtChainNum())&&court.getHearingType().equalsIgnoreCase("DT")
    			&&(court.getCourtDate()==petitionEvent.getPetitionDate()||court.getCourtDate().after(petitionEvent.getPetitionDate())) )
    		{
    		    court.setPetitionNumber(petitionEvent.getPetitionNum()); 
    		    home.bind(court);
    		}*/
		
	      }
	    }
	    
	// task 129603- find all detention records with court date > pettion date for same juvenile,referral, chain and hearingtype DT
	//and update to the petition number petitionEvent.getPetitionNum()
	//use GetJJSCLDetentionByJuvNumRefNumEvent or GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent
	GetJJSCLDetentionByJuvNumRefNumEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUM);
	detentionEvt.setJuvenileNumber(petitionEvent.getJuvenileNum());
	detentionEvt.setReferralNumber(petitionEvent.getReferralNum());
	Iterator<JJSCLDetention> detItr = JJSCLDetention.findAll(detentionEvt);

	JJSCLDetention det = null;
	while (detItr.hasNext())
	{
	    det = detItr.next();
	    if (det != null)
	    {
		if (det.getHearingType().equalsIgnoreCase("DT")
    			&&(det.getCreateTimestamp()==petitionEvent.getPetitionDate()||det.getCreateTimestamp().after(petitionEvent.getPetitionDate())) )//det.getCourtId().equalsIgnoreCase("300")&&det.getChainNumber().equals(petitionEvent.getCrtChainNum())&&
    		{
		    det.setPetitionNumber(petitionEvent.getPetitionNum()); 
    		    home.bind(det);
    		}
	    }
	}
	evt.setUpdateFlag(updateFlg);
	dispatch.postEvent(evt);
    }

    private void setPetSeverity(JJSPetition newPetition, SaveJuvenilePetitionInformationEvent petitionEvent)
    {
	Collection<JuvenileProfileReferralListResponseEvent> juvProfResEvt = JuvenileFacilityHelper.getJuvReferralDetails(petitionEvent.getJuvenileNum());
	Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = juvProfResEvt.iterator();
	List<Integer> severityList = new ArrayList();
		
	while (juvProfResItr.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent ref = juvProfResItr.next();
	    if (ref != null && ref.getReferralTypeInd() != null
		    && ref.getReferralTypeInd().equalsIgnoreCase("TR") && ref.getReferralNumber().equalsIgnoreCase(petitionEvent.getReferralNum()))
	    {
		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
		getEvent.setJuvenileNumber(petitionEvent.getJuvenileNum());
		getEvent.setReferralNumber(ref.getReferralNumber());

		Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);
		
		severityList.add(0);

		while (transOffenseReferralsIter.hasNext())
		{
		    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();

		    if (transOffenseReferral != null
			    && transOffenseReferral.getOffenseCode() != null
			    && transOffenseReferral.getOffenseCode() != "")
		    {
			JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", transOffenseReferral.getOffenseCode());

			if (offenseCode != null && offenseCode.getSeverity() != null && offenseCode.getSeverity() != "")
			{
			    try {
	                            int severity = Integer.parseInt(offenseCode.getSeverity()); 
	                            severityList.add(severity); 
	                        } catch (NumberFormatException e) {
	                        }
			}
		    }
		}
	    }
	}
	
	if (!severityList.isEmpty()) {
	        int maxSeverity = Collections.max(severityList); 
	        newPetition.setSeverity(String.valueOf(maxSeverity)); 
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.Ipetition)
     */
    public void onRegister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
	// TODO Auto-generated method stub

    }

}
